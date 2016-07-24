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
	<input type="hidden" id="id" name="id" value="${course.id }" /> 

	<div class="crumbs">
		<!-- 导航菜单 -->
		<i class="iconfont">&#xe628;</i>我的菜单<span>&gt;</span>课程管理<span>&gt;</span>课程信息
	</div>
	<h2>课程信息</h2>
	<div class="well2">
		<div class="form-horizontal">
			<div class="tips tips-notice">
				注：基础课程是固定课程，可做调整，以选择形式填入，其他课程均为手动录入名称的形式！ <br />
			</div>
			
			<div class="row mt10">
				<label class="control-label">是否基础课程：</label>
				<div class="controls">
					<input type="hidden" id="isBase" name="isBase" value="${course.isBase }"/>
					<input id="isBaseCheckBox" type="checkbox" class="input input-normal" onchange="showName(this);"> 
					<span style="color: red;">* </span><a style="color: #999999; text-decoration: none;"></a>
				</div>
			</div>
			
			<div class="row mt10 ">
				<label class="control-label">课程名称：</label>
				<div class="controls" id="textName">
					<input type="text" class="input input-large" id="textInput" name="name" value="${course.name }">
					<span style="color: red;">* </span><a style="color: #999999; text-decoration: none;"></a>
				</div>
				<div class="controls" id="selectName" style="display: none;">
					<input type="hidden" id="code" name="code" value="${course.code }"/>
					<select id="selectInput" class="input input-select" onchange="selectBaseCourse(this);">
						<option value="" data-value=""></option>
						<c:forEach items="${baseCourseList }" var="baseCourse">
							<option value="${baseCourse.name }" data-value="${baseCourse.id }" <c:if test="${baseCourse.id eq course.code }"> selected</c:if>>${baseCourse.name }</option>
						</c:forEach>
					</select>
					<span style="color: red;">* </span><a style="color: #999999; text-decoration: none;"></a>
				</div>
			</div>
			
			<div class="row mt10">
				<label class="control-label">开课日期：</label>
				<div class="controls">
					<input type="text" class="input input-normal Wdate" id="courseDate" name="courseDate" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" value="${course.courseDate }"> 
					<span style="color: red;">* </span><a style="color: #999999; text-decoration: none;"></a>
				</div>
			</div>
			<div class="row mt10">
				<label class="control-label">开课时间：</label>
				<div class="controls">
					<input type="text" class="input input-normal Wdate" id="beginTime" name="beginTime" onFocus="WdatePicker({dateFmt:'HH:mm'})" value="${course.beginTime }">  - 
					<input type="text" class="input input-normal Wdate" id="endTime" name="endTime" onFocus="WdatePicker({dateFmt:'HH:mm', minDate:'#F{$dp.$D(\'beginTime\')}'})" value="${course.endTime }"> 
					<span style="color: red;">* </span><a style="color: #999999; text-decoration: none;"></a>
				</div>
			</div>
			<div class="row  mt10">
				<label class="control-label">教练：</label>
				<div class="controls">
					<input type="text" class="input input-normal" id="coach" name="coach" value="${course.coach }">
					<span style="color: red;">*</span> 
					<a style="color: #999999; text-decoration: none"></a>
				</div>
			</div>
			<div class="row mt10">
				<label class="control-label">限制人数：</label>
				<div class="controls">
					<input type="number" class="input input-normal" id="personLimit" name="personLimit" value="${course.personLimit }"> 
					<span style="color: red;">*</span>
					<a style="color: #999999; text-decoration: none">注：只能为数字</a>
				</div>
			</div>
			<div class="row mt10">
				<label class="control-label">课程描述：</label>
				<div class="controls">
					<textarea class="span16" style=" height: 140px;" id="content" name="content">${course.content }</textarea>
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
<script src="resources/js/src/web/course/course.js" type="text/javascript"></script>
