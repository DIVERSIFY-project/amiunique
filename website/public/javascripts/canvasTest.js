//Canvas n°1 - Normal test (radial gradient)
canvas = document.getElementById ("canvas1");
ctx = canvas.getContext("2d");
canvas.style.display = "inline";
ctx.textBaseline = "alphabetic";
var gradient = ctx.createRadialGradient(canvas.width/2, canvas.height/2, 0, canvas.width/2, canvas.height/2, canvas.width/2);
gradient.addColorStop("0","red");
gradient.addColorStop("0.5","green");
gradient.addColorStop("1.0","blue");
ctx.fillStyle = gradient;
ctx.font = "15pt no-real-font-123";
ctx.fillText("Cwm fjordbank glyphs vext quiz, \ud83d\ude03", 15, 50);
var canvasData1 = canvas.toDataURL();

//Canvas n°2 - Normal test + stroke
canvas = document.getElementById ("canvas2");
ctx = canvas.getContext("2d");
canvas.style.display = "inline";
ctx.textBaseline = "alphabetic";
ctx.strokeStyle = gradient;
ctx.font = "15pt no-real-font-123";
ctx.strokeText("Cwm fjordbank glyphs vext quiz, \ud83d\ude03", 15, 50);
var canvasData2 = canvas.toDataURL();


//Canvas n°3 - Normal test + different string
canvas = document.getElementById ("canvas3");
ctx = canvas.getContext("2d");
canvas.style.display = "inline";
ctx.textBaseline = "alphabetic";
ctx.fillStyle = gradient;
ctx.font = "15pt no-real-font-123";
ctx.fillText("\ud83d\ude03 ,zuiq txev shpylg knabdrojf mwC", 15, 50);
var canvasData3 = canvas.toDataURL();

//Canvas n°4 - Normal test + longer string
canvas = document.getElementById ("canvas4");
ctx = canvas.getContext("2d");
canvas.style.display = "inline";
ctx.textBaseline = "alphabetic";
ctx.fillStyle = gradient;
ctx.font = "15pt no-real-font-123";
ctx.fillText("Cwm f1jor2dban3k4 gly56phs ve7xt 8 quiz9 ,10 \ud83d\ude03", 15, 50);
var canvasData4 = canvas.toDataURL();


//Canvas n°5 - Normal test + different font size
canvas = document.getElementById ("canvas5");
ctx = canvas.getContext("2d");
canvas.style.display = "inline";
ctx.textBaseline = "alphabetic";
var gradient2 = ctx.createRadialGradient(canvas.width/2, canvas.height/2, 0, canvas.width/2, canvas.height/2, canvas.width/2);
gradient2.addColorStop("0","red");
gradient2.addColorStop("0.5","green");
gradient2.addColorStop("1.0","blue");
ctx.fillStyle = gradient2;
ctx.font = "30pt no-real-font-123";
ctx.fillText("Cwm fjordbank glyphs vext quiz, \ud83d\ude03", 30, 100);
var canvasData5 = canvas.toDataURL();

//Canvas n°6 - Normal test + different font size + stroke
canvas = document.getElementById ("canvas6");
ctx = canvas.getContext("2d");
canvas.style.display = "inline";
ctx.textBaseline = "alphabetic";
ctx.strokeStyle = gradient2;
ctx.font = "30pt no-real-font-123";
ctx.strokeText("Cwm fjordbank glyphs vext quiz, \ud83d\ude03", 30, 100);
var canvasData6 = canvas.toDataURL();


//Canvas n°7 - Normal test + different rotation
canvas = document.getElementById ("canvas7");
ctx = canvas.getContext("2d");
canvas.style.display = "inline";
ctx.textBaseline = "alphabetic";
ctx.fillStyle = gradient;
ctx.font = "15pt no-real-font-123";
//ctx.translate( canvas.width / 2, canvas.height / 2 );
ctx.rotate(Math.PI/16);
ctx.fillText("Cwm fjordbank glyphs vext quiz, \ud83d\ude03", 20, 15);
var canvasData7 = canvas.toDataURL();


//Canvas n°8 - Normal test + different font
canvas = document.getElementById ("canvas8");
ctx = canvas.getContext("2d");
canvas.style.display = "inline";
ctx.textBaseline = "alphabetic";
ctx.fillStyle = gradient;
ctx.font = "15pt Arial";
ctx.fillText("Cwm fjordbank glyphs vext quiz, \ud83d\ude03", 15, 50);
var canvasData8 = canvas.toDataURL();

//Canvas n°9 - Normal test + different color
canvas = document.getElementById ("canvas9");
ctx = canvas.getContext("2d");
canvas.style.display = "inline";
ctx.textBaseline = "alphabetic";
var gradient3 = ctx.createRadialGradient(canvas.width/2, canvas.height/2, 0, canvas.width/2, canvas.height/2, canvas.width/2);
gradient3.addColorStop("0","purple");
gradient3.addColorStop("0.5","brown");
gradient3.addColorStop("1.0","cyan");
ctx.fillStyle = gradient3;
ctx.font = "15pt no-real-font-123";
ctx.fillText("Cwm fjordbank glyphs vext quiz, \ud83d\ude03", 15, 50);
var canvasData9 = canvas.toDataURL();

//Canvas n°10 - Normal test + different position of the points in the gradient
canvas = document.getElementById ("canvas10");
ctx = canvas.getContext("2d");
canvas.style.display = "inline";
ctx.textBaseline = "alphabetic";
var gradient4 = ctx.createRadialGradient(canvas.width/2, canvas.height/2, 0, canvas.width/2, canvas.height/2, canvas.width/2);
gradient4.addColorStop("0","red");
gradient4.addColorStop("0.25","green");
gradient4.addColorStop("1.0","blue");
ctx.fillStyle = gradient4;
ctx.font = "15pt no-real-font-123";
ctx.fillText("Cwm fjordbank glyphs vext quiz, \ud83d\ude03", 15, 50);
var canvasData10 = canvas.toDataURL();

//Canvas n°11 - Normal test + higher number of points for the gradient
canvas = document.getElementById ("canvas11");
ctx = canvas.getContext("2d");
canvas.style.display = "inline";
ctx.textBaseline = "alphabetic";
var gradient5 = ctx.createRadialGradient(canvas.width/2, canvas.height/2, 0, canvas.width/2, canvas.height/2, canvas.width/2);
gradient5.addColorStop("0","red");
gradient5.addColorStop("0.2","azure");
gradient5.addColorStop("0.4","chartreuse");
gradient5.addColorStop("0.6","darkorchid");
gradient5.addColorStop("0.8","crimson");
gradient5.addColorStop("1.0","lavender");
ctx.fillStyle = gradient5;
ctx.font = "15pt no-real-font-123";
ctx.fillText("Cwm fjordbank glyphs vext quiz, \ud83d\ude03", 15, 50);
var canvasData11 = canvas.toDataURL();

//Canvas n°12 - Normal test + linear gradient
canvas = document.getElementById ("canvas12");
ctx = canvas.getContext("2d");
canvas.style.display = "inline";
ctx.textBaseline = "alphabetic";
var gradient6 = ctx.createLinearGradient(0, 0, canvas.width, canvas.height);
gradient6.addColorStop("0","red");
gradient6.addColorStop("0.5","green");
gradient6.addColorStop("1.0","blue");
ctx.fillStyle = gradient6;
ctx.font = "15pt no-real-font-123";
ctx.fillText("Cwm fjordbank glyphs vext quiz, \ud83d\ude03", 15, 50);
var canvasData12 = canvas.toDataURL();

//Canvas n°13 - Normal test + more complex linear gradient
canvas = document.getElementById ("canvas13");
ctx = canvas.getContext("2d");
canvas.style.display = "inline";
ctx.textBaseline = "alphabetic";
var gradient7 = ctx.createLinearGradient(0, 0, canvas.width, canvas.height);
gradient7.addColorStop("0","red");
gradient7.addColorStop("0.2","azure");
gradient7.addColorStop("0.4","chartreuse");
gradient7.addColorStop("0.6","darkorchid");
gradient7.addColorStop("0.8","crimson");
gradient7.addColorStop("1.0","lavender");
ctx.fillStyle = gradient7;
ctx.font = "15pt no-real-font-123";
ctx.fillText("Cwm fjordbank glyphs vext quiz, \ud83d\ude03", 15, 50);
var canvasData13 = canvas.toDataURL();


//Canvas n°14 - Normal test + radial gradient at a different position
canvas = document.getElementById ("canvas14");
ctx = canvas.getContext("2d");
canvas.style.display = "inline";
ctx.textBaseline = "alphabetic";
var gradient8 = ctx.createRadialGradient(canvas.width/4, canvas.height/4, 0, canvas.width/2, canvas.height*3/4, canvas.width*3/4);
gradient8.addColorStop("0","red");
gradient8.addColorStop("0.5","green");
gradient8.addColorStop("1.0","blue");
ctx.fillStyle = gradient8;
ctx.font = "15pt no-real-font-123";
ctx.fillText("Cwm fjordbank glyphs vext quiz, \ud83d\ude03", 15, 50);
var canvasData14 = canvas.toDataURL();

//Canvas n°15 - Normal test + different Emoji
canvas = document.getElementById ("canvas15");
ctx = canvas.getContext("2d");
canvas.style.display = "inline";
ctx.textBaseline = "alphabetic";
ctx.fillStyle = gradient;
ctx.font = "15pt no-real-font-123";
ctx.fillText("Cwm fjordbank glyphs vext quiz, \ud83d\ude18", 15, 50);
var canvasData15 = canvas.toDataURL();

//********************************* Random
var maxGradientPoints = 15;
var minStringLength = 3;
var maxStringLength = 10;
var tailleMax = 78;

var fontList = [
    "Andale Mono", "Arial", "Arial Black", "Arial Hebrew", "Arial MT", "Arial Narrow", "Arial Rounded MT Bold", "Arial Unicode MS",
    "Bitstream Vera Sans Mono", "Book Antiqua", "Bookman Old Style",
    "Calibri", "Cambria", "Cambria Math", "Century", "Century Gothic", "Century Schoolbook", "Comic Sans", "Comic Sans MS", "Consolas", "Courier", "Courier New",
    "Garamond", "Geneva", "Georgia",
    "Helvetica", "Helvetica Neue",
    "Impact",
    "Lucida Bright", "Lucida Calligraphy", "Lucida Console", "Lucida Fax", "LUCIDA GRANDE", "Lucida Handwriting", "Lucida Sans", "Lucida Sans Typewriter", "Lucida Sans Unicode",
    "Microsoft Sans Serif", "Monaco", "Monotype Corsiva", "MS Gothic", "MS Outlook", "MS PGothic", "MS Reference Sans Serif", "MS Sans Serif", "MS Serif", "MYRIAD", "MYRIAD PRO",
    "Palatino", "Palatino Linotype",
    "Segoe Print", "Segoe Script", "Segoe UI", "Segoe UI Light", "Segoe UI Semibold", "Segoe UI Symbol",
    "Tahoma", "Times", "Times New Roman", "Times New Roman PS", "Trebuchet MS",
    "Verdana", "Wingdings", "Wingdings 2", "Wingdings 3"
];

function getRandomColor() {
    var letters = ['0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'];
    var color = '#';
    for (var i = 0; i < 6; i++ ) {
        color += letters[Math.floor(Math.random() * 16)];
    }
    return color;
}

function randomString(len, charSet) {
    charSet = charSet || 'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789';
    var randomString = '';
    for (var i = 0; i < len; i++) {
        var randomPos = Math.floor(Math.random() * charSet.length);
        randomString += charSet.substring(randomPos,randomPos+1);
    }
    return randomString;
}


function random(canvas,nbGlyphs) {
    var ctx = canvas.getContext("2d");
    for (var j = 0; j < nbGlyphs; j++) {
        var str = randomString(Math.floor(Math.random() * (maxStringLength - minStringLength + 1)) + minStringLength);

        ctx.font = Math.floor(Math.random() * tailleMax) + "pt " + fontList[Math.floor(Math.random() * fontList.length)]; //Random size
        var rX =  Math.floor(Math.random() * canvas.width); //Random X
        var rY = Math.floor(Math.random() * canvas.height); //Random Y
        var rAngle = Math.floor(Math.random() * 360) / 360; //Random rotation

        ctx.translate(rX, rY);
        ctx.rotate(Math.PI * rAngle);

        var randomX = Math.floor(Math.random() * canvas.width); //compris entre 0 et canvas.width
        var randomY = Math.floor(Math.random() * canvas.height); //compris entre 0 et canvas.height
        var gradient = ctx.createRadialGradient(randomX, randomY, 0, randomX, randomY, canvas.width-randomY);
        gradient.addColorStop("0",getRandomColor());
        gradient.addColorStop("1.0",getRandomColor());
        var nbRandomPoints = Math.floor(Math.random() * maxGradientPoints);
        for(var i=0; i<nbRandomPoints; i++){
            gradient.addColorStop(Math.random().toString(),getRandomColor());
        }

        ctx.fillStyle = gradient;
        ctx.fillText(str, 0, 0);
        ctx.rotate(-Math.PI * rAngle);
        ctx.translate(-rX, -rY);
    }
}


//Canvas n°16 - Random Test with one string
canvas = document.getElementById ("canvas16");
random(canvas,1);
var canvasData16 = canvas.toDataURL();


//Canvas n°17 - Random Test with two strings
canvas = document.getElementById ("canvas17");
random(canvas,2);
var canvasData17 = canvas.toDataURL();

//Canvas n°18 - Random Test with five strings
canvas = document.getElementById ("canvas18");
random(canvas,5);
var canvasData18 = canvas.toDataURL();