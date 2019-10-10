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
                <h1 class="page-header">资产合同管理</h1>
            </div>
            <!-- /.col-lg-12 -->
        </div>
        <!-- /.row -->
        <div class="panel panel-default">
            <div class="panel-body">
                <form class="form-inline" action="${pageContext.request.contextPath }/propertyLeasing/list.action"
                      method="get">
                    <div class="form-group">
                        <label for="property_leasing_num">合同编号</label>
                        <input type="text" class="form-control" id="property_leasing_num"
                               value="${property_leasing_num }" name="property_leasing_num">
                    </div>
                    <button type="submit" class="btn btn-primary">查询</button>
                    <a href="#" class="btn btn-primary" data-toggle="modal"
                       data-target="#newPropertyLeasingDialog" onclick="clearPropertyLeasing()">新建</a>
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
                            <th>承租人</th>
                            <th>承租面积</th>
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
                            <th>水电费收费方式</th>
                            <th>租期开始时间</th>
                            <th>租期结束时间</th>
                            <th>合同状态</th>

                            <th>备注</th>
                            <th>操作</th>
                            <th>关联</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${page.rows}" var="row">

                            <tr>

                                <td>${row.id}</td>
                                <td>${row.property_leasing_num}</td>
                                <td>${row.tenant}</td>
                                <td>${row.rental_area}</td>
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
                                <td>${row.collect_rate_way}</td>
                                <td><fmt:formatDate value="${row.rent_start_time}" pattern="yyyy-MM-dd"/></td>
                                <td><fmt:formatDate value="${row.rent_end_time}" pattern="yyyy-MM-dd"/></td>
                                <td>${row.property_leasing_state}</td>
                                <td>${row.remark}</td>
                                <td>
                                    <a href="#" class="btn btn-primary btn-xs" data-toggle="modal"
                                       data-target="#caseEditDialog" onclick="editPropertyLeasing(${row.id})">修改</a>
                                    <a href="#" class="btn btn-danger btn-xs"
                                       onclick="deletePropertyLeasing(${row.id})">删除</a>
                                </td>

                                <td>
                                    <a href="#" class="btn btn-primary btn-xs" data-toggle="modal"
                                       data-target="#caseEditDialog" onclick="addAssert(${row.id})">添加</a>
                                    <a href="#" class="btn btn-danger btn-xs"
                                       onclick="show(${row.id})">查看</a>
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


                    <div class="form-group">
                        <label for="edit_tenant" class="col-sm-2 control-label">承租人</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="edit_tenant" placeholder="承租人"
                                   name="tenant"/>
                        </div>
                    </div>


                    <div class="form-group">
                        <label for="edit_rental_area" class="col-sm-2 control-label">承租面积</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="edit_rental_area" placeholder="承租面积"
                                   name="rental_area"/>
                        </div>
                    </div>


                    <div class="form-group">
                        <label for="edit_sign_in_time" class="col-sm-2 control-label">合同签订时间</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="edit_sign_in_time" placeholder="合同签订时间"
                                   name="sign_in_time"/>
                        </div>
                    </div>


                    <div class="form-group">
                        <label for="edit_rent_charge_standard" class="col-sm-2 control-label">租金收费标准</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="edit_rent_charge_standard" placeholder="租金收费标准"
                                   name="rent_charge_standard"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="edit_monthly_rental" class="col-sm-2 control-label">月租</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="edit_monthly_rental" placeholder="月租"
                                   name="monthly_rental"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="edit_rent_free_period" class="col-sm-2 control-label">免租期</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="edit_rent_free_period" placeholder="免租期(月)"
                                   name="rent_free_period"/>
                        </div>
                    </div>


                    <div class="form-group">
                        <label for="edit_rent_period" class="col-sm-2 control-label">合同期</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="edit_rent_period" placeholder="合同期(年)"
                                   name="rent_period"/>
                        </div>
                    </div>


                    <div class="form-group">
                        <label for="edit_collect_rent_way" class="col-sm-2 control-label">租金缴纳方式</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="edit_collect_rent_way"
                                   placeholder="租金缴纳方式(1、全部交清 ，2、月缴，3、免租)"
                                   name="collect_rent_way"/>
                        </div>
                    </div>


                    <div class="form-group">
                        <label for="edit_collect_rent_time" class="col-sm-2 control-label">租金缴纳时间</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="edit_collect_rent_time" placeholder="租金缴纳时间"
                                   name="collect_rent_time"/>
                        </div>
                    </div>


                    <div class="form-group">
                        <label for="edit_estate_charge_standard" class="col-sm-2 control-label">物业收费标准</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="edit_estate_charge_standard"
                                   placeholder="物业收费标准(元/平方米)"
                                   name="estate_charge_standard"/>
                        </div>
                    </div>


                    <div class="form-group">
                        <label for="edit_estate_charge_standard" class="col-sm-2 control-label">月物业费</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="edit_estate_charge_month" placeholder="月物业费"
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

                    <div class="form-group">
                        <label for="edit_deposit_time" class="col-sm-2 control-label">保证金到账时间</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="edit_deposit_time"
                                   placeholder="保证金到账时间（20180616）"
                                   name="deposit_time"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="edit_water_rate" class="col-sm-2 control-label">水费</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="edit_water_rate" placeholder="水费(元/吨)"
                                   name="water_rate"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="edit_power_rate" class="col-sm-2 control-label">电费</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="edit_power_rate" placeholder="电费(元/度)）"
                                   name="power_rate"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="edit_collect_rate_way" class="col-sm-2 control-label">水电费收费方式</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="edit_collect_rate_way"
                                   placeholder="水电费收费方式（1、自缴  2、代缴）"
                                   name="collect_rate_way"/>
                        </div>
                    </div>


                    <div class="form-group">
                        <label for="edit_rent_start_time" class="col-sm-2 control-label">租赁起始时间</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="edit_rent_start_time"
                                   placeholder="租赁起始时间(20190616)"
                                   name="rent_start_time"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="edit_rent_end_time" class="col-sm-2 control-label">租赁结束时间</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="edit_rent_end_time"
                                   placeholder="租赁结束时间(20190619)"
                                   name="rent_end_time"/>
                        </div>
                    </div>


                    <div class="form-group">
                        <label for="edit_property_leasing_state" class="col-sm-2 control-label">合同的状态</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="edit_property_leasing_state"
                                   placeholder="合同的状态（1、生效，2、过期，3、欠款）"
                                   name="property_leasing_state"/>
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

<!-- 创建客户模态框 -->
<div class="modal fade" id="newPropertyLeasingDialog" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="myModalLabel">新建资产合同</h4>
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


                    <div class="form-group">
                        <label for="new_tenant" class="col-sm-2 control-label">承租人</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="new_tenant" placeholder="承租人"
                                   name="tenant"/>
                        </div>
                    </div>


                    <div class="form-group">
                        <label for="new_rental_area" class="col-sm-2 control-label">承租面积</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="new_rental_area" placeholder="承租面积"
                                   name="rental_area"/>
                        </div>
                    </div>


                    <div class="form-group">
                        <label for="new_sign_in_time" class="col-sm-2 control-label">合同签订时间</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="new_sign_in_time" placeholder="合同签订时间"
                                   name="sign_in_time"/>
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
                            <input type="text" class="form-control" id="new_rent_period" placeholder="合同期(年)"
                                   name="rent_period"/>
                        </div>
                    </div>


                    <div class="form-group">
                        <label for="new_collect_rent_way" class="col-sm-2 control-label">租金缴纳方式</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="new_collect_rent_way"
                                   placeholder="租金缴纳方式(1、全部交清 ，2、月缴，3、免租)"
                                   name="collect_rent_way"/>
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

                    <div class="form-group">
                        <label for="new_deposit_time" class="col-sm-2 control-label">保证金到账时间</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="new_deposit_time"
                                   placeholder="保证金到账时间（20180616）"
                                   name="deposit_time"/>
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

                    <div class="form-group">
                        <label for="new_collect_rate_way" class="col-sm-2 control-label">水电费收费方式</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="new_collect_rate_way"
                                   placeholder="水电费收费方式（1、自缴  2、代缴）"
                                   name="collect_rate_way"/>
                        </div>
                    </div>


                    <div class="form-group">
                        <label for="new_rent_start_time" class="col-sm-2 control-label">租赁起始时间</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="new_rent_start_time"
                                   placeholder="租赁起始时间(20190616)"
                                   name="rent_start_time"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="new_rent_end_time" class="col-sm-2 control-label">租赁结束时间</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="new_rent_end_time"
                                   placeholder="租赁结束时间(20190619)"
                                   name="rent_end_time"/>
                        </div>
                    </div>


                    <div class="form-group">
                        <label for="new_property_leasing_state" class="col-sm-2 control-label">合同的状态</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="new_property_leasing_state"
                                   placeholder="合同的状态（1、生效，2、过期，3、欠款）"
                                   name="property_leasing_state"/>
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

    function editPropertyLeasing(id) {
        $.ajax({
            type: "get",
            url: "<%=basePath%>propertyLeasing/edit.action",
            data: {"id": id},
            success: function (data) {
                $("#edit_id").val(data.id);
                $("#edit_property_leasing_num").val(data.property_leasing_num);
                $("#edit_tenant").val(data.tenant);
                $("#edit_rental_area").val(data.rental_area);
                $("#edit_sign_in_time").val(data.sign_in_time);
                $("#edit_rent_charge_standard").val(data.rent_charge_standard);
                $("#edit_monthly_rental").val(data.monthly_rental);
                $("#edit_rent_free_period").val(data.rent_free_period);
                $("#edit_rent_period").val(data.rent_period);
                $("#edit_collect_rent_way").val(data.collect_rent_way);
                $("#edit_collect_rent_time").val(data.collect_rent_time);
                $("#edit_estate_charge_standard").val(data.estate_charge_standard);
                $("#edit_estate_charge_month").val(data.estate_charge_month);
                $("#edit_deposit").val(data.deposit);
                $("#edit_deposit_time").val(data.deposit_time);
                $("#edit_water_rate").val(data.water_rate);

                $("#edit_power_rate").val(data.power_rate);
                $("#edit_collect_rate_way").val(data.collect_rate_way);
                $("#edit_rent_start_time").val(data.rent_start_time);
                $("#edit_rent_end_time").val(data.rent_end_time);
                $("#edit_property_leasing_state").val(data.property_leasing_state);
                $("#edit_remark").val(data.remark);
            }
        });
    }


     function   addAssert(id){
         $.ajax({
             type: "get",
             url: "<%=basePath%>propertyLeasing/edit.action",
             data: {"id": id},
             success: function (data) {
                 $("#edit_id").val(data.id);
                 $("#edit_property_leasing_num").val(data.property_leasing_num);
                 $("#edit_tenant").val(data.tenant);
                 $("#edit_rental_area").val(data.rental_area);
                 $("#edit_sign_in_time").val(data.sign_in_time);
                 $("#edit_rent_charge_standard").val(data.rent_charge_standard);
                 $("#edit_monthly_rental").val(data.monthly_rental);
                 $("#edit_rent_free_period").val(data.rent_free_period);
                 $("#edit_rent_period").val(data.rent_period);
                 $("#edit_collect_rent_way").val(data.collect_rent_way);
                 $("#edit_collect_rent_time").val(data.collect_rent_time);
                 $("#edit_estate_charge_standard").val(data.estate_charge_standard);
                 $("#edit_estate_charge_month").val(data.estate_charge_month);
                 $("#edit_deposit").val(data.deposit);
                 $("#edit_deposit_time").val(data.deposit_time);
                 $("#edit_water_rate").val(data.water_rate);

                 $("#edit_power_rate").val(data.power_rate);
                 $("#edit_collect_rate_way").val(data.collect_rate_way);
                 $("#edit_rent_start_time").val(data.rent_start_time);
                 $("#edit_rent_end_time").val(data.rent_end_time);
                 $("#edit_property_leasing_state").val(data.property_leasing_state);
                 $("#edit_remark").val(data.remark);
             }
         });


     }

    function updatePropertyLeasing() {
        $.post("<%=basePath%>propertyLeasing/update.action", $("#edit_case_form").serialize(), function (data) {
            alert("合同信息修改成功！");
            window.location.reload();
        });
    }

    function deletePropertyLeasing(id) {
        if (confirm('确实要删除该合同吗?')) {
            $.post("<%=basePath%>propertyLeasing/delete.action", {"id": id}, function (data) {
                alert("合同删除更新成功！");
                window.location.reload();
            });
        }
    }


    function clearPropertyLeasing() {
        $("#new_property_leasing_num").val("");
        $("#new_tenant").val("");
        $("#new_rental_area").val("");
        $("#new_sign_in_time").val("");
        $("#new_rent_charge_standard").val("");

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
        $("#new_remark").val("");
    }


    // 创建客户
    function createPropertyLeasing() {
        $.post("<%=basePath%>propertyLeasing/create.action",
            $("#new_PropertyLeasing_form").serialize(), function (data) {
                if (data == "OK") {
                    alert("合同信息创建成功！");
                    window.location.reload();
                } else {
                    alert("合同信息创建失败！");
                    window.location.reload();
                }
            });
    }
</script>

</body>

</html>
