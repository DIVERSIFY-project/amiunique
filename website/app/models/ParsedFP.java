package models;


import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParsedFP {

    private String browser;
    private String browserVersion;
    private String os;
    private String osVersion;
    private String language;
    private String timezone;
    private String nbFonts;

    /* Pattern */
    private static Pattern langP = Pattern.compile("^(\\S\\S)");
    private static Pattern fireP = Pattern.compile("Firefox/(\\d*\\.\\d*)");
    private static Pattern chromeP = Pattern.compile("Chrome/(\\d*\\.\\d*)");
    private static Pattern safariP = Pattern.compile("Version/(\\d*\\.\\d*) Safari");
    private static Pattern IEP = Pattern.compile("MSIE (\\d*\\.\\d*)");
    private static Pattern operaP1 = Pattern.compile("Opera.*?Version/(\\d*\\.\\d*)");
    private static Pattern operaP2 = Pattern.compile("OPR/(\\d*\\.\\d*)");
    private static Pattern winP = Pattern.compile("Windows NT (\\d*\\.\\d*)");
    private static Pattern macP = Pattern.compile("Mac OS X (10[\\._]\\d*)");
    private static Pattern androidP = Pattern.compile("Android (\\d*\\.\\d*)");
    private static Pattern iosP= Pattern.compile("OS (\\d*[\\._]\\d*)");
    private static Pattern winPhoneP = Pattern.compile("Windows Phone (\\d*\\.\\d*)");
    private static final Map<String, String> windowsMap;
    static
    {
        windowsMap = new HashMap<String, String>();
        windowsMap.put("5.0","2000");
        windowsMap.put("5.1","XP");
        windowsMap.put("5.2","Server 2003");
        windowsMap.put("6.0","Vista");
        windowsMap.put("6.1","7");
        windowsMap.put("6.2","8");
        windowsMap.put("6.3","8.1");
        windowsMap.put("6.4","10");
        windowsMap.put("10.0","10");
    }

    public ParsedFP(String ua) {

        if (ua == null){
            this.browser = "Others";
            this.browserVersion = "Unknown";
            this.os = "Others";
            this.osVersion = "Desktop";
        } else {
            /* Browser */
            Matcher fireM = fireP.matcher(ua);
            Matcher chromeM = chromeP.matcher(ua);
            Matcher safariM = safariP.matcher(ua);
            Matcher IEM = IEP.matcher(ua);
            Matcher operaM1 = operaP1.matcher(ua);
            Matcher operaM2 = operaP2.matcher(ua);

            if (fireM.find()) {//Firefox
                String ver = fireM.group(1);
                this.browser = "Firefox";
                this.browserVersion = ver;
            } else if (operaM1.find()) {//Opera (old)
                String ver = operaM1.group(1);
                this.browser = "Opera";
                this.browserVersion = ver;
            } else if (operaM2.find()) {//Opera (new)
                String ver = operaM2.group(1);
                this.browser = "Opera";
                this.browserVersion = ver;
            } else if (chromeM.find()) {//Chrome
                String ver = chromeM.group(1);
                this.browser = "Chrome";
                this.browserVersion = ver;
            } else if (safariM.find()) {//Safari
                String ver = safariM.group(1);
                this.browser = "Safari";
                this.browserVersion = ver;
            } else if (IEM.find()) {//IE8/9/10
                String ver = IEM.group(1);
                this.browser = "IE";
                this.browserVersion = ver;
            } else if (ua.contains("Trident/7.0; rv:11.0")) {//IE11
                this.browser = "IE";
                this.browserVersion = "11.0";
            } else {//Others
                this.browser = "Others";
                this.browserVersion = "Unknown";
            }

            /* OS */
            if (ua.contains("Mobile")) {
                Matcher androidM = androidP.matcher(ua);
                Matcher iosM = iosP.matcher(ua);
                Matcher winPhoneM = winPhoneP.matcher(ua);

                if (androidM.find()) {
                    String ver = androidM.group(1);
                    this.os = "Android";
                    this.osVersion = ver;
                } else if (ua.contains("Android")) {
                    this.os = "Android";
                    this.osVersion = "Unknown";
                } else if (iosM.find()) {
                    String ver = iosM.group(1);
                    this.os = "iOS";
                    this.osVersion = ver.replace("_", ".");
                } else if (winPhoneM.find()) {
                    String ver = winPhoneM.group(1);
                    this.os = "Windows Phone";
                    this.osVersion = ver;
                } else {
                    this.os = "Others";
                    this.osVersion = "Mobile";
                }

            } else {
                Matcher winM = winP.matcher(ua);
                Matcher macM = macP.matcher(ua);
                if (winM.find()) {//Windows
                    String ver = winM.group(1);
                    this.os = "Windows";
                    this.osVersion = windowsMap.get(ver);
                } else if (macM.find()) {//Mac
                    String ver = macM.group(1);
                    this.os = "Mac";
                    this.osVersion = ver.replace("_", ".");
                } else if (ua.contains("Linux")) {//Linux
                    this.os = "Linux";
                    if (ua.contains("Ubuntu") || ua.contains("U;")) {
                        this.osVersion = "Ubuntu";
                    } else {
                        this.osVersion = "Other distros";
                    }
                } else {//Others
                    this.os = "Others";
                    if (ua.contains("bot")) {
                        this.osVersion = "Bot";
                    } else {
                        this.osVersion = "Desktop";
                    }
                }
            }
        }
    }

    public String getBrowser() {
        return browser;
    }

    public String getBrowserVersion() {
        return browserVersion;
    }

    public String getOs() {
        return os;
    }

    public String getOsVersion() {
        return osVersion;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String languageHttp) {
        if(languageHttp == null){
            this.language = "Not communicated";
        } else {
            Matcher langM = langP.matcher(languageHttp);
            if (langM.find()) {
                this.language = langM.group(1);
            } else {
                this.language = "Not communicated";
            }
        }
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        if(timezone == null){
            this.timezone = "Not specified";
        } else {
            this.timezone = timezone.replace("\"", "");
        }
    }

    public String getNbFonts() {
        return nbFonts;
    }

    public void setNbFonts(String fonts) {
        if(fonts == null){
            this.nbFonts = "NC";
        } else {
            Integer nbFonts = fonts.split("_").length;
            if (nbFonts > 2) {
                int step = 50;
                int j = step;
                while (j < nbFonts) {
                    j += step;
                }
                this.nbFonts = (j - step) + "-" + j;
            } else {
                this.nbFonts = "NC";
            }
        }

    }
}
