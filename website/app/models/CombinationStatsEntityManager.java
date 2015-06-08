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



    public CombinationStatsEntity createCombinationStats(String combination, String indicator, long number, float percentage){
        return withTransaction(em -> {
            CombinationStatsEntity cs = new CombinationStatsEntity();
            cs.setCombination(combination);
            cs.setIndicator(indicator);
            cs.setNumber(number);
            cs.setPercentage(percentage);
            
            em.persist(cs);
            return cs;
        });
    }

    public int updateCombinationStats(String combination, String indicator, long number, float percentage){
        String query = "UPDATE CombinationStatsEntity cs SET cs.number = :number, cs.percentage = :percentage WHERE cs.indicator = :indicator AND cs.combination = :combination";
        int counter = withTransaction(em -> (Integer)( em.createQuery(query)
                        .setParameter("number", number)
                        .setParameter("percentage",percentage)
                        .setParameter("indicator", indicator)
                        .setParameter("combination", combination)
                        .executeUpdate()));
        return counter;
    }

    public Map<String,Double> getPercentages(JsonNode values) {

        //Get the total number of entries in the database
        String nbTotalQuery = "SELECT count(*) FROM FpDataEntity";
        double nbTotal = withTransaction(em -> ((Long) em.createQuery(nbTotalQuery).getResultList().get(0)).doubleValue());


        String nbSameValueBaseQuery = "SELECT percentage FROM CombinationStatsEntity WHERE ";//Add attribute = value
        HashMap<String,Double> percentage = new HashMap<>();
        Iterator<String> it = values.fieldNames();
        while(it.hasNext()) {
            String column = it.next();
            String nbSameValueQuery = nbSameValueBaseQuery+" combination = :col and indicator = :indic";
                      
            try{
                double percSameValue = withTransaction(em -> ((Float) em.createQuery(nbSameValueQuery)
                        .setParameter("col", (values.get(column).asText()).replace("\"", "'"))
                        .setParameter("indic",column)
                        .getResultList().get(0)).doubleValue());

                percentage.put(column, percSameValue);
            }catch(Exception e){
                double percSameValue = 1.0 / nbTotal;
                percentage.put(column, percSameValue);
            }

        }
        return percentage;
    }

    public boolean testExistingCombination(String combi){
        String query = "SELECT count(*) FROM CombinationStatsEntity WHERE combination = :combi";
        long nbTotal = withTransaction(em -> ((Long) em.createQuery(query)
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
        String query = "SELECT percentage FROM CombinationStatsEntity WHERE indicator='pluginsJs' AND combination = :combination";

        while(matcher.find()) {
            String plugin = matcher.group(1);
            if(plugin !=null){                    
                try{
                    double percSameValue = withTransaction(em -> ((Float) em.createQuery(query)
                            .setParameter("combination",plugin)
                            .getResultList().get(0)).doubleValue());

                    percentage.put(plugin, percSameValue);
                }catch(Exception e){
                    double percSameValue = 1.0 / nbTotal;
                    percentage.put(plugin, percSameValue);
                }
            }
        }
        return percentage;
    }


}
