<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=8">
    <title>左边导航</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/reset.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/common.css"/>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
    <!--框架高度设置-->
    <script type="text/javascript">
        $(function () {
            $('.sidenav li').click(function () {
                $(this).siblings('li').removeClass('now');
                $(this).addClass('now');
            });

            $('.erji li').click(function () {
                $(this).siblings('li').removeClass('now_li');
                $(this).addClass('now_li');
            });

            var main_h = $(window).height();
            $('.sidenav').css('height', main_h + 'px');
        })
    </script>
    <!--框架高度设置-->
</head>

<body>
<div id="left_ctn">
    <ul class="sidenav">

        <li class="now">
            <div class="nav_m">
                <span><a href="${pageContext.request.contextPath }/propertyLeasing/queryList.action" target="main">主页面</a></span>
            </div>
        </li>

        <li>
            <div class="nav_m">
                <span><a>资产信息管理</a></span>
                <i>&nbsp;</i>
            </div>
            <ul class="erji">
                <li>
                    <span><a href="${pageContext.request.contextPath}/assertInfol/list.action" target="main">资产信息管理</a></span>
                </li>
            </ul>
        </li>
        <li>
            <div class="nav_m">
                <span><a>资产合同管理</a></span>
                <i>&nbsp;</i>
            </div>
            <ul class="erji">
                <li>
                    <span><a href="${pageContext.request.contextPath}/propertyLeasing/list.action"
                             target="main">资产合同管理</a></span>
                </li>
            </ul>
        </li>

        <li>
            <div class="nav_m">
                <span><a>资产收入管理</a></span>
                <i>&nbsp;</i>
            </div>
            <ul class="erji">

                <li>
                    <span><a href="${pageContext.request.contextPath}/assertDeposit/list.action" target="main">押金收入管理</a></span>
                </li>

                <li>
                    <span><a href="${pageContext.request.contextPath}/assertRental/list.action" target="main">租金收入管理</a></span>
                </li>

                <li>
                    <span><a href="${pageContext.request.contextPath}/assertEstate/list.action" target="main">物业费收入管理</a></span>
                </li>

                <li>
                    <span><a href="${pageContext.request.contextPath}/assertWaterRent/list.action" target="main">水费收入管理</a></span>
                </li>

                <li>
                    <span><a href="${pageContext.request.contextPath}/assertPowerRent/list.action" target="main">电费收入管理</a></span>
                </li>
                <%-- <li>
                     <span><a href="log/loginLog.do" target="main">资产合同管理</a></span>
                 </li>
                 <li>
                     <span><a href="log/systemLog.do" target="main">系统日志</a></span>
                 </li>--%>
            </ul>
        </li>

        <li >
            <div class="nav_m">
                <span><a>水电信息录入管理</a></span>
                <i>&nbsp;</i>
            </div>
            <ul class="erji">
                <li>
                    <span><a href="${pageContext.request.contextPath}/assertWater/list.action" target="main">用水信息管理</a></span>
                </li>
                <li>
                    <span><a href="${pageContext.request.contextPath}/assertPower/list.action" target="main">用电信息管理</a></span>
                </li>
            </ul>
        </li>


        <li >
            <div class="nav_m">
                <span><a>租金物业费信息管理</a></span>
                <i>&nbsp;</i>
            </div>
            <ul class="erji">
                <li>
                    <span><a href="${pageContext.request.contextPath}/totalRental/list.action" target="main">租金明细</a></span>
                </li>
                <li>
                    <span><a href="${pageContext.request.contextPath}/totalRental/listCommunity.action" target="main">租金统计</a></span>
                </li>
                <li>
                    <span><a href="${pageContext.request.contextPath}/totalEstate/list.action" target="main">物业费明细</a></span>
                </li>
                <li>
                    <span><a href="${pageContext.request.contextPath}/totalEstate/listCommunity.action" target="main">物业费统计</a></span>
                </li>
                <li>
                    <span><a href="${pageContext.request.contextPath}/assertInfol/listCommunity.action" target="main">资产统计</a></span>
                </li>
            </ul>
        </li>


        <li >
            <div class="nav_m">
                <span><a>系统管理</a></span>
                <i>&nbsp;</i>
            </div>
            <ul class="erji">
                <li>
                    <span><a href="${pageContext.request.contextPath}/userInfo/list.action" target="main">用户管理</a></span>
                </li>
                <%--<li>
                    <span><a href="${pageContext.request.contextPath}/totalRental/listCommunity.action" target="main">租金统计</a></span>
                </li>
                <li>
                    <span><a href="${pageContext.request.contextPath}/totalEstate/list.action" target="main">物业费明细</a></span>
                </li>
                <li>
                    <span><a href="${pageContext.request.contextPath}/totalEstate/listCommunity.action" target="main">物业费统计</a></span>
                </li>
                <li>
                    <span><a href="${pageContext.request.contextPath}/assertInfol/listCommunity.action" target="main">资产统计</a></span>
                </li>--%>
            </ul>
        </li>
        <li >
            <div class="nav_m">
                <span><a href="${pageContext.request.contextPath }/logout.action" target="_top">退出系统</a></span>
            </div>
        </li>
    </ul>
</div>
</body>
</html>
