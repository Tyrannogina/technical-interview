<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main"/>
        <title>Sara's app</title>

        <link rel="stylesheet" href="${resource(dir: 'css', file: 'main.css')}" type="text/css">

        <asset:javascript src="format.js" />
        <asset:javascript src="newChart.js"/>
    </head>
    <body>
        <h1>GRAPH PAGE</h1>
        <p>Data: </p>
        <script>
            defCanvasWidth = 1200;
            defCanvasHeight = 600;
            var mydata1 = {
                labels: ["-3", -2, -1, "0", 1, 2, "3"],
                xBegin: -3,
                xEnd: 3,
                datasets: [
                    {
                        strokeColor : "rgba(220,220,220,1)",
                        data : ${typeList},
                        xPos : [],
                        title : "Sinus"
                    }
                ]
            }
            var gauss_var = 1;
            var gauss_mean = 0;

            var nbiter = 400;
            for(var i = 0; i < nbiter; i++) {
                mydata1.datasets[0].xPos[i] = mydata1.xBegin + i * (mydata1.xEnd - mydata1.xBegin) / nbiter;
                mydata1.datasets[0].data[i] = (1 / (gauss_var * Math.sqrt(2 * Math.PI))) * Math.exp(-((mydata1.datasets[0].xPos[i] - gauss_mean) * (mydata1.datasets[0].xPos[i] - gauss_mean)) / (2 * gauss_var));
            }
            var opt1 = {
                canvasBorders : true,
                canvasBordersWidth : 3,
                canvasBordersColor : "black",
                graphTitle : "Gauss Function - (0,1)",
                legend : true,
                datasetFill : false,
                annotateDisplay : true,
                pointDot :false,
                animationLeftToRight : true,
                animationEasing: "linear",
                yAxisMinimumInterval : 0.02,
                graphTitleFontSize: 18
            }

            document.write("<canvas id=\"canvas_Line1\" height=\""+defCanvasHeight+"\" width=\""+defCanvasWidth+"\"></canvas>");
            window.onload = function() {
                var myLine = new Chart(
                    document.getElementById("canvas_Line1").getContext("2d")
                ).Line(mydata1, opt1);
            }
        </script>
    </body>
</html>