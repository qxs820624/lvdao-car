<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <title>选择投资项目</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
        <meta name="format-detection" content="telephone=no">
    	<meta name="apple-mobile-web-app-capable" content="yes">
    	<script type="text/javascript" src="../../resources/js/jquery-2.1.1.min.js"></script>
        <link rel="stylesheet" type="text/css" href="../../resources/css/carRental.css">
        <link rel="stylesheet" type="text/css" href="../../resources/dist/css/swiper.css">
    </head>
    <body>
    	<div class="container">
            <header>
                <a class="return_arr" href="/index/index.do"></a>
                <span>选择投资项目</span>
            </header>
            <div class="investment_cons">
                <div class="investment_type">
                    <h3>新能源汽车</h3>
                    <div class="investment_img">
                        <div class="swiper-container investment01">
                            <div class="swiper-wrapper">
                                <div class="swiper-slide"><img src="../../resources/images/info_img.png" alt=""></div>
                                <div class="swiper-slide"><img src="../../resources/images/info_img.png" alt=""></div>
                                <div class="swiper-slide"><img src="../../resources/images/info_img.png" alt=""></div>
                                <div class="swiper-slide"><img src="../../resources/images/info_img.png" alt=""></div>
                                <div class="swiper-slide"><img src="../../resources/images/info_img.png" alt=""></div>
                            </div>
                        </div>
                        <!-- Add Pagination -->
                        <div class="swiper-pagination swiper-pagination01"></div>
                        <!-- Add Arrows -->
                        <div class="swiper-button-next swiper-button-next01"></div>
                        <div class="swiper-button-prev swiper-button-prev01"></div>
                    </div>
                    <div class="investment_info outer">
                        <p><i>¥10</i>万</p>
                        <span>每天可返利500元</span>
                        <button onclick="javascript:window.location.href='orderForm.html'">选TA</button>
                    </div>
                </div>
                <div class="investment_type">
                    <h3>新能源汽车</h3>
                    <div class="investment_img">
                        <div class="swiper-container investment02">
                            <div class="swiper-wrapper">
                                <div class="swiper-slide"><img src="../../resources/images/info_img.png" alt=""></div>
                                <div class="swiper-slide"><img src="../../resources/images/info_img.png" alt=""></div>
                                <div class="swiper-slide"><img src="../../resources/images/info_img.png" alt=""></div>
                                <div class="swiper-slide"><img src="../../resources/images/info_img.png" alt=""></div>
                                <div class="swiper-slide"><img src="../../resources/images/info_img.png" alt=""></div>
                            </div>
                        </div>
                        <!-- Add Pagination -->
                        <div class="swiper-pagination swiper-pagination02"></div>
                        <!-- Add Arrows -->
                        <div class="swiper-button-next swiper-button-next02"></div>
                        <div class="swiper-button-prev swiper-button-prev02"></div>
                    </div>
                    <div class="investment_info outer">
                        <p><i>¥10</i>万</p>
                        <span>每天可返利500元</span>
                        <button>选TA</button>
                    </div>
                </div>
            </div>
        </div>
        <script type="text/javascript" src="../../resources/dist/js/swiper.min.js"></script>
        <script type="text/javascript">
            var swiper = new Swiper('.investment01', {
                nextButton: '.swiper-button-next01',
                prevButton: '.swiper-button-prev01',
                pagination: '.swiper-pagination01',
                paginationType: 'fraction'
            });
            var swiper = new Swiper('.investment02', {
                nextButton: '.swiper-button-next02',
                prevButton: '.swiper-button-prev02',
                pagination: '.swiper-pagination02',
                paginationType: 'fraction'
            });
        </script>
    </body>
</html>