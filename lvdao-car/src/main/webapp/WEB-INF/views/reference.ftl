<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <title>推荐人</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
        <meta name="format-detection" content="telephone=no">
    	<meta name="apple-mobile-web-app-capable" content="yes">
    	<script type="text/javascript" src="js/jquery-2.1.1.min.js"></script>
        <link rel="stylesheet" type="text/css" href="../../resources/css/carRental.css">
        <link rel="stylesheet" type="text/css" href="../../resources/layui/css/layui.css">
    </head>
    <body>
    	<div class="container">
            <header>
                <a class="return_arr" href="/user/personal.do"></a>
                <span>推荐人</span>
            </header>
            <div class="reference_cons">
                <ul class="outer">
                    <li class="reference_current">我的推荐人<i></i></li>
                    <li>我推荐的人<i></i></li>
                </ul>
                <ol>
                    <li>
                        <div class="reference_info outer">
                            <p>姓名</p>
                            <span><#if userParentName??>${userParentName}</span><#else>？？</#if></span>
                        </div>
                        <div class="reference_info outer">
                            <p>手机号</p>
                            <span><#if myRecommendUser??>${myRecommendUser}</span><#else>？？</#if></span>
                        </div>
                        <div class="erweima" style="width:60%;position:fixed;bottom:20%;left:20%;padding:20px;box-sizing:border-box; -webkit-box-sizing:border-box; -moz-box-sizing:border-box;">
                            <!-- 二维码 -->
                            <img src="images/erweima.jpg" alt="">
                            <p style="text-align:center;margin-top:10px;">我的推荐二维码</p>
                        </div>
                    </li>
                    <li style="display:none;">
                        <div class="list">
                            <ul class="outer">
                                <li>序号</li>
                                <li>姓名</li>
                                <li>手机号</li>
                            </ul>
                            <ol id="reference_data">
                                <li class="outer">
                                    <span>1</span>
                                    <span>兔斯基</span>
                                    <span>15012341718</span>
                                </li>
                            </ol>
                        </div>
                    </li>
                </ol>
            </div>
        </div>
        <script type="text/javascript" src="../../resources/layui/layui.js"></script>
        <script type="text/javascript">
            $(".reference_cons>ul>li").click(function(event) {
                $(this).addClass('reference_current').siblings().removeClass('reference_current');
                $(".reference_cons>ol>li").eq($(this).index()).show().siblings().hide();
            });

            var Width = $(window).width();
            if(Width<321){
                $(".erweima").css('bottom', '10%');
            }else{
                $(".erweima").css('bottom', '20%');
            }

            var er_Width = $(".erweima").width();
            $(".erweima>img").height(er_Width);

            //流加载
            layui.use('flow', function(){
                var flow = layui.flow;
                flow.load({
                    elem: '#reference_data' //流加载容器
                    ,scrollElem: '' //滚动条所在元素，一般不用填，此处只是演示需要。
                    ,done: function(page, next){ //执行下一页的回调
                  
                    //模拟数据插入
                    setTimeout(function(){
                        var lis = [];
                        for(var i = 0; i < 20; i++){
                            //TODO  后台数据
                            lis.push('<li class="outer"><span>'+ ( (page-1)*20 + i + 1 ) +'</span><span>兔斯基</span><span>15012341718</span></li>')
                        }
                    
                        //执行下一页渲染，第二参数为：满足“加载更多”的条件，即后面仍有分页
                        //pages为Ajax返回的总页数，只有当前页小于总页数的情况下，才会继续出现加载更多
                        next(lis.join(''), page < 10); //假设总页数为 10
                    }, 500);
                    }
                });
            })
        </script>
    </body>
</html>