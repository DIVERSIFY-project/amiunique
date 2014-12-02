var platform = window.navigator.platform;
var cookieEnabled = window.navigator.cookieEnabled? "yes" : "no";
var doNotTrack = "";
if (window.navigator.doNotTrack != null && window.navigator.doNotTrack != "unspecified"){
    if(window.navigator.doNotTrack == "1" || window.navigator.doNotTrack == "yes"){
        doNotTrack = "yes";
    } else {
        doNotTrack = "no";
    }
} else {
	doNotTrack = "NC";
}

var timezone = new Date().getTimezoneOffset();
var resolution = window.screen.width+"x"+window.screen.height+"x"+window.screen.colorDepth;

//Enumeration of navigator.plugins or use of Plugin detect
var plugins = "";
if(PluginDetect.browser.isIE){
    var nbPlugins = 1;
    var pluginsList = ["QuickTime", "Java", "DevalVR", "Flash", "Shockwave",
        "WindowsMediaPlayer", "Silverlight", "VLC", "AdobeReader", "PDFReader",
        "RealPlayer", "PDFjs"];
    PluginDetect.getVersion(".");
    for (i = 0; i < pluginsList.length; i++) {
        var ver = PluginDetect.getVersion(pluginsList[i]);
        if(ver != null){
            plugins+="Plugin "+nbPlugins+": "+pluginsList[i]+" "+ver+"; ";
            nbPlugins++;
        }
    }
} else {
    var np = window.navigator.plugins;
    var plist = new Array();
    for (var i = 0; i < np.length; i++) {
        plist[i] = np[i].name + "; ";
        plist[i] += np[i].description + "; ";
        plist[i] += np[i].filename;
        plist[i] += ". ";
    }
    plist.sort();
    for (i = 0; i < np.length; i++)
        plugins+= "Plugin "+i+": " + plist[i];
}

try {
	ieUserData = "";
    oPersistDiv.setAttribute("testStorage", "value remembered");
    oPersistDiv.save("oXMLStore");
    oPersistDiv.setAttribute("testStorage", "overwritten!");
    oPersistDiv.load("oXMLStore");
	if ("value remembered" == (oPersistDiv.getAttribute("testStorage"))) {
      ieUserData= "yes";
	} else { 
     ieUserData = "no";
	}
} catch (ex) {
     ieUserData = "no";
 }

try { 
    localStorage.fp = "test";
    sessionStorage.fp = "test";
} catch (ex) { 
}

try {
	domLocalStorage = "";
    if (localStorage.fp == "test") {
       domLocalStorage = "yes";
    } else {
       domLocalStorage = "no";
    }
} catch (ex) { 
	domLocalStorage = "no"; 
}

try {
	domSessionStorage = "";
    if (sessionStorage.fp == "test") {
       domSessionStorage = "yes";
    } else {
       domSessionStorage = "no";
    }
} catch (ex) { 
	domSessionStorage = "no"; 
}

