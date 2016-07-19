//自己扩展的jquery函数
//压缩时请把编码改成ANSI
$.page = {
	/**
	 * 分页
	 * 
	 * @param pageSize
	 * @param pn
	 * @param child
	 *            table的子
	 */
	turnPage : function(pageSize, pageNo, formId, uri, async) {
		if (pageNo == null) {
			pageNo = $("#toPageSize").val();
			if (pageNo == '' || isNaN(pageNo) == true || parseInt(pageNo) <= 0
					|| parseInt(pageNo) > parseInt($("#querySomePage").attr("page-totalpage"))) {
				layer.msg("跳转页码不合法", {icon: 5});
				return;
			}
		}
		document.forms[formId].pageNo.value = pageNo;
		document.forms[formId].pageSize.value = pageSize;
		if (async == "true") {
			var options = {
			    url : uri,
				type : "POST",
				dataType : 'html',
				success : function(json) {
					if (json != "") {
						$("#" + formId + "Content").html(json);
					} else {
						layer.msg("查询异常", {icon: 5})
					}
				}
			};
			$("#" + formId).ajaxSubmit(options);
		} else {
			$("#" + formId).submit()
		}
	}
}
