<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <title>奖励明细</title>
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
                <span>奖励明细</span>
            </header>
            <div class="reward_detail_cons">
                <div class="total outer">
                    <p>当月奖励：<span>¥10000</span></p>
                    <div class="date">
                        <input type="text" class="layui-input" id="test3" placeholder="">
                    </div>
                </div>
                <div class="detail_data">
                    <ul id="detail_data">
                        <!-- 数据插入 -->
                    </ul>
                </div>
            </div>
        </div>
        <script type="text/javascript" src="layui/layui.js"></script>
        <script type="text/javascript">
            var myDate = new Date();
            var Year = myDate.getFullYear();
            var Month = '0'+(myDate.getMonth()+1);
            $(".date>input").attr('placeholder',Year+'/'+Month)

            //日历
            layui.use('laydate', function(){
                var laydate = layui.laydate;
                //年月选择器
                laydate.render({
                    elem: '#test3',
                    type: 'month',
                    theme: '#076C45',
                    format: 'yyyy/MM'
                });
            })

            //流加载
            layui.use('flow', function(){
                var flow = layui.flow;
                flow.load({
                    elem: '#detail_data' //流加载容器
                    ,scrollElem: '' //滚动条所在元素，一般不用填，此处只是演示需要。
                    ,done: function(page, next){ //执行下一页的回调
                  
                    //模拟数据插入
                    setTimeout(function(){
                        var lis = [];
                        for(var i = 0; i < 20; i++){
                            //TODO  后台数据
                            lis.push('<li class="outer"><div class="data_time"><i>一级推荐</i><p>2018/03/12 09:00:00</p></div><span>¥100</span></li>')
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