<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String path=request.getScheme()+"://"+request.getServerName()+":"+
	request.getServerPort()+request.getContextPath()+"/";
	pageContext.setAttribute("path", path);
%>
<!DOCTYPE html>
<html>
<head>
    <title>优就业-线医疗管理系统</title>
	<meta charset="UTF-8">
	<link rel="icon" href="Images/logo_favicon.ico" type="image/x-icon" />
   <link rel="stylesheet" type="text/css" href="Css/bootstrap.css" />
    <link rel="stylesheet" type="text/css" href="Css/bootstrap-responsive.css" />
    <link rel="stylesheet" type="text/css" href="Css/style.css" />
    <script type="text/javascript" src="Js/jquery.js"></script>
    <script type="text/javascript" src="Js/jquery.sorted.js"></script>
    <script type="text/javascript" src="Js/bootstrap.js"></script>
    <script type="text/javascript" src="Js/ckform.js"></script>
    <script type="text/javascript" src="Js/common.js"></script>
    <script type="text/javascript" src="Js/jquery-3.4.1.js"></script>
    <style type="text/css">
        body {
            padding-top: 140px;
            padding-bottom: 40px;
            background-color: #f5f5f5;
            font-family: "微软雅黑";
            background: url("Images/yy.jpg");
            background-size: 100%;
            background-repeat: no-repeat;
        }

        .form-signin {
            max-width: 400px;
            padding: 19px 29px 29px;
            margin: 0 auto 20px;
            background-color: #fff;
            border: 1px solid #e5e5e5;
            -webkit-border-radius: 5px;
            -moz-border-radius: 5px;
            border-radius: 5px;
            -webkit-box-shadow: 0 1px 2px rgba(0, 0, 0, .05);
            -moz-box-shadow: 0 1px 2px rgba(0, 0, 0, .05);
            box-shadow: 0 1px 2px rgba(0, 0, 0, .05);
            background: rgba(255,255,255,0.5);
        }

        .form-signin .form-signin-heading,
        .form-signin .checkbox {
            margin-bottom: 10px;
            font-size: 24px;
            margin-left: 90px;
        }

        .form-signin input[type="text"],
        .form-signin input[type="password"] {
            font-size: 16px;
            height: auto;
            margin-bottom: 15px;
            padding: 7px 9px;
        }
		
		
		#message{
			font-size: 14px;
			color:red;
			margin-left: 40px;
		}
		
		.input-block-level{
			width: 300px;
			margin-left: 40px;
		}
		.input-medium{
			margin-left: 40px;
		}
		.code_images{
			width: 115px;
			height: 35px;
			margin-top: -15px;
			margin-left: 10px;
		}
		.error{
			color: red;
			font-size: 12px;
		}
		
    </style>  
</head>
<body>
<div class="container">
	
    <form class="form-signin" method="post" action="user">
    	<input type="hidden" name="func" value="checkLogin">
        <h2 class="form-signin-heading" >在线医疗管理系统</h2>
        <span id="message" class="message">${msg }</span><br>
        <input type="text" id="username" name="username" class="input-block-level" placeholder="账号">
        <input type="password" id="password" name="password" class="input-block-level" placeholder="密码" >
        <input type="text" id="key" name="verify" class="input-medium" placeholder="验证码"> 
        <img id="code" class="code_images"  src="${path }authImage" /> 
		<!--  
			验证码功能参考：
			https://www.cnblogs.com/jianlun/articles/5553452.html
		-->
        <p style="text-align: center;">
        <input id="login" type="button" value="登录" name="login" class="btn btn-large btn-primary" style="width: 150px;"/>
        <a href="regist.jsp">注册</a>
        </p>
    </form>
</div>	
<script type="text/javascript">
	//用户名
	var usernameSucc = false;
	$("#username").blur(function() {
		//非空校验
		var username = $(this).val();
		//非空校验
		if(username == null || username == "") {
			$("#message").text("用户名不能为空").css("color", "red");
		} else {
			//使用Ajax异步交互技术
			$.ajax({
				url: "user",	//请求的路径
				type: "post",	//请求的方式
				data: {"func": "checkUsernameIsRegist", "username": username},	//请求中携带的数据
				dataType: "json",	//指定后台返回数据的类型
				success: function(isRegist) {
					if(isRegist) {
						$("#message").text("");
						usernameSucc = true;
					} else {
						$("#message").text("用户名还未注册").css("color", "red");
					}
				}//Ajax请求成功的回调函数
			})
		}
	})
	//密码校验
	var passwordSucc = false;
	$("#password").blur(function() {
		var password = $(this).val();
		if(password == null || password == "") {
			$("#message").text("密码不能为空").css("color", "red");
		} else {
			$("#message").text("");
			passwordSucc = true;
		}
	})
	
	//图形验证码的刷新
	$("#code").click(function() {
		//利用authImage的Servlet类加上系统时间进行刷新
		$(this).prop("src", "${path }authImage?date=" + new Date());
	})
	//验证码校验
	var keySucc = false;
	$("#key").blur(function() {
		var newKey = $(this).val();
		if(newKey == null || newKey == "") {
			$("#message").text("验证码不能为空").css("color", "red");
		} else {
			//使用Ajax异步交互技术
			$.ajax({
				url: "user",	//请求的路径
				type: "post",	//请求的方式
				data: {"func": "checkKey", "newKey": newKey},	//请求中携带的数据
				dataType: "json",	//指定后台返回数据的类型
				success: function(isRight) {
					if(isRight) {
						$("#message").text("");
						keySucc = true;
					} else {
						$("#message").text("验证码输入错误，请重新输入！").css("color", "red");
					}
				}//Ajax请求成功的回调函数
			})
		}
	})
	//登录按钮
	$("#login").click(function() {
		if(usernameSucc && passwordSucc && keySucc) {
			$("form").submit();		//提交表单
		} else {
			alert("请输入正确的信息!");
		}
	})
</script>
</body>
</html>







