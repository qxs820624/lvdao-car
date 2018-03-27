<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <title>系统通知</title>
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
                <span>系统通知</span>
            </header>
            <div class="info_system_cons">
                <ul>
                    <#if dictList??>
                    	<#list dictList as dict>
		                    <li>
		                        <div class="info_time"><#if dict.createTime??>${dict.createTime?string('yyyy/MM/dd hh:mm')}</#if></div>
		                        <div class="money_main">${dict.dictValue!''}</div>
		                    </li>
	                    </#list>
                    </#if>
                </ul>
            </div>
        </div>
    </body>
</html>