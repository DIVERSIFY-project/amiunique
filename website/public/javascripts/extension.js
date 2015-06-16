$(document).ready(function(){

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


    if(domLocalStorage === "yes"){
      
        if(localStorage.getItem("fpUserId") != null){

            if((localStorage.getItem("lastFp") != null && parseFloat(localStorage.getItem("lastFp"))+ (30) <= Math.round(new Date().getTime()/1000.0)) || (localStorage.getItem("lastFp") == null)){

                $("#presExt").html(loadingGif);
                $("#presExt").css("text-align","center");
                $("#presExt").css("margin-top","50px");

                sendFp(localStorage.getItem("fpUserId"), ieUserData, domLocalStorage, domSessionStorage, orderHttp);
                localStorage.setItem("lastFp", Math.round(new Date().getTime()/1000.0));
            }else{
                $("#presExt").html("");
                $("#successAdd").append(successAdd);
            }
        }

    }



    function sendFp(userId, ieUserData, domLocalStorage, domSessionStorage, orderHttp){
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

      //Test if java is enabled
      java = navigator.javaEnabled();

      try {
          canvas = document.createElement("canvas");
          canvas.height = 60;
          canvas.width = 400;
          canvasContext = canvas.getContext("2d");
          canvas.style.display = "inline";
          canvasContext.textBaseline = "alphabetic";
          canvasContext.fillStyle = "#f60";
          canvasContext.fillRect(125, 1, 62, 20);
          canvasContext.fillStyle = "#069";
          canvasContext.font = "11pt no-real-font-123";
          canvasContext.fillText("Cwm fjordbank glyphs vext quiz, \ud83d\ude03", 2, 15);
          canvasContext.fillStyle = "rgba(102, 204, 0, 0.7)";
          canvasContext.font = "18pt Arial";
          canvasContext.fillText("Cwm fjordbank glyphs vext quiz, \ud83d\ude03", 4, 45);
          canvasData = canvas.toDataURL();
      } catch(e){
          canvasData = "Not supported";
      }

       
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

        var osData ={};

        osData.pluginsJs =  plugins.replace(/[&\/\\#,+()$~%'"*?<>{}]/g,'');
        osData.platformJs = platform;
        osData.cookiesJs = cookieEnabled;
        osData.dntJs = doNotTrack;
        osData.timezoneJs = timezone;
        osData.resolutionJs = resolution;
        osData.localJs = domLocalStorage;
        osData.sessionJs = domSessionStorage;
        osData.IEDataJs =  ieUserData;
        osData.canvasJs = canvasData;
        osData.adBlock = document.getElementById('ads')? 'no' : 'yes';
        osData.fontsFlash = fontsFlash;
        osData.resolutionFlash = resolutionFlash;
        osData.languageFlash = languageFlash;
        osData.platformFlash = platformFlash;
        osData.id=localStorage.getItem("fpUserId");
        osData.orderHttp=orderHttp;

        console.log(osData);

        $.ajax({
          url: 'http://localhost:9000/evolution',
          data: osData, 
          contentType: 'application/x-www-form-urlencoded', 
          method: "POST",
          success : function(){
            $("#presExt").html("");
            $("#successAdd").append(successAdd);

            if(readCookie('amiunique') != null){
                createCookie('amiunique', osData.id, 120)
            }
          }
        });

    },1000);
    }         

});