<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="js/jquery-1.7.2.js"></script>
<script type="text/javascript">
	
	$(function(){
		
	})
	
	function getNameAndSex(){
		$.ajax({
			url:"queryById",
			type:"post",
			dataType:"json",
			success:function(result){
				alert("姓名:" + result.name + "性别:" + result.sex);
			}
		});
	}
	
	function getStudentList(){
		$.ajax({
			url:"queryAll",
			type:"post",
			dataType:"json",
			success:function(result){
				//清空内容
				$("#tt").empty();
				//$("#tt").html("");
				//添加表头
				$("#tt").append("<tr><th>编号</th><th>姓名</th><th>帐号</th><th>密码</th><th>性别</th><th>年龄</th><th>出生日期</th><th>创建时间</th></tr>");
				//添加表格内容
				for(var i=0;i<result.length;i++){
					var row = result[i];
					$("#tt").append("<tr><td>"+row.id+"</td><td>"+row.name+"</td><td>"+row.username+"</td><td>"+row.password+"</td><td>"+row.sex+"</td><td>"+row.age+"</td><td>"+row.birthday+"</td><td>"+row.creatTime+"</td></tr>");
				}
			}
		});
	}
	
</script>
</head>
<body>
	
	<input type="button" value="获取姓名和性别" onclick="getNameAndSex();" /><br/><br/>
	<input type="button" value="获取学生列表" onclick="getStudentList();" />
	<table border="1" id="tt"></table>
</body>
</html>