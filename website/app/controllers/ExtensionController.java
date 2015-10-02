package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import models.ExtensionDataEntity;
import models.ExtensionDataEntityManager;
import org.apache.commons.codec.digest.DigestUtils;
import play.Play;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.extension;
import views.html.noTimeline;
import views.html.timeline;
import views.html.timelineEmpty;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class ExtensionController extends Controller {

    /*
        AmIUnique Extension
    */

    public static Result extension(){
        return ok(extension.render());
    }


    public static Result addFingerprintFromExtension(String uuid){
        Map<String, String[]> vals = request().body().asFormUrlEncoded();

        if(uuid.length()>5) {
            ExtensionDataEntityManager em = new ExtensionDataEntityManager();

            String pluginsJsHashed = DigestUtils.sha1Hex(vals.get("pluginsJs")[0]);
            String canvasJsHashed = DigestUtils.sha1Hex(vals.get("canvasJs")[0]);
            String webGLJsHashed = DigestUtils.sha1Hex(vals.get("webGLJs")[0]);
            String fontsFlashHashed = DigestUtils.sha1Hex(vals.get("fontsFlash")[0]);

            Timestamp currentTime = Timestamp.valueOf(LocalDateTime.now().truncatedTo(ChronoUnit.HOURS));

            boolean create = false;
            boolean update = false;
            boolean end = false;

            //Check if FP with this ID exist
            //If no, we add the presented FP
            //If yes, we check if it is identical with the last one
            //  If no, we update the last one (endDate) and add the new one
            //  If yes, we update the last one (updateDate)

            if (em.checkIfIDExists(uuid)) {
                if (em.checkIfLastFPIsIdentical(uuid, vals.get("userAgentHttp")[0],
                        vals.get("acceptHttp")[0], vals.get("encodingHttp")[0], vals.get("languageHttp")[0],
                        pluginsJsHashed, vals.get("platformJs")[0], vals.get("cookiesJs")[0],
                        vals.get("dntJs")[0], vals.get("timezoneJs")[0], vals.get("resolutionJs")[0],
                        vals.get("localJs")[0], vals.get("sessionJs")[0], vals.get("IEDataJs")[0],
                        canvasJsHashed, fontsFlashHashed, vals.get("resolutionFlash")[0],
                        vals.get("languageFlash")[0], vals.get("platformFlash")[0], vals.get("adBlock")[0])) {
                    update = true;
                } else {
                    end = true;
                    create = true;
                }
            } else {
                create = true;
            }


            if (update) {
                em.updateLastFP(uuid, currentTime);
            }

            if (end) {
                em.endLastFP(uuid, currentTime);
            }

            if (create) {
                String ip;
                if(Play.isProd()) {
                    ip = FPController.getHeader(request(), "X-Real-IP");
                } else {
                    ip = request().remoteAddress();
                }
                em.createFP(uuid,
                        DigestUtils.sha1Hex(ip), currentTime, null, null, vals.get("userAgentHttp")[0],
                        vals.get("acceptHttp")[0], vals.get("hostHttp")[0], vals.get("connectionHttp")[0],
                        vals.get("encodingHttp")[0], vals.get("languageHttp")[0], vals.get("orderHttp")[0],
                        vals.get("pluginsJs")[0], vals.get("platformJs")[0], vals.get("cookiesJs")[0],
                        vals.get("dntJs")[0], vals.get("timezoneJs")[0], vals.get("resolutionJs")[0],
                        vals.get("localJs")[0], vals.get("sessionJs")[0], vals.get("IEDataJs")[0],
                        vals.get("canvasJs")[0], vals.get("webGLJs")[0], vals.get("fontsFlash")[0],
                        vals.get("resolutionFlash")[0], vals.get("languageFlash")[0], vals.get("platformFlash")[0],
                        vals.get("adBlock")[0], vals.get("vendorWebGLJs")[0], vals.get("rendererWebGLJs")[0], "", "",
                        pluginsJsHashed, canvasJsHashed, webGLJsHashed, fontsFlashHashed);
            }

            return ok();
        } else {
            return ok();
        }
    }

    public static Result getNbEvol(String uuid){
        if(uuid.length()>5) {
            ExtensionDataEntityManager em = new ExtensionDataEntityManager();
            return ok(Integer.toString(em.getExistingFPsById(uuid).size() - 1));
        } else {
            return ok(Integer.toString(0));
        }
    }

    /*
        Timeline
     */

    public static Result noTimeline(){
        return ok(noTimeline.render());
    }

    public static Result timeline(String id){

        //We retrieve the FPs
        ExtensionDataEntityManager edm = new ExtensionDataEntityManager();
        TreeSet<ExtensionDataEntity> fps = edm.getExistingFPsById(id);
        if(fps.size()>0) {

            ExtensionDataEntity fp = edm.getLastFP(id);
            ObjectNode node = (ObjectNode) Json.toJson(fp);
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
            JsonNode jsonFP = (JsonNode) node;

            if (fps.size() > 1) {
                fps = (TreeSet) fps.descendingSet();

                HashMap<Integer, String> differencesMap = new HashMap<Integer, String>();
                Iterator<ExtensionDataEntity> it = fps.iterator();

                //Initialisation for the first fingerprint
                ExtensionDataEntity fp0 = it.next();
                HashMap<String, String> att0 = fp0.fpToHashMap();
                HashMap<Integer, String> tabHtmlDifferences = new HashMap<Integer, String>();
                HashMap<Integer, String> startDate = new HashMap<Integer, String>();
                HashMap<Integer, String> endDate = new HashMap<Integer, String>();
                int numberFp = fps.size();
                int counterCanvas = 1;

                String firstDate = att0.get("creationDate");
                String currentDate = new SimpleDateFormat("yyyy,MM,dd,HH").format(new Date());

                while (it.hasNext()) {
                    ExtensionDataEntity fp1 = it.next();
                    HashMap<String, String> att1 = fp1.fpToHashMap();

                    String diff = "";
                    //We compare fp1 with fp0
                    if (fp1.getUserAgentHttp() != null ? !fp1.getUserAgentHttp().equals(fp0.getUserAgentHttp()) : fp0.getUserAgentHttp() != null)
                        diff += "userAgentHttp, ";
                    if (fp1.getAcceptHttp() != null ? !fp1.getAcceptHttp().equals(fp0.getAcceptHttp()) : fp0.getAcceptHttp() != null)
                        diff += "acceptHttp, ";
                    if (fp1.getEncodingHttp() != null ? !fp1.getEncodingHttp().equals(fp0.getEncodingHttp()) : fp0.getEncodingHttp() != null)
                        diff += "encodingHttp, ";
                    if (fp1.getLanguageHttp() != null ? !fp1.getLanguageHttp().equals(fp0.getLanguageHttp()) : fp0.getLanguageHttp() != null)
                        diff += "languageHttp, ";
                    if (fp1.getPluginsJs() != null ? !fp1.getPluginsJs().equals(fp0.getPluginsJs()) : fp0.getPluginsJs() != null)
                        diff += "pluginsJs, ";
                    if (fp1.getPlatformJs() != null ? !fp1.getPlatformJs().equals(fp0.getPlatformJs()) : fp0.getPlatformJs() != null)
                        diff += "platformJs, ";
                    if (fp1.getCookiesJs() != null ? !fp1.getCookiesJs().equals(fp0.getCookiesJs()) : fp0.getCookiesJs() != null)
                        diff += "cookiesJs, ";
                    if (fp1.getDntJs() != null ? !fp1.getDntJs().equals(fp0.getDntJs()) : fp0.getDntJs() != null)
                        diff += "dntJs, ";
                    if (fp1.getTimezoneJs() != null ? !fp1.getTimezoneJs().equals(fp0.getTimezoneJs()) : fp0.getTimezoneJs() != null)
                        diff += "timezoneJs, ";
                    if (fp1.getResolutionJs() != null ? !fp1.getResolutionJs().equals(fp0.getResolutionJs()) : fp0.getResolutionJs() != null)
                        diff += "resolutionJs, ";
                    if (fp1.getLocalJs() != null ? !fp1.getLocalJs().equals(fp0.getLocalJs()) : fp0.getLocalJs() != null)
                        diff += "localJs, ";
                    if (fp1.getSessionJs() != null ? !fp1.getSessionJs().equals(fp0.getSessionJs()) : fp0.getSessionJs() != null)
                        diff += "sessionJs, ";
                    if (fp1.getCanvasJs() != null ? !fp1.getCanvasJs().equals(fp0.getCanvasJs()) : fp0.getCanvasJs() != null)
                        diff += "canvasJs, ";
                    if (fp1.getVendorWebGljs() != null ? !fp1.getVendorWebGljs().equals(fp0.getVendorWebGljs()) : fp0.getRendererWebGljs() != null)
                        diff += "vendorWebGLJs, ";
                    if (fp1.getRendererWebGljs() != null ? !fp1.getRendererWebGljs().equals(fp0.getRendererWebGljs()) : fp0.getRendererWebGljs() != null)
                        diff += "rendererWebGLJs, ";
                    if (fp1.getFontsFlash() != null ? !fp1.getFontsFlash().equals(fp0.getFontsFlash()) : fp0.getFontsFlash() != null)
                        diff += "fontsFlash, ";
                    if (fp1.getResolutionFlash() != null ? !fp1.getResolutionFlash().equals(fp0.getResolutionFlash()) : fp0.getResolutionFlash() != null)
                        diff += "resolutionFlash, ";
                    if (fp1.getLanguageFlash() != null ? !fp1.getLanguageFlash().equals(fp0.getLanguageFlash()) : fp0.getLanguageFlash() != null)
                        diff += "languageFlash, ";
                    if (fp1.getPlatformFlash() != null ? !fp1.getPlatformFlash().equals(fp0.getPlatformFlash()) : fp0.getPlatformFlash() != null)
                        diff += "platformFlash, ";
                    if (fp1.getAdBlock() != null ? !fp1.getAdBlock().equals(fp0.getAdBlock()) : fp0.getAdBlock() != null)
                        diff += "adBlock, ";

                    try {
                        diff = diff.substring(0, diff.length() - 2);
                        String[] attDiff = diff.split(",");
                        String rowValue = "";
                        for (String att : attDiff) {
                            att = att.trim();
                            if (!att.equals("canvasJs")) {
                                rowValue += "<tr><td>" + att + "</td><td>" + att0.get(att) + "</td><td>" + att1.get(att) + "</td><td></td></tr>";
                            } else {
                                rowValue += "<tr><td>" + att + "</td><td><img id=\\\"img" + counterCanvas + "\\\" src=\\\"" + att0.get(att)
                                        + "\\\"></td><td><img id=\\\"img" + (counterCanvas + 1) + "\\\" src=\\\""
                                        + att1.get(att) + "\\\"></td><td></td></tr>";
                                counterCanvas += 2;
                            }
                        }
                        tabHtmlDifferences.put(fp1.getCounter(), rowValue);
                        startDate.put(fp1.getCounter(), att1.get("creationDate"));
                        endDate.put(fp0.getCounter(), att0.get("endDate"));

                        differencesMap.put(fp1.getCounter(), diff);
                        fp0 = (ExtensionDataEntity) fp1.clone();
                        att0 = (HashMap<String, String>) att1.clone();
                    } catch (StringIndexOutOfBoundsException e) {
                        String rowValue = "";
                        tabHtmlDifferences.put(fp1.getCounter(), rowValue);
                        differencesMap.put(fp1.getCounter(), "nodiff");
                    }

                }

                fps.remove(fps.last());
                return ok(timeline.render(jsonFP, Json.toJson(tabHtmlDifferences), Json.toJson(startDate), Json.toJson(endDate), firstDate, currentDate));
            } else {
                String message;
                if (fps.size() == 1) {
                    message = "A second fingerprint is needed to be able to use the timeline feature.<br>Come back in a few days and you will see all the changes!";
                } else {
                    message = "To use the timeline feature of the AmIUnique website, download our extension for Chrome and Firefox!";
                }

                return ok(timelineEmpty.render(jsonFP, message));
            }
        } else {
            return ok(noTimeline.render());
        }
    }
}
