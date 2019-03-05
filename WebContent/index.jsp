<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

	欢迎页面 ${name }
	<br/>
	<a href="downloadFile?fileName=人力资源管理系统需求分析.doc">人力资源管理系统需求分析.doc(servlet传统方式)</a>
	<br/>
	<a href="download?fileName=人力资源管理系统需求分析.doc">人力资源管理系统需求分析.doc(springmvc方式)</a>
	
</body>
</html>