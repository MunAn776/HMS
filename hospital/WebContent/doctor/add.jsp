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
    <title>添加医生</title>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="../Css/bootstrap.css" />
    <link rel="stylesheet" type="text/css" href="../Css/bootstrap-responsive.css" />
    <link rel="stylesheet" type="text/css" href="../Css/style.css" />
    <script type="text/javascript" src="../Js/jquery.js"></script>
    <script type="text/javascript" src="../Js/jquery.sorted.js"></script>
    <script type="text/javascript" src="../Js/bootstrap.js"></script>
    <script type="text/javascript" src="../Js/ckform.js"></script>
    <script type="text/javascript" src="../Js/common.js"></script>
    <script type="text/javascript" src="../Js/ckeditor/ckeditor.js"></script>
 	<script type="text/javascript" src="../Js/My97DatePicker/WdatePicker.js"></script>

    <style type="text/css">
        body {
            padding-bottom: 40px;
        }
        .sidebar-nav {
            padding: 9px 0;
        }

        @media (max-width: 980px) {
            /* Enable use of floated navbar text */
            .navbar-text.pull-right {
                float: none;
                padding-left: 5px;
                padding-right: 5px;
            }
        }


    </style>
    <script type="text/javascript">
    $(function () {       
		$('#backid').click(function(){
				window.location.href="${path}doctor?func=findAllDoctors&currentPage=1"; //
		 });
    });
    </script>
</head>
<body>

<form action="${path }/doctor" method="post" class="definewidth m20">
<input type="hidden" name="func" value="insertDoctor">

<table class="table table-bordered table-hover definewidth m10">
    <tr>
        <td width="10%" class="tableleft">姓名</td>
        <td><input type="text" id="dname" name="dname"/><span id="dnameSpan"></span></td>
    </tr>
    
    <tr>
        <td width="10%" class="tableleft">身份证号</td>
        <td><input type="text" id="cardno" name="cardno"/><span id="cardnoSpan"></span></td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">手机</td>
        <td><input type="text" id="phone" name="phone"/><span id="phoneSpan"></span></td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">性别</td>
        <td><input type="radio" name="gender" value="1" checked/>男&nbsp;&nbsp;&nbsp;
        <input type="radio" name="gender"  value="0"/>女</td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">出生年月</td>
        <td><input type="text" id="birthday" name="birthday" class="Wdate" onclick="WdatePicker({el:this,dateFmt:'yyyy-MM-dd HH:mm:ss'})" value=""/>
        	<span id="birthdaySpan"></span>
        </td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">年龄</td>
        <td><input type="text" id="age" name="age"/><span id="ageSpan"></span></td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">电子邮箱</td>
        <td><input type="text" id="email" name="email"/><span id="emailSpan"></span></td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">所属科室</td>
        <td>
        	<select name="department">
        		<option value="1">急诊科</option>
        		<option value="2">儿科</option>
        		<option value="3">妇科</option>
        		<option value="4">皮肤科</option>
        		<option value="5">内分泌科</option>
        		<option value="6">牙科</option>
        	</select>
        </td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">学历</td>
        <td>
        	<select name="degree">
        		<option value="1">专科</option>
        		<option value="2">本科</option>
        		<option value="3">研究生</option>
        		<option value="4">博士</option>
        	</select>
        </td>
    </tr>
	<tr>
        <td width="10%" class="tableleft">备注</td>
        <td><textarea name="mark"></textarea></td>
	</tr>
    <tr>
        <td colspan="2">
			<center>
			<input name="save" id="save" type="submit" class="btn btn-primary" value="保存"/>
			 &nbsp;&nbsp;
			 <button type="button" class="btn btn-success" name="backid" id="backid">返回列表</button>
			</center>
		</td>
    </tr>
</table>
</form>

<script>
	//姓名
	var doctornameSucc = false;
	$("#dname").blur(function() {
		var dname = $(this).val();
		//非空校验
		if(dname == null || dname == "") {
			$("#dnameSpan").text("姓名不能为空").css("color", "red");
		} else {
			$("#dnameSpan").text("");
			doctornameSucc = true;
		}
	})
	//身份证
	var cardnoSucc = false;
	$("#cardno").blur(function() {
		//获取输入的密码
		var cardno = $(this).val();
		if(cardno == null || cardno == "") {
			$("#cardnoSpan").text("身份证号不能为空").css("color", "red");
		} else {
			//使用Ajax异步交互技术
			$.ajax({
				url: "${path }doctor",	//请求的路径
				type: "post",	//请求的方式
				data: {"func": "checkCardno", "cardno": cardno},	//请求中携带的数据
				dataType: "json",	//指定后台返回数据的类型
				success: function(isRegist) {
					if(isRegist) {
						$("#cardnoSpan").text("身份证号已存在").css("color", "red");
					} else {
						$("#cardnoSpan").text("");
						cardnoSucc = true;
					}
				}//Ajax请求成功的回调函数
			})
		}
	})
	
	//手机
	var phoneSucc = false;
	$("#phone").blur(function() {
		var phone = $(this).val();
		//非空校验
		if(phone == null || phone == "") {
			$("#phoneSpan").text("手机号不能为空").css("color", "red");
		} else {
			//使用Ajax异步交互技术
			$.ajax({
				url: "${path }doctor",	//请求的路径
				type: "post",	//请求的方式
				data: {"func": "checkPhone", "phone": phone},	//请求中携带的数据
				dataType: "json",	//指定后台返回数据的类型
				success: function(isRegist) {
					if(isRegist) {
						$("#phoneSpan").text("手机号已存在").css("color", "red");
					} else {
						$("#phoneSpan").text("");
						phoneSucc = true;
					}
				}//Ajax请求成功的回调函数
			})
		}
	})
	
	//出生年月
	var birthdaySucc = false;
	$("#birthday").blur(function() {
		var birthday = $(this).val();
		//非空校验
		if(birthday == null || birthday == "") {
			$("#birthdaySpan").text("出生日期不能为空").css("color", "red");
		} else {
			$("#birthdaySpan").text("");
			birthdaySucc = true;
		}
	})
	
	//年龄
	var ageSucc = false;
	$("#age").blur(function() {
		var age = $(this).val();
		//非空校验
		if(age == null || age == "") {
			$("#ageSpan").text("年龄不能为空").css("color", "red");
		} else {
			$("#ageSpan").text("");
			ageSucc = true;
		}
	})
	
	//邮箱
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
				//使用Ajax异步交互技术
				$.ajax({
					url: "${path }doctor",	//请求的路径
					type: "post",	//请求的方式
					data: {"func": "checkEmail", "email": email},	//请求中携带的数据
					dataType: "json",	//指定后台返回数据的类型
					success: function(isRegist) {
						if(isRegist) {
							$("#emailSpan").text("邮箱已存在").css("color", "red");
						} else {
							$("#emailSpan").text("");
							emailSucc = true;
						}
					}//Ajax请求成功的回调函数
				})
			}
		}
	})
	//保存
	$("#save").click(function() {
		//当所有验证通过后提交表单
		if(doctornameSucc && cardnoSucc && phoneSucc && birthdaySucc && ageSucc && emailSucc) {
			//alert("即将注册");
			$("form").submit();		//提交表单
		} else {
			alert("请先通过校验再添加！");
		}
	})
</script>

</body>
</html>