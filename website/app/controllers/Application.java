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
import play.cache.Cache;

import views.html.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.*;
import java.io.*;
import java.text.DateFormat;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import org.apache.commons.lang.time.DateFormatUtils;


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
            response().setCookie("amiunique",UUID.randomUUID().toString(),60*60*24*120); //Warning set true,true for production
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

        String pluginsJsHashed = DigestUtils.sha1Hex(getAttribute(json,"pluginsJs"));
        String canvasJsHashed = DigestUtils.sha1Hex(getAttribute(json,"canvasJs"));
        String webGLJsHashed = DigestUtils.sha1Hex(getAttribute(json,"webGLJs"));
        String fontsFlashHashed = DigestUtils.sha1Hex(getAttribute(json,"fontsFlash"));

        if(!id.equals("Not supported") && em.checkIfFPExists(id,getAttribute(json,"userAgentHttp"),
                getAttribute(json,"acceptHttp"),getAttribute(json,"encodingHttp"), getAttribute(json,"languageHttp"),
                pluginsJsHashed, getAttribute(json,"platformJs"), getAttribute(json,"cookiesJs"),
                getAttribute(json,"dntJs"), getAttribute(json,"timezoneJs"), getAttribute(json,"resolutionJs"),
                getAttribute(json,"localJs"), getAttribute(json,"sessionJs"), getAttribute(json,"IEDataJs"),
                canvasJsHashed, webGLJsHashed, fontsFlashHashed,
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
                    getAttribute(json,"adBlock"), getAttribute(json,"vendorWebGLJs"),getAttribute(json,"rendererWebGLJs"), "", "",
                    pluginsJsHashed, canvasJsHashed, webGLJsHashed, fontsFlashHashed);
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

    public static Result jsRoutes(){
        response().setContentType("text/javascript");
        return ok(Routes.javascriptRouter("jsRoutes", routes.javascript.Application.addFingerprint(),
                routes.javascript.Application.percentages()));
    }

    public static Result stats() throws Exception{
        return ok(Cache.getOrElse("stats-html", () -> {
            Stats s = Stats.getInstance();
            return stats.render(s.getNbTotal(),Json.toJson(s.getTimezone()),Json.toJson(s.getBrowsers()),
                    Json.toJson(s.getOs()),Json.toJson(s.getLanguages()),Json.toJson(s.getNbFonts()),"","","");
        }, 1800));
    }

    public static Result history(){

        Http.Cookie cookie = request().cookies().get("amiunique");
        String id;
        if(cookie == null){
            return redirect("/home");
        }
            
        id = cookie.value();
        FpDataEntityManager emf = new FpDataEntityManager();

        String[] fields ={"userAgentHttp","acceptHttp","connectionHttp","languageHttp","orderHttp","encodingHttp","pluginsJs","platformJs","cookiesJs","dntJs","timezoneJs","resolutionJs","localJs",
            "sessionJs","ieDataJs","canvasJs","fontsFlash","resolutionFlash","languageFlash","platformFlash","adBlock","vendorWebGljs","rendererWebGljs"};
        TreeSet<FpDataEntity> fps = emf.getExistingFPsById(id);
        
        return ok(history.render(fps));
    }

    /*
        Method called with ajax on my history page
        It receives counters of fingerprints to compare
        It compares fingerprint(t+1) with fingerprint(t)
        It builds an HTML tab which is inserted in the "my history" page
    */
    public static Result compareFpHistory(){
        String[] values = request().body().asFormUrlEncoded().get("list")[0].split(",");
        ArrayList<Integer> valuesCasted = new ArrayList<Integer>();
        for(String s : values){
            valuesCasted.add(Integer.parseInt(s));
        }

        FpDataEntityManager em = new FpDataEntityManager();
        TreeSet<FpDataEntity> fpsSorted = new TreeSet<FpDataEntity>();

        for(Integer counter : valuesCasted){
            fpsSorted.add(em.getExistingFPByCounter(counter));
        }

        //We retrieve the differences between the fp (t+1) and fo (t)
        TreeSet<FpDataEntity> fpsInversed = new TreeSet<FpDataEntity>();
        fpsInversed = (TreeSet)fpsSorted.descendingSet();

        HashMap<Integer, String> differencesMap = new HashMap<Integer, String>();
        Iterator<FpDataEntity> it = fpsInversed.iterator();

        //Initialisation for the first fingerprint
        FpDataEntity fp0 = it.next();
        HashMap<String, String> att0 = fp0.fpToHashMap();
        HashMap<Integer, String> tabHtmlDifferences = new HashMap<Integer,String>();
        int numberFp = fpsInversed.size();

        while(it.hasNext()){
            FpDataEntity fp1 = it.next();
            HashMap<String, String> att1 = fp1.fpToHashMap();

            String diff = "";
            //We compare fp1 with fp0
            if (fp1.getUserAgentHttp() != null ? !fp1.getUserAgentHttp().equals(fp0.getUserAgentHttp()) : fp0.getUserAgentHttp() != null) diff +="userAgentHttp, ";
            if (fp1.getAcceptHttp() != null ? !fp1.getAcceptHttp().equals(fp0.getAcceptHttp()) : fp0.getAcceptHttp() != null) diff += "acceptHttp, ";
            if (fp1.getEncodingHttp() != null ? !fp1.getEncodingHttp().equals(fp0.getEncodingHttp()) : fp0.getEncodingHttp() != null) diff += "encodingHttp, ";
            if (fp1.getLanguageHttp() != null ? !fp1.getLanguageHttp().equals(fp0.getLanguageHttp()) : fp0.getLanguageHttp() != null) diff += "languageHttp, ";
            if (fp1.getAddressHttp() != null ? !fp1.getAddressHttp().equals(fp0.getAddressHttp()) : fp0.getAddressHttp() != null) diff += "addresse IP, ";

            if (fp1.getPluginsJs() != null ? !fp1.getPluginsJs().equals(fp0.getPluginsJs()) : fp0.getPluginsJs() != null) diff += "pluginsJs, ";
            if (fp1.getPlatformJs() != null ? !fp1.getPlatformJs().equals(fp0.getPlatformJs()) : fp0.getPlatformJs() != null) diff += "platformJs, ";
            if (fp1.getCookiesJs() != null ? !fp1.getCookiesJs().equals(fp0.getCookiesJs()) : fp0.getCookiesJs() != null) diff += "cookiesJs, ";
            if (fp1.getDntJs() != null ? !fp1.getDntJs().equals(fp0.getDntJs()) : fp0.getDntJs() != null) diff += "dntJs, ";
            if (fp1.getTimezoneJs() != null ? !fp1.getTimezoneJs().equals(fp0.getTimezoneJs()) : fp0.getTimezoneJs() != null) diff += "timezoneJs, ";
            if (fp1.getResolutionJs() != null ? !fp1.getResolutionJs().equals(fp0.getResolutionJs()) : fp0.getResolutionJs() != null) diff +="resolutionJs, ";
            if (fp1.getLocalJs() != null ? !fp1.getLocalJs().equals(fp0.getLocalJs()) : fp0.getLocalJs() != null) diff += "localJs, ";
            if (fp1.getSessionJs() != null ? !fp1.getSessionJs().equals(fp0.getSessionJs()) : fp0.getSessionJs() != null) diff += "sessionJs, ";
            if (fp1.getCanvasJs() != null ? !fp1.getCanvasJs().equals(fp0.getCanvasJs()) : fp0.getCanvasJs() != null) diff += "canvasJs, ";
            //manque webgl vendor et renderer

            if (fp1.getFontsFlash() != null ? !fp1.getFontsFlash().equals(fp0.getFontsFlash()) : fp0.getFontsFlash() != null) diff += "fontsFlash, ";
            if (fp1.getResolutionFlash() != null ? !fp1.getResolutionFlash().equals(fp0.getResolutionFlash()) : fp0.getResolutionFlash() != null) diff +="resolutionFlash, ";
            if (fp1.getLanguageFlash() != null ? !fp1.getLanguageFlash().equals(fp0.getLanguageFlash()) : fp0.getLanguageFlash() != null) diff += "languageFlash, ";
            if (fp1.getPlatformFlash() != null ? !fp1.getPlatformFlash().equals(fp0.getPlatformFlash()) : fp0.getPlatformFlash() != null) diff +="platformFlash, ";

            if (fp1.getAdBlock() != null ? !fp1.getAdBlock().equals(fp0.getAdBlock()) : fp0.getAdBlock() != null) diff += "adBlock, ";
            System.out.println("counter fp1 "+fp1.getCounter());
            System.out.println("counter fp0 "+fp0.getCounter());
            try{
                diff = diff.substring(0, diff.length()-2);
                String[] attDiff = diff.split(",");
                String rowValue = "<tr class=\"legend\"><td>date</td><td>"+DateFormatUtils.format(fp0.getTime(), "dd/MM/yyyy")+" - "+DateFormatUtils.format(fp0.getTime(), "HH")+"h<td>"+DateFormatUtils.format(fp1.getTime(), "dd/MM/yyyy")+" - "+DateFormatUtils.format(fp1.getTime(), "HH")+"h</td><td></td></tr>";
                for(String att : attDiff){
                    att = att.trim();
                    rowValue += "<tr><td>"+att+"</td><td>"+att0.get(att)+"</td><td>"+att1.get(att)+"</td><td></td></tr>";
                }
                tabHtmlDifferences.put(fp1.getCounter(), rowValue);

                differencesMap.put(fp1.getCounter(), diff);
                fp0 = (FpDataEntity) fp1.clone();
                att0 = (HashMap<String, String>) att1.clone();
            }catch(StringIndexOutOfBoundsException e){
                String rowValue = "<tr class=\"legend\"><td>date</td><td>"+DateFormatUtils.format(fp0.getTime(), "dd/MM/yyyy")+" - "+DateFormatUtils.format(fp0.getTime(), "HH")+"h<td>"+DateFormatUtils.format(fp1.getTime(), "dd/MM/yyyy")+" - "+DateFormatUtils.format(fp1.getTime(), "HH")+"h</td><td></td></tr>";
                tabHtmlDifferences.put(fp1.getCounter(), rowValue);
                differencesMap.put(fp1.getCounter(), "nodiff");
            }

        }

        fpsSorted.remove(fpsSorted.last());
        return ok(differences.render(fpsSorted, differencesMap, tabHtmlDifferences));
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
            System.out.println("test cache");  
            Stats s = (Stats) Cache.get(typeReq+"Stats");

            return ok(stats.render(s.getNbTotal(),Json.toJson(s.getTimezone()),Json.toJson(s.getBrowsers()),
                    Json.toJson(s.getOs()),Json.toJson(s.getLanguages()),Json.toJson(s.getNbFonts()), datelString, dateuString, typeReq));
        }

        System.out.println("test cache month "+Cache.get("monthStats"));
        //Only if custom or if the information is not in cache
        try{
            System.out.println("test non cache");  
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
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



    /*
        !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

    This method is only used with the amiunique extension
    
        !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    */

    public static Result extension(){
        return ok(extension.render());
    }


    public static Result addFingerprinFromExtension(){

        Map<String, String[]> vals = request().body().asFormUrlEncoded();
        FpDataEntityManager em = new FpDataEntityManager();
        FpDataEntity fp;

        String pluginsJsHashed = DigestUtils.sha1Hex(vals.get("pluginsJs")[0]);
        String canvasJsHashed = DigestUtils.sha1Hex(vals.get("canvasJs")[0]);
        String webGLJsHashed = "";
        String fontsFlashHashed = DigestUtils.sha1Hex(vals.get("fontsFlash")[0]);

        LocalDateTime time = LocalDateTime.now();
        time = time.truncatedTo(ChronoUnit.HOURS);
        fp = em.createFull(vals.get("id")[0],
            DigestUtils.sha1Hex(request().remoteAddress()), Timestamp.valueOf(time), request().getHeader("User-Agent"),
            request().getHeader("Accept"), request().getHeader("Host"), request().getHeader("Connection"),
            request().getHeader("Accept-Encoding"), request().getHeader("Accept-Language"), vals.get("orderHttp")[0],
            vals.get("pluginsJs")[0], vals.get("platformJs")[0], vals.get("cookiesJs")[0],
            vals.get("dntJs")[0], vals.get("timezoneJs")[0], vals.get("resolutionJs")[0],
            vals.get("localJs")[0], vals.get("sessionJs")[0], vals.get("IEDataJs")[0],
            vals.get("canvasJs")[0], "nc", vals.get("fontsFlash")[0],
            vals.get("resolutionFlash")[0], vals.get("languageFlash")[0], vals.get("platformFlash")[0],
            vals.get("adBlock")[0], "nc","nc", "", "", pluginsJsHashed, canvasJsHashed, webGLJsHashed, fontsFlashHashed);

        return ok();
    }

}
