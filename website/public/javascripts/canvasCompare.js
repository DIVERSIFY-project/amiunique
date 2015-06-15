(function (global, doc) {
    'use strict';
    var ctx = "2d";
    var tag = "canvas";

    function compareCanvas(canvas1, canvas2) {

        console.log(canvas1);
        var ctx1 = canvas1.getContext(ctx),
            data1 = ctx1.getImageData(0, 0, canvas1.width, canvas1.height);

        var ctx2 = canvas2.getContext(ctx),
            data2 = ctx2.getImageData(0, 0, canvas2.width, canvas2.height);

        var diffObject = diff(canvas1.width, canvas1.height, data1, data2);
        return diffObject.canvas;

    }

    function diff(nWidth, nHeight, aDataImage, aLastDataImage) {
        var aData = aDataImage.data,
            aLastData = aLastDataImage.data,
            nLenPixels,
            nDiffPixels = 0,
            nDiffPercentage,
            oCanvas = doc.createElement(tag),
            oContext = oCanvas.getContext(ctx),
            oDataImage = oContext.createImageData(nWidth, nHeight),
            aCreatedDataImage = oDataImage.data,
            nData,
            nRow,
            nColumn = 0,
            nX = 0,
            nY = 0,
            nLenData = aCreatedDataImage.length,
            nRed, nGreen, nBlue, nAlpha, nLastRed, nLastGreen, nLastBlue, nLastAlpha;
        oCanvas.width = nWidth;
        oCanvas.height = nHeight;
        oCanvas.style.border = "#000 1px solid";

        for (nData = nLenData - 1; nData > 0; nData = nData - 4) {
            aCreatedDataImage[nData] = 255;
        }
        nLenPixels = aDataImage.height * aDataImage.width;
        for (nRow = aDataImage.height; nRow--;) {
            for (nColumn = aDataImage.width; nColumn--;) {
                nX = 4 * (nRow * nWidth + nColumn);
                nY = 4 * (nRow * aDataImage.width + nColumn);
                nRed = aData[nY + 0];
                nGreen = aData[nY + 1];
                nBlue = aData[nY + 2];
                nAlpha = aData[nY + 3];
                nLastRed = aLastData[nY + 0];
                nLastGreen = aLastData[nY + 1];
                nLastBlue = aLastData[nY + 2];
                nLastAlpha = aLastData[nY + 3];

                if (nRed === nLastRed && nGreen === nLastGreen && nBlue === nLastBlue && nAlpha === nLastAlpha) {
                    //aCreatedDataImage[nX + 0] = Math.abs(nRed - nLastRed); // r
                    //aCreatedDataImage[nX + 1] = Math.abs(nGreen - nLastGreen); // g
                    //aCreatedDataImage[nX + 2] = Math.abs(nBlue - nLastBlue); // b
                    aCreatedDataImage[nX + 3] = Math.abs(nAlpha - nLastAlpha); // a
                } else {
                    nDiffPixels++;
                    aCreatedDataImage[nX + 0] = aData[nY + 0]; // r
                    //aCreatedDataImage[nX + 1] = aData[nY + 1]; // g
                    //aCreatedDataImage[nX + 2] = aData[nY + 2]; // b
                    //aCreatedDataImage[nX + 3] = aData[nY + 3]; // a
                }

            }
        }

        oContext.putImageData(oDataImage, 0, 0);
        nDiffPercentage = Math.abs((((nDiffPixels - nLenPixels) / nLenPixels) * 100));
        return {
            percentage: parseFloat(nDiffPercentage),
            canvas: oCanvas
        };
    }

    global.compareCanvas = compareCanvas;
}(window, document));

