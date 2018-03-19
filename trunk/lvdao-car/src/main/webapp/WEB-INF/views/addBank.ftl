<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <title>添加银行卡</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
        <meta name="format-detection" content="telephone=no">
    	<meta name="apple-mobile-web-app-capable" content="yes">
    	<script type="text/javascript" src="../../resources/js/jquery-2.1.1.min.js"></script>
        <link rel="stylesheet" type="text/css" href="../../resources/css/carRental.css">
    </head>
    <body>
    	<div class="container">
            <header>
                <a class="return_arr" href="/user/cashWithdraw.do"></a>
                <span>添加银行卡</span>
            </header>
            <div class="add_bank_cons">
                <div class="add_bank_form">
                    <ul>
                        <li class="no_border outer">
                            <span>持卡人</span>
                            <input type="text">
                        </li>
                        <li class="outer">
                            <span>卡号</span>
                            <input type="text">
                            <i></i>
                        </li>
                        <li class="outer">
                            <span>银行卡类型</span>
                            <input type="text">
                        </li>
                        <li class="outer">
                            <span>手机号</span>
                            <input type="text">
                        </li>
                        <li class="outer">
                            <span>验证码</span>
                            <input type="text">
                        </li>
                    </ul>
                </div>
                <button>保存</button>
            </div>
        </div>
    </body>
</html>