package models;

import com.fasterxml.jackson.databind.JsonNode;
import play.db.jpa.JPA;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.sql.Timestamp;
import java.util.*;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class CombinationStatsEntityManager {

    private <A> A withTransaction(Function<EntityManager, A> f) {
        try {
            return JPA.withTransaction(() -> f.apply(JPA.em()));
        } catch (Throwable throwable) {
            throw new RuntimeException(throwable);
        }
    }

    public CombinationStatsEntity createCombinationStats(String combination, String indicator, long number){
        return withTransaction(em -> {
            CombinationStatsEntity cs = new CombinationStatsEntity();
            cs.setCombination(combination);
            cs.setIndicator(indicator);
            cs.setNumber(number);
            
            em.persist(cs);
            return cs;
        });
    }

    public int getNumberOfEntries(){
        String nbTotalQuery = "SELECT count(*) FROM CombinationStatsEntity";
        return withTransaction(em -> ((Long) em.createQuery(nbTotalQuery).getResultList().get(0)).intValue());
    }

    public int updateCombinationStats(String combination, String indicator, long number){
        String query = "UPDATE CombinationStatsEntity cs SET cs.number = :number WHERE cs.indicator = :indicator AND cs.combination = :combination";
        int counter = withTransaction(em -> (Integer)( em.createQuery(query)
                        .setParameter("number", number)
                        .setParameter("indicator", indicator)
                        .setParameter("combination", combination)
                        .executeUpdate()));
        return counter;
    }

    public Map<String,Double> getPercentages(JsonNode values) {
        //Get the total number of entries in the database
        String nbTotalQuery = "SELECT count(*) FROM FpDataEntity";
        double nbTotal = withTransaction(em -> ((Long) em.createQuery(nbTotalQuery).getResultList().get(0)).doubleValue());

        String nbSameValueBaseQuery = "SELECT number FROM CombinationStatsEntity WHERE ";//Add attribute = value
        HashMap<String,Double> percentage = new HashMap<>();
        Iterator<String> it = values.fieldNames();
        while(it.hasNext()) {
            String column = it.next();
            String nbSameValueQuery = nbSameValueBaseQuery+" combination = :col and indicator = :indic";
            long nbSameValue = (withTransaction(em -> {
                Query q = em.createQuery(nbSameValueQuery)
                    .setParameter("col", values.get(column).asText().replace("\"", "'"))
                    .setParameter("indic",column);

                List l = q.getResultList();
                return (Long) l.get(0);
            }));

            percentage.put(column, (nbSameValue/nbTotal)*100);
        }
        return percentage;
    }

    public boolean testExistingCombination(String combi){
        String query = "SELECT count(*) FROM CombinationStatsEntity WHERE combination = :combi";
        int nbTotal = withTransaction(em -> ((Long) em.createQuery(query)
            .setParameter("combi",combi)
            .getResultList().get(0)).intValue());

        if(nbTotal > 0){
            return true;
        }else{
            return false;
        }
    }

    public Long getNbIdenticalPlugins(String plugin) {
        String query = "SELECT count(*) FROM FpDataEntity WHERE pluginsJs LIKE :plugin";
        Long nbTotal = withTransaction(em -> ((Long) em.createQuery(query)
            .setParameter("plugin","%"+plugin+"%")
            .getResultList().get(0)).longValue());
        return nbTotal;
    }

    public HashMap<String,Double> getPercentagesPlugins(String pluginsJs){

        //Get the total number of entries in the database
        String nbTotalQuery = "SELECT count(*) FROM FpDataEntity";
        double nbTotal = withTransaction(em -> ((Long) em.createQuery(nbTotalQuery).getResultList().get(0)).doubleValue());

        String patternStringPlugin = "Plugin [0-9]+: ([a-zA-Z -.]+)";
        Pattern pattern = Pattern.compile(patternStringPlugin);
        Matcher matcher = pattern.matcher(pluginsJs);

        HashMap<String,Double> percentage = new HashMap<>();
        String query = "SELECT number FROM CombinationStatsEntity WHERE indicator='pluginsJs' AND combination = :combination";

        while(matcher.find()) {
            String plugin = matcher.group(1);
            if(plugin !=null){                    
                double percSameValue = (withTransaction(em -> ((Long) em.createQuery(query)
                        .setParameter("combination",plugin)
                        .getResultList().get(0)).intValue()))/nbTotal;

                percentage.put(plugin, percSameValue*100);
            }
        }
        return percentage;
    }

    public void updateCombinationStats(String userAgentHttp, String acceptHttp, String connectionHttp,
                    String encodingHttp, String languageHttp, String orderHttp, String pluginsJs, String platformJs, String cookiesJs,
                    String dntJs, String timezoneJs, String resolutionJs, String localJs, String sessionJs, String IEDataJs,
                    String resolutionFlash, String languageFlash, String platformFlash,
                    String adBlock, String pluginsJsHashed, String canvasJsHashed, String fontsFlashHashed){

        //UserAgentHtpp
        String query = "UPDATE CombinationStatsEntity SET number = number +1 WHERE indicator='userAgentHttp' AND combination = :combination";
        if ((withTransaction(em -> (Integer) (em.createQuery(query)
                .setParameter("combination", userAgentHttp)
                .executeUpdate()))) == 0) {
            createCombinationStats(userAgentHttp, "userAgentHttp", 1);
        }

        //AcceptHttp
        String query2 = "UPDATE CombinationStatsEntity SET number = number +1 WHERE indicator='acceptHttp' AND combination = :combination";
        if ((withTransaction(em -> (Integer) (em.createQuery(query2)
                .setParameter("combination", acceptHttp)
                .executeUpdate()))) == 0) {
            createCombinationStats(acceptHttp, "acceptHttp", 1);
        }

        //connectionHttp
        String query3 = "UPDATE CombinationStatsEntity SET number = number +1 WHERE indicator='connectionHttp' AND combination = :combination";
        if ((withTransaction(em -> (Integer) (em.createQuery(query3)
                .setParameter("combination", connectionHttp)
                .executeUpdate()))) == 0) {
            createCombinationStats(connectionHttp, "connectionHttp", 1);
        }

        //encodingHttp
        String query4 = "UPDATE CombinationStatsEntity SET number = number +1 WHERE indicator='encodingHttp' AND combination = :combination";
        if ((withTransaction(em -> (Integer) (em.createQuery(query4)
                .setParameter("combination", encodingHttp)
                .executeUpdate()))) == 0) {
            createCombinationStats(encodingHttp, "encodingHttp", 1);
        }

        //languageHttp
        String query5 = "UPDATE CombinationStatsEntity SET number = number +1 WHERE indicator='languageHttp' AND combination = :combination";
        if ((withTransaction(em -> (Integer) (em.createQuery(query5)
                .setParameter("combination", languageHttp)
                .executeUpdate()))) == 0) {
            createCombinationStats(languageHttp, "languageHttp", 1);
        }

        //orderHttp
        String query6 = "UPDATE CombinationStatsEntity SET number = number +1 WHERE indicator='orderHttp' AND combination = :combination";
        if ((withTransaction(em -> (Integer) (em.createQuery(query6)
                .setParameter("combination", orderHttp)
                .executeUpdate()))) == 0) {
            createCombinationStats(orderHttp, "orderHttp", 1);
        }

        //platformJs
        String query7 = "UPDATE CombinationStatsEntity SET number = number +1 WHERE indicator='platformJs' AND combination = :combination";
        if ((withTransaction(em -> (Integer) (em.createQuery(query7)
                .setParameter("combination", platformJs)
                .executeUpdate()))) == 0) {
            createCombinationStats(platformJs, "platformJs", 1);
        }

        if(!platformJs.equals("no JS")) {

            //cookiesJs
            String query8 = "UPDATE CombinationStatsEntity SET number = number +1 WHERE indicator='cookiesJs' AND combination = :combination";
            if ((withTransaction(em -> (Integer) (em.createQuery(query8)
                    .setParameter("combination", cookiesJs)
                    .executeUpdate()))) == 0) {
                createCombinationStats(cookiesJs, "cookiesJs", 1);
            }

            //dntJs
            String query9 = "UPDATE CombinationStatsEntity SET number = number +1 WHERE indicator='dntJs' AND combination = :combination";
            if ((withTransaction(em -> (Integer) (em.createQuery(query9)
                    .setParameter("combination", dntJs)
                    .executeUpdate()))) == 0) {
                createCombinationStats(dntJs, "dntJs", 1);
            }

            //timezoneJs
            String query10 = "UPDATE CombinationStatsEntity SET number = number +1 WHERE indicator='timezoneJs' AND combination = :combination";
            if ((withTransaction(em -> (Integer) (em.createQuery(query10)
                    .setParameter("combination", timezoneJs)
                    .executeUpdate()))) == 0) {
                createCombinationStats(timezoneJs, "timezoneJs", 1);
            }

            //resolutionJs
            String query11 = "UPDATE CombinationStatsEntity SET number = number +1 WHERE indicator='resolutionJs' AND combination = :combination";
            if ((withTransaction(em -> (Integer) (em.createQuery(query11)
                    .setParameter("combination", resolutionJs)
                    .executeUpdate()))) == 0) {
                createCombinationStats(resolutionJs, "resolutionJs", 1);
            }

            //localJs
            String query12 = "UPDATE CombinationStatsEntity SET number = number +1 WHERE indicator='localJs' AND combination = :combination";
            if ((withTransaction(em -> (Integer) (em.createQuery(query12)
                    .setParameter("combination", localJs)
                    .executeUpdate()))) == 0) {
                createCombinationStats(localJs, "localJs", 1);
            }

            //sessionJs
            String query13 = "UPDATE CombinationStatsEntity SET number = number +1 WHERE indicator='sessionJs' AND combination = :combination";
            if ((withTransaction(em -> (Integer) (em.createQuery(query13)
                    .setParameter("combination", sessionJs)
                    .executeUpdate()))) == 0) {
                createCombinationStats(sessionJs, "sessionJs", 1);
            }

            //IEDataJs
            String query14 = "UPDATE CombinationStatsEntity SET number = number +1 WHERE indicator='IEDataJs' AND combination = :combination";
            if ((withTransaction(em -> (Integer) (em.createQuery(query14)
                    .setParameter("combination", IEDataJs)
                    .executeUpdate()))) == 0) {
                createCombinationStats(IEDataJs, "IEDataJs", 1);
            }

            //resolutionFlash
            String query15 = "UPDATE CombinationStatsEntity SET number = number +1 WHERE indicator='resolutionFlash' AND combination = :combination";
            if ((withTransaction(em -> (Integer) (em.createQuery(query15)
                    .setParameter("combination", resolutionFlash)
                    .executeUpdate()))) == 0) {
                createCombinationStats(resolutionFlash, "resolutionFlash", 1);
            }

            //languageFlash
            String query16 = "UPDATE CombinationStatsEntity SET number = number +1 WHERE indicator='languageFlash' AND combination = :combination";
            if ((withTransaction(em -> (Integer) (em.createQuery(query16)
                    .setParameter("combination", languageFlash)
                    .executeUpdate()))) == 0) {
                createCombinationStats(languageFlash, "languageFlash", 1);
            }

            //platformFlash
            String query17 = "UPDATE CombinationStatsEntity SET number = number +1 WHERE indicator='platformFlash' AND combination = :combination";
            if ((withTransaction(em -> (Integer) (em.createQuery(query17)
                    .setParameter("combination", platformFlash)
                    .executeUpdate()))) == 0) {
                createCombinationStats(platformFlash, "platformFlash", 1);
            }

            //adBlock
            String query18 = "UPDATE CombinationStatsEntity SET number = number +1 WHERE indicator='adBlock' AND combination = :combination";
            if ((withTransaction(em -> (Integer) (em.createQuery(query18)
                    .setParameter("combination", adBlock)
                    .executeUpdate()))) == 0) {
                createCombinationStats(adBlock, "adBlock", 1);
            }

            //pluginsJsHashed
            String query19 = "UPDATE CombinationStatsEntity SET number = number +1 WHERE indicator='pluginsJsHashed' AND combination = :combination";
            if ((withTransaction(em -> (Integer) (em.createQuery(query19)
                    .setParameter("combination", pluginsJsHashed)
                    .executeUpdate()))) == 0) {
                createCombinationStats(pluginsJsHashed, "pluginsJsHashed", 1);
            }

            //canvasJsHashed
            String query20 = "UPDATE CombinationStatsEntity SET number = number +1 WHERE indicator='canvasJsHashed' AND combination = :combination";
            if ((withTransaction(em -> (Integer) (em.createQuery(query20)
                    .setParameter("combination", canvasJsHashed)
                    .executeUpdate()))) == 0) {
                createCombinationStats(canvasJsHashed, "canvasJsHashed", 1);
            }

            //fontsFlashHashed
            String query21 = "UPDATE CombinationStatsEntity SET number = number +1 WHERE indicator='fontsFlashHashed' AND combination = :combination";
            if ((withTransaction(em -> (Integer) (em.createQuery(query21)
                    .setParameter("combination", fontsFlashHashed)
                    .executeUpdate()))) == 0) {
                createCombinationStats(fontsFlashHashed, "fontsFlashHashed", 1);
            }

            String query22 = "UPDATE CombinationStatsEntity SET number = number +1 WHERE indicator='pluginsJs' AND combination = :combination";
            String patternStringPlugin = "Plugin [0-9]+: ([a-zA-Z -.]+)";
            Pattern pattern = Pattern.compile(patternStringPlugin);

            Matcher matcher = pattern.matcher(pluginsJs);

            while (matcher.find()) {
                String plugin = matcher.group(1);
                if (plugin != null) {
                    if ((withTransaction(em -> (Integer) (em.createQuery(query22)
                            .setParameter("combination", plugin)
                            .executeUpdate()))) == 0) {
                        createCombinationStats(plugin, "pluginsJs", 1);
                    }
                } else {
                    if ((withTransaction(em -> (Integer) (em.createQuery(query22)
                            .setParameter("combination", pluginsJs)
                            .executeUpdate()))) == 0) {
                        createCombinationStats(pluginsJs, "pluginsJs", 1);
                    }
                }
            }

        } else {
            //With no JS
            //Bug FIX: we retrive the number of no JS values from the platform attribute
            String platQuery = "SELECT number from CombinationStatsEntity WHERE indicator='platformJs' AND combination='no JS'";
            int nbNoJS = withTransaction(em -> ((Long) em.createQuery(platQuery).getResultList().get(0)).intValue());
            String[] attributes = {"cookiesJs","dntJs","timezoneJs","resolutionJs","localJs","sessionJs","IEDataJs","resolutionFlash",
                    "languageFlash","platformFlash","adBlock","pluginsJsHashed","canvasJsHashed","fontsFlashHashed", "pluginsJS"};
            for(String  att: attributes){
                String queryNoJS = "UPDATE CombinationStatsEntity SET number = "+nbNoJS+" WHERE indicator='"+att+"' AND combination = 'no JS'";
                if ((withTransaction(em -> (Integer) (em.createQuery(queryNoJS).executeUpdate()))) == 0) {
                    createCombinationStats(canvasJsHashed, att, nbNoJS);
                }
            }
        }

    }


}
