<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <title>推荐人</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
        <meta name="format-detection" content="telephone=no">
    	<meta name="apple-mobile-web-app-capable" content="yes">
    	<script type="text/javascript" src="../../resources/js/jquery-2.1.1.min.js"></script>
        <link rel="stylesheet" type="text/css" href="../../resources/css/carRental.css">
    </head>
    <body>
    	<div class="container">
            <header>
                <a class="return_arr" href="personal.html"></a>
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
                            <span>艾多多</span>
                        </div>
                        <div class="reference_info outer">
                            <p>手机号</p>
                            <span>15966668888</span>
                        </div>
                    </li>
                    <li style="display:none;">
                        <div class="table">
                            <table border="0" cellspacing="0" cellpadding="0">
                                <thead>
                                    <tr>
                                        <th>序号</th>
                                        <th>姓名</th>
                                        <th>手机号</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr>
                                        <td>1</td>
                                        <td>兔斯基</td>
                                        <td>15012341718</td>
                                    </tr>
                                    <tr>
                                        <td>2</td>
                                        <td>兔斯基</td>
                                        <td>15012341718</td>
                                    </tr>
                                    <tr>
                                        <td>3</td>
                                        <td>兔斯基</td>
                                        <td>15012341718</td>
                                    </tr>
                                </tbody>
                            </table>
                        </div>
                    </li>
                </ol>
            </div>
        </div>
        <script type="text/javascript">
            $(".reference_cons>ul>li").click(function(event) {
                $(this).addClass('reference_current').siblings().removeClass('reference_current');
                $(".reference_cons>ol>li").eq($(this).index()).show().siblings().hide();
            });
        </script>
    </body>
</html>