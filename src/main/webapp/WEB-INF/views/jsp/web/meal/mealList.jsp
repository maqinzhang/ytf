<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="wrap">
	<input type="hidden" id="rs" value="${param.rs }" />
	<input type="hidden" id="prs" value="${param.prs }" />
	
	<div class="crumbs">
		<i class="iconfont">&#xe628;</i>我的菜单<span>&gt;</span>餐食管理<span>&gt;</span>餐食列表
	</div>
	
	<table class="table vertical-tabs mt10 user">
		<tr>
			<td class="vertical-main">
				<form name="searchForm" id="searchForm" method="post">
					<input type="hidden" id="formPageNo" name="pageNo" />
					<input type="hidden" id="formPageSize" name="pageSize" />
					<h2>
						<span class="ml10">餐食日期：</span> 
						<input type="text" class="input ml10 Wdate" id="beginDate" name="beginDate" value="${beginDate }" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" style=" font-weight: normal;">
							- 
						<input type="text" class="input ml10 Wdate" id="endDate" name="endDate" value="${endDate }" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd', minDate:'#beginDate'})" style=" font-weight: normal;"> 
						<span class="button button-success ml10" id="queryBtn">搜索</span> 
						<a href="javascript:void(0);" class="button button-success" id="addBtn">新增餐食</a>
					</h2>
				</form>
				<!-- 用户分页信息 -->
				<div id="searchFormContent" class="tree-table well3 mt10">
					<jsp:include page="mealPageContent.jsp"></jsp:include>
				</div>
			</td>
		</tr>
	</table>
	
	<script src="resources/js/src/web/meal/mealList.js" type="text/javascript"></script>
</div>
