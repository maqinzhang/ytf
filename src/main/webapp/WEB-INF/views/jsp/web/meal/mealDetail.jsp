<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

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
			<div class="row mt10 ">
				<label class="control-label">餐食类目：</label>
				<div class="controls">
					<c:choose>
						<c:when test="${meal.type eq '1' }">食品类</c:when>
						<c:when test="${meal.type eq '2' }">饮品类</c:when>
						<c:otherwise>${meal.type }</c:otherwise>
					</c:choose>
				</div>
			</div>
			<div class="row mt10 ">
				<label class="control-label">餐食名称：</label>
				<div class="controls">
					${meal.name }
				</div>
			</div>
			<div class="row mt10">
				<label class="control-label">餐食日期：</label>
				<div class="controls">
					${meal.mealDate }
				</div>
			</div>
			<div class="row mt10">
				<label class="control-label">餐食数量：</label>
				<div class="controls">
					<font color="blue">${meal.orderNum }份预约</font>  / <font color="red">限制${meal.orderLimit }份</font>
				</div>
			</div>
			<div class="row mt10">
				<label class="control-label">餐食描述：</label>
				<div class="controls" id="content">${meal.content }</div>
			</div>
		</div>
	</div>

	<h2>餐食预约记录</h2>
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
								<th>预约数量</th>
								<th>是否配送</th>
								<th>配送地址</th>
								<th>预约时间</th>
							</tr>
						</thead>
						<tbody>
							<c:choose>
								<c:when test="${not empty userMealRecordList }">
									<c:forEach items="${userMealRecordList }" var="record">
										<tr>
											<td>${record.userInfo.nickName }</td>
											<td>${record.orderNum }</td>
											<td>
												<c:choose>
													<c:when test="${record.isDelivery eq '1' }"><font color="red">是</font></c:when>
													<c:otherwise><font color="blue">否</font></c:otherwise>
												</c:choose>
											</td>
											<td>${record.deliveryAddress }</td>
											<td><fmt:formatDate value="${record.createTime }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
										</tr>
									</c:forEach>
								</c:when>
								<c:otherwise>
									<tr><td colspan="5">暂无预约记录！</td></tr>
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
<script src="resources/js/src/web/meal/meal.js" type="text/javascript"></script>
<script type="text/javascript">
	/**替换textarea换行**/
	var reg = new RegExp("\n", "gi");  
	$("#content").html($("#content").html().replace(reg,"<br/>"));
</script>
