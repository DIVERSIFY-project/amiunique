package models;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.HashMap;

@Entity
@Table(name = "fpData", schema = "", catalog = "fingerprint")
public class FpDataEntity implements Comparable, Cloneable {
    private int counter;
    private String id;
    private String addressHttp;
    private Timestamp time;
    private String userAgentHttp;
    private String acceptHttp;
    private String hostHttp;
    private String connectionHttp;
    private String encodingHttp;
    private String languageHttp;
    private String orderHttp;
    private String pluginsJs;
    private String platformJs;
    private String cookiesJs;
    private String dntJs;
    private String timezoneJs;
    private String resolutionJs;
    private String localJs;
    private String sessionJs;
    private String ieDataJs;
    private String canvasJs;
    private String fontsFlash;
    private String resolutionFlash;
    private String languageFlash;
    private String platformFlash;
    private String adBlock;
    private String octaneScore;
    private String sunspiderTime;
    private String webGlJs;
    private String vendorWebGljs;
    private String rendererWebGljs;

    public FpDataEntity() {
    }

    @Id
    @Column(name = "counter")
    @GeneratedValue
    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

    @Basic
    @Column(name = "id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "addressHttp")
    public String getAddressHttp() {
        return addressHttp;
    }

    public void setAddressHttp(String addressHttp) {
        this.addressHttp = addressHttp;
    }

    @Basic
    @Column(name = "time")
    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    @Basic
    @Column(name = "userAgentHttp")
    public String getUserAgentHttp() {
        return userAgentHttp;
    }

    public void setUserAgentHttp(String userAgentHttp) {
        this.userAgentHttp = userAgentHttp;
    }

    @Basic
    @Column(name = "acceptHttp")
    public String getAcceptHttp() {
        return acceptHttp;
    }

    public void setAcceptHttp(String acceptHttp) {
        this.acceptHttp = acceptHttp;
    }

    @Basic
    @Column(name = "hostHttp")
    public String getHostHttp() {
        return hostHttp;
    }

    public void setHostHttp(String hostHttp) {
        this.hostHttp = hostHttp;
    }

    @Basic
    @Column(name = "connectionHttp")
    public String getConnectionHttp() {
        return connectionHttp;
    }

    public void setConnectionHttp(String connectionHttp) {
        this.connectionHttp = connectionHttp;
    }

    @Basic
    @Column(name = "encodingHttp")
    public String getEncodingHttp() {
        return encodingHttp;
    }

    public void setEncodingHttp(String encodingHttp) {
        this.encodingHttp = encodingHttp;
    }

    @Basic
    @Column(name = "languageHttp")
    public String getLanguageHttp() {
        return languageHttp;
    }

    public void setLanguageHttp(String languageHttp) {
        this.languageHttp = languageHttp;
    }

    @Basic
    @Column(name = "orderHttp")
    public String getOrderHttp() {
        return orderHttp;
    }

    public void setOrderHttp(String orderHttp) {
        this.orderHttp = orderHttp;
    }

    @Basic
    @Column(name = "pluginsJS")
    public String getPluginsJs() {
        return pluginsJs;
    }

    public void setPluginsJs(String pluginsJs) {
        this.pluginsJs = pluginsJs;
    }

    @Basic
    @Column(name = "platformJS")
    public String getPlatformJs() {
        return platformJs;
    }

    public void setPlatformJs(String platformJs) {
        this.platformJs = platformJs;
    }

    @Basic
    @Column(name = "cookiesJS")
    public String getCookiesJs() {
        return cookiesJs;
    }

    public void setCookiesJs(String cookiesJs) {
        this.cookiesJs = cookiesJs;
    }

    @Basic
    @Column(name = "dntJS")
    public String getDntJs() {
        return dntJs;
    }

    public void setDntJs(String dntJs) {
        this.dntJs = dntJs;
    }

    @Basic
    @Column(name = "timezoneJS")
    public String getTimezoneJs() {
        return timezoneJs;
    }

    public void setTimezoneJs(String timezoneJs) {
        this.timezoneJs = timezoneJs;
    }

    @Basic
    @Column(name = "resolutionJS")
    public String getResolutionJs() {
        return resolutionJs;
    }

    public void setResolutionJs(String resolutionJs) {
        this.resolutionJs = resolutionJs;
    }

    @Basic
    @Column(name = "localJS")
    public String getLocalJs() {
        return localJs;
    }

    public void setLocalJs(String localJs) {
        this.localJs = localJs;
    }

    @Basic
    @Column(name = "sessionJS")
    public String getSessionJs() {
        return sessionJs;
    }

    public void setSessionJs(String sessionJs) {
        this.sessionJs = sessionJs;
    }

    @Basic
    @Column(name = "IEDataJS")
    public String getIeDataJs() {
        return ieDataJs;
    }

    public void setIeDataJs(String ieDataJs) {
        this.ieDataJs = ieDataJs;
    }

    @Basic
    @Column(name = "canvasJS")
    public String getCanvasJs() {
        return canvasJs;
    }

    public void setCanvasJs(String canvasJs) {
        this.canvasJs = canvasJs;
    }

    @Basic
    @Column(name = "fontsFlash")
    public String getFontsFlash() {
        return fontsFlash;
    }

    public void setFontsFlash(String fontsFlash) {
        this.fontsFlash = fontsFlash;
    }

    @Basic
    @Column(name = "resolutionFlash")
    public String getResolutionFlash() {
        return resolutionFlash;
    }

    public void setResolutionFlash(String resolutionFlash) {
        this.resolutionFlash = resolutionFlash;
    }

    @Basic
    @Column(name = "languageFlash")
    public String getLanguageFlash() {
        return languageFlash;
    }

    public void setLanguageFlash(String languageFlash) {
        this.languageFlash = languageFlash;
    }

    @Basic
    @Column(name = "platformFlash")
    public String getPlatformFlash() {
        return platformFlash;
    }

    public void setPlatformFlash(String platformFlash) {
        this.platformFlash = platformFlash;
    }

    @Basic
    @Column(name = "adBlock")
    public String getAdBlock() {
        return adBlock;
    }

    public void setAdBlock(String adBlock) {
        this.adBlock = adBlock;
    }

    @Basic
    @Column(name = "octaneScore")
    public String getOctaneScore() {
        return octaneScore;
    }

    public void setOctaneScore(String octaneScore) {
        this.octaneScore = octaneScore;
    }

    @Basic
    @Column(name = "sunspiderTime")
    public String getSunspiderTime() {
        return sunspiderTime;
    }

    public void setSunspiderTime(String sunspiderTime) {
        this.sunspiderTime = sunspiderTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;

        FpDataEntity that = (FpDataEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (counter != that.counter) return false;
        if (acceptHttp != null ? !acceptHttp.equals(that.acceptHttp) : that.acceptHttp != null) return false;
        if (adBlock != null ? !adBlock.equals(that.adBlock) : that.adBlock != null) return false;
        if (addressHttp != null ? !addressHttp.equals(that.addressHttp) : that.addressHttp != null) return false;
        if (canvasJs != null ? !canvasJs.equals(that.canvasJs) : that.canvasJs != null) return false;
        if (connectionHttp != null ? !connectionHttp.equals(that.connectionHttp) : that.connectionHttp != null)
            return false;
        if (cookiesJs != null ? !cookiesJs.equals(that.cookiesJs) : that.cookiesJs != null) return false;
        if (dntJs != null ? !dntJs.equals(that.dntJs) : that.dntJs != null) return false;
        if (encodingHttp != null ? !encodingHttp.equals(that.encodingHttp) : that.encodingHttp != null) return false;
        if (fontsFlash != null ? !fontsFlash.equals(that.fontsFlash) : that.fontsFlash != null) return false;
        if (hostHttp != null ? !hostHttp.equals(that.hostHttp) : that.hostHttp != null) return false;
        if (ieDataJs != null ? !ieDataJs.equals(that.ieDataJs) : that.ieDataJs != null) return false;
        if (languageFlash != null ? !languageFlash.equals(that.languageFlash) : that.languageFlash != null)
            return false;
        if (languageHttp != null ? !languageHttp.equals(that.languageHttp) : that.languageHttp != null) return false;
        if (localJs != null ? !localJs.equals(that.localJs) : that.localJs != null) return false;
        if (octaneScore != null ? !octaneScore.equals(that.octaneScore) : that.octaneScore != null) return false;
        if (orderHttp != null ? !orderHttp.equals(that.orderHttp) : that.orderHttp != null) return false;
        if (platformFlash != null ? !platformFlash.equals(that.platformFlash) : that.platformFlash != null)
            return false;
        if (platformJs != null ? !platformJs.equals(that.platformJs) : that.platformJs != null) return false;
        if (pluginsJs != null ? !pluginsJs.equals(that.pluginsJs) : that.pluginsJs != null) return false;
        if (resolutionFlash != null ? !resolutionFlash.equals(that.resolutionFlash) : that.resolutionFlash != null)
            return false;
        if (resolutionJs != null ? !resolutionJs.equals(that.resolutionJs) : that.resolutionJs != null) return false;
        if (sessionJs != null ? !sessionJs.equals(that.sessionJs) : that.sessionJs != null) return false;
        if (sunspiderTime != null ? !sunspiderTime.equals(that.sunspiderTime) : that.sunspiderTime != null)
            return false;
        if (time != null ? !time.equals(that.time) : that.time != null) return false;
        if (timezoneJs != null ? !timezoneJs.equals(that.timezoneJs) : that.timezoneJs != null) return false;
        if (userAgentHttp != null ? !userAgentHttp.equals(that.userAgentHttp) : that.userAgentHttp != null)
            return false;
        if (webGlJs != null ? !webGlJs.equals(that.webGlJs) : that.webGlJs != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = counter;
        result = 31 * result + (id != null ? id.hashCode() : 0);
        result = 31 * result + (addressHttp != null ? addressHttp.hashCode() : 0);
        result = 31 * result + (time != null ? time.hashCode() : 0);
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
        result = 31 * result + (octaneScore != null ? octaneScore.hashCode() : 0);
        result = 31 * result + (sunspiderTime != null ? sunspiderTime.hashCode() : 0);
        return result;
    }

    public HashMap<String, String> fpToHashMap(){
        HashMap<String, String> fpHashMap = new HashMap<String, String>();

        fpHashMap.put("id",this.getId());
        fpHashMap.put("addressHttp",this.getAddressHttp());
        fpHashMap.put("userAgentHttp",this.getUserAgentHttp());
        fpHashMap.put("acceptHttp",this.getAcceptHttp());
        fpHashMap.put("hostHttp",this.getHostHttp());
        fpHashMap.put("connectionHttp",this.getConnectionHttp());
        fpHashMap.put("encodingHttp",this.getEncodingHttp());
        fpHashMap.put("languageHttp",this.getLanguageHttp());
        fpHashMap.put("orderHttp",this.getOrderHttp());
        fpHashMap.put("pluginsJs",this.getPluginsJs());
        fpHashMap.put("platformJs",this.getPlatformJs());
        fpHashMap.put("cookiesJs",this.getCookiesJs());
        fpHashMap.put("dntJs",this.getDntJs());
        fpHashMap.put("timezoneJs",this.getTimezoneJs());
        fpHashMap.put("resolutionJs",this.getResolutionJs());
        fpHashMap.put("localJs",this.getLocalJs());
        fpHashMap.put("sessionJs",this.getSessionJs());
        fpHashMap.put("ieDataJs",this.getIeDataJs());
        fpHashMap.put("canvasJs",this.getCanvasJs());
        fpHashMap.put("webGlJs",this.getWebGlJs());
        fpHashMap.put("fontsFlash",this.getFontsFlash());
        fpHashMap.put("resolutionFlash",this.getResolutionFlash());
        fpHashMap.put("languageFlash",this.getLanguageFlash());
        fpHashMap.put("platformFlash",this.getPlatformFlash());
        fpHashMap.put("adBlock",this.getAdBlock());
        fpHashMap.put("octaneScore",this.getOctaneScore());
        fpHashMap.put("sunspiderTime",this.getSunspiderTime());

        return fpHashMap;
    }

    @Basic
    @Column(name = "webGLJs")
    public String getWebGlJs() {
        return webGlJs;
    }

    public void setWebGlJs(String webGlJs) {
        this.webGlJs = webGlJs;
    }

    @Basic
    @Column(name = "vendorWebGLJS")
    public String getVendorWebGljs() {
        return vendorWebGljs;
    }

    public void setVendorWebGljs(String vendorWebGljs) {
        this.vendorWebGljs = vendorWebGljs;
    }

    @Basic
    @Column(name = "rendererWebGLJS")
    public String getRendererWebGljs() {
        return rendererWebGljs;
    }

    public void setRendererWebGljs(String rendererWebGljs) {
        this.rendererWebGljs = rendererWebGljs;
    }

    public int compareTo(Object o){
        if (this == o) return 0;
        if (o == null) return -1;

        FpDataEntity that = (FpDataEntity) o;
        return that.getCounter() - getCounter();

    }

    public Object clone() {
        Object o = null;
        try {
            o = super.clone();
        } catch(CloneNotSupportedException cnse) {
            cnse.printStackTrace(System.err);
        }
        
        return o;
    }
}
