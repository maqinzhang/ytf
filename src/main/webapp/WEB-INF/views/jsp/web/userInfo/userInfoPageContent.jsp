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
			<th>用户ID</th>
			<th>用户昵称</th>
			<th>性别</th>
			<th>关注时间</th>
			<th>状态</th>
			<!-- <th>操作</th> -->
		</tr>
	</thead>
	<tbody>
		<c:choose>
			<c:when test="${ !empty page.list }">
				<c:forEach items="${page.list }" var="userInfo">
					<tr>
						<td>${userInfo.userId }</td>
						<td>${userInfo.nickName }</td>
						<td>
							<c:choose>
								<c:when test="${userInfo.sex eq '1' }">男</c:when>
								<c:when test="${userInfo.sex eq '2' }">女</c:when>
								<c:when test="${userInfo.sex eq '0' }">未知</c:when>
								<c:otherwise>${userInfo.sex }</c:otherwise>
							</c:choose>
						</td>
						<td><fmt:formatDate value="${userInfo.regTime }" pattern="yyyy-MM-dd"/></td>
						<td>
							<c:choose>
								<c:when test="${userInfo.status eq '1' }"><font color="blue">有效</font></c:when>
								<c:when test="${userInfo.status eq '0' }"><font color="red">失效</font></c:when>
								<c:otherwise>${userInfo.status }</c:otherwise>
							</c:choose>
						</td>
						<%-- 
						<td>
							<a href="javascript:deleteAmUser('${userInfo.userId}')">删除</a>
						</td> 
						--%>
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

<diy:page formId="searchForm" async="true" pageSize="${page.pageSize}" page="${page}" gotoURI="web/userInfo/userInfoPageContent"></diy:page>

<script type="text/javascript">
	$("#formPageNo").val($("#pageNo").val());
	$("#formPageSize").val($("#pageSize").val());
</script>

