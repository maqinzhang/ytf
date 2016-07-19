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
	</colgroup>
	<thead>
		<tr>
			<th>课程编号</th>
			<th>课程名称</th>
			<th>创建时间</th>
			<th>操作</th>
		</tr>
	</thead>
	<tbody>
		<c:choose>
			<c:when test="${ !empty page.list }">
				<c:forEach items="${page.list }" var="course">
					<tr>
						<td>${course.id }</td>
						<td>${course.name }</td>
						<td><fmt:formatDate value="${course.createTime }" pattern="yyyy-MM-dd"/></td>
						<td>
							<a href="javascript:editCourse('${course.id}', '${course.name}')">编辑</a>
							&nbsp;&nbsp;
							<a href="javascript:deleteCourse('${course.id}')">删除</a>
						</td> 
					</tr>
				</c:forEach>
			</c:when>
			<c:otherwise>
				<tr>
					<td colspan="4" class="center">没有查找到符合条件的数据，请重新输入条件并查询！</td>
				</tr>
			</c:otherwise>
		</c:choose>
	</tbody>
</table>

<diy:page formId="searchForm" async="true" pageSize="${page.pageSize}" page="${page}" gotoURI="web/baseCourse/coursePageContent"></diy:page>

<script type="text/javascript">
	$("#formPageNo").val($("#pageNo").val());
	$("#formPageSize").val($("#pageSize").val());
</script>

