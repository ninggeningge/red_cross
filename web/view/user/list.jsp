<%--
  Created by IntelliJ IDEA.
  User: ningge
  Date: 16/1/3
  Time: 22:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/view/header.jsp" />
<jsp:include page="/view/menu.jsp" />
<%

    //判断是否登录
    if(!common.CommonFunctions.checkLogin(request)){
        common.CommonFunctions.error(request,response,"","您还未登录","/view/login.html");
    }
%>
<div class="row">
    <div class="box col-md-12">
        <div class="box-inner">
            <div class="box-header well" data-original-title="">
                <h2><i class="glyphicon glyphicon-user"></i>用户列表</h2>

                <div class="box-icon">
                    <a href="#" class="btn btn-minimize btn-round btn-default"><i
                            class="glyphicon glyphicon-chevron-up"></i></a>

                </div>
            </div>
            <div class="box-content">
                <table class="table table-striped table-bordered bootstrap-datatable datatable responsive">
                    <thead>
                    <tr>
                        <th>用户名</th>
                        <th>添加日期</th>
                        <th>状态</th>
                        <th>操作</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="item" items="${users}"> 
                    <tr>
                        <td><c:out value="${item.username}"></c:out></td>
                        <td><c:out value="${item.addon}"></c:out></td>
                        <td class="center">
                            <c:if test="${item.is_forbid==false}">
                              <span class="label-success label label-default">可登录</span>
                            </c:if>
                            <c:if test="${item.is_forbid==true}">
                                <span class="label-danger label label-default">禁止</span>
                            </c:if>
                        </td>
                        <td class="center">
                            <a class="btn btn-success" href="/user?action=view&id=<c:out value="${item.id}"></c:out>">
                                <i class="glyphicon glyphicon-zoom-in icon-white"></i>
                                View
                            </a>
                            <a class="btn btn-info" href="/user?action=edit&id=<c:out value="${item.id}"></c:out>">
                                <i class="glyphicon glyphicon-edit icon-white"></i>
                                Edit
                            </a>
                            <a class="btn btn-danger" href="/user?action=delete&id=<c:out value="${item.id}"></c:out>">
                                <i class="glyphicon glyphicon-trash icon-white"></i>
                                Delete
                            </a>
                        </td>
                    </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
    <!--/span-->

</div>
<jsp:include page="/view/footer.jsp" />