<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<title><!DOCTYPE html>
<html lang="zh-cmn-Hans">
<head>

	<meta charset="utf-8">
    <!-- 优先使用 IE 最新版本和 Chrome -->
    <meta http-equiv="X-UA-Compatible" content="chrome=1,IE=edge" />
    <!-- 360 使用Google Chrome Frame -->
    <meta name="renderer" content="webkit">
    <!-- 如果安装了 Google Chrome Frame，则使用 GCF 来渲染页面，如果没有安装 GCF，则使用最高版本的 IE 内核进行渲染 -->
    <meta http-equiv="X-UA-Compatible" content="IE=Edge,chrome=1">
    <!-- 百度禁止转码 -->
    <meta http-equiv="Cache-Control" content="no-siteapp" />
    <title>微商城后台</title>
    <meta name ="viewport" content ="initial-scale=1, maximum-scale=3, minimum-scale=1, user-scalable=no"> 
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/response.css">
    	<script type="text/javascript">
		function gotoUrl(url){
		if(url!=null&&""!=url){
		window.location.href="${pageContext.request.contextPath}"+"${url}";
		}else{
		window.history.back();
		}
		}
	</script>
</head>
<body>
<div class="response">
	<div>
		<img src="${pageContext.request.contextPath}/images/ok.png">
		<div class="desc">
			<p class="err">操作成功</p>
			<p><%=request.getAttribute("msg") %></p>
			<button onclick="gotoUrl('${url }');">返回</button>
		</div>
	</div>
</div>
</body>
</html>