$( document ).ready(function() {
    $('#validate').click(function(e){
    	datel = $("#datel").val();
    	dateu = $("#dateu").val();

      //We check if the date has the good format
      if(!(date1.match(/^[0-9]{4}\-(0[1-9]|1[012])\-(0[1-9]|[12][0-9]|3[01])/) && dateu.match(/^[0-9]{4}\-(0[1-9]|1[012])\-(0[1-9]|[12][0-9]|3[01])/))
      {
        e.preventDefault();
      }
    });
});