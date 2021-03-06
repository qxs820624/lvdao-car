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
                                <span>¥<#if accountbonuAmount??>${accountbonuAmount!'0'}<#else>0</#if></span>
                                <div class="button return_account_btn outer">
                                    <button onclick="javascript:window.location.href='/user/accountListDetail.do?logType=20'">返还明细</button>
                                    <button onclick="javascript:window.location.href='/user/cashWithdraw.do?accountType=6'">账户提现</button>
                                </div>
                            </div>
                        </div>
                        <div class="swiper-slide">
                            <div class="personal_box income_account">
                                <h4>燃油包</h4>
                                <span><#if ownAmount??>${ownAmount}个</span><#else>0个</#if></span>
                                <div class="button income_account_btn outer">
                                    <button onclick="javascript:window.location.href='/user/accountListDetail.do?logType=24'">账户明细</button>
                                </div>
                            </div>
                        </div>
                        <div class="swiper-slide">
                            <div class="personal_box commended">
                                <h4>现金账户</h4>
                                <span>¥<#if rmbAccount??>${rmbAccount}<#else>0</#if></span>
                                <div class="button commended_btn outer">
                                    <button onclick="javascript:window.location.href='/user/accountListDetail.do?logType=12'">奖励明细</button>
                                </div>
                            </div>
                        </div>
                        
                        
                        <div class="swiper-slide">
                            <div class="personal_box hongkong">
                                <h4>上车补贴账户</h4>
                                <span>¥<#if recommendBonusAccount??>${recommendBonusAccount}<#else>0</#if></span>
                                <div class="button income_account_btn outer">
                                    <button onclick="javascript:window.location.href='/user/accountListDetail.do?logType=21'">奖励明细</button>
                                    <button onclick="javascript:window.location.href='/user/cashWithdraw.do?accountType=5'">账户提现</button>
                                </div>
                            </div>
                        </div>
                  
                        
                        <div class="swiper-slide">
                            <div class="personal_box mainland">
                                <h4>乘车券账户</h4>
                                <span>¥<#if rideCouponAccount??>${rideCouponAccount}<#else>0</#if></span>
                                <div class="button return_account_btn outer">
                                    <button onclick="javascript:window.location.href='/user/accountListDetail.do?logType=23'">奖励明细</button>
                                </div>
                            </div>
                        </div>
                        <!--
                        <div class="swiper-slide">
                            <div class="personal_box usa">
                                <h4>美股账户<#if stockAccount??>${stockAccount}</span><#else>0</#if></h4>
                                <div class="shares"><i>见市值</i>股</div>
                                <div class="button commended_btn outer">
                                    <button onclick="javascript:window.location.href='/user/accountListDetail.do?logType=22'">奖励明细</button>
                                </div>
                            </div>
                        </div>
                        -->
                        <div class="swiper-slide">
                            <div class="personal_box usa">
                                <h4>YECO股券账户</h4>
                                <div class="shares" ><i><#if yecoStock??>${yecoStock}</span><#else>0</#if></i>股</div>
                                <!--<div class="button commended_btn outer" style="background-color: ##9E905C;">
                                    <button onclick="javascript:window.location.href='/user/accountListDetail.do?logType=22'">账户明细</button>
                                </div>-->
                            </div>
                        </div>
                        
                          
                       <div class="swiper-slide">
                            <div class="personal_box hongkong">
                                <h4>港股股券账户</h4>
                                <div class="shares"><i><#if hongKongStock??>${hongKongStock}</span><#else>0</#if></i>股</div>
                                <!--<div class="button commended_btn outer">
                                    <button onclick="javascript:window.location.href='/user/accountListDetail.do?logType=28'">账户明细</button>
                                </div>-->
                            </div>
                        </div>
                        
                        <div class="swiper-slide">
                            <div class="personal_box mainland">
                                <h4>英吉尔股券账户</h4>
                                <div class="shares"><i><#if ingeoStock??>${ingeoStock}</span><#else>0</#if></i>股</div>
                                <!--<div class="button commended_btn outer">
                                    <button onclick="javascript:window.location.href='/user/accountListDetail.do?logType=27'">账户明细</button>
                                </div>-->
                            </div>
                        </div>
                    </div>
                </div>
                <div class="swiper-container personal_data">
                    <div class="swiper-wrapper">
                        <div class="swiper-slide">
                            <ul class="return_account_data">
                                <!--<li class="outer">
                                    <p>应返金额</p>
                                    <span>¥100000</span>
                                </li>-->
                                <li class="outer">
                                    <p>已补贴金额</p>
                                    <span>¥<#if sumBonusRaturn??>${sumBonusRaturn}<#else>0</#if></span>
                                </li>
                                <li class="outer" id="updatePasswordLi">
                                    <p>修改密码</p>
                                    <span></span>
                                </li>
                            </ul>
                        </div>
                        <div class="swiper-slide">
                            <ul class="income_account_data">
                                <li class="outer">
                                    <p>单个金额</p>
                                    <span>¥<#if packagePrice??>${packagePrice}<#else>0</#if></span>
                                </li>
                            </ul>
                        </div>
                        <div class="swiper-slide">
                            <ul class="commended_data">
                                <!--<li class="outer">
                                    <p>奖金比例</p>
                                    <span>1%</span>
                                </li>
                                <li class="outer">
                                    <p>推荐总数</p>
                                    <span>2</span>
                                </li>-->
                            </ul>
                        </div>
                        <div class="swiper-slide">
                            <!--<ul class="usa_data">
                                <li class="outer">
                                    <p>补贴次数</p>
                                    <span>$8</span>
                                </li>
                            </ul>-->
                        </div>
                        <div class="swiper-slide">
                            <ul class="hongkong_data">
                                <li class="outer">
                                    <p>过期时间</p>
                                    <span><#if expirationDate??>${expirationDate}<#else>无</#if></span>
                                </li>
                            </ul>
                        </div>
                        <div class="swiper-slide">
                            <ul class="mainland_data">
                                <li class="outer">
                                    <p>份额单价</p>
                                    <span>以市值为准</span>
                                </li>
                            </ul>
                        </div>
                        <div class="swiper-slide">
                            <ul class="mainland_data">
                                <li class="outer">
                                    <p>份额单价</p>
                                    <span>以市值为准</span>
                                </li>
                            </ul>
                        </div>
                        <div class="swiper-slide">
                            <ul class="mainland_data">
                                <li class="outer">
                                    <p>份额单价</p>
                                    <span>以市值为准</span>
                                </li>
                            </ul>
                        </div>
                    </div>
                    <div class="swiper-pagination"></div>
                </div>
            </div>
            <footer>
                <a href="/index/index.do?userName=<#if user??>${user.userName}</#if>">
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
            
            $("#updatePasswordLi").click(function() {
            	window.location.href = "/user/updatePasswordView.do";
            })
        </script>
    </body>
</html>