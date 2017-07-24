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
        <main class="container-fluid">
            <a href="#list-randomNumbersStorage" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
            <div class="nav" role="navigation">
                <ul>
                    <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                    <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
                </ul>
            </div>
            <div class="row">
                <script>
                    var list = ${typeList.randomNumbersList};

                    Array.prototype.max = function() {
                        return Math.max.apply(null, this);
                    };

                    Array.prototype.min = function() {
                        return Math.min.apply(null, this);
                    };

                    Array.prototype.stanDeviate = function(){
                        var i, j, total = 0, mean = 0, diffSqredArr = [];
                        for(i = 0; i < this.length; i += 1) {
                            total += this[i];
                        }
                        mean = total/this.length;
                        for(j = 0; j < this.length; j += 1) {
                            diffSqredArr.push(Math.pow((this[j]-mean),2));
                        }
                        return (Math.sqrt(diffSqredArr.reduce(function(firstEl, nextEl) {
                            return firstEl + nextEl;
                        }) / this.length));
                    };

                    // Translated from php function https://stackoverflow.com/questions/24048879/how-can-i-calculate-the-nth-percentile-from-an-array-of-doubles-in-php
                    Array.prototype.getPercentile = function(percentile) {
                        this.sort()
                        var index = (percentile/100) * this.length;
                        if (Math.floor(index) == index) {
                            var result = (this[index - 1] + this[index]) / 2;
                        } else {
                            result = this.push(Math.floor(index));
                        }
                        return result;
                    };

                    var minVal = Math.min.apply(null, list);
                    var maxVal = Math.max.apply(null, list);
                    defCanvasWidth = 1200;
                    defCanvasHeight = 600;
                    var mydata1 = {
                        labels: [
                            minVal,
                            list.getPercentile(16),
                            list.getPercentile(33),
                            list.getPercentile(50),
                            list.getPercentile(66),
                            list.getPercentile(83),
                            maxVal
                        ],
                        xBegin: minVal,
                        xEnd: maxVal,
                        datasets: [
                            {
                                strokeColor : "rgba(220,220,220,1)",
                                data : list,
                                xPos : [],
                                title : "Gauss"
                            }
                        ]
                    }
                    var gauss_var = list.stanDeviate();
                    var numbersSum = list.reduce(function(a, b) { return a + b; });
                    var gauss_mean = numbersSum / list.length;

                    var nbiter = 200;
                    for(var i = 0; i < nbiter; i++) {
                        mydata1.datasets[0].xPos[i] = mydata1.xBegin + i * (mydata1.xEnd - mydata1.xBegin) / nbiter;
                        mydata1.datasets[0].data[i] = (1 / (gauss_var * Math.sqrt(2 * Math.PI))) * Math.exp( -((mydata1.datasets[0].xPos[i] - gauss_mean) * (mydata1.datasets[0].xPos[i] - gauss_mean)) / (2 * gauss_var));
                    }

                    var opt1 = {
                        canvasBorders: true,
                        canvasBordersWidth: 3,
                        canvasBordersColor: "black",
                        graphTitle: "Gauss Function (Seed: ${typeList.sumResult})",
                        legend: true,
                        datasetFill: false,
                        annotateDisplay: true,
                        pointDot: false,
                        animationLeftToRight: true,
                        animationEasing: "linear",
                        yAxisMinimumInterval: 0.02,
                        graphTitleFontSize: 18
                    }

                    document.write("<canvas id=\"canvas_Line1\" height=\""+defCanvasHeight+"\" width=\""+defCanvasWidth+"\"></canvas>");
                    window.onload = function() {
                        var myLine = new Chart(
                            document.getElementById("canvas_Line1").getContext("2d")
                        ).Line(mydata1, opt1);
                        $("#std-deviation").text("Standard deviation: " + gauss_var);
                        $("#average").text("Average: " + gauss_mean);
                    }
                </script>
                <div id="graph-info">
                    <div class="well">
                        <span id="std-deviation" ></span>
                    </div>
                    <div class="well">
                        <span id="average"></span>
                    </div>
                </div>
                <table class="table table-striped">
                    <thead>
                        <tr>
                            <th class="text-center">Random numbers</th>
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${typeList.randomNumbersList}">
                        <tr>
                            <td class="text-center">${it}</td>
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
        </main>
    </body>
</html>