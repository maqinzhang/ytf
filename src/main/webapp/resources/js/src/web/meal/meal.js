// 业务代码
var locationUrl = "beginDate=" + $("#beginDate").val() + "&endDate="
		+ $("#endDate").val() + "&rs=" + $("#rs").val() + "&prs="
		+ $("#prs").val();

/** 保存餐食* */
$("#saveBtn").click(function() {

	if ($("#textInput").val() == "" && $("#selectInput").val() == "") {
		layer.msg("请输入餐食名称！");
		return false;
	}
	if ($("#mealDate").val() == "") {
		layer.msg("请输入餐食日期！");
		return false;
	}
	if ($("#orderLimit").val() == "") {
		layer.msg("请输入餐食数量！");
		return false;
	}
	if ($("#orderLimit").val() <= 0) {
		layer.msg("请输入正确的餐食数量！");
		return false;
	}

	/**
	 * 发送处理请求
	 */
	$("#submitForm").ajaxSubmit({
		url : $("base").attr("href") + "web/meal/saveMeal",
		type : "post",
		dataType : "json",
		success : function(data) {
			if (data.success == true) {
				layer.msg("保存成功！", {
					time : 1000
				// 1秒关闭（如果不配置，默认是3秒）
				}, function() {
					// 关闭后的操作
					loadPage("web/meal/list?" + locationUrl);
				});
			} else {
				layer.msg("系统出错，请联系管理员！");
			}
		}
	});
});

/** 返回* */
$("#backBtn").click(function() {
	loadPage("web/meal/list?" + locationUrl);
});