<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%> 
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>">
<title>课程预约</title>
<link rel="shortcut icon" type="image/x-icon" href="favicon.ico" media="screen" />
<meta content="Home" http-equiv="description">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="format-detection" content="telephone=no">
<meta content="width=750, user-scalable=no, target-densitydpi=device-dpi" name="viewport">
<link href="resources/css/style.css" type="text/css" rel="stylesheet">

<script type="text/javascript" src="resources/js/lib/zepto/zepto.min.js"></script>
<script type="text/javascript" src="resources/js/lib/zepto/event.js"></script>
<script type="text/javascript" src="resources/js/lib/zepto/touch.js"></script>

<script type="text/javascript" src="resources/js/common/Toast.js"></script>
</head>
<body>
	<div class="hotbody arena_info">
		<div class="logo">
			<div style=" width: 30%;  padding-left: 20px;" >
				<img alt="YTF" src="resources/img/logo_left.png">
			</div>
			<div style=" width: 70%; text-align: right; padding-right: 20px; ">
				<img alt="CROSSFIT" src="resources/img/logo_right.png">
			</div>
		</div>
		<div class="main">
			<h1 class="glo_info_model">${course.name }</h1>
			<div class="glo_module">
				<article class="glo_info_model">
					<header>
						<h3>基本信息</h3>
					</header>
					<p>开课日期：${course.courseDate }</p>
					<p>开课时间：${course.beginTime }-${course.endTime }</p>
					<!-- <p class="callout2">  </p> -->
					<p>课程教练：${course.coach }</p>
					<p>剩余名额：<font color="red">${course.personLimit - course.personNum }</font></p>
					<input type="hidden" id="personRemainder" value="${course.personLimit - course.personNum }"/>
				</article>
				<article class="glo_info_model">
					<header>
						<h3>课程介绍</h3>
					</header>
					<p id="content">${course.content }</p>
				</article>
				<input type="hidden" id="id" value="${course.id }">
			</div>
			<div style="padding-left: 10px; padding-right: 10px;">
				<a id="addBtn" class="btn-big btn-blue">预约课程</a>
				<a id="backBtn" class="btn-big btn-gray">返回</a>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		/**预约课程**/
		$("#addBtn").tap(function(){
			/* if($("#personRemainder").val() == '0'){
				Toast("已达到最大报名人数！")
				return false;
			} */
			location.href = "course/order/" + $("#id").val();
		});
	
		/**返回**/
		$("#backBtn").tap(function(){
			history.go(-1);
		});
		
		/**替换textarea换行**/
		var reg = new RegExp("\n", "gi");  
		$("#content").html($("#content").html().replace(reg,"<br/>"));
	</script>
</body>
</html>