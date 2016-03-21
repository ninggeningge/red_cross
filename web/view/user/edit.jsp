<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: ningge
  Date: 16/1/4
  Time: 10:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="/view/header.jsp" />
<style>
    label{
        font-family: "Helvetica Neue",Helvetica,Arial,sans-serif;
    }
</style>
<jsp:include page="/view/menu.jsp" />
<%

    //判断是否登录
    if(!common.CommonFunctions.checkLogin(request)){
        common.CommonFunctions.error(request,response,"","您还未登录","/view/login.html");
    }
%>

<div class="row">
    <div class="col-md-12 center ">
        <h3>修改用户</h3>
    </div>
    <div class="well col-md-12 ">
        <form role="form" action="./user" method="post">
            <div class="form-group  col-md-12" >
                <label class="control-label" for="Username"><h5>用户名</h5></label>
                <input type="text" name="username" value="<c:out value="${user.username}" > </c:out>" class="form-control" id="Username" placeholder="用户名">
            </div>
            <div class="form-group col-md-12">
                <label class="control-label"for="exampleInputPassword1"><h5>密码</h5></label>
                <input type="password" name="password" class="form-control" id="exampleInputPassword1" placeholder="密码">
            </div>

            <div class="form-group  col-md-12">
                <label class="control-label"for="iphone-toggle"><h5>是否准许登录</h5></label>
                <input data-no-uniform="true" name="canLogin" type="checkbox" id="iphone-toggle"class="iphone-toggle">
            </div>
            <input type="hidden" name="complete" value="true">
            <input type="hidden" name="action" value="edit">
            <input type="hidden" name="canLogin" value="false">
            <p class="center col-md-5">
                <button type="submit" class="btn btn-primary">提交</button>
            </p>
        </form>

    </div>
    <!--/span-->
</div><!--/row-->
<jsp:include page="/view/footer.jsp" />