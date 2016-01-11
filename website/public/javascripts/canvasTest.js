function drawEllipse(context, centerX, centerY, width, height) {

    context.beginPath();

    context.moveTo(centerX, centerY - height/2);

    context.bezierCurveTo(
        centerX + width/2, centerY - height/2,
        centerX + width/2, centerY + height/2,
        centerX, centerY + height/2);

    context.bezierCurveTo(
        centerX - width/2, centerY + height/2,
        centerX - width/2, centerY - height/2,
        centerX, centerY - height/2);

    context.fillStyle = "black";
    context.fill();
    context.closePath();
}

function drawPap(ctx, fillStyle) {

    ctx.fillStyle = "rgba(200,200,200,0.3)";
    ctx.fillRect(-30, -30, 60, 60);

    ctx.fillStyle = fillStyle;
    ctx.globalAlpha = 1.0;
    ctx.beginPath();
    ctx.moveTo(25, 25);
    ctx.lineTo(-25, -25);
    ctx.lineTo(25, -25);
    ctx.lineTo(-25, 25);
    ctx.closePath();
    ctx.fill();
}

function point(ctx) {
    ctx.save();
    ctx.fillStyle = "black";
    ctx.fillRect(-2, -2, 4, 4);
    ctx.restore();
}

function drawDashedLine(context, x1, y1, x2, y2, dashLength) {
    dashLength = dashLength === undefined ? 5 : dashLength;

    var deltaX = x2 - x1;
    var deltaY = y2 - y1;
    var numDashes = Math.floor(
        Math.sqrt(deltaX * deltaX + deltaY * deltaY) / dashLength);

    for (var i=0; i < numDashes; ++i) {
        context[ i % 2 === 0 ? 'moveTo' : 'lineTo' ]
        (x1 + (deltaX / numDashes) * i, y1 + (deltaY / numDashes) * i);
    }

    context.stroke();
}

//Canvas n°1
canvas = document.getElementById ("canvas1");
ctx = canvas.getContext("2d");
canvas.style.display = "inline";
ctx.textBaseline = "alphabetic";
ctx.fillStyle = "#069";
ctx.font = "11pt no-real-font-123";
ctx.fillText("Cwm fjordbank glyphs vext quiz, \ud83d\ude03", 2, 15);
var canvasData1 = canvas.toDataURL();

//Canvas n°2
canvas = document.getElementById ("canvas2");
ctx = canvas.getContext("2d");
canvas.style.display = "inline";
ctx.textBaseline = "alphabetic";
ctx.fillStyle = "rgba(102, 204, 0, 0.7)";
ctx.font = "18pt Arial";
ctx.fillText("Cwm fjordbank glyphs vext quiz, \ud83d\ude03", 4, 45);
var canvasData2 = canvas.toDataURL();

//Canvas n°3
canvas = document.getElementById ("canvas3");
ctx = canvas.getContext("2d");
canvas.style.display = "inline";
ctx.textBaseline = "alphabetic";
ctx.fillStyle = "rgba(153, 0, 153, 0.7)";
ctx.font = "18pt Arial";
ctx.fillText("Updated string with new color, \ud83d\ude12", 4, 45);
var canvasData3 = canvas.toDataURL();

//Canvas n°4
canvas = document.getElementById ("canvas4");
ctx = canvas.getContext("2d");
ctx.fillStyle = "rgba(153, 0, 153, 0.7)";
ctx.font = "18pt Arial";
ctx.fillText("Updated string with new color, \u0F03", 4, 45);
var canvasData4 = canvas.toDataURL();

//Canvas n°5
canvas = document.getElementById ("canvas5");
ctx = canvas.getContext("2d");
ctx.font = "20px Georgia";
ctx.fillText("Hello World!", 10, 40);
var canvasData5 = canvas.toDataURL();

//Canvas n°6
canvas = document.getElementById ("canvas6");
ctx = canvas.getContext("2d");
ctx.font="30px Verdana";
var gradient=ctx.createLinearGradient(0,0,canvas.width,0);
gradient.addColorStop("0","magenta");
gradient.addColorStop("0.5","blue");
gradient.addColorStop("1.0","red");
ctx.strokeStyle=gradient;
ctx.strokeText("Hello world !",170,40);
var canvasData6 = canvas.toDataURL();

//Canvas n°7
canvas = document.getElementById ("canvas7");
ctx = canvas.getContext("2d");
ctx.font="130px Verdana";
ctx.strokeStyle=gradient;
ctx.strokeText("Hello",20,130);
ctx.strokeText("World",5,330);
var canvasData7 = canvas.toDataURL();

//Canvas n°8
canvas = document.getElementById ("canvas8");
ctx = canvas.getContext("2d");
canvas.style.display = "inline";
ctx.textBaseline = "alphabetic";
drawEllipse(ctx,175, 30, 400, 23); // Left Eye
drawEllipse(ctx,350, 30, 15, 50); // Right Eye
var canvasData8 = canvas.toDataURL();

//Canvas n°9
canvas = document.getElementById ("canvas9");
ctx = canvas.getContext("2d");
ctx.fillStyle = "#f60";
ctx.fillRect(125, 10, 183, 40);
var canvasData9 = canvas.toDataURL();

//Canvas n°10
canvas = document.getElementById ("canvas10");
ctx = canvas.getContext("2d");
ctx.shadowColor = '#0a0';
ctx.shadowOffsetX = 0;
ctx.shadowOffsetY = 0;
ctx.shadowBlur    = 25;
ctx.fillStyle   = 'red';
ctx.beginPath();
ctx.arc(40,40,25,0,2 * Math.PI, false);
ctx.fill();
var canvasData10 = canvas.toDataURL();

//Canvas n°11
canvas = document.getElementById ("canvas11");
ctx = canvas.getContext("2d");
ctx.fillStyle = "red";
ctx.beginPath();
ctx.moveTo(30, 30);
ctx.lineTo(150, 150);
ctx.bezierCurveTo(60, 70, 60, 70, 70, 150);
ctx.lineTo(30, 30);
ctx.fill();
var canvasData11 = canvas.toDataURL();

//Canvas n°12
canvas = document.getElementById ("canvas12");
ctx = canvas.getContext("2d");
ctx.translate(45, 45);
ctx.save();
drawPap(ctx, "red");
point(ctx);
ctx.restore();
ctx.save();
ctx.translate(85, 0);
ctx.rotate(45 * Math.PI / 180);
drawPap(ctx, "green");
point(ctx);
ctx.restore();
ctx.save();
ctx.translate(0, 85);
ctx.rotate(135 * Math.PI / 180);
drawPap(ctx, "blue");
point(ctx);
ctx.restore();
ctx.save();
ctx.translate(85, 85);
ctx.rotate(90 * Math.PI / 180);
drawPap(ctx, "yellow");
point(ctx);
ctx.restore();
var canvasData12 = canvas.toDataURL();

//Canvas n°13
canvas = document.getElementById ("canvas13");
ctx = canvas.getContext("2d");
var canvasWidth = 200;
var canvasHeight = 267;
var width = 125;
var height = 105;
var padding = 25;
var lineWidth = 8;
var innerBorder = 5;
var primaryColor = "#ffc821";
var secondaryColor = "#faf100";
var tertiaryColor = "#dcaa09";
ctx.beginPath();
ctx.moveTo(padding + width/2, padding);
ctx.lineTo(padding + width, height + padding);
ctx.lineTo(padding, height + padding);
ctx.closePath();
gradient = ctx.createLinearGradient(0,0,0,height);
gradient.addColorStop(0, primaryColor);
gradient.addColorStop(1, secondaryColor);
ctx.shadowBlur = 10;
ctx.shadowColor = "black";
ctx.lineWidth = lineWidth * 2;
ctx.lineJoin = "round";
ctx.strokeStyle = gradient;
ctx.stroke();
ctx.shadowColor = "transparent";
ctx.fillStyle = gradient;
ctx.fill();
gradient=ctx.createLinearGradient(0,padding,0,padding+height);
gradient.addColorStop(0, "transparent");
gradient.addColorStop(0.5, "transparent");
gradient.addColorStop(0.5, tertiaryColor);
gradient.addColorStop(1, secondaryColor);
ctx.fillStyle = gradient;
ctx.fill();
ctx.lineWidth = lineWidth;
ctx.lineJoin = "round";
ctx.strokeStyle = "#333";
ctx.stroke();
ctx.textAlign = "center";
ctx.textBaseline = "middle";
ctx.font = "bold 60px 'Times New Roman', Times, serif";
ctx.fillStyle = "#333";
try{
    ctx.fillText("!", padding + width/2, padding + height/1.5);
}catch(ex){}
var canvasData13 = canvas.toDataURL();

//Canvas n°14
canvas = document.getElementById ("canvas14");
ctx = canvas.getContext("2d");
gradient = ctx.createRadialGradient(
    canvas.width/2, canvas.height, 10,
    canvas.width/2, 0, 100);

gradient.addColorStop(0, 'blue');
gradient.addColorStop(0.25, 'white');
gradient.addColorStop(0.5, 'purple');
gradient.addColorStop(0.75, 'red');
gradient.addColorStop(1, 'yellow');

ctx.fillStyle = gradient;
ctx.rect(0, 0, canvas.width, canvas.height);
ctx.fill();
var canvasData14 = canvas.toDataURL();

//Canvas n°15
canvas = document.getElementById ("canvas15");
ctx = canvas.getContext("2d");
ctx.lineWidth = 3;
ctx.strokeStyle = 'blue';

drawDashedLine(ctx, 20, 20, ctx.canvas.width-20, 20);
drawDashedLine(ctx, ctx.canvas.width-20, 20, ctx.canvas.width-20, ctx.canvas.height-20, 10);
drawDashedLine(ctx, ctx.canvas.width-20, ctx.canvas.height-20, 20, ctx.canvas.height-20, 15);
drawDashedLine(ctx, 20, ctx.canvas.height-20, 20, 20, 2);
var canvasData15 = canvas.toDataURL();