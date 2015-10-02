package controllers;

import models.FpDataEntity;
import models.FpDataEntityManager;
import org.apache.commons.lang3.time.DateFormatUtils;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import views.html.differences;
import views.html.history;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.TreeSet;

public class HistoryController extends Controller {

    public static Result history(){

        Http.Cookie cookie = request().cookies().get("amiunique");
        String id;
        if(cookie == null){
            return redirect("/");
        }

        id = cookie.value();
        FpDataEntityManager emf = new FpDataEntityManager();

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

            try{
                diff = diff.substring(0, diff.length()-2);
                String[] attDiff = diff.split(",");
                String rowValue = "<tr class=\"legend\"><td>date</td><td>"+ DateFormatUtils.format(fp0.getTime(), "dd/MM/yyyy")+" - "+DateFormatUtils.format(fp0.getTime(), "HH")+"h<td>"+DateFormatUtils.format(fp1.getTime(), "dd/MM/yyyy")+" - "+DateFormatUtils.format(fp1.getTime(), "HH")+"h</td><td></td></tr>";
                for(String att : attDiff){
                    att = att.trim();
                    if(!att.equals("canvasJs")){
                        rowValue += "<tr><td>"+att+"</td><td>"+att0.get(att)+"</td><td>"+att1.get(att)+"</td><td></td></tr>";
                    }else{
                        rowValue +="<tr><td>"+att+"</td><td><img id=\"img1\" src=\""+att0.get(att)+"\"></td><td><img id=\"img2\" src=\""+att1.get(att)+"\"></td><td><img id=\"diffImg\"></td></tr>";
                    }
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

}
