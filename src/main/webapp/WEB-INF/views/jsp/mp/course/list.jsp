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

</head>
<body>
	<div class="hotbody reserve_group_2">
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
					<c:choose>
						<c:when test="${fn:length(courseList) > 0}">
							<c:forEach items="${courseList }" var="course">
								<article id="${course.id }">
									<section>
										<h2>${course.name }</h2>
										<h5>
											<span>${course.beginTime }-${course.endTime }</span>
											<span>${course.coach }</span> 
											<strong> 
												人数：<em>${course.personNum }</em>/${course.personLimit }
											</strong>
										</h5>
									</section>
								</article>
							</c:forEach>
						</c:when>
						<c:otherwise>
							<div class="empty_list"><div class="empty_course"></div><p>今日暂无课程，请选择其他日期！</p></div>
						</c:otherwise>
					</c:choose>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		
		/** 绑定日期链接* */
		$("#nav h3").on('tap', function(e) {
			location.href = "course/list?courseDate=" + this.id;
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