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

<script type="text/javascript" src="resources/js/common/Toast.js"></script>
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
			<h1 class="glo_info_model">${userMealRecord.meal.name }</h1>
			<div class="glo_module">
				<article class="glo_info_model">
					<header>
						<h3>基本信息</h3>
					</header>
					<p>
						餐食类目：
						<c:choose>
							<c:when test="${userMealRecord.meal.type eq '1' }">食品类</c:when>
							<c:when test="${userMealRecord.meal.type eq '2' }">饮品类</c:when>
							<c:otherwise>${userMealRecord.meal.type }</c:otherwise>
						</c:choose>
					</p>
					<p>餐食日期：${userMealRecord.meal.mealDate }</p>
					<p>预约数量：<font color="red">${userMealRecord.orderNum } </font></p>
					<%-- <p>剩余数量：<font color="blue">${userMealRecord.meal.orderLimit - userMealRecord.meal.orderNum }</font></p> --%>
					<p>
						是否配送：
						<c:choose>
							<c:when test="${userMealRecord.isDelivery eq '1' }"><font color="red">是</font></c:when>
							<c:otherwise><font color="blue">否</font></c:otherwise>
						</c:choose> 
					</p>
					<p>配送地址：${userMealRecord.deliveryAddress }</p>
				</article>
				<article class="glo_info_model">
					<header>
						<h3>餐食介绍</h3>
					</header>
					<p id="content">${userMealRecord.meal.content }</p>
				</article>
				<input type="hidden" id="id" value="${userMealRecord.id }">
			</div>
			<div style="padding-left: 10px; padding-right: 10px;">
				<a id="cancleBtn" class="btn-big btn-red">取消预约</a>
				<a id="backBtn" style=" margin-top: 15px;" class="btn-big btn-gray">返回</a>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		/**预约餐食**/
		$("#cancleBtn").tap(function(){
			location.href = "meal/cancle/" + $("#id").val();
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