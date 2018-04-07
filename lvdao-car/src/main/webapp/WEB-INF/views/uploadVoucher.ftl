<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <title>上传付款凭证</title>
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
                <a class="return_arr" href="/order/orderForm.do"></a>
                <span>上传付款凭证</span>
            </header>
            <input type = 'hidden' id = 'picUrl' value='' />
            <input type = 'hidden' id = 'picRealUrl' value='' />
            <input type = 'hidden' id = 'orderAmount' value='' />
            <input type ="hidden" id = "orderType" value= "<#if orderType??>${orderType}</#if>"  />
            <div class="upload_cons">
                <div class="layui-upload">
                    <button type="button" class="upload_file" id="upload_file"></button>
                    <button type="button" class="upload_btn" id="upload_btn">上传付款凭证</button>
                </div>
                
                <form class="layui-form" style="margin-top:50px;">
                <input type = 'hidden' id = 'submitStatus' name="submitStatus" value='0' />
                    <div class="layui-form-item">
                        <label class="layui-form-label" style="text-align:right;padding:9px 0;">加盟类型</label>
                        <div class="layui-input-block" style="margin-left:40%;">
                            <select id="addType" name="addType" lay-filter="aihao">
                                <option value="" selected=""></option>
                                <option value="0">自主加盟</option>
                                <option value="1">代人申购</option>
                            </select>
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <label class="layui-form-label" style="text-align:right;padding:9px 0;">支付方式</label>
                        <div class="layui-input-block" style="margin-left:40%;">
                            <select name="payMethod" lay-filter="aihao" id="payMethod">
                                <option value="" selected=""></option>
                           <!--     <option value="0">支付宝</option>   -->
                                <option value="3">银行卡</option>
                            </select>
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <label class="layui-form-label" style="width:85px;text-align:right;padding:9px 0;">付款金额</label>
                        <div class="layui-input-block" style="margin-left:40%;">
                            <input type="tel" id="paymentAmount" name="paymentAmount" lay-verify="required" autocomplete="off" class="layui-input" value="">
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <label class="layui-form-label" style="width:85px;text-align:right;padding:9px 0;">加盟人手机号</label>
                        <div class="layui-input-block" style="margin-left:40%;">
                            <input type="tel" id="addUserMoblile" name="addUserMoblile" lay-verify="required|phone" autocomplete="off" class="layui-input" value="${user.userName}">
                        </div>
                    </div>
                    
                   <!--<div class="layui-form-item">
                        <label class="layui-form-label" style="width:85px;text-align:right;padding:9px 0;">加盟人姓名</label>
                        <div class="layui-input-block" style="margin-left:40%;">
                            <input type="tel" id="addUserRealName" name="addUserRealName" lay-verify="required" autocomplete="off" class="layui-input" value="">
                        </div>
                    </div>-->
                  <div class="layui-form-item" style="display: none;">
                     <label class="layui-form-label" style="width:85px;text-align:right;padding:9px 0;">我的推荐人</label>
                     <div class="layui-input-block" style="margin-left:40%;">
                        <input type="text"  class="layui-input" readonly disabled value="<#if userParentName??>${userParentName}</#if>">
                    </div>
                  </div>

                    <div class="upload_cons">
                     <div class="layui-input-block" style="margin-left: 0px;">
                     <button id="submitForm" class="layui-btn" lay-submit lay-filter="formDemo">立即提交</button>
                     <button type="reset" class="layui-btn layui-btn-primary">重置</button>
                     </div>
                    </div>
                </form>
            </div>
        </div>
        
        
        <script type="text/javascript" src="../../resources/layui/layui.js"></script>
        <script type="text/javascript" src="../../resources/js/jquery-2.1.1.min.js"></script>
        <script type="text/javascript">
            layui.use('upload', function(){
                var $ = layui.jquery,
                upload = layui.upload;
                upload.render({
                    elem: '#upload_file',
                    url: '/order/upload.do',
                    auto: false,
                    //multiple: true,
                    bindAction: '#upload_btn',
                    done: function(res){
                      console.log(res)
                      if(res.status) {
                      layer.msg(res.message);
                      $("#picUrl").val(res.picName);
                      $("#picRealUrl").val(res.url);
                      $("#upload_file").attr("style", "background:url("+res.url+");background-size: 100%");
                      }else{
                      layer.msg(res.message);
                      }
                    }
                });
            });
            
            layui.use('form', function() {
	            var form = layui.form;
	            //监听提交
	            form.on('submit(formDemo)', function(data){
		           	var picUrl = $("#picUrl").val();
		           	var picRealUrl = $("#picRealUrl").val();
	            	var addType = $("#addType option:selected").val();
	            	var payMethod = $("#payMethod option:selected").val();
	            	var addUserMoblile = $("#addUserMoblile").val();
	            	var paymentAmount = $("#paymentAmount").val();
	            	var orderType = $("#orderType").val();
	            	if(picUrl == '' || picUrl == null) {
	            		layer.alert("请上传凭证");
	            		return false;
	            	}
	            	
	            	if(addType == '' || addType == null) {
	            		layer.alert("请选择加盟类型");
	            		return false;
	            	}
	            	
	            	if(paymentAmount == '' || paymentAmount == null) {
	            		layer.alert("请填写付款金额");
	            		return false;
	            	}
	            	
	            	if(payMethod == '' || payMethod == null) {
	            		layer.alert("请选择支付方式");
	            		return false;
	            	}
	            	
	            	if(addUserMoblile == '' || addUserMoblile == null) {
	            		layer.alert("请输入加盟人手机号");
	            		return false;
	            	}
	            	
	            	jQuery.ajax({
						data : {
							"picUrl":picUrl,
							"picRealUrl":picRealUrl,
							"addType":addType,
							"orderType":orderType,
							"payMethod":payMethod,
							"addUserMoblile":addUserMoblile,
							"paymentAmount":paymentAmount
						},
				        async : false,
				        type : "POST",
				        timeout: 60000,
				        url : "/order/addOrzApply.do",
				        dataType  :"json",
				        exception : function(data){
				        },
				        error : function(data){
				        },
				        success: function(data) {
				        	if(data.status) {
			            		//window.location.href = "/order/uploadSucceed.do";
			            		$("#submitStatus").val("1");
			            		layer.alert(data.message);
			            		return false;
				        	} else {
				        		layer.alert(data.errorMessage);
				        		return false;
				        	}
				        }
				    });
	 			});
            }); 
            
            $("#upload_btn").click(function() {
            	var picUrl = $(".layui-upload-choose").text();
            	if(picUrl == '' || picUrl == null) {
            		layer.alert("请上传凭证");
            		return false;
            	}
            })       
                   
        </script>
    </body>
</html>