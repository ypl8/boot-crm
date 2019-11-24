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
                <h1 class="page-header">物业费收入管理</h1>
            </div>
            <!-- /.col-lg-12 -->
        </div>
        <!-- /.row -->
        <div class="panel panel-default">
            <div class="panel-body">
                <form class="form-inline" action="${pageContext.request.contextPath }/assertEstate/list.action"
                      method="get" id="mainForm">
                    <div class="form-group">
                        <label for="property_leasing_num">合同编号</label>
                        <input type="text" class="form-control" id="property_leasing_num"
                               value="${property_leasing_num }" name="property_leasing_num">
                    </div>

                    <div class="form-group">
                        <label for="state">物业费到账状态</label>
                        <select class="form-control" id="state" placeholder="物业费到账状态" name="state">
                            <option value="">--请选择--</option>
                            <c:forEach items="${stateType}" var="item">
                                <option value="${item.dict_id}"<c:if
                                        test="${item.dict_id == state}"> selected</c:if>>${item.dict_item_name }</option>
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
                    <security:authorize access="hasAnyRole('ROLE_ADMIN','ROLE_ESTATE')">
                        <a href="#" class="btn btn-primary" data-toggle="modal"
                           data-target="#newEstateDialog" onclick="clearEstate()">新建</a>
                    </security:authorize>

                    <a href="#" class="btn btn-success "
                       onclick="downloadEstateInfol()">导出本页</a>
                    <a href="#" class="btn btn-success "
                       onclick="downloadEstateInfolAll()">导出全部</a>
                </form>
            </div>
        </div>


        <div class="row">
            <div class="col-lg-12">
                <div class="panel panel-default">
                    <div class="panel-heading">物业费信息列表</div>
                    <!-- /.panel-heading -->
                    <table class="table table-bordered table-striped">
                        <thead>
                        <tr>
                            <th>序号</th>
                            <th>租凭合同编号</th>
                            <th>本次收取费用开始时间</th>
                            <th>本次收取费用结束时间</th>
                            <th>截止本月应收物业费(元)</th>
                            <th>实际已收物业费（元）</th>
                            <th>本次实收物业费（元)</th>
                            <th>收费时间</th>
                            <th>状态</th>
                            <th>审批状态</th>
                            <%--         <security:authorize access="hasAnyRole('ROLE_ADMIN','ROLE_CONTRACT','ROLE_ESTATE')">--%>
                            <th>操作</th>
                            <%--  </security:authorize>--%>
                        </tr>
                        </thead>
                        <tbody align="center">
                        <c:forEach items="${page.rows}" var="row">
                            <tr>
                                <td>${row.id}</td>
                                <td>${row.property_leasing_num}</td>
                                <td><fmt:formatDate value="${row.rent_start_time}" pattern="yyyy-MM-dd"/></td>
                                <td><fmt:formatDate value="${row.rent_end_time}" pattern="yyyy-MM-dd"/></td>
                                <td>${row.estate}</td>
                                <td>${row.estate_recivied}</td>
                                <td>${row.reality_estate}</td>
                                <td><fmt:formatDate value="${row.deadline}" pattern="yyyy-MM-dd"/></td>
                                <c:if test="${'9' eq row.state}">
                                    <td>缴清</td>
                                </c:if>
                                <c:if test="${'8' eq row.state}">
                                    <td> 未缴清</td>
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


                                    <security:authorize access="hasAnyRole('ROLE_ESTATE')">
                                        <c:if test="${row.status eq '28'}">
                                            <a href="#" class="btn btn-success btn-xs"
                                               onclick="submit(${row.id})">提交审核</a>
                                        </c:if>
                                        <c:if test="${row.status eq '28' || row.status eq  '31' }">
                                            <a href="#" class="btn btn-primary btn-xs" data-toggle="modal"
                                               data-target="#EditEstateDialog" onclick="editEstate(${row.id})">修改</a>
                                        </c:if>
                                        <a href="#" class="btn btn-success btn-xs" data-toggle="modal"
                                           data-target="#showDialog"
                                           onclick="comments(${row.id})">查看审核记录</a>
                                    </security:authorize>

                                    <!--  资产审核员 -->
                                    <security:authorize access="hasAnyRole('ROLE_ESTATECHECK')">
                                        <c:if test="${row.status eq '29'}">
                                            <a href="#" class="btn btn-success btn-xs" data-target="#commentDialog"
                                               data-toggle="modal"
                                               onclick="editComment(${row.id})">审核</a>
                                        </c:if>
                                        <a href="#" class="btn btn-success btn-xs" data-toggle="modal"
                                           data-target="#showDialog"
                                           onclick="comments(${row.id})">查看审核记录</a>
                                    </security:authorize>



                                        <security:authorize access="hasAnyRole('ROLE_ADMIN')">
                                            <a href="#" class="btn btn-danger btn-xs"
                                               onclick="deleteEstate(${row.id})">删除</a>

                                            <c:if test="${row.status eq '29'}">
                                                <a href="#" class="btn btn-success btn-xs" data-target="#commentDialog"
                                                   data-toggle="modal"
                                                   onclick="editComment(${row.id})">审核</a>
                                            </c:if>

                                            <a href="#" class="btn btn-success btn-xs" data-toggle="modal"
                                               data-target="#showDialog"
                                               onclick="comments(${row.id})">查看审核记录</a>

                                            <a href="#" class="btn btn-primary btn-xs" data-toggle="modal"
                                               data-target="#EditEstateDialog" onclick="editEstate(${row.id})">修改</a>
                                        </security:authorize>





                                        <%--<a href="#" class="btn btn-default btn-xs"     data-toggle="modal"  data-target="#showLeasingDialog"
                                           onclick="showDeposit(${row.id})">查看</a>--%>
                                    <a href="${pageContext.request.contextPath}/totalEstate/list.action?property_leasing_num=${row.property_leasing_num}"
                                       class="btn  btn-primary btn-xs" target="main">查看</a>
                                </td>


                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                    <div class="col-md-12 text-right">
                        <itcast:page url="${pageContext.request.contextPath }/assertEstate/list.action"/>
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
<div class="modal fade" id="commentDialog" tabindex="-1" role="dialog"
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
                <form class="form-horizontal" id="edit_comment_form">
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


<!-- 租金修改对话框 -->
<div class="modal fade" id="EditEstateDialog" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="myModalLabel2">更新物业费信息</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" id="edit_estate_form">

                    <input type="hidden" id="edit_id" name="id"/>
                    <div class="form-group">
                        <label for="edit_property_leasing_num" class="col-sm-2 control-label">租凭合同编号</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="edit_property_leasing_num" placeholder="租凭合同编号"
                                   name="property_leasing_num">
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
                        <label for="edit_estate_received" class="col-sm-2 control-label">实际已收物业费</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="edit_estate_received" placeholder="实际已收物业费（元）"
                                   name="estate_recivied">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="edit_estate" class="col-sm-2 control-label">截取当前应收物业费</label>
                        <div class="col-sm-10">
                            <input type="number" class="form-control" id="edit_estate" placeholder="截取当前应收物业费（元）"
                                   name="estate">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="edit_reality_estate" class="col-sm-2 control-label">本次收取物业费</label>
                        <div class="col-sm-10">
                            <input type="number" class="form-control" id="edit_reality_estate" placeholder="本次收取物业费（元）"
                                   name="reality_estate">
                        </div>
                    </div>


                    <div class="form-group date form_date">
                        <label for="edit_deadline" class="col-sm-2 control-label">到帐时间</label>
                        <div class="col-sm-10">
                            <input name="deadline" id="edit_deadline" type='text'
                                   class="form-control picket" placeholder="到帐时间"/>
                        </div>

                    </div>
                    <%--  <div class="form-group">
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
                <button type="button" class="btn btn-primary" onclick="updateEstate()">保存修改</button>
            </div>
        </div>
    </div>
</div>


<!-- 创建租金框 -->
<div class="modal fade" id="newEstateDialog" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="myModalLabel">添加物业信息</h4>
            </div>
            <div class="modal-body">

                <form class="form-horizontal" id="new_estate_form">
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
                                   class="form-control picket" placeholder="本次收取费用开始时间"/>
                        </div>
                    </div>


                    <div class="form-group date form_date">
                        <label for="new_rent_end_time" class="col-sm-2 control-label">本次收取费用开始时间</label>
                        <div class="col-sm-10">
                            <input name="rent_end_time" id="new_rent_end_time" type='text'
                                   class="form-control picket" placeholder="本次收取费用开始时间"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="new_estate_received" class="col-sm-2 control-label">实际已收物业费</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="new_estate_received" placeholder="实际已收物业费（元）"
                                   name="estate_recivied">
                        </div>
                    </div>


                    <div class="form-group">
                        <label for="new_estate" class="col-sm-2 control-label">截取当月应收物业费</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="new_estate" placeholder="截取当月应收物业费（元）"
                                   name="estate">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="new_reality_estate" class="col-sm-2 control-label">本次收取物业费</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="new_reality_estate" placeholder="本次收取物业费（元）"
                                   name="reality_estate">
                        </div>
                    </div>


                    <div class="form-group date form_date">
                        <label for="new_deadline" class="col-sm-2 control-label">到帐时间</label>
                        <div class="col-sm-10">
                            <input name="deadline" id="new_deadline" type='text'
                                   class="form-control picket" placeholder="到帐时间"/>
                        </div>
                    </div>

                    <%-- <div class="form-group">
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
                <button type="button" class="btn btn-primary" onclick="createEstate()">创建</button>
            </div>
        </div>
    </div>
</div>
<!-- /#wrapper -->

<!-- 显示对应批注信息的对话框 -->
<div class="modal fade" id="showDialog" tabindex="-1" role="dialog"
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


    function editEstate(id) {
        $.ajax({
            type: "get",
            url: "<%=basePath%>assertEstate/edit.action",
            data: {"id": id},
            success: function (data) {
                $("#edit_id").val(data.id);
                $("#edit_property_leasing_num").val(data.property_leasing_num);
                $("#edit_rent_start_time").val(data.rent_start_time);
                $("#edit_rent_end_time").val(data.rent_end_time);
                $("#edit_reality_estate").val(data.reality_estate);
                $("#edit_estate").val(data.estate);
                $("#edit_deadline").val(data.deadline);
                /*      $("#edit_state").val(data.state);*/
                $("#edit_estate_received").val(data.estate_recivied);
            }
        });
    }


    function updateEstate() {
        $.post("<%=basePath%>assertEstate/update.action", $("#edit_estate_form").serialize(), function (data) {
            if (data.code == 0) {
                alert(data.msg);
                window.location.reload();
            } else {
                alert(data.msg);
                window.location.reload();
            }
        });
    }

    function deleteEstate(id) {
        if (confirm('确实要删除租金信息吗?')) {
            $.post("<%=basePath%>assertEstate/delete.action", {"id": id}, function (data) {
                alert("租金信息删除成功！");
                window.location.reload();

            });
        }
    }

    function clearEstate() {
        $("#new_property_leasing_num").val("");
        $("#new_rent_start_time").val("");
        $("#new_rent_end_time").val("");
        $("#new_reality_estate").val("");
        $("#new_estate").val("");
        $("#edit_deadline").val("");
        /*        $("#new_state").val("");*/
        $("#new_estate_received").val("");
    }


    // 创建客户
    function createEstate() {
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

    //导出
    function downloadEstateInfol() {
        $("#mainForm").attr("action", "${pageContext.request.contextPath }/assertEstate/downloadEstateInfol.action").submit();
        $("#mainForm").attr("action", "${pageContext.request.contextPath }/assertEstate/list.action");
    }

    //导出
    function downloadEstateInfolAll() {
        $("#mainForm").attr("action", "${pageContext.request.contextPath }/assertEstate/downloadEstateInfolAll.action").submit();
        $("#mainForm").attr("action", "${pageContext.request.contextPath }/assertEstate/list.action");
    }


    function editComment(id) {

        $("#edit_comment_id").val(id);
        $("#edit_comment").val("");
        $("#edit_comment_state").val("");
    }

    function addComment() {

        $.post("<%=basePath%>assertEstate/changeState.action", $("#edit_comment_form").serialize(), function (data) {
            if (data.code == 0) {
                alert(data.msg);
                window.location.reload();
            } else {
                alert(data.msg);
                window.location.reload();

            }
        });
    }


    function submit(id) {
        if (confirm('确定要提交审核吗?')) {
            $.ajax({
                type: "get",
                url: "<%=basePath%>assertEstate/submit.action",
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
    function comments(id) {
        $.ajax({
            type: "get",
            url: "<%=basePath%>assertEstate/getComments.action",
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
