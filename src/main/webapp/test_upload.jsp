<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp" %>
<%-- <% 
	response.sendRedirect("swagger/home.jsp");
%> --%>
<!DOCTYPE img PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
	<form method="post" action="${ctx}/uploadFile?a=1~2~3" enctype="multipart/form-data">
		<input type="file" name="filename" >
		<input type="submit" value="上传" />
	</form>
</body>
</html>