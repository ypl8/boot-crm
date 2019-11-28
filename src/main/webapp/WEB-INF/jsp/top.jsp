<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/security/tags" prefix="security"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>头部</title>
<%-- <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/reset.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/common.css"/>--%>
</head>

<body  style="background: #F9F9F9" >
<div>

    <h1 style="text-align: center; margin-top: 5px; font-size: xx-large" >赣州建控集团资产信息管理系统V1.0</h1>
    <%--<div class="logo"><a href="#"><img src="${pageContext.request.contextPath}/images/mainlogo.png" alt="资源管理系统"/></a>
    </div>--%>
    <div class="copyright" style="font-size: 6px; text-align: center;margin-top: -13px">Copyright&copy;2019赣州喜子软件科技有限公司All Rights Reserved.</div>
    <div class="curr" style="text-align:end;font-size: 12px; margin-top: -10px;"><span>欢迎您，<security:authentication property="principal.username" />[ <a href="${pageContext.request.contextPath }/logout.action" target="_top">退出</a> ]</span></div>
</div>
</body>
</html>
