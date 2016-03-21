<%--
  Created by IntelliJ IDEA.
  User: ningge
  Date: 16/1/3
  Time: 15:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% String username = (String)request.getSession().getAttribute("username"); %>
<!DOCTYPE html>
<html>
<head>
    <!--
        ===
        This comment should NOT be removed.

        Charisma v2.0.0

        Copyright 2012-2014 Muhammad Usman
        Licensed under the Apache License v2.0
        http://www.apache.org/licenses/LICENSE-2.0

        http://usman.it
        http://twitter.com/halalit_usman
        ===
    -->
    <meta charset="utf-8">
    <title>红十字会捐款管理系统后台</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The styles -->
    <link id="bs-css" href="/view/css/bootstrap-cerulean.min.css" rel="stylesheet">

    <link href="/view/css/charisma-app.css" rel="stylesheet">
    <link href='/view/bower_components/fullcalendar/dist/fullcalendar.css' rel='stylesheet'>
    <link href='/view/bower_components/fullcalendar/dist/fullcalendar.print.css' rel='stylesheet' media='print'>
    <link href='/view/bower_components/chosen/chosen.min.css' rel='stylesheet'>
    <link href='/view/bower_components/colorbox/example3/colorbox.css' rel='stylesheet'>
    <link href='/view/bower_components/responsive-tables/responsive-tables.css' rel='stylesheet'>
    <link href='/view/bower_components/bootstrap-tour/build/css/bootstrap-tour.min.css' rel='stylesheet'>
    <link href='/view/css/jquery.noty.css' rel='stylesheet'>
    <link href='/view/css/noty_theme_default.css' rel='stylesheet'>
    <link href='/view/css/elfinder.min.css' rel='stylesheet'>
    <link href='/view/css/elfinder.theme.css' rel='stylesheet'>
    <link href='/view/css/jquery.iphone.toggle.css' rel='stylesheet'>
    <link href='/view/css/uploadify.css' rel='stylesheet'>
    <link href='/view/css/animate.min.css' rel='stylesheet'>

    <!-- jQuery -->
    <script src="/view/bower_components/jquery/jquery.js"></script>

    <!-- The HTML5 shim, for IE6-8 support of HTML5 elements -->
    <!--[if lt IE 9]>
    <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->

    <!-- The fav icon -->
    <link rel="shortcut icon" href="/view/img/red_cross.png">
    <script src="/view/js/sweetalert.min.js"></script>
    <link rel="stylesheet" href="/view/css/sweetalert.css">

</head>
<body>
<!-- topbar starts -->
<div class="navbar navbar-default" role="navigation">

    <div class="navbar-inner">
       <a class="navbar-brand" href="/index.jsp"> <img alt="Charisma Logo" src="/view/img/red_cross.png" class="hidden-xs"/>
             <span>RedCross</span></a>

        <!-- user dropdown starts -->
        <div class="btn-group pull-right">
            <button class="btn btn-default dropdown-toggle" data-toggle="dropdown">
                <i class="glyphicon glyphicon-user"></i><span class="hidden-sm hidden-xs">
                欢迎您:<%=username%></span>
                <span class="caret"></span>
            </button>
            <ul class="dropdown-menu">
                <li><a href="/login?action=logout">Logout</a></li>
            </ul>
        </div>
        <!-- user dropdown ends -->

        </ul>

    </div>
</div>
<!-- topbar ends -->
