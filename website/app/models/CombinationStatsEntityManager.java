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


    public CombinationStatsEntity getExistingCSByCombination(String combination){
        return withTransaction(em -> em.find(CombinationStatsEntity.class,combination));
    }

}
