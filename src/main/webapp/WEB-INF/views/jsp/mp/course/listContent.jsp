<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%> 

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