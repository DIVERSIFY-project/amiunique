package models;

import play.db.jpa.JPA;

import javax.persistence.EntityManager;
import java.util.function.Function;

public class BlockExtensionsEntityManager {

    private <A> A withTransaction(Function<EntityManager, A> f) {
        try {
            return JPA.withTransaction(() -> f.apply(JPA.em()));
        } catch (Throwable throwable) {
            throw new RuntimeException(throwable);
        }
    }

    public BlockExtensionsEntity create(String id, String latest_links, String lowe_links, String old_links,
                              String random_links, String latest_results, String lowe_results,
                              String old_results, String random_results){
        return withTransaction(em -> {
            BlockExtensionsEntity at = new BlockExtensionsEntity();
            at.setId(id);
            at.setLatestLinks(latest_links);
            at.setLoweLinks(lowe_links);
            at.setOldLinks(old_links);
            at.setRandomLinks(random_links);
            at.setLatestResults(latest_results);
            at.setLoweResults(lowe_results);
            at.setOldResults(old_results);
            at.setRandomResults(random_results);
            em.persist(at);
            return at;
        });
    }

}
