function init(canvas){
    var ctx = canvas.getContext("2d");
    canvas.style.display = "inline";
    ctx.textBaseline = "alphabetic";
    var gradient = ctx.createRadialGradient(canvas.width/2, canvas.height/2, 0, canvas.width/2, canvas.height/2, canvas.width/2);
    gradient.addColorStop("0","red");
    gradient.addColorStop("0.5","green");
    gradient.addColorStop("1.0","blue");
    ctx.fillStyle = gradient;
    ctx.font = "15pt no-real-font-123";
    ctx.fillText("Cwm fjordbank glyphs vext quiz, \ud83d\ude03", 15, 50);
    return ctx;
}


//Canvas n°1 - Normal test (radial gradient)
var c1 = document.getElementById ("canvas1");
init(c1);
var canvasData1 = c1.toDataURL();

//Canvas n°2 - Normal test + quadratic curve
var c2 = document.getElementById ("canvas2");
var ctx2 = init(c2);
ctx2.beginPath();
ctx2.moveTo(80, 80);
ctx2.bezierCurveTo(-20, -20, 50, 25, 380, 85);
ctx2.lineWidth = 1;
ctx2.strokeStyle = 'black';
ctx2.stroke();
var canvasData2 = c2.toDataURL();


//Canvas n°3 - Normal test + bezier curve
var c3 = document.getElementById ("canvas3");
var ctx3 = init(c3);
ctx3.beginPath();
ctx3.moveTo(50, 20);
ctx3.quadraticCurveTo(20, 100, 300, 20);
ctx3.stroke();
var canvasData3 = c3.toDataURL();

//Canvas n°4 - Normal test + quadratic and bezier curves
var c4 = document.getElementById ("canvas4");
var ctx4 = init(c4);
ctx4.beginPath();
ctx4.moveTo(80, 80);
ctx4.bezierCurveTo(-20, -20, 50, 25, 380, 85);
ctx4.lineWidth = 1;
ctx4.strokeStyle = 'black';
ctx4.stroke();
ctx4.beginPath();
ctx4.moveTo(50, 20);
ctx4.quadraticCurveTo(20, 100, 300, 20);
ctx4.stroke();
var canvasData4 = c4.toDataURL();

//Canvas n°5 - Normal test + quadratic and bezier curves
var c5 = document.getElementById ("canvas5");
var ctx5 = init(c5);
ctx5.beginPath();
ctx5.moveTo(80, 80);
ctx5.bezierCurveTo(-20, -20, 50, 25, 380, 85);
ctx5.lineWidth = 3;
ctx5.strokeStyle = 'black';
ctx5.stroke();
ctx5.beginPath();
ctx5.moveTo(50, 20);
ctx5.quadraticCurveTo(20, 100, 300, 20);
ctx5.stroke();
var canvasData5 = c5.toDataURL();


//Canvas n°6 - Normal test + shadow blur
var c6 = document.getElementById ("canvas6");
var ctx6 = c6.getContext("2d");
c6.style.display = "inline";
ctx6.textBaseline = "alphabetic";
var g6 = ctx6.createRadialGradient(c6.width/2, c6.height/2, 0, c6.width/2, c6.height/2, c6.width/2);
g6.addColorStop("0","red");
g6.addColorStop("0.5","green");
g6.addColorStop("1.0","blue");
ctx6.fillStyle = g6;
ctx6.font = "15pt no-real-font-123";
ctx6.shadowBlur = 5;
ctx6.shadowColor = "black";
ctx6.fillText("Cwm fjordbank glyphs vext quiz, \ud83d\ude03", 15, 50);
var canvasData6 = c6.toDataURL();

//Canvas n°7 - Normal test + shadow blur + different shadow color
var c7 = document.getElementById ("canvas7");
var ctx7 = c7.getContext("2d");
c7.style.display = "inline";
ctx7.textBaseline = "alphabetic";
var g7 = ctx7.createRadialGradient(c7.width/2, c7.height/2, 0, c7.width/2, c7.height/2, c7.width/2);
g7.addColorStop("0","red");
g7.addColorStop("0.5","green");
g7.addColorStop("1.0","blue");
ctx7.fillStyle = g7;
ctx7.font = "15pt no-real-font-123";
ctx7.shadowBlur = 5;
ctx7.shadowColor = "DarkOrange";
ctx7.fillText("Cwm fjordbank glyphs vext quiz, \ud83d\ude03", 15, 50);
var canvasData7 = c7.toDataURL();


//Canvas n°8 - Normal test + shadow blur (weaker)
var c8 = document.getElementById ("canvas8");
var ctx8 = c8.getContext("2d");
c8.style.display = "inline";
ctx8.textBaseline = "alphabetic";
var g8 = ctx8.createRadialGradient(c8.width/2, c8.height/2, 0, c8.width/2, c8.height/2, c8.width/2);
g8.addColorStop("0","red");
g8.addColorStop("0.5","green");
g8.addColorStop("1.0","blue");
ctx8.fillStyle = g8;
ctx8.font = "15pt no-real-font-123";
ctx8.shadowBlur = 10;
ctx8.shadowColor = "black";
ctx8.fillText("Cwm fjordbank glyphs vext quiz, \ud83d\ude03", 15, 50);
var canvasData8 = c8.toDataURL();


//Canvas n°9 - Normal test + everything new
var c9 = document.getElementById ("canvas9");
var ctx9 = c9.getContext("2d");
c9.style.display = "inline";
ctx9.textBaseline = "alphabetic";
var g9 = ctx9.createRadialGradient(c9.width/2, c9.height/2, 0, c9.width/2, c9.height/2, c9.width/2);
g9.addColorStop("0","red");
g9.addColorStop("0.5","green");
g9.addColorStop("1.0","blue");
ctx9.fillStyle = g9;
ctx9.font = "15pt no-real-font-123";
ctx9.shadowBlur = 5;
ctx9.shadowColor = "black";
ctx9.fillText("Cwm fjordbank glyphs vext quiz, \ud83d\ude03", 15, 50);
ctx9.beginPath();
ctx9.moveTo(80, 80);
ctx9.bezierCurveTo(-20, -20, 50, 25, 380, 85);
ctx9.lineWidth = 1;
ctx9.strokeStyle = 'black';
ctx9.stroke();
ctx9.beginPath();
ctx9.moveTo(50, 20);
ctx9.quadraticCurveTo(20, 100, 300, 20);
ctx9.stroke();
var canvasData9 = c9.toDataURL();


//Canvas n°10 - Normal test + evertyhing new (stronger line width)
var c10 = document.getElementById ("canvas10");
var ctx10 = c10.getContext("2d");
c10.style.display = "inline";
ctx10.textBaseline = "alphabetic";
var g10 = ctx10.createRadialGradient(c10.width/2, c10.height/2, 0, c10.width/2, c10.height/2, c10.width/2);
g10.addColorStop("0","red");
g10.addColorStop("0.5","green");
g10.addColorStop("1.0","blue");
ctx10.fillStyle = g10;
ctx10.font = "15pt no-real-font-123";
ctx10.shadowBlur = 5;
ctx10.shadowColor = "black";
ctx10.fillText("Cwm fjordbank glyphs vext quiz, \ud83d\ude03", 15, 50);
ctx10.beginPath();
ctx10.moveTo(80, 80);
ctx10.bezierCurveTo(-20, -20, 50, 25, 380, 85);
ctx10.lineWidth = 3;
ctx10.strokeStyle = 'black';
ctx10.stroke();
ctx10.beginPath();
ctx10.moveTo(50, 20);
ctx10.quadraticCurveTo(20, 100, 300, 20);
ctx10.stroke();
var canvasData10 = c10.toDataURL();

var canvasData11 = "";
var canvasData12 = "";
var canvasData13 = "";
var canvasData14 = "";
var canvasData15 = "";
var canvasData16 = "";
var canvasData17 = "";
var canvasData18 = "";