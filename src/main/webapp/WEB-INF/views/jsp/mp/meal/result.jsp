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
<title>餐食预约</title>
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

<script type="text/javascript" src="resources/js/common/WeChat.js"></script>
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
			<article class="glo_info_model"  style=" text-align: center; font-size: 1rem; margin: 2rem auto;">
				<header>
					<h3>${msg }</h3>
				</header>
			</article>
			<div style="padding-left: 10px; padding-right: 10px;">
				<a id="recordBtn" class="btn-big btn-red">查看预约餐食</a>
				<a id="mealBtn" style=" margin-top: 15px;" class="btn-big btn-gray">返回餐食列表</a>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		/**预约餐食记录**/
		$("#recordBtn").tap(function(){
			location.href = "meal/orderRecord";
		});
	
		/**返回**/
		$("#mealBtn").tap(function(){
			location.href = "meal/list";
		});
	</script>
</body>
</html>