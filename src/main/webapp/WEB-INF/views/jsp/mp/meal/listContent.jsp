<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%> 

<c:choose>
	<c:when test="${fn:length(mealList) > 0}">
		<c:forEach items="${mealList }" var="meal">
			<article id="${meal.id }">
				<section>
					<h2>${meal.name }</h2>
					<h5>
						<span>
							<c:choose>
								<c:when test="${meal.type eq '1' }">食品类</c:when>
								<c:when test="${meal.type eq '2' }">饮品类</c:when>
								<c:otherwise>${meal.type }</c:otherwise>
							</c:choose>
						</span>
						<strong> 
							人数：<em>${meal.orderNum }</em>/${meal.orderLimit }
						</strong>
					</h5>
				</section>
			</article>
		</c:forEach>
	</c:when>
	<c:otherwise>
		<div class="empty_list"><div class="empty_course"></div><p>今日暂不提供餐食，请选择其他日期！</p></div>
	</c:otherwise>
</c:choose>