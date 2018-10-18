package models;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@javax.persistence.Table(name = "blockExtensions", schema = "fingerprint", catalog = "")
public class BlockExtensionsEntity {
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

    private String latest_links;

    @Basic
    @javax.persistence.Column(name = "latest_links")
    public String getLatestLinks() {
        return latest_links;
    }

    public void setLatestLinks(String latest_links) {
        this.latest_links = latest_links;
    }

    private String lowe_links;

    @Basic
    @javax.persistence.Column(name = "lowe_links")
    public String getLoweLinks() {
        return lowe_links;
    }

    public void setLoweLinks(String lowe_links) {
        this.lowe_links = lowe_links;
    }


    private String old_links;

    @Basic
    @javax.persistence.Column(name = "old_links")
    public String getOldLinks() {
        return old_links;
    }

    public void setOldLinks(String old_links) {
        this.old_links = old_links;
    }


    private String random_links;

    @Basic
    @javax.persistence.Column(name = "random_links")
    public String getRandomLinks() {
        return random_links;
    }

    public void setRandomLinks(String random_links) {
        this.random_links = random_links;
    }



    private String latest_results;

    @Basic
    @javax.persistence.Column(name = "latest_results")
    public String getLatestResults() {
        return latest_results;
    }

    public void setLatestResults(String latest_results) {
        this.latest_results = latest_results;
    }

    private String lowe_results;

    @Basic
    @javax.persistence.Column(name = "lowe_results")
    public String getLoweResults() {
        return lowe_results;
    }

    public void setLoweResults(String lowe_results) {
        this.lowe_results = lowe_results;
    }


    private String old_results;

    @Basic
    @javax.persistence.Column(name = "old_results")
    public String getOldResults() {
        return old_results;
    }

    public void setOldResults(String old_results) {
        this.old_results = old_results;
    }


    private String random_results;

    @Basic
    @javax.persistence.Column(name = "random_results")
    public String getRandomResults() {
        return random_results;
    }

    public void setRandomResults(String random_results) {
        this.random_results = random_results;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BlockExtensionsEntity that = (BlockExtensionsEntity) o;

        if (counter != that.counter) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (latest_links != null ? !latest_links.equals(that.latest_links) : that.latest_links != null) return false;
        if (lowe_links != null ? !lowe_links.equals(that.lowe_links) : that.lowe_links != null) return false;
        if (old_links != null ? !old_links.equals(that.old_links) : that.old_links != null) return false;
        if (random_links != null ? !random_links.equals(that.random_links) : that.random_links != null) return false;
        if (latest_results != null ? !latest_results.equals(that.latest_results) : that.latest_results != null) return false;
        if (lowe_results != null ? !lowe_results.equals(that.lowe_results) : that.lowe_results != null) return false;
        if (old_results != null ? !old_results.equals(that.old_results) : that.old_results != null) return false;
        if (random_results != null ? !random_results.equals(that.random_results) : that.random_results != null) return false;
        
        return true;
    }

}

