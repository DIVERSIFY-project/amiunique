package models;

import play.db.jpa.JPA;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.sql.Timestamp;
import java.util.function.Function;

public class ExtensionDataEntityManager {

     private <A> A withTransaction(Function<EntityManager, A> f) {
        try {
            return JPA.withTransaction(() -> f.apply(JPA.em()));
        } catch (Throwable throwable) {
            throw new RuntimeException(throwable);
        }
    }

    public ExtensionDataEntity createFP(String id, String addressHttp, Timestamp creation, Timestamp update, Timestamp end,
                                   String userAgentHttp,String acceptHttp, String hostHttp, String connectionHttp, String encodingHttp,
                                   String languageHttp, String orderHttp, String pluginsJs, String platformJs, String cookiesJs,
                                   String dntJs, String timezoneJs, String resolutionJs, String localJs, String sessionJs,
                                   String ieDataJs, String canvasJs, String webGlJs, String fontsFlash, String resolutionFlash,
                                   String languageFlash, String platformFlash, String adBlock, String vendorJs,
                                   String rendererJs, String octaneScore, String sunspiderTime, String pluginsJsHashed,
                                   String canvasJsHashed, String webGLJsHashed, String fontsFlashHashed) {
        return withTransaction(em -> {
            ExtensionDataEntity fp = new ExtensionDataEntity();
            fp.setId(id);
            fp.setAddressHttp(addressHttp);
            fp.setCreationDate(creation);
            fp.setUpdateDate(update);
            fp.setEndDate(end);
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
            fp.setWebGlJsHashed(webGLJsHashed);
            fp.setFontsFlashHashed(fontsFlashHashed);
            em.persist(fp);
            return fp;
        });
    }

    public boolean checkIfIDExists(String id){

        String query = "SELECT count(*) FROM extensionData WHERE id= :id";
        int nbId = withTransaction(em -> {
            Query q = em.createNativeQuery(query).setParameter("id", id);
            return ((Number) q.getResultList().get(0)).intValue();
        });

        System.out.println("Nb ID : "+nbId);
        return nbId > 0;
    }

    public boolean checkIfLastFPIsIdentical(String id,String userAgentHttp,
                                   String acceptHttp, String encodingHttp,
                                   String languageHttp, String pluginsJsHashed, String platformJs, String cookiesJs,
                                   String dntJs, String timezoneJs, String resolutionJs, String localJs, String sessionJs,
                                   String ieDataJs, String canvasJsHashed, String fontsFlashHashed, String resolutionFlash,
                                   String languageFlash, String platformFlash, String adBlock){

        String query = "SELECT count(*) FROM ("+
                "SELECT * FROM extensionData WHERE id= :id ORDER BY counter DESC limit 1)b "+
                "WHERE acceptHttp= :acceptHttp AND userAgentHttp= :userAgentHttp AND encodingHttp= :encodingHttp "+
                "AND languageHttp= :languageHttp AND pluginsJSHashed = :pluginsJsHashed "+
                "AND platformJS= :platformJs AND cookiesJS= :cookiesJs AND dntJS= :dntJs " +
                "AND timezoneJS= :timezoneJs AND resolutionJS= :resolutionJs AND localJS= :localJs "+
                "AND sessionJS= :sessionJs AND IEDataJS= :ieDataJs AND canvasJSHashed= :canvasJsHashed "+
                "AND fontsFlashHashed= :fontsFlashHashed AND resolutionFlash= :resolutionFlash "+
                "AND languageFlash= :languageFlash AND platformFlash= :platformFlash AND adBlock= :adBlock ";

        int nbId = withTransaction(em -> {
            Query q = em.createNativeQuery(query)
            .setParameter("id", id).setParameter("acceptHttp",acceptHttp).setParameter("userAgentHttp", userAgentHttp)
            .setParameter("encodingHttp", encodingHttp);
            q.setParameter("languageHttp",languageHttp).setParameter("pluginsJsHashed",pluginsJsHashed).setParameter("platformJs",platformJs)
            .setParameter("cookiesJs", cookiesJs).setParameter("dntJs",dntJs).setParameter("timezoneJs",timezoneJs);
            q.setParameter("resolutionJs",resolutionJs).setParameter("localJs",localJs).setParameter("sessionJs",sessionJs)
            .setParameter("ieDataJs", ieDataJs).setParameter("canvasJsHashed",canvasJsHashed);
            q.setParameter("fontsFlashHashed", fontsFlashHashed).setParameter("resolutionFlash",resolutionFlash)
            .setParameter("languageFlash", languageFlash).setParameter("platformFlash",platformFlash)
            .setParameter("adBlock", adBlock);
            return ((Number) q.getResultList().get(0)).intValue();
        });
        System.out.println("FP identical: "+(nbId == 1));
        return nbId == 1;
    }


    public int updateLastFP(String id, Timestamp currentTime){
        String query = "UPDATE extensionData SET updateDate= :updateDate WHERE id= :id ORDER BY counter DESC LIMIT 1";
        System.out.println("FP updated");
        return withTransaction(em -> {
            Query q = em.createNativeQuery(query).setParameter("updateDate",currentTime).setParameter("id", id);
            return q.executeUpdate();
        });
    }

    public int endLastFP(String id, Timestamp currentTime){
        String query = "UPDATE extensionData SET endDate= :endDate WHERE id= :id ORDER BY counter DESC LIMIT 1";
        System.out.println("FP ended");
        return withTransaction(em -> {
            Query q = em.createNativeQuery(query).setParameter("endDate",currentTime).setParameter("id", id);
            return q.executeUpdate();
        });
    }

}
