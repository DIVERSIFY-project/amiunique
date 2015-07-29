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
            var counters = {};
            var nbLoaded = 0;
            var canvas1 = document.createElement("canvas");
            var canvas2 = document.createElement("canvas");

            counters.list = vals;
            $.ajax({                                                                                                                                                                                                
                url: '/compareFpHistory',
                data: counters, 
                contentType: 'application/x-www-form-urlencoded', 
                method: "POST",
                success: function(data){
                    $("#resultComparaison").html(data);
                    $("#resultComparaison").css("display","block"); 

                    var tables = $('#resultComparaison table tr[class!="legend"]'); 
                    tableMaped = tables.map(function() {
                      var $row = $(this);
                      return {
                        col2: $row.find(':nth-child(2)').text(),
                        col3: $row.find(':nth-child(3)').text()
                      };
                    }).get();


                    tables.each(function( index ) {
                        var $row = $(this);
                        colAttribute = $row.find(' :nth-child(1)').text();
                        colBefore = $row.find(':nth-child(2)').text();
                        colAfter = $row.find(':nth-child(3)').text();

                        if(colAttribute === "pluginsJs"){
                          var pluginsParsedBefore = colBefore.split("Plugin ");
                          var pluginsParsedAfter = colAfter.split("Plugin ");

                          var pluginsBefore = new Array();
                          for(var i = 1; i < pluginsParsedBefore.length; i++){
                            pluginsBefore[i-1] = pluginsParsedBefore[i].split(": ")[1]+' ';
                          }

                          var pluginsAfter = new Array(); 
                          for(var i = 1; i < pluginsParsedAfter.length; i++){
                            pluginsAfter[i-1] = pluginsParsedAfter[i].split(": ")[1]+"  ";
                          }

                          colBefore = pluginsBefore.toString();
                          colAfter = pluginsAfter.toString();

                          $row.find(':nth-child(2)').html(colBefore);
                          $row.find(':nth-child(3)').html(colAfter);


                        }


                        //We search the differences between the 2 strings
                        var dmp = new diff_match_patch();
                        var a = dmp.diff_linesToWords_(colBefore+" ", colAfter+" ");
                         
                        var lineText1 = a['chars1'];
                        var lineText2 = a['chars2'];
                        var lineArray = a['lineArray'];

                        if(colAttribute != "canvasJs") {
                            diff = dmp.diff_main(lineText1, lineText2, false);
                            dmp.diff_charsToLines_(diff, lineArray);
                            if(colBefore.lastIndexOf("Flash", 0) === 0){
                            $row.find(':nth-child(4)').html('<del style="background:#ffe6e6;">Flash not detected </del><ins style="background:#e6ffe6;">'+colAfter+'</ins>');
                            }else if(colAfter.lastIndexOf("Flash", 0) === 0){
                                $row.find(':nth-child(4)').html('<del style="background:#ffe6e6;">'+colBefore+'</del><ins style="background:#e6ffe6;">Flash not detected </ins>');
                            }else{
                                $row.find(':nth-child(4)').html(dmp.diff_prettyHtml(diff));
                            }
                        } else {
                            var img1 = document.getElementById("img1");
                            var img2 = document.getElementById("img2");

                            canvas1.id = "cvs1";
                            canvas1.width = img1.width;
                            canvas1.height = img1.height;
                            ctx1 = canvas1.getContext("2d");
                            imgCvs1 = new Image();
                            imgCvs1.onload = function() {
                                ctx1.drawImage(imgCvs1, 0, 0);
                                nbLoaded++;
                            };
                            imgCvs1.src = img1.src;

                            canvas2.id = "cvs2";
                            canvas2.width = img1.width;
                            canvas2.height = img1.height;
                            ctx2 = canvas2.getContext("2d");
                            imgCvs2 = new Image();
                            imgCvs2.onload = function() {
                                ctx2.drawImage(imgCvs2, 0, 0);
                                nbLoaded++;
                            };
                            imgCvs2.src = img2.src;

                            finishCallback();
                        }

                    });
                                            
                }
            });

            function finishCallback(){
                console.log("Début du callback");
                if (nbLoaded < 2) {
                    console.log(nbLoaded);
                    setTimeout(function() {
                        finishCallback();
                    }, 100);
                } else {
                    var diffImg = document.getElementById("diffImg");
                    var canvasDiff = compareCanvas(canvas1, canvas2);
                    diffImg.src= canvasDiff.toDataURL();
                }
            }
        }
    });
});