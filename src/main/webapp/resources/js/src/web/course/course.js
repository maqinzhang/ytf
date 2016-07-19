/*******************************************************************************
 * 需求客户经理列表JS
 ******************************************************************************/

// 业务代码
var locationUrl = "beginDate=" + $("#beginDate").val() + "&endDate="
		+ $("#endDate").val() + "&rs=" + $("#rs").val() + "&prs="
		+ $("#prs").val();

/**初始化展示**/
if($("#isBase").val() == "1"){
	$("#isBaseCheckBox").prop("checked", "checked");
	
	$("#selectName").show();
	$("#textName").hide();

	$("#textInput").removeAttr("name").val("");
	$("#selectInput").attr("name", "name");
}

/** 显示课程名称 * */
function showName(obj) {
	if (obj.checked == true) {
		$("#selectName").show();
		$("#textName").hide();

		$("#textInput").removeAttr("name");
		$("#selectInput").attr("name", "name");
	} else {
		$("#selectName").hide();
		$("#textName").show();

		$("#selectInput").removeAttr("name");
		$("#textInput").attr("name", "name");
	}
}

/** 选择基础课程 * */
function selectBaseCourse(obj) {
	$("#code").val(($(obj).find("option:selected").attr("data-value")));
}

/** 保存课程* */
$("#saveBtn").click(function() {

	if ($("#textInput").val() == "" && $("#selectInput").val() == "") {
		layer.msg("请输入课程名称！");
		return false;
	}
	if ($("#courseDate").val() == "") {
		layer.msg("请输入开课日期！");
		return false;
	}
	if ($("#beginTime").val() == "" || $("#endTime").val() == "") {
		layer.msg("请输入开课时间！");
		return false;
	}
	if ($("#coach").val() == "") {
		layer.msg("请输入课程教练！");
		return false;
	}
	if ($("#personLimit").val() == "") {
		layer.msg("请输入课程限制人数！");
		return false;
	}
	if ($("#personLimit").val() <= 0) {
		layer.msg("请输入正确的课程限制人数！");
		return false;
	}

	/**
	 * 是否基础课程
	 */
	if($("#isBaseCheckBox").prop("checked") == true || $("#isBaseCheckBox").prop("checked") == "checked"){
		$("#isBase").val("1");
	} else {
		$("#isBase").val("0");
	}

	/**
	 * 发送处理请求
	 */
	$("#submitForm").ajaxSubmit({
		url : $("base").attr("href") + "web/course/saveCourse",
		type : "post",
		dataType : "json",
		success : function(data) {
			if (data.success == true) {
				layer.msg("保存成功！", {
					time : 1000
				// 1秒关闭（如果不配置，默认是3秒）
				}, function() {
					// 关闭后的操作
					loadPage("web/course/list?" + locationUrl);
				});
			} else {
				layer.msg("系统出错，请联系管理员！");
			}
		}
	});
});

/** 返回* */
$("#backBtn").click(function() {
	loadPage("web/course/list?" + locationUrl);
});