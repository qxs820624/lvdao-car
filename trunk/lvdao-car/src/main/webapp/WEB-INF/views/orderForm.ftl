<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <title>确认订单</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
        <meta name="format-detection" content="telephone=no">
    	<meta name="apple-mobile-web-app-capable" content="yes">
    	<script type="text/javascript" src="../../resources/js/jquery-2.1.1.min.js"></script>
        <link rel="stylesheet" type="text/css" href="../../resources/css/carRental.css">
    </head>
    <body>
    	<div class="container">
            <header>
                <a class="return_arr" href="/user/investment.do"></a>
                <span>确认订单</span>
            </header>
            <div class="order_form_cons">
                <div class="order_form_main outer">
                    <div class="order_form_img">
                        <img src="../../resources/images/xin5.png" alt="">
                    </div>
                    
                    <div class="order_form_img">
                        <img src="../../resources/images/xin5.png" alt="">
                    </div>
                    <div class="order_form_info">
                        <h5>新能源汽车低级轿车</h5>
                        <p>每天可返利0.5%</p>
                        <span>¥5万</span>
                    </div>
                    <#if investType?? && investType == "1" >
                    <div class="order_form_img">
                        <img src="../../resources/images/xin5.png" alt="">
                    </div>
                    <div class="order_form_info">
                        <h5>新能源汽车低级轿车</h5>
                        <p>每天可返利0.5%</p>
                        <span>¥5万</span>
                    </div>
                    </#if>
                    
                    <#if investType?? && investType == "2" >
                    <div class="order_form_img">
                        <img src="../../resources/images/benben1.jpg" alt="">
                    </div>
                    <div class="order_form_info">
                        <h5>新能源汽车中级轿车</h5>
                        <p>每天可返利0.5%</p>
                        <span>¥10万</span>
                    </div>
                    </#if>
                    
                    <#if investType?? && investType == "3" >
                    <div class="order_form_img">
                        <img src="../../resources/images/xin5.png" alt="">
                    </div>
                    <div class="order_form_info">
                        <h5>新能源汽车高级级轿车</h5>
                        <p>每天可返利0.5%</p>
                        <span>¥20万</span>
                    </div>
                    </#if>
                    
                    <#if investType?? && investType == "4" >
                    <div class="order_form_img">
                        <img src="../../resources/images/xin5.png" alt="">
                    </div>
                    <div class="order_form_info">
                        <h5>品牌汽车高级房车</h5>
                        <p>每天可返利0.5%</p>
                        <span>¥50万</span>
                    </div>
                    </#if>
                    
                    <#if investType?? && investType == "5" >
                    <div class="order_form_img">
                        <img src="../../resources/images/xin5.png" alt="">
                    </div>
                    <div class="order_form_info">
                        <h5>品牌汽车高级房车</h5>
                        <p>每天可返利0.5%</p>
                        <span>¥100万</span>
                    </div>
                    </#if>
                    
                </div>
                <div class="pay_number outer">
                   <span>打款账号</span>
                    <div>
                        <p>深圳驴道房车连锁管理有限公司</p>
                        <p>79140078801700000155</p>
                    </div>
                </div>
                <div class="order_form_btn">
                    <button>确认投资</button>
                </div>
            </div>
            <div class="pop_up">
                <div class="ask_box">
                    <p>是否确认投资？</p>
                    <div class="ask_btn outer">
                        <button class="confirm_btn" onclick="javascript:window.location.href='/order/uploadVoucher.do'">确认</button>
                        <button class="cancel_btn">取消</button>
                    </div>
                </div>
            </div>
        </div>
        <script type="text/javascript">
            //弹窗
            $(".order_form_btn>button").click(function(event) {
                $(".pop_up").show();
            });

            //取消弹窗
            $("button.cancel_btn").click(function(event) {
                $(".pop_up").hide();
            });
        </script>
    </body>
</html>