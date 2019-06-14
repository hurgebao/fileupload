<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	response.setHeader("Cache-Control", "no-cache"); //HTTP 1.1
	response.setHeader("Pragma", "no-cache"); //HTTP 1.0
	response.setDateHeader("Expires", 0);
	String url=request.getRequestURL().toString();
	String appContext=request.getContextPath();
	int fromindex=url.indexOf(appContext)+1;
	request.setAttribute("ctx_full", url.substring(0, url.indexOf("/",fromindex)));
%>
<c:set var="ctx" value="${pageContext.request.contextPath }"/>

