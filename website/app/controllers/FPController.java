package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import models.*;
import org.apache.commons.codec.digest.DigestUtils;
import play.Play;
import play.libs.Crypto;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import views.html.fp;
import views.html.fpNoJs;
import views.html.results;
import views.html.viewFP;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


public class FPController extends Controller{

    public static String getHeader(Http.Request request, String header){
        if(request.getHeader(header) == null){
            return "Not specified";
        } else {
            return request.getHeader(header);
        }
    }

    public static String getAttribute(JsonNode json, String attribute){
        if(json.get(attribute) == null){
            return "Not specified";
        } else {
            return json.get(attribute).asText();
        }
    }

    public static Result fp() {
        if(request().cookies().get("amiunique") == null){
            response().setCookie("amiunique", UUID.randomUUID().toString(),60*60*24*120,"/","amiunique.org",true,true);
            response().setCookie("tempReturningVis","temp",60*60*12);
        }

        return ok(fp.render(request()));
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
        CombinationStatsEntityManager emc = new CombinationStatsEntityManager();
        FpDataEntity fp;
        boolean newFp;

        String noJS = "no JS";

        if(!id.equals("Not supported") && em.checkIfFPWithNoJsExists(id, getHeader(request(), "User-Agent"),
                getHeader(request(), "Accept"),getHeader(request(), "Accept-Encoding"),
                getHeader(request(), "Accept-Language"))){
            fp = em.getExistingFPById(id);
            newFp = false;
        } else {
            LocalDateTime time = LocalDateTime.now();
            time = time.truncatedTo(ChronoUnit.HOURS);

            String ip;
            if(Play.isProd()) {
                ip = getHeader(request(), "X-Real-IP");
            } else {
                ip = request().remoteAddress();
            }
            fp = em.createWithoutJavaScript(id,
                    DigestUtils.sha1Hex(ip), Timestamp.valueOf(time), getHeader(request(),"User-Agent"),
                    getHeader(request(),"Accept"), getHeader(request(),"Host"), getHeader(request(),"Connection"),
                    getHeader(request(),"Accept-Encoding"), getHeader(request(),"Accept-Language"),
                    request().headers().keySet().toString().replaceAll("[,\\[\\]]", ""));

            emc.updateCombinationStats(getHeader(request(),"User-Agent"),
                    getHeader(request(),"Accept"), getHeader(request(),"Connection"),
                    getHeader(request(),"Accept-Encoding"), getHeader(request(),"Accept-Language"), request().headers().keySet().toString().replaceAll("[,\\[\\]]", ""),
                    noJS, noJS, noJS,
                    noJS, noJS, noJS,
                    noJS, noJS, noJS,
                    noJS, noJS, noJS,
                    noJS, noJS, noJS, noJS);

            newFp = true;
        }

        ObjectNode node = (ObjectNode) Json.toJson(fp);

        //Analyse the user agent
        ParsedFP parsedFP = new ParsedFP(node.get("userAgentHttp").asText());
        //Analyse the language and timezone
        parsedFP.setLanguage(node.get("languageHttp").asText());
        parsedFP.setTimezone(node.get("timezoneJs").asText());
        parsedFP.setNbFonts(node.get("fontsFlash").asText());

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
        node.remove("canvasJs");
        node.remove("fontsFlash");
        node.remove("webGLJsHashed");
        JsonNode json = (JsonNode) node;

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
        Map<String,Double> percentages = emc.getPercentages(json);

        percentages.put("pluginsJs", percentages.get("pluginsJsHashed"));
        percentages.put("canvasJs", percentages.get("canvasJsHashed"));
        percentages.put("fontsFlash", percentages.get("fontsFlashHashed"));
        percentages.remove("pluginsJsHashed");
        percentages.remove("canvasJsHashed");
        percentages.remove("fontsFlashHashed");

        node.put("canvasJs","no JS");
        node.put("vendorWebGljs","no JS");
        node.put("rendererWebGljs","no JS");
        node.put("fontsFlash","no JS");

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
        return ok(fpNoJs.render(json, parsedFP, Json.toJson(percentages), Json.toJson(osMap),
                Json.toJson(browsersMap), Json.toJson(langMap), nbTotal, nbIdent));
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
        CombinationStatsEntityManager emc = new CombinationStatsEntityManager();
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

            String ip;
            if(Play.isProd()) {
                ip = getHeader(request(), "X-Real-IP");
            } else {
                ip = request().remoteAddress();
            }

            fp = em.createFull(id,
                    DigestUtils.sha1Hex(ip), Timestamp.valueOf(time), getAttribute(json,"userAgentHttp"),
                    getAttribute(json,"acceptHttp"), getAttribute(json,"hostHttp"), getAttribute(json,"connectionHttp"),
                    getAttribute(json,"encodingHttp"), getAttribute(json,"languageHttp"), getAttribute(json,"orderHttp"),
                    getAttribute(json,"pluginsJs"), getAttribute(json,"platformJs"), getAttribute(json,"cookiesJs"),
                    getAttribute(json,"dntJs"), getAttribute(json,"timezoneJs"), getAttribute(json,"resolutionJs"),
                    getAttribute(json,"localJs"), getAttribute(json,"sessionJs"), getAttribute(json,"IEDataJs"),
                    getAttribute(json,"canvasJs"), getAttribute(json,"webGLJs"), getAttribute(json,"fontsFlash"),
                    getAttribute(json,"resolutionFlash"), getAttribute(json,"languageFlash"), getAttribute(json,"platformFlash"),
                    getAttribute(json,"adBlock"), getAttribute(json,"vendorWebGLJs"),getAttribute(json,"rendererWebGLJs"), "", "",
                    pluginsJsHashed, canvasJsHashed, webGLJsHashed, fontsFlashHashed);

                 emc.updateCombinationStats(getAttribute(json,"userAgentHttp"),
                    getAttribute(json,"acceptHttp"), getAttribute(json,"connectionHttp"),
                    getAttribute(json,"encodingHttp"), getAttribute(json,"languageHttp"), getAttribute(json,"orderHttp"),
                    getAttribute(json,"pluginsJs"), getAttribute(json,"platformJs"), getAttribute(json,"cookiesJs"),
                    getAttribute(json,"dntJs"), getAttribute(json,"timezoneJs"), getAttribute(json,"resolutionJs"),
                    getAttribute(json,"localJs"), getAttribute(json,"sessionJs"), getAttribute(json,"IEDataJs"),
                    getAttribute(json,"resolutionFlash"), getAttribute(json,"languageFlash"), getAttribute(json,"platformFlash"),
                    getAttribute(json,"adBlock"), pluginsJsHashed, canvasJsHashed, fontsFlashHashed);

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
        n.put("t", Crypto.generateToken());
        String c = Crypto.encryptAES(n.toString());

        //Render the FP + Stats
        return ok(results.render(json, parsedFP, Json.toJson(percentages), Json.toJson(osMap),
                Json.toJson(browsersMap), Json.toJson(langMap), Json.toJson(timezoneMap), nbTotal,
                nbIdent, c));
    }

    public static Result viewFP() {
        return ok(viewFP.render(request()));
    }
}
