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

    <title>提示页面</title>

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

<div id="wrapper">


    <div id="page-wrapper">
        <div class="row">
            <div class="col-lg-12">
                <h1 class="page-header">欢迎您的光临，查看您需要的信息！</h1>
            </div>
            <!-- /.col-lg-12 -->
        </div>
        <!-- /.row -->
        <div class="panel panel-default">
            <div class="panel-body">
                <form class="form-inline" action="${pageContext.request.contextPath }/propertyLeasing/queryList.action"
                      method="get">
                    <div class="form-group">
                        <label for="property_leasing_num">合同编号</label>
                        <input type="text" class="form-control" id="property_leasing_num"
                               value="${property_leasing_num }" name="property_leasing_num">
                    </div>

                    <div class="form-group">
                        <label for="depositState">押金到账状态</label>
                        <select class="form-control" id="depositState" placeholder="押金到账状态" name="depositState">
                            <option value="">--请选择--</option>
                            <c:forEach items="${depositStateType}" var="item">
                                <option value="${item.dict_id}"<c:if
                                        test="${item.dict_id == depositState}"> selected</c:if>>${item.dict_item_name }</option>
                            </c:forEach>
                        </select>
                    </div>

                    <div class="form-group">
                        <label for="rentalState">租金到账状态</label>
                        <select class="form-control" id="rentalState" placeholder="租金到账状态" name="rentalState">
                            <option value="">--请选择--</option>
                            <c:forEach items="${rentalStateType}" var="item">
                                <option value="${item.dict_id}"<c:if
                                        test="${item.dict_id == rentalState}"> selected</c:if>>${item.dict_item_name }</option>
                            </c:forEach>
                        </select>
                    </div>


                    <div class="form-group">
                        <label for="estateState">物业费到账状态</label>
                        <select class="form-control" id="estateState" placeholder="物业费到账状态" name="estateState">
                            <option value="">--请选择--</option>
                            <c:forEach items="${estateStateType}" var="item">
                                <option value="${item.dict_id}"<c:if
                                        test="${item.dict_id == estateState}"> selected</c:if>>${item.dict_item_name }</option>
                            </c:forEach>
                        </select>
                    </div>

                    <div class="form-group">
                        <label for="waterState">水费到账状态</label>
                        <select class="form-control" id="waterState" placeholder="水费到账状态" name="waterState">
                            <option value="">--请选择--</option>
                            <c:forEach items="${waterStateType}" var="item">
                                <option value="${item.dict_id}"<c:if
                                        test="${item.dict_id == waterState}"> selected</c:if>>${item.dict_item_name }</option>
                            </c:forEach>
                        </select>
                    </div>

                    <div class="form-group">
                        <label for="powerState">电费到账状态</label>
                        <select class="form-control" id="powerState" placeholder="电费到账状态" name="powerState">
                            <option value="">--请选择--</option>
                            <c:forEach items="${powerStateType}" var="item">
                                <option value="${item.dict_id}"<c:if
                                        test="${item.dict_id == powerState}"> selected</c:if>>${item.dict_item_name }</option>
                            </c:forEach>
                        </select>
                    </div>

                    <button type="submit" class="btn btn-primary">查询</button>

                </form>
            </div>
        </div>


        <div class="row">
            <div class="col-lg-12">
                <div class="panel panel-default">
                    <div class="panel-heading">租金信息列表</div>
                    <!-- /.panel-heading -->
                    <table class="table table-bordered table-striped">
                        <thead>
                        <tr>
                            <th>序号</th>
                            <th>租凭合同编号</th>
                            <th>承租人</th>
                            <th>小区名称</th>
                            <th>租聘位置</th>
                            <th>操作</th>
                        </tr>
                        </thead>
                        <tbody align="center">
                        <c:forEach items="${page.rows}" var="row">
                            <tr>
                                <td>${row.id}</td>
                                <td>${row.property_leasing_num}</td>
                                <td>${row.tenant}</td>
                                <td>${row.community_name}</td>
                                <td>${row.rentalLocation}</td>

                                <td>

                                    <a onclick="return showAssert(${row.id})"
                                       href="${pageContext.request.contextPath}/propertyLeasing/list.action?property_leasing_num=${row.property_leasing_num}"
                                       class="btn  btn-primary btn-xs" target="main">查看</a>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                    <div class="col-md-12 text-right">
                        <itcast:page url="${pageContext.request.contextPath }/propertyLeasing/queryList"/>
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

</body>

</html>
