<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>SPRK TRAVELS</title>
<!-- LINK HEAD -->
<jsp:include page="head_include.html" />
</head>
<body>
	<jsp:include page="navbar.jsp" />
	<div class="container-fluid">

		<!-- DISPLAY MSG -->
		<c:set var="successMsg"
			value="<%=session.getAttribute(\"successMsg\")%>" />
		<c:set var="errorMsg" value="<%=session.getAttribute(\"errorMsg\")%>" />
		<c:if test="${not empty successMsg}">
			<div class="alert alert-success text-center w-50 mx-auto mt-5" role="alert">${successMsg }</div>
		</c:if>
		<c:if test="${not empty errorMsg}">
			<div class="alert alert-danger text-center w-50 mx-auto mt-5" role="alert">${errorMsg }</div>
		</c:if>
		<c:remove var="errorMsg"/>
		<c:remove var="successMsg"/>



	</div>


	<jsp:include page="body_include.html" />
</body>
</html>