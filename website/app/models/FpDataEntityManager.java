package models;

import com.fasterxml.jackson.databind.JsonNode;
import play.db.jpa.JPA;
import javax.persistence.EntityManager;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.*;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FpDataEntityManager {

    private <A> A withTransaction(Function<EntityManager, A> f) {
        try {
            return JPA.withTransaction(() -> f.apply(JPA.em()));
        } catch (Throwable throwable) {
            throw new RuntimeException(throwable);
        }
    }

    public boolean checkIfFPExists(String id,String userAgentHttp,
                                   String acceptHttp, String connectionHttp, String encodingHttp,
                                   String languageHttp, String pluginsJs, String platformJs, String cookiesJs,
                                   String dntJs, String timezoneJs, String resolutionJs, String localJs, String sessionJs,
                                   String ieDataJs, String canvasJs, String webGljs, String fontsFlash, String resolutionFlash,
                                   String languageFlash, String platformFlash, String adBlock){
        String query = "SELECT count(*) FROM FpDataEntity WHERE id= \'"+id+"\' AND acceptHttp=\'"+acceptHttp+
                "\' AND userAgentHttp= \'"+userAgentHttp+"\' AND connectionHttp=\'"+connectionHttp+
                "\' AND encodingHttp=\'"+encodingHttp+"\' AND languageHttp=\'"+languageHttp+"\' AND pluginsJS =\'"+
                pluginsJs+"\' AND platformJS=\'"+platformJs+"\' AND cookiesJs=\'"+cookiesJs+"\' AND dntJs=\'"+dntJs+
                "\' AND timezoneJs=\'"+timezoneJs+"\' AND resolutionJs=\'"+resolutionJs+"\' AND localJs=\'"+
                localJs+"\' AND sessionJs=\'"+sessionJs+"\' AND ieDataJs=\'"+ieDataJs+"\' AND canvasJs=\'"+
                canvasJs+"\' AND webGljs=\'"+webGljs+"\' AND fontsFlash=\'"+fontsFlash+"\' AND resolutionFlash=\'"+
                resolutionFlash+"\' AND languageFlash=\'"+languageFlash+"\' AND platformFlash=\'"+
                platformFlash+"\' AND adBlock=\'"+adBlock+"\'";
        int nbId = withTransaction(em -> ((Long) em.createQuery(query).getResultList().get(0)).intValue());
        return nbId == 1;
    }

    public boolean checkIfFPWithNoJsExists(String id,String userAgentHttp,
                                   String acceptHttp, String connectionHttp, String encodingHttp,
                                   String languageHttp){
        String query = "SELECT count(*) FROM FpDataEntity WHERE id= \'"+id+"\' AND userAgentHttp=\'"+userAgentHttp+
                "\' AND acceptHttp= \'"+acceptHttp+"\' AND connectionHttp=\'"+connectionHttp+
                "\' AND encodingHttp=\'"+encodingHttp+"\' AND languageHttp=\'"+languageHttp+"\' AND pluginsJS =\'no JS\'";
        int nbId = withTransaction(em -> ((Long) em.createQuery(query).getResultList().get(0)).intValue());
        return nbId == 1;
    }


    public FpDataEntity getExistingFP(String id){
        String query = "SELECT counter FROM FpDataEntity WHERE id= \'"+id+"\'";
        int counter = withTransaction(em -> ((Integer) em.createQuery(query).getResultList().get(0)).intValue());
        return withTransaction(em -> em.find(FpDataEntity.class,counter));
    }



    public FpDataEntity createFull(String id, String addressHttp, Timestamp time, String userAgentHttp,
                                   String acceptHttp, String hostHttp, String connectionHttp, String encodingHttp,
                                   String languageHttp, String orderHttp, String pluginsJs, String platformJs, String cookiesJs,
                                   String dntJs, String timezoneJs, String resolutionJs, String localJs, String sessionJs,
                                   String ieDataJs, String canvasJs, String webGljs, String fontsFlash, String resolutionFlash,
                                   String languageFlash, String platformFlash, String adBlock, String octaneScore, String sunspiderTime) {
        return withTransaction(em -> {
            FpDataEntity fp = new FpDataEntity();
            fp.setId(id);
            fp.setAddressHttp(addressHttp);
            fp.setTime(time);
            fp.setUserAgentHttp(userAgentHttp);
            fp.setAcceptHttp(acceptHttp);
            fp.setHostHttp(hostHttp);
            fp.setConnectionHttp(connectionHttp);
            fp.setEncodingHttp(encodingHttp);
            fp.setLanguageHttp(languageHttp);
            fp.setOrderHttp(orderHttp);
            fp.setPluginsJs(pluginsJs);
            fp.setPlatformJs(platformJs);
            fp.setCookiesJs(cookiesJs);
            fp.setDntJs(dntJs);
            fp.setTimezoneJs(timezoneJs);
            fp.setResolutionJs(resolutionJs);
            fp.setLocalJs(localJs);
            fp.setSessionJs(sessionJs);
            fp.setIeDataJs(ieDataJs);
            fp.setCanvasJs(canvasJs);
            fp.setWebGljs(webGljs);
            fp.setFontsFlash(fontsFlash);
            fp.setResolutionFlash(resolutionFlash);
            fp.setLanguageFlash(languageFlash);
            fp.setPlatformFlash(platformFlash);
            fp.setAdBlock(adBlock);
            fp.setOctaneScore(octaneScore);
            fp.setSunspiderTime(sunspiderTime);
            em.persist(fp);
            return fp;
        });
    }

    public FpDataEntity createWithoutFlash(String id, String addressHttp, Timestamp time, String userAgentHttp,
                                           String acceptHttp, String hostHttp, String connectionHttp, String encodingHttp,
                                           String languageHttp, String orderHttp, String pluginsJs, String platformJs, String cookiesJs,
                                           String dntJs, String timezoneJs, String resolutionJs, String localJs, String sessionJs,
                                           String ieDataJs, String canvasJs, String webGljs, String adBlock,
                                           String octaneScore, String sunspiderTime) {
        return createFull(id, addressHttp, time, userAgentHttp,
                acceptHttp, hostHttp, connectionHttp, encodingHttp,
                languageHttp, orderHttp, pluginsJs, platformJs, cookiesJs,
                dntJs, timezoneJs, resolutionJs, localJs, sessionJs,
                ieDataJs, canvasJs, webGljs, "", "",
                "", "", adBlock, octaneScore, sunspiderTime);

    }


    public FpDataEntity createWithoutJavaScript(String id, String addressHttp, Timestamp time, String userAgentHttp,
                                                String acceptHttp, String hostHttp, String connectionHttp, String encodingHttp,
                                                String languageHttp, String orderHttp) {
        String noJs = "no JS";
        return createFull(id, addressHttp, time, userAgentHttp,
                acceptHttp, hostHttp, connectionHttp, encodingHttp,
                languageHttp, orderHttp, noJs, noJs, noJs,
                noJs, noJs, noJs, noJs, noJs,
                noJs, noJs, noJs, noJs, noJs,
                noJs, noJs, noJs, noJs, noJs);
    }


    public Map<String,Double> getPercentages(JsonNode values) {

        //Get the total number of entries in the database
        String nbTotalQuery = "SELECT count(*) FROM FpDataEntity";
        double nbTotal = withTransaction(em -> ((Long) em.createQuery(nbTotalQuery).getResultList().get(0)).doubleValue());

        /**
         For each attribute
         -> computation of the percentage of each value
         **/
        //Query to get the number of entries with the same value
        String nbSameValueBaseQuery = "SELECT count(*) FROM FpDataEntity WHERE ";//Add attribute = value
        HashMap<String,Double> percentage = new HashMap<>();

        Iterator<String> it = values.fieldNames();
        while(it.hasNext()) {
            String column = it.next();

            //Computation of the percentage
            String nbSameValueQuery = (nbSameValueBaseQuery+column+" = "+values.get(column)).replace("\"","'");
            double nbSameValue = withTransaction(em -> ((Long) em.createQuery(nbSameValueQuery).getResultList().get(0)).doubleValue());
            if(nbSameValue != 1.0) {
                percentage.put(column, nbSameValue * 100 / nbTotal);
            } else {
                percentage.put(column, -1.0);
            }
        }
        percentage.put("nbTotal",nbTotal);
        return percentage;
    }

    public Map<String,Long> getPlatformStats(){
        ArrayList<String> platform = new ArrayList<>();
        platform.add("Mac"); platform.add("Win"); platform.add("Linux");
        Map<String, Long> platformMap = new HashMap<String, Long>(platform.size());
        Long nb = (long) 0;
        for(int i =0; i<platform.size(); i++){
            String query = "SELECT count(*) FROM FpDataEntity WHERE platformJs LIKE \'%"+platform.get(i)+"%\'";
            Long nbPlatform = withTransaction(em -> ((Long) em.createQuery(query).getResultList().get(0)));
            nb += nbPlatform;
            platformMap.put(platform.get(i),nbPlatform);
        }

        String nbTotalQuery = "SELECT count(*) FROM FpDataEntity";
        Long nbTotal = withTransaction(em -> ((Long) em.createQuery(nbTotalQuery).getResultList().get(0)));
        platformMap.put("Others",nbTotal-nb);
        return platformMap;
    }

    public int getNumberOfIdenticalFingerprints(JsonNode values){
        String query = "SELECT count(*) FROM FpDataEntity WHERE ";

        Iterator<String> it = values.fieldNames();
        String column = it.next();
        query+=column+" = "+values.get(column);

        while(it.hasNext()) {
            column = it.next();
            query+=" AND "+column+" = \""+values.get(column.toString())+"\"";
        }

        query = query.replace("\"\"","'").replace("\"","'");
        String finalQuery = query;
        return withTransaction(em -> ((Long) em.createQuery(finalQuery).getResultList().get(0)).intValue());
    }

    public int getNumberOfEntries(){
        String nbTotalQuery = "SELECT count(*) FROM FpDataEntity";
        return withTransaction(em -> ((Long) em.createQuery(nbTotalQuery).getResultList().get(0)).intValue());
    }

    public int getNumberOfUniqueEntries(){
        String uniqueQuery = "SELECT COUNT(*) FROM (" +
                "SELECT COUNT(*) as num FROM fpData GROUP BY userAgentHttp, acceptHttp, connectionHttp, " +
                "encodingHttp, languageHttp, orderHttp, pluginsJS, platformJS, cookiesJS, dntJS, timezoneJS, " +
                "resolutionJS, localJS, sessionJS, IEDataJS, canvasJS, webGLJS, fontsFlash, resolutionFlash, " +
                "languageFlash, platformFlash, adBlock" +
                ") as T " +
                "where num = 1";
        return withTransaction(em -> ((BigInteger) em.createNativeQuery(uniqueQuery).getResultList().get(0)).intValue());
    }

    public ArrayList<Object[]> getTimezoneStats(){
        String timeQuery = "SELECT timezoneJS, count(timezoneJS) FROM fpData GROUP BY timezoneJS";
        ArrayList<Object[]> res = withTransaction(em -> (ArrayList<Object[]>)  (em.createNativeQuery(timeQuery).getResultList()));
        return res;
    }

    public Vermap getLanguageStats(){
        String query = "SELECT languageHttp FROM FpDataEntity";
        ArrayList<String> langList = withTransaction(em -> ((ArrayList<String>) em.createQuery(query).getResultList()));
        Pattern langP = Pattern.compile("^(\\S\\S)");
        Vermap langMap = new Vermap();

        for(int i=0; i<langList.size(); i++){
             Matcher langM = langP.matcher(langList.get(i));
             if(langM.find()) {
                 langMap.add(langM.group(1));
             } else {
                 langMap.add("Not communicated");
             }
        }
        return langMap;
    }

    public HashMap<String,HashMap<String,Vermap>> getOSBrowserStats(){

        String query = "SELECT userAgentHttp FROM FpDataEntity";
        ArrayList<String> userAgents = withTransaction(em -> ((ArrayList<String>) em.createQuery(query).getResultList()));

        /* Browser */
        HashMap<String,Vermap> browsers = new HashMap<>();
        browsers.put("Firefox", new Vermap());
        browsers.put("Chrome", new Vermap());
        browsers.put("Safari", new Vermap());
        browsers.put("IE", new Vermap());
        browsers.put("Opera", new Vermap());
        browsers.put("Others", new Vermap());

        /* OS */
        HashMap<String,Vermap> os  = new HashMap<>();
        os.put("Windows", new Vermap());
        os.put("Linux", new Vermap());
        os.put("Mac", new Vermap());
        os.put("Android", new Vermap());
        os.put("iOS", new Vermap());
        os.put("Windows Phone", new Vermap());
        os.put("Others", new Vermap());


        /* Parse user agents */
        for(int i=0; i<userAgents.size(); i++){
            ParsedFP ua = new ParsedFP(userAgents.get(i));
            browsers.get(ua.getBrowser()).add(ua.getBrowserVersion());
            os.get(ua.getOs()).add(ua.getOsVersion());
        }

        HashMap<String,HashMap<String,Vermap>> res = new HashMap<>();
        res.put("browsers",browsers);
        res.put("os",os);
        return res;
    }

    public Rangemap getFontsStats(){
        String query = "SELECT fontsFlash FROM FpDataEntity";
        ArrayList<String> fontsList = withTransaction(em -> ((ArrayList<String>) em.createQuery(query).getResultList()));
        Rangemap nbFontsMap = new Rangemap();

        for(int i=0; i<fontsList.size(); i++){
            Integer nbFonts = fontsList.get(i).split("_").length;
            if(nbFonts>2) {
                int step = 50;
                int j = step;
                while (j < nbFonts) {
                    j += step;
                }
                nbFontsMap.add((j - step) + "-" + j);
            }
        }
        return nbFontsMap;
    }




}
