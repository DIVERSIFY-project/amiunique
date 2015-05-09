$( document ).ready(function() {
    if(readCookie('tempFp') != null){
    	setTimeout(function() {
    		language = $('#languageHttpVal').text();
     		language = language.split(";");

     		if(language[0].toLowerCase().indexOf("fr") >= 0){
      			$('<a id="forceFp" class="btn btn-warning btn-lg" role="button">Forcer fingerprinting</a>').insertAfter("#graBut");
      		}else{
       			$('<a id="forceFp" class="btn btn-warning btn-lg" role="button">Force fingerprinting</a>').insertAfter("#graBut");		
      		}
      		$('#forceFp').css('margin-left','3px');

      		$( "#forceFp" ).mouseover(function() {
     			if(language[0].toLowerCase().indexOf("fr") >= 0){
					$('<p id="infoforcefp"><br/>Nous utilisons un cookie temporaire de 3 minutes pour conserver votre fingerprint et charger votre empreinte plus rapidement. Si vous avez effectué des modifications et vous souhaitez les visualiser, cliquez sur forcer fingerprinting</p>').insertAfter("#forceFp");
				}else{
					$('<p id="infoforcefp"><br/>We use a temporary cookie of 3 minutes in order to keep your fingerprint and load it faster. If you have made change and would like to see them, click on Force Fingerprinting</p>').insertAfter("#forceFp");		
				}
				$('#infoforcefp').css('font-weight','bold');
				$('#infoforcefp').css('color','#149c82');
			});

			$( "#forceFp" ).mouseleave(function() {
			  $('#infoforcefp').remove();
			});

			$("#forceFp").click(function(){
				createCookie('tempFp', 1, -1);
				createCookie('tempPerc', 1, -1);
				if(domLocalStorage == "yes"){
					localStorage.setItem("tempFpContent", "");
				}

				location.reload();
			});

		}, 500);
    }
});


