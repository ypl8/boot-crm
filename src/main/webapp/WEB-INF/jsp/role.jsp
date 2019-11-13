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
                <h1 class="page-header">角色管理模块</h1>
            </div>
            <!-- /.col-lg-12 -->
        </div>
        <!-- /.row -->
        <div class="panel panel-default">
            <div class="panel-body">
                <form class="form-inline" action="${pageContext.request.contextPath }/role/list.action"
                      method="get">
                    <div class="form-group">
                        <label for="id">角色id</label>
                        <input type="text" class="form-control" id="id" value="${id}" name="id">
                    </div>
                    <div class="form-group">
                        <label for="userId">用户id</label>
                        <input type="text" class="form-control" id="userId" value="${userId}" name="id">
                    </div>
                    <button type="submit" class="btn btn-primary">查询</button>
                    <a href="#" class="btn btn-success" data-toggle="modal"
                       data-target="#newRoleDialog" onclick="clearRole()">新建</a>
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
                            <th>角色名称</th>
                            <th>角色描述</th>
                            <th>操作</th>
                        </tr>
                        </thead>
                        <tbody align="center">
                        <c:forEach items="${page.rows}" var="row">
                            <tr>
                                <td>${row.id}</td>
                                <td>${row.roleName}</td>
                                <td>${row.roleDesc}</td>

                                <td>
                                    <a href="#" class="btn btn-primary btn-xs" data-toggle="modal"
                                       data-target="#EditRoleDialog" onclick="editRole(${row.id})">修改</a>
                                    <a href="#" class="btn btn-danger btn-xs"
                                       onclick="deleteRole(${row.id})">删除</a>

                                    <a href="#" class="btn btn-success btn-xs" data-toggle="modal"
                                       data-target="#addPemissionDialog"
                                       onclick="addPemission(${row.id})">添加权限</a>
                                    <a
                                       href="${pageContext.request.contextPath}/permission/list.action?roleId=${row.id}"
                                       class="btn  btn-primary btn-xs" target="main">查看</a>
                                        <%--<a href="#" class="btn btn-default btn-xs"     data-toggle="modal"  data-target="#showLeasingDialog"
                                           onclick="showDeposit(${row.id})">查看</a>--%>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                    <div class="col-md-12 text-right">
                        <itcast:page url="${pageContext.request.contextPath }/role/list.action"/>
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

<div class="modal fade" id="addPemissionDialog" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="myModalLabel3">添加角色权限信息</h4>
            </div>
            <div class="modal-body">

                <form class="form-horizontal" id="add_permission_form">

                    <%--     <div class="form-group">
                             <label for="add_userId" class="col-sm-2 control-label">id</label>
                             <div class="col-sm-10"></div>
                             <input type="text" class="form-control" id="add_userId" placeholder="id"
                                    name="userId">
                         </div>--%>

                    <div class="form-group">
                        <label for="add_roleId" class="col-sm-2 control-label">角色id</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="add_roleId" placeholder="角色id"
                                   name="roleId"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="add_roleName" class="col-sm-2 control-label">
                            角色名称
                        </label>
                        <div class="col-sm-10">
                            <input type="text" disabled class="form-control" id="add_roleName" placeholder="角色名称"
                                   name="roleName"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="add_roleDesc" class="col-sm-2 control-label">角色描述</label>
                        <div class="col-sm-10">
                            <input type="text" disabled class="form-control" id="add_roleDesc" placeholder="角色描述"
                                   name="roleDesc">
                        </div>
                    </div>


                    <div class="form-group">
                        <label for="add_permissionId" style="float:left;padding:7px 15px 0 27px;">权限名称</label>
                        <div class="col-sm-10">
                            <select class="form-control" id="add_permissionId" placeholder="权限名称" name="permissionId">
                                <option value="">--请选择--</option>
                            </select>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary" onclick="createRolePermission()">创建</button>
            </div>
        </div>
    </div>
</div>

<!-- 用户修改对话框 -->
<div class="modal fade" id="EditRoleDialog" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="myModalLabel2">更新角色信息</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" id="edit_role_form">
                    <input type="hidden" id="edit_id" name="id"/>
                    <div class="form-group">
                        <label for="edit_roleName" class="col-sm-2 control-label">角色名称</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="edit_roleName" placeholder="角色名称"
                                   name="roleName">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="edit_roleDesc" class="col-sm-2 control-label">角色描述</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="edit_roleDesc" placeholder="角色描述"
                                   name="roleDesc">
                        </div>
                    </div>

                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary" onclick="updateRole()">保存修改</button>
            </div>
        </div>
    </div>
</div>


<!-- 创建角色信息 -->
<div class="modal fade" id="newRoleDialog" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="myModalLabel">添加角色信息</h4>
            </div>
            <div class="modal-body">

                <form class="form-horizontal" id="new_role_form">
                    <div class="form-group">
                        <label for="new_roleName" class="col-sm-2 control-label">
                            角色名称
                        </label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="new_roleName" placeholder="角色名称"
                                   name="roleName"/>
                        </div>
                    </div>


                    <div class="form-group">
                        <label for="new_roleDesc" class="col-sm-2 control-label">角色描述</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="new_roleDesc" placeholder="角色描述"
                                   name="roleDesc">
                        </div>
                    </div>

                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary" onclick="createRole()">创建</button>
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


    function editRole(id) {
        $.ajax({
            type: "get",
            url: "<%=basePath%>role/edit.action",
            data: {"id": id},
            success: function (data) {
                $("#edit_id").val(data.id);
                $("#edit_roleName").val(data.roleName);
                $("#edit_roleDesc").val(data.roleDesc);

            }
        });
    }


    function updateRole() {
        $.post("<%=basePath%>role/update.action", $("#edit_role_form").serialize(), function (data) {
            if (data.code == 0) {
                alert(data.msg);
                window.location.reload();
            } else {
                alert(data.msg);
                window.location.reload();
            }
        });
    }

    function deleteRole(id) {
        if (confirm('确实要删除角色信息吗?')) {
            $.post("<%=basePath%>role/delete.action", {"id": id}, function (data) {
                alert("角色信息删除成功！");
                window.location.reload();

            });
        }
    }

    function clearRole() {
        $("#new_roleName").val("");
        $("#new_roleDesc").val("");

    }


    function createRolePermission() {
        $.post("<%=basePath%>role/addPermissionToRole.action", $("#add_permission_form").serialize(), function (data) {
            if (data.code == 0) {
                alert(data.msg);
                window.location.reload();
            } else {
                alert(data.msg);
                window.location.reload();
            }
        });
    }

    function createRole() {
        $.post("<%=basePath%>role/create.action",
            $("#new_role_form").serialize(), function (data) {
                if (data.code == 0) {
                    alert(data.msg);
                    window.location.reload();
                } else {
                    alert(data.msg);
                    window.location.reload();
                }
            });
    }


    function addPemission(id) {
        var f = document.getElementById("add_permissionId");
        var childs = f.childNodes;
        for (var i = childs.length - 1; i >= 0; i--) {
            f.removeChild(childs[i]);
        }
        $.ajax({
            type: "get",
            url: "<%=basePath%>role/findRoleByIdAndAllPermission.action",
            data: {"roleId": id},
            success: function (data) {
                var  map=  data.data;
                for(var i in map){
                    if(i=="role"){
                        $("#add_roleId").val(map[i].id);
                        $("#add_roleName").val(map[i].roleName);
                        $("#add_roleDesc").val(map[i].roleDesc);
                    }else{
                        if(map[i].length==0){
                            alert("该角色没有其他权限了");
                        }
                        for (var index = 0; index < map[i].length; index++) {
                            document.getElementById("add_permissionId").options.add(new Option(map[i][index].permissionName, map[i][index].id));
                        }
                    }
                }

            }
        });
    }
</script>

</body>

</html>
