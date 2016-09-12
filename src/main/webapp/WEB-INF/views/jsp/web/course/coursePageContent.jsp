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
		<col />
		<col />
	</colgroup>
	<thead>
		<tr>
			<th>课程名称</th>
			<th>开课日期</th>
			<th>开始时间</th>
			<th>结束时间</th>
			<th>教练</th>
			<th>人数</th>
			<th>操作</th>
		</tr>
	</thead>
	<tbody>
		<c:choose>
			<c:when test="${ !empty page.list }">
				<c:forEach items="${page.list }" var="course">
					<tr>
						<td>${course.name }</td>
						<td>${course.courseDate }</td>
						<td>${course.beginTime }</td>
						<td>${course.endTime }</td>
						<td>${course.coach }</td>
						<td>
							<c:choose>
								<c:when test="${course.personNum gt course.personLimit }">
									<font color="blue">${course.personLimit }人预约、${course.personNum - course.personLimit }人排队</font>
								</c:when>
								<c:otherwise><font color="blue">${course.personNum }人预约</font> </c:otherwise>
							</c:choose>
							/ <font color="red">限制${course.personLimit }人</font>
						</td>
						<td>
							<a href="javascript:editCourse('${course.id}')">编辑</a>
							&nbsp;&nbsp;
							<a href="javascript:deleteCourse('${course.id}')">删除</a>
							&nbsp;&nbsp;
							<a href="javascript:detailCourse('${course.id}')">详情</a>
						</td> 
					</tr>
				</c:forEach>
			</c:when>
			<c:otherwise>
				<tr>
					<td colspan="7" class="center">没有查找到符合条件的数据，请重新输入条件并查询！</td>
				</tr>
			</c:otherwise>
		</c:choose>
	</tbody>
</table>

<diy:page formId="searchForm" async="true" pageSize="${page.pageSize}" page="${page}" gotoURI="web/course/coursePageContent"></diy:page>

<script type="text/javascript">
	$("#formPageNo").val($("#pageNo").val());
	$("#formPageSize").val($("#pageSize").val());
</script>

