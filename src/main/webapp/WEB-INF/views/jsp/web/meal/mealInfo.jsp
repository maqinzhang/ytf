<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<input type="hidden" id="rs" value="${param.rs }" />
<input type="hidden" id="prs" value="${param.prs }" />
<input type="hidden" id="pageNo" value="${param.pageNo }" />
<input type="hidden" id="pageSize" value="${param.pageSize }" />
<input type="hidden" id="beginDate" value="${param.beginDate }" />
<input type="hidden" id="endDate" value="${param.endDate }" />


<form name="submitForm" id="submitForm" method="post">

	<!-- 设置隐藏域的值 -->
	<input type="hidden" id="id" name="id" value="${meal.id }" /> 

	<div class="crumbs">
		<!-- 导航菜单 -->
		<i class="iconfont">&#xe628;</i>我的菜单<span>&gt;</span>餐食管理<span>&gt;</span>餐食信息
	</div>
	<h2>餐食信息</h2>
	<div class="well2">
		<div class="form-horizontal">
			<!-- 
			<div class="tips tips-notice">
			</div> 
			-->
			<div class="row mt10 ">
				<label class="control-label">餐食类目：</label>
				<div class="controls">
					<select id="type" name="type" class="input input-select">
						<option value="">--请选择--</option>
						<option value="1" <c:if test="${meal.type eq '1' }"> selected</c:if>>食品类</option>
						<option value="2"<c:if test="${meal.type eq '2' }"> selected</c:if>>饮品类</option>
					</select>
					<span style="color: red;">* </span><a style="color: #999999; text-decoration: none;"></a>
				</div>
			</div>
			<div class="row mt10 ">
				<label class="control-label">餐食名称：</label>
				<div class="controls">
					<input type="text" class="input input-large" id="textInput" name="name" value="${meal.name }">
					<span style="color: red;">* </span><a style="color: #999999; text-decoration: none;"></a>
				</div>
			</div>
			<div class="row mt10">
				<label class="control-label">餐食日期：</label>
				<div class="controls">
					<input type="text" class="input input-normal Wdate" id="mealDate" name="mealDate" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" value="${meal.mealDate }"> 
					<span style="color: red;">* </span><a style="color: #999999; text-decoration: none;"></a>
				</div>
			</div>
			<div class="row mt10">
				<label class="control-label">餐食数量：</label>
				<div class="controls">
					<input type="number" class="input input-normal" id="orderLimit" name="orderLimit" value="${meal.orderLimit }"> 
					<span style="color: red;">*</span>
					<a style="color: #999999; text-decoration: none">注：只能为数字</a>
				</div>
			</div>
			<div class="row mt10">
				<label class="control-label">餐食描述：</label>
				<div class="controls">
					<textarea class="span16" style=" height: 140px;" id="content" name="content">${meal.content }</textarea>
					<span style="color: red;">*</span>
					<a style="color: #999999; text-decoration: none"></a>
				</div>
			</div>
		</div>
	</div>

	<div class="row pl10">
		<label class="control-label">&nbsp;</label>
		<div class="controls">
			<a class="button button-success" href="javascript:void(0);" id="saveBtn">保存</a> 
			<a class="button ml10" href="javascript:void(0);" id="backBtn">返回</a>
		</div>
	</div>
</form>
<script src="resources/js/src/web/meal/meal.js" type="text/javascript"></script>
