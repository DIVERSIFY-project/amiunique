package models;

import play.db.jpa.JPA;

import javax.persistence.EntityManager;
import java.util.function.Function;

public class CanvasTestEntityManager {

    private <A> A withTransaction(Function<EntityManager, A> f) {
        try {
            return JPA.withTransaction(() -> f.apply(JPA.em()));
        } catch (Throwable throwable) {
            throw new RuntimeException(throwable);
        }
    }

    public CanvasTestEntity create(String id, String canvas1, String canvas2, String canvas3, String canvas4, String canvas5,
                                   String canvas6, String canvas7, String canvas8, String canvas9, String canvas10,
                                   String canvas11, String canvas12, String canvas13, String canvas14, String canvas15,
                                   String canvas16, String canvas17, String canvas18,
                                   String canvas1Hashed, String canvas2Hashed, String canvas3Hashed,
                                   String canvas4Hashed, String canvas5Hashed, String canvas6Hashed,
                                   String canvas7Hashed, String canvas8Hashed, String canvas9Hashed,
                                   String canvas10Hashed, String canvas11Hashed, String canvas12Hashed,
                                   String canvas13Hashed, String canvas14Hashed, String canvas15Hashed,
                                   String canvas16Hashed, String canvas17Hashed, String canvas18Hashed) {
        return withTransaction(em -> {
            CanvasTestEntity ct = new CanvasTestEntity();
            ct.setId(id);
            ct.setCanvas1(canvas1);
            ct.setCanvas2(canvas2);
            ct.setCanvas3(canvas3);
            ct.setCanvas4(canvas4);
            ct.setCanvas5(canvas5);
            ct.setCanvas6(canvas6);
            ct.setCanvas7(canvas7);
            ct.setCanvas8(canvas8);
            ct.setCanvas9(canvas9);
            ct.setCanvas10(canvas10);
            ct.setCanvas11(canvas11);
            ct.setCanvas12(canvas12);
            ct.setCanvas13(canvas13);
            ct.setCanvas14(canvas14);
            ct.setCanvas15(canvas15);
            ct.setCanvas16(canvas16);
            ct.setCanvas17(canvas17);
            ct.setCanvas18(canvas18);
            ct.setCanvas1Hashed(canvas1Hashed);
            ct.setCanvas2Hashed(canvas2Hashed);
            ct.setCanvas3Hashed(canvas3Hashed);
            ct.setCanvas4Hashed(canvas4Hashed);
            ct.setCanvas5Hashed(canvas5Hashed);
            ct.setCanvas6Hashed(canvas6Hashed);
            ct.setCanvas7Hashed(canvas7Hashed);
            ct.setCanvas8Hashed(canvas8Hashed);
            ct.setCanvas9Hashed(canvas9Hashed);
            ct.setCanvas10Hashed(canvas10Hashed);
            ct.setCanvas11Hashed(canvas11Hashed);
            ct.setCanvas12Hashed(canvas12Hashed);
            ct.setCanvas13Hashed(canvas13Hashed);
            ct.setCanvas14Hashed(canvas14Hashed);
            ct.setCanvas15Hashed(canvas15Hashed);
            ct.setCanvas16Hashed(canvas16Hashed);
            ct.setCanvas17Hashed(canvas17Hashed);
            ct.setCanvas18Hashed(canvas18Hashed);
            em.persist(ct);
            return ct;
        });
    }
}

