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

    <!-- Navigation -->
 <%--   <nav class="navbar navbar-default navbar-static-top" role="navigation"
         style="margin-bottom: 0">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse"
                    data-target=".navbar-collapse">
                <span class="sr-only">Toggle navigation</span> <span
                    class="icon-bar"></span> <span class="icon-bar"></span> <span
                    class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="login.jsp">BOOT案例管理系统 v2.0</a>
        </div>
        <!-- /.navbar-header -->

        <ul class="nav navbar-top-links navbar-right">
            <li class="dropdown"><a class="dropdown-toggle"
                                    data-toggle="dropdown" href="#"> <i class="fa fa-envelope fa-fw"></i>
                <i class="fa fa-caret-down"></i>
            </a>
                <ul class="dropdown-menu dropdown-messages">
                    <li><a href="#">
                        <div>
                            <strong>令狐冲</strong> <span class="pull-right text-muted">
									<em>昨天</em>
								</span>
                        </div>
                        <div>今天晚上向大哥找我吃饭，讨论一下去梅庄的事...</div>
                    </a></li>
                    <li class="divider"></li>
                    <li><a class="text-center" href="#"> <strong>查看全部消息</strong>
                        <i class="fa fa-angle-right"></i>
                    </a></li>
                </ul> <!-- /.dropdown-messages --></li>
            <!-- /.dropdown -->
            <li class="dropdown"><a class="dropdown-toggle"
                                    data-toggle="dropdown" href="#"> <i class="fa fa-tasks fa-fw"></i>
                <i class="fa fa-caret-down"></i>
            </a>
                <ul class="dropdown-menu dropdown-tasks">
                    <li><a href="#">
                        <div>
                            <p>
                                <strong>任务 1</strong> <span class="pull-right text-muted">完成40%</span>
                            </p>
                            <div class="progress progress-striped active">
                                <div class="progress-bar progress-bar-success"
                                     role="progressbar" aria-valuenow="40" aria-valuemin="0"
                                     aria-valuemax="100" style="width: 40%">
                                    <span class="sr-only">完成40%</span>
                                </div>
                            </div>
                        </div>
                    </a></li>
                    <li class="divider"></li>
                    <li><a href="#">
                        <div>
                            <p>
                                <strong>任务 2</strong> <span class="pull-right text-muted">完成20%</span>
                            </p>
                            <div class="progress progress-striped active">
                                <div class="progress-bar progress-bar-info" role="progressbar"
                                     aria-valuenow="20" aria-valuemin="0" aria-valuemax="100"
                                     style="width: 20%">
                                    <span class="sr-only">完成20%</span>
                                </div>
                            </div>
                        </div>
                    </a></li>
                    <li class="divider"></li>
                    <li><a class="text-center" href="#"> <strong>查看所有任务</strong>
                        <i class="fa fa-angle-right"></i>
                    </a></li>
                </ul> <!-- /.dropdown-tasks --></li>
            <!-- /.dropdown -->
            <li class="dropdown"><a class="dropdown-toggle"
                                    data-toggle="dropdown" href="#"> <i class="fa fa-bell fa-fw"></i>
                <i class="fa fa-caret-down"></i>
            </a>
                <ul class="dropdown-menu dropdown-alerts">
                    <li><a href="#">
                        <div>
                            <i class="fa fa-comment fa-fw"></i> 新回复 <span
                                class="pull-right text-muted small">4分钟之前</span>
                        </div>
                    </a></li>
                    <li class="divider"></li>
                    <li><a href="#">
                        <div>
                            <i class="fa fa-envelope fa-fw"></i> 新消息 <span
                                class="pull-right text-muted small">4分钟之前</span>
                        </div>
                    </a></li>
                    <li class="divider"></li>
                    <li><a href="#">
                        <div>
                            <i class="fa fa-tasks fa-fw"></i> 新任务 <span
                                class="pull-right text-muted small">4分钟之前</span>
                        </div>
                    </a></li>
                    <li class="divider"></li>
                    <li><a href="#">
                        <div>
                            <i class="fa fa-upload fa-fw"></i> 服务器重启 <span
                                class="pull-right text-muted small">4分钟之前</span>
                        </div>
                    </a></li>
                    <li class="divider"></li>
                    <li><a class="text-center" href="#"> <strong>查看所有提醒</strong>
                        <i class="fa fa-angle-right"></i>
                    </a></li>
                </ul> <!-- /.dropdown-alerts --></li>
            <!-- /.dropdown -->
            <li class="dropdown"><a class="dropdown-toggle"
                                    data-toggle="dropdown" href="#"> <i class="fa fa-user fa-fw"></i>
                <i class="fa fa-caret-down"></i>
            </a>
                <ul class="dropdown-menu dropdown-user">
                    <li><a href="#"><i class="fa fa-user fa-fw"></i> 用户：${USER_SESSION.user_name}</a></li>
                    <li><a href="#"><i class="fa fa-gear fa-fw"></i> 系统设置</a></li>
                    <li class="divider"></li>
                    <li><a href="${pageContext.request.contextPath }/logout.action"><i class="fa fa-sign-out fa-fw"></i>
                        退出登录</a></li>
                </ul> <!-- /.dropdown-user --></li>
            <!-- /.dropdown -->
        </ul>
        <!-- /.navbar-top-links -->

        <div class="navbar-default sidebar" role="navigation">
            <div class="sidebar-nav navbar-collapse">
                <ul class="nav" id="side-menu">
                    <li class="sidebar-search">
                        <div class="input-group custom-search-form">
                            <input type="text" class="form-control" placeholder="查询内容...">
                            <span class="input-group-btn">
								<button class="btn btn-default" type="button">
									<i class="fa fa-search" style="padding: 3px 0 3px 0;"></i>
								</button>
							</span>
                        </div> <!-- /input-group -->
                    </li>
                    <li><a href="<%=basePath%>/customer.action" class="active">
                        <i class="fa fa-edit fa-fw"></i> 客户管理</a></li>
                    <li><a href="<%=basePath%>/case.action"><i class="fa fa-dashboard fa-fw"></i> 案例管理</a></li>
                    <li><a href="<%=basePath%>assertInfol.action"><i class="fa fa-dashboard fa-fw"></i> 资产信息管理</a></li>
                </ul>
            </div>
            <!-- /.sidebar-collapse -->
        </div>
        <!-- /.navbar-static-side --> </nav>--%>

    <div id="page-wrapper">
        <div class="row">
            <div class="col-lg-12">
                <h1 class="page-header">资产信息管理</h1>
            </div>
            <!-- /.col-lg-12 -->
        </div>
        <!-- /.row -->
        <div class="panel panel-default">
            <div class="panel-body">
                <form class="form-inline" action="${pageContext.request.contextPath }/assertInfol/list.action"
                      method="get">
                    <div class="form-group">
                        <label for="assert_num">资产编号</label>
                        <input type="text" class="form-control" id="assert_num" value="${assert_num }" name="assert_num">
                    </div>
                    <button type="submit" class="btn btn-primary">查询</button>
                    <a href="#" class="btn btn-primary" data-toggle="modal"
                       data-target="#newAssertDialog" onclick="clearAssert()">新建</a>
                </form>
            </div>
        </div>


        <div class="row">
            <div class="col-lg-12">
                <div class="panel panel-default">
                    <div class="panel-heading">客户信息列表</div>
                    <!-- /.panel-heading -->
                    <table class="table table-bordered table-striped">
                        <thead>
                        <tr>
                            <th>序号</th>
                            <th>资产编号</th>
                            <th>产证编号</th>
                            <th>小区名称</th>
                            <th>栋号</th>
                            <th>房号或店面号</th>
                            <th>建筑面积</th>
                            <th>水表编号</th>
                            <th>电表编号</th>
                            <th>房屋状态</th>
                            <th>操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${page.rows}" var="row">
                            <tr>
                                <td>${row.id}</td>
                                <td>${row.assert_num}</td>
                                <td>${row.card_num}</td>
                                <td>${row.community_name}</td>
                                <td>${row.building_num}</td>
                                <td>${row.room_number}</td>
                                <td>${row.floorage}</td>
                                <td>${row.watermeter_num}</td>
                                <td>${row.electricmeter_num}</td>
                                <td>${row.floor_state}</td>
                                <td>
                                    <a href="#" class="btn btn-primary btn-xs" data-toggle="modal"
                                       data-target="#caseEditDialog" onclick="editAssert(${row.id})">修改</a>
                                    <a href="#" class="btn btn-danger btn-xs"
                                       onclick="deleteAssert(${row.id})">删除</a>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                    <div class="col-md-12 text-right">
                        <itcast:page url="${pageContext.request.contextPath }/case/list.action"/>
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

<!-- 客户编辑对话框 -->
<div class="modal fade" id="caseEditDialog" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="myModalLabel">更新资产信息</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" id="edit_assert_form">
                    <input type="hidden" id="edit_id" name="id"/>
                    <div class="form-group">
                        <label for="edit_assert_num" class="col-sm-2 control-label">资产编号</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="edit_assert_num" placeholder="资产编号" name="assert_num">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="edit_card_num" class="col-sm-2 control-label">产证编号</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="edit_card_num" placeholder="产证编号"
                                   name="card_num">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="edit_community_name" class="col-sm-2 control-label">小区名称</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="edit_community_name" placeholder="小区名称"
                                   name="community_name">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="edit_building_num" class="col-sm-2 control-label">栋号</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="edit_building_num" placeholder="栋号"
                                   name="building_num">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="edit_room_number" class="col-sm-2 control-label">房号或店面号</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="edit_room_number" placeholder="房号或店面号"
                                   name="room_number">
                        </div>
                    </div>


                    <div class="form-group">
                        <label for="edit_floorage" class="col-sm-2 control-label">建筑面积</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="edit_floorage" placeholder="建筑面积"
                                   name="floorage">
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
                        <label for="edit_electricmeter_num" class="col-sm-2 control-label">电表编号</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="edit_electricmeter_num" placeholder="电表编号"
                                   name="electricmeter_num">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="edit_floor_state" class="col-sm-2 control-label">房屋状态</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="edit_floor_state" placeholder="房屋状态(1、已出租 ，2、空闲，3、非法占用)"
                                   name="floor_state">
                        </div>
                    </div>

                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary" onclick="updateAssert()">保存修改</button>
            </div>
        </div>
    </div>
</div>


<!-- 创建客户模态框 -->
<div class="modal fade" id="newAssertDialog" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="myModalLabel">新建资产</h4>
            </div>
            <div class="modal-body">

                <form class="form-horizontal" id="new_assert_form">
                    <div class="form-group">
                        <label for="new_assert_num" class="col-sm-2 control-label">
                            资产编号
                        </label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="new_assert_num" placeholder="资产编号" name="assert_num"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="new_card_num" class="col-sm-2 control-label">产证编号</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="new_card_num" placeholder="产证编号"
                                   name="card_num"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="new_community_name" class="col-sm-2 control-label">小区名称</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="new_community_name" placeholder="小区名称"
                                   name="community_name"/>
                        </div>
                    </div>


                    <div class="form-group">
                        <label for="new_building_num" class="col-sm-2 control-label">栋号</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="new_building_num" placeholder="栋号"
                                   name="building_num"/>
                        </div>
                    </div>


                    <div class="form-group">
                        <label for="new_room_number" class="col-sm-2 control-label">房号或店面号</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="new_room_number" placeholder="房号或店面号"
                                   name="room_number"/>
                        </div>
                    </div>


                    <div class="form-group">
                        <label for="new_floorage" class="col-sm-2 control-label">建筑面积</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="new_floorage" placeholder="建筑面积"
                                   name="floorage"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="new_watermeter_num" class="col-sm-2 control-label">水表编号</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="new_watermeter_num" placeholder="水表编号"
                                   name="watermeter_num"/>
                        </div>
                    </div>


                    <div class="form-group">
                        <label for="new_electricmeter_num" class="col-sm-2 control-label">电表编号</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="new_electricmeter_num" placeholder="电表编号"
                                   name="electricmeter_num"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="new_floor_state" class="col-sm-2 control-label">房屋状态</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="new_floor_state" placeholder="房屋状态(1、已出租 ，2、空闲，3、非法占用)"
                                   name="floor_state"/>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary" onclick="createAssert()">创建资产</button>
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


    function editAssert(id) {
        $.ajax({
            type: "get",
            url: "<%=basePath%>assertInfol/edit.action",
            data: {"id": id},
            success: function (data) {
                $("#edit_id").val(data.id);
                $("#edit_assert_num").val(data.assert_num);
                $("#edit_card_num").val(data.card_num);
                $("#edit_community_name").val(data.community_name);
                $("#edit_building_num").val(data.building_num);
                $("#edit_room_number").val(data.room_number);
                $("#edit_floorage").val(data.floorage);
                $("#edit_watermeter_num").val(data.watermeter_num);
                $("#edit_electricmeter_num").val(data.electricmeter_num);
                $("#edit_floor_state").val(data.floor_state);
            }
        });
    }

    function updateAssert() {
        $.post("<%=basePath%>assertInfol/update.action", $("#edit_assert_form").serialize(), function (data) {
            alert("资产信息更新成功！");
            window.location.reload();
        });
    }

    function deleteAssert(id) {
        if (confirm('确实要删除该资产吗?')) {
            $.post("<%=basePath%>assertInfol/delete.action", {"id": id}, function (data) {
                alert("资产删除更新成功！");
                window.location.reload();
            });
        }
    }

    function clearAssert() {
        $("#new_assert_num").val("");
        $("#new_card_num").val("");
        $("#new_community_name").val("");
        $("#new_building_num").val("");
        $("#new_room_number").val("");
        $("#new_floorage").val("");
        $("#new_watermeter_num").val("");
        $("#new_electricmeter_num").val("");
        $("#new_floor_state").val("");
    }


    // 创建客户
    function createAssert() {
        $.post("<%=basePath%>assertInfol/create.action",
            $("#new_assert_form").serialize(), function (data) {
                if (data == "OK") {
                    alert("资产信息创建成功！");
                    window.location.reload();
                } else {
                    alert("资产信息创建失败！");
                    window.location.reload();
                }
            });
    }
</script>

</body>

</html>
