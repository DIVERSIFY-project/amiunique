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



    public CombinationStatsEntity createCombinationStats(String combination, String indicator, int number, float percentage){
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

    public int updateCombinationStats(String combination, String indicator, int number, float percentage){
        String query = "UPDATE CombinationStatsEntity cs SET cs.number = :number, cs.percentage = :percentage WHERE cs.indicator = :indicator AND cs.combination = :combination";
        int counter = withTransaction(em -> (Integer)( em.createQuery(query)
                        .setParameter("number", number)
                        .setParameter("percentage",percentage)
                        .setParameter("indicator", indicator)
                        .setParameter("combination", combination)
                        .executeUpdate()));
        System.out.println("counter  == "+counter);
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
            
            System.out.println("col : "+column+" ----- "+(values.get(column).asText()).replace("\"", "'"));
            
            try{
                double nbSameValue = withTransaction(em -> ((Float) em.createQuery(nbSameValueQuery)
                        .setParameter("col", (values.get(column).asText()).replace("\"", "'"))
                        .setParameter("indic",column)
                        .getResultList().get(0)).doubleValue());

                percentage.put(column, nbSameValue);
                System.out.println("test % = "+String.valueOf(nbSameValue));
            }catch(Exception e){
                double nbSameValue = 1.0 / nbTotal;
                percentage.put(column, nbSameValue);
                System.out.println("test val : "+column+" valeur  = "+(values.get(column).asText()).replace("\"", "'"));
            }

        }
        System.out.println("toto");
        return percentage;
    }

}
