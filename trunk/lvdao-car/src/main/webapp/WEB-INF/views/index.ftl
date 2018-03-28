<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <title><#if isApp == '1'>汽车租赁<#else>首页</#if></title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
        <meta name="format-detection" content="telephone=no">
    	<meta name="apple-mobile-web-app-capable" content="yes">
    	<script type="text/javascript" src="../../resources/js/jquery-2.1.1.min.js"></script>
        <link rel="stylesheet" type="text/css" href="../../resources/css/carRental.css">
        <link rel="stylesheet" type="text/css" href="../../resources/dist/css/swiper.min.css">
        <link rel="stylesheet" type="text/css" href="../../resources/layui/css/layui.css">
    </head>
    <body>
    	<div class="container">
            <header>
            	<#if isApp == '1'><a class="return_arr" href="/user/userLogin.do"></a></#if>
                <span>首页</span>
                <a class="header_info layui-nav-item" href="/info/infoPage.do">
                    <img src="../../resources/images/icon_info.png" alt="">
                    <span class="layui-badge-dot"></span>
                </a>
            </header>
            <div class="index_cons">
                <div class="swiper-container swiper-container01">
                    <div class="swiper-wrapper">
                        <div class="swiper-slide"><img src="../../resources/images/banner.png"></div>
                        <div class="swiper-slide"><img src="../../resources/images/banner.png"></div>
                        <div class="swiper-slide"><img src="../../resources/images/banner.png"></div>
                        <div class="swiper-slide"><img src="../../resources/images/banner.png"></div>
                    </div>
                    <div class="swiper-pagination"></div>
                </div>
                <div class="investor_driver outer">
                    <div class="left_investor" onclick="javascript:window.location.href='/user/investment.do'">
                        <img src="../../resources/images/icon_investor.png" alt="">
                        <span>我是投资人</span>
                        <p>每天可以从平台领取返利</p>
                    </div>
                    <div class="right_driver" id = "ale" onclick="javascript:layer.msg('请进入驴道拼货app使用此功能')">
                        <img src="../../resources/images/icon_driver.png" alt="">
                        <span>我是司机</span>
                        <p>平台提供车让你赚钱</p>
                    </div>
                </div>
                <div class="car_series">
                    <div class="swiper-container swiper-container02">
                        <div class="swiper-wrapper">
                            <div class="swiper-slide">
                                <img src="../../resources/images/benben1.jpg">
                                <div class="outer">
                                    <div class="car_brand">
                                        <span>新能源</span>
                                        <i>五万车主</i>
                                    </div>
                               <!--      <button>选TA</button>  -->
                                </div>
                            </div>
                            <div class="swiper-slide">
                                <img src="../../resources/images/benbenten1.jpg">
                                <div class="outer">
                                    <div class="car_brand">
                                        <span>新能源</span>
                                        <i>十万车主</i>
                                    </div>
                                   <!-- <button>选TA</button> -->
                                </div>
                            </div>
                            <div class="swiper-slide">
                                <img src="../../resources/images/chuanqi1.jpg">
                                <div class="outer">
                                    <div class="car_brand">
                                        <span>新能源</span>
                                        <i>二十万车主</i>
                                    </div>
                                  <!--  <button>选TA</button>  -->
                                </div>
                            </div>
                            <div class="swiper-slide">
                                <img src="../../resources/images/fang1.jpg">
                                <div class="outer">
                                    <div class="car_brand">
                                        <span>房车</span>
                                        <i>五十万车主</i>  
                                    </div>
                             <!--       <button>选TA</button>  -->
                                </div>
                            </div>
                            
                             <div class="swiper-slide">
                                <img src="../../resources/images/benchi.jpg">
                                <div class="outer">
                                    <div class="car_brand">
                                        <span>房车</span>
                                        <i>一百万车主</i>  
                                    </div>
                             <!--       <button>选TA</button>  -->
                                </div>
                            </div>
                            
                        </div>
                        <div class="swiper-pagination"></div>
                    </div>
                </div>
            </div>
            <footer>
                <a class="selected" href="/index/index.do?userName=<#if user??>${user.userName}</#if>">
                    <span>
                        <b class="index"></b>
                    </span>
                    <i>首页</i>
                </a>
                <a href="/vehicle/myCar.do">
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
        <script type="text/javascript" src="../../resources/dist/js/swiper.min.js"></script>
        <script type="text/javascript" src="../../resources/layui/layui.js"></script>
        <script type="text/javascript">
            //导航
            $(function(){
                $('footer').find('a').on('click', function(event) {
                    $('footer').find('a').removeClass("selected");
                    $(this).addClass("selected");
                });
            });
            
             //新消息
            layui.use('element', function(){
                var element = layui.element;
            });


            //轮播
            var swiper = new Swiper('.swiper-container01', {
                pagination: '.swiper-pagination',
                paginationClickable: true,
                spaceBetween: 10
            });

            var swiper = new Swiper('.swiper-container02', {
                slidesPerView: 3,
                paginationClickable: true,
                spaceBetween: 10,
                freeMode: true
            });
        </script>
    </body>
</html>