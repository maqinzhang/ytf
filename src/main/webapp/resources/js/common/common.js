/*******************************************************************************
 * 通用JS
 ******************************************************************************/
var _index;

//初始化AJAX请求参数，全局的AJAX请求参数
$.ajaxSetup({
	//发送请求前触发
	beforeSend : function (xhr) {
		//_index = layer.msg('加载中', {icon: 16});
		_index = layer.msg('加载中，请稍候...', {icon: 16, time: 30000});
	},
	//请求失败遇到异常触发
	error : function(XMLHttpRequest, textStatus) {
		layer.msg("系统出错，请联系管理员！");
	},
	//完成请求后触发，即在success或error触发后触发
	complete : function(xhr, status) {
		layer.close(_index);
	}
});

