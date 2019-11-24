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
    <link href="<%=basePath%>css/bootstrap-datetimepicker.css" rel="stylesheet"/>
    <%--<link href="<%=basePath%>/bootstramp/bootstrap/css/bootstrap.min.css" rel="stylesheet">--%>
    <script type="text/javascript" src="<%=basePath%>js/jquery.min.js"></script>
    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>

<body>


<div id="wrapper">

    <div id="page-wrapper" >



        <div class="row" style="padding-top: 40px; padding-left: 40px">

            <form class="form-horizontal" id="edit_PropertyLeasing_form">
                <div class="form-group">
                    <label for="edit_property_leasing_num" class="col-sm-1 control-label">
                        租赁合同编号
                    </label>
                    <div class="col-sm-5">
                        <input disabled type="text" class="form-control" id="edit_property_leasing_num"
                               placeholder="租赁合同编号"
                               name="property_leasing_num" value="${propertyLeasing.property_leasing_num}"/>
                    </div>
                </div>


                <div class="form-group">
                    <label for="edit_community_name" class="col-sm-1 control-label">小区名称</label>
                    <div class="col-sm-5">
                        <input disabled type="text" class="form-control" id="edit_community_name" placeholder="小区名称"
                               name="community_name" value="${propertyLeasing.community_name}"/>
                    </div>
                </div>


                <div class="form-group">
                    <label for="edit_building_num" class="col-sm-1 control-label">栋号</label>
                    <div class="col-sm-5">

                        <input disabled type="text" class="form-control" id="edit_building_num" placeholder="栋号"
                               name="building_num" value="${propertyLeasing.building_num}"/>
                    </div>
                </div>


                <div class="form-group">
                    <label for="edit_rentalLocation" class="col-sm-1 control-label">租聘位置</label>
                    <div class="col-sm-5">

                        <input disabled type="text" class="form-control" id="edit_rentalLocation" placeholder="栋号"
                               name="rentalLocation" value="${propertyLeasing.rentalLocation}"/>
                        <%-- <select class="form-control selectpicker" multiple data-live-search=""
                                 id="edit_rentalLocation"
                                 placeholder="租聘位置" name="rentalLocation" value="${propertyLeasing.rentalLocation}">
                         </select>--%>
                    </div>
                </div>


                <div class="form-group">
                    <label for="edit_tenant" class="col-sm-1 control-label">承租人</label>
                    <div class="col-sm-5">
                        <input disabled type="text" class="form-control" id="edit_tenant" placeholder="承租人"
                               name="tenant" value="${propertyLeasing.tenant}"/>
                    </div>
                </div>


                <div class="form-group">
                    <label for="edit_idCard" class="col-sm-1 control-label">身份证号</label>
                    <div class="col-sm-5">
                        <input type="text" disabled class="form-control" id="edit_idCard" placeholder="身份证号"
                               name="idCard" value="${propertyLeasing.idCard}"/>
                    </div>
                </div>

                <div class="form-group">
                    <label for="edit_phoneNumber" class="col-sm-1 control-label">手机号码</label>
                    <div class="col-sm-5">
                        <input type="text" disabled  class="form-control" id="edit_phoneNumber" placeholder="手机号码"
                               name="phoneNumber" value="${propertyLeasing.phoneNumber}"/>
                    </div>
                </div>


                <div class="form-group">
                    <label for="edit_rental_area" class="col-sm-1 control-label">承租面积</label>
                    <div class="col-sm-5">
                        <input disabled type="text" class="form-control" id="edit_rental_area" placeholder="承租面积"
                               name="rental_area" value="${propertyLeasing.rental_area}"/>
                    </div>
                </div>


                <div class="form-group date form_date">
                    <label for="edit_sign_in_time" class="col-sm-1 control-label">合同签订时间</label>
                    <div class="col-sm-5">
                        <input disabled name="sign_in_time" id="edit_sign_in_time" type='text'
                               class="form-control picket" placeholder="合同签订时间"
                               value="<fmt:formatDate pattern="yyyy-MM-dd"  value="${propertyLeasing.sign_in_time}"/>"/>
                    </div>
                </div>


                <div class="form-group">
                    <label for="edit_rent_charge_standard" class="col-sm-1 control-label">租金收费标准</label>
                    <div class="col-sm-5">
                        <input disabled type="text" class="form-control" id="edit_rent_charge_standard"
                               placeholder="租金收费标准"
                               name="rent_charge_standard" value="${propertyLeasing.rent_charge_standard}"/>
                    </div>
                </div>

                <div class="form-group">
                    <label for="edit_monthly_rental" class="col-sm-1 control-label">月租</label>
                    <div class="col-sm-5">
                        <textarea  disabled class="form-control" rows="3"  id="edit_monthly_rental"  placeholder="月租" >${monthRental}</textarea>
<%--                        <input disabled type="text" class="form-control" id="edit_monthly_rental" placeholder="月租"
                               name="monthly_rental" />--%>
                    </div>
                </div>

                <div class="form-group">
                    <label for="edit_rent_free_period" class="col-sm-1 control-label">免租期</label>
                    <div class="col-sm-5">
                        <input disabled type="text" class="form-control" id="edit_rent_free_period" placeholder="免租期(月)"
                               name="rent_free_period" value="${propertyLeasing.rent_free_period}"/>
                    </div>
                </div>


                <div class="form-group">
                    <label for="edit_rent_period" class="col-sm-1 control-label">合同期</label>
                    <div class="col-sm-5">
                        <input disabled type="text" class="form-control" id="edit_rent_period" placeholder="合同期(月)"
                               name="rent_period" value="${propertyLeasing.rent_period}"/>
                    </div>
                </div>


                <div class="form-group">
                    <label for="edit_collect_rent_way" class="col-sm-1 control-label">租金缴纳方式</label>
                    <div  class="col-sm-5">
                    <select class="form-control" id="edit_collect_rent_way" placeholder="租金缴纳方式"
                            name="collect_rent_way">
                        <option value="">--请选择--</option>
                        <c:forEach items="${rentalWayType}" var="item">
                            <option value="${item.dict_id}"<c:if
                                    test="${item.dict_id == propertyLeasing.collect_rent_way}"> selected</c:if>>${item.dict_item_name }</option>
                        </c:forEach>
                    </select>
                    </div>
                </div>

                <div class="form-group">
                    <label for="edit_collect_rent_time" class="col-sm-1 control-label">租金缴纳时间</label>
                    <div class="col-sm-5">
                        <input disabled type="text" class="form-control" id="edit_collect_rent_time"
                               placeholder="租金缴纳时间"
                               name="collect_rent_time" value="${propertyLeasing.collect_rent_time}"/>
                    </div>
                </div>


                <div class="form-group">
                    <label for="edit_estate_charge_standard" class="col-sm-1 control-label">物业收费标准</label>
                    <div class="col-sm-5">
                        <input disabled type="text" class="form-control" id="edit_estate_charge_standard"
                               placeholder="物业收费标准(元/平方米)"
                               name="estate_charge_standard" value="${propertyLeasing.estate_charge_standard}"/>
                    </div>
                </div>


                <div class="form-group">
                    <label for="edit_estate_charge_standard" class="col-sm-1 control-label">月物业费</label>
                    <div class="col-sm-5">
                        <input disabled type="text" class="form-control" id="edit_estate_charge_month"
                               placeholder="月物业费"
                               name="estate_charge_month" value="${propertyLeasing.estate_charge_month}"/>
                    </div>
                </div>


                <div class="form-group">
                    <label for="edit_deposit" class="col-sm-1 control-label">保证金</label>
                    <div class="col-sm-5">
                        <input disabled type="text" class="form-control" id="edit_deposit" placeholder="保证金（元）"
                               name="deposit" value="${propertyLeasing.deposit}"/>
                    </div>
                </div>


                <div class="form-group date form_date">
                    <label for="edit_deposit_time" class="col-sm-1 control-label">保证金到账时间</label>
                    <div class="col-sm-5">
                        <input disabled name="deposit_time" id="edit_deposit_time" type='text'
                               class="form-control picket" placeholder="保证金到账时间"
                               value="<fmt:formatDate pattern="yyyy-MM-dd"  value="${propertyLeasing.deposit_time}"/>"/>
                    </div>
                </div>

                <div class="form-group">
                    <label for="edit_water_rate" class="col-sm-1 control-label">水费</label>
                    <div class="col-sm-5">
                        <input disabled type="text" class="form-control" id="edit_water_rate" placeholder="水费(元/吨)"
                               name="water_rate" value="${propertyLeasing.water_rate}"/>
                    </div>
                </div>

                <div class="form-group">
                    <label for="edit_power_rate" class="col-sm-1 control-label">电费</label>
                    <div class="col-sm-5">
                        <input disabled type="text" class="form-control" id="edit_power_rate" placeholder="电费(元/度)）"
                               name="power_rate" value="${propertyLeasing.power_rate}"/>
                    </div>
                </div>


                <div class="form-group" >
                    <label for="edit_collect_rate_way" class="col-sm-1 control-label">水电费缴费方式</label>
                    <div  class="col-sm-5">
                    <select class="form-control" id="edit_collect_rate_way" placeholder="水电费缴费方式"
                            name="collect_rate_way">
                        <option value="">--请选择--</option>
                        <c:forEach items="${waterAndEletricWayType}" var="item">
                            <option value="${item.dict_id}"<c:if
                                    test="${item.dict_id == propertyLeasing.collect_rate_way}"> selected</c:if>>${item.dict_item_name }</option>
                        </c:forEach>
                    </select>
                    </div>
                </div>




                <div class="form-group date form_date">
                    <label for="edit_rent_start_time" class="col-sm-1 control-label">租赁起始时间</label>
                    <div class="col-sm-5">
                        <input disabled name="rent_start_time" id="edit_rent_start_time" type='text'
                               class="form-control picket" placeholder="租赁起始时间"
                               value="<fmt:formatDate pattern="yyyy-MM-dd"  value="${propertyLeasing.rent_start_time}"/>"/>
                    </div>
                </div>


                <div class="form-group date form_date">
                    <label for="edit_rent_end_time" class="col-sm-1 control-label">租赁结束时间</label>
                    <div class="col-sm-5">
                        <input disabled name="rent_end_time" id="edit_rent_end_time" type='text'
                               class="form-control picket" placeholder="租赁结束时间"
                              value="<fmt:formatDate pattern="yyyy-MM-dd"  value="${propertyLeasing.rent_end_time}"/>"/>
                    </div>
                </div>


             <%--   <div class="form-group">
                    <label for="edit_property_leasing_type" class="col-sm-1 control-label">返迁安置</label>
                    <div class="col-sm-5">
                        <input disabled name="property_leasing_type" id="edit_property_leasing_type" type='text'
                               class="form-control picket" placeholder="返迁安置"
                      value="${propertyLeasing.property_leasing_type}" />
                    </div>
                </div>--%>

                <div class="form-group">
                    <label for="edit_property_leasing_type" class="col-sm-1 control-label">返迁安置</label>
                    <div  class="col-sm-5">
                    <select class="form-control" id="edit_property_leasing_type" placeholder="返迁安置"
                            name="property_leasing_type">
                        <option value="">--请选择--</option>
                        <c:forEach items="${contractType}" var="item">
                            <option value="${item.dict_id}"<c:if
                                    test="${item.dict_id == propertyLeasing.property_leasing_type}"> selected</c:if>>${item.dict_item_name }</option>
                        </c:forEach>
                    </select>
                    </div>
                </div>




                <div class="form-group">
                    <label for="property_leasing_state" class="col-sm-1 control-label">合同状态</label>

                    <div  class="col-sm-5">
                    <select class="form-control" id="property_leasing_state" placeholder="合同状态"
                            name="property_leasing_state">
                        <option value="">--请选择--</option>
                        <c:forEach items="${contractStateType}" var="item">
                            <option value="${item.dict_id}"<c:if
                                    test="${item.dict_id == propertyLeasing.property_leasing_state}"> selected</c:if>>${item.dict_item_name }</option>
                        </c:forEach>
                    </select>
                    </div>
                </div>




                <div class="form-group">
                    <label for="edit_remark" class="col-sm-1 control-label">备注</label>
                    <div class="col-sm-5">
                        <input disabled type="text" class="form-control" id="edit_remark" placeholder="备注"
                               name="remark" value="${propertyLeasing.remark}"/>
                    </div>
                </div>

            </form>
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


<script type="text/javascript" src="${pageContext.request.contextPath}/bootstramp/bootstrap/js/bootstrap.min.js"
        charset="UTF-8"></script>
<%--<script type="text/javascript" src="${propertyLeasing.pageContext.request.contextPath}/bootstramp/jquery/jquery-1.8.3.min.js"></script>--%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap-datetimepicker.js"
        charset="UTF-8"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/locales/bootstrap-datetimepicker.zh-CN.js"
        charset="UTF-8"></script>

<script type="text/javascript">
    /*  //选择年月的
      $('#year_months').datetimepicker({
          format: 'yyyymm',
          weekStart: 1,
          autoclose: true,
          startView: 3,
          minView: 3,
          forceParse: false,
          language: 'zh-CN'
      });

      //选择年月的
      $('#years').datetimepicker({
          format: 'yyyy',
          weekStart: 1,
          autoclose: true,
          startView: 'decade',
          minView: 'decade',
          maxView:'decade',
          forceParse: false,
          language: 'zh-CN'
      });*/


</script>

</body>

</html>
