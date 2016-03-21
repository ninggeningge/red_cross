<%--
  Created by IntelliJ IDEA.
  User: ningge
  Date: 16/1/16
  Time: 00:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!doctype html>
<html lang="en" class="no-js">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link href='http://fonts.googleapis.com/css?family=Droid+Serif|Open+Sans:400,700' rel='stylesheet' type='text/css'>

    <link rel="stylesheet" href="/front/query/css/reset.css"> <!-- CSS reset -->
    <link rel="stylesheet" href="/front/query/css/style.css"> <!-- Resource style -->
    <script src="/view/bower_components/jquery/jquery.min.js"></script>
    <script src="/front/query/Chart.js"></script> <!-- Resource jQuery -->
    <link rel="shortcut icon" href="/view/img/red_cross.png">

    <title>统计信息</title>
    <style>
        #cd-timeline::before {
            /* this is the vertical line */
            content: '';
            position: absolute;
            top: 0;
            left: 18px;
            height: 100%;
            width: 0px !important;
            opacity: 0;
            background: #d7e4ed;
        }

    </style>
</head>
<body>
<header>
    <h1>统计信息</h1>
</header>

<section id="cd-timeline" class="cd-container">
    <div style="width: 90%">
        <canvas id="canvas" height="360" width="900"></canvas>
    </div>


    <script>
        var barChartData = {
            labels : [],
            datasets : [
                {
                    fillColor : "rgba(151,187,205,0.5)",
                    strokeColor : "rgba(151,187,205,0.8)",
                    highlightFill : "rgba(151,187,205,0.75)",
                    highlightStroke : "rgba(151,187,205,1)",
                    data : []
                }
            ]

        }

        $(document).ready(function(){
            var ctx = document.getElementById("canvas").getContext("2d");
            window.myBar = new Chart(ctx).Bar(barChartData, {
                responsive : true
            });

            $.ajax({
                url: "/statistics",
                data :{
                    start : getUrlParam('start'),
                    end : getUrlParam('end'),
                },
                dataType : "json",
                type: "post",
                success : function(data){
                    var result = eval(data);
                    for(var i in result){
                        myBar.addData([result[i]], i);
                    }

                }

            })

        })
        function getUrlParam(name) {
            var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
            var r = window.location.search.substr(1).match(reg);  //匹配目标参数
            if (r != null) return unescape(r[2]); return null; //返回参数值
        }

    </script>

</section> <!-- cd-timeline -->

</body>
</html>
