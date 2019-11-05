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
                <h1 class="page-header">合同押金收入管理</h1>
            </div>
            <!-- /.col-lg-12 -->
        </div>
        <!-- /.row -->
        <div class="panel panel-default">
            <div class="panel-body">
                <form class="form-inline" action="${pageContext.request.contextPath }/assertDeposit/list.action"
                      method="get">
                    <div class="form-group">
                        <label for="property_leasing_num">合同编号</label>
                        <input type="text" class="form-control" id="property_leasing_num"
                               value="${property_leasing_num }" name="property_leasing_num">
                    </div>

                    <div class="form-group">
                        <label for="state">押金到账状态</label>
                        <select class="form-control" id="state" placeholder="押金到账状态" name="state">
                            <option value="">--请选择--</option>
                            <c:forEach items="${stateType}" var="item">
                                <option value="${item.dict_id}"<c:if
                                        test="${item.dict_id == state}"> selected</c:if>>${item.dict_item_name }</option>
                            </c:forEach>
                        </select>
                    </div>

                    <button type="submit" class="btn btn-primary">查询</button>
                    <a href="#" class="btn btn-primary" data-toggle="modal"
                       data-target="#newDepositDialog" onclick="clearDeposit()">新建</a>
                </form>
            </div>
        </div>


        <div class="row">
            <div class="col-lg-12">
                <div class="panel panel-default">
                    <div class="panel-heading">押金信息列表</div>
                    <!-- /.panel-heading -->
                    <table class="table table-bordered table-striped">
                        <thead>
                        <tr>
                            <th>序号</th>
                            <th>租凭合同编号</th>
                            <th>本次收取费用开始时间</th>
                            <th>本次收取费用结束时间</th>
                            <th>应收保证金（元）</th>
                            <th>实际保证金（元)</th>
                            <th>到帐时间</th>
                            <th>状态</th>
                            <th>操作</th>
                        </tr>
                        </thead>
                        <tbody align="center">
                        <c:forEach items="${page.rows}" var="row">
                            <tr>
                                <td>${row.id}</td>
                                <td>${row.property_leasing_num}</td>
                                <td><fmt:formatDate value="${row.rent_start_time}" pattern="yyyy-MM-dd"/></td>
                                <td><fmt:formatDate value="${row.rent_end_time}" pattern="yyyy-MM-dd"/></td>
                                <td>${row.deposit}</td>
                                <td>${row.reality_deposit}</td>
                                <td><fmt:formatDate value="${row.deadline}" pattern="yyyy-MM-dd"/></td>
                                <c:if test="${'5' eq row.state}">
                                    <td>缴清</td>
                                </c:if>
                                <c:if test="${'4' eq row.state}">
                                    <td> 未缴清</td>
                                </c:if>

                                <td>
                                    <a href="#" class="btn btn-primary btn-xs" data-toggle="modal"
                                       data-target="#depositEditDialog" onclick="editDeposit(${row.id})">修改</a>
                                    <a href="#" class="btn btn-danger btn-xs"
                                       onclick="deleteDeposit(${row.id})">删除</a>
                                        <%--<a href="#" class="btn btn-default btn-xs"     data-toggle="modal"  data-target="#showLeasingDialog"
                                           onclick="showDeposit(${row.id})">查看</a>--%>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                    <div class="col-md-12 text-right">
                        <itcast:page url="${pageContext.request.contextPath }/assertDeposit/list.action"/>
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
<div class="modal fade" id="depositEditDialog" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="myModalLabel2">更新押金信息</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" id="edit_deposit_form">

                    <input type="hidden" id="edit_id" name="id"/>
                    <div class="form-group">
                        <label for="edit_property_leasing_num" class="col-sm-2 control-label">租凭合同编号</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="edit_property_leasing_num" placeholder="租凭合同编号"
                                   name="property_leasing_num">
                        </div>
                    </div>

                 <%--   <div class="form-group">
                        <label for="edit_rent_start_time" class="col-sm-2 control-label">本次收取费用开始时间</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="edit_rent_start_time" placeholder="本次收取费用开始时间"
                                   name="rent_start_time">
                        </div>
                    </div>--%>

                    <div class="form-group date form_date">
                        <label for="edit_rent_start_time" class="col-sm-2 control-label">本次收取费用开始时间</label>
                        <div class="col-sm-10">
                            <input name="rent_start_time" id="edit_rent_start_time" type='text'
                                   class="form-control" placeholder="本次收取费用开始时间"/>
                        </div>
                    </div>

               <%--     <div class="form-group">
                        <label for="edit_rent_end_time" class="col-sm-2 control-label">本次收取费用结束时间</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="edit_rent_end_time" placeholder="本次收取费用结束时间"
                                   name="rent_end_time">
                        </div>
                    </div>--%>

                    <div class="form-group date form_date">
                        <label for="edit_rent_end_time" class="col-sm-2 control-label">本次收取费用结束时间</label>
                        <div class="col-sm-10">
                            <input name="rent_end_time" id="edit_rent_end_time" type='text'
                                   class="form-control" placeholder="本次收取费用结束时间"/>
                        </div>
                    </div>

                   <%-- <div class="form-group date form_date">
                        <label for="edit_rent_end_time" class="col-sm-2 control-label">本次收取费用开始时间</label>
                        <div class="col-sm-10">
                            <input name="rent_start_time" id="edit_rent_end_time" type='text' value="${deadline }"
                                   class="form-control" readonly="readonly" placeholder="本次收取费用开始时间"/>
                        </div>
                    </div>--%>

                    <div class="form-group">
                        <label for="edit_deposit" class="col-sm-2 control-label">应收保证金</label>
                        <div class="col-sm-10">
                            <input type="number" class="form-control" id="edit_deposit" placeholder="应收保证金（元）"
                                   name="deposit">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="edit_reality_deposit" class="col-sm-2 control-label">实际保证金</label>
                        <div class="col-sm-10">
                            <input type="number" class="form-control" id="edit_reality_deposit" placeholder="实际保证金（元）"
                                   name="reality_deposit">
                        </div>
                    </div>


                    <div class="form-group date form_date">
                        <label for="edit_deadline" class="col-sm-2 control-label">到帐时间</label>
                        <div class="col-sm-10">
                            <input name="deadline" id="edit_deadline" type='text'
                                   class="form-control"  placeholder="到帐时间"/>
                        </div>

                    </div>


                    <%--  <div class="form-group">
                          <label for="edit_state" style="float:left;padding:7px 15px 0 27px;">押金状态</label>
                          <div class="col-sm-10">
                              <select	class="form-control" id="edit_state" placeholder="押金状态" name="state">
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
                <button type="button" class="btn btn-primary" onclick="updateDeposit()">保存修改</button>
            </div>
        </div>
    </div>
</div>


<!-- 创建客户模态框 -->
<div class="modal fade" id="newDepositDialog" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="myModalLabel">添加押金信息</h4>
            </div>
            <div class="modal-body">

                <form class="form-horizontal" id="new_deposit_form">
                    <div class="form-group">
                        <label for="new_property_leasing_num" class="col-sm-2 control-label">
                            租凭合同编号
                        </label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="new_property_leasing_num" placeholder="租凭合同编号"
                                   name="property_leasing_num"/>
                        </div>
                    </div>



                    <div class="form-group date form_date">
                        <label for="new_rent_start_time" class="col-sm-2 control-label">本次收取费用开始时间</label>
                        <div class="col-sm-10">
                            <input name="rent_start_time" id="new_rent_start_time" type='text'
                                   class="form-control" placeholder="本次收取费用开始时间"/>
                        </div>
                    </div>



                    <div class="form-group date form_date">
                        <label for="new_rent_end_time" class="col-sm-2 control-label">本次收取费用结束时间</label>
                        <div class="col-sm-10">
                            <input name="rent_end_time" id="new_rent_end_time" type='text'
                                   class="form-control" placeholder="本次收取费用结束时间"/>
                        </div>
                    </div>


                    <div class="form-group">
                        <label for="new_deposit" class="col-sm-2 control-label">应收保证金</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="new_deposit" placeholder="应收保证金（元）"
                                   name="deposit">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="new_reality_deposit" class="col-sm-2 control-label">实际保证金</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="new_reality_deposit" placeholder="实际保证金（元）"
                                   name="reality_deposit">
                        </div>
                    </div>



                    <div class="form-group date form_date">
                        <label for="new_deadline" class="col-sm-2 control-label">到帐时间</label>
                        <div class="col-sm-10">
                            <input name="deadline" id="new_deadline" type='text' value="${deadline }"
                                   class="form-control"  placeholder="到帐时间"/>
                        </div>
                        <%-- <span class="input-group-addon input-sm"><span class="glyphicon glyphicon-calendar"></span></span>--%>
                    </div>

                    <%--<div class="form-group">
                        <label for="new_state" style="float:left;padding:7px 15px 0 27px;">押金状态</label>
                        <div class="col-sm-10">
                            <select	class="form-control" id="new_state" placeholder="押金状态" name="state">
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
                <button type="button" class="btn btn-primary" onclick="createDeposit()">创建资产</button>
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


    function editDeposit(id) {
        $.ajax({
            type: "get",
            url: "<%=basePath%>assertDeposit/edit.action",
            data: {"id": id},
            success: function (data) {
                $("#edit_id").val(data.id);
                $("#edit_property_leasing_num").val(data.property_leasing_num);
                $("#edit_rent_start_time").val(data.rent_start_time);
                $("#edit_rent_end_time").val(data.rent_end_time);
                $("#edit_reality_deposit").val(data.reality_deposit);
                $("#edit_deposit").val(data.deposit);
                $("#edit_deadline").val(data.deadline);
                /* $("#edit_state").val(data.state);*/
            }
        });
    }


    function updateDeposit() {
        $.post("<%=basePath%>assertDeposit/update.action", $("#edit_deposit_form").serialize(), function (data) {
            if (data.code == 0) {
                alert(data.msg);
                window.location.reload();
            } else {
                alert(data.msg);
                window.location.reload();
            }
        });
    }

    function deleteDeposit(id) {
        if (confirm('确实要删除押金信息吗?')) {
            $.post("<%=basePath%>assertDeposit/delete.action", {"id": id}, function (data) {
                alert("押金信息删除成功！");
                window.location.reload();

            });
        }
    }

    function clearDeposit() {
        $("#new_property_leasing_num").val("");
        $("#new_rent_start_time").val("");
        $("#new_rent_end_time").val("");
        $("#new_reality_deposit").val("");
        $("#new_deposit").val("");
        $("#edit_deadline").val("");
    }


    // 创建客户
    function createDeposit() {
        $.post("<%=basePath%>assertDeposit/create.action",
            $("#new_deposit_form").serialize(), function (data) {
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

<script type="text/javascript" src="${pageContext.request.contextPath}/bootstramp/bootstrap/js/bootstrap.min.js"
        charset="UTF-8"></script>
<%--<script type="text/javascript" src="${pageContext.request.contextPath}/bootstramp/jquery/jquery-1.8.3.min.js"></script>--%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap-datetimepicker.js"
        charset="UTF-8"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/locales/bootstrap-datetimepicker.zh-CN.js"
        charset="UTF-8"></script>

<script type="text/javascript">
    //选择年月的
    $('#edit_deadline').datetimepicker({
        format: 'yyyymmdd',//显示格式
        todayHighlight: 1,//今天高亮
        minView:2,//设置只显示到月份
        startView:2,
        forceParse: 0,
        todayBtn: 1,
        showMeridian: 1,
        autoclose: 1,
        language: 'zh-CN'
    });


    $('#new_deadline').datetimepicker({
        format: 'yyyymmdd',//显示格式
        todayHighlight: 1,//今天高亮
        minView:2,//设置只显示到月份
        startView:2,
        forceParse: 0,
        todayBtn: 1,
        showMeridian: 1,
        autoclose: 1,
        language: 'zh-CN'
    });

    $('#edit_rent_start_time').datetimepicker({
        format: 'yyyymmdd',//显示格式
        todayHighlight: 1,//今天高亮
        minView:2,//设置只显示到月份
        startView:2,
        forceParse: 0,
        todayBtn: 1,
        showMeridian: 1,
        autoclose: 1,
        language: 'zh-CN'
    });


    $('#edit_rent_end_time').datetimepicker({
        format: 'yyyymmdd',//显示格式
        todayHighlight: 1,//今天高亮
        minView:2,//设置只显示到月份
        startView:2,
        forceParse: 0,
        todayBtn: 1,
        showMeridian: 1,
        autoclose: 1,
        language: 'zh-CN'
    });

    $('#new_rent_start_time').datetimepicker({
        format: 'yyyymmdd',//显示格式
        todayHighlight: 1,//今天高亮
        minView:2,//设置只显示到月份
        startView:2,
        forceParse: 0,
        todayBtn: 1,
        showMeridian: 1,
        autoclose: 1,
        language: 'zh-CN'
    });


    $('#new_rent_end_time').datetimepicker({
        format: 'yyyymmdd',//显示格式
        todayHighlight: 1,//今天高亮
        minView:2,//设置只显示到月份
        startView:2,
        forceParse: 0,
        todayBtn: 1,
        showMeridian: 1,
        autoclose: 1,
        language: 'zh-CN'
    });


</script>


</body>

</html>
