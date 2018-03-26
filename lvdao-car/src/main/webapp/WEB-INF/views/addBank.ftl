<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <title>现金提现</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
        <meta name="format-detection" content="telephone=no">
    	<meta name="apple-mobile-web-app-capable" content="yes">
    	<script type="text/javascript" src="../../resources/js/jquery-2.1.1.min.js"></script>
        <link rel="stylesheet" type="text/css" href="../../resources/css/carRental.css">
        <style type="text/css">
            .layui-form-select .layui-input{height:50px;border:none;text-align:center;}
        </style>
    </head>
    <body>
    	<div class="container">
            <header>
                <a class="return_arr" href="/user/personal.do"></a>
                <span><#if userAccount??>${userAccount.accountName!''}</#if>提现</span>
            </header>
            <div class="add_bank_cons">
                <div class="add_bank_form">
                	<input type="hidden" id="accountId" value="<#if userAccount??>${userAccount.accountId!''}</#if>" />
                	<input type="hidden" id="accountAmount" value="<#if userAccount??>${userAccount.accountAmount!'0'}</#if>" />
                    <!--<form class="layui-form" action="">
                        <div class="layui-form-item" style="margin:0;height:50px;">
                            <label class="layui-form-label" style="text-align:left;padding:0 17px;line-height:50px;font-size:15px;">提现账户</label>
                            <div class="layui-input-block" style="margin-left:40%;">
                                <select name="interest" lay-filter="aihao">
                                    <option value="" ></option>
                                    <option value="0" selected="">燃油补贴账户</option>
                                    <option value="1">2</option>
                                </select>
                            </div>
                        </div>
                    </form>-->
                    <ul>
                        <li class="outer">
                            <span>支付宝账户</span>
                            <input type="text" id="alipayAccount">
                        </li>
                        <li class="outer">
                            <span>姓名</span>
                            <input type="text" id="userRealName">
                        </li>
                        <li class="outer" style="border-bottom:1px solid #E4E4E4">
                            <span>备注</span>
                            <input type="text" id="desc">
                        </li>
                    </ul>
                    <div>
                        <div class="cash_num">提现金额</div>
                        <div class="cash_num_input outer">
                            <span>¥</span>
                            <input type="text"  id="withdrawAmount" value="">
                        </div>
                        <div class="point_text outer">
                            <p>可提现金额¥<#if userAccount??>${userAccount.accountAmount!'0'}<#else>0</#if>,手续费20%</p>
                            <span id="withdrawSpan">全部提现</span>
                        </div>
                    </div>
                </div>
                <button id="withdrawButton">确认提现</button>
            </div>
        </div>
        <script type="text/javascript">
            $("#withdrawSpan").click(function() {
            	var withdrawAmount = $("#accountAmount").val();
            	$("#withdrawAmount").val(withdrawAmount);
            })
            
            $("#withdrawButton").click(function() {
            	var accountId = $("#accountId").val();
            	var alipayAccount = $("#alipayAccount").val();
            	var userRealName = $("#userRealName").val();
            	var desc = $("#desc").val();
            	var withdrawAmount = $("#withdrawAmount").val();
            	
            	if (accountId == null || accountId == '') {
            		layer.alert("请先选择提现账户");
            		return false;
            	}
            	
            	if (alipayAccount == null || alipayAccount == '') {
            		layer.alert("请先选择支付宝账户");
            		return false;
            	}
            	
            	if (userRealName == null || userRealName == '') {
            		layer.alert("请先选择支付宝账户");
            		return false;
            	}
            	
            	if (withdrawAmount == null || withdrawAmount == '') {
            		layer.alert("请先选择支付宝账户");
            		return false;
            	}
            	
            })
        </script>
    </body>
</html>