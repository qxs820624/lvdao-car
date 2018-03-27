<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <title>收款通知</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
        <meta name="format-detection" content="telephone=no">
    	<meta name="apple-mobile-web-app-capable" content="yes">
    	<script type="text/javascript" src="../../resources/js/jquery-2.1.1.min.js"></script>
        <link rel="stylesheet" type="text/css" href="../../resources/css/carRental.css">
    </head>
    <body>
    	<div class="container">
            <header>
                <a class="return_arr" href="/info/infoPage.do"></a>
                <span>收款通知</span>
            </header>
            <div class="info_money_cons">
                <ul>
                	<#if userAccountMsgList??>
                		<#list userAccountMsgList as userAccountMsg>
		                    <li>
		                        <div class="info_time"><#if userAccountMsg.createTime??>${userAccountMsg.createTime?string('yyyy/MM/dd hh:mm')}</#if></div>
		                        <div class="money_main">您的账户进账<span>¥${userAccountMsg.amount}</span>，已存入账户余额，可点击“<a href="/user/personal.do">${userAccountMsg.accountTypeName!''}</a>”进行查询！</div>
		                    </li>
	                    </#list>
                    </#if>
                </ul>
            </div>
        </div>
    </body>
</html>