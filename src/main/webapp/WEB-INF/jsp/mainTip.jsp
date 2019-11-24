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
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="format-detection" content="telephone=no">
    <link rel="stylesheet" href="<%=basePath%>css/oksub.css" media="all"/>
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

<body  class="console console1 ok-body-scroll">

<div class="ok-body home" style="margin-top: 5px">

    <div class="layui-row layui-col-space15">

        <div class="layui-col-xs6 layui-col-md2">
            <a href="${pageContext.request.contextPath}/assertInfol/list.action?floor_state=2" target="main">
            <div class="ok-card layui-card">
                <div class="ok-card-body p0 clearfix cart-data">
                    <div class="data-body">
                        <div class="media-cont">
                            <p class="tit">空闲资产</p>
                            <h5 class="num">${empty_assert_num}</h5>
                        </div>
                        <div class="w-img" ok-pc-in-show>
                            <img src="<%=basePath%>images/home-02.png" alt="空闲资产"/>
                        </div>
                    </div>
                    <div id="echIncome" class="line-home-a"></div>
                </div>
            </div>
            </a>
        </div>



            <div class="layui-col-xs6 layui-col-md2">
                <a href="${pageContext.request.contextPath}/propertyLeasing/queryList.action?rentalState=6" target="main">
                <div class="ok-card layui-card">
                    <div class="ok-card-body p0 clearfix cart-data">
                        <div class="data-body">
                            <div class="media-cont">
                                <p class="tit">租金未缴清</p>
                                <h5 class="num">${empty_rental_num}</h5>
                            </div>
                            <div class="w-img" ok-pc-in-show>
                                <img src="<%=basePath%>images/home-03.png" alt="租金未缴清"/>
                            </div>
                        </div>
                        <div id="echGoods" class="line-home-a"></div>
                    </div>
                </div>
                </a>
            </div>


        <div class="layui-col-xs6 layui-col-md2">
            <a href="${pageContext.request.contextPath}/propertyLeasing/queryList.action?estateState=8" target="main">
            <div class="ok-card layui-card">
                <div class="ok-card-body p0 clearfix cart-data">
                    <div class="data-body">
                        <div class="media-cont">
                            <p class="tit">物业未缴清</p>
                            <h5 class="num">${empty_estate_num}</h5>
                        </div>
                        <div class="w-img" ok-pc-in-show>
                            <img src="<%=basePath%>images/home-03.png" alt="物业未缴清"/>
                        </div>
                    </div>
                    <div id="echBlogs" class="line-home-a"></div>
                </div>
            </div>
            </a>
        </div>



        <div class="layui-col-xs6 layui-col-md2">
            <a href="${pageContext.request.contextPath}/propertyLeasing/queryList.action?waterState=20" target="main">
            <div class="ok-card layui-card">
                <div class="ok-card-body p0 clearfix cart-data">
                    <div class="data-body">
                        <div class="media-cont">
                            <p class="tit">水费未缴清</p>
                            <h5 class="num">${empty_water_num}</h5>
                        </div>
                        <div class="w-img" ok-pc-in-show>
                            <img src="<%=basePath%>images/home-01.png" alt="水费未缴清"/>
                        </div>
                    </div>
                    <div id="echWater" class="line-home-a"></div>
                </div>
            </div>
        </a>
        </div>



        <div class="layui-col-xs6 layui-col-md2">
            <a href="${pageContext.request.contextPath}/propertyLeasing/queryList.action?powerState=22" target="main">
            <div class="ok-card layui-card">
                <div class="ok-card-body p0 clearfix cart-data">
                    <div class="data-body">
                        <div class="media-cont">
                            <p class="tit">电费未缴清</p>
                            <h5 class="num">${empty_power_num}</h5>
                        </div>
                        <div class="w-img" ok-pc-in-show>
                            <img src="<%=basePath%>images/home-01.png" alt="电费未缴清"/>
                        </div>
                    </div>
                    <div id="echPower" class="line-home-a"></div>
                </div>
            </div>
            </a>
        </div>


        <div class="layui-col-xs6 layui-col-md2">
            <a href="${pageContext.request.contextPath}/assertInfol/list.action?status=29" target="main">
            <div class="ok-card layui-card">
                <div class="ok-card-body p0 clearfix cart-data">
                    <div class="data-body">
                        <div class="media-cont">
                            <p class="tit">待审核</p>
                            <h5 class="num">${empty_user_num}</h5>
                        </div>
                        <div class="w-img" ok-pc-in-show>
                            <img src="<%=basePath%>images/home-04.png" alt="待审核"/>
                        </div>
                    </div>
                    <div id="echUser" class="line-home-a"></div>
                </div>
            </div>
            </a>
        </div>

    </div>
</div>

<div id="wrapper" style="margin-top:30px">


    <div id="page-wrapper">
        <%--<div class="row">
            <div class="col-lg-12">
                <h1 class="page-header">欢迎您的光临，查看您需要的信息！</h1>
            </div>
            <!-- /.col-lg-12 -->
        </div>--%>
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
                    <a href="#" class="btn btn-success " data-toggle="modal"
                       data-target="#updatePasswordDialog">修改密码</a>

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

<div class="modal fade" id="updatePasswordDialog" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="myModalLabel4">更新用户密码</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" id="edit_password_form">
                    <input type="hidden" id="new_id" name="id"/>

                    <div class="form-group">
                        <label for="edit_password" class="col-sm-2 control-label">密码</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="edit_password" placeholder="密码"
                                   name="password">
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary" onclick="updatePassword()">保存修改</button>
            </div>
        </div>
    </div>
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
    function updatePassword(id) {

        $.post("<%=basePath%>userInfo/updatePassword.action",
            $("#edit_password_form").serialize(), function (data) {
                if (data.code == 0) {
                    alert(data.msg);
                    window.location.reload();
                } else {
                    alert(data.msg);
                    window.location.reload();
                }
            });
    }
</script>

</body>

</html>
