<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <title>返还明细</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
        <meta name="format-detection" content="telephone=no">
    	<meta name="apple-mobile-web-app-capable" content="yes">
    	<script type="text/javascript" src="../../resources/js/jquery-2.1.1.min.js"></script>
        <link rel="stylesheet" type="text/css" href="../../resources/css/carRental.css">
        <link rel="stylesheet" type="text/css" href="../../resources/layui/css/layui.css">
    </head>
    <body>
    	<div class="container">
            <header>
                <a class="return_arr" href="/user/personal.do"></a>
                <span>返还明细</span>
            </header>
            <div class="return_detail_cons">
                <div class="total outer">
                    <p>当月返还总额：<span>¥${totalPrice!'0'}</span></p>
                    <div class="date">
                        <input type="text" class="layui-input" id="test3" placeholder="">
                    </div>
                </div>
                <div class="detail_data">
	                <ul>
	                	<#if accountList??>
	                		<#list accountList as account>
			                    <li class="no_border outer">
			                        <div class="data_time">
			                            <i>投资返还</i>
			                            <p>${account.createTime?string('yyyy/MM/dd hh:mm:ss')}</p>
			                        </div>
			                        <span>¥${account.logAmount!'0'}</span>
			                    </li>
		                    </#list>
	                    </#if>
					</ul>
                </div>
            </div>
        </div>
        <script type="text/javascript" src="../../resources/layui/layui.js"></script>
        <script type="text/javascript">
            var currentDate = '${selectDate}';
            if (currentDate == null || currentDate == '') {
	            var myDate = new Date();
	            var Year = myDate.getFullYear();
	            var Month = '0' + (myDate.getMonth()+1);
	            currentDate = Year+'-'+Month;
            }
            $(".date>input").attr('placeholder', currentDate);
			
            //日历
            layui.use('laydate', function() {
                var laydate = layui.laydate;
                //年月选择器
                laydate.render({
                    elem: '#test3',
                    type: 'month',
                    theme: '#076C45',
                    format: 'yyyy-MM',
                    done:function(value, date){//value, date, endDate点击日期、清空、现在、确定均会触发。回调返回三个参数，分别代表：生成的值、日期时间对象、结束的日期时间对象
				        window.location.href = "/user/accountListDetail.do?logType=${logType}&selectDate=" + value;
				    }
                });
            })
            
            
        </script>
    </body>
</html>