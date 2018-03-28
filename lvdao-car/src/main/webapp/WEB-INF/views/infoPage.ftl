<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <title>消息</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
        <meta name="format-detection" content="telephone=no">
    	<meta name="apple-mobile-web-app-capable" content="yes">
    	<script type="text/javascript" src="../../resources/js/jquery-2.1.1.min.js"></script>
        <link rel="stylesheet" type="text/css" href="../../resources/css/carRental.css">
    </head>
    <body>
    	<div class="container">
            <header>
                <a class="return_arr" href="/index/index.do?userName=<#if user??>${user.userName}</#if>"></a>
                <span>消息</span>
            </header>
            <div class="info_cons">
                <ul>
                    <li class="outer" onclick="javascript:window.location.href='/info/infoSystem.do'">
                        <img src="../../resources/images/info_system.png" alt="">
                        <div class="info_center">
                            <span>系统通知</span>
                            <p><#if systemMessageVO??>${systemMessageVO.dictValue!''}</#if></p>
                        </div>
                        <div class="info_right">
                            <span>${systemMessageSize!'0'}条</span>
                            <#if systemMessageTime??><i>${systemMessageTime!''}</i></#if>
                        </div>
                    </li>
                    <li class="outer" onclick="javascript:window.location.href='/info/infoMoney.do'">
                        <img src="../../resources/images/info_money.png" alt="">
                        <div class="info_center">
                            <span>收款通知</span>
                            <p><#if returnMessageVO??>您的账户进账${returnMessageVO.amount!'0'}，已存入${returnMessageVO.accountTypeName!''}账户余额！</#if></p>
                        </div>
                        <div class="info_right">
                            <span>${returnMessageSize!'0'}条</span>
                            <#if returnMessageTime??><i>${returnMessageTime!''}</i></#if>
                        </div>
                    </li>
                    <li class="outer">
                        <img src="../../resources/images/info_service.png" alt="">
                        <div class="info_center">
                            <span>客服中心</span>
                            <p>暂无聊天信息</p>
                        </div>
                        <div class="info_right">
                            <span></span>
                            <i></i>
                        </div>
                    </li>
                </ul>
            </div>
        </div>
    </body>
</html>