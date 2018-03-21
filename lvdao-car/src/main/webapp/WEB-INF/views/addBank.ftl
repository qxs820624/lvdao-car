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
        <link rel="stylesheet" type="text/css" href="../../resources/layui/css/layui.css">
        <style type="text/css">
            .layui-form-select .layui-input{height:50px;border:none;text-align:center;}
        </style>
    </head>
    <body>
    	<div class="container">
            <header>
                <a class="return_arr" href="/user/personal.do"></a>
                <span>现金提现</span>
            </header>
            <div class="add_bank_cons">
                <div class="add_bank_form">
                    <form class="layui-form" action="">
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
                    </form>
                    <ul>
                        <li class="outer">
                            <span>支付宝账户</span>
                            <input type="text">
                        </li>
                        <li class="outer">
                            <span>姓名</span>
                            <input type="text">
                        </li>
                        <li class="outer" style="border-bottom:1px solid #E4E4E4">
                            <span>备注</span>
                            <input type="text">
                        </li>
                    </ul>
                    <div>
                        <div class="cash_num">提现金额</div>
                        <div class="cash_num_input outer">
                            <span>¥</span>
                            <input type="text" value="">
                        </div>
                        <div class="point_text outer">
                            <p>可提现金额¥10088,手续费0.1%</p>
                            <span>全部提现</span>
                        </div>
                    </div>
                </div>
                <button>确认提现</button>
            </div>
        </div>
        <script type="text/javascript" src="../../resources/layui/layui.js"></script>
        <script type="text/javascript">
            layui.use('form', function(){
                var form = layui.form;
            })
        </script>
    </body>
</html>