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

    <title>资产管理系统</title>

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
                <h1 class="page-header">权限管理模块</h1>
            </div>
            <!-- /.col-lg-12 -->
        </div>
        <!-- /.row -->
        <div class="panel panel-default">
            <div class="panel-body">
                <form class="form-inline" action="${pageContext.request.contextPath }/permission/list.action"
                      method="get">
                    <div class="form-group">
                        <label for="id">权限id</label>
                        <input type="text" class="form-control" id="id" value="${id }" name="id">
                    </div>
                    <div class="form-group">
                        <label for="id">角色id</label>
                        <input type="text" class="form-control" id="roleId" value="${roleId }" name="roleId">
                    </div>
                    <button type="submit" class="btn btn-primary">查询</button>
                    <a href="#" class="btn btn-success" data-toggle="modal"
                       data-target="#newPermissionDialog" onclick="clearPermission()">新建</a>
                </form>
            </div>
        </div>


        <div class="row">
            <div class="col-lg-12">
                <div class="panel panel-default">
                    <div class="panel-heading">用户列表</div>
                    <!-- /.panel-heading -->
                    <table class="table table-bordered table-striped">
                        <thead>
                        <tr>
                            <th>序号</th>
                            <th>权限名称</th>
                            <th>权限url</th>
                            <th>操作</th>
                        </tr>
                        </thead>
                        <tbody align="center">
                        <c:forEach items="${page.rows}" var="row">
                            <tr>
                                <td>${row.id}</td>
                                <td>${row.permissionName}</td>
                                <td>${row.url}</td>

                                <td>
                                    <a href="#" class="btn btn-primary btn-xs" data-toggle="modal"
                                       data-target="#EditPermissionDialog" onclick="editPermission(${row.id})">修改</a>
                                    <a href="#" class="btn btn-danger btn-xs"
                                       onclick="deletePermission(${row.id})">删除</a>
                                        <%--<a href="#" class="btn btn-default btn-xs"     data-toggle="modal"  data-target="#showLeasingDialog"
                                           onclick="showDeposit(${row.id})">查看</a>--%>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                    <div class="col-md-12 text-right">
                        <itcast:page url="${pageContext.request.contextPath }/permission/list.action"/>
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




<!-- 用户修改对话框 -->
<div class="modal fade" id="EditPermissionDialog" tabindex="-1" permission="dialog"
     aria-labelledby="myModalLabel">
    <div class="modal-dialog" permission="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="myModalLabel2">更新权限信息</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" id="edit_permission_form">
                    <input type="hidden" id="edit_id" name="id"/>
                    <div class="form-group">
                        <label for="edit_permissionName" class="col-sm-2 control-label">权限名称</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="edit_permissionName" placeholder="权限名称"
                                   name="permissionName">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="edit_url" class="col-sm-2 control-label">权限url</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="edit_url" placeholder="权限url"
                                   name="url">
                        </div>
                    </div>

                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary" onclick="updatePermission()">保存修改</button>
            </div>
        </div>
    </div>
</div>


<!-- 创建权限信息 -->
<div class="modal fade" id="newPermissionDialog" tabindex="-1" permission="dialog"
     aria-labelledby="myModalLabel">
    <div class="modal-dialog" permission="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="myModalLabel">添加权限信息</h4>
            </div>
            <div class="modal-body">

                <form class="form-horizontal" id="new_permission_form">
                    <div class="form-group">
                        <label for="new_permissionName" class="col-sm-2 control-label">
                            权限名称
                        </label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="new_permissionName" placeholder="权限名称"
                                   name="permissionName"/>
                        </div>
                    </div>


                    <div class="form-group">
                        <label for="new_url" class="col-sm-2 control-label">权限url</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="new_url" placeholder="权限url"
                                   name="url">
                        </div>
                    </div>

                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary" onclick="createPermission()">创建</button>
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


    function editPermission(id) {
        $.ajax({
            type: "get",
            url: "<%=basePath%>permission/edit.action",
            data: {"id": id},
            success: function (data) {
                $("#edit_id").val(data.id);
                $("#edit_permissionName").val(data.permissionName);
                $("#edit_url").val(data.url);

            }
        });
    }


    function updatePermission() {
        $.post("<%=basePath%>permission/update.action", $("#edit_permission_form").serialize(), function (data) {
            if (data.code == 0) {
                alert(data.msg);
                window.location.reload();
            } else {
                alert(data.msg);
                window.location.reload();
            }
        });
    }

    function deletePermission(id) {
        if (confirm('确实要删除权限信息吗?')) {
            $.post("<%=basePath%>permission/delete.action", {"id": id}, function (data) {
                alert("权限信息删除成功！");
                window.location.reload();

            });
        }
    }

    function clearPermission() {
        $("#new_permissionName").val("");
        $("#new_url").val("");

    }


    function createPermission() {
        $.post("<%=basePath%>permission/create.action",
            $("#new_permission_form").serialize(), function (data) {
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
