/*******************************************************************************
 * 需求客户经理列表JS
 ******************************************************************************/

// 业务代码
var locationUrl = "rs=" + $("#rs").val() + "&prs=" + $("#prs").val();

/** 询价需求查询* */
function queryList() {

	$.ajax({
		url : $("base").attr("href") + "web/userInfo/userInfoPageContent",
		type : "post",
		data : {
			nickName : $("#nickName").val()
		},
		dataType : "html",
		success : function(data) {
			$("#searchFormContent").html(data);
		}
	});
}
$("#queryBtn").click(queryList);

/**从微信端同步用户**/
$("#transferBtn").click(function(){
	$.ajax({
		url : $("base").attr("href") + "web/userInfo/transferFromWexin",
		type : "post",
		dataType : "json",
		data : {_ : Date.parse(new Date())},
		success : function(data) {
			if(data.success == true){
				layer.msg("同步成功！", {
					time : 1000 //1秒关闭（如果不配置，默认是3秒）
				}, function() {
					//关闭后的操作
					loadPage("web/userInfo/userInfoList");
				});
			} else {
				layer.msg(data.msg);
			}
		}
	});
});
