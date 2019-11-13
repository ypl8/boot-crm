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
<%@taglib uri="http://www.springframework.org/security/tags" prefix="security" %>

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
                <h1 class="page-header">资产信息管理</h1>
            </div>
            <!-- /.col-lg-12 -->
        </div>
        <!-- /.row -->
        <div class="panel panel-default">
            <div class="panel-body">
                <form class="form-inline" id="mainForm"
                      action="${pageContext.request.contextPath }/assertInfol/list.action"
                      method="get">
                    <div class="form-group">
                        <label for="assert_num">资产编号</label>
                        <input type="text" class="form-control" id="assert_num" value="${assert_num }"
                               name="assert_num">
                    </div>

                    <div class="form-group">
                        <label for="community_name">小区名称</label>
                        <input type="text" class="form-control" id="community_name" value="${community_name}"
                               name="community_name">
                    </div>

                    <div class="form-group">
                        <label for="property_leasing_num">合同编号</label>
                        <input type="text" class="form-control" id="property_leasing_num"
                               value="${property_leasing_num }" name="property_leasing_num">
                    </div>

                    <div class="form-group">
                        <label for="floor_state">房屋状态</label>
                        <select class="form-control" id="floor_state" placeholder="房屋状态" name="floor_state">
                            <option value="">--请选择--</option>
                            <c:forEach items="${stateType}" var="item">
                                <option value="${item.dict_id}"<c:if
                                        test="${item.dict_id == floor_state}"> selected</c:if>>${item.dict_item_name }</option>
                            </c:forEach>
                        </select>
                    </div>

                    <button type="submit" class="btn btn-primary">查询</button>
                    <security:authorize access="hasAnyRole('ROLE_ADMIN','ROLE_ASSERT')">
                        <a href="#" class="btn btn-warning" data-toggle="modal"
                           data-target="#newAssertDialog" onclick="clearAssert()">新建</a>
                        <a href="#" class="btn btn-info "
                           onclick="uploadAssertInfol()">导入</a>
                    </security:authorize>
                    <a href="#" class="btn btn-success "
                       onclick="downloadAssertInfol()">导出本页</a>
                    <a href="#" class="btn btn-success "
                       onclick="downloadAssertInfolAll()">导出全部</a>
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
                            <th>备注</th>
                            <th>房屋状态</th>
                            <th>操作</th>
                        </tr>
                        </thead>
                        <tbody align="center">
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
                                <td>${row.remark}</td>
                                <c:if test="${'1' eq row.floor_state}">
                                    <td>已出租</td>
                                </c:if>
                                <c:if test="${'2' eq row.floor_state}">
                                    <td> 空闲</td>
                                </c:if>
                                <c:if test="${'3' eq row.floor_state}">
                                    <td> 非法占用</td>
                                </c:if>

                                <td>
                                    <security:authorize access="hasAnyRole('ROLE_ADMIN','ROLE_ASSERT')">
                                        <a href="#" class="btn btn-warning btn-xs" data-toggle="modal"
                                           data-target="#caseEditDialog" onclick="editAssert(${row.id})">修改</a>
                                        <security:authorize access="hasAnyRole('ROLE_ADMIN')">
                                            <a href="#" class="btn btn-danger btn-xs"
                                               onclick="deleteAssert(${row.id})">删除</a>
                                        </security:authorize>
                                    </security:authorize>
                                        <%-- <a href="#" class="btn btn-primary btn-xs"     data-toggle="modal"  data-target="#showLeasingDialog"
                                            onclick="showAssert(${row.id})">查看</a>--%>
                                    <a onclick="return showAssert(${row.id})"
                                       href="${pageContext.request.contextPath}/propertyLeasing/list.action?assert_num=${row.assert_num}"
                                       class="btn  btn-primary btn-xs" target="main">查看</a>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                    <div class="col-md-12 text-right">
                        <itcast:page url="${pageContext.request.contextPath }/assertInfol/list.action"/>
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
                <h4 class="modal-title" id="myModalLabel2">更新资产信息</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" id="edit_assert_form">
                    <input type="hidden" id="edit_id" name="id"/>
                    <div class="form-group">
                        <label for="edit_assert_num" class="col-sm-2 control-label">资产编号</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="edit_assert_num" placeholder="资产编号"
                                   name="assert_num">
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
                        <label for="edit_remark" class="col-sm-2 control-label">备注</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="edit_remark" placeholder="备注"
                                   name="remark">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="edit_floor_state" style="float:left;padding:7px 15px 0 27px;">房屋状态</label>
                        <div class="col-sm-10">
                            <select class="form-control" id="edit_floor_state" placeholder="房屋状态" name="floor_state">
                                <option value="">--请选择--</option>
                                <c:forEach items="${stateType}" var="item">
                                    <option value="${item.dict_id}"<c:if
                                            test="${item.dict_id == state}"> selected</c:if>>${item.dict_item_name }</option>
                                </c:forEach>
                            </select>
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
                            <input type="text" class="form-control" id="new_assert_num" placeholder="资产编号"
                                   name="assert_num"/>
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
                        <label for="new_remark" class="col-sm-2 control-label">备注</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="new_remark" placeholder="备注"
                                   name="remark">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="new_floor_state" style="float:left;padding:7px 15px 0 27px;">房屋状态</label>
                        <div class="col-sm-10">
                            <select class="form-control" id="new_floor_state" placeholder="房屋状态" name="floor_state">
                                <option value="">--请选择--</option>
                                <c:forEach items="${stateType}" var="item">
                                    <option value="${item.dict_id}"<c:if
                                            test="${item.dict_id == state}"> selected</c:if>>${item.dict_item_name }</option>
                                </c:forEach>
                            </select>
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

<!-- 显示对应资产的对话框 -->
<div class="modal fade" id="showLeasingDialog" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="myModalLabel3">合同和资产绑定</h4>
            </div>
            <div class="modal-body">
                <div class="row">
                    <div class="col-lg-12">
                        <div class="panel panel-default">
                            <div class="panel-heading">客户信息列表</div>
                            <!-- /.panel-heading -->
                            <table class="table table-bordered table-striped">
                                <thead>
                                <tr>
                                    <th>序号</th>
                                    <th>租凭合同编号</th>
                                    <th>承租人</th>
                                    <th>月租</th>
                                    <th>合同期(年)</th>
                                    <th>月物业费</th>
                                    <th>保证金（元）</th>
                                    <th>水费</th>
                                    <th>电费</th>
                                    <%--<th>租期开始时间</th>
                                    <th>租期结束时间</th>--%>
                                </tr>
                                </thead>
                                <tbody id="tbody" align="center">

                                </tbody>
                            </table>
                            <%--<div class="col-md-12 text-right">
                                <itcast:page url="${pageContext.request.contextPath }/case/list.action"/>
                            </div>--%>
                            <!-- /.panel-body -->
                        </div>
                        <!-- /.panel -->
                    </div>
                    <!-- /.col-lg-12 -->
                </div>
            </div>

        </div>
        <div class="modal-footer">
            <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
            <%--  <button type="button" class="btn btn-primary" onclick="addPropertyLeasing()">创建关系</button>--%>
        </div>
    </div>
</div>
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
                $("#edit_remark").val(data.remark);
                $("#edit_floor_state").val(data.floor_state);
            }
        });
    }

    //导入
    function uploadAssertInfol() {
        window.location.href = "<%=basePath%>assertInfol/showUpload.action";
    }

    //导出
    function downloadAssertInfol() {
        $("#mainForm").attr("action", "${pageContext.request.contextPath }/assertInfol/downloadAssertInfol.action").submit();
        $("#mainForm").attr("action", "${pageContext.request.contextPath }/assertInfol/list.action");
    }

    //导出
    function downloadAssertInfolAll() {
        $("#mainForm").attr("action", "${pageContext.request.contextPath }/assertInfol/downloadAssertInfolAll.action").submit();
        $("#mainForm").attr("action", "${pageContext.request.contextPath }/assertInfol/list.action");
    }

    function showAssert(id) {
        var flag = true;
        $.ajax({
            type: "get",
            async: false,
            url: "<%=basePath%>assertInfol/show.action",
            data: {"id": id},
            success: function (data) {
                if (data.propertyLeasings.length == 0) {
                    alert("该资产没有任何合同");
                    flag = false;
                } else {
                    flag = true;
                }
                /*var $table = $("#tbody");
                $table.empty();
                var $tr;
                for (var i=0;i<data.propertyLeasings.length;i++){
                $tr = $("<tr>"+
                "<td>"+data.propertyLeasings[i].id+"</td>"+
                "<td>"+data.propertyLeasings[i].property_leasing_num+"</td>"+
                "<td>"+data.propertyLeasings[i].tenant+"</td>"+
                "<td>"+data.propertyLeasings[i].monthly_rental+"</td>"+
                "<td>"+data.propertyLeasings[i].rent_period+"</td>"+
                "<td>"+data.propertyLeasings[i].estate_charge_month+"</td>"+
                "<td>"+data.propertyLeasings[i].deposit+"</td>"+
                "<td>"+data.propertyLeasings[i].water_rate+"</td>"+
                "<td>"+data.propertyLeasings[i].power_rate+"</td>"+
                /!* "<td>"+data.propertyLeasings[i].rent_start_time+"</td>"+
                "<td>"+data.propertyLeasings[i].rent_end_time+"</td>"+*!/
                "</tr>");
                $table.append($tr);
                }*/

            }

        });
        return flag;
    }

    function updateAssert() {
        $.post("<%=basePath%>assertInfol/update.action", $("#edit_assert_form").serialize(), function (data) {
            if (data.code == 0) {
                alert(data.msg);
                window.location.reload();
            } else {
                alert(data.msg);
                window.location.reload();

            }
        });
    }

    function deleteAssert(id) {
        if (confirm('确实要删除该资产吗?')) {
            $.post("<%=basePath%>assertInfol/delete.action", {"id": id}, function (data) {
                if (data == "OK") {
                    alert("资产信息删除成功！");
                    window.location.reload();
                } else {
                    alert("资产信息删除失败！存在合同信息关联");
                    window.location.reload();
                }
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
        $("#new_remark").val("");
        $("#new_floor_state").val("");
    }


    // 创建资产
    function createAssert() {
        $.post("<%=basePath%>assertInfol/create.action",
            $("#new_assert_form").serialize(), function (data) {
                if (data.code == 0) {
                    alert("资产信息创建成功！");
                    window.location.reload();
                } else {
                    alert(data.msg);
                    // window.location.reload();
                }
            });
    }
</script>

</body>

</html>
