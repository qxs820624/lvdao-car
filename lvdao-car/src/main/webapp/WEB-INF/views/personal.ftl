<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <title>个人中心</title>
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
                <a class="reference" href="/user/reference.do">推荐人</a>
                <span>个人中心</span>
                <a class="car_info" href="/vehicle/carInfo.do">汽车信息</a>
            </header>
            <div class="personal_cons">
                <div class="swiper-container personal_info">
                    <div class="swiper-wrapper">
                        <div class="swiper-slide">
                            <div class="personal_box return_account">
                                <h4>燃油补贴账户</h4>
                                <span>¥<#if accountbonuAmount??>${accountbonuAmount}<#else>0</#if></span>
                                <div class="button return_account_btn outer">
                                    <button onclick="javascript:window.location.href='/user/accountListDetail.do?logType='+'20'">返还明细</button>
                                    <button onclick="javascript:window.location.href='/user/cashWithdraw.do'">账户提现</button>
                                </div>
                            </div>
                        </div>
                        <div class="swiper-slide">
                            <div class="personal_box income_account">
                                <h4>燃油包</h4>
                                <span>¥<#if shareRewardAccount??>${shareRewardAccount}</span><#else>0</#if></span>
                                <div class="button income_account_btn outer">
                                    <button onclick="javascript:window.location.href='/user/accountListDetail.do?logType='+'6'">收入明细</button>
                                    <button onclick="javascript:window.location.href='/user/cashWithdraw.do'">账户提现</button>
                                </div>
                            </div>
                        </div>
                        <div class="swiper-slide">
                            <div class="personal_box commended">
                                <h4>现金账户</h4>
                                <span>¥<#if rmbAccount??>${rmbAccount}<#else>0</#if></span>
                                <div class="button commended_btn outer">
                                    <button onclick="javascript:window.location.href='/user/accountListDetail.do?logType='+'12'">奖励明细</button>
                                    <button onclick="javascript:window.location.href='/user/cashWithdraw.do'">账户提现</button>
                                </div>
                            </div>
                        </div>
                        
                        
                        <div class="swiper-slide">
                            <div class="personal_box hongkong">
                                <h4>上车补贴账户</h4>
                                <span>¥<#if recommendBonusAccount??>${recommendBonusAccount}<#else>0</#if></span>
                                <div class="button income_account_btn outer">
                                    <button onclick="javascript:window.location.href='/user/accountListDetail.do?logType='+'21'">奖励明细</button>
                                    <button onclick="javascript:window.location.href='/user/cashWithdraw.do'">账户提现</button>
                                </div>
                            </div>
                        </div>
                  
                        
                        <div class="swiper-slide">
                            <div class="personal_box mainland">
                                <h4>乘车券账户</h4>
                                <span>¥<#if rideCouponAccount??>${rideCouponAccount}<#else>0</#if></span>
                                <div class="button return_account_btn outer">
                                    <button onclick="javascript:window.location.href='/user/accountListDetail.do?logType='+'5'">奖励明细</button>
                                    <button onclick="javascript:window.location.href='/user/cashWithdraw.do'">账户提现</button>
                                </div>
                            </div>
                        </div>
                        <!--
                        <div class="swiper-slide">
                            <div class="personal_box usa">
                                <h4>美股账户<#if stockAccount??>${stockAccount}</span><#else>0</#if></h4>
                                <div class="shares"><i>见市值</i>股</div>
                                <div class="button commended_btn outer">
                                    <button onclick="javascript:window.location.href='/user/accountListDetail.do?logType='+'2'">奖励明细</button>
                                </div>
                            </div>
                        </div>
                        -->
                        <div class="swiper-slide">
                            <div class="personal_box usa">
                                <h4>美股账户<#if stockAccount??>${stockAccount}</span><#else>0</#if></h4>
                                <div class="shares"><i>市值</i>股</div>
                            </div>
                        </div>
                       
                        
                    </div>
                </div>
                <div class="swiper-container personal_data">
                    <div class="swiper-wrapper">
                        <div class="swiper-slide">
                            <ul class="return_account_data">
                                <li class="outer">
                                    <p>应返金额</p>
                                    <span>¥100000</span>
                                </li>
                                <li class="outer">
                                    <p>已返金额</p>
                                    <span>¥20000</span>
                                </li>
                                <li class="outer">
                                    <p>已返比例</p>
                                    <span>20%</span>
                                </li>
                            </ul>
                        </div>
                        <div class="swiper-slide">
                            <ul class="income_account_data">
                                <li class="outer">
                                    <p>收入比例</p>
                                    <span>50%</span>
                                </li>
                            </ul>
                        </div>
                        <div class="swiper-slide">
                            <ul class="commended_data">
                                <li class="outer">
                                    <p>奖金比例</p>
                                    <span>1%</span>
                                </li>
                                <li class="outer">
                                    <p>推荐总数</p>
                                    <span>2</span>
                                </li>
                            </ul>
                        </div>
                        <div class="swiper-slide">
                            <ul class="usa_data">
                                <li class="outer">
                                    <p>当前股价</p>
                                    <span>$8</span>
                                </li>
                            </ul>
                        </div>
                        <div class="swiper-slide">
                            <ul class="hongkong_data">
                                <li class="outer">
                                    <p>当前股价</p>
                                    <span>hk$8</span>
                                </li>
                            </ul>
                        </div>
                        <div class="swiper-slide">
                            <ul class="mainland_data">
                                <li class="outer">
                                    <p>当前股价</p>
                                    <span>¥8</span>
                                </li>
                            </ul>
                        </div>
                    </div>
                    <div class="swiper-pagination"></div>
                </div>
            </div>
            <footer>
                <a href="/index/index.do">
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
                <a class="selected" href="/user/personal.do">
                    <span>
                        <b class="personal"></b>
                    </span>
                    <i>个人中心</i>
                </a>
            </footer>
        </div>
        <script type="text/javascript" src="../../resources/dist/js/swiper.min.js"></script>
        <script type="text/javascript">
            var Height = $(window).height()*0.43;
            $(".personal_info .personal_box").height(Height);

            var Width = $(".personal_box .shares").width();
            $(".personal_box .shares").css({
                height: Width,
                lineHeight: Width+'px'
            });

            //导航
            $(function(){
                $('footer').find('a').on('click', function(event) {
                    $('footer').find('a').removeClass("selected");
                    $(this).addClass("selected");
                });
            });

            // 轮播
            var galleryTop = new Swiper('.personal_info',{
                slidesPerView: 'auto',
                centeredSlides: true,
                paginationClickable: true,
                spaceBetween: 17,
                initialSlide: 0
            })
            var galleryThumbs = new Swiper('.personal_data',{
                paginationClickable: true,
                spaceBetween: 30
            })
            galleryTop.params.control = galleryThumbs;
            galleryThumbs.params.control = galleryTop;
        </script>
    </body>
</html>