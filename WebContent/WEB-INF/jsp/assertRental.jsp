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

    <title>租金管理</title>

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
                <h1 class="page-header">租金收入管理</h1>
            </div>
            <!-- /.col-lg-12 -->
        </div>
        <!-- /.row -->
        <div class="panel panel-default">
            <div class="panel-body">
                <form class="form-inline" action="${pageContext.request.contextPath }/assertRental/list.action"
                      method="get" id="mainForm">
                    <div class="form-group">
                        <label for="property_leasing_num">合同编号</label>
                        <input type="text" class="form-control" id="property_leasing_num"
                               value="${property_leasing_num }" name="property_leasing_num">
                    </div>

                    <div class="form-group">
                        <label for="state">租金到账状态</label>
                        <select class="form-control" id="state" placeholder="租金到账状态" name="state">
                            <option value="">--请选择--</option>
                            <c:forEach items="${stateType}" var="item">
                                <option value="${item.dict_id}"<c:if
                                        test="${item.dict_id == state}"> selected</c:if>>${item.dict_item_name }</option>
                            </c:forEach>
                        </select>
                    </div>

                    <button type="submit" class="btn btn-primary">查询</button>
                    <security:authorize access="hasAnyRole('ROLE_ADMIN','ROLE_CONTRACT')">
                        <a href="#" class="btn btn-primary" data-toggle="modal"
                           data-target="#newRentalDialog" onclick="clearRental()">新建</a>
                    </security:authorize>
                    <a href="#" class="btn btn-primary "
                       onclick="downloadAssertRental()">导出本页</a>
                    <a href="#" class="btn btn-primary "
                       onclick="downloadAssertRentalAll()">导出全部</a>

                </form>
            </div>
        </div>


        <div class="row">
            <div class="col-lg-12">
                <div class="panel panel-default">
                    <div class="panel-heading">租金信息列表</div>
                    <!-- /.panel-heading -->
                    <table class="table table-bordered table-striped">
                        <thead>
                        <tr>
                            <th>序号</th>
                            <th>租凭合同编号</th>
                            <th>本次收取费用开始时间</th>
                            <th>本次收取费用结束时间</th>
                            <th>截止本月应收租金(元)</th>
                            <th>实际已收租金（元）</th>
                            <th>本次实收租金（元)</th>
                            <th>收费时间</th>
                            <th>状态</th>
                            <%--             <security:authorize access="hasAnyRole('ROLE_ADMIN','ROLE_CONTRACT')">--%>
                            <th>操作</th>
                            <%--            </security:authorize>--%>
                        </tr>
                        </thead>
                        <tbody align="center">
                        <c:forEach items="${page.rows}" var="row">
                            <tr>
                                <td>${row.id}</td>
                                <td>${row.property_leasing_num}</td>
                                <td><fmt:formatDate value="${row.rent_start_time}" pattern="yyyy-MM-dd"/></td>
                                <td><fmt:formatDate value="${row.rent_end_time}" pattern="yyyy-MM-dd"/></td>
                                <td>${row.rental}</td>
                                <td>${row.rent_recivied}</td>
                                <td>${row.reality_rental}</td>
                                <td><fmt:formatDate value="${row.deadline}" pattern="yyyy-MM-dd"/></td>
                                <c:if test="${'7' eq row.state}">
                                    <td>缴清</td>
                                </c:if>
                                <c:if test="${'6' eq row.state}">
                                    <td> 未缴清</td>
                                </c:if>

                                <td>
                                    <security:authorize access="hasAnyRole('ROLE_ADMIN','ROLE_RENTAL')">
                                        <a href="#" class="btn btn-primary btn-xs" data-toggle="modal"
                                           data-target="#EditRentalDialog" onclick="editRental(${row.id})">修改</a>

                                        <security:authorize access="hasAnyRole('ROLE_ADMIN')">
                                            <a href="#" class="btn btn-danger btn-xs"
                                               onclick="deleteRental(${row.id})">删除</a>
                                        </security:authorize>
                                    </security:authorize>
                                    <a href="${pageContext.request.contextPath}/totalRental/list.action?property_leasing_num=${row.property_leasing_num}"
                                       class="btn  btn-primary btn-xs" target="main">查看</a>
                                </td>


                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                    <div class="col-md-12 text-right">
                        <itcast:page url="${pageContext.request.contextPath }/assertRental/list.action"/>
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
<div class="modal fade" id="EditRentalDialog" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="myModalLabel2">更新租金信息</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" id="edit_rental_form">
                    <input type="hidden" id="edit_day" name="edit_day"/>
                    <input type="hidden" id="edit_id" name="id"/>
                    <div class="form-group">
                        <label for="edit_property_leasing_num" class="col-sm-2 control-label">租凭合同编号</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="edit_property_leasing_num" placeholder="租凭合同编号"
                                   name="property_leasing_num">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="edit_year_months" style="float:left;padding:7px 15px 0 27px;">对应年月</label>
                        <div class="col-sm-10">
                            <select class="form-control" id="edit_year_months" placeholder="对应年月" name="year_months">

                                <%-- <c:forEach items="${rentalStateType}" var="item">
                                     <option value="${item.dict_id}"<c:if
                                             test="${item.dict_id == state}"> selected</c:if>>${item.dict_item_name }</option>
                                 </c:forEach>--%>
                            </select>
                        </div>
                    </div>

                    <div class="form-group date form_date">
                        <label for="edit_rent_start_time" class="col-sm-2 control-label">本次收取费用开始时间</label>
                        <div class="col-sm-10">
                            <input name="rent_start_time" id="edit_rent_start_time" type='text'
                                   class="form-control picket" placeholder="本次收取费用开始时间"/>
                        </div>
                    </div>


                    <div class="form-group date form_date">
                        <label for="edit_rent_end_time" class="col-sm-2 control-label">本次收取费用结束时间</label>
                        <div class="col-sm-10">
                            <input name="rent_end_time" id="edit_rent_end_time" type='text'
                                   class="form-control picket" placeholder="本次收取费用结束时间"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="edit_rent_received" class="col-sm-2 control-label">实际已收租金</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="edit_rent_received" placeholder="实际已收租金（元）"
                                   name="rent_recivied">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="edit_rental" class="col-sm-2 control-label">截止当前应收租金</label>
                        <div class="col-sm-10">
                            <input type="number" class="form-control" id="edit_rental" placeholder="截止当前应收租金（元）"
                                   name="rental">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="edit_reality_rental" class="col-sm-2 control-label">本次收取租金</label>
                        <div class="col-sm-10">
                            <input type="number" class="form-control" id="edit_reality_rental" placeholder="本次收取租金（元）"
                                   name="reality_rental">
                        </div>
                    </div>

                    <div class="form-group date form_date">
                        <label for="edit_deadline" class="col-sm-2 control-label">到帐时间</label>
                        <div class="col-sm-10">
                            <input name="deadline" id="edit_deadline" type='text'
                                   class="form-control picket" placeholder="到帐时间"/>
                        </div>
                    </div>


                    <%--<div class="form-group">
                        <label for="edit_state" style="float:left;padding:7px 15px 0 27px;">租金状态</label>
                        <div class="col-sm-10">
                            <select	class="form-control" id="edit_state" placeholder="租金状态" name="state">
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
                <button type="button" class="btn btn-primary" onclick="updateRental()">保存修改</button>
            </div>
        </div>
    </div>
</div>


<!-- 创建租金框 -->
<div class="modal fade" id="newRentalDialog" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="myModalLabel">添加租金信息</h4>
            </div>
            <div class="modal-body">

                <form class="form-horizontal" id="new_rental_form">
                    <input type="hidden" id="new_day" name="new_day"/>
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
                        <label for="new_year_months" style="float:left;padding:7px 15px 0 27px;">对应年月</label>
                        <div class="col-sm-10">
                            <select class="form-control" id="new_year_months" placeholder="对应年月" name="year_months">
                                <option value="">--请选择--</option>
                                <%-- <c:forEach items="${rentalStateType}" var="item">
                                     <option value="${item.dict_id}"<c:if
                                             test="${item.dict_id == state}"> selected</c:if>>${item.dict_item_name }</option>
                                 </c:forEach>--%>
                            </select>
                        </div>
                    </div>


                    <div class="form-group date form_date">
                        <label for="new_rent_start_time" class="col-sm-2 control-label">本次收取费用开始时间</label>
                        <div class="col-sm-10">
                            <input name="rent_start_time" id="new_rent_start_time" type='text'
                                   class="form-control picket" placeholder="本次收取费用开始时间"/>
                        </div>
                    </div>


                    <div class="form-group date form_date">
                        <label for="new_rent_end_time" class="col-sm-2 control-label">本次收取费用结束时间</label>
                        <div class="col-sm-10">
                            <input name="rent_end_time" id="new_rent_end_time" type='text'
                                   class="form-control picket" placeholder="本次收取费用结束时间"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="new_rent_received" class="col-sm-2 control-label">实际已收租金</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="new_rent_received" placeholder="实际已收租金（元）"
                                   name="rent_recivied">
                        </div>
                    </div>


                    <div class="form-group">
                        <label for="new_rental" class="col-sm-2 control-label">截止本月应收租金</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="new_rental" placeholder="截止本月应收租金（元）"
                                   name="rental">
                        </div>
                    </div>


                    <div class="form-group">
                        <label for="new_month_rental" class="col-sm-2 control-label">本月应收租金</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="new_month_rental" placeholder="本月应收租金（元）"
                                   name="month_rental">
                        </div>
                    </div>


                    <div class="form-group">
                        <label for="new_reality_rental" class="col-sm-2 control-label">本次收取租金</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="new_reality_rental" placeholder="本次收取租金（元）"
                                   name="reality_rental">
                        </div>
                    </div>


                    <div class="form-group date form_date">
                        <label for="new_deadline" class="col-sm-2 control-label">到帐时间</label>
                        <div class="col-sm-10">
                            <input name="deadline" id="new_deadline" type='text'
                                   class="form-control picket" placeholder="到帐时间"/>
                        </div>
                    </div>


                    <%--       <div class="form-group">
                               <label for="new_state" style="float:left;padding:7px 15px 0 27px;">租金状态</label>
                               <div class="col-sm-10">
                                   <select	class="form-control" id="new_state" placeholder="租金状态" name="state">
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
                <button type="button" class="btn btn-primary" onclick="createRental()">创建</button>
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


    function editRental(id) {
        $.ajax({
            type: "get",
            url: "<%=basePath%>assertRental/edit.action",
            data: {"id": id},
            success: function (data) {
                $("#edit_id").val(data.id);
                $("#edit_property_leasing_num").val(data.property_leasing_num);
                $("#edit_rent_start_time").val(data.rent_start_time);
                $("#edit_rent_end_time").val(data.rent_end_time);
                $("#edit_reality_rental").val(data.reality_rental);
                $("#edit_rental").val(data.rental);
                $("#edit_deadline").val(data.deadline);
                /*     $("#edit_state").val(data.state);*/
                $("#edit_day").val(data.day);
                $("#edit_rent_received").val(data.rent_recivied);
                var year_months = data.rent_start_time.substring(0, 6);

                for (var i = 0; i < data.totalRentals.length; i++) {
                    if (data.totalRentals[i].year_months == year_months) {
                        var option = new Option(data.totalRentals[i].year_months, data.totalRentals[i].id);
                        option.selected = true;
                        document.getElementById("edit_year_months").options.add(option);
                    } else {
                        document.getElementById("edit_year_months").options.add(new Option(data.totalRentals[i].year_months, data.totalRentals[i].id));
                    }
                }
            }
        });
    }


    //导出
    function downloadAssertRental() {
        $("#mainForm").attr("action", "${pageContext.request.contextPath }/assertRental/downloadAssertRental.action").submit();
        $("#mainForm").attr("action", "${pageContext.request.contextPath }/assertRental/list.action");
    }

    //导出
    function downloadAssertRentalAll() {
        $("#mainForm").attr("action", "${pageContext.request.contextPath }/assertRental/downloadAssertRentalAll.action").submit();
        $("#mainForm").attr("action", "${pageContext.request.contextPath }/assertRental/list.action");
    }

    function updateRental() {
        $.post("<%=basePath%>assertRental/update.action", $("#edit_rental_form").serialize(), function (data) {
            if (data.code == 0) {
                alert(data.msg);
                window.location.reload();
            } else {
                alert(data.msg);
                window.location.reload();
            }
        });
    }

    function deleteRental(id) {
        if (confirm('确实要删除租金信息吗?')) {
            $.post("<%=basePath%>assertRental/delete.action", {"id": id}, function (data) {
                alert("租金信息删除成功！");
                window.location.reload();

            });
        }
    }

    function clearRental() {
        $("#new_property_leasing_num").val("");
        $("#new_rent_start_time").val("");
        $("#new_rent_end_time").val("");
        $("#new_reality_rental").val("");
        $("#new_rental").val("");
        $("#new__deadline").val("");
        /*     $("#new_state").val("");*/
        $("#new_year_months").val("");
        $("#new_rent_received").val("");
    }


    // 创建客户
    function createRental() {
        $.post("<%=basePath%>assertRental/create.action",
            $("#new_rental_form").serialize(), function (data) {
                if (data.code == 0) {
                    alert(data.msg);
                    window.location.reload();
                } else {
                    alert(data.msg);
                    window.location.reload();
                }
            });
    }

    $('#new_property_leasing_num').blur(function () {
        var property_leasing_num = $('#new_property_leasing_num').val();
        $.ajax({
            type: "get",
            url: "<%=basePath%>propertyLeasing/editRentalByLeasingNum.action",
            data: {"property_leasing_num": property_leasing_num},
            success: function (data) {
                $("#new_rent_received").val(data.rent_recivied);
                $("#new_rental").val(data.rental);
                $("#new_day").val(data.day);
                $("#new_reality_rental").val(data.reality_rental);
                for (var i = 0; i < data.totalRentals.length; i++) {
                    document.getElementById("new_year_months").options.add(new Option(data.totalRentals[i].year_months, data.totalRentals[i].id));
                }
            }
        });

    });


    $('#new_year_months').change(function () {
        var time = $("#new_year_months").find("option:selected").text();
        var year = time.substring(0, 4);
        var month = time.substring(4, 6);
        var day = $("#new_day").val();
        var nextSize = new Date(year, parseInt(month), 0).getDate();//获取这个月的总天数
        if (nextSize < parseInt(day)) {
            day = nextSize;
        }
        var startTime = year + month + day;
        var endTime = getXmonthToday(1, year, month, day);
        $("#new_rent_start_time").val(startTime);
        $("#new_rent_end_time").val(endTime);
    });

    $('#edit_year_months').change(function () {
        var time = $("#edit_year_months").find("option:selected").text();
        var year = time.substring(0, 4);
        var month = time.substring(4, 6);
        var day = $("#edit_day").val();
        var nextSize = new Date(year, parseInt(month), 0).getDate();//获取这个月的总天数
        if (nextSize < parseInt(day)) {
            day = nextSize;
        }
        var startTime = year + month + day;
        var endTime = getXmonthToday(1, year, month, day);
        $("#edit_rent_start_time").val(startTime);
        $("#edit_rent_end_time").val(endTime);
    });

    function getXmonthToday(type, year, month, day) {
        // type 0 是当天 -1 是上个月   1是下个月
        // var now = new Date(); 可以传值调式 now = new Date(2019,2,30); 今天是3月30号
        /* var year = now.getFullYear();//getYear()+1900=getFullYear()
         var month = now.getMonth() + 1;//0-11表示1-12月
         var day = now.getDate(); // 当天日期*/
        /* if (parseInt(month) < 10) {
             month = "0" + month;
         }
         if (parseInt(day) < 10) {
             day = "0" + day;
         }*/
        /* now = year + '-' + month + '-' + day; // 如果取当月日期可直接 return 返回*/
        var preMonth = parseInt(month) - 1;
        preMonth = preMonth < 10 ? '0' + preMonth : preMonth; // 获取上个月的值
        var nextMonth = parseInt(month) + 1;
        nextMonth = nextMonth < 10 ? '0' + nextMonth : nextMonth; // 获取下个月个月的值

        if (parseInt(month) == 1 && type == -1) {//如果是1月份，要上个月 ，则取上一年的12月份
            return (parseInt(year) - 1) + '12' + day;
        } else if (parseInt(month) == 12 && type == 1) { // 如果是12月，要下个月，取下一年的1月
            return (parseInt(year) + 1) + '01' + day;
        }
        var preSize = new Date(year, parseInt(month) - 1, 0).getDate();//上月总天数
        var nextSize = new Date(year, parseInt(month) + 1, 0).getDate();//下月总天数
        console.log(preSize, nextSize)
        if (preSize < parseInt(day) && type == -1) {// 取上个月，如果上个月总天数小于本月今天，取上个月最后一天
            return year + preMonth + preSize;
        } else if (nextSize < parseInt(day) && type == 1) { // 如果下个月总天数小于本月今天，取下个月最后一天
            return year + nextMonth + nextSize;
        }
        if (type == -1) {
            return year + preMonth + day;
        } else /*(type == 1)*/ {
            return year + nextMonth + day;
        }

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
    $('.picket').datetimepicker({
        format: 'yyyymmdd',//显示格式
        todayHighlight: 1,//今天高亮
        minView: 2,//设置只显示到月份
        startView: 2,
        forceParse: 0,
        todayBtn: 1,
        showMeridian: 1,
        autoclose: 1,
        language: 'zh-CN'
    });


</script>


</body>

</html>
