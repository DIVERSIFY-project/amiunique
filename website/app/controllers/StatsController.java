package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import models.CombinationStatsEntityManager;
import models.FpDataEntity;
import models.FpDataEntityManager;
import models.Stats;
import play.cache.Cache;
import play.libs.Crypto;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.stats;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;


public class StatsController extends Controller{

    public static Result percentages(){
        String enc = request().body().asText();
        String clear = Crypto.decryptAES(enc);
        JsonNode n = Json.parse(clear);
        try {
            Integer counter = n.get("c").asInt();
            FpDataEntityManager em = new FpDataEntityManager();
            FpDataEntity fp = em.getExistingFPByCounter(counter);
            CombinationStatsEntityManager emc = new CombinationStatsEntityManager();
            ObjectNode node = (ObjectNode) Json.toJson(fp);
            node.remove("counter");
            node.remove("octaneScore");
            node.remove("sunspiderTime");
            node.remove("addressHttp");
            node.remove("time");
            node.remove("hostHttp");
            node.remove("connectionHttp");
            node.remove("orderHttp");
            node.remove("ieDataJs");
            node.remove("id");
            node.remove("vendorWebGljs");
            node.remove("rendererWebGljs");
            node.remove("webGlJs");
            String pluginsJs = fp.getPluginsJs();
            node.remove("pluginsJs");
            node.remove("canvasJs");
            node.remove("fontsFlash");
            node.remove("webGLJsHashed");

            JsonNode json = (JsonNode) node;
            Map<String,Double> percentages = emc.getPercentages(json);
            Map<String,Double> percentagesPlugins = emc.getPercentagesPlugins(pluginsJs);

            percentages.put("pluginsJs", percentages.get("pluginsJsHashed"));
            percentages.put("canvasJs", percentages.get("canvasJsHashed"));
            percentages.put("fontsFlash", percentages.get("fontsFlashHashed"));
            percentages.remove("pluginsJsHashed");
            percentages.remove("canvasJsHashed");
            percentages.remove("fontsFlashHashed");

            ObjectNode nodePer = (ObjectNode) Json.toJson(percentages);

            JsonNode jsonPerPlugins = Json.toJson(percentagesPlugins);
            nodePer.put("perPluginsJs", jsonPerPlugins);

            json = (JsonNode) nodePer;

            return ok(json);
        } catch (Exception e){
            return badRequest();
        }
    }

    public static Result stats() throws Exception{
        return ok(Cache.getOrElse("stats-html", () -> {
            Stats s = Stats.getInstance();
            return stats.render(s.getNbTotal(),Json.toJson(s.getTimezone()),Json.toJson(s.getBrowsers()),
                    Json.toJson(s.getOs()),Json.toJson(s.getLanguages()),Json.toJson(s.getNbFonts()),"","","");
        }, 60*60*3));
    }

    /*
    Method called through a form on "stats" page
    It receives 2 dates (upper and lower)
    It reloads the page with new parameters for the charts
    */
    public static Result statsTime(){
        Map<String, String[]> vals = request().body().asFormUrlEncoded();
        String datelString = vals.get("datel")[0];
        String dateuString = vals.get("dateu")[0];
        String typeReq = vals.get("typereq")[0];

        //We check if the information is already in cache
        if((typeReq.equals("month") && Cache.get("monthStats") != null) || (typeReq.equals("week") && Cache.get("weekStats") != null)){
            Stats s = (Stats) Cache.get(typeReq+"Stats");

            return ok(stats.render(s.getNbTotal(), Json.toJson(s.getTimezone()), Json.toJson(s.getBrowsers()),
                    Json.toJson(s.getOs()), Json.toJson(s.getLanguages()), Json.toJson(s.getNbFonts()), datelString, dateuString, typeReq));
        }

        //Only if custom or if the information is not in cache
        try{
            DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
            Date datel = dateFormat.parse(datelString);
            Date dateu = dateFormat.parse(dateuString);

            Timestamp datelTs = new Timestamp(datel.getTime());
            Timestamp dateuTs = new Timestamp(dateu.getTime());

            Stats s = new Stats(datelTs, dateuTs);

            //We check if the cache is empty
            if(typeReq.equals("month") && Cache.get("monthStats") == null){
                Cache.set("monthStats", s, 60*60*24);
            }else if(typeReq.equals("week") && Cache.get("weekStats") == null){
                Cache.set("weekStats", s, 60*60*12);
            }
            //The case for "all" is managed by controllers.stats

            return ok(stats.render(s.getNbTotal(),Json.toJson(s.getTimezone()),Json.toJson(s.getBrowsers()),
                    Json.toJson(s.getOs()),Json.toJson(s.getLanguages()),Json.toJson(s.getNbFonts()), datelString, dateuString, typeReq));

        }catch(ParseException e){
            System.out.println("Parse exception : "+e);
        }

        return redirect("/stats");
    }
}
