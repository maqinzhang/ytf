/** 餐食列表查询* */
function queryList() {

	$.ajax({
		url : $("base").attr("href") + "web/meal/mealPageContent",
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

/** 新增餐食 * */
$("#addBtn").click(function() {
	// 业务代码
	var locationUrl = "beginDate=" + $("#beginDate").val() + "&endDate="
			+ $("#endDate").val() + "&rs=" + $("#rs").val() + "&prs="
			+ $("#prs").val();
	
	loadPage("web/meal/info?" + locationUrl);
});

/** 修改餐食 * */
function editMeal(id) {
	
	// 业务代码
	var locationUrl = "beginDate=" + $("#beginDate").val() + "&endDate="
			+ $("#endDate").val() + "&rs=" + $("#rs").val() + "&prs="
			+ $("#prs").val();
	
	loadPage("web/meal/info?id=" + id + "&" + locationUrl);
}

/** 删除餐食 * */
function deleteMeal(id) {
	
	layer.confirm('您确认删除该餐食？', function() {
		// 确认
		$.ajax({
			url : $("base").attr("href") + "web/meal/deleteMeal",
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
						loadPage("web/meal/list?" + locationUrl);
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

/** 餐食详情 * */
function detailMeal(id) {
	
	// 业务代码
	var locationUrl = "beginDate=" + $("#beginDate").val() + "&endDate="
			+ $("#endDate").val() + "&rs=" + $("#rs").val() + "&prs="
			+ $("#prs").val();
	
	loadPage("web/meal/detail/" + id + "?" + locationUrl);
}
