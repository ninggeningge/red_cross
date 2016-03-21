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

    Map<Integer, String> provinces = (HashMap<Integer,String>)request.getAttribute("provinces");
    Iterator iterator = provinces.entrySet().iterator();
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

        $("#form").submit(function(){
            var Username = $("#Username").val();
            var amount = $("#amount").val();
            var name = $("#name").val();
            if(Username == "" || amount == ""){
                swal("出错啦","必须完整输入才能提交","error");
                return false;
            }
            if(amount < 0){
                swal("出错啦","金额小于0,这可不行啊","error");
                return false;
            }

        });
    })
</script>

<div class="row">
    <div class="col-md-12 center ">
        <h3>添加捐款</h3>
    </div>
    <div class="well col-md-12 ">
        <form role="form" action="./donation" method="post" id="form">
            <div class="form-group  col-md-12" >
                <label class="control-label" for="Username"><h5>捐款人</h5></label>
                <input type="text" name="person" class="form-control" id="Username" placeholder="捐款人">
            </div>
            <div class="form-group col-md-12">
                <label class="control-label" for="selectProvince"><h5>选择省份</h5></label>

                <div class="controls ">
                    <select id="selectProvince" name="province" data-rel="chosen">
                        <%
                            while (iterator.hasNext()){
                                Map.Entry entry = (Map.Entry)iterator.next();
                                String key = Integer.toString((Integer)entry.getKey());
                                String value = (String)entry.getValue();
                                out.print("<option value=\" "+ key + "\">" + value + "</option>");
                            }
                        %>
                    </select>
                </div>
            </div>

            <div class="form-group  col-md-12 controls0">
                <label class="control-label"  for="selectCity"><h5>选择市（区）</h5></label>
                <div class="controls ">
                <select id="selectCity" name="city" data-rel="chosen">
                    <c:forEach var="item" items="${city}">
                        <option value="${item.id}">${item.name}</option>;
                    </c:forEach>
                </select>
                </div>
            </div>
            <c:forEach var="item" items="${cities}">
                <div class="form-group  col-md-12 controls${item.key}" style="display: none">
                    <label class="control-label"  for="selectCity${item.key}"><h5>选择市（区）</h5></label>
                    <div>
                        <div class="controls"  >
                            <select id="selectCity${item.key}"  data-rel="chosen" >
                                    <c:forEach var="i" items="${item.value}">
                                        <option value="${i.id}">${i.name}</option>;
                                    </c:forEach>
                            </select>
                        </div>
                    </div>
                </div>
            </c:forEach>
            <div class="form-group  col-md-12" >
            <label class="control-label" for="amount"><h5>捐款金额
                <a href="#" title="" data-toggle="tooltip"   data-placement="right"
                   data-original-title="我猜这是大于零的数">
                    <i class="glyphicon glyphicon-info-sign"></i></a></h5></label>
            <input type="number" name="amount" class="form-control" id="amount" placeholder="捐款金额">
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