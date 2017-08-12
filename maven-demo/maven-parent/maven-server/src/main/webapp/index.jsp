<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head> 
     <title>人员信息表</title>
     <link  rel="stylesheet" type="text/css" charset="utf-8" href="<%=basePath %>extjs/resources/css/ext-all.css">
     <script type="text/javascript" charset="utf-8" src="<%=basePath %>extjs/ext-all.js"></script> 
     <script type="text/javascript" charset="utf-8" src="<%=basePath %>js/curd.js"></script>
     <script type="text/javascript" charset="utf-8" src="<%=basePath %>extjs/ext-lang-zh_CN.js"></script>
  </head> 
  <body>   
  </body>
</html>
