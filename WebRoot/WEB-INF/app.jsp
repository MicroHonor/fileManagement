<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>录入系统</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!-- ext的样式文件 -->
	<link rel="styleSheet" type="text/css" href="js/extjs/resources/css/ext-all.css"/>
	
	<link rel="styleSheet" type="text/css" href="core/css/comm.css"/>
	<!-- ext的核心js文件 -->
	<script type="text/javascript" src="js/extjs/ext-all-debug.js"></script>
	<!-- 国际化文件 -->
	<script type="text/javascript" src="js/extjs/ext-lang-zh_CN.js"></script>
	<script type="text/javascript" src="core/app.js"></script>
	<!-- upload样式文件 -->
	<link rel="stylesheet" type="text/css" href="js/uploadPanel/UploadPanel.css" />
	<!-- swfupload -->
	<script type="text/javascript" src="js/swfupload/swfupload.js"></script>
	<!-- uploadPanel -->
	<script type="text/javascript" src="js/uploadPanel/UploadPanel.js"></script> 
	<!-- 工具 -->
	<script type="text/javascript" src="js/public.js"></script> 
  </head>
  
  <body>
  </body>
</html>
