<%--
  Created by IntelliJ IDEA.
  User: ningge
  Date: 16/1/3
  Time: 21:17
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

<script type="text/javascript">
    $(document).ready(function(){
        $("#form").submit(function(){
            var username = $("#Username").val();
            var password = $("#exampleInputPassword1").val();
            var reg = new RegExp("^[a-zA-Z0-9_\u4e00-\u9fa5]+$");
            if(username.length < 3){
                swal("出错啦","用户名太短可不行","error");
                return false;
            }
            if(!reg.test(username)){
                swal("出错啦","用户名格式有问题呃","error");
                return false;
            }
            if(password.length < 6){
                swal("出错啦","密码太短更不行","error");
                return false;
            }

        })
    })

</script>

<div class="row">
    <div class="col-md-12 center ">
        <h3>添加用户</h3>
    </div>
    <div class="well col-md-12 ">
        <form role="form" action="./user" method="post" id="form">
            <div class="form-group  col-md-12" >
                <label class="control-label" for="Username"><h5>用户名
                    <a href="#" title="" data-toggle="tooltip"   data-placement="right"
                       data-original-title="只含有汉字、数字、字母、下划线，下划线位置不限,长度大于2">
                        <i class="glyphicon glyphicon-info-sign"></i></a></h5></label>
                <input type="text" name="username" class="form-control" id="Username" placeholder="用户名">
            </div>
            <div class="form-group col-md-12">
                <label class="control-label" for="exampleInputPassword1"><h5>密码
                    <a href="#" title="" data-toggle="tooltip"   data-placement="right"
                       data-original-title="长度不小于6位">
                        <i class="glyphicon glyphicon-info-sign"></i></a></h5></label>
                <input type="password" name="password" class="form-control" id="exampleInputPassword1" placeholder="密码">
            </div>

            <div class="form-group  col-md-12">
                <label class="control-label"for="iphone-toggle"><h5>是否准许登录</h5></label>
                <input data-no-uniform="true" name="canLogin" type="checkbox" id="iphone-toggle"class="iphone-toggle">
            </div>
            <input type="hidden" name="complete" value="true">
            <input type="hidden" name="action" value="add">
            <input type="hidden" name="canLogin" value="false">
            <p class="center col-md-5">
                <button type="submit" class="btn btn-primary">提交</button>
            </p>

        </form>

    </div>
    <!--/span-->
</div><!--/row-->
<jsp:include page="/view/footer.jsp" />