<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%> 
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
<title>餐食预约</title>
<link rel="shortcut icon" type="image/x-icon" href="favicon.ico" media="screen" />
<meta content="Home" http-equiv="description">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="format-detection" content="telephone=no">
<meta content="width=750, user-scalable=no, target-densitydpi=device-dpi" name="viewport">
<link href="resources/css/style.css" type="text/css" rel="stylesheet">

<script type="text/javascript" src="resources/js/lib/zepto/zepto.min.js"></script>
<script type="text/javascript" src="resources/js/lib/zepto/event.js"></script>
<script type="text/javascript" src="resources/js/lib/zepto/touch.js"></script>

<script type="text/javascript" src="resources/js/common/Toast.js"></script>
<script type="text/javascript" src="resources/js/common/WeChat.js"></script>
</head>
<body>
	<div class="hotbody arena_info">
		<div class="logo">
			<div style=" width: 30%;  padding-left: 20px;" >
				<img alt="YTF" src="resources/img/logo_left.png">
			</div>
			<div style=" width: 70%; text-align: right; padding-right: 20px; ">
				<img alt="CROSSFIT" src="resources/img/logo_right.png">
			</div>
		</div>
		<div class="main">
			<h1 class="glo_info_model">${meal.name }</h1>
			<div class="glo_module">
				<article class="glo_info_model">
					<header>
						<h3>基本信息</h3>
					</header>
					<p>订餐日期：${meal.mealDate }</p>
					<!-- <p class="callout2">  </p> -->
					<p>剩余份数：<font color="red">${meal.orderLimit - meal.orderNum }</font></p>
					<input type="hidden" id="orderRemainder" value="${meal.orderLimit - meal.orderNum }"/>
				</article>
				<article class="glo_info_model">
					<header>
						<h3>课程介绍</h3>
					</header>
					<p id="content">${meal.content }</p>
				</article>
				<input type="hidden" id="id" value="${meal.id }">
			</div>
			<div style="padding-left: 10px; padding-right: 10px;">
				<a id="addBtn" class="btn-big btn-red">预约餐食</a>
				<a id="backBtn" style=" margin-top: 15px;" class="btn-big btn-gray">返回</a>
			</div>
		</div>
	</div>
	
	<div id="platform" class="over_all" style="display: hidden;">
		<div class="content platform-send clearfix">
		  	<div class="ope-title">预约餐饮</div>
		   	<form id="orderForm" action="meal/order" method="post">
		   		<input type="hidden" name="mealId" value="${meal.id }">
		   		<div class="input-box-big">
				      <label>预定份数</label>
				      <input id="orderNum" name="orderNum" type="text" value="1" data-minNum="1" data-maxNum="${meal.orderLimit - meal.orderNum }" class="num" readonly="readonly">
				      <span class="change-num subtract" btn-role="subtract"><i class="icon iconfont">&#xe602;</i></span>
				      <span class="change-num add edit" btn-role="add"><i class="icon iconfont">&#xe601;</i></span>
			    </div>
			    <div class="distinct">
				      <label class="if-hide">是否需要配送<span id="isDeliveryControl" class="icon_control"><i></i></span></label>
				      <div class="val js-txt-empty" id="certificate">
				      		<input type="hidden" id="isDelivery" name="isDelivery"/>
				      		<input type="text" id="deliveryAddress" name="deliveryAddress" placeholder="请填写配送地址">
				      </div>
			    </div>
		   	</form>
		   	<input class="btn-middle btn-red sure" style=" height: 60px;" value="确认" type="button">
		   	<input class="btn-middle btn-cancel cancel" style=" height: 60px;" value="取消" type="button">
		</div>
	</div>
	<script type="text/javascript">
	
		$("#orderNum").val($("#orderNum").attr("data-minNum"));
		
		// 订餐分数调整
	    $(".change-num").bind('touchend',function(){
	        var that=$(this),par=that.parent();
	        var txtInput=par.find("input"),
	            sub=par.find(".subtract"),
	            add=par.find(".add");
	        var num=txtInput.val(),
	            minN=txtInput.attr("data-minNum"),
	            maxN=txtInput.attr("data-maxNum");
	        if(minN==maxN){
	        	Toast("最多只能订购"+maxN+"份！");
	            return;
	        }
	        if(that.hasClass("edit")){
	            switch(that.attr("btn-role")){
	                case "add":
	                    num=parseInt(num)+1;
	                    if(!sub.hasClass("edit")) sub.addClass("edit");
	                    if(num==maxN) that.removeClass("edit");
	                break;
	                case "subtract":
	                    num=parseInt(num)-1;
	                    if(!add.hasClass("edit")) add.addClass("edit");
	                    if(num==minN) that.removeClass("edit");
	                break
	            }
	            txtInput.val(num);
	        }
	    });
		
		 /** 预定按钮事件 **/
	    $('.cancel').on('click',function(){
	        // 点击取消，弹出层消失
	        $("#platform").hide();
	    });
	    $('.sure').on('click',function(){
	    	
	    	var isDelivery = "";
	    	
	    	/** 设置是否配送字段* */
			if ($("#isDeliveryControl").hasClass('checked')) {
				$("#isDelivery").val("1");
				if($("#deliveryAddress").val() == ""){
					Toast("请填写配送地址！");
					return false;
				}
			} else {
				$("#isDelivery").val("0");
			}
	    	
			$("#orderForm").submit();
	    	
	    	/** 取消绑定事件 防止重复提交* */
			$(".sure").unbind("tap");
		
	    });
	    
	    // 是否隐藏我的信息
	    $('#isDeliveryControl').click(function(){
	        var that=$(this);
	        if(that.hasClass('checked')){
	            that.removeClass('checked');
	            $('#isDeliveryControl i').css("left","0");
	            $("#certificate").css("height","0").removeClass("border-top")
	                .find("input").val("").blur();
	        }else{
	            that.addClass('checked');
	            $('#isDeliveryControl i').css("left","30px");
	            $("#certificate").css("height","48px").addClass("border-top")
	                .find("input").focus();
	        }
	    });
	
		/**预约餐食预约**/
		$("#addBtn").tap(function(){
			
			if($("#orderRemainder").val() == "0"){
				Toast("该餐食已经定完，请选择其他餐食！");
				return false;
			}
			$("#platform").show();
		});
	
		/**返回**/
		$("#backBtn").tap(function(){
			history.go(-1);
		});
		
		/**替换textarea换行**/
		var reg = new RegExp("\n", "gi");  
		$("#content").html($("#content").html().replace(reg,"<br/>"));
	</script>
</body>
</html>