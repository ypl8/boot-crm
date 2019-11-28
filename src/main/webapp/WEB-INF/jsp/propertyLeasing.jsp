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
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>资产管理-BootCRM</title>

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

    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
    <link rel="stylesheet"
          href="https://cdn.jsdelivr.net/npm/bootstrap-select@1.13.9/dist/css/bootstrap-select.min.css">
    <%-- <link rel="stylesheet" href="css/bootstrap.min.css">--%>
    <link href="<%=basePath%>css/bootstrap.min.css" rel="stylesheet">
    <%--    <script src="<%=basePath%>js/bootstrap.min.js"></script>--%>

    <!-- Latest compiled and minified JavaScript -->
    <%--  <script src="https://cdn.jsdelivr.net/npm/bootstrap-select@1.13.9/dist/js/bootstrap-select.min.js"></script>--%>

    <link href="${pageContext.request.contextPath}/css/bootstrap-datetimepicker.css" rel="stylesheet"/>
    <%--<link href="<%=basePath%>/bootstramp/bootstrap/css/bootstrap.min.css" rel="stylesheet">--%>


    <%--    <script src="<%=basePath%>js/moment-with-locales.min.js"></script>--%>
    <%--    <script  type="text/javascript" src="${pageContext.request.contextPath}/js/locales/bootstrap-datetimepicker.zh-CN.js"></script>
        <script  type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap-datetimepicker.js"></script>--%>

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
    <!-- Latest compiled and minified CSS -->
    <!-- jQuery -->
    <script src="<%=basePath%>js/jquery.min.js"></script>


    <!-- Metis Menu Plugin JavaScript -->
    <script src="<%=basePath%>js/metisMenu.min.js"></script>

    <!-- DataTables JavaScript -->
    <script src="<%=basePath%>js/jquery.dataTables.min.js"></script>
    <script src="<%=basePath%>js/dataTables.bootstrap.min.js"></script>

    <!-- Custom Theme JavaScript -->
    <script src="<%=basePath%>js/sb-admin-2.js"></script>


    <!-- Bootstrap Core JavaScript -->
    <script src="<%=basePath%>js/bootstrap.min.js"></script>
    <%--    <script type="text/javascript" src="${pageContext.request.contextPath}/bootstramp/bootstrap/js/bootstrap.min.js"
                charset="UTF-8"></script>--%>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.12.2/js/bootstrap-select.min.js"></script>
    <%--<script type="text/javascript" src="${pageContext.request.contextPath}/bootstramp/jquery/jquery-1.8.3.min.js"></script>--%>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap-datetimepicker.js"
            charset="UTF-8"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/locales/bootstrap-datetimepicker.zh-CN.js"
            charset="UTF-8"></script>

</head>

<body>

<div id="wrapper">


    <div id="page-wrapper">
        <div class="row">
            <div class="col-lg-12">
                <h1 class="page-header">资产合同管理</h1>
            </div>
            <!-- /.col-lg-12 -->
        </div>
        <!-- /.row -->
        <div class="panel panel-default">
            <div class="panel-body">
                <form class="form-inline" id="mainForm"
                      action="${pageContext.request.contextPath }/propertyLeasing/list.action"
                      method="get">
                    <div class="form-group">
                        <label for="property_leasing_num">合同编号</label>
                        <input type="text" class="form-control" id="property_leasing_num"
                               value="${property_leasing_num }" name="property_leasing_num">
                    </div>

                    <div class="form-group" style="visibility: visible">
                        <label for="assert_num">资产编号</label>
                        <input type="text" class="form-control" id="assert_num" value="${assert_num }"
                               name="assert_num">
                    </div>

                    <div class="form-group">
                        <label for="community_name">小区名称</label>
                        <input type="text" class="form-control" id="community_name"
                               value="${community_name }" name="community_name">
                    </div>


                    <div class="form-group">
                        <label for="query_collect_rent_way">租金缴纳方式</label>
                        <select class="form-control" id="query_collect_rent_way" placeholder="租金缴纳方式"
                                name="collect_rent_way">
                            <option value="">--请选择--</option>
                            <c:forEach items="${rentalWayType}" var="item">
                                <option value="${item.dict_id}"<c:if
                                        test="${item.dict_id == collect_rent_way}"> selected</c:if>>${item.dict_item_name }</option>
                            </c:forEach>
                        </select>
                    </div>

                    <div class="form-group">
                        <label for="query_collect_rate_way">水电费缴费方式</label>
                        <select class="form-control" id="query_collect_rate_way" placeholder="水电费缴费方式"
                                name="collect_rate_way">
                            <option value="">--请选择--</option>
                            <c:forEach items="${waterAndEletricWayType}" var="item">
                                <option value="${item.dict_id}"<c:if
                                        test="${item.dict_id == collect_rate_way}"> selected</c:if>>${item.dict_item_name }</option>
                            </c:forEach>
                        </select>
                    </div>

                    <div class="form-group">
                        <label for="query_property_leasing_state">合同状态</label>
                        <select class="form-control" id="query_property_leasing_state" placeholder="合同状态"
                                name="property_leasing_state">
                            <option value="">--请选择--</option>
                            <c:forEach items="${contractStateType}" var="item">
                                <option value="${item.dict_id}"<c:if
                                        test="${item.dict_id == property_leasing_state}"> selected</c:if>>${item.dict_item_name }</option>
                            </c:forEach>
                        </select>
                    </div>


                    <div class="form-group">
                        <label for="query_property_leasing_state">返迁安置</label>
                        <select class="form-control" id="query_property_leasing_typee" placeholder="返迁安置"
                                name="property_leasing_type">
                            <option value="">--请选择--</option>
                            <c:forEach items="${contractType}" var="item">
                                <option value="${item.dict_id}"<c:if
                                        test="${item.dict_id == property_leasing_type}"> selected</c:if>>${item.dict_item_name }</option>
                            </c:forEach>
                        </select>
                    </div>

                    <div class="form-group">
                        <label for="status">审核状态</label>
                        <select class="form-control" id="status" placeholder="审核状态" name="status">
                            <option value="">--请选择--</option>
                            <c:forEach items="${checkType}" var="item">
                                <option value="${item.dict_id}"<c:if
                                        test="${item.dict_id == status}"> selected</c:if>>${item.dict_item_name }</option>
                            </c:forEach>
                        </select>
                    </div>

                    <button type="submit" class="btn btn-primary">查询</button>
                    <security:authorize access="hasAnyRole('ROLE_ADMIN','ROLE_CONTRACT')">
                        <a href="#" class="btn btn-warning" data-toggle="modal"
                           data-target="#newPropertyLeasingDialog" onclick="clearPropertyLeasing()">新建</a>
                      <%--  <a href="#" class="btn btn-warning"&lt;%&ndash; data-toggle="modal"
                           data-target="#newPropertyLeasingDialog"&ndash;%&gt; onclick="clearPropertyLeasing()">新建</a>--%>
                        <a href="#" class="btn btn-info"
                           onclick="uploadPropertyLeasing()">导入</a>
                    </security:authorize>
                    <a href="#" class="btn btn-success"
                       onclick="downloadPropertyLeasing()">导出</a>
                    <a href="#" class="btn btn-success"
                       onclick="downloadPropertyLeasingAll()">导出全部</a>

                </form>
            </div>
        </div>


        <div class="row">
            <div class="col-lg-12">
                <div class="panel panel-default">
                    <div class="panel-heading">资产合同管理</div>
                    <!-- /.panel-heading -->
                    <table class="table table-bordered table-striped">
                        <thead>
                        <tr>
                            <th>序号</th>
                            <th>租凭合同编号</th>
                            <th>小区名称</th>
                            <th>承租人</th>
                            <%--               <th>承租面积</th>
                                           <th>合同签订时间</th>
                                           <th>租金收费标准</th>
                                           <th>月租</th>
                                           <th>免租期(月)</th>

                                           <th>合同期(年)</th>
                                           <th>租金缴纳方式</th>
                                           <th>租金缴纳时间</th>
                                           <th>物业收费标准</th>
                                           <th>月物业费</th>
                                           <th>保证金（元）</th>

                                           <th>保证金到账时间</th>
                                           <th>水费</th>
                                           <th>电费</th>
                                           <th>水电费收费方式</th>--%>
                            <th>租期开始时间</th>
                            <th>租期结束时间</th>
                            <th>合同状态</th>
                            <th>状态</th>
                          <%--  <security:authorize access="hasAnyRole('ROLE_ADMIN','ROLE_CONTRACT')">--%>
                                <th>操作</th>
                        <%--    </security:authorize>--%>
                            <th>资产管理</th>
                            <th>水电初始化管理</th>
                            <th>押金管理</th>
                            <th>租金管理</th>
                            <th>物业费管理</th>
                            <th>水抄表管理</th>
                            <th>水费管理</th>
                            <th>电操表管理</th>
                            <th>电费管理</th>
                        </tr>
                        </thead>
                        <tbody align="center">
                        <c:forEach items="${page.rows}" var="row">

                            <tr>

                                <td>${row.id}</td>
                                <td>${row.property_leasing_num}</td>
                                <td>${row.community_name}</td>
                                <td>${row.tenant}</td>
                                    <%--<td>${row.rental_area}</td>
                                    <td><fmt:formatDate value="${row.sign_in_time}" pattern="yyyy-MM-dd"/></td>
                                    <td>${row.rent_charge_standard}</td>
                                    <td>${row.monthly_rental}</td>
                                    <td>${row.rent_free_period}</td>

                                    <td>${row.rent_period}</td>
                                    <td>${row.collect_rent_way}</td>
                                    <td>${row.collect_rent_time}</td>
                                    <td>${row.estate_charge_standard}</td>
                                    <td>${row.estate_charge_month}</td>
                                    <td>${row.deposit}</td>
                                    <td><fmt:formatDate value="${row.deposit_time}" pattern="yyyy-MM-dd"/></td>
                                    <td>${row.water_rate}</td>
                                    <td>${row.power_rate}</td>
                                    <td>${row.collect_rate_way}</td>--%>
                                <td><fmt:formatDate value="${row.rent_start_time}" pattern="yyyy-MM-dd"/></td>
                                <td><fmt:formatDate value="${row.rent_end_time}" pattern="yyyy-MM-dd"/></td>
                                <c:if test="${'16' eq row.property_leasing_state}">
                                    <td>生效</td>
                                </c:if>
                                <c:if test="${'17' eq row.property_leasing_state}">
                                    <td> 过期</td>
                                </c:if>
                                <c:if test="${'18' eq row.property_leasing_state}">
                                    <td> 欠款</td>
                                </c:if>
                                    <%--   <td>${row.property_leasing_state}</td>--%>
                                    <%-- <td>${row.remark}</td>--%>

                                <c:if test="${'28' eq row.status}">
                                    <td> 未提交</td>
                                </c:if>
                                <c:if test="${'29' eq row.status}">
                                    <td> 审核中</td>
                                </c:if>
                                <c:if test="${'30' eq row.status}">
                                    <td> 审核通过</td>
                                </c:if>
                                <c:if test="${'31' eq row.status}">
                                    <td> 审核拒绝</td>
                                </c:if>
                                <td>
                                    <a href="${pageContext.request.contextPath}/propertyLeasing/show.action?id=${row.id}"
                                       class="btn  btn-primary btn-xs" <%--onclick="return showRental(${row.id})"--%>
                                       target="main">查看</a>
                                <security:authorize access="hasAnyRole('ROLE_ADMIN','ROLE_CONTRACT','ROLE_CONTRACTCHECK')">

                                        <security:authorize access="hasAnyRole('ROLE_CONTRACT')">
                                            <c:if test="${row.status eq '28'}">
                                                <a href="#" class="btn btn-success btn-xs"
                                                   onclick="propertyLeasingSubmit(${row.id})">提交审核</a>
                                            </c:if>
                                            <c:if test="${row.status eq '28' ||row.status eq '31'}">
                                                <a href="#" class="btn btn-warning btn-xs" data-toggle="modal"
                                                   data-target="#caseEditDialog"
                                                   onclick="editPropertyLeasing(${row.id})">修改</a>
                                            </c:if>

                                        </security:authorize>

                                        <security:authorize access="hasAnyRole('ROLE_CONTRACTCHECK')">
                                            <c:if test="${row.status eq '29'}">
                                                <a href="#" class="btn btn-success btn-xs"
                                                   data-target="#editCommentDialog" data-toggle="modal"
                                                   onclick="editPropertyLeasingComment(${row.id})">审核</a>
                                            </c:if>
                                        </security:authorize>

                                        <security:authorize access="hasAnyRole('ROLE_ADMIN')">
                                            <a href="#" class="btn btn-danger btn-xs"
                                               onclick="deletePropertyLeasing(${row.id})">删除</a>
                                            <c:if test="${row.status eq '29'}">
                                                <a href="#" class="btn btn-success btn-xs"
                                                   data-target="#editCommentDialog" data-toggle="modal"
                                                   onclick="editPropertyLeasingComment(${row.id})">审核</a>
                                            </c:if>
                                            <a href="#" class="btn btn-warning btn-xs" data-toggle="modal"
                                               data-target="#caseEditDialog"
                                               onclick="editPropertyLeasing(${row.id})">修改</a>
                                        </security:authorize>

                                        <a href="#" class="btn btn-success btn-xs" data-toggle="modal"
                                           data-target="#propertyLeasingCommentDialog"
                                           onclick="propertyLeasingComments(${row.id})">审核记录</a>



                                </security:authorize>
                                </td>
                                <td>

                                        <%--   <a href="#" class="btn btn-danger btn-xs" data-toggle="modal"
                                              data-target="#showAssertDialog"
                                              onclick="showAssert(${row.id})">查看</a>--%>
                                    <a href="${pageContext.request.contextPath}/assertInfol/list.action?property_leasing_num=${row.property_leasing_num}"
                                       class="btn  btn-primary btn-xs" target="main">查看</a>

                                </td>

                                <td>
                                    <security:authorize access="hasAnyRole('ROLE_ADMIN','ROLE_CONTRACT')">
                                        <a href="#" class="btn btn-info btn-xs" data-toggle="modal"
                                           data-target="#addAssertDialog"
                                           onclick="addAssert('${row.property_leasing_num}','${row.status}')">添加</a>
                                    </security:authorize>


                                    <a href="${pageContext.request.contextPath}/assertLeasing/list.action?property_leasing_num=${row.property_leasing_num}"
                                       class="btn  btn-primary btn-xs" target="main">查看</a>
                                </td>

                                <td>
                                    <security:authorize
                                            access="hasAnyRole('ROLE_ADMIN','ROLE_DEPOSIT')">
                                        <a href="#" class="btn btn-info  btn-xs" data-toggle="modal"
                                           data-target="#newDepositDialog"
                                           onclick="editDeposit(${row.id},'${row.status}')">添加</a>
                                    </security:authorize>
                                    <a href="${pageContext.request.contextPath}/assertDeposit/list.action?property_leasing_num=${row.property_leasing_num}"
                                       class="btn  btn-primary btn-xs" target="main">查看</a>
                                        <%--<a href="#" class="btn btn-danger btn-xs" data-toggle="modal"
                                           data-target="#showDepositDialog"
                                           onclick="showDeposit('${row.property_leasing_num}')">查看</a>--%>
                                </td>


                                <td>
                                    <security:authorize access="hasAnyRole('ROLE_ADMIN','ROLE_RENTAL')">
                                        <a href="#" class="btn btn-success btn-xs" data-toggle="modal"
                                           data-target="#newRentalDialog"
                                           onclick="editRental(${row.id},'${row.status}')">月缴</a>

                                        <a href="#" class="btn btn-warning btn-xs" data-toggle="modal"
                                           data-target="#newRentalByDefDialog"
                                           onclick="editRentalByDef(${row.id},'${row.status}')">自缴</a>
                                    </security:authorize>
                                    <a href="${pageContext.request.contextPath}/assertRental/list.action?property_leasing_num=${row.property_leasing_num}"
                                       class="btn  btn-primary btn-xs" <%--onclick="return showRental(${row.id})"--%>
                                       target="main">查看</a>

                                    <a href="${pageContext.request.contextPath}/totalRental/list.action?property_leasing_num=${row.property_leasing_num}"
                                       class="btn  btn-success btn-xs" <%--onclick="return showRental(${row.id})"--%>
                                       target="main">明细</a>
                                        <%--  <a href="#" class="btn btn-primary btn-xs" data-toggle="modal"
                                             data-target="#showRentalDialog"
                                             onclick=" showRental(${row.id})">查看</a>--%>
                                </td>

                                <td>
                                    <security:authorize access="hasAnyRole('ROLE_ADMIN','ROLE_ESTATE')">
                                        <a href="#" class="btn btn-info btn-xs" data-toggle="modal"
                                           data-target="#newEstateDialog"
                                           onclick="editEstate(${row.id},'${row.status}')">月缴</a>

                                        <a href="#" class="btn btn-warning btn-xs" data-toggle="modal"
                                           data-target="#newEstateByDefDialog"
                                           onclick="editEstateByDef(${row.id},'${row.status}')">自缴</a>
                                    </security:authorize>
                                        <%--   <a href="#" class="btn btn-primary btn-xs" data-toggle="modal"
                                              data-target="#showEstateDialog"
                                              onclick="showEstate(${row.id})">查看</a>--%>
                                    <a href="${pageContext.request.contextPath}/assertEstate/list.action?property_leasing_num=${row.property_leasing_num}"
                                       class="btn  btn-primary btn-xs" <%--onclick="return showRental(${row.id})"--%>
                                       target="main">查看</a>

                                    <a href="${pageContext.request.contextPath}/totalEstate/list.action?property_leasing_num=${row.property_leasing_num}"
                                       class="btn  btn-success btn-xs" <%--onclick="return showRental(${row.id})"--%>
                                       target="main">明细</a>
                                </td>


                                <td>
                                    <security:authorize access="hasAnyRole('ROLE_ADMIN','ROLE_WATER')">
                                        <a href="#" class="btn btn-success btn-xs" data-toggle="modal"
                                           data-target="#newWaterDialog"
                                           onclick="editWater(${row.id},'${row.status}')">添加</a>
                                    </security:authorize>
                                    <a href="${pageContext.request.contextPath}/assertWater/list.action?property_leasing_num=${row.property_leasing_num}"
                                       class="btn  btn-info btn-xs" <%--onclick="return showRental(${row.id})"--%>
                                       target="main">查看</a>
                                </td>
                                <td>

                                    <security:authorize access="hasAnyRole('ROLE_ADMIN','ROLE_WATER')">
                                        <a href="#" class="btn btn-warning btn-xs" data-toggle="modal"
                                           data-target="#newWaterRentDialog"
                                           onclick="editWaterRent(${row.id},'${row.status}')">添加</a>
                                    </security:authorize>
                                        <%-- <a href="#" class="btn btn-danger btn-xs" data-toggle="modal"
                                            data-target="#showEstateDialog"
                                            onclick="showEstate(${row.id})">查看</a>--%>
                                    <a href="${pageContext.request.contextPath}/assertWaterRent/list.action?property_leasing_num=${row.property_leasing_num}"
                                       class="btn  btn-primary btn-xs" <%--onclick="return showRental(${row.id})"--%>
                                       target="main">查看</a>
                                </td>


                                <td>
                                    <security:authorize access="hasAnyRole('ROLE_ADMIN','ROLE_WATER')">
                                        <a href="#" class="btn btn-success btn-xs" data-toggle="modal"
                                           data-target="#newPowerDialog"
                                           onclick="editPower(${row.id},'${row.status}')">录入</a>
                                    </security:authorize>
                                    <a href="${pageContext.request.contextPath}/assertPower/list.action?property_leasing_num=${row.property_leasing_num}"
                                       class="btn  btn-info btn-xs" <%--onclick="return showRental(${row.id})"--%>
                                       target="main">查看</a>
                                </td>

                                <td>

                                    <security:authorize access="hasAnyRole('ROLE_ADMIN','ROLE_WATER')">
                                        <a href="#" class="btn btn-warning btn-xs" data-toggle="modal"
                                           data-target="#newPowerRentDialog"
                                           onclick="editPowerRent(${row.id},'${row.status}')">添加</a>
                                    </security:authorize>
                                    <a href="${pageContext.request.contextPath}/assertPowerRent/list.action?property_leasing_num=${row.property_leasing_num}"
                                       class="btn  btn-primary btn-xs" <%--onclick="return showRental(${row.id})"--%>
                                       target="main">查看</a>
                                        <%-- <a href="#" class="btn btn-danger btn-xs" data-toggle="modal"
                                            data-target="#showEstateDialog"
                                            onclick="showEstate(${row.id})">查看</a>--%>
                                </td>

                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                    <div class="col-md-12 text-right">
                        <itcast:page url="${pageContext.request.contextPath }/propertyLeasing/list.action"/>
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

<div class="modal fade" id="propertyLeasingCommentDialog" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="myModalLabel3">显示审核信息</h4>
            </div>
            <div class="modal-body">
                <div class="row">
                    <div class="col-lg-12">
                        <div class="panel panel-default">
                            <div class="panel-heading">显示审核信息</div>
                            <!-- /.panel-heading -->
                            <table class="table table-bordered table-striped">
                                <thead>

                                <tr>
                                    <th>序号</th>
                                    <th>审批人</th>
                                    <th>审批备注</th>
                                    <th>审批时间</th>
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
        <%-- <div class="modal-footer">
             <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
             &lt;%&ndash;  <button type="button" class="btn btn-primary" onclick="addPropertyLeasing()">创建关系</button>&ndash;%&gt;
         </div>--%>
    </div>
</div>
<!-- 审核对话框 -->
<div class="modal fade" id="editCommentDialog" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="myModalLabe66">审核信息</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" id="edit_contract_comment_form">
                    <input type="hidden" id="edit_comment_id" name="id"/>

                    <div class="form-group">
                        <label for="edit_comment_state" style="float:left;padding:7px 15px 0 27px;">是否通过</label>
                        <div class="col-sm-10">
                            <select class="form-control" id="edit_comment_state" placeholder="是否审核通过"
                                    name="comment_state">
                                <option value="30" selected>--审核通过--</option>
                                <option value="31">--审核拒绝--</option>
                            </select>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="edit_comment" class="col-sm-2 control-label">批注</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="edit_comment" placeholder="批注"
                                   name="comment">
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary" onclick="addComment()">保存修改</button>
            </div>
        </div>
    </div>
</div>
<!-- 编辑合同对话框 -->
<div class="modal fade" id="caseEditDialog" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">

        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="myModalLabel">更新合同信息</h4>
            </div>

            <div class="modal-body">

                <form class="form-horizontal" id="edit_case_form">
                    <input type="hidden" id="edit_id" name="id"/>
                    <div class="form-group">
                        <label for="edit_property_leasing_num" class="col-sm-2 control-label">
                            租凭合同编号
                        </label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="edit_property_leasing_num" placeholder="资产编号"
                                   name="property_leasing_num"/>
                        </div>
                    </div>


                    <%--  <div class="form-group">
                          <label for="edit_community_name" class="col-sm-2 control-label">小区名称</label>
                          <div class="col-sm-10">
                              <input type="text" class="form-control" id="edit_community_name" placeholder="小区名称"
                                     name="community_name"/>
                          </div>
                      </div>--%>

                    <div class="form-group">
                        <label for="edit_community_name" class="col-sm-2 control-label">小区名称</label>
                        <div class="col-sm-10">
                            <select class="form-control" id="edit_community_name" placeholder="小区名称"
                                    name="community_name">
                                <option value="">--请选择--</option>
                                <c:forEach items="${communityNameList}" var="item">
                                    <option value="${item}" <c:if
                                            test="${item == community_name}"> selected</c:if>>${item}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>

                    <%--     <div class="form-group">
                             <label for="edit_building_num" class="col-sm-2 control-label">栋号</label>
                             <div class="col-sm-10">
                                 <input type="text" class="form-control" id="edit_building_num" placeholder="栋号"
                                        name="building_num"/>
                             </div>
                         </div>--%>

                    <div class="form-group">
                        <label for="edit_building_num" class="col-sm-2 control-label">栋号</label>
                        <div class="col-sm-10">
                            <%--     <input type="text" class="form-control" id="water_assert_num" placeholder="资产编号"
                                        name="assert_num">--%>
                            <select class="form-control" id="edit_building_num" placeholder="栋号" name="building_num">
                                <option value="">--请选择--</option>
                            </select>
                        </div>
                    </div>


                    <%--  <div class="form-group">
                          <label for="edit_rentalLocation" class="col-sm-2 control-label">租聘位置</label>
                          <div class="col-sm-10">
                              <input type="text" class="form-control" id="edit_rentalLocation" placeholder="租聘位置"
                                     name="rentalLocation"/>
                          </div>
                      </div>--%>

                    <div class="form-group">
                        <label for="edit_rentalLocation" class="col-sm-2 control-label">租聘位置</label>
                        <div class="col-sm-10">
                            <%--     <input type="text" class="form-control" id="water_assert_num" placeholder="资产编号"
                                        name="assert_num">--%>
                            <select class="form-control selectpicker" id="edit_rentalLocation" multiple
                                    placeholder="租聘位置" name="rentalLocation">
                                <%-- <option value="">--请选择--</option>--%>
                            </select>
                        </div>
                    </div>


                    <div class="form-group">
                        <label for="edit_tenant" class="col-sm-2 control-label">承租人</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="edit_tenant" placeholder="承租人"
                                   name="tenant"/>
                        </div>
                    </div>


                    <div class="form-group">
                        <label for="edit_idCard" class="col-sm-2 control-label">身份证号</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="edit_idCard" placeholder="身份证号"
                                   name="idCard"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="edit_phoneNumber" class="col-sm-2 control-label">手机号码</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="edit_phoneNumber" placeholder="手机号码"
                                   name="phoneNumber"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="edit_rental_area" class="col-sm-2 control-label">承租面积</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="edit_rental_area" placeholder="承租面积"
                                   name="rental_area"/>
                        </div>
                    </div>


                    <div class="form-group date form_date">
                        <label for="edit_sign_in_time" class="col-sm-2 control-label">合同签订时间</label>
                        <div class="col-sm-10">
                            <input name="sign_in_time" id="edit_sign_in_time" type='text'
                                   class="form-control picket" placeholder="合同签订时间"/>
                        </div>
                    </div>


                    <div class="form-group">
                        <label for="edit_rent_charge_standard" class="col-sm-2 control-label">租金收费标准</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="edit_rent_charge_standard"
                                   placeholder="租金收费标准"
                                   name="rent_charge_standard"/>
                        </div>
                    </div>


                    <div class="form-group">
                        <label for="edit_monthly_rental" class="col-sm-2 control-label">月租</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="edit_monthly_rental"
                                   placeholder="如果出现多个数据请用分号(;)隔开"
                                   name="monthly_rental"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="edit_rent_free_period" class="col-sm-2 control-label">免租期</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="edit_rent_free_period"
                                   placeholder="免租期(月)"
                                   name="rent_free_period"/>
                        </div>
                    </div>


                    <div class="form-group">
                        <label for="edit_rent_period" class="col-sm-2 control-label">合同期</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="edit_rent_period"
                                   placeholder="合同期(月)"
                                   name="rent_period"/>
                        </div>
                    </div>


                    <%--      <div class="form-group">
                              <label for="edit_collect_rent_way" class="col-sm-2 control-label">租金缴纳方式</label>
                              <div class="col-sm-10">
                                  <input type="text" class="form-control" id="edit_collect_rent_way"
                                         placeholder="租金缴纳方式(1、全部交清 ，2、月缴，3、免租)"
                                         name="collect_rent_way"/>
                              </div>
                          </div>--%>


                    <div class="form-group">
                        <label for="edit_collect_rent_way"
                               class="col-sm-2 control-label">租金方式</label>
                        <div class="col-sm-10">
                            <select class="form-control" id="edit_collect_rent_way" placeholder="租金缴纳方式"
                                    name="collect_rent_way">
                                <option value="">--请选择--</option>
                                <c:forEach items="${rentalWayType}" var="item">
                                    <option value="${item.dict_id}"<c:if
                                            test="${item.dict_id == collect_rent_way}"> selected</c:if>>${item.dict_item_name }</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>


                    <div class="form-group">
                        <label for="edit_collect_rent_time" class="col-sm-2 control-label">租金缴纳时间</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="edit_collect_rent_time"
                                   placeholder="租金缴纳时间"
                                   name="collect_rent_time"/>
                        </div>
                    </div>


                    <div class="form-group">
                        <label for="edit_estate_charge_standard"
                               class="col-sm-2 control-label">物业收费标准</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="edit_estate_charge_standard"
                                   placeholder="物业收费标准(元/平方米)"
                                   name="estate_charge_standard"/>
                        </div>
                    </div>


                    <div class="form-group">
                        <label for="edit_estate_charge_standard" class="col-sm-2 control-label">月物业费</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="edit_estate_charge_month"
                                   placeholder="月物业费"
                                   name="estate_charge_month"/>
                        </div>
                    </div>


                    <div class="form-group">
                        <label for="edit_deposit" class="col-sm-2 control-label">保证金</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="edit_deposit" placeholder="保证金（元）"
                                   name="deposit"/>
                        </div>
                    </div>


                    <div class="form-group date form_date">
                        <label for="edit_deposit_time" class="col-sm-2 control-label">保证金到账时间</label>
                        <div class="col-sm-10">
                            <input name="deposit_time" id="edit_deposit_time" type='text'
                                   class="form-control picket" placeholder="保证金到账时间"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="edit_water_rate" class="col-sm-2 control-label">水费</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="edit_water_rate"
                                   placeholder="水费(元/吨)"
                                   name="water_rate"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="edit_power_rate" class="col-sm-2 control-label">电费</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="edit_power_rate"
                                   placeholder="电费(元/度)）"
                                   name="power_rate"/>
                        </div>
                    </div>

                    <%--      <div class="form-group">
                              <label for="edit_collect_rate_way" class="col-sm-2 control-label">水电费收费方式</label>
                              <div class="col-sm-10">
                                  <input type="text" class="form-control" id="edit_collect_rate_way"
                                         placeholder="水电费收费方式（1、自缴  2、代缴）"
                                         name="collect_rate_way"/>
                              </div>
                          </div>--%>

                    <div class="form-group">
                        <label for="edit_collect_rate_way"
                               class="col-sm-2 control-label">水电方式</label>
                        <div class="col-sm-10">
                            <select class="form-control" id="edit_collect_rate_way" placeholder="水电费收费方式"
                                    name="collect_rate_way">
                                <option value="">--请选择--</option>
                                <c:forEach items="${waterAndEletricWayType}" var="item">
                                    <option value="${item.dict_id}"<c:if
                                            test="${item.dict_id == collect_rate_way}"> selected</c:if>>${item.dict_item_name }</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>


                    <div class="form-group date form_date">
                        <label for="edit_rent_start_time" class="col-sm-2 control-label">租赁起始时间</label>
                        <div class="col-sm-10">
                            <input name="rent_start_time" id="edit_rent_start_time" type='text'
                                   class="form-control picket" placeholder="租赁起始时间"/>
                        </div>
                    </div>


                    <div class="form-group date form_date">
                        <label for="edit_rent_end_time" class="col-sm-2 control-label">租赁结束时间</label>
                        <div class="col-sm-10">
                            <input name="rent_end_time" id="edit_rent_end_time" type='text'
                                   class="form-control picket" placeholder="租赁结束时间"/>
                        </div>
                    </div>


                    <div class="form-group">
                        <label for="edit_property_leasing_type"
                               class="col-sm-2 control-label">返迁安置</label>
                        <div class="col-sm-10">
                            <select class="form-control" id="edit_property_leasing_type" placeholder="返迁安置"
                                    name="property_leasing_type">
                                <option value="">--请选择--</option>
                                <c:forEach items="${contractType}" var="item">
                                    <option value="${item.dict_id}"<c:if
                                            test="${item.dict_id == property_leasing_type}"> selected</c:if>>${item.dict_item_name }</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>


                    <div class="form-group">
                        <label for="edit_property_leasing_state"
                               class="col-sm-2 control-label">合同状态</label>
                        <div class="col-sm-10">
                            <select class="form-control" id="edit_property_leasing_state" placeholder="合同状态"
                                    name="property_leasing_state">
                                <option value="">--请选择--</option>
                                <c:forEach items="${contractStateType}" var="item">
                                    <option value="${item.dict_id}"<c:if
                                            test="${item.dict_id == property_leasing_state}"> selected</c:if>>${item.dict_item_name }</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="edit_remark" class="col-sm-2 control-label">备注</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="edit_remark" placeholder="备注"
                                   name="remark"/>
                        </div>
                    </div>

                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary" onclick="updatePropertyLeasing()">保存修改</button>
            </div>
        </div>
    </div>
</div>

<!-- 创建合同对话框框 -->
<div class="modal fade" id="newPropertyLeasingDialog" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="myModalLabel4">新建资产合同</h4>
            </div>
            <div class="modal-body">

                <form class="form-horizontal" id="new_PropertyLeasing_form">
                    <div class="form-group">
                        <label for="new_property_leasing_num" class="col-sm-2 control-label">
                            租凭合同编号
                        </label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="new_property_leasing_num" placeholder="资产编号"
                                   name="property_leasing_num"/>
                        </div>
                    </div>

                    <%--          <div class="form-group">
                                  <label for="new_community_name" class="col-sm-2 control-label">小区名称</label>
                                  <div class="col-sm-10">
                                      <input type="text" class="form-control" id="new_community_name" placeholder="小区名称"
                                             name="community_name"/>
                                  </div>
                              </div>--%>

                    <div class="form-group">
                        <label for="new_community_name" style="float:left;padding:7px 15px 0 27px;">小区名称</label>
                        <div class="col-sm-10">
                            <select class="form-control" id="new_community_name" placeholder="小区名称"
                                    name="community_name">
                                <option value="">--请选择--</option>
                                <c:forEach items="${communityNameList}" var="item">
                                    <option value="${item}">${item}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>

                    <%--     <div class="form-group">
                             <label for="new_building_num" class="col-sm-2 control-label">栋号</label>
                             <div class="col-sm-10">
                                 <input type="text" class="form-control" id="new_building_num" placeholder="栋号"
                                        name="building_num"/>
                             </div>
                         </div>--%>

                    <div class="form-group">
                        <label for="new_building_num" class="col-sm-2 control-label">栋号</label>
                        <div class="col-sm-10">
                            <%--     <input type="text" class="form-control" id="water_assert_num" placeholder="资产编号"
                                        name="assert_num">--%>
                            <select class="form-control" id="new_building_num" placeholder="栋号" name="building_num">
                                <option value="">--请选择--</option>
                            </select>
                        </div>
                    </div>

                    <%--  <div class="form-group">
                          <label for="new_rentalLocation" class="col-sm-2 control-label">租聘位置</label>
                          <div class="col-sm-10">
                              <input type="text" class="form-control" id="new_rentalLocation" placeholder="租聘位置"
                                     name="rentalLocation"/>
                          </div>
                      </div>--%>

                    <div class="form-group">
                        <label for="new_rentalLocation" class="col-sm-2 control-label">租聘位置</label>
                        <div class="col-sm-10">
                            <select class="form-control selectpicker" multiple data-live-search=""
                                    id="new_rentalLocation"
                                    placeholder="租聘位置" name="rentalLocation">

                            </select>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="new_tenant" class="col-sm-2 control-label">承租人</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="new_tenant" placeholder="承租人"
                                   name="tenant"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="new_idCard" class="col-sm-2 control-label">身份证号</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="new_idCard" placeholder="身份证号"
                                   name="idCard"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="new_phoneNumber" class="col-sm-2 control-label">手机号码</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="new_phoneNumber" placeholder="手机号码"
                                   name="phoneNumber"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="new_rental_area" class="col-sm-2 control-label">承租面积</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="new_rental_area" placeholder="承租面积"
                                   name="rental_area"/>
                        </div>
                    </div>


                    <div class="form-group date form_date">
                        <label for="new_sign_in_time" class="col-sm-2 control-label">合同签订时间</label>
                        <div class="col-sm-10">
                            <input name="sign_in_time" id="new_sign_in_time" type='text'
                                   class="form-control picket" placeholder="合同签订时间"/>
                        </div>
                    </div>


                    <div class="form-group">
                        <label for="new_rent_charge_standard" class="col-sm-2 control-label">租金收费标准</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="new_rent_charge_standard" placeholder="租金收费标准"
                                   name="rent_charge_standard"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="new_monthly_rental" class="col-sm-2 control-label">月租</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="new_monthly_rental" placeholder="月租"
                                   name="monthly_rental"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="new_rent_free_period" class="col-sm-2 control-label">免租期</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="new_rent_free_period" placeholder="免租期(月)"
                                   name="rent_free_period"/>
                        </div>
                    </div>


                    <div class="form-group">
                        <label for="new_rent_period" class="col-sm-2 control-label">合同期</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="new_rent_period" placeholder="合同期(月)"
                                   name="rent_period"/>
                        </div>
                    </div>


                    <%--    <div class="form-group">
                            <label for="new_collect_rent_way" class="col-sm-2 control-label">租金缴纳方式</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" id="new_collect_rent_way"
                                       placeholder="租金缴纳方式(1、全部交清 ，2、月缴，3、免租)"
                                       name="collect_rent_way"/>
                            </div>
                        </div>--%>

                    <div class="form-group">
                        <label for="new_collect_rent_way" style="float:left;padding:7px 15px 0 27px;">租金方式</label>
                        <div class="col-sm-10">
                            <select class="form-control" id="new_collect_rent_way" placeholder="租金方式"
                                    name="collect_rent_way">
                                <option value="">--请选择--</option>
                                <c:forEach items="${rentalWayType}" var="item">
                                    <option value="${item.dict_id}"<c:if
                                            test="${item.dict_id == collect_rent_way}"> selected</c:if>>${item.dict_item_name }</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>


                    <div class="form-group">
                        <label for="new_collect_rent_time" class="col-sm-2 control-label">租金缴纳时间</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="new_collect_rent_time" placeholder="租金缴纳时间"
                                   name="collect_rent_time"/>
                        </div>
                    </div>


                    <div class="form-group">
                        <label for="new_estate_charge_standard" class="col-sm-2 control-label">物业收费标准</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="new_estate_charge_standard"
                                   placeholder="物业收费标准(元/平方米)"
                                   name="estate_charge_standard"/>
                        </div>
                    </div>


                    <div class="form-group">
                        <label for="new_estate_charge_standard" class="col-sm-2 control-label">月物业费</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="new_estate_charge_month" placeholder="月物业费"
                                   name="estate_charge_month"/>
                        </div>
                    </div>


                    <div class="form-group">
                        <label for="new_deposit" class="col-sm-2 control-label">保证金</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="new_deposit" placeholder="保证金（元）"
                                   name="deposit"/>
                        </div>
                    </div>


                    <div class="form-group date form_date">
                        <label for="new_deposit_time" class="col-sm-2 control-label">保证金到账时间</label>
                        <div class="col-sm-10">
                            <input name="deposit_time" id="new_deposit_time" type='text'
                                   class="form-control picket" placeholder="保证金到账时间"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="new_water_rate" class="col-sm-2 control-label">水费</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="new_water_rate" placeholder="水费(元/吨)"
                                   name="water_rate"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="new_power_rate" class="col-sm-2 control-label">电费</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="new_power_rate" placeholder="电费(元/度)）"
                                   name="power_rate"/>
                        </div>
                    </div>

                    <%--       <div class="form-group">
                               <label for="new_collect_rate_way" class="col-sm-2 control-label">水电费收费方式</label>
                               <div class="col-sm-10">
                                   <input type="text" class="form-control" id="new_collect_rate_way"
                                          placeholder="水电费收费方式（1、自缴  2、代缴）"
                                          name="collect_rate_way"/>
                               </div>
                           </div>--%>

                    <div class="form-group">
                        <label for="new_collect_rate_way" style="float:left;padding:7px 15px 0 27px;">水电方式</label>
                        <div class="col-sm-10">
                            <select class="form-control" id="new_collect_rate_way" placeholder="水电费收费方式"
                                    name="collect_rate_way">
                                <option value="">--请选择--</option>
                                <c:forEach items="${waterAndEletricWayType}" var="item">
                                    <option value="${item.dict_id}"<c:if
                                            test="${item.dict_id == collect_rate_way}"> selected</c:if>>${item.dict_item_name }</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>


                    <div class="form-group date form_date">
                        <label for="new_rent_start_time" class="col-sm-2 control-label">租赁起始时间</label>
                        <div class="col-sm-10">
                            <input name="rent_start_time" id="new_rent_start_time" type='text'
                                   class="form-control picket" placeholder="租赁起始时间"/>
                        </div>
                    </div>


                    <div class="form-group date form_date">
                        <label for="new_rent_end_time" class="col-sm-2 control-label">租赁结束时间</label>
                        <div class="col-sm-10">
                            <input name="rent_end_time" id="new_rent_end_time" type='text'
                                   class="form-control picket" placeholder="租赁结束时间"/>
                        </div>
                    </div>


                    <div class="form-group">
                        <label for="new_property_leasing_type" style="float:left;padding:7px 15px 0 27px;">返迁安置</label>
                        <div class="col-sm-10">
                            <select class="form-control" id="new_property_leasing_type" placeholder="返迁安置"
                                    name="property_leasing_type">
                                <option value="">--请选择--</option>
                                <c:forEach items="${contractType}" var="item">
                                    <option value="${item.dict_id}"<c:if
                                            test="${item.dict_id == property_leasing_type}"> selected</c:if>>${item.dict_item_name }</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="new_property_leasing_state" style="float:left;padding:7px 15px 0 27px;">合同状态</label>
                        <div class="col-sm-10">
                            <select class="form-control" id="new_property_leasing_state" placeholder="合同的状态"
                                    name="property_leasing_state">
                                <option value="">--请选择--</option>
                                <c:forEach items="${contractStateType}" var="item">
                                    <option value="${item.dict_id}"<c:if
                                            test="${item.dict_id == property_leasing_state}"> selected</c:if>>${item.dict_item_name }</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="new_remark" class="col-sm-2 control-label">备注</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="new_remark" placeholder="备注"
                                   name="remark"/>
                        </div>
                    </div>

                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary" onclick="createPropertyLeasing()">创建合同</button>
            </div>
        </div>
    </div>
</div>
<!-- /#wrapper -->

<!-- 创建合同和资产的关联关系 -->
<div class="modal fade" id="addAssertDialog" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="myModalLabel2">合同和资产绑定</h4>
            </div>
            <div class="modal-body">

                <form class="form-horizontal" id="addAssert_form">
                    <div class="form-group">
                        <label for="add_property_leasing_num" class="col-sm-2 control-label">
                            租凭合同编号
                        </label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="add_property_leasing_num" placeholder="合同编号"
                                   name="property_leasing_num"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="edit_customerFrom" style="float:left;padding:7px 15px 0 27px;">资产编号</label>
                        <div class="col-sm-10">
                            <select class="form-control" id="edit_customerFrom" placeholder="资产编号" name="assert_num">
                            </select>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="edit_water_num" style="float:left;padding:7px 15px 0 27px;">水表吨数</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="edit_water_num" placeholder="水表吨数"
                                   name="water_num"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="edit_electric_num" style="float:left;padding:7px 15px 0 27px;">电表度数</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="edit_electric_num" placeholder="电表度数"
                                   name="electric_num"/>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary" onclick="addPropertyLeasing()">创建关系</button>
            </div>
        </div>
    </div>
</div>

<!-- 创建押金对话框框 -->
<div class="modal fade" id="newDepositDialog" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="myModalLabel0">添加押金信息</h4>
            </div>
            <div class="modal-body">

                <form class="form-horizontal" id="new_deposit_form">
                    <div class="form-group">
                        <label for="add2_property_leasing_num" class="col-sm-2 control-label">
                            租凭合同编号
                        </label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="add2_property_leasing_num" placeholder="租凭合同编号"
                                   name="property_leasing_num"/>
                        </div>
                    </div>


                    <div class="form-group date form_date">
                        <label for="add_rent_start_time" class="col-sm-2 control-label">本次收取费用开始时间</label>
                        <div class="col-sm-10">
                            <input name="rent_start_time" id="add_rent_start_time" type='text' placeholder="本次收取费用开始时间"
                                   class="form-control picket"/>
                        </div>

                    </div>


                    <div class="form-group date form_date">
                        <label for="add_rent_end_time" class="col-sm-2 control-label">本次收取费用结束时间</label>
                        <div class="col-sm-10">
                            <input name="rent_end_time" id="add_rent_end_time" type='text' placeholder="本次收取费用结束时间"
                                   class="form-control picket"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="add_deposit" class="col-sm-2 control-label">应收保证金</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="add_deposit" placeholder="应收保证金（元）"
                                   name="deposit">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="add_reality_deposit" class="col-sm-2 control-label">实际保证金</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="add_reality_deposit" placeholder="实际保证金（元）"
                                   name="reality_deposit">
                        </div>
                    </div>

                    <div class="form-group date form_date">
                        <label for="add_deadline" class="col-sm-2 control-label">到帐时间</label>
                        <div class="col-sm-10">
                            <input name="deadline" id="add_deadline" type='text'
                                   class="form-control picket"/>
                        </div>
                        <%-- <span class="input-group-addon input-sm"><span class="glyphicon glyphicon-calendar"></span></span>--%>
                    </div>


                    <%-- <div class="form-group">
                         <label for="add_state" style="float:left;padding:7px 15px 0 27px;">押金状态</label>
                         <div class="col-sm-10">
                             <select class="form-control" id="add_state" placeholder="押金状态" name="state">
                                 <option value="">--请选择--</option>
                                 <c:forEach items="${depositStateType}" var="item">
                                     <option value="${item.dict_id}"<c:if
                                             test="${item.dict_id == state}"> selected</c:if>>${item.dict_item_name }</option>
                                 </c:forEach>
                             </select>
                         </div>
                     </div>--%>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" onclick="createDeposit()">创建</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
            </div>
        </div>
    </div>
</div>

<!-- 显示押金对话框框 -->
<div class="modal fade" id="showDepositDialog" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="myModalLabel5">显示押金信息</h4>
            </div>
            <div class="modal-body">

                <form class="form-horizontal" id="show_deposit_form">
                    <div class="form-group">
                        <label for="show_property_leasing_num" class="col-sm-2 control-label">
                            租凭合同编号
                        </label>
                        <div class="col-sm-10">
                            <input type="text" disabled class="form-control" id="show_property_leasing_num"
                                   placeholder="租凭合同编号" name="property_leasing_num"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="show_rent_start_time" class="col-sm-2 control-label">本次收取费用开始时间</label>
                        <div class="col-sm-10">
                            <input type="text" disabled class="form-control" id="show_rent_start_time"
                                   placeholder="本次收取费用开始时间"
                                   name="rent_start_time">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="show_rent_end_time" class="col-sm-2 control-label">本次收取费用结束时间</label>
                        <div class="col-sm-10">
                            <input type="text" disabled class="form-control" id="show_rent_end_time"
                                   placeholder="本次收取费用结束时间"
                                   name="rent_end_time">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="show_deposit" class="col-sm-2 control-label">应收保证金</label>
                        <div class="col-sm-10">
                            <input type="text" disabled class="form-control" id="show_deposit" placeholder="应收保证金（元）"
                                   name="deposit">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="show_reality_deposit" class="col-sm-2 control-label">实际保证金</label>
                        <div class="col-sm-10">
                            <input type="text" disabled class="form-control" id="show_reality_deposit"
                                   placeholder="实际保证金（元）"
                                   name="reality_deposit">
                        </div>
                    </div>


                    <div class="form-group date form_date">
                        <label for="show_deadline" class="col-sm-2 control-label">到帐时间</label>
                        <div class="col-sm-10">
                            <input name="deadline" disabled id="show_deadline" type='text'
                                   class="form-control"/>
                        </div>
                        <%-- <span class="input-group-addon input-sm"><span class="glyphicon glyphicon-calendar"></span></span>--%>
                    </div>

                    <div class="form-group">
                        <label for="show_state" style="float:left;padding:7px 15px 0 27px;">押金状态</label>
                        <div class="col-sm-10">
                            <select class="form-control" id="show_state" disabled placeholder="押金状态" name="state">
                                <option value="">--请选择--</option>
                                <c:forEach items="${depositStateType}" var="item">
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

            </div>
        </div>
    </div>
</div>

<!-- 显示对应资产的对话框 -->
<%--<div class="modal fade" id="showAssertDialog" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="myModalLabe28">合同和资产绑定</h4>
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
                                    <th>资产编号</th>
                                    <th>产证编号</th>
                                    <th>小区名称</th>
                                    <th>栋号</th>
                                    <th>房号或店面号</th>
                                    <th>建筑面积</th>
                                    <th>水表编号</th>
                                    <th>电表编号</th>
                                    <th>房屋状态</th>
                                </tr>
                                </thead>
                                <tbody id="tbody">

                                </tbody>
                            </table>
                            &lt;%&ndash;<div class="col-md-12 text-right">
                                <itcast:page url="${pageContext.request.contextPath }/case/list.action"/>
                            </div>&ndash;%&gt;
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
            &lt;%&ndash;  <button type="button" class="btn btn-primary" onclick="addPropertyLeasing()">创建关系</button>&ndash;%&gt;
        </div>
    </div>
</div>--%>

<!-- 创建租金对话框 -->
<div class="modal fade" id="newRentalDialog" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="myModalLabel6">添加租金信息</h4>
            </div>
            <div class="modal-body">

                <form class="form-horizontal" id="new_rental_form">
                    <input type="hidden" id="rental_day" name="rental_day"/>
                    <div class="form-group">
                        <label for="rental_property_leasing_num" class="col-sm-2 control-label">
                            租凭合同编号
                        </label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="rental_property_leasing_num"
                                   placeholder="租凭合同编号" name="property_leasing_num" readonly="readonly"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="rental_year_months" style="float:left;padding:7px 15px 0 27px;">对应年月</label>
                        <div class="col-sm-10">
                            <select class="form-control" id="rental_year_months" placeholder="对应年月" name="year_months">
                                <option value="">--请选择--</option>
                                <%-- <c:forEach items="${rentalStateType}" var="item">
                                     <option value="${item.dict_id}"<c:if
                                             test="${item.dict_id == state}"> selected</c:if>>${item.dict_item_name }</option>
                                 </c:forEach>--%>
                            </select>
                        </div>
                    </div>


                    <div class="form-group date form_date">
                        <label for="rental_rent_start_time" class="col-sm-2 control-label">本次收取费用开始时间</label>
                        <div class="col-sm-10">
                            <input name="rent_start_time" id="rental_rent_start_time" type='text'
                                   class="form-control picket" placeholder="本次收取费用开始时间"/>
                        </div>
                    </div>


                    <div class="form-group date form_date">
                        <label for="rental_rent_end_time" class="col-sm-2 control-label">本次收取费用结束时间</label>
                        <div class="col-sm-10">
                            <input name="rent_end_time" id="rental_rent_end_time" type='text'
                                   class="form-control picket" placeholder="本次收取费用结束时间"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="rental_rent_received" class="col-sm-2 control-label ">实际已收租金</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="rental_rent_received" readonly="readonly"
                                   placeholder="实际已收租金（元）"
                                   name="rent_recivied">
                        </div>
                    </div>


                    <div class="form-group">
                        <label for="rental_rental" class="col-sm-2 control-label">截取当前应收租金</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="rental_rental" readonly="readonly"
                                   placeholder="截取当前应收租金（元）"
                                   name="rental">
                        </div>
                    </div>

                    <div class="form-group date form_date">
                        <label for="rental_deadline" class="col-sm-2 control-label">收费时间</label>
                        <div class="col-sm-10">
                            <input name="deadline" id="rental_deadline" type='text'
                                   class="form-control picket" placeholder="收费时间"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="rental_reality_rental" class="col-sm-2 control-label">本次收取租金</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="rental_reality_rental" placeholder="本次收取租金（元）"
                                   name="reality_rental">
                        </div>
                    </div>


                    <%--<div class="form-group">
                        <label for="rental_state" style="float:left;padding:7px 15px 0 27px;">租金状态</label>
                        <div class="col-sm-10">
                            <select class="form-control" id="rental_state" placeholder="租金状态" name="state">
                                <option value="">--请选择--</option>
                                <c:forEach items="${rentalStateType}" var="item">
                                    <option value="${item.dict_id}"<c:if
                                            test="${item.dict_id == state}"> selected</c:if>>${item.dict_item_name }</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>--%>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" onclick="createRental()">创建租金</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
            </div>
        </div>
    </div>
</div>

<!-- 创建租金对话框 -->
<div class="modal fade" id="newRentalByDefDialog" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="myModalLabe29">添加租金信息</h4>
            </div>
            <div class="modal-body">

                <form class="form-horizontal" id="new2_rental_form">
                    <div class="form-group">
                        <label for="rental2_property_leasing_num" class="col-sm-2 control-label">
                            租凭合同编号
                        </label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" readonly="readonly"
                                   id="rental2_property_leasing_num"
                                   placeholder="租凭合同编号" name="property_leasing_num"/>
                        </div>
                    </div>


                    <div class="form-group date form_date" hidden>
                        <label for="rental2_rent_start_time" class="col-sm-2 control-label">本次收取费用开始时间</label>
                        <div class="col-sm-10">
                            <input name="rent_start_time" id="rental2_rent_start_time" type='text'
                                   class="form-control picket" placeholder="本次收取费用开始时间"/>
                        </div>
                    </div>


                    <div class="form-group date form_date" hidden>
                        <label for="rental2_rent_end_time" class="col-sm-2 control-label">本次收取费用结束时间</label>
                        <div class="col-sm-10">
                            <input name="rent_end_time" id="rental2_rent_end_time" type='text'
                                   class="form-control picket" placeholder="本次收取费用结束时间"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="rental2_rent_received" class="col-sm-2 control-label">实际已收租金</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="rental2_rent_received" readonly="readonly"
                                   placeholder="实际已收租金（元）"
                                   name="rent_recivied">
                        </div>
                    </div>


                    <div class="form-group">
                        <label for="rental2_rental" class="col-sm-2 control-label">截取当前应收租金</label>
                        <div class="col-sm-10">
                            <input type="text" readonly="readonly" class="form-control" id="rental2_rental"
                                   placeholder="截取当前应收租金（元）"
                                   name="rental">
                        </div>
                    </div>

                    <div class="form-group date form_date">
                        <label for="rental2_deadline" class="col-sm-2 control-label">收费时间</label>
                        <div class="col-sm-10">
                            <input name="deadline" id="rental2_deadline" type='text'
                                   class="form-control picket" placeholder="收费时间"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="rental2_reality_rental" class="col-sm-2 control-label">本次收取租金</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="rental2_reality_rental" placeholder="本次收取租金（元）"
                                   name="reality_rental">
                        </div>
                    </div>


                    <%--<div class="form-group">
                        <label for="rental_state" style="float:left;padding:7px 15px 0 27px;">租金状态</label>
                        <div class="col-sm-10">
                            <select class="form-control" id="rental_state" placeholder="租金状态" name="state">
                                <option value="">--请选择--</option>
                                <c:forEach items="${rentalStateType}" var="item">
                                    <option value="${item.dict_id}"<c:if
                                            test="${item.dict_id == state}"> selected</c:if>>${item.dict_item_name }</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>--%>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" onclick="createRental2()">创建租金</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
            </div>
        </div>
    </div>
</div>

<!-- 显示租金对话框 -->
<div class="modal fade" id="showRentalDialog" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="myModalLabel7">显示租金信息</h4>
            </div>
            <div class="modal-body">

                <form class="form-horizontal" id="show_rental_form">
                    <div class="form-group">
                        <label for="rental_show_property_leasing_num" class="col-sm-2 control-label">
                            租凭合同编号
                        </label>
                        <div class="col-sm-10">
                            <input type="text" disabled class="form-control" id="rental_show_property_leasing_num"
                                   placeholder="租凭合同编号" name="property_leasing_num"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="rental_show_rent_start_time" class="col-sm-2 control-label">本次收取费用开始时间</label>
                        <div class="col-sm-10">
                            <input type="text" disabled class="form-control" id="rental_show_rent_start_time"
                                   placeholder="本次收取费用开始时间"
                                   name="rent_start_time">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="rental_show_rent_end_time" class="col-sm-2 control-label">本次收取费用结束时间</label>
                        <div class="col-sm-10">
                            <input type="text" disabled class="form-control" id="rental_show_rent_end_time"
                                   placeholder="本次收取费用结束时间"
                                   name="rent_end_time">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="rental_show_rent_received" class="col-sm-2 control-label">实际已收租金</label>
                        <div class="col-sm-10">
                            <input type="text" disabled class="form-control" id="rental_show_rent_received"
                                   placeholder="实际已收租金（元）"
                                   name="rent_recivied">
                        </div>
                    </div>


                    <div class="form-group">
                        <label for="rental_show_rental" class="col-sm-2 control-label">截取当前应收租金</label>
                        <div class="col-sm-10">
                            <input type="text" disabled class="form-control" id="rental_show_rental"
                                   placeholder="截取当前应收租金（元）"
                                   name="rental">
                        </div>
                    </div>


                    <div class="form-group">
                        <label for="rental_show_reality_rental" class="col-sm-2 control-label">本次收取租金</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" disabled id="rental_show_reality_rental"
                                   placeholder="本次应收取租金（元）"
                                   name="reality_rental">
                        </div>
                    </div>


                    <%--<div class="form-group date form_date">
                        <label for="rental_show_deadline" class="col-sm-2 control-label">收费时间</label>
                        <div class="col-sm-10">
                            <input name="deadline" id="rental_show_deadline" type='text' disabled
                                   class="form-control picket" placeholder="收费时间"/>
                        </div>
                    </div>--%>

                    <div class="form-group">
                        <label for="rental_show_state" style="float:left;padding:7px 15px 0 27px;">租金状态</label>
                        <div class="col-sm-10">
                            <select class="form-control" id="rental_show_state" disabled placeholder="租金状态"
                                    name="state">
                                <option value="">--请选择--</option>
                                <c:forEach items="${rentalStateType}" var="item">
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
            </div>
        </div>
    </div>
</div>

<!-- 创建物业费对话框 -->
<div class="modal fade" id="newEstateDialog" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="myModalLabel11">添加物业费信息</h4>
            </div>
            <div class="modal-body">

                <form class="form-horizontal" id="new_estate_form">
                    <input type="hidden" id="estate_day" name="estate_day"/>
                    <div class="form-group">
                        <label for="estate_property_leasing_num" class="col-sm-2 control-label">
                            租凭合同编号
                        </label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="estate_property_leasing_num"
                                   placeholder="租凭合同编号" name="property_leasing_num"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="estate_year_months" style="float:left;padding:7px 15px 0 27px;">对应年月</label>
                        <div class="col-sm-10">
                            <select class="form-control" id="estate_year_months" placeholder="对应年月" name="year_months">
                                <option value="">--请选择--</option>
                                <%-- <c:forEach items="${rentalStateType}" var="item">
                                     <option value="${item.dict_id}"<c:if
                                             test="${item.dict_id == state}"> selected</c:if>>${item.dict_item_name }</option>
                                 </c:forEach>--%>
                            </select>
                        </div>
                    </div>


                    <div class="form-group date form_date">
                        <label for="estate_rent_start_time" class="col-sm-2 control-label">本次收取费用开始时间</label>
                        <div class="col-sm-10">
                            <input name="rent_start_time" id="estate_rent_start_time" type='text'
                                   class="form-control picket" placeholder="本次收取费用开始时间"/>
                        </div>
                    </div>


                    <div class="form-group date form_date">
                        <label for="estate_rent_end_time" class="col-sm-2 control-label">本次收取费用结束时间</label>
                        <div class="col-sm-10">
                            <input name="rent_end_time" id="estate_rent_end_time" type='text'
                                   class="form-control picket" placeholder="本次收取费用结束时间"/>
                        </div>
                    </div>


                    <div class="form-group">
                        <label for="estate_estate_received" class="col-sm-2 control-label">实际已收物业费</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="estate_estate_received" placeholder="实际已收物业费（元）"
                                   name="estate_recivied">
                        </div>
                    </div>


                    <div class="form-group">
                        <label for="estate_estate" class="col-sm-2 control-label">截取当前应收物业费</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="estate_estate" placeholder="截取当前应收物业费（元）"
                                   name="estate">
                        </div>
                    </div>

                    <div class="form-group date form_date">
                        <label for="estate_deadline" class="col-sm-2 control-label">收费时间</label>
                        <div class="col-sm-10">
                            <input name="deadline" id="estate_deadline" type='text'
                                   class="form-control picket" placeholder="收费时间"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="estate_reality_estate" class="col-sm-2 control-label">本次收取物业费</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="estate_reality_estate" placeholder="本次收取物业费（元）"
                                   name="reality_estate">
                        </div>
                    </div>


                    <%-- <div class="form-group">
                         <label for="estate_state" style="float:left;padding:7px 15px 0 27px;">物业状态</label>
                         <div class="col-sm-10">
                             <select class="form-control" id="estate_state" placeholder="物业状态" name="state">
                                 <option value="">--请选择--</option>
                                 <c:forEach items="${estateStateType}" var="item">
                                     <option value="${item.dict_id}"<c:if
                                             test="${item.dict_id == state}"> selected</c:if>>${item.dict_item_name }</option>
                                 </c:forEach>
                             </select>
                         </div>
                     </div>--%>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" onclick="createEstate()">创建物业费</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
            </div>
        </div>
    </div>
</div>

<!-- 创建物业费对话框 -->
<div class="modal fade" id="newEstateByDefDialog" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="myModalLabel44">添加物业费信息</h4>
            </div>
            <div class="modal-body">

                <form class="form-horizontal" id="new2_estate_form">
                    <input type="hidden" id="estate_day" name="estate_day"/>
                    <div class="form-group">
                        <label for="estate2_property_leasing_num" class="col-sm-2 control-label">
                            租凭合同编号
                        </label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="estate2_property_leasing_num"
                                   placeholder="租凭合同编号" name="property_leasing_num"/>
                        </div>
                    </div>


                    <div class="form-group date form_date">
                        <label for="estate2_rent_start_time" class="col-sm-2 control-label">本次收取费用开始时间</label>
                        <div class="col-sm-10">
                            <input name="rent_start_time" id="estate2_rent_start_time" type='text'
                                   class="form-control picket" placeholder="本次收取费用开始时间"/>
                        </div>
                    </div>


                    <div class="form-group date form_date">
                        <label for="estate2_rent_end_time" class="col-sm-2 control-label">本次收取费用结束时间</label>
                        <div class="col-sm-10">
                            <input name="rent_end_time" id="estate2_rent_end_time" type='text'
                                   class="form-control picket" placeholder="本次收取费用结束时间"/>
                        </div>
                    </div>


                    <div class="form-group">
                        <label for="estate2_estate_received" class="col-sm-2 control-label">实际已收物业费</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="estate2_estate_received"
                                   placeholder="实际已收物业费（元）"
                                   name="estate_recivied">
                        </div>
                    </div>


                    <div class="form-group">
                        <label for="estate2_estate" class="col-sm-2 control-label">截取当前应收物业费</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="estate2_estate" placeholder="截取当前应收物业费（元）"
                                   name="estate">
                        </div>
                    </div>

                    <div class="form-group date form_date">
                        <label for="estate2_deadline" class="col-sm-2 control-label">收费时间</label>
                        <div class="col-sm-10">
                            <input name="deadline" id="estate2_deadline" type='text'
                                   class="form-control picket" placeholder="收费时间"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="estate2_reality_estate" class="col-sm-2 control-label">本次收取物业费</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="estate2_reality_estate" placeholder="本次收取物业费（元）"
                                   name="reality_estate">
                        </div>
                    </div>


                    <%-- <div class="form-group">
                         <label for="estate_state" style="float:left;padding:7px 15px 0 27px;">物业状态</label>
                         <div class="col-sm-10">
                             <select class="form-control" id="estate_state" placeholder="物业状态" name="state">
                                 <option value="">--请选择--</option>
                                 <c:forEach items="${estateStateType}" var="item">
                                     <option value="${item.dict_id}"<c:if
                                             test="${item.dict_id == state}"> selected</c:if>>${item.dict_item_name }</option>
                                 </c:forEach>
                             </select>
                         </div>
                     </div>--%>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" onclick="createEstate2()">创建物业费</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
            </div>
        </div>
    </div>
</div>

<!-- 显示物业费对话框 -->
<div class="modal fade" id="showEstateDialog" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="myModalLabel12">显示物业费信息</h4>
            </div>
            <div class="modal-body">

                <form class="form-horizontal" id="show_estate_form">
                    <div class="form-group">
                        <label for="estate_show_property_leasing_num" class="col-sm-2 control-label">
                            租凭合同编号
                        </label>
                        <div class="col-sm-10">
                            <input type="text" disabled class="form-control" id="estate_show_property_leasing_num"
                                   placeholder="租凭合同编号" name="property_leasing_num"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="estate_show_rent_start_time" class="col-sm-2 control-label">本次收取费用开始时间</label>
                        <div class="col-sm-10">
                            <input type="text" disabled class="form-control" id="estate_show_rent_start_time"
                                   placeholder="本次收取费用开始时间"
                                   name="rent_start_time">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="estate_show_rent_end_time" class="col-sm-2 control-label">本次收取费用结束时间</label>
                        <div class="col-sm-10">
                            <input type="text" disabled class="form-control" id="estate_show_rent_end_time"
                                   placeholder="本次收取费用结束时间"
                                   name="rent_end_time">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="estate_show_estate_received" class="col-sm-2 control-label">实际已收物业费</label>
                        <div class="col-sm-10">
                            <input type="text" disabled class="form-control" id="estate_show_estate_received"
                                   placeholder="实际已收物业费（元）"
                                   name="estate_recivied">
                        </div>
                    </div>


                    <div class="form-group">
                        <label for="estate_show_estate" class="col-sm-2 control-label">截取当前应收物业费</label>
                        <div class="col-sm-10">
                            <input type="text" disabled class="form-control" id="estate_show_estate"
                                   placeholder="截取当前应物业费（元）"
                                   name="estate">
                        </div>
                    </div>


                    <div class="form-group">
                        <label for="estate_show_reality_estate" class="col-sm-2 control-label">本次收取物业费</label>
                        <div class="col-sm-10">
                            <input type="text" disabled class="form-control" id="estate_show_reality_estate"
                                   placeholder="本次收取物业费（元）"
                                   name="reality_estate">
                        </div>
                    </div>


                    <div class="form-group">
                        <label for="estate_show_state" style="float:left;padding:7px 15px 0 27px;">租金状态</label>
                        <div class="col-sm-10">
                            <select class="form-control" id="estate_show_state" disabled placeholder="租金状态"
                                    name="state">
                                <option value="">--请选择--</option>
                                <c:forEach items="${estateStateType}" var="item">
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
            </div>
        </div>
    </div>
</div>


<!-- 水表度数录入对话框 -->
<div class="modal fade" id="newWaterDialog" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="myModalLabel18">添加用水信息</h4>
            </div>
            <div class="modal-body">

                <form class="form-horizontal" id="new_water_form">
                    <div class="form-group">
                        <label for="water_property_leasing_num" class="col-sm-2 control-label">
                            租凭合同编号
                        </label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="water_property_leasing_num"
                                   placeholder="租凭合同编号" name="property_leasing_num"/>
                        </div>
                    </div>


                    <div class="form-group">
                        <label for="water_assert_num" class="col-sm-2 control-label">资产编号</label>
                        <div class="col-sm-10">
                            <%--     <input type="text" class="form-control" id="water_assert_num" placeholder="资产编号"
                                        name="assert_num">--%>
                            <select class="form-control" id="water_assert_num" placeholder="资产编号" name="assert_num">
                                <option value="">--请选择--</option>
                            </select>
                        </div>
                    </div>


                    <div class="form-group">
                        <label for="water_watermeter_num" class="col-sm-2 control-label">水表编号</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="water_watermeter_num" placeholder="水表编号"
                                   name="watermeter_num">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="water_water_num" class="col-sm-2 control-label">水表度数</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="water_water_num" placeholder="水表度数（吨）"
                                   name="water_num">
                        </div>
                    </div>


                    <div class="form-group date form_date">
                        <label for="water_deadline" class="col-sm-2 control-label">截止抄表时间</label>
                        <div class="col-sm-10">
                            <input name="deadline" id="water_deadline" type='text'
                                   class="form-control picket" placeholder="截止抄表时间"/>
                        </div>
                    </div>

                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" onclick="createWater()">创建</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
            </div>
        </div>
    </div>
</div>


<!-- 电表度数录入对话框 -->
<div class="modal fade" id="newPowerDialog" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="myModalLabel19">添加用电信息</h4>
            </div>
            <div class="modal-body">

                <form class="form-horizontal" id="new_power_form">
                    <div class="form-group">
                        <label for="power_property_leasing_num" class="col-sm-2 control-label">
                            租凭合同编号
                        </label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="power_property_leasing_num"
                                   placeholder="租凭合同编号" name="property_leasing_num"/>
                        </div>
                    </div>


                    <div class="form-group">
                        <label for="power_assert_num" class="col-sm-2 control-label">资产编号</label>
                        <div class="col-sm-10">
                            <%--     <input type="text" class="form-control" id="power_assert_num" placeholder="资产编号"
                                        name="assert_num">--%>
                            <select class="form-control" id="power_assert_num" placeholder="资产编号" name="assert_num">
                                <option value="">--请选择--</option>
                            </select>
                        </div>
                    </div>


                    <div class="form-group">
                        <label for="power_powermeter_num" class="col-sm-2 control-label">电表编号</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="power_powermeter_num" placeholder="电表编号"
                                   name="powermeter_num">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="power_power_num" class="col-sm-2 control-label">电表度数</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="power_power_num" placeholder="电表度数（吨）"
                                   name="power_num">
                        </div>
                    </div>


                    <div class="form-group date form_date">
                        <label for="power_deadline" class="col-sm-2 control-label">截止抄表时间</label>
                        <div class="col-sm-10">
                            <input name="deadline" id="power_deadline" type='text'
                                   class="form-control picket" placeholder="截止抄表时间"/>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" onclick="createPower()">创建</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
            </div>
        </div>
    </div>
</div>


<!-- 水表费用录入对话框 -->
<div class="modal fade" id="newWaterRentDialog" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="myModalLabel23">添加用水信息</h4>
            </div>
            <div class="modal-body">

                <form class="form-horizontal" id="new_water_rent_form">
                    <div class="form-group">
                        <label for="water_rate_property_leasing_num" class="col-sm-2 control-label">
                            租凭合同编号
                        </label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="water_rate_property_leasing_num"
                                   placeholder="租凭合同编号" name="property_leasing_num"/>
                        </div>
                    </div>


                    <div class="form-group">
                        <label for="water_rate_assert_num" class="col-sm-2 control-label">资产编号</label>
                        <div class="col-sm-10">
                            <%--     <input type="text" class="form-control" id="water_assert_num" placeholder="资产编号"
                                        name="assert_num">--%>
                            <select class="form-control" id="water_rate_assert_num" placeholder="资产编号"
                                    name="assert_num">
                                <option value="">--请选择--</option>
                            </select>
                        </div>
                    </div>


                    <div class="form-group date form_date">
                        <label for="water_rate_rent_start_time" class="col-sm-2 control-label">本次收取费用开始时间</label>
                        <div class="col-sm-10">
                            <input name="rent_start_time" id="water_rate_rent_start_time" type='text'
                                   class="form-control picket" placeholder="本次收取费用开始时间"/>
                        </div>
                    </div>


                    <div class="form-group date form_date">
                        <label for="water_rate_rent_end_time" class="col-sm-2 control-label">本次收取费用结束时间</label>
                        <div class="col-sm-10">
                            <input name="rent_end_time" id="water_rate_rent_end_time" type='text'
                                   class="form-control picket" placeholder="本次收取费用结束时间"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="water_rate_watermeter_num" class="col-sm-2 control-label">水表编号</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="water_rate_watermeter_num" placeholder="水表编号"
                                   name="watermeter_num">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="water_rate_water_num" class="col-sm-2 control-label">水表度数</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="water_rate_water_num" placeholder="水表度数（吨）"
                                   name="water_num">
                        </div>
                    </div>


                    <div class="form-group date form_date">
                        <label for="water_rate_deadline" class="col-sm-2 control-label">截止时间</label>
                        <div class="col-sm-10">
                            <input name="deadline" id="water_rate_deadline" type='text'
                                   class="form-control picket" placeholder="截止时间"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="water_rate_water_rent_received" class="col-sm-2 control-label">实际已收水费</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="water_rate_water_rent_received"
                                   placeholder="实际已收水费（元）"
                                   name="water_rent_recivied">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="water_rate_water_rent" class="col-sm-2 control-label">截取当前应收水费</label>
                        <div class="col-sm-10">
                            <input type="number" class="form-control" id="water_rate_water_rent"
                                   placeholder="截止当前应收水费（元）"
                                   name="water_rent">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="water_rate_reality_water_rent" class="col-sm-2 control-label">本次收取水费</label>
                        <div class="col-sm-10">
                            <input type="number" class="form-control" id="water_rate_reality_water_rent"
                                   placeholder="本次收取水费（元）"
                                   name="reality_water_rent">
                        </div>
                    </div>


                    <%-- <div class="form-group">
                         <label for="water_rate_state" style="float:left;padding:7px 15px 0 27px;">水费状态</label>
                         <div class="col-sm-10">
                             <select class="form-control" id="water_rate_state" placeholder="水费状态" name="state">
                                 <option value="">--请选择--</option>
                                 <c:forEach items="${waterStateType}" var="item">
                                     <option value="${item.dict_id}"<c:if
                                             test="${item.dict_id == state}"> selected</c:if>>${item.dict_item_name }</option>
                                 </c:forEach>
                             </select>
                         </div>
                     </div>--%>

                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" onclick="createWaterRate()">创建</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
            </div>
        </div>
    </div>
</div>


<!-- 电表费用录入对话框 -->
<div class="modal fade" id="newPowerRentDialog" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="myModalLabel24">添加用电信息</h4>
            </div>
            <div class="modal-body">

                <form class="form-horizontal" id="new_power_rent_form">
                    <div class="form-group">
                        <label for="power_rate_property_leasing_num" class="col-sm-2 control-label">
                            租凭合同编号
                        </label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="power_rate_property_leasing_num"
                                   placeholder="租凭合同编号" name="property_leasing_num"/>
                        </div>
                    </div>


                    <div class="form-group">
                        <label for="power_rate_assert_num" class="col-sm-2 control-label">资产编号</label>
                        <div class="col-sm-10">
                            <%--     <input type="text" class="form-control" id="power_assert_num" placeholder="资产编号"
                                        name="assert_num">--%>
                            <select class="form-control" id="power_rate_assert_num" placeholder="资产编号"
                                    name="assert_num">
                                <option value="">--请选择--</option>
                            </select>
                        </div>
                    </div>


                    <div class="form-group date form_date">
                        <label for="power_rate_rent_start_time" class="col-sm-2 control-label">本次收取费用开始时间</label>
                        <div class="col-sm-10">
                            <input name="rent_start_time" id="power_rate_rent_start_time" type='text'
                                   class="form-control picket" placeholder="本次收取费用开始时间"/>
                        </div>
                    </div>

                    <%--  <div class="form-group">
                          <label for="power_rate_rent_end_time" class="col-sm-2 control-label">本次收取费用结束时间</label>
                          <div class="col-sm-10">
                              <input type="text" class="form-control" id="power_rate_rent_end_time"
                                     placeholder="本次收取费用结束时间"
                                     name="rent_end_time">
                          </div>
                      </div>--%>


                    <div class="form-group date form_date">
                        <label for="power_rate_rent_end_time" class="col-sm-2 control-label">本次收取费用结束时间</label>
                        <div class="col-sm-10">
                            <input name="rent_end_time" id="power_rate_rent_end_time" type='text'
                                   class="form-control picket" placeholder="本次收取费用结束时间"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="power_rate_powermeter_num" class="col-sm-2 control-label">电表编号</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="power_rate_powermeter_num" placeholder="电表编号"
                                   name="powermeter_num">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="power_rate_power_num" class="col-sm-2 control-label">电表度数</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="power_rate_power_num" placeholder="电表度数（吨）"
                                   name="power_num">
                        </div>
                    </div>


                    <div class="form-group date form_date">
                        <label for="power_rate_deadline" class="col-sm-2 control-label">截止时间</label>
                        <div class="col-sm-10">
                            <input name="deadline" id="power_rate_deadline" type='text'
                                   class="form-control picket" placeholder="截止时间"/>
                        </div>
                    </div>


                    <div class="form-group">
                        <label for="power_rate_power_rent_received" class="col-sm-2 control-label">实际已收电费</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="power_rate_power_rent_received"
                                   placeholder="实际已收电费（元）"
                                   name="power_rent_recivied">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="power_rate_power_rent" class="col-sm-2 control-label">截取当前应收电费</label>
                        <div class="col-sm-10">
                            <input type="number" class="form-control" id="power_rate_power_rent"
                                   placeholder="截取当前应收电费（元）"
                                   name="power_rent">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="power_rate_reality_power_rent" class="col-sm-2 control-label">本次收取电费</label>
                        <div class="col-sm-10">
                            <input type="number" class="form-control" id="power_rate_reality_power_rent"
                                   placeholder="本次收取电费（元）"
                                   name="reality_power_rent">
                        </div>
                    </div>


                    <%--<div class="form-group">
                        <label for="power_rate_state" style="float:left;padding:7px 15px 0 27px;">电费状态</label>
                        <div class="col-sm-10">
                            <select class="form-control" id="power_rate_state" placeholder="电费状态" name="state">
                                <option value="">--请选择--</option>
                                <c:forEach items="${powerStateType}" var="item">
                                    <option value="${item.dict_id}"<c:if
                                            test="${item.dict_id == state}"> selected</c:if>>${item.dict_item_name }</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>--%>

                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" onclick="createPowerRate()">创建</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
            </div>
        </div>
    </div>
</div>


<script type="text/javascript">


    //编辑合同信息
    function editPropertyLeasing(id) {
        $.ajax({
            type: "get",
            url: "<%=basePath%>propertyLeasing/edit.action",
            data: {"id": id},
            success: function (data) {
                $("#edit_id").val(id);
                $("#edit_property_leasing_num").val(data.property_leasing_num);
                $("#edit_community_name").val(data.community_name);
                var f = document.getElementById("edit_building_num");
                var childs = f.childNodes;
                for (var i = childs.length - 1; i >= 0; i--) {
                    f.removeChild(childs[i]);
                }
                document.getElementById("edit_building_num").options.add(new Option("请选择", ""));
                var rentalLocation = data.rentalLocation;
                var community_name = data.community_name;
                var building_num = data.building_num;
                var tenant = data.tenant;
                var rental_area = data.rental_area;
                var sign_in_time = data.sign_in_time;
                var rent_charge_standard = data.rent_charge_standard;
                var monthly_rental = data.monthly_rental;
                var rent_free_period = data.rent_free_period;
                var rent_period = data.rent_period;

                var collect_rent_way = data.collect_rent_way;
                var collect_rent_time = data.collect_rent_time;
                var estate_charge_standard = data.estate_charge_standard;
                var estate_charge_month = data.estate_charge_month;


                var deposit = data.deposit;
                var deposit_time = data.deposit_time;
                var water_rate = data.water_rate;
                var power_rate = data.power_rate;

                var collect_rate_way = data.collect_rate_way;
                var rent_start_time = data.rent_start_time;
                var rent_end_time = data.rent_end_time;
                var property_leasing_state = data.property_leasing_state;
                var remark = data.remark;
                var phoneNumber=data.phoneNumber;
                var idCard=data.idCard;
                var property_leasing_type=data.property_leasing_type;
                $.ajax({
                    type: "get",
                    url: "<%=basePath%>assertInfol/getAllBuildNum.action",
                    data: {"community_name": data.community_name},
                    success: function (data) {
                        if (data.code == 0) {
                            var building_nums = data.data;
                            for (var i = 0; i < building_nums.length; i++) {
                                if (building_num == building_nums[i]) {
                                    var option = new Option(building_nums[i], building_nums[i]);
                                    document.getElementById("edit_building_num").options.add(option);
                                    option.selected = true;
                                } else {
                                    document.getElementById("edit_building_num").options.add(new Option(building_nums[i], building_nums[i]));
                                }
                            }
                            var f2 = document.getElementById("edit_rentalLocation");
                            var childs2 = f2.childNodes;
                            for (var i = childs2.length - 1; i >= 0; i--) {
                                f2.removeChild(childs2[i]);
                            }

                            $.ajax({
                                type: "get",
                                url: "<%=basePath%>assertInfol/getAllRentalLocation.action",
                                data: {"community_name": community_name, "building_num": building_num},
                                success: function (data) {
                                    if (data.code == 0) {
                                        var rentalLocations = data.data;

                                        for (var i = 0; i < rentalLocations.length; i++) {
                                            var option = new Option(rentalLocations[i], rentalLocations[i]);
                                            var rentalLocationArray = rentalLocation.split(",");
                                            for (var j = 0; j < rentalLocationArray.length; j++) {
                                                if (rentalLocationArray[j] == rentalLocations[i]) {
                                                    option.selected = true;
                                                }
                                            }
                                            document.getElementById("edit_rentalLocation").options.add(option);
                                        }
                                        $("#edit_rentalLocation").selectpicker({
                                            noneSelectedText: '请选择'//默认显示内容投入额
                                        });
                                        $("#edit_rentalLocation").selectpicker('refresh');
                                        // 缺一不可
                                        /* $('#new_rentalLocation').selectpicker('refresh');
                                        $('#new_rentalLocation').selectpicker('render');*/
                                        $("#edit_tenant").val(tenant);
                                        $("#edit_rental_area").val(rental_area);
                                        $("#edit_sign_in_time").val(sign_in_time);
                                        $("#edit_rent_charge_standard").val(rent_charge_standard);
                                        $("#edit_monthly_rental").val(monthly_rental);
                                        $("#edit_rent_free_period").val(rent_free_period);
                                        $("#edit_rent_period").val(rent_period);
                                        $("#edit_collect_rent_way").val(collect_rent_way);
                                        $("#edit_collect_rent_time").val(collect_rent_time);
                                        $("#edit_estate_charge_standard").val(estate_charge_standard);
                                        $("#edit_estate_charge_month").val(estate_charge_month);
                                        $("#edit_deposit").val(deposit);
                                        $("#edit_deposit_time").val(deposit_time);
                                        $("#edit_water_rate").val(water_rate);
                                        $("#edit_power_rate").val(power_rate);
                                        $("#edit_collect_rate_way").val(collect_rate_way);
                                        $("#edit_rent_start_time").val(rent_start_time);
                                        $("#edit_rent_end_time").val(rent_end_time);
                                        $("#edit_property_leasing_state").val(property_leasing_state);
                                        $("#edit_remark").val(remark);
                                        $("#edit_property_leasing_type").val(property_leasing_type);
                                        $("#edit_idCard").val(idCard);
                                        $("#edit_phoneNumber").val(phoneNumber);
                                    } else {
                                        alert(data.msg);
                                        // window.location.reload();
                                    }

                                }
                            });
                        } else {
                            alert(data.msg);
                            // window.location.reload();
                        }

                    }
                });


                /* document.getElementById("new_rentalLocation").options.add(new Option("--请选择--", ""));*/
                /* var building_num = $("#edit_building_num").find("option:selected").text();
                var community_name = $("#edit_community_name").val();
                var rentalLocation = $("#edit_rentalLocation").find("option:selected").text();*/


                /* $("#edit_rentalLocation").prepend(option2);
                $("#edit_rentalLocation").selectpicker('refresh');*/

            }
        });
    }


    /* ,rent_start_time,rent_end_time,deposit*/
    //编辑押金
    function editDeposit(id,status) {
        if(status!='30'){
            alert("合同没有审核通过请先审核");
            return  ;
        }
        $.ajax({
            type: "get",
            url: "<%=basePath%>propertyLeasing/edit.action",
            data: {"id": id},
            success: function (data) {
                $("#add2_property_leasing_num").val(data.property_leasing_num);
                $("#add_rent_start_time").val(data.rent_start_time);
                $("#add_rent_end_time").val(data.rent_end_time);
                $("#add_deposit").val(data.deposit);
            }
        });
    }


    function editRental(id,status) {

        if(status!='30'){
            alert("合同没有审核通过请先审核");
            return  ;
        }
        var f = document.getElementById("rental_year_months");
        var childs = f.childNodes;
        for (var i = childs.length - 1; i >= 0; i--) {
            f.removeChild(childs[i]);
        }
        document.getElementById("rental_year_months").options.add(new Option("--请选择--", ""));
        $.ajax({
            type: "get",
            url: "<%=basePath%>propertyLeasing/editRental.action",
            data: {"id": id},
            success: function (data) {
                $("#rental_property_leasing_num").val(data.property_leasing_num);
                $("#rental_rent_received").val(data.rent_recivied);
                $("#rental_rental").val(data.rental);
                $("#rental_day").val(data.day);
                /* $("#rental_reality_rental").val(data.reality_rental);*/
                for (var i = 0; i < data.totalRentals.length; i++) {
                    document.getElementById("rental_year_months").options.add(new Option(data.totalRentals[i].year_months, data.totalRentals[i].id));
                }
            }
        });

    }


    function editRentalByDef(id,status) {

        if(status!='30'){
            alert("合同没有审核通过请先审核");
            return  ;
        }
        $.ajax({
            type: "get",
            url: "<%=basePath%>propertyLeasing/editRental.action",
            data: {"id": id},
            success: function (data) {
                $("#rental2_property_leasing_num").val(data.property_leasing_num);
                $("#rental2_rent_received").val(data.rent_recivied);
                $("#rental2_rental").val(data.rental);
                $("#rental2_rent_start_time").val(data.rent_start_time);
                $("#rental2_rent_end_time").val(data.rent_end_time);
                /* $("#rental_reality_rental").val(data.reality_rental);*/
                /* for (var i = 0; i < data.totalRentals.length; i++) {
                document.getElementById("rental_year_months").options.add(new Option(data.totalRentals[i].year_months, data.totalRentals[i].id));
                }*/
            }
        });

    }

    function editEstateByDef(id,status) {
        if(status!='30'){
            alert("合同没有审核通过请先审核");
            return  ;
        }
        $.ajax({
            type: "get",
            url: "<%=basePath%>propertyLeasing/editEstate.action",
            data: {"id": id},
            success: function (data) {
                $("#estate2_property_leasing_num").val(data.property_leasing_num);
                $("#estate2_estate_received").val(data.estate_recivied);
                $("#estate2_estate").val(data.estate);
                $("#estate2_rent_start_time").val(data.rent_start_time);
                $("#estate2_rent_end_time").val(data.rent_end_time);
                /* $("#rental_reality_rental").val(data.reality_rental);*/
                /* for (var i = 0; i < data.totalRentals.length; i++) {
                document.getElementById("rental_year_months").options.add(new Option(data.totalRentals[i].year_months, data.totalRentals[i].id));
                }*/
            }
        });


    }

    function editPropertyLeasingComment(id) {

        $("#edit_comment_id").val(id);
        $("#edit_comment").val("");
        $("#edit_comment_state").val("");
    }

    function addComment() {
        /* $.ajax({
             type: "get",
             url: "
        <%=basePath%>assertInfol/changeState.action",
            data: {"id": id, "flag": flag},
            success: function (data) {
                if (data.code == 0) {
                    alert(data.msg);
                    window.location.reload();
                } else {
                    alert(data.msg);
                    window.location.reload();
                }
            }
        });*/


        $.post("<%=basePath%>propertyLeasing/changeState.action", $("#edit_contract_comment_form").serialize(), function (data) {
            if (data.code == 0) {
                alert(data.msg);
                window.location.reload();
            } else {
                alert(data.msg);
                window.location.reload();

            }
        });
    }


    function propertyLeasingSubmit(id) {
        if (confirm('确定要提交审核吗?')) {
            $.ajax({
                type: "get",
                url: "<%=basePath%>propertyLeasing/propertyLeasingSubmit.action",
                data: {"id": id},
                success: function (data) {
                    if (data.code == 0) {
                        alert(data.msg);
                        window.location.reload();
                    } else {
                        alert(data.msg);
                        window.location.reload();
                    }
                }
            });
        }
    }


    //显示批注
    function propertyLeasingComments(id) {
        $.ajax({
            type: "get",
            url: "<%=basePath%>propertyLeasing/getComments.action",
            data: {"id": id},
            success: function (data) {
                if (data.code == 0) {

                    if (data.data.length == 0) {
                        alert("没有批次信息");
                        window.location.reload();
                        return;
                    }
                    var $table = $("#tbody");
                    $table.empty();
                    var $tr;
                    for (var i = 0; i < data.data.length; i++) {
                        $tr = $("<tr>" +
                            "<td>" + data.data[i].id + "</td>" +
                            "<td>" + data.data[i].userId + "</td>" +
                            "<td>" + data.data[i].fullMessage + "</td>" +
                            "<td>" + getMyDate(data.data[i].time) + "</td>" +

                            /* "<td>"+data.propertyLeasings[i].rent_start_time+"</td>"+
                            "<td>"+data.propertyLeasings[i].rent_end_time+"</td>"+*/
                            "</tr>");
                        $table.append($tr);
                    }
                } else {
                    alert(data.msg);
                    window.location.reload();
                }
            }
        });
    }

    function editEstate(id,status) {
        if(status!='30'){
            alert("合同没有审核通过请先审核");
            return  ;
        }
        var f = document.getElementById("estate_year_months");
        var childs = f.childNodes;
        for (var i = childs.length - 1; i >= 0; i--) {
            f.removeChild(childs[i]);
        }
        document.getElementById("estate_year_months").options.add(new Option("--请选择--", ""));
        $.ajax({
            type: "get",
            url: "<%=basePath%>propertyLeasing/editEstate.action",
            data: {"id": id},
            success: function (data) {
                $("#estate_property_leasing_num").val(data.property_leasing_num);
                $("#estate_rent_start_time").val(data.rent_start_time);
                $("#estate_rent_end_time").val(data.rent_end_time);
                $("#estate_estate_received").val(data.estate_recivied);
                $("#estate_estate").val(data.estate);
                $("#estate_day").val(data.day);
                $("#estate_reality_estate").val(data.reality_estate);
                for (var i = 0; i < data.totalEstates.length; i++) {
                    document.getElementById("estate_year_months").options.add(new Option(data.totalEstates[i].year_months, data.totalEstates[i].id));
                }
            }
        });

    }

    function editWater(id,status) {
        if(status!='30'){
            alert("合同没有审核通过请先审核");
            return  ;
        }
        var f = document.getElementById("water_assert_num");
        var childs = f.childNodes;
        for (var i = childs.length - 1; i >= 0; i--) {
            f.removeChild(childs[i]);
        }
        document.getElementById("water_assert_num").options.add(new Option("--请选择--", ""));
        $.ajax({
            type: "get",
            url: "<%=basePath%>propertyLeasing/find.action",
            data: {"id": id},
            success: function (data) {

                if (data.code == 0) {
                    if (data.data.assertInfols.length == 0) {
                        alert("该合同并未关联任何资产，请先关联资产");
                        return;
                    }
                    $("#water_property_leasing_num").val(data.data.property_leasing_num);
                    for (var i = 0; i < data.data.assertInfols.length; i++) {
                        document.getElementById("water_assert_num").options.add(new Option(data.data.assertInfols[i].assert_num, data.data.assertInfols[i].assert_num));
                    }
                }else{
                    alert(data.msg);
                }

                /* $("#water_watermeter_num").val(data.assertInfols[0].watermeter_num);*/
            }
        });
    }

    function editWaterRent(id,status) {
        if(status!='30'){
            alert("合同没有审核通过请先审核");
            return  ;
        }
        var f = document.getElementById("water_rate_assert_num");
        var childs = f.childNodes;
        for (var i = childs.length - 1; i >= 0; i--) {
            f.removeChild(childs[i]);
        }
        document.getElementById("water_rate_assert_num").options.add(new Option("--请选择--", ""));
        $.ajax({
            type: "get",
            url: "<%=basePath%>propertyLeasing/find.action",
            data: {"id": id},
            success: function (data) {

                if (data.code == 0) {
                    if (data.data.assertInfols.length == 0) {
                        alert("该合同并未关联任何资产，请先关联资产");
                        return;
                    }
                    $("#water_rate_property_leasing_num").val(data.data.property_leasing_num);
                    $("#water_rate_rent_start_time").val(data.data.rent_start_time);
                    $("#water_rate_rent_end_time").val(data.data.rent_end_time);
                    for (var i = 0; i < data.data.assertInfols.length; i++) {
                        document.getElementById("water_rate_assert_num").options.add(new Option(data.data.assertInfols[i].assert_num, data.data.assertInfols[i].assert_num));
                    }
                }else{
                    alert(data.msg);
                }

                /* $("#water_rate_watermeter_num").val(data.assertInfols[0].watermeter_num);*/
            }
        });

    }


    function editPowerRent(id,status) {
        if(status!='30'){
            alert("合同没有审核通过请先审核");
            return  ;
        }
        var f = document.getElementById("power_rate_assert_num");
        var childs = f.childNodes;
        for (var i = childs.length - 1; i >= 0; i--) {
            f.removeChild(childs[i]);
        }
        document.getElementById("power_rate_assert_num").options.add(new Option("--请选择--", ""));
        $.ajax({
            type: "get",
            url: "<%=basePath%>propertyLeasing/find.action",
            data: {"id": id},
            success: function (data) {
                if (data.code == 0) {
                    if (data.data.assertInfols.length == 0) {
                        alert("该合同并未关联任何资产，请先关联资产");
                        return;
                    }
                    $("#power_rate_property_leasing_num").val(data.data.property_leasing_num);
                    $("#power_rate_rent_start_time").val(data.data.rent_start_time);
                    $("#power_rate_rent_end_time").val(data.data.rent_end_time);
                    for (var i = 0; i < data.data.assertInfols.length; i++) {
                        document.getElementById("power_rate_assert_num").options.add(new Option(data.data.assertInfols[i].assert_num, data.data.assertInfols[i].assert_num));
                    }

                }

                /* $("#water_rate_watermeter_num").val(data.assertInfols[0].watermeter_num);*/
            }
        });

    }


    // 创建水费
    function createWaterRate() {
        $.post("<%=basePath%>assertWaterRent/create.action",
            $("#new_water_rent_form").serialize(), function (data) {
                if (data.code == 0) {
                    alert(data.msg);
                    window.location.reload();
                } else {
                    alert(data.msg);
                    window.location.reload();
                }
            });
    }

    // 创建电费
    function createPowerRate() {
        $.post("<%=basePath%>assertPowerRent/create.action",
            $("#new_power_rent_form").serialize(), function (data) {
                if (data.code == 0) {
                    alert(data.msg);
                    window.location.reload();
                } else {
                    alert(data.msg);
                    window.location.reload();
                }
            });
    }

    function clearWaterRent() {
        $("#water_rate_property_leasing_num").val("");
        $("#water_rate_assert_num").val("");
        $("#water_rate_rent_start_time").val("");
        $("#water_rate_rent_end_time").val("");
        $("#water_rate_reality_water_rent").val("");
        $("#water_rate_water_rent").val("");
        $("#water_rate_state").val("");
        $("#water_rate_water_num").val("");
        $("#water_rate_water_rent_received").val("");
    }

    function editPower(id,status) {
        if(status!='30'){
            alert("合同没有审核通过请先审核");
            return  ;
        }
        var f = document.getElementById("power_assert_num");
        var childs = f.childNodes;
        for (var i = childs.length - 1; i >= 0; i--) {
            f.removeChild(childs[i]);
        }
        document.getElementById("power_assert_num").options.add(new Option("--请选择--", ""));
        $.ajax({
            type: "get",
            url: "<%=basePath%>propertyLeasing/find.action",
            data: {"id": id},
            success: function (data) {
                if(data.code==0){
                    if (data.data.assertInfols.length == 0) {
                        alert("该合同并未关联任何资产，请先关联资产");
                        return;
                    }
                    $("#power_property_leasing_num").val(data.data.property_leasing_num);
                    for (var i = 0; i < data.data.assertInfols.length; i++) {
                        document.getElementById("power_assert_num").options.add(new Option(data.data.assertInfols[i].assert_num, data.data.assertInfols[i].assert_num));
                    }
                }else{
                    alert(data.msg);
                }


            }
        });
    }

    function createWater() {
        $.post("<%=basePath%>assertWater/create.action",
            $("#new_water_form").serialize(), function (data) {
                if (data.code == 0) {
                    alert(data.msg);
                    window.location.reload();
                } else {
                    alert(data.msg);
                    window.location.reload();
                }
            });
    }

    function createPower() {
        $.post("<%=basePath%>assertPower/create.action",
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

    function createEstate() {
        var realityRental = $("#estate_reality_estate").val();
        if (realityRental < 0) {
            alert("缴纳金额不能够为负数");
            window.location.reload();
            return;
        }
        $.post("<%=basePath%>assertEstate/create.action",
            $("#new_estate_form").serialize(), function (data) {
                if (data.code == 0) {
                    alert(data.msg);
                    window.location.reload();
                } else {
                    alert(data.msg);
                    window.location.reload();
                }
            });
    }

    function createEstate2() {
        $.post("<%=basePath%>assertEstate/create2.action",
            $("#new2_estate_form").serialize(), function (data) {
                if (data.code == 0) {
                    alert(data.msg);
                    window.location.reload();
                } else {
                    alert(data.msg);
                    window.location.reload();
                }
            });
    }

    // 显示租金
    function showEstate(id) {
        $.ajax({
            type: "get",
            url: "<%=basePath%>assertEstate/find.action",
            data: {"id": id},
            success: function (data) {
                $("#estate_show_property_leasing_num").val(data.property_leasing_num);
                $("#estate_show_estate").val(data.estate);
                $("#estate_show_reality_estate").val(data.reality_estate);
                $("#estate_show_rent_start_time").val(data.rent_start_time);
                $("#estate_show_rent_end_time").val(data.rent_end_time);
                $("#estate_show_estate_received").val(data.estate_recivied);
                $("#estate_show_state").val(data.state);
            }
        });

    }

    function createRental() {
        var realityRental = $("#rental_reality_rental").val();
        if (realityRental < 0) {
            alert("缴纳金额不能够为负数");
            window.location.reload();
            return;
        }
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

    function createRental2() {
        var realityRental = $("#rental2_reality_rental").val();
        if (realityRental < 0) {
            alert("缴纳金额不能够为负数");
            return;
        }
        $.post("<%=basePath%>assertRental/create2.action",
            $("#new2_rental_form").serialize(), function (data) {
                if (data.code == 0) {
                    alert(data.msg);
                    window.location.reload();
                } else {
                    alert(data.msg);
                    window.location.reload();
                }
            });
    }

    // 创建押金
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

    // 显示押金
    /* function showDeposit(property_leasing_num) {
    $.ajax({
    type: "get",
    url: "/assertDeposit/find.action",
    data: {"property_leasing_num": property_leasing_num},
    success: function (data) {
    $("#show_property_leasing_num").val(data.property_leasing_num);
    $("#show_deposit").val(data.deposit);
    $("#show_rent_start_time").val(data.rent_start_time);
    $("#show_rent_end_time").val(data.rent_end_time);
    $("#show_state").val(data.state);
    $("#show_reality_deposit").val(data.reality_deposit);
    $("#show_deadline").val(data.deadline);
    }
    });

    }*/


    // 显示租金
    function showRental(id) {
        $.ajax({
            type: "get",
            url: "<%=basePath%>assertRental/find.action",
            data: {"id": id},
            success: function (data) {
                $("#rental_show_property_leasing_num").val(data.property_leasing_num);
                $("#rental_show_rental").val(data.rental);
                $("#rental_show_reality_rental").val(data.reality_rental);
                $("#rental_show_rent_start_time").val(data.rent_start_time);
                $("#rental_show_rent_end_time").val(data.rent_end_time);
                $("#rental_show_rent_received").val(data.rent_recivied);
                /* $("#rental_show_deadline").val(data.deadline);*/
                $("#rental_show_state").val(data.state);
            }
        });

    }

    //显示合同对应的资产信息
    function showAssert(id) {
        $.ajax({
            type: "get",
            url: "<%=basePath%>propertyLeasing/find.action",
            data: {"id": id},
            success: function (data) {
                if (data.data.assertInfols.length == 0) {
                    alert("该合同并未关联任何资产，请先关联");
                    return;
                }
                var $table = $("#tbody");
                $table.empty();
                var $tr;
                for (var i = 0; i < data.data.assertInfols.length; i++) {
                    $tr = $("<tr>" +
                        "<td>" + data.data.assertInfols[i].id + "</td>" +
                        "<td>" + data.data.assertInfols[i].assert_num + "</td>" +
                        "<td>" + data.data.assertInfols[i].card_num + "</td>" +
                        "<td>" + data.data.assertInfols[i].community_name + "</td>" +
                        "<td>" + data.data.assertInfols[i].building_num + "</td>" +
                        "<td>" + data.data.assertInfols[i].room_number + "</td>" +
                        "<td>" + data.data.assertInfols[i].floorage + "</td>" +
                        "<td>" + data.data.assertInfols[i].watermeter_num + "</td>" +
                        "<td>" + data.data.assertInfols[i].electricmeter_num + "</td>" +
                        "<td>" + data.data.assertInfols[i].floor_state + "</td>" +
                        "</tr>");
                    $table.append($tr)
                }

            }
        });

    }

    //添加水电信息
    function addAssert(property_leasing_num,status) {

        if(status!='30'){
            alert("请先审批通过合同.");
            return ;
        }

        var f = document.getElementById("edit_customerFrom");
        var childs = f.childNodes;
        for (var i = childs.length - 1; i >= 0; i--) {
            f.removeChild(childs[i]);
        }
        $.ajax({
            type: "get",
            url: "<%=basePath%>propertyLeasing/idlestate.action",
            data: {"property_leasing_num": property_leasing_num},
            success: function (data) {
                $("#add_property_leasing_num").val(property_leasing_num);
                if (data.length == 0) {
                    alert("该合同未关联资产信息");
                    return;
                }
                for (var i = 0; i < data.length; i++) {
                    document.getElementById("edit_customerFrom").options.add(new Option(data[i].assert_num, data[i].assert_num));
                }

            }
        });
    }

    //更新合同信息
    function updatePropertyLeasing() {

        if (confirm('确实要更新该合同吗?会清除掉以前的租金和物业费信息。')) {
            $.post("<%=basePath%>propertyLeasing/update.action", $("#edit_case_form").serialize(), function (data) {
                if (data.code == 0) {
                    alert(data.msg);
                    window.location.reload();
                } else {
                    alert(data.msg);
                    window.location.reload();
                }
            });
        }

    }

    //删除合同信息
    function deletePropertyLeasing(id) {
        if (confirm('确实要删除该合同吗?')) {
            $.post("<%=basePath%>propertyLeasing/delete.action", {"id": id}, function (data) {
                if (data.code == 0) {
                    alert(data.msg);
                    window.location.reload();
                } else {
                    alert(data.msg);
                    window.location.reload();
                }
            });
        }
    }

    function getMyDate(str) {
        var oDate = new Date(str),
            oYear = oDate.getFullYear(),
            oMonth = oDate.getMonth() + 1,
            oDay = oDate.getDate(),
            oHour = oDate.getHours(),
            oMin = oDate.getMinutes(),
            oSen = oDate.getSeconds(),
            oTime = oYear + '-' + addZero(oMonth) + '-' + addZero(oDay) + ' ' + addZero(oHour) + ':' +
                addZero(oMin) + ':' + addZero(oSen);
        return oTime;
    }

    //补零操作
    function addZero(num) {
        if (parseInt(num) < 10) {
            num = '0' + num;
        }
        return num;
    }

    //清除合同信息
    function clearPropertyLeasing() {
        $("#new_property_leasing_num").val("");
        $("#new_building_num").val("");
        $("#new_community_name").val("");
        $("#new_tenant").val("");
        $("#new_rental_area").val("");
        $("#new_sign_in_time").val("");
        $("#new_rent_charge_standard").val("");
        $("#new_rentalLocation").val("");
        $("#new_monthly_rental").val("");
        $("#new_rent_free_period").val("");
        $("#new_rent_period").val("");
        $("#new_collect_rent_way").val("");
        $("#new_collect_rent_time").val("");
        $("#new_estate_charge_standard").val("");
        $("#new_estate_charge_month").val("");
        $("#new_deposit").val("");
        $("#new_deposit_time").val("");
        $("#new_water_rate").val("");
        $("#new_power_rate").val("");
        $("#new_collect_rate_way").val("");
        $("#new_rent_start_time").val("");
        $("#new_rent_end_time").val("");
        $("#new_property_leasing_state").val("");
        $("#new_property_leasing_type").val("");
        $("#new_remark").val("");
        $("#new_idCard").val("");
        $("#new_phoneNumber").val("");
    }


    // 创建合同信息
    function createPropertyLeasing() {
        $.post("<%=basePath%>propertyLeasing/create.action",
            $("#new_PropertyLeasing_form").serialize(), function (data) {
                if (data.code == 0) {
                    alert(data.msg);
                    window.location.reload();
                } else {
                    alert(data.msg);
                    // window.location.reload();
                }
            });
    }

    // 创建合同关系信息
    function addPropertyLeasing() {
        $.post("<%=basePath%>propertyLeasing/add.action",
            $("#addAssert_form").serialize(), function (data) {
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


<script type="text/javascript">


    /* $(function () {
    $('.form_date').datetimepicker({
    language: 'zh-CN',
    format: 'yyyymmdd',//显示格式
    weekStart: 1,
    todayBtn: 1,
    autoclose: 1,
    todayHighlight: 1,
    startView: 2,
    minView: 2,
    forceParse: 0
    });
    });*/


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


    $('#water_assert_num').change(function () {
        var assertNum = $("#water_assert_num").find("option:selected").val();
        var  property_leasing_num = $("#water_property_leasing_num").val();
        $("#water_watermeter_num").val("");
        $.ajax({
            type: "get",
            url: "<%=basePath%>assertInfol/findByAssertNum.action",
            data: {"property_leasing_num":property_leasing_num,"assert_num": assertNum},
            success: function (data) {
                if(data.code==0){
                    $("#water_watermeter_num").val(data.data.watermeter_num);
                }else{
                    alert(data.msg);
                }

            }
        });
    });

    $('#power_assert_num').change(function () {
        var assertNum = $("#power_assert_num").find("option:selected").val();
        var  property_leasing_num = $("#power_property_leasing_num").val();
        $("#power_powermeter_num").val("");
        $.ajax({
            type: "get",
            url: "<%=basePath%>assertInfol/findByAssertNum.action",
            data: {"property_leasing_num":property_leasing_num,"assert_num": assertNum},
            success: function (data) {
                if(data.code==0){
                    $("#power_powermeter_num").val(data.data.electricmeter_num);
                }else{
                    alert(data.msg);
                }

            }
        });
    });


    $('#water_rate_assert_num').change(function () {
        var assertNum = $("#water_rate_assert_num").find("option:selected").val();
        var property_leasing_num = $("#water_rate_property_leasing_num").val();
        $("#water_rate_watermeter_num").val("");
        $("#water_rate_water_num").val("");
        $("#water_rate_water_rent").val("");
        $("#water_rate_water_rent_received").val("");
        $("#water_rate_deadline").val("");
        $.ajax({
            type: "get",
            url: "<%=basePath%>assertWater/find.action",
            data: {"assert_num": assertNum, "property_leasing_num": property_leasing_num},
            success: function (data) {
                if(data.code==0){
                    $("#water_rate_watermeter_num").val(data.data.watermeter_num);
                    $("#water_rate_water_num").val(data.data.water_num);
                    $("#water_rate_water_rent").val(data.data.water_rent);
                    $("#water_rate_water_rent_received").val(data.data.water_rent_recivied);
                    $("#water_rate_deadline").val(data.data.deadline);
                }else{
                    alert(data.msg);
                }

            }
        });
    });


    $('#power_rate_assert_num').change(function () {

        var assertNum = $("#power_rate_assert_num").find("option:selected").val();
        var property_leasing_num = $("#power_rate_property_leasing_num").val();
        $("#power_rate_powermeter_num").val("");
        $("#power_rate_power_num").val("");
        $("#power_rate_power_rent").val("");
        $("#power_rate_power_rent_received").val("");
        $("#power_rate_deadline").val("");
        $.ajax({
            type: "get",
            url: "<%=basePath%>assertPower/find.action",
            data: {"assert_num": assertNum, "property_leasing_num": property_leasing_num},
            success: function (data) {
                if(data.code==0){
                    $("#power_rate_powermeter_num").val(data.data.powermeter_num);
                    $("#power_rate_power_num").val(data.data.power_num);
                    $("#power_rate_power_rent").val(data.data.power_rent);
                    $("#power_rate_power_rent_received").val(data.data.power_rent_recivied);
                    $("#power_rate_deadline").val(data.data.deadline);
                }else{
                    alert(data.msg)
                }

            }
        });
    });


    /* $('#rental_year_months').change(function () {
         var time = $("#rental_year_months").find("option:selected").text();
         var year = time.substring(0, 4);
         var month = time.substring(4, 6);
         var day = $("#rental_day").val();
         var nextSize = new Date(year, parseInt(month), 0).getDate();//获取这个月的总天数
         if (nextSize < parseInt(day)) {
             day = nextSize;
         }
         var startTime = year + month + day;
         var endTime = getXmonthToday(1, year, month, day);
         $("#rental_rent_start_time").val(startTime);
         $("#rental_rent_end_time").val(endTime);
         var property_leasing_num = $("#rental_property_leasing_num").val();

         $.ajax({
             type: "get",
             url: "<%=basePath%>/propertyLeasing/editRentalByStartDate.action",
            data: {"startDate": startTime, "property_leasing_num": property_leasing_num},
            success: function (data) {
                if (data.code == 0) {
                    $("#rental_reality_rental").val(data.msg);
                } else {
                    alert(data.msg);
                    // window.location.reload();
                }
            }
        });

    });*/

    $('#rental_year_months').change(function () {
        var time = $("#rental_year_months").find("option:selected").text();
        var year = time.substring(0, 4);
        var month = time.substring(4, 6);
        var day = $("#rental_day").val();
        var nextSize = new Date(year, parseInt(month), 0).getDate();//获取这个月的总天数
        if (nextSize < parseInt(day)) {
            day = nextSize;
        }
        var startTime = year + month + day;
        var endTime = getXmonthToday(1, year, month, day);
        $("#rental_rent_start_time").val(startTime);
        $("#rental_rent_end_time").val(endTime);

        var id = $("#rental_year_months").find("option:selected").val();
        /* var property_leasing_num = $("#rental_property_leasing_num").val();*/

        $.ajax({
            type: "get",
            url: "<%=basePath%>propertyLeasing/editRentalByStartDate.action",
            data: {"id": id},
            success: function (data) {
                if (data.code == 0) {
                    $("#rental_reality_rental").val(data.msg);
                } else {
                    alert(data.msg);
                    // window.location.reload();
                }
            }
        });

    });


    $('#estate_year_months').change(function () {
        var time = $("#estate_year_months").find("option:selected").text();
        var year = time.substring(0, 4);
        var month = time.substring(4, 6);
        var day = $("#estate_day").val();
        var nextSize = new Date(year, parseInt(month), 0).getDate();//获取这个月的总天数
        if (nextSize < parseInt(day)) {
            day = nextSize;
        }
        var startTime = year + month + day;
        var endTime = getXmonthToday(1, year, month, day);
        $("#estate_rent_start_time").val(startTime);
        $("#estate_rent_end_time").val(endTime);
        var id = $("#estate_year_months").find("option:selected").val();
        $.ajax({
            type: "get",
            url: "<%=basePath%>propertyLeasing/editEstateByid.action",
            data: {"id": id},
            success: function (data) {
                if (data.code == 0) {
                    $("#estate_reality_estate").val(data.msg);
                } else {
                    alert(data.msg);
                    // window.location.reload();
                }
            }
        });
    });


    $('#new_community_name').change(function () {
        var f = document.getElementById("new_building_num");
        var childs = f.childNodes;
        for (var i = childs.length - 1; i >= 0; i--) {
            f.removeChild(childs[i]);
        }
        document.getElementById("new_building_num").options.add(new Option("--请选择--", ""));
        var community_name = $("#new_community_name").find("option:selected").text();
        $.ajax({
            type: "get",
            url: "<%=basePath%>assertInfol/getAllBuildNum.action",
            data: {"community_name": community_name, "floor_state": "2"},
            success: function (data) {
                if (data.code == 0) {
                    var building_nums = data.data;
                    for (var i = 0; i < building_nums.length; i++) {
                        document.getElementById("new_building_num").options.add(new Option(building_nums[i], building_nums[i]));
                    }
                } else {
                    alert(data.msg);
                    // window.location.reload();
                }

            }
        });
    });


    $('#edit_community_name').change(function () {
        var building_num = $("#edit_building_num").find("option:selected").text();
        var f = document.getElementById("edit_building_num");
        var childs = f.childNodes;
        for (var i = childs.length - 1; i >= 0; i--) {
            f.removeChild(childs[i]);
        }
        document.getElementById("edit_building_num").options.add(new Option("--请选择--", ""));
        var community_name = $("#edit_community_name").find("option:selected").text();
        $.ajax({
            type: "get",
            url: "<%=basePath%>assertInfol/getAllBuildNum.action",
            data: {"community_name": community_name},
            success: function (data) {
                if (data.code == 0) {
                    var building_nums = data.data;
                    for (var i = 0; i < building_nums.length; i++) {
                        if (building_num == building_nums[i]) {
                            var option = new Option(building_nums[i], building_nums[i]);
                            document.getElementById("edit_building_num").options.add(option);
                            option.selected = true;
                        } else {
                            document.getElementById("edit_building_num").options.add(new Option(building_nums[i], building_nums[i]));
                        }
                    }
                } else {
                    alert(data.msg);
                    // window.location.reload();
                }

            }
        });
    });


    $('#new_building_num').change(function () {
        var f = document.getElementById("new_rentalLocation");
        var childs = f.childNodes;
        for (var i = childs.length - 1; i >= 0; i--) {
            f.removeChild(childs[i]);
        }
        /* document.getElementById("new_rentalLocation").options.add(new Option("--请选择--", ""));*/
        var building_num = $("#new_building_num").find("option:selected").text();
        var community_name = $("#new_community_name").val();
        $.ajax({
            type: "get",
            url: "<%=basePath%>assertInfol/getAllRentalLocation.action",
            data: {"community_name": community_name, "building_num": building_num, "floor_state": "2"},
            success: function (data) {
                if (data.code == 0) {
                    var rentalLocations = data.data;

                    /*for (var i = 0; i < rentalLocations.length; i++) {
                    document.getElementById("new_rentalLocation").options.add(new Option(rentalLocations[i], rentalLocations[i]));
                    }*/
                    for (var i = 0; i < rentalLocations.length; i++) {
                        $('#new_rentalLocation').append("<option value=" + rentalLocations[i] + ">" + rentalLocations[i] + "</option>");
                    }
                    $("#new_rentalLocation").selectpicker({
                        noneSelectedText: '请选择'//默认显示内容投入额
                    });
                    $("#new_rentalLocation").selectpicker('refresh');
                    // 缺一不可
                    /* $('#new_rentalLocation').selectpicker('refresh');
                    $('#new_rentalLocation').selectpicker('render');*/

                } else {
                    alert(data.msg);
                    // window.location.reload();
                }

            }
        });
    });


    $('#edit_building_num').change(function () {
        var rentalLocation = $("#edit_rentalLocation").find("option:selected").text();
        var f = document.getElementById("edit_rentalLocation");
        var childs = f.childNodes;
        for (var i = childs.length - 1; i >= 0; i--) {
            f.removeChild(childs[i]);
        }
        /* document.getElementById("new_rentalLocation").options.add(new Option("--请选择--", ""));*/
        var building_num = $("#edit_building_num").find("option:selected").text();
        var community_name = $("#edit_community_name").val();
        $.ajax({
            type: "get",
            url: "<%=basePath%>assertInfol/getAllRentalLocation.action",
            data: {"community_name": community_name, "building_num": building_num},
            success: function (data) {
                if (data.code == 0) {
                    var rentalLocations = data.data;

                    for (var i = 0; i < rentalLocations.length; i++) {
                        var option = new Option(rentalLocations[i], rentalLocations[i]);
                        var rentalLocationArray = rentalLocation.split(",");
                        for (var j = 0; j < rentalLocationArray.length; j++) {
                            if (rentalLocationArray[j] == rentalLocations[i]) {
                                option.selected = true;
                            }
                        }
                        document.getElementById("edit_rentalLocation").options.add(option);
                    }
                    /*for (var i = 0; i < rentalLocations.length; i++) {

                    $('#edit_rentalLocation').append("<option value=" + rentalLocations[i] + ">" + rentalLocations[i] + "</option>");
                    }*/
                    $("#edit_rentalLocation").selectpicker({
                        noneSelectedText: '请选择'//默认显示内容投入额
                    });
                    $("#edit_rentalLocation").selectpicker('refresh');
                    // 缺一不可
                    /* $('#new_rentalLocation').selectpicker('refresh');
                    $('#new_rentalLocation').selectpicker('render');*/

                } else {
                    alert(data.msg);
                    // window.location.reload();
                }

            }
        });
    });

    //导入
    function uploadPropertyLeasing() {
        window.location.href = "<%=basePath%>propertyLeasing/showUpload.action";
    }

    //导出
    function downloadPropertyLeasing() {
        $("#mainForm").attr("action", "${pageContext.request.contextPath }/propertyLeasing/downloadPropertyLeasing.action").submit();
        $("#mainForm").attr("action", "${pageContext.request.contextPath }/propertyLeasing/list.action");
    }

    //导出
    function downloadPropertyLeasingAll() {
        $("#mainForm").attr("action", "${pageContext.request.contextPath }/propertyLeasing/downloadPropertyLeasingAll.action").submit();
        $("#mainForm").attr("action", "${pageContext.request.contextPath }/propertyLeasing/list.action");
    }

    function getXmonthToday(type, year, month, day) {
        // type 0 是当天 -1 是上个月 1是下个月
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
