<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <title>推荐人</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
        <meta name="format-detection" content="telephone=no">
    	<meta name="apple-mobile-web-app-capable" content="yes">
    	<script type="text/javascript" src="../resources/js/jquery-2.1.1.min.js"></script>
        <link rel="stylesheet" type="text/css" href="../../resources/css/carRental.css">
    </head>
    <body>
    	<div class="container">
            <header>
                <a class="return_arr" href="/user/personal.do"></a>
                <span>推荐人</span>
            </header>
            <div class="reference_cons">
                <ul class="outer">
                    <li class="reference_current">我的推荐人<i></i></li>
                    <li>我推荐的人<i></i></li>
                </ul>
                <ol>
                    <li>
                        <div class="reference_info outer">
                            <p>姓名</p>
                            <span><#if userParentName??>${userParentName}</span><#else>？？</#if></span>
                        </div>
                        <div class="reference_info outer">
                            <p>手机号</p>
                            <span><#if myRecommendUser??>${myRecommendUser}</span><#else>？？</#if></span>
                        </div>
                        <div class="erweima" style="width:60%;position:fixed;bottom:20%;left:20%;padding:20px;box-sizing:border-box; -webkit-box-sizing:border-box; -moz-box-sizing:border-box;">
                            <!-- 二维码 -->
                            <img src="/user/createQRCode.do?codeUrl=${createQRCode}" alt="">
                            <p style="text-align:center;margin-top:10px;">我的推荐二维码</p>
                        </div>
                    </li>
                    <li style="display:none;">
                        <div class="table">
                            <table border="0" cellspacing="0" cellpadding="0">
                                <thead>
                                    <tr>
                                        <th>序号</th>
                                        <th>姓名</th>
                                        <th>手机号</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <#if userList??>
                                    	<#list userList as user>
		                                    <tr>
		                                        <td>${user_index+1}</td>
		                                        <td>${user.userRealName!''}</td>
		                                        <td>${user.userName!''}</td>
		                                    </tr>
                                    	</#list>
                                    </#if>
                                </tbody>
                            </table>
                        </div>
                    </li>
                </ol>
            </div>
        </div>
        <script type="text/javascript">
            $(".reference_cons>ul>li").click(function(event) {
                $(this).addClass('reference_current').siblings().removeClass('reference_current');
                $(".reference_cons>ol>li").eq($(this).index()).show().siblings().hide();
            });
        </script>
    </body>
</html>
