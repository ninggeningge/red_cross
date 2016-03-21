<%--
  Created by IntelliJ IDEA.
  User: ningge
  Date: 16/1/3
  Time: 15:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="header.jsp" />
<jsp:include page="menu.jsp" />
<%

    //判断是否登录
    if(!common.CommonFunctions.checkLogin(request)){
        common.CommonFunctions.error(request,response,"","您还未登录","/view/login.html");
    }
%>

<div class="row">
    <div class="col-md-12 center login-header">
        <h2>欢迎进入管理后台</h2>
    </div>
    <!--/span-->
</div><!--/row-->
<jsp:include page="footer.jsp" />