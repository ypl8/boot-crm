<%--
  Created by IntelliJ IDEA.
  User: ypl
  Date: 2019/10/9
  Time: 12:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="itcast" uri="http://itcast.cn/common/" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>资源管理系统</title>

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
    <link href="${pageContext.request.contextPath}/css/bootstrap-datetimepicker.css" rel="stylesheet"/>
    <%--<link href="<%=basePath%>/bootstramp/bootstrap/css/bootstrap.min.css" rel="stylesheet">--%>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>

<body>

<div id="wrapper">



    <div id="page-wrapper">
        <div class="row">
            <div class="col-lg-12">
                <h1 class="page-header">资产汇总管理</h1>
            </div>
            <!-- /.col-lg-12 -->
        </div>
        <!-- /.row -->
        <div class="panel panel-default">
            <div class="panel-body">
                <form class="form-inline" action="${pageContext.request.contextPath }/assertInfol/listCommunity.action"
                      method="get">

                    <div class="form-group">
                        <label for="community_name">小区名称</label>
                        <input type="text" class="form-control" id="community_name" value="${community_name}" name="community_name">
                    </div>

                    <button type="submit" class="btn btn-primary">查询</button>
                    <%--<a href="#" class="btn btn-primary" data-toggle="modal"
                       data-target="#newTotalRentalDialog" onclick="clearTotalRental()">新建</a>--%>
                </form>
            </div>
        </div>


    <div class="row">
            <div class="col-lg-12">
                <div class="panel panel-default">
                    <div class="panel-heading">资产统计信息列表</div>
                    <table class="table table-bordered table-striped">
                        <thead>
                        <tr>
                            <th>小区编号</th>
                            <th>资产总数</th>
                            <th>资产出租个数</th>
                            <th>资产空闲个数</th>
                            <th>资产非法占用</th>
                            <th>出租率</th>
                          <%--  <th>操作</th>--%>
                        </tr>
                        </thead>
                        <tbody align="center">
                        <c:forEach items="${page.rows}" var="row">
                            <tr>

                                <td>${row.community_name}</td>
                                <td>${row.countSum}</td>
                                <td>${row.countRented}</td>
                                <td>${row.countUnrent}</td>
                                <td>${row.countOccupy}</td>
                                <td>${row.assertRate}</td>
                               <%-- <td>
                                    <a href="#" class="btn btn-primary btn-xs" data-toggle="modal"
                                       data-target="#EditTotalRentalDialog" onclick="editTotalRental(${row.id})">修改</a>
                                    <a href="#" class="btn btn-danger btn-xs"
                                       onclick="deleteTotalRental(${row.id})">删除</a>
                                </td>--%>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                    <div class="col-md-12 text-right">
                        <itcast:page url="${pageContext.request.contextPath }/assertInfol/listCommunity.action"/>
                    </div>
                    <!-- /.panel-body -->
                </div>
                <!-- /.panel -->
            </div>
            <!-- /.col-lg-12 -->
        </div>
    </div>
    <!-- /#page-wrapper -->





</div>


<!-- /#wrapper -->


<!-- jQuery -->
<script src="<%=basePath%>js/jquery.min.js"></script>

<!-- Bootstrap Core JavaScript -->
<script src="<%=basePath%>js/bootstrap.min.js"></script>

<!-- Metis Menu Plugin JavaScript -->
<script src="<%=basePath%>js/metisMenu.min.js"></script>

<!-- DataTables JavaScript -->
<script src="<%=basePath%>js/jquery.dataTables.min.js"></script>
<script src="<%=basePath%>js/dataTables.bootstrap.min.js"></script>

<!-- Custom Theme JavaScript -->
<script src="<%=basePath%>js/sb-admin-2.js"></script>

<script type="text/javascript">


</script>


<script type="text/javascript" src="${pageContext.request.contextPath}/bootstramp/bootstrap/js/bootstrap.min.js"
        charset="UTF-8"></script>
<%--<script type="text/javascript" src="${pageContext.request.contextPath}/bootstramp/jquery/jquery-1.8.3.min.js"></script>--%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap-datetimepicker.js"
        charset="UTF-8"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/locales/bootstrap-datetimepicker.zh-CN.js"
        charset="UTF-8"></script>

<script type="text/javascript">
  /*  //选择年月的
    $('#year_months').datetimepicker({
        format: 'yyyymm',
        weekStart: 1,
        autoclose: true,
        startView: 3,
        minView: 3,
        forceParse: false,
        language: 'zh-CN'
    });

    //选择年月的
    $('#years').datetimepicker({
        format: 'yyyy',
        weekStart: 1,
        autoclose: true,
        startView: 'decade',
        minView: 'decade',
        maxView:'decade',
        forceParse: false,
        language: 'zh-CN'
    });*/


</script>

</body>

</html>
