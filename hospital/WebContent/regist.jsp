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
    <script type="text/javascript" src="Js/jquery.validate.js"></script>
    <script type="text/javascript" src="Js/messages_zh.js"></script>
    <style type="text/css">
        body {
            padding-top: 140px;
            padding-bottom: 40px;
            background-color: #f5f5f5;
            font-family: "微软雅黑";
            background-color: buttonhighlight;
        }

        .form-signin {
            max-width: 600px;
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
        
        .form-signin .form-signin-heading{
        	margin-bottom: 10px;
            font-size: 24px;
            margin-left: 200px;
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
			font-size: 14px;
		}
		
    </style>  
</head>
<body>
<div class="container">	
    <form class="form-signin" method="post" action="user">
    	<input type="hidden" name="func" value="registUser">
        <h2 class="form-signin-heading" >管理员注册</h2>
                        姓&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名：
		<input type="text" id="uname" name="uname" class="input-block-level" placeholder="姓名">
		<span id="unameSpan"></span>
		<br/>
                        密&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;码：
        <input id="password" type="password" name="password" class="input-block-level" placeholder="密码">
        <span id="passwordSpan"></span>
        <br/>
             	确认密码：
        <input type="password" id="cpwd" name="password2" class="input-block-level" placeholder="确认密码">
        <span id="cpwdSpan"></span>
        <br/>
                        用&nbsp;&nbsp;户&nbsp;&nbsp;名：
        <input type="text" id="username" name="username" class="input-block-level" placeholder="用户名">
        <span id="usernameSpan"></span>
        <br/>
                        邮&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;箱：
        <input type="text" id="email" name="email" class="input-block-level" placeholder="邮箱">
        <span id="emailSpan"></span>
        <br/>               
        <p style="text-align: center;">
        <input id="regist" type="button" value="注册" name="login" class="btn btn-large btn-info" style="width: 100px;"/>
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        <input id="reset" type="button" value="清空" name="login" class="btn btn-large btn-info" style="width: 100px;"/>
        </p>
    </form>
</div>

<script type="text/javascript">
	//姓名的非空校验
	var unameSucc = false;
	$("#uname").blur(function() {
		//获取输入的姓名
		var uname = $(this).val();
		if(uname == null || uname == "") {
			$("#unameSpan").text("姓名不能为空").css("color", "red");
		} else {
			$("#unameSpan").text("姓名ok").css("color", "green");
			unameSucc = true;
		}
	})
	//密码的非空校验
	var passwordSucc = false;
	$("#password").blur(function() {
		//获取输入的密码
		var password = $(this).val();
		if(password == null || password == "") {
			$("#passwordSpan").text("密码不能为空").css("color", "red");
		} else {
			$("#passwordSpan").text("密码ok").css("color", "green");
			passwordSucc = true;
		}
	})
	//确认密码的校验
	var cpwdSucc = false;
	$("#cpwd").blur(function() {
		//获取两次输入的密码
		var pwd1 = $("#password").val();
		var pwd2 = $(this).val();
		//非空校验
		if(pwd2 == null || pwd2 == "") {
			$("#cpwdSpan").text("确认密码不能为空").css("color", "red");
		} else {
			//一致性校验
			if(pwd1 != pwd2) {
				$("#cpwdSpan").text("两次密码不一致").css("color", "red");
			} else {
				$("#cpwdSpan").text("确认密码ok").css("color", "green");
				cpwdSucc = true;
			}
		}
	})
	//用户名的校验
	var usernameSucc = false;
	$("#username").blur(function() {
		//获取用户名
		var username = $(this).val();
		//非空校验
		if(username == null || username == "") {
			$("#usernameSpan").text("用户名不能为空").css("color", "red");
		} else {
			//使用Ajax异步交互技术
			$.ajax({
				url: "user",	//请求的路径
				type: "post",	//请求的方式
				data: {"func": "checkUsername", "username": username},	//请求中携带的数据
				dataType: "json",	//指定后台返回数据的类型
				success: function(isRegist) {
					if(isRegist) {
						$("#usernameSpan").text("用户名已注册").css("color", "red");
					} else {
						$("#usernameSpan").text("用户名ok").css("color", "green");
						usernameSucc = true;
					}
				}//Ajax请求成功的回调函数
			})
		}
	})
	//邮箱的校验
	var emailSucc = false;
	$("#email").blur(function() {
		//获取邮箱
		var email = $(this).val();
		//非空校验
		if(email == null || email == "") {
			$("#emailSpan").text("邮箱不能为空").css("color", "red");
		} else {
			//正则校验
			var myreg = /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
			if(!myreg.test(email)) {
				$("#emailSpan").text("邮箱格式不正确").css("color", "red");
			} else {
				$.ajax({
					url: "user",	//请求的路径
					type: "post",	//请求的方式
					data: {"func": "checkEmail", "email": email},	//请求中携带的数据
					dataType: "json",	//指定后台返回数据的类型
					success: function(isRegist) {
						if(isRegist) {
							$("#emailSpan").text("邮箱已注册").css("color", "red");
						} else {
							$("#emailSpan").text("邮箱ok").css("color", "green");
							emailSucc = true;
						}
					}//Ajax请求成功的回调函数
				})
			}
		}
	})
	//注册按钮
	$("#regist").click(function() {
		//当所有验证通过后提交表单
		if(unameSucc && passwordSucc && cpwdSucc && usernameSucc && emailSucc) {
			//alert("即将注册");
			$("form").submit();		//提交表单
		} else {
			alert("请先通过校验再注册！");
		}
	})
	//清空数据
	$("#reset").click(function() {
		//清空输入框
		$(".input-block-level").val("");
		//清空span
		$("span").text("");
		//校验的Boolean全部改为false
		unameSucc = false;
		passwordSucc = false;
		cpwdSucc = false;
		usernameSucc = false;
		emailSucc = false;
	})
</script>
</body>
</html>










