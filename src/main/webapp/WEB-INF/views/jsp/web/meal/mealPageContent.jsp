<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="diy" tagdir="/WEB-INF/tags" %>

<input type="hidden" id="pageNo" name="pageNo" value="${pageNo }"/>
<input type="hidden" id="pageSize" name="pageSize" value="${pageSize }"/>

<table class="table table-bordered table-hover center mt10">
	<caption></caption>
	<colgroup>
		<col />
		<col />
		<col />
		<col />
		<col />
	</colgroup>
	<thead>
		<tr>
			<th>餐食类型</th>
			<th>餐食名称</th>
			<th>餐食日期</th>
			<th>餐食数量</th>
			<th>操作</th>
		</tr>
	</thead>
	<tbody>
		<c:choose>
			<c:when test="${ !empty page.list }">
				<c:forEach items="${page.list }" var="meal">
					<tr>
						<td>
							<c:choose>
								<c:when test="${meal.type eq '1' }">食品类</c:when>
								<c:when test="${meal.type eq '2' }">饮品类</c:when>
								<c:otherwise>${meal.type }</c:otherwise>
							</c:choose>
						</td>
						<td>${meal.name }</td>
						<td>${meal.mealDate }</td>
						<td>
							<font color="blue">${meal.orderNum }人预定</font> / <font color="red">限制${meal.orderLimit }人</font>
						</td>
						<td>
							<a href="javascript:editMeal('${meal.id}')">编辑</a>
							&nbsp;&nbsp;
							<a href="javascript:deleteMeal('${meal.id}')">删除</a>
							&nbsp;&nbsp;
							<a href="javascript:detailMeal('${meal.id}')">详情</a>
						</td> 
					</tr>
				</c:forEach>
			</c:when>
			<c:otherwise>
				<tr>
					<td colspan="5" class="center">没有查找到符合条件的数据，请重新输入条件并查询！</td>
				</tr>
			</c:otherwise>
		</c:choose>
	</tbody>
</table>

<diy:page formId="searchForm" async="true" pageSize="${page.pageSize}" page="${page}" gotoURI="web/meal/mealPageContent"></diy:page>

<script type="text/javascript">
	$("#formPageNo").val($("#pageNo").val());
	$("#formPageSize").val($("#pageSize").val());
</script>

