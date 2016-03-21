<%--
  Created by IntelliJ IDEA.
  User: ningge
  Date: 16/1/12
  Time: 11:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
                <h2><i class="glyphicon glyphicon-user"></i>捐助列表</h2>

                <div class="box-icon">
                    <a href="#" class="btn btn-minimize btn-round btn-default"><i
                            class="glyphicon glyphicon-chevron-up"></i></a>

                </div>
            </div>
            <div class="box-content">
                <table class="table table-striped table-bordered bootstrap-datatable datatable responsive">
                    <thead>
                    <tr>
                        <th>捐款人（单位）</th>
                        <th>省份</th>
                        <th>市（区）</th>
                        <th>捐款金额</th>
                        <th>添加时间</th>
                        <th>操作</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="item" items="${donations}">
                        <tr>
                            <td><c:out value="${item.name}"></c:out></td>
                            <td><c:out value="${item.province}"></c:out></td>
                            <td><c:out value="${item.city}"></c:out></td>
                            <td><c:out value="${item.amount}"></c:out></td>
                            <td><c:out value="${item.time}"></c:out></td>
                            <td class="center">
                                <a class="btn btn-success" href="/donation?action=view&id=<c:out value="${item.id}"></c:out>">
                                    <i class="glyphicon glyphicon-zoom-in icon-white"></i>
                                    View
                                </a>
                                <a class="btn btn-info" href="/donation?action=edit&id=<c:out value="${item.id}"></c:out>">
                                    <i class="glyphicon glyphicon-edit icon-white"></i>
                                    Edit
                                </a>
                                <a class="btn btn-danger" href="/donation?action=delete&id=<c:out value="${item.id}"></c:out>">
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
