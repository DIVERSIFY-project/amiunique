var UNKNOWN = "unknown";
var ERROR = "error";

var hardwareConcurrency = getHardwareConcurrency();
var availableScreenResolution = getAvailableScreenResolution();
var cpuClass = getNavigatorCpuClass();
var modernizr = testModernizr();
var overwrittenObjects = testOverwrittenObjects();
var appCodeName = getAppCodeName();
var oscpu = getOscpu();
var appName = getAppName();
var appVersion = getAppVersion();
var languages = getLanguages();
var mimeTypes = getMimeTypes();
var pluginsUsingMimeTypes = getPluginsUsingMimeTypes();
var product = getProduct();
var productSub = getProductSub();
var vendor = getVendor();
var vendorSub = getVendorSub();
var touchSupport = getTouchSupport();
var buildID = getBuildId();
var navigatorPrototype = getNavigatorPrototype();
var mathsConstants = getMathsConstants(); 
var resOverflow = generateStackOverflow();
var errorsGenerated = generateErrors();

// Async part
//Unknown image
var unknownImageError = "";
var p1 = new Promise(function(resolve, reject){
    generateUnknownImageError().then(function(val){
    unknownImageError = val;
        return resolve();
    });
});

var fontsEnum = "";
var p2 = new Promise(function(resolve, reject){
    getFontsEnum().then(function(val){
        fontsEnum = val;
        return resolve();
    })
});

var audio = "";
var p3 = new Promise(function(resolve, reject){
    getAudio().then(function(val){
        audio = val.data;
        return resolve();
    });
});

var osMediaqueries = "";
var p4 = new Promise(function(resolve, reject){
    getOSMq().then(function(val){
        osMediaqueries = val;
        return resolve();
    });
});

function getHardwareConcurrency(){
    if(navigator.hardwareConcurrency){
	    return navigator.hardwareConcurrency;
	}
	return UNKNOWN;
}

function getAvailableScreenResolution(){
	if(screen.availWidth && screen.availHeight) {
		return screen.availWidth+","+screen.availHeight;
	}
	return UNKNOWN;
}

function getNavigatorCpuClass(){
	if(navigator.cpuClass){
		return navigator.cpuClass;
	}
	return UNKNOWN;
}

function testModernizr(){
    var propertiesVec = [];
    try{
        var modernizrProperties = Object.getOwnPropertyNames(Modernizr);
        modernizrProperties.forEach(function(prop){
            if(typeof Modernizr[prop] == "boolean"){
                propertiesVec.push(prop+"--"+Modernizr[prop].toString());
            }
        });
        return propertiesVec.join(";;");
    } catch(e){
        return UNKNOWN;
    }
}

function getOscpu(){
	if(navigator.oscpu){
		return navigator.oscpu;
	}
	return UNKNOWN;
}

function testOverwrittenObjects(){
    var screenTest;
    try{
        screenTest = Object.getOwnPropertyDescriptor(Object.getPrototypeOf(screen), "width").get.toString();
    } catch(e){
        screenTest = ERROR;
    }
    var canvasTest;
    try{
        canvasTest = Object.getOwnPropertyDescriptor(window.HTMLCanvasElement.prototype, "toDataURL").value.toString();
    } catch(e){
        canvasTest = ERROR;
    }

    var dateTest;
    try{
        dateTest = Object.getOwnPropertyDescriptor(Date.prototype, "getTimezoneOffset").value.toString();
    } catch(e) {
        dateTest = ERROR;
    }

    // We separe with many characters in case they use dash in their overwritten functions
    return screenTest+"~~~"+canvasTest+"~~~"+dateTest;
}

function getOSMq(){
    return new Promise(function(resolve, reject){
        document.addEventListener("DOMContentLoaded", function(event){
            var divTest = document.createElement("div");
            var body = document.getElementsByTagName("body")[0];
            body.appendChild(divTest);
        
            var macP = document.createElement("p");
            macP.setAttribute("id", "testmac1");
            var winxpP = document.createElement("p");
            winxpP.setAttribute("id", "testwinxp"); 
            var winvisP = document.createElement("p");
            winvisP.setAttribute("id", "testwinvis");
            var win7P = document.createElement("p");
            win7P.setAttribute("id", "testwin7");
            var win8P = document.createElement("p");
            win8P.setAttribute("id", "testwin8");

            divTest.appendChild(macP);    
            divTest.appendChild(winxpP);
            divTest.appendChild(winvisP);
            divTest.appendChild(win7P);
            divTest.appendChild(win8P);

            var queryMatchedColor = "red";
            var res = [];

            if(macP.style.color == queryMatchedColor){
                res.push("true");
            }else{
                res.push("false");
            }

            if(winxpP.style.color == queryMatchedColor){
                res.push("true");
            }else{
                res.push("false");
            }

            if(winvisP.style.color == queryMatchedColor){
                res.push("true");
            }else{
                res.push("false");
            }

            if(win7P.style.color == queryMatchedColor){
                res.push("true");
            }else{
                res.push("false");
            }

            if(win8P.style.color == queryMatchedColor){
                res.push("true");
            }else{
                res.push("false");
            }

            return resolve(res.join(";"));
        });
    });
}

function getAppName(){
	return navigator.appName;
}

function getAppCodeName(){
	return navigator.appCodeName;
}

function getAppVersion(){
	return navigator.appVersion;
}

function getLanguages(){
	if(navigator.languages){
		return navigator.languages.join("~~");
	}
	return UNKNOWN;
}

function getMimeTypes(){
	var mimeTypes = [];
	for(var i = 0; i < navigator.mimeTypes.length; i++){
		var mt = navigator.mimeTypes[i];
		mimeTypes.push([mt.description, mt.type, mt.suffixes].join("~~"));
	}
	return mimeTypes.join(";;");
}

function getPluginsUsingMimeTypes(){
	var plugins = [];
	for(var i = 0; i < navigator.mimeTypes.length; i++){
		var mt = navigator.mimeTypes[i];
		plugins.push([mt.enabledPlugin.name, mt.enabledPlugin.description, mt.enabledPlugin.filename].join("::")+mt.type);
	}
	return plugins.join(";;");
}

function getProduct(){
	return navigator.product;	
}

function getProductSub(){
	return navigator.productSub;
}

function getVendor(){
	return navigator.vendor;
}

function getVendorSub(){
	return navigator.vendorSub;
}

function getTouchSupport(){
    var maxTouchPoints = 0;
    var touchEvent = false;
    if(typeof navigator.maxTouchPoints !== "undefined") {
        maxTouchPoints = navigator.maxTouchPoints;
    } else if (typeof navigator.msMaxTouchPoints !== "undefined") {
        maxTouchPoints = navigator.msMaxTouchPoints;
    }
    try {
        document.createEvent("TouchEvent");
        touchEvent = true;
    } catch(_) { /* squelch */ }
    var touchStart = "ontouchstart" in window;
    return [maxTouchPoints, touchEvent, touchStart].join(";");
}

function getBuildId(){
	if(navigator.buildID){
		return navigator.buildID;
	}
	return UNKNOWN;
}

function getNavigatorPrototype(){
    try{
        var obj = window.navigator;
        var protoNavigator = [];
        do Object.getOwnPropertyNames(obj).forEach(function(name) {
            protoNavigator.push(name);
        });
        while(obj = Object.getPrototypeOf(obj));

        var res;
        var finalProto = [];
        protoNavigator.forEach(function(prop){
            var objDesc = Object.getOwnPropertyDescriptor(Object.getPrototypeOf(navigator), prop);
            if(objDesc != undefined){
            if(objDesc.value != undefined){
                res = objDesc.value.toString();
            }else if(objDesc.get != undefined){
                res = objDesc.get.toString();
            }
            }else{
                res = "";
            }
            finalProto.push(prop+"~~~"+res);

        });
        return finalProto.join(";;;");
    } catch(e){
        return UNKNOWN;
    }
}

function getMathsConstants(){
    function asinh(x) {
        if (x === -Infinity) {
            return x;
        } else {
            return Math.log(x + Math.sqrt(x * x + 1));
        }
    }

    function acosh(x) {
        return Math.log(x + Math.sqrt(x * x - 1));
    }

    function atanh(x) {
        return Math.log((1 + x) / (1 - x)) / 2;
    }

    function cbrt(x) {
        var y = Math.pow(Math.abs(x), 1 / 3);
        return x < 0 ? -y : y;
    }

    function cosh(x) {
        var y = Math.exp(x);
        return (y + 1 / y) / 2;
    }

    function expm1(x) {
        return Math.exp(x) - 1;
    }

    function log1p(x) {
        return Math.log(1 + x);
    }

    function sinh(x) {
        var y = Math.exp(x);
        return (y - 1 / y) / 2;
    }

    function tanh(x) {
        if (x === Infinity) {
            return 1;
        } else if (x === -Infinity) {
            return -1;
        } else {
            var y = Math.exp(2 * x);
            return (y - 1) / (y + 1);
        }
    }

    return [
        asinh(1),
        (acosh(1e300) == "Infinity") ? "Infinity" : acosh(1e300),
        atanh(0.5),
        expm1(1),
        cbrt(100),
        log1p(10),
        sinh(1),
        cosh(10),
        tanh(1)
    ].join(";");
}

function generateStackOverflow(){
    var depth = 0;
    var errorMessage;
    var errorName;
    function inc(){
        try{
            depth++;
            inc();
        }catch(e){
        errorMessage = e.message;
        errorName = e.name;
        }
    }

    inc();
	return [depth, errorName, errorMessage].join(";;;");
}

function generateErrors(){
    var errors = [];
    try{
        azeaze + 3;
    }catch(e){
        errors.push(e.message);
        errors.push(e.fileName);
        errors.push(e.lineNumber);
        errors.push(e.description);
        errors.push(e.number);
        errors.push(e.columnNumber);
        try{
            errors.push(e.toSource().toString());
        }catch(e){
            errors.push(undefined);
        }
    }

    try{
		var a = new WebSocket("itsgonnafail");
	}catch(e){
		errors.push(e.toString());
	}

    return errors.join("~~~");
}

function generateUnknownImageError(){
    return new Promise(function(resolve, reject){
        document.addEventListener("DOMContentLoaded", function(event){
            var body = document.getElementsByTagName("body")[0];
            var image = document.createElement("img");
            image.src = "http://iloveponeydotcom32188.jg";
            image.setAttribute("id", "fakeimage");
            body.appendChild(image);
            image = document.getElementById("fakeimage");
                setTimeout(function(){
                        resolve([image.width, image.height].join(";"));
                }, 1000);
            });
    });
}

function getAudio() {
    var audioData = [];

	// Performs fingerprint as found in https://client.a.pxi.pub/PXmssU3ZQ0/main.min.js
	//Sum of buffer values
    var p1 = new Promise(function (resolve, reject) {
        try {
            if (context = new (window.OfflineAudioContext || window.webkitOfflineAudioContext)(1, 44100, 44100), !context) {
                audioData.push(0);
            }
            // Create oscillator
            pxi_oscillator = context.createOscillator();
            pxi_oscillator.type = "triangle";
            pxi_oscillator.frequency.value = 1e4;

            // Create and configure compressor
            pxi_compressor = context.createDynamicsCompressor();
            pxi_compressor.threshold && (pxi_compressor.threshold.value = -50);
            pxi_compressor.knee && (pxi_compressor.knee.value = 40);
            pxi_compressor.ratio && (pxi_compressor.ratio.value = 12);
            pxi_compressor.reduction && (pxi_compressor.reduction.value = -20);
            pxi_compressor.attack && (pxi_compressor.attack.value = 0);
            pxi_compressor.release && (pxi_compressor.release.value = .25);

            // Connect nodes
            pxi_oscillator.connect(pxi_compressor);
            pxi_compressor.connect(context.destination);

            // Start audio processing
            pxi_oscillator.start(0);
            context.startRendering();
            context.oncomplete = function (evnt) {
                try {
                    audioData.push(0);
                    var sha1 = CryptoJS.algo.SHA1.create();
                    for (var i = 0; i < evnt.renderedBuffer.length; i++) {
                        sha1.update(evnt.renderedBuffer.getChannelData(0)[i].toString());
                    }
                    hash = sha1.finalize();
                    audioData.push(hash.toString(CryptoJS.enc.Hex));
                    var tmp = [];
                    for (var i = 4500; 5e3 > i; i++) {
                        tmp.push(Math.abs(evnt.renderedBuffer.getChannelData(0)[i]));
                    }
                    pxi_compressor.disconnect();
                    audioData.push(tmp.join("~"));
                    resolve();
                } catch(u){
                    audioData.push("0");
                    resolve();
                }
            }
        } catch (u) {
            audioData.push(0);
            resolve();
        }
    });

// End PXI fingerprint

// Performs fingerprint as found in some versions of http://metrics.nt.vc/metrics.js
    function a(a, b, c) {
        for (var d in b) "dopplerFactor" === d || "speedOfSound" === d || "currentTime" ===
        d || "number" !== typeof b[d] && "string" !== typeof b[d] || (a[(c ? c : "") + d] = b[d]);
        return a
    }

    var p2 = new Promise(function (resolve, reject) {
        try {
            var nt_vc_context = window.AudioContext || window.webkitAudioContext;
            if ("function" !== typeof nt_vc_context) audioData.push("Not available");
            else {
                var f = new nt_vc_context,
                    d = f.createAnalyser();
                var tmp = a({}, f, "ac-");
                tmp = a(tmp, f.destination, "ac-");
                tmp = a(tmp, f.listener, "ac-");
                var res = a(tmp, d, "an-");
                var arr = [], i;
                for(i in res){ 
                    if(res.hasOwnProperty(i)){
                        arr.push(res[i]);
                    }
                }
                arr.sort(function(x,b){ return x[0]>b[0]?1:-1; }) 
                audioData.push(arr.join("~"));
            }
        } catch (g) {
            audioData.push(0)
        }
        resolve();
    });

// Performs fingerprint as found in https://www.cdn-net.com/cc.js
    var cc_output = [];

    var p3 = new Promise(function (resolve, reject) {
        var audioCtx = new (window.AudioContext || window.webkitAudioContext),
            oscillator = audioCtx.createOscillator(),
            analyser = audioCtx.createAnalyser(),
            gain = audioCtx.createGain(),
            scriptProcessor = audioCtx.createScriptProcessor(4096, 1, 1);


        gain.gain.value = 0; // Disable volume
        oscillator.type = "triangle"; // Set oscillator to output triangle wave
        oscillator.connect(analyser); // Connect oscillator output to analyser input
        analyser.connect(scriptProcessor); // Connect analyser output to scriptProcessor input
        scriptProcessor.connect(gain); // Connect scriptProcessor output to gain input
        gain.connect(audioCtx.destination); // Connect gain output to audiocontext destination

        scriptProcessor.onaudioprocess = function (bins) {
            bins = new Float32Array(analyser.frequencyBinCount);
            analyser.getFloatFrequencyData(bins);
            for (var i = 0; i < bins.length; i = i + 1) {
                cc_output.push(bins[i]);
            }
            analyser.disconnect();
            scriptProcessor.disconnect();
            gain.disconnect();
            audioData.push(cc_output.slice(0, 30).join("~"));
            resolve();
        };

        oscillator.start(0);
    });

// Performs a hybrid of cc/pxi methods found above
    var hybrid_output = [];

    var p4 = new Promise(function (resolve, reject) {
        var audioCtx = new (window.AudioContext || window.webkitAudioContext),
            oscillator = audioCtx.createOscillator(),
            analyser = audioCtx.createAnalyser(),
            gain = audioCtx.createGain(),
            scriptProcessor = audioCtx.createScriptProcessor(4096, 1, 1);

        // Create and configure compressor
        compressor = audioCtx.createDynamicsCompressor();
        compressor.threshold && (compressor.threshold.value = -50);
        compressor.knee && (compressor.knee.value = 40);
        compressor.ratio && (compressor.ratio.value = 12);
        compressor.reduction && (compressor.reduction.value = -20);
        compressor.attack && (compressor.attack.value = 0);
        compressor.release && (compressor.release.value = .25);

        gain.gain.value = 0; // Disable volume
        oscillator.type = "triangle"; // Set oscillator to output triangle wave
        oscillator.connect(compressor); // Connect oscillator output to dynamic compressor
        compressor.connect(analyser); // Connect compressor to analyser
        analyser.connect(scriptProcessor); // Connect analyser output to scriptProcessor input
        scriptProcessor.connect(gain); // Connect scriptProcessor output to gain input
        gain.connect(audioCtx.destination); // Connect gain output to audiocontext destination

        scriptProcessor.onaudioprocess = function (bins) {
            bins = new Float32Array(analyser.frequencyBinCount);
            analyser.getFloatFrequencyData(bins);
            for (var i = 0; i < bins.length; i = i + 1) {
                hybrid_output.push(bins[i]);
            }
            analyser.disconnect();
            scriptProcessor.disconnect();
            gain.disconnect();

            audioData.push(hybrid_output.slice(0, 30).join("~"));
            resolve();
        };

        oscillator.start(0);
    });

    return Promise.all([p1, p2, p3, p4]).then(function () {
            return {name: "audio", data: audioData};
    });
}

function getFontsEnum(){
    // Code taken from fingerprintjs2
    var fontsToTest = ['Arial Narrow Gras', 'NewJumja', 'TSCu', 'Times Italique', 'CL', 'Latin Modern Roman Dunhill', 'Latin Modern Roman Unslanted', 'Helvetica Gras', 'Times New Roman Italique', 'Arial Narrow Gras Italique', 'MotoyaG04Mincho', 'Ume Gothic S4', 'SignPainter', 'Bordeaux Roman Bold LET', 'Ume Gothic O5', 'Latin Modern Mono Light Cond', 'Bell Gothic Std Light', 'pcf', 'boot', 'Latin Modern Mono', 'Arial Gras Italique', 'Police système Moyen', 'Letter Gothic', 'IV50', 'Trebuchet MS Gras Italique', 'Kohinoor Devanagari', 'Times Gras', 'cursor', 'Latin Modern Sans Demi Cond', 'Courier Gras', 'MSung B5HK', 'Police système Intense', 'Police système Gras', 'Courier New Gras Italique', 'Apple Emoji couleur', 'SAPDings', 'Coronet', 'Latin Modern Mono Prop', 'Georgia Gras Italique', 'Times Gras italique', 'Klee', 'Orange LET', 'TAMu', 'Ume Gothic S5', 'Ruach LET', 'Ume P Gothic O5', 'Arial Italique', 'ITF Devanagari', 'Nuosu SIL', 'Wolf', 'Trebuchet MS Gras', 'TG Pagella Math', 'Police système Léger', 'HolidayPi BT', 'Ro', 'Westwood LET', 'Latin Modern Mono Caps', 'Charlie', 'Ume P Gothic S4', 'Yuanti TC', 'Bradley Hand', 'Times New Roman Gras', 'HCR Batang', 'Ume P Mincho', 'Trebuchet MS Italique', 'msam10', 'SchoolHouse Printed A', 'ParkAvenue BT', 'stmary10', 'Earth', 'Tlwg Typewriter', 'Latin Modern Roman Demi', 'W', 'Courier New Italique', 'eufm10', 'Comic Sans MS Gras', 'Lohit Odia', 'Brush Script MT Italique', 'Bodoni 72', 'Arial Black Normal', 'Police système Courant', 'John Handy LET', 'Highlight LET', 'Kievit Offc Pro', 'Verdana Italique', 'AR PL UMing TW', 'Victorian LET', 'Ume Mincho S3', 'Laksaman', 'Ume Mincho', 'Smudger LET', 'Phosphate', 'La Bamba LET', 'Arial Gras', 'Bickham Script Pro Regular', 'Police système Italique', 'SAPIcons', 'Droid Sans Devanagari', 'Clarendon', 'Princetown LET', 'Odessa LET', 'Police système', 'Ume Gothic C4', 'University Roman LET', 'Ki', 'TG Termes Math', 'Latin Modern Roman Slanted', 'Quixley LET', 'Verdana Gras Italique', 'Times New Roman Gras Italique', 'Synchro LET', 'Georgia Gras', 'Blackletter686 BT', 'wasy10', '36p Kana', 'AR PL UMing TW MBE', 'HCR Dotum', 'Eeyek Unicode', 'rsfs10', 'MotoyaG04GothicMono', 'Scruff LET', 'Gabo Drive', 'Latin Modern Roman Caps', 'One Stroke Script LET', 'Rage Italic LET', 'Lohit Gurmukhi', 'Latin Modern Mono Slanted', 'Arial monospaced for SAP', 'Bodoni 72 Oldstyle', 'MotoyaG04Gothic', 'Ume P Gothic C4', 'PingFang HK', 'Oxygen-Sans', 'Ume P Gothic', 'Khmer OS Content', 'Tsukushi A Round Gothic', 'MSung GB18030', 'Latin Modern Sans Quotation', 'HyhwpEQ', 'Ume Gothic C5', 'Albertus Medium', 'Broadway BT', 'Tlwg Mono', 'Calligraph421 BT', 'FixedSys', 'MisterEarl BT', 'Cataneo BT', 'Pump Demi Bold LET', 'Latin Modern Mono Prop Light', 'Tahoma Gras', 'Marigold', 'Nimbus Sans Narrow', 'Microsoft Yahei', 'Bodoni 72 Smallcaps', 'SchoolHouse Cursive B', 'Mekanik LET', 'Montserrat SemiBold', 'Verdana Gras', 'Enigmatic Unicode', 'Ume Gothic', 'PingFang TC', 'Latin Modern Sans', 'URW Gothic', 'Bradley Hand Gras', 'WenQuanYi Zen Hei Sharp', 'PingFang SC', 'ITF Devanagari Marathi', 'Georgia Italique', 'Latin Modern Mono Light', 'P', 'Tiranti Solid LET', 'Garamond Premr Pro', 'Mona Lisa Solid ITC TT', 'Hiragino Sans', 'AR PL UMing HK', 'Virgo 01', 'AR PL UMing CN', 'Staccato222 BT', 'ori1Uni', 'Ume P Mincho S3', 'OldDreadfulNo7 BT', 'Latin Modern Roman', 'Milano LET', 'esint10', 'WST', 'IPT', 'Courier New Gras', 'Ume UI Gothic', 'Arial Narrow Italique', 'Fixed', 'msbm10', 'Ume P Gothic S5', 'Mishafi Gold', 'Police système Semi-gras', 'Noto Sans Emoji', 'Thonburi Gras', 'Ume UI Gothic O5', 'Roman SD', 'PakType Naqsh', 'Ostrich Sans Heavy', 'Ume P Gothic C5', 'BRK', 'MotoyaG04MinchoMono', 'Tsukushi B Round Gothic', 'IV25', '12x10'];
    fontsToTest = fontsToTest.concat("cursive;monospace;serif;sans-serif;fantasy;default;Arial;Arial Black;Arial Narrow;Arial Rounded MT Bold;Book Antiqua;Bookman Old Style;Bradley Hand ITC;Bodoni MT;Calibri;Century;Century Gothic;Casual;Comic Sans MS;Consolas;Copperplate Gothic Bold;Courier;Courier New;English Text MT;Felix Titling;Futura;Garamond;Geneva;Georgia;Gentium;Haettenschweiler;Helvetica;Impact;Jokerman;King;Kootenay;Latha;Liberation Serif;Lucida Console;Lalit;Lucida Grande;Magneto;Mistral;Modena;Monotype Corsiva;MV Boli;OCR A Extended;Onyx;Palatino Linotype;Papyrus;Parchment;Pericles;Playbill;Segoe Print;Shruti;Tahoma;TeX;Times;Times New Roman;Trebuchet MS;Verdana;Verona;Arial Cyr;Comic Sans MS;Arial Black;Chiller;Arial Narrow;Arial Rounded MT Bold;Baskerville Old Face;Berlin Sans FB;Blackadder ITC;Lucida Console;Symbol;Times New Roman;Webdings;Agency FB;Vijaya;Algerian;Arial Unicode MS;Bodoni MT Poster Compressed;Bookshelf Symbol 7;Calibri;Cambria;Cambria Math;Kartika;MS Mincho;MS Outlook;MT Extra;Segoe UI;Aharoni;Aparajita;Amienne;cursive;Academy Engraved LET;LCD;LuzSans-Book;sans-serif;ZWAdobeF;Eurostile;SimSun-PUA;Blackletter686 BT;Myriad Web Pro Condensed;Matisse ITC;Bell Gothic Std Black;David Transparent;Adobe Caslon Pro;AR BERKLEY;Australian Sunrise;Myriad Web Pro;Gentium Basic;Highlight LET;Adobe Myungjo Std M;GothicE;HP PSG;DejaVu Sans;Arno Pro;Futura Bk;DejaVu Sans Condensed;Euro Sign;Neurochrome;Bell Gothic Std Light;Jokerman Alts LET;Adobe Fan Heiti Std B;Baby Kruffy;Tubular;Woodcut;HGHeiseiKakugothictaiW3;YD2002;Tahoma Small Cap;Helsinki;Bickley Script;Unicorn;X-Files;GENISO;Frutiger SAIN Bd v.1;Opus;ZDingbats;ABSALOM;Vagabond;Year supply of fairy cakes;Myriad Condensed Web;Segoe Media Center;Coronet;Helsinki Metronome;Segoe Condensed;Weltron Urban;AcadEref;DecoType Naskh;Freehand521 BT;Opus Chords Sans;Enviro;SWGamekeys MT;Croobie;Arial Narrow Special G1;AVGmdBU;Candles;Futura Bk BT;Andy;QuickType;WP Arabic Sihafa;DigifaceWide;ELEGANCE;BRAZIL;Pepita MT;Nina;Geneva;OCR B MT;Futura;Blade Runner Movie Font;Allegro BT;Lucida Blackletter;AGA Arabesque;AdLib BT;Clarendon;Monotype Sorts;Alibi;Bremen Bd BT;mono;News Gothic MT;AvantGarde Bk BT;chs_boot;fantasy;Palatino;BernhardFashion BT;Courier New;CloisterBlack BT;Scriptina;Tahoma;BernhardMod BT;Virtual DJ;Nokia Smiley;Boulder;Andale Mono IPA;Belwe Lt BT;Calligrapher;Belwe Cn BT;Tanseek Pro Arabic;FuturaBlack BT;Abadi MT Condensed;Mangal;Chaucer;Belwe Bd BT;Liberation Serif;DomCasual BT;Bitstream Vera Sans;URW Gothic L;GeoSlab703 Lt BT;Bitstream Vera Sans Mono;Nimbus Mono L;Heather;Antique Olive;Clarendon Cn BT;Amazone BT;Bitstream Vera Serif;Utopia;Americana BT;Map Symbols;Bitstream Charter;Aurora Cn BT;CG Omega;Lohit Punjabi;Balloon XBd BT;Akhbar MT;Courier 10 Pitch;Benguiat Bk BT;Market;Cursor;Bodoni Bk BT;Letter Gothic;Luxi Sans;Brush455 BT;Sydnie;Lohit Hindi;Lithograph;Albertus;DejaVu LGC Serif;Lydian BT;Antique Olive Compact;KacstArt;Incised901 Bd BT;Clarendon Extended;Lohit Telugu;Incised901 Lt BT;GiovanniITCTT;KacstOneFixed;Folio XBd BT;Edda;Loma;Formal436 BT;Fine Hand;Garuda;Impress BT;RefSpecialty;Sazanami Mincho;Staccato555 BT;VL Gothic;Hkmer OS;WP BoxDrawing;Clarendon Blk BT;Droid Sans;CommonBullets;Sherwood;Helvetica;CopprplGoth Bd BT;Smudger Alts LET;BPG Rioni;CopprplGoth BT;Guitar Pro 5;Estrangelo TurAbdin;Dauphin;Arial Tur;English111 Vivace BT;Steamer;OzHandicraft BT;Futura Lt BT;Liberation Sans Narrow;Futura XBlk BT;Candy Round BTN Cond;GoudyHandtooled BT;GrilledCheese BTN Cn;GoudyOlSt BT;Galeforce BTN;Kabel Bk BT;Sneakerhead BTN Shadow;OCR-A BT;Denmark;OCR-B 10 BT;Swiss921 BT;PosterBodoni BT;Arial (Arabic);Serifa BT;FlemishScript BT;Arial;American Typewriter;Arial Black;Apple Symbols;Arial Narrow;AppleMyungjo;Arial Rounded MT Bold;Zapfino;Arial Unicode MS;BlairMdITC TT-Medium;Century Gothic;Cracked;Papyrus;KufiStandardGK;Plantagenet Cherokee;Courier;Helvetica;Baskerville Old Face;Apple Casual;Type Embellishments One LET;Bookshelf Symbol 7;Abadi MT Condensed Extra Bold;Calibri;Calibri Bold;Calisto MT;Chalkduster;Cambria;Franklin Gothic Book Italic;Century;Geneva CY;Franklin Gothic Book;Helvetica Light;Gill Sans MT;Academy Engraved LET;MT Extra;Bank Gothic;Eurostile;Bodoni SvtyTwo SC ITC TT-Book;Tekton Pro;Courier CE;Maestro;BO Futura BoldOblique;Lucida Bright Demibold;New;AGaramond;Charcoal;DIN-Black;Lucida Sans Demibold;Stone Sans OS ITC TT-Bold;AGaramond Italic;Bickham Script Pro Regular;Adobe Arabic Bold;AGaramond Semibold;Al Bayan Bold;Doremi;AGaramond SemiboldItalic;Arno Pro Bold;Casual;B Futura Bold;Frutiger 47LightCn;Gadget;HelveticaNeueLT Std Bold;Frutiger 57Cn;DejaVu Serif Italic Condensed;Myriad Pro Black It;Frutiger 67BoldCn;Gentium Basic Bold;Sand;GillSans;H Futura Heavy;Liberation Mono Bold;GillSans Bold;Cambria Math;Courier Final Draft;HelveticaNeue BlackCond;cursive;Techno;HelveticaNeue BlackCondObl;Gabriola;JazzText Extended;HelveticaNeue BlackExt;sans-serif;Textile;HelveticaNeue BlackExtObl fantasy;HelveticaNeue BoldCond;Palatino Linotype Bold;HelveticaNeue BoldCondObl;BIRTH OF A HERO;HelveticaNeue BoldExt;Bleeding Cowboys;HelveticaNeue BoldExtObl;ChopinScript;HelveticaNeue ExtBlackCond;LCD;HelveticaNeue ExtBlackCondObl;Myriad Web Pro Condensed;HelveticaNeue HeavyCond;Scriptina;HelveticaNeue HeavyCondObl;OpenSymbol;HelveticaNeue HeavyExt;Virtual DJ;HelveticaNeue HeavyExtObl;Guitar Pro 5;HelveticaNeue LightCondObl;Nueva Std;HelveticaNeue ThinCond;Chicago;HelveticaNeue ThinCondObl;Nueva Std Bold;Brush Script MT;Capitals;Myriad Web Pro;Avant Garde;B Avant Garde Demi;Nueva Std Bold Italic;BI Avant Garde DemiOblique;MaestroTimes;Univers BoldExtObl;APC Courier;Myriad Web Pro Bold;Liberation Serif;Myriad Pro Light;Carta;DIN-Bold;DIN-Light;Myriad Web Pro Condensed Italic;DIN-Medium;Tekton Pro Oblique;DIN-Regular;AScore;HelveticaNeue UltraLigCondObl;Opus;HelveticaNeue UltraLigExt;Myriad Pro Light It;HelveticaNeue UltraLigExtObl;Opus Chords Sans;HO Futura HeavyOblique;Opus Japanese Chords;L Frutiger Light;VT100;L Futura Light;Helsinki;LO Futura LightOblique;Helsinki Metronome;Myriad Pro Black;New York;O Futura BookOblique;R Frutiger Roman;Reprise;TradeGothic;Warnock Pro Bold Caption;Univers 45 Light;Warnock Pro;XBO Futura ExtraBoldOblique;Univers 45 LightOblique;Liberation Mono;Univers 55 Oblique;UC LCD;Univers 57 Condensed;Warnock Pro Bold;Univers ExtraBlack;Warnock Pro Light Ital Subhead;Univers LightUltraCondensed;Matrix Ticker;Univers UltraCondensed;Fang Song".split(";"));

    return new Promise(function(resolve, reject){
      document.addEventListener("DOMContentLoaded", function(event){
        var baseFonts = ["monospace", "sans-serif", "serif"];
        var testString = "mmmmmmmmmmlli";
        var testSize = "72px";
        var h = document.getElementsByTagName("body")[0];

        // div to load spans for the base fonts
        var baseFontsDiv = document.createElement("div");

        // div to load spans for the fonts to detect
        var fontsDiv = document.createElement("div");

        var defaultWidth = {};
        var defaultHeight = {};

        // creates a span where the fonts will be loaded
        var createSpan = function() {
            var s = document.createElement("span");
            /*
            * We need this css as in some weird browser this
            * span elements shows up for a microSec which creates a
            * bad user experience
            */
            s.style.position = "absolute";
            s.style.left = "-9999px";
            s.style.fontSize = testSize;
            s.style.lineHeight = "normal";
            s.innerHTML = testString;
            return s;
        };

        var createSpanWithFonts = function(fontToDetect, baseFont) {
            var s = createSpan();
            s.style.fontFamily = "'" + fontToDetect + "'," + baseFont;
            return s;
        };

        var initializeBaseFontsSpans = function() {
            var spans = [];
            for (var index = 0, length = baseFonts.length; index < length; index++) {
                var s = createSpan();
                s.style.fontFamily = baseFonts[index];
                baseFontsDiv.appendChild(s);
                spans.push(s);
            }
            return spans;
        };

        var initializeFontsSpans = function() {
            var spans = {};
            for(var i = 0, l = fontsToTest.length; i < l; i++) {
                var fontSpans = [];
                for(var j = 0, numDefaultFonts = baseFonts.length; j < numDefaultFonts; j++) {
                    var s = createSpanWithFonts(fontsToTest[i], baseFonts[j]);
                    fontsDiv.appendChild(s);
                    fontSpans.push(s);
                }
                spans[fontsToTest[i]] = fontSpans;
            }
            return spans;
        };

        var isFontAvailable = function(fontSpans) {
            var detected = false;
            for(var i = 0; i < baseFonts.length; i++) {
                detected = (fontSpans[i].offsetWidth !== defaultWidth[baseFonts[i]] || fontSpans[i].offsetHeight !== defaultHeight[baseFonts[i]]);
                if(detected) {
                    return detected;
                }
            }
            return detected;
        };

        var baseFontsSpans = initializeBaseFontsSpans();

        // add the spans to the DOM
        h.appendChild(baseFontsDiv);

        // get the default width for the three base fonts
        for (var index = 0, length = baseFonts.length; index < length; index++) {
            defaultWidth[baseFonts[index]] = baseFontsSpans[index].offsetWidth; // width for the default font
            defaultHeight[baseFonts[index]] = baseFontsSpans[index].offsetHeight; // height for the default font
        }

        // create spans for fonts to detect
        var fontsSpans = initializeFontsSpans();

        // add all the spans to the DOM
        h.appendChild(fontsDiv);

        // check available fonts
        var available = [];
        for(var i = 0, l = fontsToTest.length; i < l; i++) {
            if(isFontAvailable(fontsSpans[fontsToTest[i]])) {
                available.push(fontsToTest[i]+"--true");
            }else{
                available.push(fontsToTest[i]+"--false");
            }
        }

        // remove spans from DOM
        h.removeChild(fontsDiv);
        h.removeChild(baseFontsDiv);
        return resolve(available.join(";;"));
      });
    });
}