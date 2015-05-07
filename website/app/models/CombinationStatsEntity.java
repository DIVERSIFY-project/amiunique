package models;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "combinationStats", schema = "", catalog="fingerprint")
public class CombinationStatsEntity {
    private int counter;
    private String combination;
    private String indicator;
    private int number;
    private float percentage;

    @Id
    @Column(name = "counter")
    @GeneratedValue
    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

    public CombinationStatsEntity() {
    }

    @Column(name = "combination")
    public String getCombination() {
        return combination;
    }

    public void setCombination(String combination) {
        this.combination = combination;
    }

    @Basic
    @Column(name = "indicator")
    public String getIndicator() {
        return indicator;
    }

    public void setIndicator(String indicator) {
        this.indicator = indicator;
    }

    @Basic
    @Column(name = "number")
    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }


    @Basic
    @Column(name = "percentage")
    public float getPercentage() {
        return percentage;
    }

    public void setPercentage(float percentage) {
        this.percentage = percentage;
    }

    

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;

        CombinationStatsEntity that = (CombinationStatsEntity) o;

        if(that.getIndicator() != this.getIndicator()) return false;
        if(that.getCombination() != this.getCombination()) return false;
        if(that.getNumber() != this.getNumber()) return false;
        if(that.getPercentage() != this.getPercentage()) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = 0;
        result = 31 * result + (combination != null ? combination.hashCode() : 0);
        result = 31 * result + (indicator != null ? indicator.hashCode() : 0);

        return result;
    }
    
}
