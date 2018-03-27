<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <title>查看付款凭证</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
        <meta name="format-detection" content="telephone=no">
    	<meta name="apple-mobile-web-app-capable" content="yes">
    	<script type="text/javascript" src="../../resources/js/jquery-2.1.1.min.js"></script>
        <link rel="stylesheet" type="text/css" href="../../resources/css/carRental.css">
        <link rel="stylesheet" type="text/css" href="../../resources/layui/css/layui.css">
    </head>
    <body>
    	<div class="container">
            <header>
                <a class="return_arr" href="/vehicle/carInfo.do"></a>
                <span>查看车辆凭证</span>
            </header>
            <div class="upload_cons">
                <div class="layui-upload">
                    <img src="${userOrder.remark}"/>
                </div>
                
                <form class="layui-form" style="margin-top:50px;">
                    <div class="layui-form-item">
                        <label class="layui-form-label" style="text-align:right;padding:9px 0;">加盟类型</label>
                        <div class="layui-input-block" style="margin-left: 50%;padding-top: 10px;">
                            <#if userOrder.addMethod == '0'>
                            	自主加盟
                            <#elseif userOrder.addMethod == '1'>
                            	代人申购
                            <#else>
                            </#if>
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <label class="layui-form-label" style="text-align:right;padding:9px 0;">支付方式</label>
                        <div class="layui-input-block" style="margin-left: 50%;padding-top: 10px;">
                            <#if userOrder.paymentMethod == '3'>
                            	银行卡
                            <#else>
                            	${userOrder.paymentMethod!''}
                            </#if>
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <label class="layui-form-label" style="width:85px;text-align:right;padding:9px 0;">加盟人手机号</label>
                        <div class="layui-input-block" style="margin-left: 50%;padding-top: 10px;">
                            <#if userOrder.addMethod == '0'>
                            	${userOrder.userName!''}
                            <#elseif userOrder.addMethod == '1'>
                            	${userOrder.otherPersonMobile!''}
                            <#else>
                            </#if>
                        </div>
                    </div>
                </form>
            </div>
        </div>
        
        
        <script type="text/javascript" src="../../resources/layui/layui.js"></script>
        <script type="text/javascript" src="../../resources/js/jquery-2.1.1.min.js"></script>
        <script type="text/javascript">
        </script>
    </body>
</html>