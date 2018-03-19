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
            <div class="upload_cons">
                <div class="layui-upload">
                    <button type="button" class="upload_file" id="upload_file"></button>
                    <button type="button" class="upload_btn" id="upload_btn">上传付款凭证</button>
                </div>
            </div>
        </div>
        <script type="text/javascript" src="../../resources/layui/layui.js"></script>
        <script type="text/javascript">
            layui.use('upload', function(){
                var $ = layui.jquery,
                upload = layui.upload;
                upload.render({
                    elem: '#upload_file',
                    url: '/upload/',
                    auto: false,
                    //multiple: true,
                    bindAction: '#upload_btn',
                    done: function(res){
                      console.log(res)
                    }
                });
            });
        </script>
    </body>
</html>