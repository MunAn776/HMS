<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
	String path=request.getScheme()+"://"+request.getServerName()+":"+
	request.getServerPort()+request.getContextPath()+"/";
	pageContext.setAttribute("path", path);
%>
<!DOCTYPE html>
<html>
<head>
	<base href="<%=this.getServletContext().getContextPath() %>/doctor/">
    <title>门诊医生</title>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="../Css/bootstrap.css" />
    <link rel="stylesheet" type="text/css" href="../Css/bootstrap-responsive.css" />
    <link rel="stylesheet" type="text/css" href="../Css/style.css" />
    <script type="text/javascript" src="../Js/jquery.js"></script>
    <script type="text/javascript" src="../Js/jquery.sorted.js"></script>
    <script type="text/javascript" src="../Js/bootstrap.js"></script>
    <script type="text/javascript" src="../Js/ckform.js"></script>
    <script type="text/javascript" src="../Js/common.js"></script>
    <script type="text/javascript" src="../Js/jquery-3.4.1.js"></script>

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

		.table td, .table th {
			text-align: center;
		}

    </style>
    <script type="text/javascript">
    	
    </script>
</head>
<body>

<form action="${path }doctor?method=findDoctorsByPage" method="post" class="definewidth m20">
<table class="table table-bordered table-hover definewidth m10">
	<tr>
	  <td width="10%" class="tableleft">医生姓名：</td>
	  <td><input type="text" id="name" name="name" value=""/></td>		
	  <td width="10%" class="tableleft">科室：</td>
	  <td>
	    <select name="department" id="department">
	        <option value="0" >==请选择==</option>
	        <option value="1" >急诊科</option>
	        <option value="2" >儿科</option>
	        <option value="3" >妇科</option>
	        <option value="4" >皮肤科</option>
	        <option value="5" >内分泌科</option>
	        <option value="6" >牙科</option>
        </select>
	  </td>
	</tr>
	<tr>
	  <td colspan="6">
	    <center>
			<input id="find" name="find" type="submit" class="btn btn-primary" value="查询"/>
			<input id="ret" name="ret" type="button" class="btn btn-primary" value="清空"/>
		</center>
	  </td>
	 </tr>
</table>
</form>
   
<table class="table table-bordered table-hover definewidth m10" >
   <thead>
    <tr>
    	<th><input type="checkbox" id="checkall" onChange="checkall();"></th>
        <th>医生编号</th>
        <th>医生姓名</th>
        <th>联系方式</th>
        <th>所属科室</th>
        <th>操作</th>
    </tr>
    </thead>
    <tbody>
    	 
    	 <c:forEach items="${doctors }" var="doctor">
    	 	<tr>
		    	<td><input type="checkbox" class="one" value="${doctor.did }"></td>
		        <td>${doctor.cardno }</td>
		        <td>${doctor.dname }</td>
		        <td>${doctor.phone }</td>
		        <td>
			        <%-- 
			        	<c:if test="${doctor.department == 1 }">急诊科</c:if>
			        	<c:if test="${doctor.department == 2 }">儿科</c:if>
			        	<c:if test="${doctor.department == 3 }">妇科</c:if>
			        	<c:if test="${doctor.department == 4 }">皮肤科</c:if>
			        	<c:if test="${doctor.department == 5 }">内分泌科</c:if>
			        	<c:if test="${doctor.department == 6 }">牙科</c:if>
			        --%>
			        <c:choose>
			        	<c:when test="${doctor.department == 1 }">急诊科</c:when>
			        	<c:when test="${doctor.department == 2 }">儿科</c:when>
			        	<c:when test="${doctor.department == 3 }">妇科</c:when>
			        	<c:when test="${doctor.department == 4 }">皮肤科</c:when>
			        	<c:when test="${doctor.department == 5 }">内分泌科</c:when>
			        	<c:when test="${doctor.department == 6 }">牙科</c:when>
			        	<c:otherwise>没有该科室</c:otherwise>
			        </c:choose>
		        </td>
		        <td>
		        	<!-- 
		        		${path }	为了保证路径的正确
		        		doctor		是Servlet的访问路径
		        		func		是当前执行的方法
		        		did			是医生的唯一标识
		        	 -->
		        	<a href="${path }doctor?func=lookDoctor&did=${doctor.did}">详情>></a>&nbsp;
		        	<a href="${path }doctor?func=findDoctorByDid&did=${doctor.did }">修改>></a>
		        </td>
		    </tr>
    	 </c:forEach>
    	 
	 </tbody>
  </table>
  
  <table class="table table-bordered table-hover definewidth m10" >
  	<tr>
  		<th colspan="5">  <div class="inline pull-right page">
          <a href='${path }doctor?func=findAllDoctors&currentPage=1'>首页</a> 
          <a href='${path }doctor?func=findAllDoctors&currentPage=${pageTool.lastPage }'>上一页</a>
          <a href='${path }doctor?func=findAllDoctors&currentPage=${pageTool.nextPage }'>下一页</a> 
          <a href='${path }doctor?func=findAllDoctors&currentPage=${pageTool.pageCount }'>尾页</a>
		  &nbsp;&nbsp;&nbsp;共<span class='current'> ${pageTool.totalCount } </span>条记录
		  <span class='current'> ${pageTool.currentPage }/${pageTool.pageCount } </span>页
		  
		  </div>
		 <div>
		 <button type="button" class="btn btn-success" id="newNav">添加新医生</button>
		 <button type="button" class="btn btn-success" id="delAll">批量删除</button>
		 </div>
		 
		 </th>
	</tr>
  </table> 
  <script>
	//医生添加的跳转
	$("#newNav").click(function() {
		window.location = "add.jsp";
	})
		
	//批量删除按钮的点击事件
	$("#delAll").click(function() {
		//创建一个ids字符串
		var ids = "";
		//找到复选框完成遍历获得id
		$(".one:checked").each(function() {
			ids = ids + "," +$(this).val();
		})
		//为了避免没有选择医生发送请求导致的错误
		if(ids == "") {
			alert("请先选择要删除的医生");
			return
		}
		//增强用户体验
		if(confirm("您确定要删除吗？")) {
			//截取第一个逗号
			ids = ids.substring(1);
			//请求Servlet传递id字符串进行批量删除 doctor是Servlet路径，func是当前的方法，ids是保存要删除医生的id字符串
			window.location = "${path }doctor?func=deleteDoctor&ids=" + ids;
		}
	})
  </script> 
</body>
</html>
