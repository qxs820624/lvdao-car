<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <title>汽车信息</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
        <meta name="format-detection" content="telephone=no">
    	<meta name="apple-mobile-web-app-capable" content="yes">
    	<script type="text/javascript" src="../../resources/js/jquery-2.1.1.min.js"></script>
        <link rel="stylesheet" type="text/css" href="../../resources/css/carRental.css">
    </head>
    <body>
    	<div class="container">
            <header>
                <a class="return_arr" href="/user/personal.do"></a>
                <span>汽车信息</span>
            </header>
            <div class="car_info_cons">
                <div class="car_num">投资汽车数：${carSize!'0'}辆</div>
                <ul>
                    <#if userOrderList??>
                    	<#list userOrderList as userOrder>
                    	<a href="/vehicle/viewCarVoucher.do?orderId=${userOrder.orderId}">
	                    <li class="no_border">
	                        <div class="car_type outer">
	                            <span>${userOrder.orderMoney}元新能源汽车</span>
	                            <i><#if userOrder.paidStatus == 1>已支付<#else>待审核</#if></i>
	                        </div>
	                        <p>当前位置：宝源路1234号</p>
	                    </li></a>
	                    </#list>
                    </#if>
                </ul>
            </div>
        </div>
    </body>
</html>