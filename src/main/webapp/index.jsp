<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>">
<title>首页</title>
<meta charset="UTF-8">
<link rel="shortcut icon" type="image/x-icon" href="favicon.ico" media="screen" />
<body>
	<div>YTF欢迎你！</div>
</body>
</html>