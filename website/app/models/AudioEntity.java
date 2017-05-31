package models;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@javax.persistence.Table(name = "audio", schema = "fingerprint", catalog = "")
public class AudioEntity {
    private int counter;

    @Id
    @javax.persistence.Column(name = "counter")
    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

    private String id;

    @Basic
    @javax.persistence.Column(name = "id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    private String acSampleRate;

    @Basic
    @javax.persistence.Column(name = "acSampleRate")
    public String getAcSampleRate() {
        return acSampleRate;
    }

    public void setAcSampleRate(String acSampleRate) {
        this.acSampleRate = acSampleRate;
    }

    private String acState;

    @Basic
    @javax.persistence.Column(name = "acState")
    public String getAcState() {
        return acState;
    }

    public void setAcState(String acState) {
        this.acState = acState;
    }

    private String acMaxChannelCount;

    @Basic
    @javax.persistence.Column(name = "acMaxChannelCount")
    public String getAcMaxChannelCount() {
        return acMaxChannelCount;
    }

    public void setAcMaxChannelCount(String acMaxChannelCount) {
        this.acMaxChannelCount = acMaxChannelCount;
    }

    private String acNumberOfInputs;

    @Basic
    @javax.persistence.Column(name = "acNumberOfInputs")
    public String getAcNumberOfInputs() {
        return acNumberOfInputs;
    }

    public void setAcNumberOfInputs(String acNumberOfInputs) {
        this.acNumberOfInputs = acNumberOfInputs;
    }

    private String acNumberOfOutputs;

    @Basic
    @javax.persistence.Column(name = "acNumberOfOutputs")
    public String getAcNumberOfOutputs() {
        return acNumberOfOutputs;
    }

    public void setAcNumberOfOutputs(String acNumberOfOutputs) {
        this.acNumberOfOutputs = acNumberOfOutputs;
    }

    private String acChannelCount;

    @Basic
    @javax.persistence.Column(name = "acChannelCount")
    public String getAcChannelCount() {
        return acChannelCount;
    }

    public void setAcChannelCount(String acChannelCount) {
        this.acChannelCount = acChannelCount;
    }

    private String acChannelCountMode;

    @Basic
    @javax.persistence.Column(name = "acChannelCountMode")
    public String getAcChannelCountMode() {
        return acChannelCountMode;
    }

    public void setAcChannelCountMode(String acChannelCountMode) {
        this.acChannelCountMode = acChannelCountMode;
    }

    private String acChannelInterpretation;

    @Basic
    @javax.persistence.Column(name = "acChannelInterpretation")
    public String getAcChannelInterpretation() {
        return acChannelInterpretation;
    }

    public void setAcChannelInterpretation(String acChannelInterpretation) {
        this.acChannelInterpretation = acChannelInterpretation;
    }

    private String anFftSize;

    @Basic
    @javax.persistence.Column(name = "anFftSize")
    public String getAnFftSize() {
        return anFftSize;
    }

    public void setAnFftSize(String anFftSize) {
        this.anFftSize = anFftSize;
    }

    private String anFrequencyBinCount;

    @Basic
    @javax.persistence.Column(name = "anFrequencyBinCount")
    public String getAnFrequencyBinCount() {
        return anFrequencyBinCount;
    }

    public void setAnFrequencyBinCount(String anFrequencyBinCount) {
        this.anFrequencyBinCount = anFrequencyBinCount;
    }

    private String anMinDecibels;

    @Basic
    @javax.persistence.Column(name = "anMinDecibels")
    public String getAnMinDecibels() {
        return anMinDecibels;
    }

    public void setAnMinDecibels(String anMinDecibels) {
        this.anMinDecibels = anMinDecibels;
    }

    private String anMaxDecibels;

    @Basic
    @javax.persistence.Column(name = "anMaxDecibels")
    public String getAnMaxDecibels() {
        return anMaxDecibels;
    }

    public void setAnMaxDecibels(String anMaxDecibels) {
        this.anMaxDecibels = anMaxDecibels;
    }

    private String anSmoothingTimeConstant;

    @Basic
    @javax.persistence.Column(name = "anSmoothingTimeConstant")
    public String getAnSmoothingTimeConstant() {
        return anSmoothingTimeConstant;
    }

    public void setAnSmoothingTimeConstant(String anSmoothingTimeConstant) {
        this.anSmoothingTimeConstant = anSmoothingTimeConstant;
    }

    private String anNumberOfInputs;

    @Basic
    @javax.persistence.Column(name = "anNumberOfInputs")
    public String getAnNumberOfInputs() {
        return anNumberOfInputs;
    }

    public void setAnNumberOfInputs(String anNumberOfInputs) {
        this.anNumberOfInputs = anNumberOfInputs;
    }

    private String anNumberOfOutputs;

    @Basic
    @javax.persistence.Column(name = "anNumberOfOutputs")
    public String getAnNumberOfOutputs() {
        return anNumberOfOutputs;
    }

    public void setAnNumberOfOutputs(String anNumberOfOutputs) {
        this.anNumberOfOutputs = anNumberOfOutputs;
    }

    private String anChannelCount;

    @Basic
    @javax.persistence.Column(name = "anChannelCount")
    public String getAnChannelCount() {
        return anChannelCount;
    }

    public void setAnChannelCount(String anChannelCount) {
        this.anChannelCount = anChannelCount;
    }

    private String anChannelCountMode;

    @Basic
    @javax.persistence.Column(name = "anChannelCountMode")
    public String getAnChannelCountMode() {
        return anChannelCountMode;
    }

    public void setAnChannelCountMode(String anChannelCountMode) {
        this.anChannelCountMode = anChannelCountMode;
    }

    private String anChannelInterpretation;

    @Basic
    @javax.persistence.Column(name = "anChannelInterpretation")
    public String getAnChannelInterpretation() {
        return anChannelInterpretation;
    }

    public void setAnChannelInterpretation(String anChannelInterpretation) {
        this.anChannelInterpretation = anChannelInterpretation;
    }

    private String audioDynSum;

    @Basic
    @javax.persistence.Column(name = "audioDynSum")
    public String getAudioDynSum() {
        return audioDynSum;
    }

    public void setAudioDynSum(String audioDynSum) {
        this.audioDynSum = audioDynSum;
    }

    private String audioDynHash;

    @Basic
    @javax.persistence.Column(name = "audioDynHash")
    public String getAudioDynHash() {
        return audioDynHash;
    }

    public void setAudioDynHash(String audioDynHash) {
        this.audioDynHash = audioDynHash;
    }

    private String audioPoints;

    @Basic
    @javax.persistence.Column(name = "audioPoints")
    public String getAudioPoints() {
        return audioPoints;
    }

    public void setAudioPoints(String audioPoints) {
        this.audioPoints = audioPoints;
    }

    private String audioDynPoints;

    @Basic
    @javax.persistence.Column(name = "audioDynPoints")
    public String getAudioDynPoints() {
        return audioDynPoints;
    }

    public void setAudioDynPoints(String audioDynPoints) {
        this.audioDynPoints = audioDynPoints;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AudioEntity that = (AudioEntity) o;

        if (counter != that.counter) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (acSampleRate != null ? !acSampleRate.equals(that.acSampleRate) : that.acSampleRate != null) return false;
        if (acState != null ? !acState.equals(that.acState) : that.acState != null) return false;
        if (acMaxChannelCount != null ? !acMaxChannelCount.equals(that.acMaxChannelCount) : that.acMaxChannelCount != null)
            return false;
        if (acNumberOfInputs != null ? !acNumberOfInputs.equals(that.acNumberOfInputs) : that.acNumberOfInputs != null)
            return false;
        if (acNumberOfOutputs != null ? !acNumberOfOutputs.equals(that.acNumberOfOutputs) : that.acNumberOfOutputs != null)
            return false;
        if (acChannelCount != null ? !acChannelCount.equals(that.acChannelCount) : that.acChannelCount != null)
            return false;
        if (acChannelCountMode != null ? !acChannelCountMode.equals(that.acChannelCountMode) : that.acChannelCountMode != null)
            return false;
        if (acChannelInterpretation != null ? !acChannelInterpretation.equals(that.acChannelInterpretation) : that.acChannelInterpretation != null)
            return false;
        if (anFftSize != null ? !anFftSize.equals(that.anFftSize) : that.anFftSize != null) return false;
        if (anFrequencyBinCount != null ? !anFrequencyBinCount.equals(that.anFrequencyBinCount) : that.anFrequencyBinCount != null)
            return false;
        if (anMinDecibels != null ? !anMinDecibels.equals(that.anMinDecibels) : that.anMinDecibels != null)
            return false;
        if (anMaxDecibels != null ? !anMaxDecibels.equals(that.anMaxDecibels) : that.anMaxDecibels != null)
            return false;
        if (anSmoothingTimeConstant != null ? !anSmoothingTimeConstant.equals(that.anSmoothingTimeConstant) : that.anSmoothingTimeConstant != null)
            return false;
        if (anNumberOfInputs != null ? !anNumberOfInputs.equals(that.anNumberOfInputs) : that.anNumberOfInputs != null)
            return false;
        if (anNumberOfOutputs != null ? !anNumberOfOutputs.equals(that.anNumberOfOutputs) : that.anNumberOfOutputs != null)
            return false;
        if (anChannelCount != null ? !anChannelCount.equals(that.anChannelCount) : that.anChannelCount != null)
            return false;
        if (anChannelCountMode != null ? !anChannelCountMode.equals(that.anChannelCountMode) : that.anChannelCountMode != null)
            return false;
        if (anChannelInterpretation != null ? !anChannelInterpretation.equals(that.anChannelInterpretation) : that.anChannelInterpretation != null)
            return false;
        if (audioDynSum != null ? !audioDynSum.equals(that.audioDynSum) : that.audioDynSum != null) return false;
        if (audioDynHash != null ? !audioDynHash.equals(that.audioDynHash) : that.audioDynHash != null) return false;
        if (audioPoints != null ? !audioPoints.equals(that.audioPoints) : that.audioPoints != null) return false;
        if (audioDynPoints != null ? !audioDynPoints.equals(that.audioDynPoints) : that.audioDynPoints != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = counter;
        result = 31 * result + (id != null ? id.hashCode() : 0);
        result = 31 * result + (acSampleRate != null ? acSampleRate.hashCode() : 0);
        result = 31 * result + (acState != null ? acState.hashCode() : 0);
        result = 31 * result + (acMaxChannelCount != null ? acMaxChannelCount.hashCode() : 0);
        result = 31 * result + (acNumberOfInputs != null ? acNumberOfInputs.hashCode() : 0);
        result = 31 * result + (acNumberOfOutputs != null ? acNumberOfOutputs.hashCode() : 0);
        result = 31 * result + (acChannelCount != null ? acChannelCount.hashCode() : 0);
        result = 31 * result + (acChannelCountMode != null ? acChannelCountMode.hashCode() : 0);
        result = 31 * result + (acChannelInterpretation != null ? acChannelInterpretation.hashCode() : 0);
        result = 31 * result + (anFftSize != null ? anFftSize.hashCode() : 0);
        result = 31 * result + (anFrequencyBinCount != null ? anFrequencyBinCount.hashCode() : 0);
        result = 31 * result + (anMinDecibels != null ? anMinDecibels.hashCode() : 0);
        result = 31 * result + (anMaxDecibels != null ? anMaxDecibels.hashCode() : 0);
        result = 31 * result + (anSmoothingTimeConstant != null ? anSmoothingTimeConstant.hashCode() : 0);
        result = 31 * result + (anNumberOfInputs != null ? anNumberOfInputs.hashCode() : 0);
        result = 31 * result + (anNumberOfOutputs != null ? anNumberOfOutputs.hashCode() : 0);
        result = 31 * result + (anChannelCount != null ? anChannelCount.hashCode() : 0);
        result = 31 * result + (anChannelCountMode != null ? anChannelCountMode.hashCode() : 0);
        result = 31 * result + (anChannelInterpretation != null ? anChannelInterpretation.hashCode() : 0);
        result = 31 * result + (audioDynSum != null ? audioDynSum.hashCode() : 0);
        result = 31 * result + (audioDynHash != null ? audioDynHash.hashCode() : 0);
        result = 31 * result + (audioPoints != null ? audioPoints.hashCode() : 0);
        result = 31 * result + (audioDynPoints != null ? audioDynPoints.hashCode() : 0);
        return result;
    }
}
