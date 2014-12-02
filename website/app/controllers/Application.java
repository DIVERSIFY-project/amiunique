package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import models.*;
import org.apache.commons.codec.digest.DigestUtils;
import play.Routes;
import play.cache.Cache;
import play.libs.Json;
import play.mvc.*;

import views.html.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

//@With(ForceHttps.class)
public class Application extends Controller {

    public static Result home() {
        return ok(home.render());
    }

    public static Result privacy() {
        return ok(privacy.render());
    }

    public static Result fp() {
        if(request().cookies().get("amiunique") == null){
            response().setCookie("amiunique",UUID.randomUUID().toString(),60*60*24*120,"/","amiunique.org",true,true);
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

        if(id != "Not supported" && em.checkIfFPWithNoJsExists(id, request().getHeader("User-Agent"),
                request().getHeader("Accept"), request().getHeader("Connection"),
                request().getHeader("Accept-Encoding"), request().getHeader("Accept-Language"))){
            fp = em.getExistingFP(id);
        } else {
            LocalDateTime time = LocalDateTime.now();
            time = time.truncatedTo(ChronoUnit.HOURS);
            fp = em.createWithoutJavaScript(id,
                    DigestUtils.sha1Hex(request().remoteAddress()), Timestamp.valueOf(time), request().getHeader("User-Agent"),
                    request().getHeader("Accept"), request().getHeader("Host"), request().getHeader("Connection"),
                    request().getHeader("Accept-Encoding"), request().getHeader("Accept-Language"),
                    request().headers().keySet().toString().replaceAll("[,\\[\\]]", ""));
        }

        ObjectNode node = (ObjectNode) Json.toJson(fp);
        node.remove("counter");
        node.remove("octaneScore");
        node.remove("sunspiderTime");
        node.remove("addressHttp");
        node.remove("time");
        node.remove("hostHttp");
        node.remove("orderHttp");
        node.remove("id");
        JsonNode json = (JsonNode) node;

        //Get the surprisal and entropy of every attribute
        Map<String,Double> percentages = em.getPercentages(json);

        //Number of fingerprints
        Integer nbTotal = em.getNumberOfEntries();
        //Number of identical fingerprints
        Integer nbIdent = em.getNumberOfIdenticalFingerprints(json);

        //Analyse the user agent
        ParsedFP parsedFP = new ParsedFP(node.get("userAgentHttp").asText());
        //Analyse the language and timezone
        parsedFP.setLanguage(node.get("languageHttp").asText());

        //Get some general stats
        HashMap<String,HashMap<String, Vermap>> resMap = em.getOSBrowserStats();
        HashMap<String, Vermap> osMap = resMap.get("os");
        HashMap<String, Vermap> browsersMap = resMap.get("browsers");
        Vermap langMap = em.getLanguageStats();

        //Adding percentages for OS and browsers
        for (Map.Entry<String, Vermap> entry : osMap.entrySet()) {
            percentages.put(entry.getKey(),entry.getValue().getCounter()*100/nbTotal);
        }
        for (Map.Entry<String, Vermap> entry : browsersMap.entrySet()) {
            percentages.put(entry.getKey(),entry.getValue().getCounter()*100/nbTotal);
        }

        //Render the FP + Stats
        return ok(fpNoJs.render(json, parsedFP, Json.toJson(percentages),Json.toJson(osMap),
                Json.toJson(browsersMap),Json.toJson(langMap),nbTotal,nbIdent));
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

        if(id != "Not supported" && em.checkIfFPExists(id,json.get("userAgentHttp").asText(),
                json.get("acceptHttp").asText(), json.get("connectionHttp").asText(),
                json.get("encodingHttp").asText(), json.get("languageHttp").asText(),
                json.get("pluginsJs").asText(), json.get("platformJs").asText(), json.get("cookiesJs").asText(),
                json.get("dntJs").asText(), json.get("timezoneJs").asText(), json.get("resolutionJs").asText(),
                json.get("localJs").asText(), json.get("sessionJs").asText(), json.get("IEDataJs").asText(),
                json.get("canvasJs").asText(), json.get("webGLJs").asText(), json.get("fontsFlash").asText(),
                json.get("resolutionFlash").asText(), json.get("languageFlash").asText(), json.get("platformFlash").asText(),
                json.get("adBlock").asText())){
            fp = em.getExistingFP(id);
        } else {
            LocalDateTime time = LocalDateTime.now();
            time = time.truncatedTo(ChronoUnit.HOURS);

            fp = em.createFull(id,
                    DigestUtils.sha1Hex(request().remoteAddress()), Timestamp.valueOf(time), json.get("userAgentHttp").asText(),
                    json.get("acceptHttp").asText(), json.get("hostHttp").asText(), json.get("connectionHttp").asText(),
                    json.get("encodingHttp").asText(), json.get("languageHttp").asText(), json.get("orderHttp").asText(),
                    json.get("pluginsJs").asText(), json.get("platformJs").asText(), json.get("cookiesJs").asText(),
                    json.get("dntJs").asText(), json.get("timezoneJs").asText(), json.get("resolutionJs").asText(),
                    json.get("localJs").asText(), json.get("sessionJs").asText(), json.get("IEDataJs").asText(),
                    json.get("canvasJs").asText(), json.get("webGLJs").asText(), json.get("fontsFlash").asText(),
                    json.get("resolutionFlash").asText(), json.get("languageFlash").asText(), json.get("platformFlash").asText(),
                    json.get("adBlock").asText(), "", "");
        }

        ObjectNode node = (ObjectNode) Json.toJson(fp);
        node.remove("counter");
        node.remove("octaneScore");
        node.remove("sunspiderTime");
        node.remove("addressHttp");
        node.remove("time");
        node.remove("hostHttp");
        node.remove("orderHttp");
        node.remove("id");
        json = (JsonNode) node;

        //Analyse the user agent
        ParsedFP parsedFP = new ParsedFP(node.get("userAgentHttp").asText());
        //Analyse the language and timezone
        parsedFP.setLanguage(node.get("languageHttp").asText());
        parsedFP.setTimezone(node.get("timezoneJs").asText());

        //Number of fingerprints
        Integer nbTotal = em.getNumberOfEntries();
        //Number of identical fingerprints
        Integer nbIdent = em.getNumberOfIdenticalFingerprints(json);

        //Get the percentages of every attribute
        Map<String,Double> percentages = em.getPercentages(json);

        //Get some general stats
        HashMap<String,HashMap<String, Vermap>> resMap = em.getOSBrowserStats();
        HashMap<String, Vermap> osMap = resMap.get("os");
        HashMap<String, Vermap> browsersMap = resMap.get("browsers");
        Vermap langMap = em.getLanguageStats();
        ArrayList<Object[]> timezoneMap = em.getTimezoneStats();

        //Adding percentages for OS and browsers
        for (Map.Entry<String, Vermap> entry : osMap.entrySet()) {
            percentages.put(entry.getKey(),entry.getValue().getCounter()*100/nbTotal);
        }
        for (Map.Entry<String, Vermap> entry : browsersMap.entrySet()) {
            percentages.put(entry.getKey(),entry.getValue().getCounter()*100/nbTotal);
        }

        //Render the FP + Stats
        return ok(results.render(json, parsedFP, Json.toJson(percentages),Json.toJson(osMap),
                Json.toJson(browsersMap),Json.toJson(langMap),Json.toJson(timezoneMap),nbTotal,nbIdent));
    }

    public static Result jsRoutes(){
        response().setContentType("text/javascript");
        return ok(Routes.javascriptRouter("jsRoutes", routes.javascript.Application.addFingerprint()));
    }

    public static Result stats() throws Exception{
        return ok(Cache.getOrElse("stats-html", () -> {
            FpDataEntityManager em = new FpDataEntityManager();
            Integer nbTotal = em.getNumberOfEntries();
            Integer nbUnique = em.getNumberOfUniqueEntries();
            ArrayList<Object[]> timezone = em.getTimezoneStats();
            HashMap<String,HashMap<String, Vermap>> resMap = em.getOSBrowserStats();
            Vermap langMap = em.getLanguageStats();
            Rangemap nbFontsList = em.getFontsStats();
            return stats.render(nbTotal,nbUnique,Json.toJson(timezone),Json.toJson(resMap.get("browsers")),
                    Json.toJson(resMap.get("os")),Json.toJson(langMap),Json.toJson(nbFontsList));
        }, 1800));
    }

}
