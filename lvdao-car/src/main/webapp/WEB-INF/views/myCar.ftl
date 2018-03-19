<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <title>我的汽车</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
        <meta name="format-detection" content="telephone=no">
    	<meta name="apple-mobile-web-app-capable" content="yes">
        <script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=Yxk3CU7QLBCn4guiIsjjmZj6"></script>
        <script type="text/javascript" src="http://webapi.amap.com/maps?v=1.3&key=8325164e247e15eea68b59e89200988b"></script>
    	<script type="text/javascript" src="../../resources/js/jquery-2.1.1.min.js"></script>
        <link rel="stylesheet" type="text/css" href="../../resources/css/carRental.css">
    </head>
    <body>
    	<div class="container">
            <header>
                <span>我的汽车</span>
            </header>
            <div class="mycar_cons">
                <div class="map" id="allmap"></div>
                <div class="driver_info">
                    <div class="outer">
                        <div class="info_left">
                            <img src="../../resources/images/icon_carG.png" alt="">
                        </div>
                        <div class="info_right">宝安区西乡宝源路123号</div>
                    </div>
                    <p>车牌号： <span>粤B 123456</span></p>
                    <p>司机信息：<span>李师傅</span><i>已认证</i></p>
                </div>
            </div>
            <footer>
                <a href="/index/index.do">
                    <span>
                        <b class="index"></b>
                    </span>
                    <i>首页</i>
                </a>
                <a class="selected" href="/user/myCar.do">
                    <span>
                        <b class="my_car"></b>
                    </span>
                    <i>我的汽车</i>
                </a>
                <a href="/user/personal.do">
                    <span>
                        <b class="personal"></b>
                    </span>
                    <i>个人中心</i>
                </a>
            </footer>
        </div>
        <script type="text/javascript" src="../../resources/js/baiduMap.js"></script>
        <script type="text/javascript">
            //地图高度
            var Height = $(window).height()*0.47;
            $(".mycar_cons .map").height(Height)

            //导航
            $(function(){
                $('footer').find('a').on('click', function(event) {
                    $('footer').find('a').removeClass("selected");
                    $(this).addClass("selected");
                });
            });
        </script>
    </body>
</html>