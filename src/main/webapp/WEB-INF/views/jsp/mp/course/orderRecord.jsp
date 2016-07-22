<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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
		<div class="list" style=" margin-top: 10px;">
			<div id="content">
				<div id="list">
					<c:choose>
						<c:when test="${fn:length(courseRecordList) > 0}">
							<c:forEach items="${courseRecordList }" var="courseRecord">
								<article id="${courseRecord.id }" style=" height: 230px;">
									<section>
										<h2>${courseRecord.course.name }</h2>
										<h5>
											<span style=" overflow: visible;">
												开课时间：${courseRecord.course.courseDate } ${courseRecord.course.beginTime }-${courseRecord.course.endTime } 
											</span>
											<strong>
												<c:choose>
													<c:when test="${courseRecord.isStandby eq '1' }"><font color="red">候补排队</font></c:when>
													<c:otherwise><font color="blue">预约成功</font></c:otherwise>
												</c:choose>
											</strong>
										</h5>
										<h5>
											<span>教练：${courseRecord.course.coach }</span>
											<span>人数：${courseRecord.course.personNum }</span> 
											<strong> 
												预约时间：<em><fmt:formatDate value="${courseRecord.createTime}" pattern="yyyy-MM-dd"/></em>
											</strong>
										</h5>
									</section>
								</article>
							</c:forEach>
						</c:when>
						<c:otherwise>
							<div class="empty_list"><div class="empty_course"></div><p>暂无预约课程！</p></div>
						</c:otherwise>
					</c:choose>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		/** 绑定列表链接* */
		$("#list article").on('tap', function(e) {
			location.href = "course/orderDetail/" + this.id;
		});
	</script>
</body>
</html>