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

    <title>资源管理系统</title>

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
    <link href="${pageContext.request.contextPath}/css/bootstrap-datetimepicker.css" rel="stylesheet"/>
    <%--<link href="<%=basePath%>/bootstramp/bootstrap/css/bootstrap.min.css" rel="stylesheet">--%>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
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
                <h1 class="page-header">租金明细</h1>
            </div>
            <!-- /.col-lg-12 -->
        </div>
        <!-- /.row -->
        <div class="panel panel-default">
            <div class="panel-body">
                <form class="form-inline" action="${pageContext.request.contextPath }/totalRental/list.action"
                      method="get">

                    <div class="form-group">
                        <label for="community_name">小区名称</label>
                        <input type="text" class="form-control" id="community_name" value="${community_name }"
                               name="community_name">
                    </div>

                    <div class="form-group">
                        <label for="property_leasing_num">合同编号</label>
                        <input type="text" class="form-control" id="property_leasing_num"
                               value="${property_leasing_num }" name="property_leasing_num">
                    </div>

                    <div class="form-group date form_date">
                        <label for="year_months">按年月查找</label>
                        <input name="year_months" id="year_months" type='text' value="${year_months }"
                               class="form-control" readonly="readonly"/>
                        <%-- <span class="input-group-addon input-sm"><span class="glyphicon glyphicon-calendar"></span></span>--%>
                    </div>
                    <button type="submit" class="btn btn-primary">查询</button>
                    <a href="#" class="btn btn-primary" data-toggle="modal"
                       data-target="#newTotalRentalDialog" onclick="clearTotalRental()">新建</a>
                </form>
            </div>
        </div>


        <div class="row">
            <div class="col-lg-12">
                <div class="panel panel-default">
                    <div class="panel-heading">租金信息列表</div>
                    <table class="table table-bordered table-striped">
                        <thead>
                        <tr>
                            <th>序号</th>
                            <th>合同编号</th>
                            <th>小区编号</th>
                            <th>栋号</th>
                            <th>承租人</th>
                            <th>承租位置</th>
                            <th>应收租金</th>
                            <th>实收租金</th>
                            <th>对应月份</th>
                            <th>到帐时间</th>
                            <th>操作</th>
                        </tr>
                        </thead>
                        <tbody align="center">
                        <c:forEach items="${page.rows}" var="row">
                            <tr>
                                <td>${row.id}</td>
                                <td>${row.property_leasing_num}</td>
                                <td>${row.community_name}</td>
                                <td>${row.building_num}</td>
                                <td>${row.tenant}</td>
                                <td>${row.rentalLocation}</td>
                                <td>${row.rental}</td>
                                <td>${row.reality_rental}</td>
                                <td>${row.year_months}</td>
                                <td><fmt:formatDate value="${row.deadline}" pattern="yyyy-MM-dd"/></td>
                                <td>
                                    <a href="#" class="btn btn-primary btn-xs" data-toggle="modal"
                                       data-target="#EditTotalRentalDialog" onclick="editTotalRental(${row.id})">修改</a>
                                    <a href="#" class="btn btn-danger btn-xs"
                                       onclick="deleteTotalRental(${row.id})">删除</a>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                    <div class="col-md-12 text-right">
                        <itcast:page url="${pageContext.request.contextPath }/totalRental/list.action"/>
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

<!-- 编辑每个月租金对话框 -->
<div class="modal fade" id="EditTotalRentalDialog" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="myModalLabel2">更新合同租金收入信息</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" id="edit_TotalRental_form">

                    <input type="hidden" id="edit_id" name="id"/>
                    <div class="form-group">
                        <label for="edit_property_leasing_num" class="col-sm-2 control-label">租凭合同编号</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="edit_property_leasing_num" placeholder="租凭合同编号"
                                   name="property_leasing_num">
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
                        <label for="edit_rentalLocation" class="col-sm-2 control-label">位置</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="edit_rentalLocation" placeholder="位置"
                                   name="rentalLocation">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="edit_tenant" class="col-sm-2 control-label">租赁人</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="edit_tenant" placeholder="租赁人"
                                   name="tenant">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="edit_rental" class="col-sm-2 control-label">应收租金</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="edit_rental" placeholder="应收租金（元）"
                                   name="rental">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="edit_reality_rental" class="col-sm-2 control-label">实际收租金</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="edit_reality_rental" placeholder="截止抄表时间"
                                   name="reality_rental">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="edit_year_months" class="col-sm-2 control-label">年月</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="edit_year_months" placeholder="年月"
                                   name="year_months">
                        </div>
                    </div>


                    <div class="form-group">
                        <label for="edit_deadline" class="col-sm-2 control-label">缴费时间</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="edit_deadline" placeholder="缴费时间"
                                   name="deadline">
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary" onclick="updateTotalRental()">保存修改</button>
            </div>
        </div>
    </div>
</div>


<!-- 新建每个月租金对话框 -->
<div class="modal fade" id="newTotalRentalDialog" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="myModalLabel">添加合同租金收入信息</h4>
            </div>
            <div class="modal-body">

                <form class="form-horizontal" id="new_TotalRental_form">
                    <div class="form-group">
                        <label for="new_property_leasing_num" class="col-sm-2 control-label">
                            租凭合同编号
                        </label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="new_property_leasing_num" placeholder="租凭合同编号"
                                   name="property_leasing_num"/>
                        </div>
                    </div>


                    <div class="form-group">
                        <label for="new_community_name" class="col-sm-2 control-label">小区名称</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="new_community_name" placeholder="小区名称"
                                   name="community_name">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="new_building_num" class="col-sm-2 control-label">栋号</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="new_building_num" placeholder="栋号"
                                   name="building_num">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="new_rentalLocation" class="col-sm-2 control-label">位置</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="new_rentalLocation" placeholder="位置"
                                   name="rentalLocation">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="new_tenant" class="col-sm-2 control-label">租赁人</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="new_tenant" placeholder="租赁人"
                                   name="tenant">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="new_rental" class="col-sm-2 control-label">应收租金</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="new_rental" placeholder="应收租金（元）"
                                   name="rental">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="new_reality_rental" class="col-sm-2 control-label">实际收租金</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="new_reality_rental" placeholder="截止抄表时间"
                                   name="reality_rental">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="new_year_months" class="col-sm-2 control-label">年月</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="new_year_months" placeholder="年月"
                                   name="year_months">
                        </div>
                    </div>


                    <div class="form-group">
                        <label for="new_deadline" class="col-sm-2 control-label">缴费时间</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="new_deadline" placeholder="缴费时间"
                                   name="deadline">
                        </div>
                    </div>

                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary" onclick="createTotalRental()">创建</button>
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


    function editTotalRental(id) {
        $.ajax({
            type: "get",
            url: "<%=basePath%>totalRental/edit.action",
            data: {"id": id},
            success: function (data) {
                $("#edit_id").val(data.id);
                $("#edit_property_leasing_num").val(data.property_leasing_num);
                $("#edit_community_name").val(data.community_name);
                $("#edit_tenant").val(data.tenant);
                $("#edit_building_num").val(data.building_num);
                $("#edit_rentalLocation").val(data.rentalLocation);
                $("#edit_deadline").val(data.deadline);
                $("#edit_year_months").val(data.year_months);
                $("#edit_rental").val(data.rental);
                $("#edit_reality_rental").val(data.reality_rental);
            }
        });
    }


    function updateTotalRental() {
        $.post("<%=basePath%>totalRental/update.action", $("#edit_TotalRental_form").serialize(), function (data) {
            if (data.code == 0) {
                alert(data.msg);
                window.location.reload();
            } else {
                alert(data.msg);
                window.location.reload();
            }
        });
    }

    function deleteTotalRental(id) {
        if (confirm('确实要删除租金信息吗?')) {
            $.post("<%=basePath%>totalRental/delete.action", {"id": id}, function (data) {
                alert("每个月租金信息删除成功！");
                window.location.reload();

            });
        }
    }

    function clearTotalRental() {
        $("#new_property_leasing_num").val("");
        $("#new_community_name").val("");
        $("#new_year_months").val("");
        $("#new_rental").val("");
        $("#new_reality_rental").val("");
        $("#new_deadline").val("");
        $("#new_rentalLocation").val("");
        $("#new_tenant").val("");
        $("#new_building_num").val("");
    }


    function createTotalRental() {
        $.post("<%=basePath%>totalRental/create.action",
            $("#new_power_form").serialize(), function (data) {
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
    $('#year_months').datetimepicker({
        format: 'yyyymm',
        weekStart: 1,
        autoclose: true,
        startView: 3,
        minView: 3,
        forceParse: false,
        language: 'zh-CN'
    });

    $("#new_property_leasing_num").blur(function () {
        var property_leasing_num = $("#new_property_leasing_num").val();
        $.ajax({
            type: "get",
            url: "<%=basePath%>propertyLeasing/findByPropertyLeasingNum.action",
            data: {"property_leasing_num": property_leasing_num},
            success: function (data) {
                $("#new_community_name").val(data.community_name);
                $("#new_tenant").val(data.tenant);
                $("#new_building_num").val(data.building_num);
                $("#new_rentalLocation").val(data.rentalLocation);
            }
        });
    });


</script>

</body>

</html>
