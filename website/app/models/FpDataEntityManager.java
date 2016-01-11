package models;

import com.fasterxml.jackson.databind.JsonNode;
import org.hibernate.ScrollMode;
import org.hibernate.ScrollableResults;
import org.hibernate.Session;
import org.hibernate.StatelessSession;
import play.db.jpa.JPA;

import javax.persistence.EntityManager;
import javax.persistence.Query;
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
                                   String acceptHttp, String encodingHttp,
                                   String languageHttp, String pluginsJsHashed, String platformJs, String cookiesJs,
                                   String dntJs, String timezoneJs, String resolutionJs, String localJs, String sessionJs,
                                   String ieDataJs, String canvasJsHashed, String webGlJsHashed, String fontsFlashHashed, String resolutionFlash,
                                   String languageFlash, String platformFlash, String adBlock){

        String query = "SELECT count(*) FROM FpDataEntity WHERE id= :id AND acceptHttp= :acceptHttp " +
                "AND userAgentHttp= :userAgentHttp AND encodingHttp= :encodingHttp "+
                "AND languageHttp= :languageHttp AND pluginsJsHashed = :pluginsJsHashed "+
                "AND platformJs= :platformJs AND cookiesJs= :cookiesJs AND dntJs= :dntJs " +
                "AND timezoneJs= :timezoneJs AND resolutionJs= :resolutionJs AND localJs= :localJs "+
                "AND sessionJs= :sessionJs AND ieDataJs= :ieDataJs AND canvasJsHashed= :canvasJsHashed "+
                "AND webGlJsHashed= :webGlJsHashed AND fontsFlashHashed= :fontsFlashHashed AND resolutionFlash= :resolutionFlash "+
                "AND languageFlash= :languageFlash AND platformFlash= :platformFlash AND adBlock= :adBlock ";

        int nbId = withTransaction(em -> {
            Query q = em.createQuery(query)
            .setParameter("id", id).setParameter("acceptHttp",acceptHttp).setParameter("userAgentHttp", userAgentHttp)
            .setParameter("encodingHttp", encodingHttp);
            q.setParameter("languageHttp",languageHttp).setParameter("pluginsJsHashed",pluginsJsHashed).setParameter("platformJs",platformJs)
            .setParameter("cookiesJs", cookiesJs).setParameter("dntJs",dntJs).setParameter("timezoneJs",timezoneJs);
            q.setParameter("resolutionJs",resolutionJs).setParameter("localJs",localJs).setParameter("sessionJs",sessionJs)
            .setParameter("ieDataJs", ieDataJs).setParameter("canvasJsHashed",canvasJsHashed).setParameter("webGlJsHashed",webGlJsHashed);
            q.setParameter("fontsFlashHashed", fontsFlashHashed).setParameter("resolutionFlash",resolutionFlash)
            .setParameter("languageFlash", languageFlash).setParameter("platformFlash",platformFlash)
            .setParameter("adBlock", adBlock);
            return ((Long) q.getResultList().get(0)).intValue();
        });
        return nbId == 1;
    }

    public boolean checkIfFPWithNoJsExists(String id,String userAgentHttp,
                                   String acceptHttp, String encodingHttp,
                                   String languageHttp){

        String query = "SELECT count(*) FROM FpDataEntity WHERE id= :id AND acceptHttp= :acceptHttp " +
                "AND userAgentHttp= :userAgentHttp AND encodingHttp= :encodingHttp "+
                "AND languageHttp= :languageHttp AND pluginsJS =\'no JS\'";

        int nbId = withTransaction(em -> ((Long) em.createQuery(query)
                .setParameter("id", id).setParameter("acceptHttp",acceptHttp).setParameter("userAgentHttp", userAgentHttp)
                .setParameter("encodingHttp", encodingHttp).setParameter("languageHttp",languageHttp)
                .getResultList().get(0)).intValue());
        return nbId == 1;
    }


    public FpDataEntity getExistingFPById(String id){
        String query = "SELECT counter FROM FpDataEntity WHERE id= :id";
        int counter = withTransaction(em -> ((Integer) em.createQuery(query).setParameter("id", id).getResultList().get(0)).intValue());
        return withTransaction(em -> em.find(FpDataEntity.class,counter));
    }


    public FpDataEntity getExistingFPByCounter(int counter){
        return withTransaction(em -> em.find(FpDataEntity.class,counter));
    }


    public TreeSet<FpDataEntity> getExistingFPsById(String id){
        String query = "SELECT counter FROM FpDataEntity WHERE id= :id";
        List<Integer> counters= withTransaction(em -> (em.createQuery(query).setParameter("id", id).getResultList()));

        TreeSet<FpDataEntity> fps = new TreeSet<FpDataEntity>();
        for(int c : counters){
            fps.add(getExistingFPByCounter(c));
        }

        return fps;
    }

    public FpDataEntity createFull(String id, String addressHttp, Timestamp time, String userAgentHttp,
                                   String acceptHttp, String hostHttp, String connectionHttp, String encodingHttp,
                                   String languageHttp, String orderHttp, String pluginsJs, String platformJs, String cookiesJs,
                                   String dntJs, String timezoneJs, String resolutionJs, String localJs, String sessionJs,
                                   String ieDataJs, String canvasJs, String webGlJs, String fontsFlash, String resolutionFlash,
                                   String languageFlash, String platformFlash, String adBlock, String vendorJs,
                                   String rendererJs, String octaneScore, String sunspiderTime, String pluginsJsHashed,
                                   String canvasJsHashed, String webGLJsHashed, String fontsFlashHashed) {
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
            fp.setWebGlJs(webGlJs);
            fp.setFontsFlash(fontsFlash);
            fp.setResolutionFlash(resolutionFlash);
            fp.setLanguageFlash(languageFlash);
            fp.setPlatformFlash(platformFlash);
            fp.setAdBlock(adBlock);
            fp.setVendorWebGljs(vendorJs);
            fp.setRendererWebGljs(rendererJs);
            fp.setOctaneScore(octaneScore);
            fp.setSunspiderTime(sunspiderTime);
            fp.setPluginsJsHashed(pluginsJsHashed);
            fp.setCanvasJsHashed(canvasJsHashed);
            fp.setWebGLJsHashed(webGLJsHashed);
            fp.setFontsFlashHashed(fontsFlashHashed);
            em.persist(fp);
            return fp;
        });
    }

    public FpDataEntity createWithoutFlash(String id, String addressHttp, Timestamp time, String userAgentHttp,
                                           String acceptHttp, String hostHttp, String connectionHttp, String encodingHttp,
                                           String languageHttp, String orderHttp, String pluginsJs, String platformJs, String cookiesJs,
                                           String dntJs, String timezoneJs, String resolutionJs, String localJs, String sessionJs,
                                           String ieDataJs, String canvasJs, String webGlJs, String adBlock, String vendorJs, String rendererJs,
                                           String octaneScore, String sunspiderTime, String pluginsJsHashed,
                                           String canvasJsHashed, String webGLJsHashed) {
        return createFull(id, addressHttp, time, userAgentHttp,
                acceptHttp, hostHttp, connectionHttp, encodingHttp,
                languageHttp, orderHttp, pluginsJs, platformJs, cookiesJs,
                dntJs, timezoneJs, resolutionJs, localJs, sessionJs,
                ieDataJs, canvasJs, webGlJs, "", "",
                "", "", adBlock, vendorJs, rendererJs, octaneScore, sunspiderTime,
                pluginsJsHashed, canvasJsHashed, webGLJsHashed, "");

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
                noJs, noJs, noJs, noJs, noJs,
                noJs, noJs, noJs, noJs, noJs, noJs);
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
            String nbSameValueQuery = nbSameValueBaseQuery+column+" = :"+column;
            double nbSameValue = withTransaction(em -> ((Long) em.createQuery(nbSameValueQuery)
                    .setParameter(column, (values.get(column).asText()).replace("\"", "'"))
                    .getResultList().get(0)).doubleValue());
            if(nbSameValue != 1.0) {
                percentage.put(column, nbSameValue * 100 / nbTotal);
            } else {
                percentage.put(column, -1.0);
            }
        }
        return percentage;
    }

    public int getNumberOfIdenticalFingerprints(JsonNode values){
        String query = "SELECT count(*) FROM FpDataEntity WHERE ";

        Iterator<String> it = values.fieldNames();
        String column = it.next();
        query+=column+" = :"+column;

        //Building query
        while(it.hasNext()) {
            column = it.next();
            query+=" AND "+column+" = :"+column;
        }
        String finalQuery = query;

        return withTransaction(em -> {
            Iterator<String> itQ = values.fieldNames();
            String col = itQ.next();
            Query q = em.createQuery(finalQuery);
            q.setParameter(col,(values.get(col).asText()).replace("\"", "'"));
            while(itQ.hasNext()) {
              col = itQ.next();
              q.setParameter(col,(values.get(col).asText()).replace("\"", "'"));
            }
            return ((Long) q.getResultList().get(0)).intValue();
        });
    }

    public int getNumberOfEntries(){
        String nbTotalQuery = "SELECT count(*) FROM FpDataEntity";
        return withTransaction(em -> ((Long) em.createQuery(nbTotalQuery).getResultList().get(0)).intValue());
    }

    public int getNumberOfEntriesSinceDate(Timestamp tsl, Timestamp tsu){
        String nbTotalQuery = "SELECT count(*) FROM FpDataEntity WHERE time BETWEEN :lower AND :upper";
        return withTransaction(em -> ((Long) em.createQuery(nbTotalQuery)
            .setParameter("lower",tsl)
            .setParameter("upper",tsu)
            .getResultList().get(0)).intValue());
    }

    public CounterMap getTimezoneStats(){
        String timeQuery = "SELECT timezoneJS, count(timezoneJS) FROM fpData GROUP BY timezoneJS";
        ArrayList<Object[]> q = withTransaction(em -> (ArrayList<Object[]>)  (em.createNativeQuery(timeQuery).getResultList()));

        CounterMap res = new CounterMap();
        for(Object[] obj:  q){
            res.add(obj[0].toString(), obj[1].toString());
        }
        return res;
    }

    public CounterMap getTimezoneStatsSinceDate(Timestamp tsl, Timestamp tsu){
        String timeQuery = "SELECT timezoneJS, count(timezoneJS) FROM fpData WHERE time BETWEEN :lower AND :upper GROUP BY timezoneJS";
        ArrayList<Object[]> q = withTransaction(em -> (ArrayList<Object[]>)  (em.createNativeQuery(timeQuery)
            .setParameter("lower",tsl)
            .setParameter("upper",tsu)
            .getResultList()));

        CounterMap res = new CounterMap();
        for(Object[] obj:  q){
            res.add(obj[0].toString(), obj[1].toString());
        }
        return res;
    }


    public VersionMap getLanguageStats(){
        String query = "SELECT languageHttp FROM FpDataEntity";
        return withTransaction(em -> {
            Session session = (Session) em.getDelegate();
            StatelessSession stateless = session.getSessionFactory().openStatelessSession();
            stateless.beginTransaction();
            ScrollableResults langList = stateless.createQuery(query).scroll(ScrollMode.FORWARD_ONLY);

            VersionMap res = parseLanguages(langList);

            stateless.getTransaction().commit();
            stateless.close();
            return res;
        });
    }

    public VersionMap getLanguageStatsSinceDate(Timestamp tsl, Timestamp tsu){
        String query = "SELECT languageHttp FROM FpDataEntity WHERE time BETWEEN :lower AND :upper";
        return withTransaction(em -> {
            Session session = (Session) em.getDelegate();
            StatelessSession stateless = session.getSessionFactory().openStatelessSession();
            stateless.beginTransaction();
            ScrollableResults langList = stateless.createQuery(query)
                    .setParameter("lower",tsl)
                    .setParameter("upper",tsu)
                    .scroll(ScrollMode.FORWARD_ONLY);

            VersionMap res = parseLanguages(langList);

            stateless.getTransaction().commit();
            stateless.close();
            return res;
        });
    }

    public VersionMap parseLanguages(ScrollableResults langList){
        Pattern langP = Pattern.compile("^(\\S\\S)");
        VersionMap langMap = new VersionMap();
        while (langList.next()) {
             Matcher langM = langP.matcher((String) langList.get(0));
             if(langM.find()) {
                 langMap.add(langM.group(1));
             } else {
                 langMap.add("Not communicated");
             }
        }
        return langMap;
    }


    public HashMap<String,HashMap<String, VersionMap>> getOSBrowserStats(){
        String query = "SELECT userAgentHttp FROM FpDataEntity";
        return withTransaction(em -> {
            Session session = (Session) em.getDelegate();
            StatelessSession stateless = session.getSessionFactory().openStatelessSession();
            stateless.beginTransaction();
            ScrollableResults userAgentList = stateless.createQuery(query).scroll(ScrollMode.FORWARD_ONLY);

            HashMap<String,HashMap<String, VersionMap>> res = parseUserAgents(userAgentList);

            stateless.getTransaction().commit();
            stateless.close();
            return res;
        });
    }

    public HashMap<String,HashMap<String, VersionMap>> getOSBrowserStatsSinceDate(Timestamp tsl, Timestamp tsu){
        String query = "SELECT userAgentHttp FROM FpDataEntity WHERE time BETWEEN :lower AND :upper";
        return withTransaction(em -> {
            Session session = (Session) em.getDelegate();
            StatelessSession stateless = session.getSessionFactory().openStatelessSession();
            stateless.beginTransaction();
            ScrollableResults userAgentList = stateless.createQuery(query)
                    .setParameter("lower",tsl)
                    .setParameter("upper",tsu)
                    .scroll(ScrollMode.FORWARD_ONLY);

            HashMap<String,HashMap<String, VersionMap>> res = parseUserAgents(userAgentList);

            stateless.getTransaction().commit();
            stateless.close();
            return res;
        });
    }

    public HashMap<String,HashMap<String, VersionMap>> parseUserAgents(ScrollableResults userAgents){

        /* Browser */
        HashMap<String, VersionMap> browsers = new HashMap<>();
        browsers.put("Firefox", new VersionMap());
        browsers.put("Chrome", new VersionMap());
        browsers.put("Safari", new VersionMap());
        browsers.put("IE", new VersionMap());
        browsers.put("Edge", new VersionMap());
        browsers.put("Opera", new VersionMap());
        browsers.put("Others", new VersionMap());

        /* OS */
        HashMap<String, VersionMap> os  = new HashMap<>();
        os.put("Windows", new VersionMap());
        os.put("Linux", new VersionMap());
        os.put("Mac", new VersionMap());
        os.put("Android", new VersionMap());
        os.put("iOS", new VersionMap());
        os.put("Windows Phone", new VersionMap());
        os.put("Others", new VersionMap());


        /* Parse user agents */
         while (userAgents.next()) {
            ParsedFP ua = new ParsedFP((String) userAgents.get(0));
            browsers.get(ua.getBrowser()).add(ua.getBrowserVersion());
            os.get(ua.getOs()).add(ua.getOsVersion());
        }

        HashMap<String,HashMap<String, VersionMap>> res = new HashMap<>();
        res.put("browsers",browsers);
        res.put("os",os);
        return res;
    }

    public RangeMap getFontsStats(){
        String query = "SELECT fontsFlash FROM FpDataEntity";
        return withTransaction(em -> {
            Session session = (Session) em.getDelegate();
            StatelessSession stateless = session.getSessionFactory().openStatelessSession();
            stateless.beginTransaction();
            ScrollableResults fontsList = stateless.createQuery(query).scroll(ScrollMode.FORWARD_ONLY);

            RangeMap nbFontsMap = parseFonts(fontsList);

            stateless.getTransaction().commit();
            stateless.close();
            return nbFontsMap;
        });
    }

    public RangeMap getFontsStatsSinceDate(Timestamp tsl, Timestamp tsu){
        String query = "SELECT fontsFlash FROM FpDataEntity WHERE time BETWEEN :lower AND :upper";
        return withTransaction(em -> {
            Session session = (Session) em.getDelegate();
            StatelessSession stateless = session.getSessionFactory().openStatelessSession();
            stateless.beginTransaction();
            ScrollableResults fontsList = stateless.createQuery(query)
                    .setParameter("lower",tsl)
                    .setParameter("upper",tsu)
                    .scroll(ScrollMode.FORWARD_ONLY);

            RangeMap nbFontsMap = parseFonts(fontsList);

            stateless.getTransaction().commit();
            stateless.close();
            return nbFontsMap;
        });
    }

    public RangeMap parseFonts(ScrollableResults fontsList){
        RangeMap nbFontsMap = new RangeMap();

        while (fontsList.next()) {
            String fonts = (String) fontsList.get(0);
            Integer nbFonts = fonts.split("_").length;
            if (nbFonts > 2) {
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


    public ArrayList<String> getAttribute(String attribute){
        String query = "SELECT "+attribute+" FROM FpDataEntity";
        ArrayList<String> listAttribute = withTransaction(em -> ((ArrayList<String>) em.createQuery(query).getResultList()));

        return listAttribute;
    }

    public List<Object[]> getStatsAttribute(String attribute){
        String query = "SELECT "+attribute+", count(*) AS nbAtt FROM FpDataEntity Group By "+attribute;

        List<Object[]> result = withTransaction(em -> ((List<Object[]>) em.createQuery(query).getResultList()));
        return result;

    }


}
