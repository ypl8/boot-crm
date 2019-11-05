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

    <title>电费管理</title>

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
                <h1 class="page-header">电费收入管理</h1>
            </div>
            <!-- /.col-lg-12 -->
        </div>
        <!-- /.row -->
        <div class="panel panel-default">
            <div class="panel-body">
                <form class="form-inline" action="${pageContext.request.contextPath }/assertPowerRent/list.action"
                      method="get">
                    <div class="form-group">
                        <label for="property_leasing_num">合同编号</label>
                        <input type="text" class="form-control" id="property_leasing_num" value="${property_leasing_num }" name="property_leasing_num">
                    </div>
                    <div class="form-group">
                        <label for="assert_num">资产编号</label>
                        <input type="text" class="form-control" id="assert_num" value="${assert_num }" name="assert_num">
                    </div>

                    <div class="form-group">
                        <label for="state">电费到账状态</label>
                        <select	class="form-control" id="state" placeholder="电费到账状态" name="state">
                            <option value="">--请选择--</option>
                            <c:forEach items="${stateType}" var="item">
                                <option value="${item.dict_id}"<c:if test="${item.dict_id == state}"> selected</c:if>>${item.dict_item_name }</option>
                            </c:forEach>
                        </select>
                    </div>

                    <button type="submit" class="btn btn-primary">查询</button>
                    <a href="#" class="btn btn-primary" data-toggle="modal"
                       data-target="#newPowerRentDialog" onclick="clearPowerRent()">新建</a>
                </form>
            </div>
        </div>


        <div class="row">
            <div class="col-lg-12">
                <div class="panel panel-default">
                    <div class="panel-heading">电费信息列表</div>
                    <!-- /.panel-heading -->
                    <table class="table table-bordered table-striped">
                        <thead>
                        <tr>
                            <th>序号</th>
                            <th>租凭合同编号</th>
                            <th>资产编号</th>
                          <%--  <th>本次收取费用开始时间</th>
                            <th>本次收取费用结束时间</th>--%>
                            <th>电表编号</th>
                            <th>电费度数(元)</th>
                            <th>截止时间</th>
                            <th>截止本月应收电费(元)</th>
                            <th>实际已收电费（元）</th>
                            <th>本次实收电费（元)</th>
                            <th>状态</th>
                            <th>操作</th>
                        </tr>
                        </thead>
                        <tbody align="center">
                        <c:forEach items="${page.rows}" var="row">
                            <tr>
                                <td>${row.id}</td>
                                <td>${row.property_leasing_num}</td>
                                <td>${row.assert_num}</td>
                              <%--  <td><fmt:formatDate value="${row.rent_start_time}" pattern="yyyy-MM-dd"/></td>
                                <td><fmt:formatDate value="${row.rent_end_time}" pattern="yyyy-MM-dd"/></td>--%>
                                <td>${row.powermeter_num}</td>
                                <td>${row.power_num}</td>
                                <td><fmt:formatDate value="${row.deadline}" pattern="yyyy-MM-dd"/></td>
                                <td>${row.power_rent}</td>
                                <td>${row.power_rent_recivied}</td>
                                <td>${row.reality_power_rent}</td>
                                <c:if test="${'21' eq row.state}">
                                   <td>缴清</td>
                                </c:if>
                                <c:if test="${'22' eq row.state}">
                                    <td> 未缴清</td>
                                </c:if>
                                <td>
                                    <a href="#" class="btn btn-primary btn-xs" data-toggle="modal"
                                       data-target="#EditPowerRentDialog" onclick="editPowerRent(${row.id})">修改</a>
                                    <a href="#" class="btn btn-danger btn-xs"
                                       onclick="deletePowerRent(${row.id})">删除</a>
                                    <%--<a href="#" class="btn btn-default btn-xs"     data-toggle="modal"  data-target="#showLeasingDialog"
                                       onclick="showDeposit(${row.id})">查看</a>--%>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                    <div class="col-md-12 text-right">
                        <itcast:page url="${pageContext.request.contextPath }/assertPowerRent/list.action"/>
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

<!-- 租金修改对话框 -->
<div class="modal fade" id="EditPowerRentDialog" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="myModalLabel2">更新信息</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" id="edit_power_rent_form">

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
                            <input type="text" class="form-control" id="edit_assert_num" placeholder="资产编号" name="assert_num">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="edit_rent_start_time" class="col-sm-2 control-label">本次收取费用开始时间</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="edit_rent_start_time" placeholder="本次收取费用开始时间"
                                   name="rent_start_time">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="edit_rent_end_time" class="col-sm-2 control-label">本次收取费用结束时间</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="edit_rent_end_time" placeholder="本次收取费用结束时间"
                                   name="rent_end_time">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="edit_powermeter_num" class="col-sm-2 control-label">电表编号</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="edit_powermeter_num" placeholder="电表编号"
                                   name="powermeter_num">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="edit_power_num" class="col-sm-2 control-label">当前电表吨数</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="edit_power_num" placeholder="当前电表吨数（吨）"
                                   name="power_num">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="edit_deadline" class="col-sm-2 control-label">截止时间</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="edit_deadline" placeholder="截止时间"
                                   name="deadline">
                        </div>
                    </div>


                    <div class="form-group">
                        <label for="edit_power_rent_received" class="col-sm-2 control-label">实际已收电费</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="edit_power_rent_received" placeholder="实际已收电费（元）"
                                   name="power_rent_recivied">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="edit_power_rent" class="col-sm-2 control-label">截取当前应收电费</label>
                        <div class="col-sm-10">
                            <input type="number" class="form-control" id="edit_power_rent" placeholder="截取当前应收电费（元）"
                                   name="power_rent">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="edit_reality_power_rent" class="col-sm-2 control-label">本次收取电费</label>
                        <div class="col-sm-10">
                            <input type="number" class="form-control" id="edit_reality_power_rent" placeholder="本次收取电费（元）"
                                   name="reality_power_rent">
                        </div>
                    </div>


                    <%--<div class="form-group">
                        <label for="edit_state" style="float:left;padding:7px 15px 0 27px;">电费状态</label>
                        <div class="col-sm-10">
                            <select	class="form-control" id="edit_state" placeholder="电费状态" name="state">
                                <option value="">--请选择--</option>
                                <c:forEach items="${stateType}" var="item">
                                    <option value="${item.dict_id}"<c:if test="${item.dict_id == state}"> selected</c:if>>${item.dict_item_name }</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>--%>

                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary" onclick="updatePowerRent()">保存修改</button>
            </div>
        </div>
    </div>
</div>


<!-- 创建电费对话框 -->
<div class="modal fade" id="newPowerRentDialog" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="myModalLabel">添加电费信息</h4>
            </div>
            <div class="modal-body">

                <form class="form-horizontal" id="new_power_rent_form">
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
                            <input type="text" class="form-control" id="new_assert_num" placeholder="资产编号" name="assert_num">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="new_rent_start_time" class="col-sm-2 control-label">本次收取费用开始时间</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="new_rent_start_time" placeholder="本次收取费用开始时间"
                                   name="rent_start_time">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="new_rent_end_time" class="col-sm-2 control-label">本次收取费用结束时间</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="new_rent_end_time" placeholder="本次收取费用结束时间"
                                   name="rent_end_time">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="new_powermeter_num" class="col-sm-2 control-label">电表编号</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="new_powermeter_num" placeholder="电表编号"
                                   name="powermeter_num">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="new_power_num" class="col-sm-2 control-label">当前电表吨数</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="new_power_num" placeholder="当前电表吨数（吨）"
                                   name="power_num">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="new_deadline" class="col-sm-2 control-label">截止时间</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="new_deadline" placeholder="截止时间"
                                   name="deadline">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="new_power_rent_received" class="col-sm-2 control-label">实际已收电费</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="new_power_rent_received" placeholder="实际已收电费（元）"
                                   name="power_rent_recivied">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="new_power_rent" class="col-sm-2 control-label">截取当前应收电费</label>
                        <div class="col-sm-10">
                            <input type="number" class="form-control" id="new_power_rent" placeholder="截取当前应收电费（元）"
                                   name="power_rent">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="new_reality_power_rent" class="col-sm-2 control-label">本次收取电费</label>
                        <div class="col-sm-10">
                            <input type="number" class="form-control" id="new_reality_power_rent" placeholder="本次收取电费（元）"
                                   name="reality_power_rent">
                        </div>
                    </div>


                   <%-- <div class="form-group">
                        <label for="new_state" style="float:left;padding:7px 15px 0 27px;">电费状态</label>
                        <div class="col-sm-10">
                            <select	class="form-control" id="new_state" placeholder="电费状态" name="state">
                                <option value="">--请选择--</option>
                                <c:forEach items="${stateType}" var="item">
                                    <option value="${item.dict_id}"<c:if test="${item.dict_id == state}"> selected</c:if>>${item.dict_item_name }</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>--%>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary" onclick="createPowerRent()">创建</button>
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


    function editPowerRent(id) {
        $.ajax({
            type: "get",
            url: "<%=basePath%>assertPowerRent/edit.action",
            data: {"id": id},
            success: function (data) {
                $("#edit_id").val(id);
                $("#edit_assert_num").val(data.assert_num);
                $("#edit_property_leasing_num").val(data.property_leasing_num);
                $("#edit_rent_start_time").val(data.rent_start_time);
                $("#edit_rent_end_time").val(data.rent_end_time);
                $("#edit_reality_power_rent").val(data.reality_power_rent);
                $("#edit_power_rent").val(data.power_rent);
        /*        $("#edit_state").val(data.state);*/
                $("#edit_power_num").val(data.power_num);
                $("#edit_deadline").val(data.deadline);
                $("#edit_powermeter_num").val(data.powermeter_num);
                $("#edit_power_rent_received").val(data.power_rent_recivied);

            }
        });
    }


    function updatePowerRent() {
        $.post("<%=basePath%>assertPowerRent/update.action", $("#edit_power_rent_form").serialize(), function (data) {
            if(data.code==0){
                alert(data.msg);
                window.location.reload();
            }else{
                alert(data.msg);
                window.location.reload();
            }
        });
    }

    function deletePowerRent(id) {
        if (confirm('确实要删除电费信息吗?')) {
            $.post("<%=basePath%>assertPowerRent/delete.action", {"id": id}, function (data) {
                    alert("电费信息删除成功！");
                    window.location.reload();

            });
        }
    }

    function clearPowerRent() {
        $("#new_property_leasing_num").val("");
        $("#new_assert_num").val("");
        $("#new_rent_start_time").val("");
        $("#new_rent_end_time").val("");
        $("#new_reality_power_rent").val("");
        $("#new_power_rent").val("");
  /*      $("#new_state").val("");*/
        $("#new_power_num").val("");
        $("#new_power_rent_received").val("");
        $("#new_powermeter_num").val("");
        $("#new_deadline").val("");
    }


    // 创建电费
    function createPowerRent() {
        $.post("<%=basePath%>assertWaterRent/create.action",
            $("#new_power_rent_form").serialize(), function (data) {
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
