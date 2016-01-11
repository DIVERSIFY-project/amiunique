function sendDates(datel, dateu, typeRequest){
    $("#datel").val(datel);
    $("#dateu").val(dateu);
    $("#typereq").val(typeRequest);
    $('#validate').trigger('click');
}

$( document ).ready(function() {
    $("a[role=button]").click(1000,function(){
        if( $(this).attr("id") != "custom"){
            if($(this).attr("id") === "all"){
            	//Call the page without any parameters
            	window.location.href = "/stats";
            }else if($(this).attr("id") === "month"){
            	datel = new Date().last().month().toString('dd-MM-yyyy');
            	dateu = Date.today().toString('dd-MM-yyyy') ;
            	sendDates(datel, dateu, "month");
            }else if($(this).attr("id") === "week"){
            	datel = new Date().last().week().toString('dd-MM-yyyy');
            	dateu = Date.today().toString('dd-MM-yyyy') ;
            	sendDates(datel, dateu, "week");
            }
        }else if($(this).attr("id") === "custom"){
            $("#dateForm").css("display","block");
        }
    });
});