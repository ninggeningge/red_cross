<%--
  Created by IntelliJ IDEA.
  User: ningge
  Date: 16/1/3
  Time: 15:35
  To change this template use File | Settings | File Templates.
--%>
<%
    //判断是否登录
    if(!common.CommonFunctions.checkLogin(request)){
        common.CommonFunctions.error(request,response,"","您还未登录","/view/login.html");
    }
%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="ch-container">
    <div class="row">

        <!-- left menu starts -->
        <div class="col-sm-2 col-lg-2">
            <div class="sidebar-nav">
                <div class="nav-canvas">
                    <div class="nav-sm nav nav-stacked">

                    </div>
                    <ul class="nav nav-pills nav-stacked main-menu">
                        <li class="nav-header">菜单</li>
                        <li><a class="ajax-link" href="index.jsp"><i class="glyphicon glyphicon-home"></i>
                            <span>主页</span></a>
                        </li>
                        <% String username = (String)request.getSession().getAttribute("username");
                        if(username.equals("admin")){%>

                        <li class="accordion"><a href="#"><i class="glyphicon glyphicon-eye-open"></i>
                            <span>用户管理</span></a>
                            <ul class="nav nav-pills nav-stacked">
                                <li><a href="/user?action=add">添加用户</a></li>
                                <li><a href="/user?action=list">用户列表</a></li>
                            </ul>
                        </li>
                        <%}%>
                        <li class="accordion"><a class="ajax-link" href="#"><i class="glyphicon glyphicon-list-alt"></i>
                            <span>捐款管理</span></a>
                            <ul class="nav nav-pills nav-stacked">
                                <li><a href="/donation?action=add">添加接收</a></li>
                                <li><a href="/donation?action=list">接收列表</a></li>
                            </ul>
                        </li>
                        <li class="accordion"><a class="ajax-link" href="#"><i class="glyphicon glyphicon-tasks"></i>
                            <span>资助管理</span></a>
                            <ul class="nav nav-pills nav-stacked">
                                <li><a href="/support?action=add">添加资助</a></li>
                                <li><a href="/support?action=list">资助列表</a></li>
                            </ul>
                        </li>

                    </ul>
                </div>
            </div>
        </div>
        <!--/span-->
        <!-- left menu ends -->

        <noscript>
            <div class="alert alert-block col-md-12">
                <h4 class="alert-heading">Warning!</h4>

                <p>You need to have <a href="http://en.wikipedia.org/wiki/JavaScript" target="_blank">JavaScript</a>
                    enabled to use this site.</p>
            </div>
        </noscript>

        <div id="content" class="col-lg-10 col-sm-10">
            <!-- content starts -->

