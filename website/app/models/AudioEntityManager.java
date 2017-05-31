package models;

import play.db.jpa.JPA;

import javax.persistence.EntityManager;
import java.util.function.Function;

public class AudioEntityManager {

    private <A> A withTransaction(Function<EntityManager, A> f) {
        try {
            return JPA.withTransaction(() -> f.apply(JPA.em()));
        } catch (Throwable throwable) {
            throw new RuntimeException(throwable);
        }
    }

    public AudioEntity create(String id, String acSampleRate, String acState, String acMaxChannelCount,
                              String acNumberOfInputs, String acNumberOfOutputs, String acChannelCount,
                              String acChannelCountMode, String acChannelInterpretation, String anFftSize,
                              String anFrequencyBinCount, String anMinDecibels, String anMaxDecibels,
                              String anSmoothingTimeConstant, String anNumberOfInputs, String anNumberOfOutputs,
                              String anChannelCount, String anChannelCountMode, String anChannelInterpretation,
                              String audioDynSum, String audioDynHash, String audioPoints, String audioDynPoints){
        return withTransaction(em -> {
            AudioEntity at = new AudioEntity();
            at.setId(id);
            at.setAcSampleRate(acSampleRate);
            at.setAcState(acState);
            at.setAcMaxChannelCount(acMaxChannelCount);
            at.setAcNumberOfInputs(acNumberOfInputs);
            at.setAcNumberOfOutputs(acNumberOfOutputs);
            at.setAcChannelCount(acChannelCount);
            at.setAcChannelCountMode(acChannelCountMode);
            at.setAcChannelInterpretation(acChannelInterpretation);
            at.setAnFftSize(anFftSize);
            at.setAnFrequencyBinCount(anFrequencyBinCount);
            at.setAnMinDecibels(anMinDecibels);
            at.setAnMaxDecibels(anMaxDecibels);
            at.setAnSmoothingTimeConstant(anSmoothingTimeConstant);
            at.setAnNumberOfInputs(anNumberOfInputs);
            at.setAnNumberOfOutputs(anNumberOfOutputs);
            at.setAnChannelCount(anChannelCount);
            at.setAnChannelCountMode(anChannelCountMode);
            at.setAnChannelInterpretation(anChannelInterpretation);
            at.setAudioDynSum(audioDynSum);
            at.setAudioDynHash(audioDynHash);
            at.setAudioPoints(audioPoints);
            at.setAudioDynPoints(audioDynPoints);
            em.persist(at);
            return at;
        });
    }

}
