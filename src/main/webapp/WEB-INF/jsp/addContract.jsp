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

        <h1 class="page-header">资产汇总管理</h1>


        <form class="form-horizontal" id="edit_PropertyLeasing_form">
            <div class="form-group">
                <label for="edit_property_leasing_num" class="col-sm-1 control-label">
                    租赁合同编号
                </label>
                <div class="col-sm-5">
                    <input disabled type="text" class="form-control" id="edit_property_leasing_num" placeholder="租赁合同编号"
                           name="property_leasing_num"/>
                </div>
            </div>


            <div class="form-group">
                <label for="edit_community_name" class="col-sm-1 control-label">小区名称</label>
                <div class="col-sm-5">
                    <select class="form-control" id="edit_community_name" placeholder="小区名称"
                            name="community_name">
                        <option value="">--请选择--</option>
                        <c:forEach items="${communityNameList}" var="item">
                            <option value="${item}">${item}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>


            <div class="form-group">
                <label for="edit_building_num" class="col-sm-1 control-label">栋号</label>
                <div class="col-sm-5">

                    <select class="form-control" id="edit_building_num" placeholder="栋号" name="building_num">
                        <option value="">--请选择--</option>
                    </select>
                </div>
            </div>


            <div class="form-group">
                <label for="edit_rentalLocation" class="col-sm-1 control-label">租聘位置</label>
                <div class="col-sm-5">
                    <select class="form-control selectpicker" multiple data-live-search=""
                            id="edit_rentalLocation"
                            placeholder="租聘位置" name="rentalLocation">
                    </select>
                </div>
            </div>

            <div class="form-group">
                <label for="edit_tenant" class="col-sm-1 control-label">承租人</label>
                <div class="col-sm-5">
                    <input disabled type="text" class="form-control" id="edit_tenant" placeholder="承租人"
                           name="tenant"/>
                </div>
            </div>


            <div class="form-group">
                <label for="edit_rental_area" class="col-sm-1 control-label">承租面积</label>
                <div class="col-sm-5">
                    <input disabled type="text" class="form-control" id="edit_rental_area" placeholder="承租面积"
                           name="rental_area"/>
                </div>
            </div>


            <div class="form-group date form_date">
                <label for="edit_sign_in_time" class="col-sm-1 control-label">合同签订时间</label>
                <div class="col-sm-5">
                    <input disabled name="sign_in_time" id="edit_sign_in_time" type='text'
                           class="form-control picket" placeholder="合同签订时间"/>
                </div>
            </div>


            <div class="form-group">
                <label for="edit_rent_charge_standard" class="col-sm-1 control-label">租金收费标准</label>
                <div class="col-sm-5">
                    <input disabled  type="text" class="form-control"  id="edit_rent_charge_standard" placeholder="租金收费标准"
                           name="rent_charge_standard"/>
                </div>
            </div>

            <div class="form-group">
                <label for="edit_monthly_rental" class="col-sm-1 control-label">月租</label>
                <div class="col-sm-5">
                    <input disabled type="text" class="form-control" id="edit_monthly_rental" placeholder="月租"
                           name="monthly_rental"/>
                </div>
            </div>

            <div class="form-group">
                <label for="edit_rent_free_period" class="col-sm-1 control-label">免租期</label>
                <div class="col-sm-5">
                    <input disabled type="text" class="form-control" id="edit_rent_free_period" placeholder="免租期(月)"
                           name="rent_free_period"/>
                </div>
            </div>


            <div class="form-group">
                <label for="edit_rent_period" class="col-sm-1 control-label">合同期</label>
                <div class="col-sm-5">
                    <input disabled type="text" class="form-control" id="edit_rent_period" placeholder="合同期(月)"
                           name="rent_period"/>
                </div>
            </div>


            <div class="form-group">
                <label for="edit_collect_rent_way" class="col-sm-1 control-label">租金方式</label>
                <div class="col-sm-5">
                    <select class="form-control" id="edit_collect_rent_way" placeholder="租金方式"
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
                <label for="edit_collect_rent_time" class="col-sm-1 control-label">租金缴纳时间</label>
                <div class="col-sm-5">
                    <input disabled type="text" class="form-control" id="edit_collect_rent_time" placeholder="租金缴纳时间"
                           name="collect_rent_time"/>
                </div>
            </div>


            <div class="form-group">
                <label for="edit_estate_charge_standard" class="col-sm-1 control-label">物业收费标准</label>
                <div class="col-sm-5">
                    <input disabled type="text" class="form-control" id="edit_estate_charge_standard"
                           placeholder="物业收费标准(元/平方米)"
                           name="estate_charge_standard"/>
                </div>
            </div>


            <div class="form-group">
                <label for="edit_estate_charge_standard" class="col-sm-1 control-label">月物业费</label>
                <div class="col-sm-5">
                    <input disabled type="text" class="form-control" id="edit_estate_charge_month" placeholder="月物业费"
                           name="estate_charge_month"/>
                </div>
            </div>


            <div class="form-group">
                <label for="edit_deposit" class="col-sm-1 control-label">保证金</label>
                <div class="col-sm-5">
                    <input disabled type="text" class="form-control" id="edit_deposit" placeholder="保证金（元）"
                           name="deposit"/>
                </div>
            </div>


            <div class="form-group date form_date">
                <label for="edit_deposit_time" class="col-sm-1 control-label">保证金到账时间</label>
                <div class="col-sm-5">
                    <input disabled name="deposit_time" id="edit_deposit_time" type='text'
                           class="form-control picket" placeholder="保证金到账时间"/>
                </div>
            </div>

            <div class="form-group">
                <label for="edit_water_rate" class="col-sm-1 control-label">水费</label>
                <div class="col-sm-5">
                    <input disabled type="text" class="form-control" id="edit_water_rate" placeholder="水费(元/吨)"
                           name="water_rate"/>
                </div>
            </div>

            <div class="form-group">
                <label for="edit_power_rate" class="col-sm-1 control-label">电费</label>
                <div class="col-sm-5">
                    <input disabled type="text" class="form-control" id="edit_power_rate" placeholder="电费(元/度)）"
                           name="power_rate"/>
                </div>
            </div>


            <div class="form-group">
                <label for="edit_collect_rate_way" class="col-sm-1 control-label">水电方式</label>
                <div class="col-sm-5">
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
                <label for="edit_rent_start_time" class="col-sm-1 control-label">租赁起始时间</label>
                <div class="col-sm-5">
                    <input disabled name="rent_start_time" id="edit_rent_start_time" type='text'
                           class="form-control picket" placeholder="租赁起始时间"/>
                </div>
            </div>


            <div class="form-group date form_date">
                <label for="edit_rent_end_time" class="col-sm-1 control-label">租赁结束时间</label>
                <div class="col-sm-5">
                    <input disabled name="rent_end_time" id="edit_rent_end_time" type='text'
                           class="form-control picket" placeholder="租赁结束时间"/>
                </div>
            </div>


            <div class="form-group">
                <label for="edit_property_leasing_type" class="col-sm-1 control-label">返迁安置</label>
                <div class="col-sm-5">
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
                <label for="edit_property_leasing_state" class="col-sm-1 control-label">合同状态</label>
                <div class="col-sm-5">
                    <select class="form-control" id="edit_property_leasing_state" placeholder="合同的状态"
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
                <label for="edit_remark" class="col-sm-1 control-label">备注</label>
                <div class="col-sm-5">
                    <input disabled type="text" class="form-control" id="edit_remark" placeholder="备注"
                           name="remark"/>
                </div>
            </div>

        </form>



        <!-- /.row -->
    </div>


    <!-- /#page-wrapper -->


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
<%--<script type="text/javascript" src="${pageContext.request.contextPath}/bootstramp/jquery/jquery-1.8.3.min.js"></script>--%>
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

    //编辑合同信息
    function editPropertyLeasing(id) {
        $.ajax({
            type: "get",
            url: "<%=basePath%>propertyLeasing/edit.action",
            data: {"id": id},
            success: function (data) {
               /* $("#edit_id").val(id);*/
                $("#edit_property_leasing_num").val(data.property_leasing_num);
                $("#edit_community_name").val(data.community_name);
                var f = document.getElementById("edit_building_num");
                var childs = f.childNodes;
                for (var i = childs.length - 1; i >= 0; i--) {
                    f.removeChild(childs[i]);
                }
                document.getElementById("edit_building_num").options.add(edit Option("请选择", ""));
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

                $.ajax({
                    type: "get",
                    url: "<%=basePath%>assertInfol/getAllBuildNum.action",
                    data: {"community_name": data.community_name},
                    success: function (data) {
                        if (data.code == 0) {
                            var building_nums = data.data;
                            for (var i = 0; i < building_nums.length; i++) {
                                if (building_num == building_nums[i]) {
                                    var option = edit Option(building_nums[i], building_nums[i]);
                                    document.getElementById("edit_building_num").options.add(option);
                                    option.selected = true;
                                } else {
                                    document.getElementById("edit_building_num").options.add(edit Option(building_nums[i], building_nums[i]));
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
                                            var option = edit Option(rentalLocations[i], rentalLocations[i]);
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
                                        /* $('#edit_rentalLocation').selectpicker('refresh');
                                        $('#edit_rentalLocation').selectpicker('render');*/
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


                /* document.getElementById("edit_rentalLocation").options.add(edit Option("--请选择--", ""));*/
                /* var building_num = $("#edit_building_num").find("option:selected").text();
                var community_name = $("#edit_community_name").val();
                var rentalLocation = $("#edit_rentalLocation").find("option:selected").text();*/


                /* $("#edit_rentalLocation").prepend(option2);
                $("#edit_rentalLocation").selectpicker('refresh');*/

            }
        });
    }

</script>

</body>

</html>
