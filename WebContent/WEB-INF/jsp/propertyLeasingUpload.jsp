<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%--<%@ include file="/common/taglib.jsp"%>--%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <%--  	<jsp:include page="/common/css_js.jsp"></jsp:include>--%>
    <title>信息的导入</title>

    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
        <%
            String path = request.getContextPath();
            String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
                    + path + "/";
        %>

        <!-- Bootstrap Core CSS -->
        <link href="<%=basePath%>css/bootstrap.min.css" rel="stylesheet">

        <!-- MetisMenu CSS -->
        <link href="<%=basePath%>css/metisMenu.min.css" rel="stylesheet">

        <!-- DataTables CSS -->
        <link href="<%=basePath%>css/dataTables.bootstrap.css" rel="stylesheet">

        <!-- Custom CSS -->
        <link href="<%=basePath%>css/sb-admin-2.css?id=1" rel="stylesheet">

        <!-- Custom Fonts -->
        <link href="<%=basePath%>css/font-awesome.min.css" rel="stylesheet"
              type="text/css">
        <link href="<%=basePath%>css/boot-crm.css" rel="stylesheet"
              type="text/css">

        <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
        <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
        <![endif]-->
</head>

<body>
<form id="inputForm" action="${pageContext.request.contextPath}/propertyLeasing/uploadPropertyLeasing.action"
      enctype="multipart/form-data" method="post">
    <div class="main-box"  style="padding: 50px">
         <h1>上传文件</h1>
        <div style="margin-top: 20px"><input id="uploadFile" type="file"  name="uploadFile"></div>
        <div class="form-group"  style="margin-top: 20px">
            <button type="submit" class="btn btn-primary"  onclick="uploadPropertyLeasing();">上传</button>
            <button class="btn btn-primary" type="button" onclick="javascript:window.history.back()">返回</button>
        </div>

    </div>
</form>
</body>
<script type="text/javascript">
    function checkFileChange() {
        var obj = document.getElementById('uploadFile');
        var filesize = obj.files.length;
        if (filesize == 0) {
            alert("请选择需要上传的csv文件");
            return false;
        }
        return true;
    };

    function uploadPropertyLeasing() {
        if (checkFileChange()) {
            $("#inputForm").submit();
        }


    }
</script>

</html>
