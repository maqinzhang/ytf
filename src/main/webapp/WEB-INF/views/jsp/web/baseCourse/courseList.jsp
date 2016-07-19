<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="wrap">
	<input type="hidden" id="rs" value="${param.rs }" />
	<input type="hidden" id="prs" value="${param.prs }" />
	
	<div class="crumbs">
		<i class="iconfont">&#xe628;</i>我的菜单<span>&gt;</span>基础课程管理<span>&gt;</span>课程列表
	</div>
	
	<table class="table vertical-tabs mt10 user">
		<tr>
			<td class="vertical-main">
				<form name="searchForm" id="searchForm" method="post">
					<input type="hidden" id="formPageNo" name="pageNo" />
					<input type="hidden" id="formPageSize" name="pageSize" />
					<h2>
						<a href="javascript:void(0);" class="button button-success" id="addBtn">新增基础课程</a>
					</h2>
				</form>
				<!-- 分页信息 -->
				<div id="searchFormContent" class="tree-table well3 mt10">
					<jsp:include page="coursePageContent.jsp"></jsp:include>
				</div>
			</td>
		</tr>
	</table>
	
	<script src="resources/js/src/web/baseCourse/courseList.js" type="text/javascript"></script>
</div>
