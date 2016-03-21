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
<jsp:include page="/view/menu.jsp" />
<%
    request.setCharacterEncoding("UTF-8");

    //判断是否登录
    if(!common.CommonFunctions.checkLogin(request)){
        common.CommonFunctions.error(request,response,"","您还未登录","/view/login.html");
    }
%>

<script type="text/javascript">
    $(document).ready(function(){
        var last = 0;
        var now;
        $("#selectProvince").change(function(){
            now = $.trim($(this).find("option:selected").val());
            if(last != 0) {
                $(".controls" + last).hide();
            }
            else{
                $(".controls0").hide();
            }
            $(".controls" + now).show();
            $("#selectCity" + now + "_chosen").width(145);
            last = now;
            $("select[name=city]").find("option:selected").val($("#selectCity" + now).val());
        })


    })
</script>

<div class="row">
    <div class="col-md-12 center ">
        <h3>添加资助</h3>
    </div>
    <div class="well col-md-12 ">
        <form role="form" action="./support" method="post">
            <div class="form-group  col-md-12" >
                <label class="control-label" for="idNum"><h5>受捐款人身份证
                    <a href="#" title="" data-toggle="tooltip"   data-placement="right"
                       data-original-title="仅允许18位身份证号">
                        <i class="glyphicon glyphicon-info-sign"></i></a></h5></label>
                <input type="text" name="idNum"  value="${support.idnum}"data-toggle="tooltip" data-original-title="仅允许18位身份证号"
                       class="form-control" id="idNum" placeholder="受捐款人身份证">

            </div>
            <div class="form-group  col-md-12" >
                <label class="control-label" for="name"><h5>受捐款人姓名</h5></label>
                <input type="text" name="name" value="${support.name}"class="form-control" id="name" placeholder="受捐款人姓名">
            </div>
            <div class="form-group  col-md-12" >
                <label class="control-label" for="amount"><h5>捐款金额</h5></label>
                <input type="number" name="amount" value="${support.amount}" class="form-control" id="amount" placeholder="捐款金额">
            </div>
            <div class="form-group  col-md-12" >
                <label class="control-label" for="time"><h5>捐款时间</h5></label>
                <input type="text" name="amount" value="${support.time}" class="form-control" id="time" placeholder="捐款时间">
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