/*******************************************************************************
 * 需求客户经理列表JS
 ******************************************************************************/

/** 课程列表查询* */
function queryList() {

	$.ajax({
		url : $("base").attr("href") + "web/course/coursePageContent",
		type : "post",
		data : {
			beginDate : $("#beginDate").val(),
			endDate : $("#endDate").val()
		},
		dataType : "html",
		success : function(data) {
			$("#searchFormContent").html(data);
		}
	});
}
$("#queryBtn").click(queryList);

/** 新增课程 * */
$("#addBtn").click(function() {
	// 业务代码
	var locationUrl = "beginDate=" + $("#beginDate").val() + "&endDate="
			+ $("#endDate").val() + "&rs=" + $("#rs").val() + "&prs="
			+ $("#prs").val();
	
	loadPage("web/course/info?" + locationUrl);
});

/** 修改课程 * */
function editCourse(id) {
	
	// 业务代码
	var locationUrl = "beginDate=" + $("#beginDate").val() + "&endDate="
			+ $("#endDate").val() + "&rs=" + $("#rs").val() + "&prs="
			+ $("#prs").val();
	
	loadPage("web/course/info?id=" + id + "&" + locationUrl);
}

/** 删除课程 * */
function deleteCourse(id) {
	
	layer.confirm('您确认删除该课程？', function() {
		// 确认
		$.ajax({
			url : $("base").attr("href") + "web/course/deleteCourse",
			type : "post",
			data : {
				id : id
			},
			dataType : "json",
			success : function(data) {
				if (data.success == true) {
					layer.msg("删除成功！", {
						time : 1000
					// 1秒关闭（如果不配置，默认是3秒）
					}, function() {
						
						// 业务代码
						var locationUrl = "beginDate=" + $("#beginDate").val() + "&endDate="
								+ $("#endDate").val() + "&rs=" + $("#rs").val() + "&prs="
								+ $("#prs").val();
						
						// 关闭后的操作
						loadPage("web/baseCourse/list?" + locationUrl);
					});
				} else {
					layer.msg("系统出错，请联系管理员！");
				}
			}
		});
	}, function() {
		// 取消
	});
}

/** 课程详情 * */
function detailCourse(id) {
	
	// 业务代码
	var locationUrl = "beginDate=" + $("#beginDate").val() + "&endDate="
			+ $("#endDate").val() + "&rs=" + $("#rs").val() + "&prs="
			+ $("#prs").val();
	
	loadPage("web/course/detail/" + id + "?" + locationUrl);
}
