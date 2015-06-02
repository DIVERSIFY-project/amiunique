function sendDates(datel, dateu, typeRequest){
	var dates = {};
	dates.datel = datel;
	dates.dateu = dateu;

	$("body").append('<form style="visibility:hidden" id="fakeForm" action="/stats" method="POST"><input type="date"  value="'+datel+'" name="datel" /><input type="date"  value="'+dateu+'" name="dateu"/><input type="text" value="'+typeRequest+'" name="typereq"><button id="valid" type="submit" class="btn btn-default">Envoyer</button>');

    $('#valid').trigger('click');
}

$( document ).ready(function() {
    $("a[role=button]").click(1000,function(){
        if( $(this).attr("id") != "custom"){
            if($(this).attr("id") === "all"){
            	//Call the page without any parameters
            	window.location.href = "/stats";
            }else if($(this).attr("id") === "month"){
            	datel = new Date().last().month().toString('yyyy-MM-dd');
            	dateu = Date.today().toString('yyyy-MM-dd') ;
            	sendDates(datel, dateu, "month");
            }else if($(this).attr("id") === "week"){
            	datel = new Date().last().week().toString('yyyy-MM-dd');
            	dateu = Date.today().toString('yyyy-MM-dd') ;
            	sendDates(datel, dateu, "week");
            }
        }else if($(this).attr("id") === "custom"){
            $("#dateForm").css("display","block");
        }
    });

    $('#validate').click(function(e){
    	datel = $("#datel").val();
    	dateu = $("#dateu").val();
      //We check if the date has the good format
      if(!(date1.match(/^[0-9]{4}\-(0[1-9]|1[012])\-(0[1-9]|[12][0-9]|3[01])/) && dateu.match(/^[0-9]{4}\-(0[1-9]|1[012])\-(0[1-9]|[12][0-9]|3[01])/)))
      {
        /*

            Ajouter un traitement !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

        */
        e.preventDefault();
      }
    });
});