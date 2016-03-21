<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="model.City" %>
<%@ page import="model.MyConnection" %><%--
  Created by IntelliJ IDEA.
  User: ningge
  Date: 16/1/11
  Time: 19:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/view/header.jsp" />
<style>
    label{
        font-family: "Helvetica Neue",Helvetica,Arial,sans-serif;
    }
</style>
<%
    request.setCharacterEncoding("UTF-8");

    //判断是否登录
    if(!common.CommonFunctions.checkLogin(request)){
        common.CommonFunctions.error(request,response,"","您还未登录","/view/login.html");
    }
%>
<jsp:include page="/view/menu.jsp" />
<div class="row">
    <div class="col-md-12 center ">
        <h3>捐款详情</h3>
    </div>
    <div class="well col-md-12 ">
        <form role="form" action="./donation" method="post">
            <div class="form-group  col-md-12" >
                <label class="control-label" for="Username"><h5>捐款人</h5></label>
                <input type="text" name="person"value="${donation.name}" class="form-control" id="Username" placeholder="捐款人">
            </div>
            <div class="form-group  col-md-12" >
                <label class="control-label" for="province"><h5>省份</h5></label>
                <input type="text" name="amount" value="${donation.province}" class="form-control" id="province" placeholder="捐款金额">
            </div>
            <div class="form-group  col-md-12" >
                <label class="control-label" for="city"><h5>城市</h5></label>
                <input type="text" name="amount" value="${donation.city}" class="form-control" id="city" placeholder="捐款金额">
            </div>
            <div class="form-group  col-md-12" >
                <label class="control-label" for="amount"><h5>捐款金额</h5></label>
                <input type="number" name="amount" value="${donation.amount}" class="form-control" id="amount" placeholder="捐款金额">
            </div>
            <div class="form-group  col-md-12" >
                <label class="control-label" for="time"><h5>捐款时间</h5></label>
                <input type="text" name="amount" value="${donation.time}" class="form-control" id="time" placeholder="捐款金额">
            </div>
            <input type="hidden" name="complete" value="true">
            <input type="hidden" name="action" value="view">
            <p class="center col-md-5">
                <button type="submit" class="btn btn-primary">确认</button>
            </p>

        </form>

    </div>
    <!--/span-->
</div><!--/row-->
<jsp:include page="/view/footer.jsp" />