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
                <h1 class="page-header">用户管理模块</h1>
            </div>
            <!-- /.col-lg-12 -->
        </div>
        <!-- /.row -->
        <div class="panel panel-default">
            <div class="panel-body">
                <form class="form-inline" action="${pageContext.request.contextPath }/userInfo/list.action"
                      method="get">
                    <div class="form-group">
                        <label for="userName">用户名</label>
                        <input type="text" class="form-control" id="userName" value="${userName }" name="userName">
                    </div>

                    <div class="form-group">
                        <label for="status">用户状态</label>
                        <select class="form-control" id="status" placeholder="用户状态" name="status">
                            <option value="">--请选择--</option>
                            <c:forEach items="${stateType}" var="item">
                                <option value="${item.dict_id}"<c:if
                                        test="${item.dict_id == status}"> selected</c:if>>${item.dict_item_name }</option>
                            </c:forEach>
                        </select>
                    </div>

                    <button type="submit" class="btn btn-primary">查询</button>
                    <a href="#" class="btn btn-success" data-toggle="modal"
                       data-target="#newUserInfoDialog" onclick="clearUserInfo()">新建</a>
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
                            <th>邮箱</th>
                            <th>用户名</th>
                            <th>手机号</th>
                            <th>状态</th>
                            <th>操作</th>
                        </tr>
                        </thead>
                        <tbody align="center">
                        <c:forEach items="${page.rows}" var="row">
                            <tr>
                                <td>${row.id}</td>
                                <td>${row.email}</td>
                                <td>${row.userName}</td>
                                <td>${row.phoneNum}</td>
                                <c:if test="${'23' eq row.status}">
                                    <td>启用</td>
                                </c:if>
                                <c:if test="${'24' eq row.status}">
                                    <td> 未启用</td>
                                </c:if>
                                <td>
                                    <a href="#" class="btn btn-warning btn-xs" data-toggle="modal"
                                       data-target="#EditUserInfoDialog" onclick="editUserInfo(${row.id})">修改</a>
                                    <a href="#" class="btn btn-danger btn-xs"
                                       onclick="deleteUserInfo(${row.id})">删除</a>

                                    <a href="#" class="btn btn-success btn-xs" data-toggle="modal"
                                       data-target="#updatePasswordDialog"
                                       onclick="editPassword(${row.id})">修改密码</a>

                                    <a href="#" class="btn btn-success btn-xs" data-toggle="modal"
                                       data-target="#addRoleDialog"
                                       onclick="addRole(${row.id})">添加角色</a>
                                    <a
                                            href="${pageContext.request.contextPath}/role/list.action?userId=${row.id}"
                                            class="btn  btn-primary btn-xs" target="main">查看</a>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                    <div class="col-md-12 text-right">
                        <itcast:page url="${pageContext.request.contextPath }/userInfo/list.action"/>
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


<div class="modal fade" id="addRoleDialog" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="myModalLabel3">添加角色信息</h4>
            </div>
            <div class="modal-body">

                <form class="form-horizontal" id="add_role_form">

                    <%--     <div class="form-group">
                             <label for="add_userId" class="col-sm-2 control-label">id</label>
                             <div class="col-sm-10"></div>
                             <input type="text" class="form-control" id="add_userId" placeholder="id"
                                    name="userId">
                         </div>--%>

                    <div class="form-group">
                        <label for="add_userId" class="col-sm-2 control-label">id</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="add_userId" placeholder="用户id"
                                   name="userId"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="add_email" class="col-sm-2 control-label">
                            邮箱
                        </label>
                        <div class="col-sm-10">
                            <input type="text" disabled class="form-control" id="add_email" placeholder="邮箱"
                                   name="email"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="add_userName" class="col-sm-2 control-label">用户名</label>
                        <div class="col-sm-10">
                            <input type="text" disabled class="form-control" id="add_userName" placeholder="用户名"
                                   name="userName">
                        </div>
                    </div>


                    <div class="form-group">
                        <label for="add_phoneNum" class="col-sm-2 control-label">手机号</label>
                        <div class="col-sm-10">
                            <input type="text" disabled class="form-control" id="add_phoneNum" placeholder="手机号"
                                   name="phoneNum">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="add_roleId" style="float:left;padding:7px 15px 0 27px;">角色名称</label>
                        <div class="col-sm-10">
                            <select class="form-control" id="add_roleId" placeholder="角色名称" name="roleId">
                                <option value="">--请选择--</option>
                            </select>
                        </div>
                    </div>

                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary" onclick="createUserRole()">创建</button>
            </div>
        </div>
    </div>
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


<!-- 用户修改对话框 -->
<div class="modal fade" id="EditUserInfoDialog" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="myModalLabel2">更新用户信息</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" id="edit_userInfo_form">
                    <input type="hidden" id="edit_id" name="id"/>
                    <div class="form-group">
                        <label for="edit_email" class="col-sm-2 control-label">邮箱</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="edit_email" placeholder="邮箱"
                                   name="email">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="edit_userName" class="col-sm-2 control-label">用户名</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="edit_userName" placeholder="用户名"
                                   name="userName">
                        </div>
                    </div>

                    <%-- <div class="form-group">
                         <label for="edit_password" class="col-sm-2 control-label">密码</label>
                         <div class="col-sm-10">
                             <input type="text" class="form-control" id="edit_password" placeholder="密码"
                                    name="password">
                         </div>
                     </div>--%>

                    <div class="form-group">
                        <label for="edit_phoneNum" class="col-sm-2 control-label">手机号</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="edit_phoneNum" placeholder="手机号"
                                   name="phoneNum">
                        </div>
                    </div>


                    <div class="form-group">
                        <label for="edit_status" style="float:left;padding:7px 15px 0 27px;">用户状态</label>
                        <div class="col-sm-10">
                            <select class="form-control" id="edit_status" placeholder="用户状态" name="status">
                                <option value="">--请选择--</option>
                                <c:forEach items="${stateType}" var="item">
                                    <option value="${item.dict_id}"<c:if
                                            test="${item.dict_id == status}"> selected</c:if>>${item.dict_item_name }</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>

                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary" onclick="updateUserInfo()">保存修改</button>
            </div>
        </div>
    </div>
</div>


<!-- 创建用户信息 -->
<div class="modal fade" id="newUserInfoDialog" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="myModalLabel">添加用户信息</h4>
            </div>
            <div class="modal-body">

                <form class="form-horizontal" id="new_userInfo_form">
                    <div class="form-group">
                        <label for="new_email" class="col-sm-2 control-label">
                            邮箱
                        </label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="new_email" placeholder="邮箱"
                                   name="email"/>
                        </div>
                    </div>


                    <div class="form-group">
                        <label for="new_userName" class="col-sm-2 control-label">用户名</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="new_userName" placeholder="用户名"
                                   name="userName">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="new_password" class="col-sm-2 control-label">密码</label>
                        <div class="col-sm-10">
                            <input type="password" class="form-control" id="new_password" placeholder="密码"
                                   name="password">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="new_phoneNum" class="col-sm-2 control-label">手机号</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="new_phoneNum" placeholder="手机号"
                                   name="phoneNum">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="new_status" style="float:left;padding:7px 15px 0 27px;">用户状态</label>
                        <div class="col-sm-10">
                            <select class="form-control" id="new_status" placeholder="用户状态" name="status">
                                <option value="">--请选择--</option>
                                <c:forEach items="${stateType}" var="item">
                                    <option value="${item.dict_id}">${item.dict_item_name }</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>

                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary" onclick="createUserInfo()">创建</button>
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


    function editUserInfo(id) {
        $.ajax({
            type: "get",
            url: "<%=basePath%>userInfo/edit.action",
            data: {"id": id},
            success: function (data) {
                $("#edit_id").val(data.id);
                $("#edit_email").val(data.email);
                $("#edit_userName").val(data.userName);
                $("#edit_password").val(data.password);
                $("#edit_status").val(data.status);
                $("#edit_phoneNum").val(data.phoneNum);
            }
        });
    }


    function updateUserInfo() {
        $.post("<%=basePath%>userInfo/update.action", $("#edit_userInfo_form").serialize(), function (data) {
            if (data.code == 0) {
                alert(data.msg);
                window.location.reload();
            } else {
                alert(data.msg);
                window.location.reload();
            }
        });
    }

    function editPassword(id) {
        $("#new_id").val(id);
    }


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

    function createUserRole() {

        $.post("<%=basePath%>userInfo/addRoleToUser.action", $("#add_role_form").serialize(), function (data) {
            if (data.code == 0) {
                alert(data.msg);
                window.location.reload();
            } else {
                alert(data.msg);
                window.location.reload();
            }
        });

    }

    function deleteUserInfo(id) {
        if (confirm('确实要删除用户信息吗?')) {
            $.post("<%=basePath%>userInfo/delete.action", {"id": id}, function (data) {
                alert("电表度数信息删除成功！");
                window.location.reload();

            });
        }
    }

    function addRole(id) {
        var f = document.getElementById("add_roleId");
        var childs = f.childNodes;
        for (var i = childs.length - 1; i >= 0; i--) {
            f.removeChild(childs[i]);
        }
        $.ajax({
            type: "get",
            url: "<%=basePath%>userInfo/findUserByIdAndAllRole.action",
            data: {"id": id},
            success: function (data) {
                var map = data.data;
                for (var i in map) {
                    if (i == "user") {
                        $("#add_userId").val(map[i].id);
                        $("#add_email").val(map[i].email);
                        $("#add_userName").val(map[i].userName);
                        $("#add_phoneNum").val(map[i].phoneNum);
                    } else {
                        if (map[i].length == 0) {
                            alert("该用户没有其他角色了");
                        }
                        for (var index = 0; index < map[i].length; index++) {
                            document.getElementById("add_roleId").options.add(new Option(map[i][index].roleName, map[i][index].id));
                        }
                    }
                }

            }
        });
    }

    function clearUserInfo() {
        $("#new_email").val("");
        $("#new_userName").val("");
        $("#new_password").val("");
        $("#new_phoneNum").val("");
        $("#new_status").val("");
    }


    function createUserInfo() {
        $.post("<%=basePath%>userInfo/create.action",
            $("#new_userInfo_form").serialize(), function (data) {
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
