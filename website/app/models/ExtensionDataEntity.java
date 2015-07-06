package models;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;

@Entity
@javax.persistence.Table(name = "extensionData", schema = "", catalog = "fingerprint")
public class ExtensionDataEntity {
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

    private String addressHttp;

    @Basic
    @javax.persistence.Column(name = "addressHttp")
    public String getAddressHttp() {
        return addressHttp;
    }

    public void setAddressHttp(String addressHttp) {
        this.addressHttp = addressHttp;
    }

    private Timestamp creationDate;

    @Basic
    @javax.persistence.Column(name = "creationDate")
    public Timestamp getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Timestamp creationDate) {
        this.creationDate = creationDate;
    }

    private Timestamp updateDate;

    @Basic
    @javax.persistence.Column(name = "updateDate")
    public Timestamp getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Timestamp updateDate) {
        this.updateDate = updateDate;
    }

    private Timestamp endDate;

    @Basic
    @javax.persistence.Column(name = "endDate")
    public Timestamp getEndDate() {
        return endDate;
    }

    public void setEndDate(Timestamp endDate) {
        this.endDate = endDate;
    }

    private String userAgentHttp;

    @Basic
    @javax.persistence.Column(name = "userAgentHttp")
    public String getUserAgentHttp() {
        return userAgentHttp;
    }

    public void setUserAgentHttp(String userAgentHttp) {
        this.userAgentHttp = userAgentHttp;
    }

    private String acceptHttp;

    @Basic
    @javax.persistence.Column(name = "acceptHttp")
    public String getAcceptHttp() {
        return acceptHttp;
    }

    public void setAcceptHttp(String acceptHttp) {
        this.acceptHttp = acceptHttp;
    }

    private String hostHttp;

    @Basic
    @javax.persistence.Column(name = "hostHttp")
    public String getHostHttp() {
        return hostHttp;
    }

    public void setHostHttp(String hostHttp) {
        this.hostHttp = hostHttp;
    }

    private String connectionHttp;

    @Basic
    @javax.persistence.Column(name = "connectionHttp")
    public String getConnectionHttp() {
        return connectionHttp;
    }

    public void setConnectionHttp(String connectionHttp) {
        this.connectionHttp = connectionHttp;
    }

    private String encodingHttp;

    @Basic
    @javax.persistence.Column(name = "encodingHttp")
    public String getEncodingHttp() {
        return encodingHttp;
    }

    public void setEncodingHttp(String encodingHttp) {
        this.encodingHttp = encodingHttp;
    }

    private String languageHttp;

    @Basic
    @javax.persistence.Column(name = "languageHttp")
    public String getLanguageHttp() {
        return languageHttp;
    }

    public void setLanguageHttp(String languageHttp) {
        this.languageHttp = languageHttp;
    }

    private String orderHttp;

    @Basic
    @javax.persistence.Column(name = "orderHttp")
    public String getOrderHttp() {
        return orderHttp;
    }

    public void setOrderHttp(String orderHttp) {
        this.orderHttp = orderHttp;
    }

    private String pluginsJs;

    @Basic
    @javax.persistence.Column(name = "pluginsJS")
    public String getPluginsJs() {
        return pluginsJs;
    }

    public void setPluginsJs(String pluginsJs) {
        this.pluginsJs = pluginsJs;
    }

    private String platformJs;

    @Basic
    @javax.persistence.Column(name = "platformJS")
    public String getPlatformJs() {
        return platformJs;
    }

    public void setPlatformJs(String platformJs) {
        this.platformJs = platformJs;
    }

    private String cookiesJs;

    @Basic
    @javax.persistence.Column(name = "cookiesJS")
    public String getCookiesJs() {
        return cookiesJs;
    }

    public void setCookiesJs(String cookiesJs) {
        this.cookiesJs = cookiesJs;
    }

    private String dntJs;

    @Basic
    @javax.persistence.Column(name = "dntJS")
    public String getDntJs() {
        return dntJs;
    }

    public void setDntJs(String dntJs) {
        this.dntJs = dntJs;
    }

    private String timezoneJs;

    @Basic
    @javax.persistence.Column(name = "timezoneJS")
    public String getTimezoneJs() {
        return timezoneJs;
    }

    public void setTimezoneJs(String timezoneJs) {
        this.timezoneJs = timezoneJs;
    }

    private String resolutionJs;

    @Basic
    @javax.persistence.Column(name = "resolutionJS")
    public String getResolutionJs() {
        return resolutionJs;
    }

    public void setResolutionJs(String resolutionJs) {
        this.resolutionJs = resolutionJs;
    }

    private String localJs;

    @Basic
    @javax.persistence.Column(name = "localJS")
    public String getLocalJs() {
        return localJs;
    }

    public void setLocalJs(String localJs) {
        this.localJs = localJs;
    }

    private String sessionJs;

    @Basic
    @javax.persistence.Column(name = "sessionJS")
    public String getSessionJs() {
        return sessionJs;
    }

    public void setSessionJs(String sessionJs) {
        this.sessionJs = sessionJs;
    }

    private String ieDataJs;

    @Basic
    @javax.persistence.Column(name = "IEDataJS")
    public String getIeDataJs() {
        return ieDataJs;
    }

    public void setIeDataJs(String ieDataJs) {
        this.ieDataJs = ieDataJs;
    }

    private String canvasJs;

    @Basic
    @javax.persistence.Column(name = "canvasJS")
    public String getCanvasJs() {
        return canvasJs;
    }

    public void setCanvasJs(String canvasJs) {
        this.canvasJs = canvasJs;
    }

    private String webGlJs;

    @Basic
    @javax.persistence.Column(name = "webGLJs")
    public String getWebGlJs() {
        return webGlJs;
    }

    public void setWebGlJs(String webGlJs) {
        this.webGlJs = webGlJs;
    }

    private String fontsFlash;

    @Basic
    @javax.persistence.Column(name = "fontsFlash")
    public String getFontsFlash() {
        return fontsFlash;
    }

    public void setFontsFlash(String fontsFlash) {
        this.fontsFlash = fontsFlash;
    }

    private String resolutionFlash;

    @Basic
    @javax.persistence.Column(name = "resolutionFlash")
    public String getResolutionFlash() {
        return resolutionFlash;
    }

    public void setResolutionFlash(String resolutionFlash) {
        this.resolutionFlash = resolutionFlash;
    }

    private String languageFlash;

    @Basic
    @javax.persistence.Column(name = "languageFlash")
    public String getLanguageFlash() {
        return languageFlash;
    }

    public void setLanguageFlash(String languageFlash) {
        this.languageFlash = languageFlash;
    }

    private String platformFlash;

    @Basic
    @javax.persistence.Column(name = "platformFlash")
    public String getPlatformFlash() {
        return platformFlash;
    }

    public void setPlatformFlash(String platformFlash) {
        this.platformFlash = platformFlash;
    }

    private String adBlock;

    @Basic
    @javax.persistence.Column(name = "adBlock")
    public String getAdBlock() {
        return adBlock;
    }

    public void setAdBlock(String adBlock) {
        this.adBlock = adBlock;
    }

    private String vendorWebGljs;

    @Basic
    @javax.persistence.Column(name = "vendorWebGLJS")
    public String getVendorWebGljs() {
        return vendorWebGljs;
    }

    public void setVendorWebGljs(String vendorWebGljs) {
        this.vendorWebGljs = vendorWebGljs;
    }

    private String rendererWebGljs;

    @Basic
    @javax.persistence.Column(name = "rendererWebGLJS")
    public String getRendererWebGljs() {
        return rendererWebGljs;
    }

    public void setRendererWebGljs(String rendererWebGljs) {
        this.rendererWebGljs = rendererWebGljs;
    }

    private String octaneScore;

    @Basic
    @javax.persistence.Column(name = "octaneScore")
    public String getOctaneScore() {
        return octaneScore;
    }

    public void setOctaneScore(String octaneScore) {
        this.octaneScore = octaneScore;
    }

    private String sunspiderTime;

    @Basic
    @javax.persistence.Column(name = "sunspiderTime")
    public String getSunspiderTime() {
        return sunspiderTime;
    }

    public void setSunspiderTime(String sunspiderTime) {
        this.sunspiderTime = sunspiderTime;
    }

    private String pluginsJsHashed;

    @Basic
    @javax.persistence.Column(name = "pluginsJSHashed")
    public String getPluginsJsHashed() {
        return pluginsJsHashed;
    }

    public void setPluginsJsHashed(String pluginsJsHashed) {
        this.pluginsJsHashed = pluginsJsHashed;
    }

    private String canvasJsHashed;

    @Basic
    @javax.persistence.Column(name = "canvasJSHashed")
    public String getCanvasJsHashed() {
        return canvasJsHashed;
    }

    public void setCanvasJsHashed(String canvasJsHashed) {
        this.canvasJsHashed = canvasJsHashed;
    }

    private String webGlJsHashed;

    @Basic
    @javax.persistence.Column(name = "webGLJsHashed")
    public String getWebGlJsHashed() {
        return webGlJsHashed;
    }

    public void setWebGlJsHashed(String webGlJsHashed) {
        this.webGlJsHashed = webGlJsHashed;
    }

    private String fontsFlashHashed;

    @Basic
    @javax.persistence.Column(name = "fontsFlashHashed")
    public String getFontsFlashHashed() {
        return fontsFlashHashed;
    }

    public void setFontsFlashHashed(String fontsFlashHashed) {
        this.fontsFlashHashed = fontsFlashHashed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ExtensionDataEntity that = (ExtensionDataEntity) o;

        if (counter != that.counter) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (addressHttp != null ? !addressHttp.equals(that.addressHttp) : that.addressHttp != null) return false;
        if (creationDate != null ? !creationDate.equals(that.creationDate) : that.creationDate != null) return false;
        if (updateDate != null ? !updateDate.equals(that.updateDate) : that.updateDate != null) return false;
        if (endDate != null ? !endDate.equals(that.endDate) : that.endDate != null) return false;
        if (userAgentHttp != null ? !userAgentHttp.equals(that.userAgentHttp) : that.userAgentHttp != null)
            return false;
        if (acceptHttp != null ? !acceptHttp.equals(that.acceptHttp) : that.acceptHttp != null) return false;
        if (hostHttp != null ? !hostHttp.equals(that.hostHttp) : that.hostHttp != null) return false;
        if (connectionHttp != null ? !connectionHttp.equals(that.connectionHttp) : that.connectionHttp != null)
            return false;
        if (encodingHttp != null ? !encodingHttp.equals(that.encodingHttp) : that.encodingHttp != null) return false;
        if (languageHttp != null ? !languageHttp.equals(that.languageHttp) : that.languageHttp != null) return false;
        if (orderHttp != null ? !orderHttp.equals(that.orderHttp) : that.orderHttp != null) return false;
        if (pluginsJs != null ? !pluginsJs.equals(that.pluginsJs) : that.pluginsJs != null) return false;
        if (platformJs != null ? !platformJs.equals(that.platformJs) : that.platformJs != null) return false;
        if (cookiesJs != null ? !cookiesJs.equals(that.cookiesJs) : that.cookiesJs != null) return false;
        if (dntJs != null ? !dntJs.equals(that.dntJs) : that.dntJs != null) return false;
        if (timezoneJs != null ? !timezoneJs.equals(that.timezoneJs) : that.timezoneJs != null) return false;
        if (resolutionJs != null ? !resolutionJs.equals(that.resolutionJs) : that.resolutionJs != null) return false;
        if (localJs != null ? !localJs.equals(that.localJs) : that.localJs != null) return false;
        if (sessionJs != null ? !sessionJs.equals(that.sessionJs) : that.sessionJs != null) return false;
        if (ieDataJs != null ? !ieDataJs.equals(that.ieDataJs) : that.ieDataJs != null) return false;
        if (canvasJs != null ? !canvasJs.equals(that.canvasJs) : that.canvasJs != null) return false;
        if (webGlJs != null ? !webGlJs.equals(that.webGlJs) : that.webGlJs != null) return false;
        if (fontsFlash != null ? !fontsFlash.equals(that.fontsFlash) : that.fontsFlash != null) return false;
        if (resolutionFlash != null ? !resolutionFlash.equals(that.resolutionFlash) : that.resolutionFlash != null)
            return false;
        if (languageFlash != null ? !languageFlash.equals(that.languageFlash) : that.languageFlash != null)
            return false;
        if (platformFlash != null ? !platformFlash.equals(that.platformFlash) : that.platformFlash != null)
            return false;
        if (adBlock != null ? !adBlock.equals(that.adBlock) : that.adBlock != null) return false;
        if (vendorWebGljs != null ? !vendorWebGljs.equals(that.vendorWebGljs) : that.vendorWebGljs != null)
            return false;
        if (rendererWebGljs != null ? !rendererWebGljs.equals(that.rendererWebGljs) : that.rendererWebGljs != null)
            return false;
        if (octaneScore != null ? !octaneScore.equals(that.octaneScore) : that.octaneScore != null) return false;
        if (sunspiderTime != null ? !sunspiderTime.equals(that.sunspiderTime) : that.sunspiderTime != null)
            return false;
        if (pluginsJsHashed != null ? !pluginsJsHashed.equals(that.pluginsJsHashed) : that.pluginsJsHashed != null)
            return false;
        if (canvasJsHashed != null ? !canvasJsHashed.equals(that.canvasJsHashed) : that.canvasJsHashed != null)
            return false;
        if (webGlJsHashed != null ? !webGlJsHashed.equals(that.webGlJsHashed) : that.webGlJsHashed != null)
            return false;
        if (fontsFlashHashed != null ? !fontsFlashHashed.equals(that.fontsFlashHashed) : that.fontsFlashHashed != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = counter;
        result = 31 * result + (id != null ? id.hashCode() : 0);
        result = 31 * result + (addressHttp != null ? addressHttp.hashCode() : 0);
        result = 31 * result + (creationDate != null ? creationDate.hashCode() : 0);
        result = 31 * result + (updateDate != null ? updateDate.hashCode() : 0);
        result = 31 * result + (endDate != null ? endDate.hashCode() : 0);
        result = 31 * result + (userAgentHttp != null ? userAgentHttp.hashCode() : 0);
        result = 31 * result + (acceptHttp != null ? acceptHttp.hashCode() : 0);
        result = 31 * result + (hostHttp != null ? hostHttp.hashCode() : 0);
        result = 31 * result + (connectionHttp != null ? connectionHttp.hashCode() : 0);
        result = 31 * result + (encodingHttp != null ? encodingHttp.hashCode() : 0);
        result = 31 * result + (languageHttp != null ? languageHttp.hashCode() : 0);
        result = 31 * result + (orderHttp != null ? orderHttp.hashCode() : 0);
        result = 31 * result + (pluginsJs != null ? pluginsJs.hashCode() : 0);
        result = 31 * result + (platformJs != null ? platformJs.hashCode() : 0);
        result = 31 * result + (cookiesJs != null ? cookiesJs.hashCode() : 0);
        result = 31 * result + (dntJs != null ? dntJs.hashCode() : 0);
        result = 31 * result + (timezoneJs != null ? timezoneJs.hashCode() : 0);
        result = 31 * result + (resolutionJs != null ? resolutionJs.hashCode() : 0);
        result = 31 * result + (localJs != null ? localJs.hashCode() : 0);
        result = 31 * result + (sessionJs != null ? sessionJs.hashCode() : 0);
        result = 31 * result + (ieDataJs != null ? ieDataJs.hashCode() : 0);
        result = 31 * result + (canvasJs != null ? canvasJs.hashCode() : 0);
        result = 31 * result + (webGlJs != null ? webGlJs.hashCode() : 0);
        result = 31 * result + (fontsFlash != null ? fontsFlash.hashCode() : 0);
        result = 31 * result + (resolutionFlash != null ? resolutionFlash.hashCode() : 0);
        result = 31 * result + (languageFlash != null ? languageFlash.hashCode() : 0);
        result = 31 * result + (platformFlash != null ? platformFlash.hashCode() : 0);
        result = 31 * result + (adBlock != null ? adBlock.hashCode() : 0);
        result = 31 * result + (vendorWebGljs != null ? vendorWebGljs.hashCode() : 0);
        result = 31 * result + (rendererWebGljs != null ? rendererWebGljs.hashCode() : 0);
        result = 31 * result + (octaneScore != null ? octaneScore.hashCode() : 0);
        result = 31 * result + (sunspiderTime != null ? sunspiderTime.hashCode() : 0);
        result = 31 * result + (pluginsJsHashed != null ? pluginsJsHashed.hashCode() : 0);
        result = 31 * result + (canvasJsHashed != null ? canvasJsHashed.hashCode() : 0);
        result = 31 * result + (webGlJsHashed != null ? webGlJsHashed.hashCode() : 0);
        result = 31 * result + (fontsFlashHashed != null ? fontsFlashHashed.hashCode() : 0);
        return result;
    }
}
