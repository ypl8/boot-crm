<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="Pragma" content="no-cache">
    <meta http-equiv="Cache-Control" content="no-cache">
    <meta http-equiv="Expires" content="0">
    <script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
    <title>后台管理</title>
    <link href="${pageContext.request.contextPath}/css/login.css" rel="stylesheet" type="text/css"/>
    <script>
        // 判断是登录账号和密码是否为空
        function check() {
            var userName = $("#userName").val();
            var password = $("#password").val();
            if (userName == "" || password == "") {
                $("#message").text("账号或密码不能为空！");
                return false;
            }
            return true;
        }
    </script>

</head>

<body>
<div class="login_box">
    <div class="login_l_img"><img src="${pageContext.request.contextPath}/images/login-img.png"/></div>
    <div class="login">
        <div class="login_logo"><a href="#"><img src="${pageContext.request.contextPath}/images/login_logo.png"/></a>
        </div>
        <div class="login_name">
            <p>资产信息管理系统</p>
        </div>
        <form method="post" action="${pageContext.request.contextPath}/userInfo/login.action" onsubmit="return check()">
            <input name="userName" id="userName" type="text" value="用户名" onfocus="this.value=''"
                   onblur="if(this.value==''){this.value='用户名'}">
            <span id="password_text"
                  onclick="this.style.display='none';document.getElementById('password').style.display='block';">密码</span>
            <input name="password" type="password" id="password" style="display:none;"
                   onblur="if(this.value==''){document.getElementById('password_text').style.display='block';this.style.display='none'};"/>
            <input value="登录" style="width:100%;" type="submit">
            <span>${msg}</span>
        </form>
    </div>
    <div class="copyright" style="margin-bottom: 100px">赣州市建控投资股份有限公司版权所有©2019-2021 </div>
</div>
</body>
</html>
