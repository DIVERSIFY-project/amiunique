package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import models.*;
import org.apache.commons.codec.digest.DigestUtils;
import play.Routes;
import play.cache.Cache;
import play.libs.Json;
import play.libs.Crypto;
import play.mvc.*;

import views.html.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.*;
import java.io.*;

//@With(ForceHttps.class)
public class Application extends Controller {

    public static Result secret(){
        return ok("Got request " + request() + "!");
    }

    public static Result home() {
        return ok(home.render());
    }

    public static Result privacy() {
        return ok(privacy.render());
    }

    public static Result fp() {
        if(request().cookies().get("amiunique") == null){
            response().setCookie("amiunique",UUID.randomUUID().toString(),60*60*24*120); //remettre true,true en prod
        }

        return ok(fp.render(request()));
    }

    public static Result faq(){
        return ok(faq.render());
    }

    public static Result links() {
        return ok(links.render());
    }

    public static Result about(){
        return ok(about.render());
    }

    public static String getHeader(Http.Request request, String header){
        if(request.getHeader(header) == null){
            return "Not specified";
        } else {
            return request.getHeader(header);
        }
    }

    public static Result fpNoJs() {
        Http.Cookie cookie = request().cookies().get("amiunique");
        String id;
        if(cookie == null){
            id = "Not supported";
        } else {
            id = cookie.value();
        }

        FpDataEntityManager em = new FpDataEntityManager();
        FpDataEntity fp;
        boolean newFp;

        if(!id.equals("Not supported") && em.checkIfFPWithNoJsExists(id, getHeader(request(), "User-Agent"),
                getHeader(request(), "Accept"),getHeader(request(), "Accept-Encoding"),
                getHeader(request(), "Accept-Language"))){
            fp = em.getExistingFPById(id);
            newFp = false;
        } else {
            LocalDateTime time = LocalDateTime.now();
            time = time.truncatedTo(ChronoUnit.HOURS);
            fp = em.createWithoutJavaScript(id,
                    DigestUtils.sha1Hex(request().remoteAddress()), Timestamp.valueOf(time), getHeader(request(),"User-Agent"),
                    getHeader(request(),"Accept"), getHeader(request(),"Host"), getHeader(request(),"Connection"),
                    getHeader(request(),"Accept-Encoding"), getHeader(request(),"Accept-Language"),
                    request().headers().keySet().toString().replaceAll("[,\\[\\]]", ""));
            newFp = true;
        }

        ObjectNode node = (ObjectNode) Json.toJson(fp);
        node.remove("counter");
        node.remove("octaneScore");
        node.remove("sunspiderTime");
        node.remove("addressHttp");
        node.remove("time");
        node.remove("hostHttp");
        node.remove("orderHttp");
        node.remove("connectionHttp");
        node.remove("id");
        JsonNode json = (JsonNode) node;

        //Analyse the user agent
        ParsedFP parsedFP = new ParsedFP(node.get("userAgentHttp").asText());
        //Analyse the language and timezone
        parsedFP.setLanguage(node.get("languageHttp").asText());
        parsedFP.setTimezone(node.get("timezoneJs").asText());
        parsedFP.setNbFonts(node.get("fontsFlash").asText());

        //Get the stats instance
        Stats s = Stats.getInstance();
        if(newFp) {
            //Add the newly parsed FP to the stats
            s.addFingerprint(parsedFP);
        }

        //Number of fingerprints
        Double nbTotal = s.getNbTotal().doubleValue();
        //Number of identical fingerprints
        Integer nbIdent = em.getNumberOfIdenticalFingerprints(json);
        //Get the percentages of every attribute
        Map<String,Double> percentages = em.getPercentages(json);

        //Get some general stats
        HashMap<String, VersionMap> osMap = s.getOs();
        HashMap<String, VersionMap> browsersMap = s.getBrowsers();
        VersionMap langMap = s.getLanguages();

        //Adding percentages for OS and browsers
        for (Map.Entry<String, VersionMap> entry : osMap.entrySet()) {
            percentages.put(entry.getKey(),entry.getValue().getCounter()*100/nbTotal);
        }
        for (Map.Entry<String, VersionMap> entry : browsersMap.entrySet()) {
            percentages.put(entry.getKey(),entry.getValue().getCounter()*100/nbTotal);
        }

        //Render the FP + models.Stats
        return ok(fpNoJs.render(json, parsedFP, Json.toJson(percentages),Json.toJson(osMap),
                Json.toJson(browsersMap),Json.toJson(langMap),nbTotal,nbIdent));
    }


    public static String getAttribute(JsonNode json, String attribute){
        if(json.get(attribute) == null){
            return "Not specified";
        } else {
            return json.get(attribute).asText();
        }
    }

    public static Result addFingerprint() {
        Http.Cookie cookie = request().cookies().get("amiunique");
        String id;
        if(cookie == null){
            id = "Not supported";
        } else {
            id = cookie.value();
        }

        //Get FP attributes (body content)
        JsonNode json = request().body().asJson();
        FpDataEntityManager em = new FpDataEntityManager();
        FpDataEntity fp;
        boolean newFp;

        if(!id.equals("Not supported") && em.checkIfFPExists(id,getAttribute(json,"userAgentHttp"),
                getAttribute(json,"acceptHttp"),getAttribute(json,"encodingHttp"), getAttribute(json,"languageHttp"),
                getAttribute(json,"pluginsJs"), getAttribute(json,"platformJs"), getAttribute(json,"cookiesJs"),
                getAttribute(json,"dntJs"), getAttribute(json,"timezoneJs"), getAttribute(json,"resolutionJs"),
                getAttribute(json,"localJs"), getAttribute(json,"sessionJs"), getAttribute(json,"IEDataJs"),
                getAttribute(json,"canvasJs"), getAttribute(json,"webGLJs"), getAttribute(json,"fontsFlash"),
                getAttribute(json,"resolutionFlash"), getAttribute(json,"languageFlash"), getAttribute(json,"platformFlash"),
                getAttribute(json,"adBlock"))){

                fp = em.getExistingFPById(id);
                newFp = false;
        } else {
            LocalDateTime time = LocalDateTime.now();
            time = time.truncatedTo(ChronoUnit.HOURS);

            fp = em.createFull(id,
                    DigestUtils.sha1Hex(request().remoteAddress()), Timestamp.valueOf(time), getAttribute(json,"userAgentHttp"),
                    getAttribute(json,"acceptHttp"), getAttribute(json,"hostHttp"), getAttribute(json,"connectionHttp"),
                    getAttribute(json,"encodingHttp"), getAttribute(json,"languageHttp"), getAttribute(json,"orderHttp"),
                    getAttribute(json,"pluginsJs"), getAttribute(json,"platformJs"), getAttribute(json,"cookiesJs"),
                    getAttribute(json,"dntJs"), getAttribute(json,"timezoneJs"), getAttribute(json,"resolutionJs"),
                    getAttribute(json,"localJs"), getAttribute(json,"sessionJs"), getAttribute(json,"IEDataJs"),
                    getAttribute(json,"canvasJs"), getAttribute(json,"webGLJs"), getAttribute(json,"fontsFlash"),
                    getAttribute(json,"resolutionFlash"), getAttribute(json,"languageFlash"), getAttribute(json,"platformFlash"),
                    getAttribute(json,"adBlock"), getAttribute(json,"vendorWebGLJs"),getAttribute(json,"rendererWebGLJs"), "", "");
            newFp = true;
        }

        ObjectNode node = (ObjectNode) Json.toJson(fp);
        int counter = node.get("counter").asInt();
        node.remove("counter");
        node.remove("octaneScore");
        node.remove("sunspiderTime");
        node.remove("addressHttp");
        node.remove("time");
        node.remove("hostHttp");
        node.remove("connectionHttp");
        node.remove("orderHttp");
        node.remove("id");
        node.remove("webGlJs");
        json = (JsonNode) node;

        //Analyse the user agent
        ParsedFP parsedFP = new ParsedFP(node.get("userAgentHttp").asText());
        //Analyse the language and timezone
        parsedFP.setLanguage(node.get("languageHttp").asText());
        parsedFP.setTimezone(node.get("timezoneJs").asText());
        parsedFP.setNbFonts(node.get("fontsFlash").asText());

        //Get the stats instance
        Stats s = Stats.getInstance();
        if(newFp) {
            //Add the newly parsed FP to the stats
            s.addFingerprint(parsedFP);
        }

        //Number of fingerprints
        Double nbTotal = s.getNbTotal().doubleValue();
        //Number of identical fingerprints
        Integer nbIdent = em.getNumberOfIdenticalFingerprints(json);
        //Get the percentages of every attribute
        //Map<String,Double> percentages = em.getPercentages(json);
        Map<String,Double> percentages = new HashMap<>();

        //Get some general stats
        HashMap<String, VersionMap> osMap = s.getOs();
        HashMap<String, VersionMap> browsersMap = s.getBrowsers();
        VersionMap langMap = s.getLanguages();
        CounterMap timezoneMap = s.getTimezone();

        //Adding percentages for OS and browsers
        for (Map.Entry<String, VersionMap> entry : osMap.entrySet()) {
            percentages.put(entry.getKey(),entry.getValue().getCounter()*100/nbTotal);
        }
        for (Map.Entry<String, VersionMap> entry : browsersMap.entrySet()) {
            percentages.put(entry.getKey(),entry.getValue().getCounter()*100/nbTotal);
        }

        ObjectNode n = Json.newObject();
        n.put("c",counter);
        n.put("t",Crypto.generateToken());
        String c = Crypto.encryptAES(n.toString());

        //Render the FP + Stats
        return ok(results.render(json, parsedFP, Json.toJson(percentages),Json.toJson(osMap),
                Json.toJson(browsersMap),Json.toJson(langMap),Json.toJson(timezoneMap),nbTotal,
                nbIdent,c));
    }

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
            JsonNode json = (JsonNode) node;
            Map<String,Double> percentages = emc.getPercentages(json);
            Map<String,Double> percentagesPlugins = emc.getPercentagesPlugins(getAttribute(json, "pluginsJs"));

            System.out.println("test pourcentages plugins : ");
            for(String key : percentagesPlugins.keySet()){
                    double val = percentagesPlugins.get(key);
                    System.out.println("combination : "+key+" ,valeur : "+val);
                    
            }

            ObjectNode nodePer = (ObjectNode) Json.toJson(percentages);

            JsonNode jsonPerPlugins = Json.toJson(percentagesPlugins);
            nodePer.put("perPluginsJs", jsonPerPlugins);

            json = (JsonNode) nodePer;

            /*
            System.out.println("test pourcentages plugins : ");
            for(String key : percentagesPlugins.keySet()){
                    double val = percentagesPlugins.get(key);
                    System.out.println("combination : "+key+" ,valeur : "+val);
                    
            }
            */

            System.out.println("test stringify : "+Json.stringify(json));
            return ok(json);
        } catch (Exception e){
            return badRequest();
        }
    }

    public static Result jsRoutes(){
        response().setContentType("text/javascript");
        return ok(Routes.javascriptRouter("jsRoutes", routes.javascript.Application.addFingerprint(),
                routes.javascript.Application.percentages()));
    }

    public static Result stats() throws Exception{
        return ok(Cache.getOrElse("stats-html", () -> {
            Stats s = Stats.getInstance();
            return stats.render(s.getNbTotal(),Json.toJson(s.getTimezone()),Json.toJson(s.getBrowsers()),
                    Json.toJson(s.getOs()),Json.toJson(s.getLanguages()),Json.toJson(s.getNbFonts()));
        }, 1800));
    }

    public static Result history(){

        Http.Cookie cookie = request().cookies().get("amiunique");
        String id;
        if(cookie == null){
            return redirect("/home");
        }
            
        id = cookie.value();

        //FpDataEntity fp = em.getExistingFPById(id);


        return ok(history.render());
    }

    public static Result updateCombinationStats(){
        Boolean autorized = false;
        String filesk = System.getProperty("user.dir")+"/secret/sk.txt";
        try {
            FileReader fileReader = new FileReader(filesk);

            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String sk = bufferedReader.readLine() ;
            bufferedReader.close();     

            Map<String, String[]> vals = request().body().asFormUrlEncoded();
            String secretKey = vals.get("secretKey")[0];

            if(sk.equals(secretKey)){
                autorized = true;
            }

        }catch(FileNotFoundException ex) {
            System.out.println("file not found : "+filesk);
        }
        catch(IOException ex) {
            System.out.println("IOException");
        }

        if(autorized){
            FpDataEntityManager em = new FpDataEntityManager();
            CombinationStatsEntityManager emc = new CombinationStatsEntityManager();
            String[] fields ={"userAgentHttp","acceptHttp","connectionHttp","languageHttp","orderHttp","encodingHttp","pluginsJs","platformJs","cookiesJs","dntJs","timezoneJs","resolutionJs","localJs",
            "sessionJs","ieDataJs","canvasJs","fontsFlash","resolutionFlash","languageFlash","platformFlash","adBlock","vendorWebGljs","rendererWebGljs"};
            int nbEntries = em.getNumberOfEntries();

            for(String attribute : fields){
                HashMap<String,Integer> stats = new HashMap<String,Integer>();
                ArrayList<String> values = em.getAttribute(attribute);
                for(String combination : values){
                    if(stats.get(combination) != null){
                        stats.put(combination, stats.get(combination)+1);
                    }else{
                        stats.put(combination, 1);
                    }
                }

                for(String key : stats.keySet()){
                    int val = stats.get(key);

                    //We delete the old row before adding the new one
                    if(emc.updateCombinationStats(key, attribute, val, (val/((float)nbEntries))*100) == 0){
                        emc.createCombinationStats(key, attribute, val, (val/((float)nbEntries))*100);
                    }
                }
            }
        }

        return ok();
    }

}
