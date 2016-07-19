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
		<div class="main">
			<h1 class="glo_info_model">${userCourseRecord.course.name }</h1>
			<div class="glo_module">
				<article class="glo_info_model">
					<header>
						<h3>基本信息</h3>
					</header>
					<p>开课日期：${userCourseRecord.course.courseDate }</p>
					<p>开课时间：${userCourseRecord.course.beginTime }-${userCourseRecord.course.endTime }</p>
					<!-- <p class="callout2">  </p> -->
					<p>课程教练：${userCourseRecord.course.coach }</p>
					<p>剩余名额：<font color="red">${userCourseRecord.course.personLimit - userCourseRecord.course.personNum }</font></p>
					<p>预约情况：<c:choose>
									<c:when test="${userCourseRecord.isStandby eq '1' }"><font color="blue">预约成功</font></c:when>
									<c:otherwise><font color="red">候补排队</font></c:otherwise>
								</c:choose>
					</p>
				</article>
				<article class="glo_info_model">
					<header>
						<h3>课程介绍</h3>
					</header>
					<p>${userCourseRecord.course.content }</p>
				</article>
				<input type="hidden" id="id" value="${userCourseRecord.id }">
			</div>
			<div style="padding-left: 10px; padding-right: 10px;">
				<a id="cancleBtn" class="btn-big btn-blue">取消预约</a>
				<a id="backBtn" class="btn-big btn-gray">返回</a>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		/**预约课程**/
		$("#cancleBtn").tap(function(){
			location.href = "course/cancle/" + $("#id").val();
		});
	
		/**返回**/
		$("#backBtn").tap(function(){
			history.go(-1);
		});
	</script>
</body>
</html>