package models;

import java.util.HashMap;

public class Stats{

    private Integer nbTotal;
    private CounterMap timezone;
    private HashMap<String, VersionMap> browsers;
    private HashMap<String, VersionMap> os;
    private VersionMap languages;
    private RangeMap nbFonts;

    public Integer getNbTotal() {
        return nbTotal;
    }

    public CounterMap getTimezone() {
        return timezone;
    }

    public HashMap<String, VersionMap> getBrowsers() {
        return browsers;
    }

    public HashMap<String, VersionMap> getOs() {
        return os;
    }

    public VersionMap getLanguages() {
        return languages;
    }

    public RangeMap getNbFonts() {
        return nbFonts;
    }

    private Stats(){
        FpDataEntityManager em = new FpDataEntityManager();
        this.nbTotal = em.getNumberOfEntries();
        this.timezone = em.getTimezoneStats();
        HashMap<String,HashMap<String, VersionMap>> resMap = em.getOSBrowserStats();
        this.browsers = resMap.get("browsers");
        this.os = resMap.get("os");
        this.languages = em.getLanguageStats();
        this.nbFonts = em.getFontsStats();
    }

    private static Stats INSTANCE = new Stats();

    public static Stats getInstance(){
        return INSTANCE;
    }

    public void addFingerprint(ParsedFP fp){
        nbTotal += 1;
        timezone.add(fp.getTimezone());
        browsers.get(fp.getBrowser()).add(fp.getBrowserVersion());
        os.get(fp.getOs()).add(fp.getOsVersion());
        languages.add(fp.getLanguage());
        if(!fp.getNbFonts().equals("NC")) nbFonts.add(fp.getNbFonts());
    }


}
