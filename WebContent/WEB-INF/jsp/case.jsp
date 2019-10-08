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
    <link href="<%=basePath%>css/sb-admin-2.css" rel="stylesheet">

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
    <nav class="navbar navbar-default navbar-static-top" role="navigation"
         style="margin-bottom: 0">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse"
                    data-target=".navbar-collapse">
                <span class="sr-only">Toggle navigation</span> <span
                    class="icon-bar"></span> <span class="icon-bar"></span> <span
                    class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="index.html">BOOT案例管理系统 v2.0</a>
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
                    <li><a href="<%=basePath%>/case.action" class="active"><i
                            class="fa fa-edit fa-fw"></i> 客户管理</a></li>
                    <li><a href="salevisit.action"><i class="fa fa-dashboard fa-fw"></i> 案例管理</a></li>
                </ul>
            </div>
            <!-- /.sidebar-collapse -->
        </div>
        <!-- /.navbar-static-side --> </nav>

    <div id="page-wrapper">
        <div class="row">
            <div class="col-lg-12">
                <h1 class="page-header">案例管理</h1>
            </div>
            <!-- /.col-lg-12 -->
        </div>
        <!-- /.row -->
        <div class="panel panel-default">
            <div class="panel-body">
                <form class="form-inline" action="${pageContext.request.contextPath }/customer/list.action"
                      method="get">
                    <div class="form-group">
                        <label for="caseNo">案号</label>
                        <input type="text" class="form-control" id="caseNo" value="${caseNo }" name="caseNo">
                    </div>
                    <%--<div class="form-group">
                        <label for="customerFrom">客户来源</label>
                        <select	class="form-control" id="customerFrom" placeholder="客户来源" name="custSource">
                            <option value="">--请选择--</option>
                            <c:forEach items="${fromType}" var="item">
                                <option value="${item.dict_id}"<c:if test="${item.dict_id == custSource}"> selected</c:if>>${item.dict_item_name }</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="custIndustry">所属行业</label>
                        <select	class="form-control" id="custIndustry"  name="custIndustry">
                            <option value="">--请选择--</option>
                            <c:forEach items="${industryType}" var="item">
                                <option value="${item.dict_id}"<c:if test="${item.dict_id == custIndustry}"> selected</c:if>>${item.dict_item_name }</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="custLevel">客户级别</label>
                        <select	class="form-control" id="custLevel" name="custLevel">
                            <option value="">--请选择--</option>
                            <c:forEach items="${levelType}" var="item">
                                <option value="${item.dict_id}"<c:if test="${item.dict_id == custLevel}"> selected</c:if>>${item.dict_item_name }</option>
                            </c:forEach>
                        </select>
                    </div>--%>
                    <button type="submit" class="btn btn-primary">查询</button>

                    <a href="#" class="btn btn-primary" data-toggle="modal"
                       data-target="#newCaseDialog" onclick="clearCase()">新建</a>
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
                            <th>ID</th>
                            <th>案号</th>
                            <th>承办法官/法官助理</th>
                            <th>当事人</th>
                            <th>状态</th>
                            <th>立案时间</th>
                            <th>结案时间</th>
                            <th>操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${page.rows}" var="row">
                            <tr>
                                <td>${row.case_id}</td>
                                <td>${row.case_no}</td>
                                <td>${row.case_judgepeople}</td>
                                <td>${row.case_partypeople}</td>
                                <td>${row.case_state}</td>
                                <td><fmt:formatDate value="${row.case_startime}" pattern="yyyyMMdd" /></td>
                                <td><fmt:formatDate value="${row.case_endtime}" pattern="yyyyMMdd" /></td>
                                <td>
                                    <a href="#" class="btn btn-primary btn-xs" data-toggle="modal"
                                       data-target="#caseEditDialog" onclick="editCase(${row.case_id})">修改</a>
                                    <a href="#" class="btn btn-danger btn-xs"
                                       onclick="deleteCase(${row.case_id})">删除</a>
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
                <h4 class="modal-title" id="myModalLabel">更新案例信息</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" id="edit_case_form">
                    <input type="hidden" id="edit_case_id" name="case_id"/>
                    <div class="form-group">
                        <label for="edit_case_no" class="col-sm-2 control-label">案例编号</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="edit_case_no" placeholder="案例编号" name="case_no">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="edit_case_judgepeople" class="col-sm-2 control-label">承办法官/法官助理</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="edit_case_judgepeople" placeholder="承办法官/法官助理"
                                   name="case_judgepeople">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="edit_case_partypeople" class="col-sm-2 control-label">当事人</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="edit_case_partypeople" placeholder="当事人"
                                   name="case_partypeople">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="edit_case_state" class="col-sm-2 control-label">状态</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="edit_case_state" placeholder="状态"
                                   name="case_state">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="edit_case_startime" class="col-sm-2 control-label">立案时间</label>
                        <div class="col-sm-10">
                            <input type="datetime" class="form-control" id="edit_case_startime" placeholder="立案时间"
                                   name="case_startime">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="edit_case_endtime" class="col-sm-2 control-label">结案时间</label>
                        <div class="col-sm-10">
                            <input type="datetime" class="form-control" id="edit_case_endtime" placeholder="结案时间"
                                   name="case_endtime">
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary" onclick="updateCase()">保存修改</button>
            </div>
        </div>
    </div>
</div>


<!-- 创建客户模态框 -->
<div class="modal fade" id="newCaseDialog" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="myModalLabel">新建案例</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" id="new_case_form">
                    <div class="form-group">
                        <label for="new_case_no" class="col-sm-2 control-label">
                            案例编号
                        </label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="new_case_no" placeholder="案例编号" name="case_no"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="new_casejudgepeople" class="col-sm-2 control-label">承办法官/法官助理</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="new_casejudgepeople" placeholder="承办法官/法官助理"
                                   name="case_judgepeople"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="new_case_partypeople" class="col-sm-2 control-label">当事人</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="new_case_partypeople" placeholder="当事人"
                                   name="case_partypeople"/>
                        </div>
                    </div>


                    <div class="form-group">
                        <label for="new_case_state" class="col-sm-2 control-label">状态</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="new_case_state" placeholder="状态"
                                   name="case_state"/>
                        </div>
                    </div>


                    <div class="form-group">
                        <label for="new_case_startime" class="col-sm-2 control-label">开案时间</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="new_case_startime" placeholder="开案时间"
                                   name="case_startime"/>
                        </div>
                    </div>


                    <div class="form-group">
                        <label for="new_case_endtime" class="col-sm-2 control-label">结案时间</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="new_case_endtime" placeholder="结案时间"
                                   name="case_endtime"/>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary" onclick="createCase()">创建案例</button>
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
    function editCase(id) {
        $.ajax({
            type: "get",
            url: "<%=basePath%>case/edit.action",
            data: {"id": id},
            success: function (data) {
                $("#edit_case_id").val(data.case_id);
                $("#edit_case_no").val(data.case_no);
                $("#edit_case_judgepeople").val(data.case_judgepeople)
                $("#edit_case_partypeople").val(data.case_partypeople)
                $("#edit_case_state").val(data.case_state)
                alert(data.case_startime);
                $("#edit_case_startime").val(new Date(data.case_startime).Format('yyyyMMdd'));
                $("#edit_case_endtime").val(new Date(data.case_endtime).Format('yyyyMMdd'));
            }
        });
    }

    function updateCase() {
        $.post("<%=basePath%>case/update.action", $("#edit_case_form").serialize(), function (data) {
            alert("案例信息更新成功！");
            window.location.reload();
        });
    }

    function deleteCase(id) {
        if (confirm('确实要删除该案例吗?')) {
            $.post("<%=basePath%>case/delete.action", {"id": id}, function (data) {
                alert("案例删除更新成功！");
                window.location.reload();
            });
        }
    }

    function clearCase() {
        $("#new_case_no").val("");
        $("#new_casejudgepeople").val("");
        $("#new_edit_case_partypeople").val("")
        $("#new_case_state").val("")
        $("#new_case_startime").val("");
        $("#new_case_endtime").val("");
    }


    // 创建客户
    function createCase() {
        $.post("<%=basePath%>case/create.action",
            $("#new_case_form").serialize(), function (data) {
                if (data == "OK") {
                    alert("案例创建成功！");
                    window.location.reload();
                } else {
                    alert("案例创建失败！");
                    window.location.reload();
                }
            });
    }
</script>

</body>

</html>
