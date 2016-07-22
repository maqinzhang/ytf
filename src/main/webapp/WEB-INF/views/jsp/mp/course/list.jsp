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

<script type="text/javascript" src="resources/js/lib/jquery/jquery.min.js"></script>

<script type="text/javascript" src="resources/js/lib/datepicker/datepicker.min.js" charset="utf-8"></script>
<script type="text/javascript" src="resources/js/lib/datepicker/datepicker.zh-CN.js"></script>

<script type="text/javascript" src="resources/js/lib/zepto/zepto.min.js"></script>
<script type="text/javascript" src="resources/js/lib/zepto/event.js"></script>
<script type="text/javascript" src="resources/js/lib/zepto/touch.js"></script>

<script type="text/javascript" src="resources/js/common/date.js"></script>
<script type="text/javascript" src="resources/js/common/WeChat.js"></script>

</head>
<body>
	<div class="hotbody reserve_group_2">
		<div class="logo">
			<div style=" width: 30%;  padding-left: 20px;" >
				<img alt="YTF" src="resources/img/logo_left.png">
			</div>
			<div style=" width: 70%; text-align: right; padding-right: 20px; ">
				<img alt="CROSSFIT" src="resources/img/logo_right.png">
			</div>
		</div>
		<nav id="nav">
			<c:forEach items="${dateList }" var="date" varStatus="status">
				<h3 <c:if test="${status.index eq 0 }">class="on"</c:if> id="${fn:substring(date, 0, 10) }">
					${fn:substring(date, 5, fn:length(date)) }<em></em>
				</h3>
			</c:forEach>
			<aside data-toggle="datepicker">
				<em></em>
			</aside>
		</nav>
		<div class="list">
			<div id="content">
				<div id="list">
					<jsp:include page="listContent.jsp"></jsp:include>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		
		/** 绑定日期链接* */
		$("#nav h3").on('tap', function(e) {
			
			var courseDate = this.id;
			
			/** 加载列表数据  **/
			$.ajax({
				url : $("base").attr("href") + "course/listContent",
				type : "post",
				data : {
					courseDate : courseDate
				},
				dataType : "html",
				success : function(data) {
					
					$("#nav .on").removeClass("on");
					$("#"+ courseDate).addClass("on");
					$("#list").html(data);
					
					/** 绑定列表链接* */
					$("#list article").on('tap', function(e) {
						location.href = "course/detail/" + this.id;
					});
				}
			});
			//location.href = "course/list?courseDate=" + this.id;
		});
		
		/** 绑定列表链接* */
		$("#list article").on('tap', function(e) {
			location.href = "course/detail/" + this.id;
		});
	
		$('[data-toggle="datepicker"]').datepicker({
			language : 'zh-CN',
			autohide : true,
			startDate: new Date()
		});
		
		$('[data-toggle="datepicker"]').on('pick.datepicker', function(e) {
			var courseDate = e.date.format("yyyy-MM-dd");
			location.href = "course/list?courseDate=" + courseDate;
			e.preventDefault();
		});
	</script>
</body>
</html>