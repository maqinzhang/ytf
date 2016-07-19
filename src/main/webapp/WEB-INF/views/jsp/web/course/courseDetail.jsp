<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

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
					<c:choose>
						<c:when test="${course.isBase eq '1' }">是</c:when>
						<c:otherwise>否</c:otherwise>
					</c:choose>
				</div>
			</div>
			
			<div class="row mt10 ">
				<label class="control-label">课程名称：</label>
				<div class="controls" id="textName">
					${course.name }
				</div>
			</div>
			
			<div class="row mt10">
				<label class="control-label">开课日期：</label>
				<div class="controls">
					${course.courseDate }
				</div>
			</div>
			<div class="row mt10">
				<label class="control-label">开课时间：</label>
				<div class="controls">
					${course.beginTime }  - ${course.endTime }
				</div>
			</div>
			<div class="row  mt10">
				<label class="control-label">教练：</label>
				<div class="controls">
					${course.coach }
				</div>
			</div>
			<div class="row mt10">
				<label class="control-label">人数：</label>
				<div class="controls">
					<c:choose>
						<c:when test="${course.personNum gt course.personLimit }">
							<font color="red">${course.personLimit }人预约、${course.personNum - course.personLimit }人排队</font>
						</c:when>
						<c:otherwise><font color="red">${course.personNum }人预约</font> </c:otherwise>
					</c:choose>
					/ <font color="blue">限制${course.personLimit }人</font>
				</div>
			</div>
			<div class="row mt10">
				<label class="control-label">课程描述：</label>
				<div class="controls">
					${course.content }
				</div>
			</div>
		</div>
	</div>

	<h2>课程预约记录</h2>
	<div class="well2 vertical-main">
		<div class="form-horizontal">
            <div class="row">
                <div style=" padding: 10px;">
                   <table class="table table-bordered table-hover center"  style="table-layout:fixed">
						<caption></caption>
						<colgroup>
							<col />
							<col />
							<col />
						</colgroup>
						<thead>
							<tr>
								<th>预约用户</th>
								<th>预约状态</th>
								<th>预约时间</th>
							</tr>
						</thead>
						<tbody>
							<c:choose>
								<c:when test="${not empty userCourseRecordList }">
									<c:forEach items="${userCourseRecordList }" var="record">
										<tr>
											<td>${record.userInfo.nickName }</td>
											<td>
												<c:choose>
													<c:when test="${record.isStandby eq '1' }"><font color="blue">预约成功</font></c:when>
													<c:otherwise><font color="red">候补排队</font></c:otherwise>
												</c:choose>
											</td>
											<td><fmt:formatDate value="${record.createTime }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
										</tr>
									</c:forEach>
								</c:when>
								<c:otherwise>
									<tr><td colspan="3">暂无预约记录！</td></tr>
								</c:otherwise>
							</c:choose>
						</tbody>
					</table>
                </div>
            </div>
        </div>
	</div>

	<div class="row pl10">
		<label class="control-label">&nbsp;</label>
		<div class="controls">
			<!-- <a class="button button-success" href="javascript:void(0);" id="saveBtn">保存</a>  -->
			<a class="button ml10" href="javascript:void(0);" id="backBtn">返回</a>
		</div>
	</div>
</form>
<script src="resources/js/src/web/course/course.js" type="text/javascript"></script>
