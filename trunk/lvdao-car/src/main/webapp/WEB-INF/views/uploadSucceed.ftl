<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <title>提交成功</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
        <meta name="format-detection" content="telephone=no">
    	<meta name="apple-mobile-web-app-capable" content="yes">
    	<script type="text/javascript" src="../../resources/js/jquery-2.1.1.min.js"></script>
        <link rel="stylesheet" type="text/css" href="../../resources/css/carRental.css">
        <link rel="stylesheet" type="text/css" href="../../resources/layui/css/layui.css">
    </head>
    <body>
    	<div class="container">
            <div class="succeed_cons">
                <div class="close_btn">
                    <img src="../../resources/images/close_btn.png" alt="">
                </div>
                <div class="succeed_main">
                    <div class="succeed_img">
                        <img src="../../resources/images/succeed_icon.png" alt="">
                    </div>
                    <span>提交成功！</span>
                    <p>待平台审核通过后，可以参加返利～</p>
                    <button onclick="javascript:window.location.href='/index/index.do?userName=<#if user??>${user.userName}</#if>'">返回首页</button>
                </div>
            </div>
        </div>
    </body>
</html>