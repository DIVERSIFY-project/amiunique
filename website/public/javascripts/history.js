function getCheckedValues()
{
  return ($("input[type=checkbox]:checked").map(
     function () {return this.value;}).get().join(","));
}

$( document ).ready(function() {

    $("#compareButton").click(function(){
        var numberCheckbox = $( "input:checked" ).length;

        if(numberCheckbox < 2 ){
            $('#verifycb').trigger('click');                                                                                    
        }else{                                                                                                                                                                                                                                        
            var vals = getCheckedValues();                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                              
            console.log("valeurs : "+vals);
            var counters = {};
            counters.list = vals;
            $.ajax({                                                                                                                                                                                                
                url: '/compareFpHistory',
                data: counters, 
                contentType: 'application/x-www-form-urlencoded', 
                method: "POST",
                success: function(data){
                    $("#resultComparaison").html(data);
                    $("#resultComparaison").css("display","block");                                                     
                }
            });
        }
    });
});