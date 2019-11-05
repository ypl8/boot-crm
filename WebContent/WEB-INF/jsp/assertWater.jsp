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

    <title>案例列表-BootCRM</title>

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
                <h1 class="page-header">水表度数管理</h1>
            </div>
            <!-- /.col-lg-12 -->
        </div>
        <!-- /.row -->
        <div class="panel panel-default">
            <div class="panel-body">
                <form class="form-inline" action="${pageContext.request.contextPath }/assertWater/list.action"
                      method="get">
                    <div class="form-group">
                        <label for="property_leasing_num">合同编号</label>
                        <input type="text" class="form-control" id="property_leasing_num" value="${property_leasing_num }" name="property_leasing_num">
                    </div>
                    <div class="form-group">
                        <label for="assert_num">资产编号</label>
                        <input type="text" class="form-control" id="assert_num" value="${assert_num }" name="assert_num">
                    </div>

                    <button type="submit" class="btn btn-primary">查询</button>
                    <a href="#" class="btn btn-primary" data-toggle="modal"
                       data-target="#newWaterDialog" onclick="clearWater()">新建</a>
                </form>
            </div>
        </div>


        <div class="row">
            <div class="col-lg-12">
                <div class="panel panel-default">
                    <div class="panel-heading">水表信息列表</div>
                    <!-- /.panel-heading -->
                    <table class="table table-bordered table-striped">
                        <thead>
                        <tr>
                            <th>序号</th>
                            <th>租凭合同编号</th>
                            <th>资产编号</th>
                            <th>水表编号</th>
                            <th>水表度数</th>
                            <th>截止抄表时间</th>
                            <th>操作</th>
                        </tr>
                        </thead>
                        <tbody align="center">
                        <c:forEach items="${page.rows}" var="row">
                            <tr>
                                <td>${row.id}</td>
                                <td>${row.property_leasing_num}</td>
                                <td>${row.assert_num}</td>
                                <td>${row.watermeter_num}</td>
                                <td>${row.water_num}</td>
                                <td><fmt:formatDate value="${row.deadline}" pattern="yyyy-MM-dd"/></td>
                                <td>
                                    <a href="#" class="btn btn-primary btn-xs" data-toggle="modal"
                                       data-target="#EditWaterDialog" onclick="editWater(${row.id})">修改</a>
                                    <a href="#" class="btn btn-danger btn-xs"
                                       onclick="deleteWater(${row.id})">删除</a>
                                    <%--<a href="#" class="btn btn-default btn-xs"     data-toggle="modal"  data-target="#showLeasingDialog"
                                       onclick="showDeposit(${row.id})">查看</a>--%>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                    <div class="col-md-12 text-right">
                        <itcast:page url="${pageContext.request.contextPath }/assertWater/list.action"/>
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

<!-- 水度数修改对话框 -->
<div class="modal fade" id="EditWaterDialog" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="myModalLabel2">更新水表度数信息</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" id="edit_water_form">

                    <input type="hidden" id="edit_id" name="id"/>
                    <div class="form-group">
                        <label for="edit_property_leasing_num" class="col-sm-2 control-label">租凭合同编号</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="edit_property_leasing_num" placeholder="租凭合同编号" name="property_leasing_num">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="edit_assert_num" class="col-sm-2 control-label">资产编号</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="edit_assert_num" placeholder="资产编号"
                                   name="assert_num">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="edit_watermeter_num" class="col-sm-2 control-label">水表编号</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="edit_watermeter_num" placeholder="水表编号"
                                   name="watermeter_num">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="edit_water_num" class="col-sm-2 control-label">水表度数</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="edit_water_num" placeholder="水表度数（吨）"
                                   name="water_num">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="edit_deadline" class="col-sm-2 control-label">截止抄表时间</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="edit_deadline" placeholder="截止抄表时间"
                                   name="deadline">
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary" onclick="updateWater()">保存修改</button>
            </div>
        </div>
    </div>
</div>


<!-- 创建水度数 -->
<div class="modal fade" id="newWaterDialog" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="myModalLabel">添加水表度数信息</h4>
            </div>
            <div class="modal-body">

                <form class="form-horizontal" id="new_water_form">
                    <div class="form-group">
                        <label for="new_property_leasing_num" class="col-sm-2 control-label">
                            租凭合同编号
                        </label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="new_property_leasing_num" placeholder="租凭合同编号" name="property_leasing_num"/>
                        </div>
                    </div>


                    <div class="form-group">
                        <label for="new_assert_num" class="col-sm-2 control-label">资产编号</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="new_assert_num" placeholder="资产编号"
                                   name="assert_num">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="new_watermeter_num" class="col-sm-2 control-label">水表编号</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="new_watermeter_num" placeholder="水表编号"
                                   name="watermeter_num">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="new_water_num" class="col-sm-2 control-label">水表度数</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="new_water_num" placeholder="水表度数（吨）"
                                   name="water_num">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="new_deadline" class="col-sm-2 control-label">截止抄表时间</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="new_deadline" placeholder="截止抄表时间"
                                   name="deadline">
                        </div>
                    </div>

                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary" onclick="createWater()">创建</button>
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


    function editWater(id) {
        $.ajax({
            type: "get",
            url: "<%=basePath%>assertWater/edit.action",
            data: {"id": id},
            success: function (data) {
                $("#edit_id").val(data.id);
                $("#edit_property_leasing_num").val(data.property_leasing_num);
                $("#edit_assert_num").val(data.assert_num);
                $("#edit_deadline").val(data.deadline);
                $("#edit_water_num").val(data.water_num);
                $("#edit_watermeter_num").val(data.watermeter_num);
            }
        });
    }



    function updateWater() {
        $.post("<%=basePath%>assertWater/update.action", $("#edit_water_form").serialize(), function (data) {
            if(data.code==0){
                alert(data.msg);
                window.location.reload();
            }else{
                alert(data.msg);
                window.location.reload();
            }
        });
    }

    function deleteWater(id) {
        if (confirm('确实要删除水表度数信息吗?')) {
            $.post("<%=basePath%>assertWater/delete.action", {"id": id}, function (data) {
                    alert("水表度数信息删除成功！");
                    window.location.reload();

            });
        }
    }

    function clearWater() {
        $("#new_property_leasing_num").val("");
        $("#new_assert_num").val("");
        $("#new_water_num").val("");
        $("#new_watermeter_num").val("");
        $("#new_deadline").val("");
    }



    function createWater() {
        $.post("<%=basePath%>assertWater/create.action",
            $("#new_water_form").serialize(), function (data) {
                if(data.code==0){
                    alert(data.msg);
                    window.location.reload();
                }else{
                    alert(data.msg);
                    window.location.reload();
                }
            });
    }
</script>

</body>

</html>
