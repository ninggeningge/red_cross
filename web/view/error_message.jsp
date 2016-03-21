<%--
  Created by IntelliJ IDEA.
  User: ningge
  Date: 16/1/3
  Time: 17:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%  String title =  (String)request.getAttribute("title");
    String message=  (String)request.getAttribute("message");
    String url = (String)request.getAttribute("url");
%>
<html>
<head>
    <title>失败了</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <link href="/view/css/style.css" rel="stylesheet" type="text/css" media="all"/>

</head>
<body>
<div class="wrap">
    <div class="main">
        <h1><%=message%></h1>
        <h3><%=title%></h3>
        <p><span id="error" class="error"></span>秒后跳转</p>

    </div>

</div>


<script type="text/javascript">
    c = 3;
    update();
    setTimeout("jump()", 3000);

    function update(){
        document.getElementById("error").innerHTML = c.toString();
        c--;
        setTimeout("update()", 1000);

    }
    function jump(){
        <% if(url.equals("")){
            url = "window.history.back()";
        }
        else{
            url = "window.location.href= \"" + url + "\"";
        }%>
        <%=url%>
    }
</script>
</body>
</html>