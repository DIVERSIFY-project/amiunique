$(document).ready(function(){

    var fl = document.getElementById("OSData");

    setTimeout(function() {
        if((plugins.indexOf("flash") > -1) || (plugins.indexOf("Flash") > -1)){
            var fl = document.getElementById("OSData");
            if(fl == null){
                fontsFlash = resolutionFlash = languageFlash = platformFlash = "Flash detected but blocked by an extension";
            } else if((typeof fl.getOS != "undefined") || (typeof flashAvailable == "boolean")) {
                fontsFlash = fl.getFonts().join().replace(/[&\/\\#,+()$~%.'":*?<>{}]/g, '_');
                resolutionFlash = fl.getResolution().join("x");
                languageFlash = fl.getLanguage();
                platformFlash = fl.getOS();
            } else {
                fontsFlash = resolutionFlash = languageFlash = platformFlash = "Flash detected but not activated (click-to-play)";
            }
        } else {
            fontsFlash = resolutionFlash = languageFlash = platformFlash = "Flash not detected";
        }

        var fp ={};

        fp.userAgentHttp = userAgentHttp;
        fp.acceptHttp = acceptHttp;
        fp.hostHttp = hostHttp;
        fp.connectionHttp = connectionHttp;
        fp.encodingHttp = encodingHttp;
        fp.languageHttp = languageHttp;
        fp.orderHttp = orderHttp;
        fp.pluginsJs =  plugins.replace(/[&\/\\#,+()$~%'"*?<>{}]/g,'');
        fp.platformJs = platform;
        fp.cookiesJs = cookieEnabled;
        fp.dntJs = doNotTrack;
        fp.timezoneJs = timezone;
        fp.resolutionJs = resolution;
        fp.localJs = domLocalStorage;
        fp.sessionJs = domSessionStorage;
        fp.IEDataJs =  ieUserData;
        fp.canvasJs = canvasData;
        fp.webGLJs = webGLData;
        fp.vendorWebGLJs = webGLVendor;
        fp.rendererWebGLJs = webGLRenderer;
        fp.fontsFlash = fontsFlash;
        fp.resolutionFlash = resolutionFlash;
        fp.languageFlash = languageFlash;
        fp.platformFlash = platformFlash;
        fp.adBlock = document.getElementById('ads')? 'no' : 'yes';

        // New attributes
        fp.hardwareConcurrency = hardwareConcurrency;
        fp.availableScreenResolution = availableScreenResolution;
        fp.cpuClass = cpuClass;
        fp.modernizr = modernizr;
        fp.overwrittenObjects = overwrittenObjects;
        fp.appCodeName = appCodeName;
        fp.oscpu = oscpu;
        fp.appName = appName;
        fp.appVersion = appVersion;
        fp.languages = languages;
        fp.mimeTypes = mimeTypes;
        fp.pluginsUsingMimeTypes = pluginsUsingMimeTypes;
        fp.product = product;
        fp.productSub = productSub;
        fp.vendor = vendor;
        fp.vendorSub = vendorSub;
        fp.touchSupport = touchSupport;
        fp.buildID = buildID;
        fp.navigatorPrototype = navigatorPrototype;
        fp.mathsConstants = mathsConstants; 
        fp.resOverflow = resOverflow;
        fp.errorsGenerated = errorsGenerated;

        return Promise.all([p1, p2, p3, p4]).then(function () {
            fp.audio = audio.join(";;;");
            fp.unknownImageError = unknownImageError;
            fp.fontsEnum = fontsEnum;
            fp.osMediaqueries = osMediaqueries;
            $.ajax({
                url: 'https://amiunique.org/evolution/'+document.location.hash.substring(1),
                // url: 'http://localhost:9000/evolution/dsd25sdfsd', // Needs to add fake param for testing
                data: fp,
                contentType: 'application/x-www-form-urlencoded',
                method: "POST"
            });
        }); 
    },2000);

});