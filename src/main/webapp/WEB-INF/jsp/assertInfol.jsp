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

                    <div class="form-group">
                        <label for="floor_state">资产类型</label>
                        <select class="form-control" id="assertType" placeholder="资产类型" name="assertType">
                            <option value="">--请选择--</option>
                            <c:forEach items="${assertStateType}" var="item">
                                <option value="${item.dict_id}"<c:if
                                        test="${item.dict_id == assertType}"> selected</c:if>>${item.dict_item_name }</option>
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
                            <th>资产类型</th>
                            <th>状态</th>
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

                                <c:if test="${'32' eq row.assertType}">
                                    <td> 店铺</td>
                                </c:if>
                                <c:if test="${'33' eq row.assertType}">
                                    <td> 写字楼</td>
                                </c:if>
                                <c:if test="${'34' eq row.assertType}">
                                    <td> 农贸市场</td>
                                </c:if>
                                <c:if test="${'35' eq row.assertType}">
                                    <td> 厂房</td>
                                </c:if>
                                <c:if test="${'36' eq row.assertType}">
                                    <td> 员工宿舍</td>
                                </c:if>
                                <c:if test="${'37' eq row.assertType}">
                                    <td> 人才公寓</td>
                                </c:if>
                                <c:if test="${'38' eq row.assertType}">
                                    <td> 公租房</td>
                                </c:if>

                                <c:if test="${'1' eq row.floor_state}">
                                    <td>已出租</td>
                                </c:if>
                                <c:if test="${'2' eq row.floor_state}">
                                    <td> 空闲</td>
                                </c:if>
                                <c:if test="${'3' eq row.floor_state}">
                                    <td> 非法占用</td>
                                </c:if>


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

                                    <!--   录入管理功能的实现  -->
                                    <security:authorize access="hasAnyRole('ROLE_ASSERT')">
                                        <c:if test="${row.status eq '28'}">
                                            <a href="#" class="btn btn-success btn-xs"
                                               onclick="assertSubmit(${row.id})">提交审核</a>
                                        </c:if>

                                        <c:if test="${row.status eq '28' || row.status eq  '31' }">
                                            <a href="#" class="btn btn-warning btn-xs" data-toggle="modal"
                                               data-target="#caseEditDialog" onclick="editAssert(${row.id})">修改</a>
                                        </c:if>
                                        <a href="#" class="btn btn-success btn-xs" data-toggle="modal"
                                           data-target="#showLeasingDialog"
                                           onclick="assertComments(${row.id})">查看审核记录</a>
                                    </security:authorize>


                                    <security:authorize access="hasAnyRole('ROLE_ADMIN')">
                                        <%--   <c:if test="${row.status ne '29'}">--%>
                                        <a href="#" class="btn btn-danger btn-xs"
                                           onclick="deleteAssert(${row.id})">删除</a>
                                        <%--</c:if>--%>
                                        <c:if test="${row.status eq '29'}">
                                            <a href="#" class="btn btn-success btn-xs"
                                               data-target="#assertCommentDialog" data-toggle="modal"
                                               onclick="editAssertComment(${row.id})">审核</a>
                                        </c:if>
                                        <a href="#" class="btn btn-warning btn-xs" data-toggle="modal"
                                           data-target="#caseEditDialog" onclick="editAssert(${row.id})">修改</a>
                                        <a href="#" class="btn btn-success btn-xs" data-toggle="modal"
                                           data-target="#showLeasingDialog"
                                           onclick="assertComments(${row.id})">查看审核记录</a>
                                    </security:authorize>


                                    <!--  资产审核员 -->
                                    <security:authorize access="hasAnyRole('ROLE_ASSERTCHECK')">
                                        <c:if test="${row.status eq '29'}">
                                            <a href="#" class="btn btn-success btn-xs"
                                               data-target="#assertCommentDialog" data-toggle="modal"
                                               onclick="editAssertComment(${row.id})">审核</a>
                                        </c:if>
                                        <a href="#" class="btn btn-success btn-xs" data-toggle="modal"
                                           data-target="#showLeasingDialog"
                                           onclick="assertComments(${row.id})">查看审核记录</a>
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


<!-- 审批对话框 -->
<div class="modal fade" id="assertCommentDialog" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="myModalLabel5">审核信息</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" id="edit_assert_comment_form">
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
                        <label for="edit_floor_state" class="col-sm-2 control-label">房屋状态</label>
                        <div class="col-sm-10">
                            <select class="form-control" id="edit_floor_state" placeholder="房屋状态" name="floor_state">
                                <option value="">--请选择--</option>
                                <c:forEach items="${stateType}" var="item">
                                    <option value="${item.dict_id}"<c:if
                                            test="${item.dict_id == floor_state}"> selected</c:if>>${item.dict_item_name }</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>


                    <div class="form-group">
                        <label for="edit_assertType" class="col-sm-2 control-label">资产类型</label>
                        <div class="col-sm-10">
                            <select class="form-control" id="edit_assertType" placeholder="资产类型" name="assertType">
                                <option value="">--请选择--</option>
                                <c:forEach items="${assertStateType}" var="item">
                                    <option value="${item.dict_id}"<c:if
                                            test="${item.dict_id == assertType}"> selected</c:if>>${item.dict_item_name }</option>
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
                        <label for="new_floor_state" class="col-sm-2 control-label">房屋状态</label>
                        <div class="col-sm-10">
                            <select class="form-control" id="new_floor_state" placeholder="房屋状态" name="floor_state">
                                <option value="">--请选择--</option>
                                <c:forEach items="${stateType}" var="item">
                                    <option value="${item.dict_id}">${item.dict_item_name }</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="new_assertType" class="col-sm-2 control-label">资产类型</label>
                        <div class="col-sm-10">
                            <select class="form-control" id="new_assertType" placeholder="资产类型" name="assertType">
                                <option value="">--请选择--</option>
                                <c:forEach items="${assertStateType}" var="item">
                                    <option value="${item.dict_id}">${item.dict_item_name }</option>
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
                $("#edit_assertType").val(data.assertType);
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

    function editAssertComment(id) {

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


        $.post("<%=basePath%>assertInfol/changeState.action", $("#edit_assert_comment_form").serialize(), function (data) {
            if (data.code == 0) {
                alert(data.msg);
                window.location.reload();
            } else {
                alert(data.msg);
                window.location.reload();

            }
        });
    }


    function assertSubmit(id) {
        if (confirm('确定要提交审核吗?')) {
            $.ajax({
                type: "get",
                url: "<%=basePath%>assertInfol/assertSubmit.action",
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
    function assertComments(id) {
        $.ajax({
            type: "get",
            url: "<%=basePath%>assertInfol/getComments.action",
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
