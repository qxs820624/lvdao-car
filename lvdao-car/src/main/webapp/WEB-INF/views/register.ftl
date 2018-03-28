<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
    	<title>注册</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
        <meta name="format-detection" content="telephone=no">
    	<meta name="apple-mobile-web-app-capable" content="yes">
    	<script type="text/javascript" src="http://hangong.oss-cn-shanghai.aliyuncs.com/wx/js/jquery-2.1.0.min.js" ></script>
    	<script type="text/javascript" src="../resources/layer/layer.js?ver=${version}"></script>
    	<link rel="stylesheet" type="text/css" href="../resources/layer/skin/default/layer.css?ver=${version}">
    	<link rel="stylesheet" type="text/css" href="../resources/css/style.css?ver=${version}">
    </head>
    <body>
    	<div class="container">
    		<div class="swiper-container">
			    <div class="swiper-wrapper">
			        <div class="swiper-slide">
			        	<a href="#">
			        		<img src="../..//resources/images/banner.png" alt="1"/>
			        	</a>
			        </div>
			    </div>
			</div>	
    		<div class="content" style="padding-bottom:10px;">
	    		<div class="logo"></div>
				<div class="login_con">
                    <div class="login_con_main" style="margin-top: 2rem;">
                        <div class="login_line phone">
                            <input class="phone_num" id="userMobile" type="text" placeholder="请输入手机号">
                            <input class="code code_btn code-btn" id="sendCode" readonly value="获取验证码" style="color:#fff;">
                        </div>
                        <div class="login_line code">
                            <input type="text" id="smsCode" placeholder="请输入验证码">
                        </div>
                        <div class="login_line phone">
							<input class="phone_num" id="userParentName" type="text" placeholder="推荐人手机号" value="<#if userParentName??>${userParentName}</#if>">
						</div>
					</div>
                    <div class="login_btn">
                        <a href="javascript:void(0);" id="register">立即注册</a>
                    </div>        
                </div>
			</div>	
    	</div>
    	<script type="text/javascript">	
    	//获取验证码时间
        var text = "重新获取";
        var wait = 60;  
        function time(o) {  
            if (wait == 0) {
                o.removeAttr("disabled");            
                o.val("重新获取");  
                wait = 60;  
            } else {
                o.attr("disabled", true);  
                o.val(text+"("+wait+")");
                wait--;  
                setTimeout(function() {  
                    time(o)  
                },  
                1000)  
            }
            $(".input_password").show(); 
        }  
        
        $("#sendCode").click(function(){
			var mobile = $("#userMobile").val();
			if(!validateMobile(mobile)){
				return;
			}
			jQuery.ajax({
		  		data : {
		  			'mobile': mobile
		  		},
		        async : false,
		        type : "POST",
		        timeout: 60000,
		        url : "/user/sendCode.do",
		        dataType  :"json",
		        success: function(data) {
		        	if(data.result == "S"){
		        		time($("#sendCode"));
		        	}
		        }
		     });
		});

        function validateMobile(mobile){ 
            var mobile = $("#userMobile").val();
            if(mobile.length==0) 
            { 
                alert('请输入手机号码！'); 
                return false; 
            }     
            if(mobile.length!=11) 
            { 
                alert('请输入有效的手机号码！'); 
                return false; 
            } 

            var myreg = /^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1}))+\d{8})$/; 
            if(!myreg.test(mobile)) 
            { 
                alert('请输入有效的手机号码！'); 
                return false; 
            }
            return true;
        } 
    	
		$("#register").on("click", function(){
			var mobile = $("#userMobile").val();
			var sendCode = $("#smsCode").val();
			var userParentName = $("#userParentName").val();
			
			if(mobile == "" || mobile == null || mobile == undefined){
				 alert("手机号码不能为空");  
				 return false;
			}
			mobile = mobile.trim();
			
			if(sendCode == "" || sendCode == null){
				alert("验证码不能为空");
				return false;
			}
			sendCode = sendCode.trim();
			
			jQuery.ajax({
		  		data : {
		  			'mobile':mobile,
		  			'sendCode' : sendCode,
		  			'userParentName' : userParentName
		  		},
		        async : false,
		        type : "POST",
		        timeout: 60000,
		        url : "/user/userRegister.do",
		        dataType  :"json",
		        success: function(data) {
		        	if(data.status){
		        		alert(data.message);
		        	}else{
		        		alert(data.errorMessage);
		        		return false;
		        	}
		        }
		     });
			
		});
	  </script>
    </body>
</html>