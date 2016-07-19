<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>工作控制台</title>
<link rel="shortcut icon" type="image/x-icon" href="favicon.ico" media="screen" />
<link rel="stylesheet" href="resources/css/dpl.css">
<link rel="stylesheet" href="resources/css/plugins.css">
<link rel="stylesheet" href="resources/css/layout.css">

<script type="text/javascript" src="resources/js/lib/jquery/jquery-2.1.4.min.js"></script>
<script type="text/javascript" src="resources/js/lib/jquery/jquery.form.js"></script>
<script type="text/javascript" src="resources/js/lib/layer-v2.1/layer.js"></script>
<script type="text/javascript" src="resources/js/lib/layer-v2.1/extend/layer.ext.js"></script>
<script type="text/javascript" src="resources/js/lib/My97DatePicker/WdatePicker.js"></script>

<script type="text/javascript" src="resources/js/common/page.js"></script>

<style type="text/css">
	.main-content{ padding-left: 180px; padding-right: 10px;}
	.main-content h2{ height: 26px; line-height: 26px; padding: 5px 0;}
	.icon-wrap,a.icon-wrap{ width: 20px; height: 20px; display: inline-block; text-decoration: none; color: #666; overflow: hidden; background: none;}
	/*所在位置*/
	.crumbs{ padding: 10px 0 2px;}
	.crumbs i{ margin-right: 5px;}
	.crumbs span{ padding: 0 5px;}
	/*首页*/
	.index-info{ position: relative;}
	.index-info img{ position: absolute; left: 0; top: 0; border-radius:60px;}
	.index-info ul{ float: left; padding-left: 130px; min-height: 115px;}
	.index-info li{ width: 49.5%; float: left; padding: 3px 0; overflow: hidden;}
</style>

</head>
<body>
	<div class="wrap">
		<c:import url="/top" />
		<!--// Top-->

		<div class="wy-sidebar">
			<c:import url="/menu" />
		</div>
		<div class="wy-sidebar-bg"></div>
		<!--// sidebar-->

		<div class="main-content" id="main-content">
			<%-- <c:import url="/default" /> --%>
		</div>
	</div>

	<script type="text/javascript">

		/**初始化进入页面的操作**/
		$(function() {
			var hashStr = location.hash.replace("#","");
			
			if(hashStr != "" && hashStr.indexOf("default") == -1){
				var rs = "00";
				var prs = "00";
				var pos = hashStr.indexOf("?");
				hashParamter = hashStr.slice(pos + 1);
				//rs=xx&prs=xx
				var arr = hashParamter.split("&");
				$.each(arr, function(index, part) {
					 var _pos = part.indexOf("=");
                     var key = part.slice(0, _pos);
                     if(key == "rs"){
                    	 rs = part.slice(_pos + 1);
                     } else if(key == "prs"){
                    	 prs = part.slice(_pos + 1);
                     }
				});
				showChildMenus(prs);
				$(".wy-menu .on").removeClass("on");
				$("#rs_" + rs).addClass("on");
				
				refreshPage();
			} else {
				showChildMenus("00");
				$(".wy-menu .on").removeClass("on");
				$("#rs_00").addClass("on");
				
				$.ajax({
					type : 'get',
					url : '<%=basePath%>default',
					success : function(data){
						$("#main-content").html(data);
					}
				});
			}
		});
	
		/**面包屑导航，兼容菜单栏的时候发生问题，无法获取面包屑中a标签上的属性值，顾单独提出**/
		function setTab_1(rsId){
			showChildMenus(rsId);
			var $this = $("#dd_" + rsId);
			var element = $this.find("a").first();
			var prs = $(element).attr("prs");
			var rs = $(element).attr("rs");
			var url = $(element).attr("url");
			var isnewtab = $(element).attr("isnewtab");
			
			toLocation(isnewtab, url, rs, prs);
		}
	
		function toLocation(isnewtab, url, rs, prs) {
			var x = new RegExp("\\?");
			if (x.test(url)) {
				//有问号
				url = url + "&rs=" + rs + "&prs=" + prs;
			} else {
				url = url + "?rs=" + rs + "&prs=" + prs;
			}
			
			$(".wy-menu .on").removeClass("on");
			$("#rs_" + rs).addClass("on");
			
			loadPage(url, isnewtab);
		}
	
		//加载页面
		function loadPage(url, isnewtab) {
			if (typeof isnewtab != 'undefined' && isnewtab == "1") {
				url = "<%=basePath%>" + url;
				window.open(url, '_blank');
			} else {
				var x = new RegExp("\\?");
				if (x.test(url)) {
					//有问号
					url = url + "&_=" +Date.parse(new Date());
				} else {
					url = url + "?_=" + Date.parse(new Date());
				}
				
				location.hash = url;
			}
		}
		
		function showChildMenus(operatorId){
			$(".wy-menu .active").removeClass("active");
			$("#dl_" + operatorId).addClass("active");
			$("#dd_" + operatorId).show();
			$("#dt_" + operatorId).attr("onclick","hideChildMenus('" + operatorId + "')");
		}
		
		function hideChildMenus(operatorId) {
			$(".wy-menu .active").removeClass("active");
			$("#dl_" + operatorId).addClass("active");
			$("#dd_" + operatorId).hide();
			$("#dt_" + operatorId).attr("onclick","showChildMenus('" + operatorId + "')")
		}
		
		/** hash change **/
		function refreshPage() {
			var hashStr = location.hash.replace("#","");
			$.ajax({
				type : 'get',
				url : '<%=basePath%>' + hashStr,
				success : function(data) {
					$("#main-content").html(data);
				}
			});
		}

		//监听hashchange事件
		window.addEventListener("hashchange", refreshPage);
		
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
	</script>
</body>
</html>