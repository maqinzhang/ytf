// 业务代码
var locationUrl = "rs=" + $("#rs").val() + "&prs=" + $("#prs").val();

/** 课程列表查询* */
function queryList() {

	$.ajax({
		url : $("base").attr("href") + "web/baseCourse/coursePageContent",
		type : "post",
		dataType : "html",
		success : function(data) {
			$("#searchFormContent").html(data);
		}
	});
}
$("#queryBtn").click(queryList);

/** 新增课程 * */
$("#addBtn").click(function() {
	layer.prompt({
		title : '输入课程名称'
	}, function(text) {
		$.ajax({
			url : $("base").attr("href") + "web/baseCourse/saveCourse",
			type : "post",
			data : {
				name : text
			},
			dataType : "json",
			success : function(data) {
				if (data.success == true) {
					layer.msg("保存成功！", {
						time : 1000
					// 1秒关闭（如果不配置，默认是3秒）
					}, function() {
						// 关闭后的操作
						loadPage("web/baseCourse/list?" + locationUrl);
					});
				} else {
					layer.msg(data.msg);
				}
			}
		});
	});
});

/** 修改课程 * */
function editCourse(id, title) {
	layer.prompt({
		title : '输入课程名称',
		value : title
	}, function(text) {
		$.ajax({
			url : $("base").attr("href") + "web/baseCourse/saveCourse",
			type : "post",
			data : {
				id : id,
				name : text
			},
			dataType : "json",
			success : function(data) {
				if (data.success == true) {
					layer.msg("保存成功！", {
						time : 1000
					// 1秒关闭（如果不配置，默认是3秒）
					}, function() {
						// 关闭后的操作
						loadPage("web/baseCourse/list?" + locationUrl);
					});
				} else {
					layer.msg("系统出错，请联系管理员！");
				}
			}
		});
	});
}

/** 删除课程 * */
function deleteCourse(id) {
	layer.confirm('您确认删除该课程？', function() {
		//确认
		$.ajax({
			url : $("base").attr("href") + "web/baseCourse/deleteCourse",
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
						// 关闭后的操作
						loadPage("web/baseCourse/list?" + locationUrl);
					});
				} else {
					layer.msg("系统出错，请联系管理员！");
				}
			}
		});
	}, function() {
		//取消
	});
}
