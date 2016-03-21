<%--
  Created by IntelliJ IDEA.
  User: ningge
  Date: 16/1/15
  Time: 23:07
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
    <script src="/front/query/js/modernizr.js"></script> <!-- Modernizr -->
    <script src="/view/bower_components/jquery/jquery.min.js"></script>
    <title>流水信息</title>
    <style>
        .name{
            color :#2980b9;
        }

        .active{
            color :#2980d9;
        }
    </style>
    <link rel="shortcut icon" href="/view/img/red_cross.png">
    <script type="text/javascript">
        $(document).ready(function(){
            $(".links").click(function(){
                var id = $(this).attr("id");
                if(id == 'timeline'){
                    $("#list").removeClass("active");
                    $("#timeline").addClass("active");
                }
                else{
                    $("#timeline").removeClass("active");
                    $("#list").addClass("active");
                }
            })

            $('#pagination-demo').twbsPagination({
                totalPages: 35,
                visiblePages: 7,
                onPageClick: function (event, page) {
                    $('#page-content').text('Page ' + page);
                }
            });
        })

    </script>
</head>
<body>
<header>
    <h1 style="padding-top: 120px; line-height: 20px">流水信息</h1>
</header>

<%
    String type = request.getParameter("type");
%>
<section id="cd-timeline" class="cd-container">
    <c:forEach var="item" items="${items}">
        <div class="cd-timeline-block">
            <div class="cd-timeline-img cd-picture">
                <img src="/front/query/img/cd-icon-picture.svg" alt="Picture">
            </div> <!-- cd-timeline-img -->

            <div class="cd-timeline-content">
                        <% if(type.equals("1")){%>
                        <h2>捐款</h2>
                        <p>在${item.time}接收到来自${item.province}${item.city}的<span class="name">${item.name}</span>的捐款${item.amount}元</p>
                        <%} else{%>

                        <h2>资助</h2>
                        <p>在${item.time}资助<span class="name">${item.name}</span>${item.amount}元</p>
                        <%}%>
                <span class="cd-date">${item.time}</span>
            </div> <!-- cd-timeline-content -->
        </div> <!-- cd-timeline-block -->
    </c:forEach>
</section> <!-- cd-timeline -->


<script src="/view/bower_components/jquery/jquery.min.js"></script>
<script src="/front/query/js/main.js"></script> <!-- Resource jQuery -->
</body>
</html>