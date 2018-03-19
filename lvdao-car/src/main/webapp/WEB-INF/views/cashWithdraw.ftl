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
    </head>
    <body>
    	<div class="container">
            <header>
                <a class="return_arr" href="/user/personal.do"></a>
                <span>现金提现</span>
            </header>
            <div class="cash_cons">
                <div class="cash_form">
                    <div class="add_bank outer">
                        <p>到账银行卡</p>
                        <span onclick="javascript:window.location.href='/user/addBank.do'"></span>
                    </div>
                    <div class="add_bank outer">
                        <p>到账支付宝</p>
                        <span></span>
                    </div>
                    <div class="cash_num">提现金额</div>
                    <div class="cash_num_input outer">
                        <span>¥</span>
                        <input type="text" value="">
                    </div>
                    <div class="point_text outer">
                        <p>可提现金额¥10088,手续费0.1%</p>
                        <span>全部提现</span>
                    </div>
                    <button>确认提现</button>
                </div>
            </div>
        </div>
    </body>
</html>