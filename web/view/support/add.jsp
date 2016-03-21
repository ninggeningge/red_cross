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
        $("#form").submit(function(){
                var idNum = $("#idNum").val();
                var amount = $("#amount").val();
                var name = $("#name").val();
            if(name == "" || idNum == "" || amount == ""){
                swal("出错啦","必须完整输入才能提交","error");
                return false;
            }
                if(!IdentityCodeValid(idNum)){
                    swal("出错啦","身份证输错啦","error");
                    return false;
                }
                if(amount < 0){
                    swal("出错啦","金额小于0,这可不行啊","error");
                    return false;
                }

        });
    })
    function IdentityCodeValid(code) {
        var city={11:"北京",12:"天津",13:"河北",14:"山西",15:"内蒙古",21:"辽宁",22:"吉林",23:"黑龙江 ",31:"上海",32:"江苏",33:"浙江",34:"安徽",35:"福建",36:"江西",37:"山东",41:"河南",42:"湖北 ",43:"湖南",44:"广东",45:"广西",46:"海南",50:"重庆",51:"四川",52:"贵州",53:"云南",54:"西藏 ",61:"陕西",62:"甘肃",63:"青海",64:"宁夏",65:"新疆",71:"台湾",81:"香港",82:"澳门",91:"国外 "};
        var pass= true;


        if(!code || !/^\d{6}(18|19|20)?\d{2}(0[1-9]|1[12])(0[1-9]|[12]\d|3[01])\d{3}(\d|X)$/i.test(code)){
        pass = false;
    }

    else if(!city[code.substr(0,2)]){
        pass = false;
    }
    else{
        //18位身份证需要验证最后一位校验位
        if(code.length == 18){
            code = code.split('');
            //∑(ai×Wi)(mod 11)
            //加权因子
            var factor = [ 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2 ];
            //校验位
            var parity = [ 1, 0, 'X', 9, 8, 7, 6, 5, 4, 3, 2 ];
            var sum = 0;
            var ai = 0;
            var wi = 0;
            for (var i = 0; i < 17; i++)
            {
                ai = code[i];
                wi = factor[i];
                sum += ai * wi;
            }
            var last = parity[sum % 11];
            if(parity[sum % 11] != code[17]){
                pass =false;
            }
        }
    }
    return pass;
    }
</script>

<div class="row">
    <div class="col-md-12 center ">
        <h3>添加资助</h3>
    </div>
    <div class="well col-md-12 ">
        <form role="form" action="./support" id="form" method="post">
            <div class="form-group  col-md-12" >
                <label class="control-label" for="idNum"><h5>受捐款人身份证
                    <a href="#" title="" data-toggle="tooltip"   data-placement="right"
                       data-original-title="仅允许18位身份证号">
                        <i class="glyphicon glyphicon-info-sign"></i></a></h5></label>
                <input type="text" name="idNum"  data-toggle="tooltip" data-original-title="仅允许18位身份证号"
                       class="form-control" id="idNum" placeholder="受捐款人身份证">

            </div>
            <div class="form-group  col-md-12" >
                <label class="control-label" for="name"><h5>受捐款人姓名</h5></label>
                <input type="text" name="name" class="form-control" id="name" placeholder="受捐款人姓名">
            </div>
            <div class="form-group  col-md-12" >
                <label class="control-label" for="amount"><h5>捐款金额
                    <a href="#" title="" data-toggle="tooltip"   data-placement="right"
                       data-original-title="我猜这是大于零的数">
                    <i class="glyphicon glyphicon-info-sign"></i></a></h5></label>
                <input type="number" name="amount" class="form-control" id="amount" placeholder="捐款金额">
            </div>
            <input type="hidden" name="complete" value="true">
            <input type="hidden" name="action" value="add">
            <p class="center col-md-5">
                <button type="submit" class="btn btn-primary">提交</button>
            </p>

        </form>

    </div>
    <!--/span-->
</div><!--/row-->
<jsp:include page="/view/footer.jsp" />