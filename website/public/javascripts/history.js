// Based on https://code.google.com/p/google-diff-match-patch/wiki/LineOrWordDiffs.
diff_match_patch.prototype.diff_linesToWords_ = function(text1, text2) {
  var lineArray = [];
  var lineHash = {};
 
  lineArray[0] = '';
 
  function diff_linesToCharsMunge_(text) {
    var chars = '';
    var lineStart = 0;
    var lineEnd = -1;
    var lineArrayLength = lineArray.length;
    var line;
 
    while (lineEnd < text.length - 1) {
      // Everything that is not a word symbol - is a separator.
      lineEnd = text.substring(lineStart).search(/[^\w]/);
 
      if (lineEnd == -1) {
        lineEnd = text.length - 1;
      } else {
        lineEnd += lineStart;
      }
 
      // Do not include separator symbols in line.
      if (lineStart === lineEnd) {
        line = text.substring(lineStart, lineEnd + 1);
        lineStart = lineEnd + 1;
      } else {
        line = text.substring(lineStart, lineEnd);
        lineStart = lineEnd;
      }
 
      try {
 
      // NOTE if you have hasOwnProperty in lineHash - then you are going to call on number and get a message like this:
      // if (lineHash.hasOwnProperty ? lineHash.hasOwnProperty(line) : (lineHash[
      //                                              ^
      // TypeError: number is not a function
 
      // if (lineHash.hasOwnProperty ? lineHash.hasOwnProperty(line) : (lineHash[line] !== undefined)) {
      if (lineHash[line] !== undefined) {
        chars += String.fromCharCode(lineHash[line]);
      } else {
        chars += String.fromCharCode(lineArrayLength);
        lineHash[line] = lineArrayLength;
        lineArray[lineArrayLength++] = line;
      }
        } catch (ex) {
            console.log(lineHash.hasOwnProperty, lineHash);
        }
    }
    return chars;
  }
 
  var chars1 = diff_linesToCharsMunge_(text1);
  var chars2 = diff_linesToCharsMunge_(text2);
  return {chars1: chars1, chars2: chars2, lineArray: lineArray};
};
 
// ----------------------------------------------------------------------------------------------------------------- //
// Then you can calculate diff like this:
var dmp = new diff_match_patch();
var text1 = "j'aime le pain aux olives";
var text2 = "j'aime la baguette aux olives";
var a = dmp.diff_linesToWords_(text1, text2);
 
var lineText1 = a['chars1'];
var lineText2 = a['chars2'];
var lineArray = a['lineArray'];
 
diff = dmp.diff_main(lineText1, lineText2, false);
dmp.diff_charsToLines_(diff, lineArray);
//console.log(diff);

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
                        colBefore = $row.find(':nth-child(3)');
                        colAfter = $row.find(':nth-child(2)');


                        //We search the differences between the 2 strings
                        var dmp = new diff_match_patch();
                        var a = dmp.diff_linesToWords_(colBefore.text()+" ", colAfter.text()+" ");
                         
                        var lineText1 = a['chars1'];
                        var lineText2 = a['chars2'];
                        var lineArray = a['lineArray'];
                         
                        diff = dmp.diff_main(lineText1, lineText2, false);
                        dmp.diff_charsToLines_(diff, lineArray);
                        console.log("test ");
                        console.log(dmp.diff_prettyHtml(diff));

                        if(colBefore.text()==="Flash not detected"){
                            $row.find(':nth-child(2)').html('<del style="background:#ffe6e6;">Flash not detected </del><ins style="background:#e6ffe6;">'+colAfter.text()+'</ins>');
                        }else if(colAfter.text()==="Flash not detected"){
                            $row.find(':nth-child(2)').html('<ins style="background:#e6ffe6;">Flash not detected </ins><del style="background:#ffe6e6;">'+colBefore.text()+'</del>');
                        }else{
                            $row.find(':nth-child(2)').html(dmp.diff_prettyHtml(diff));
                        }

                    });
                                            
                }
            });
        }
    });
});