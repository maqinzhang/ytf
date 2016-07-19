<%--
 分页格式
   首页  上一页 <<   1   2   3   4   5   6   7   8   9   10  11>  >> 下一页 尾页
   首页  上一页<<   1   2   3   4   5   6   7   8   9   ... 11  12 >  >> 下一页 尾页
   首页  上一页 <<   1   2  ...  4   5   6   7   8   9   10 ... 12  13 >  >> 下一页 尾页
   首页  上一页 <<   1   2  ...  5   6   7   8   9   10  11  12  13 >  >> 下一页 尾页
   首页  上一页 <<   1   2  ...  5   6   7   8   9   10  11  ... 13  14 >  >> 下一页 尾页
   首页  上一页 <<   1   2  ...  5   6   7   8   9   10  11  ...   21  22 >  >> 下一页 尾页

--%>
<%@tag pageEncoding="UTF-8" description="分页"%>
<%@ attribute name="page" type="com.github.pagehelper.PageInfo" required="true" description="分页"%>
<%@ attribute name="pageSize" type="java.lang.Integer" required="false" description="每页显示记录数"%>
<%@ attribute name="gotoURI" type="java.lang.String" required="true" description="跳转URI"%>
<%@ attribute name="formId" type="java.lang.String" required="false" description="表单ID"%>
<%@ attribute name="async" type="java.lang.String" required="true" description="是否异步刷新"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:if test="${empty pageSize}">
	<c:set var="page.pageSize" value="20" />
</c:if>
<c:if test="${empty formId}">
	<c:set var="formId" value="searchForm" />
</c:if>
<!-- 计算... -->
<c:set var="displaySize" value="2" />
<c:set var="current" value="${page.pageNum }" />

<c:set var="begin" value="${current - displaySize}" />
<c:if test="${begin <= displaySize}">
	<c:set var="begin" value="${1}" />
</c:if>
<c:set var="end" value="${current + displaySize}" />
<c:if test="${end > page.pages - displaySize}">
	<c:set var="end" value="${page.pages - displaySize}" />
</c:if>
<c:if test="${end < 0 or page.pages < displaySize * 4}">
	<c:set var="end" value="${page.pages}" />
</c:if>

<div class="clearfix mt10">
	<ul class="pagination pull-right ">
		<c:choose>
			<c:when test="${page.isFirstPage || page.pages eq '0'}">
				<li class="disabled"><a title="首页">首页</a></li>
				<li class="disabled"><a title="上一页">上一页</a></li>
			</c:when>
			<c:otherwise>
				<li><a href="javascript:;" onclick="$.page.turnPage('${pageSize}', 1, '${formId }', '${gotoURI }', '${async}');" title="首页">首页</a></li>
				<li><a href="javascript:;" onclick="$.page.turnPage('${pageSize}', ${current - 1}, '${formId }', '${gotoURI }', '${async}');" title="上一页">&lt;&lt;</a>
				</li>
			</c:otherwise>
		</c:choose>

		<c:forEach begin="1" end="${begin == 1 ? 0 : 2}" var="i">
			<li <c:if test="${current == i}"> class="active"</c:if>><a href="javascript:;"
				onclick="$.page.turnPage('${pageSize}', ${i}, '${formId }', '${gotoURI }', '${async}');" title="第${i}页">${i}</a></li>
		</c:forEach>

		<c:if test="${begin > displaySize + 1}">
			<li><a>...</a></li>
		</c:if>

		<c:forEach begin="${begin}" end="${end}" var="i">
			<li <c:if test="${current == i}"> class="active"</c:if>><a href="javascript:;"
				onclick="$.page.turnPage('${pageSize}', ${i}, '${formId }', '${gotoURI }', '${async}');" title="第${i}页">${i}</a></li>
		</c:forEach>


		<c:if test="${end < page.pages - displaySize}">
			<li><a>...</a></li>
		</c:if>

		<c:forEach begin="${end < page.pages ? page.pages - 1 : page.pages + 1}" end="${page.pages}" var="i">
			<li <c:if test="${current == i}"> class="active"</c:if>><a href="javascript:;"
				onclick="$.page.turnPage('${pageSize}', ${i}, '${formId }', '${gotoURI }', '${async}');" title="第${i}页">${i}</a></li>
		</c:forEach>

		<c:choose>
			<c:when test="${page.isLastPage || page.pages eq '0'}">
				<li class="disabled"><a title="下一页">下一页</a></li>
				<li class="disabled"><a title="尾页">尾页</a></li>
			</c:when>
			<c:otherwise>
				<li><a href="javascript:;" onclick="$.page.turnPage('${pageSize}', ${current + 1}, '${formId }', '${gotoURI }', '${async}');" title="下一页">&gt;&gt;</a>
				</li>
				<li><a href="javascript:;" onclick="$.page.turnPage('${pageSize}', ${page.pages}, '${formId }', '${gotoURI }', '${async}');" title="尾页">尾页</a>
				</li>
			</c:otherwise>
		</c:choose>
		<li>&nbsp;&nbsp;&nbsp;&nbsp;共${page.total }条记录&nbsp;&nbsp;&nbsp;&nbsp;共${page.pages }页&nbsp;&nbsp;&nbsp;&nbsp;转向第&nbsp;&nbsp;&nbsp;&nbsp; <input
			type="text" id="toPageSize" class="span1 span-width"> &nbsp;&nbsp;&nbsp;&nbsp;页&nbsp;&nbsp;&nbsp;&nbsp;
			<div page-totalPage="${page.pages }" onclick="$.page.turnPage('${pageSize}', null, '${formId }', '${gotoURI }', '${async}')"
				class="button button-small">确定</div>
		</li>
	</ul>
</div>
