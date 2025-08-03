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
	<div class="container">

		<!-- DISPLAY MSG -->
		<c:set var="successMsg"
			value="<%=session.getAttribute(\"successMsg\")%>" />
		<c:set var="errorMsg" value="<%=session.getAttribute(\"errorMsg\")%>" />
		<c:if test="${not empty successMsg}">
			<div class="alert alert-success text-center w-50 mx-auto mt-5"
				role="alert">${successMsg }</div>
		</c:if>
		<c:if test="${not empty errorMsg}">
			<div class="alert alert-danger text-center w-50 mx-auto mt-5"
				role="alert">${errorMsg }</div>
		</c:if>
		<c:remove var="errorMsg" />
		<c:remove var="successMsg" />

		<c:set value="<%=request.getAttribute(\"hotel\")%>" var="hotel" />

		<div class="d-flex flex-column w-50 mx-auto mt-5">
			<h3 class="mt-5 mb-3">${hotel.propertyName}</h3>

			<div class="card">
				<img src="${hotel.propertyUrl}" style="height: 30vh;"
					class="card-img-top" alt="Listing Images">
				<div class="card-body mt-3">

					<p class="card-text mb-3">${hotel.propertyDescription }</p>
					<p class="card-text mb-5">&#8377;${hotel.propertyPrice}/night</p>
					<c:url var="updateLink" value="/hotel/edit">
						<c:param name="hotel_id" value="${hotel.propertyId}"></c:param>

					</c:url>
					<c:url var="deleteLink" value="/hotel/remove">
						<c:param name="hotel_id" value="${hotel.propertyId}"></c:param>
					</c:url>
					<a href="${updateLink}" class="btn btn-outline-dark me-3 btn-lg"><i
						class="uil uil-edit"></i> Update</a> 
						<form action="${deleteLink}" method="post" class="mt-3">
							<button class="btn btn-outline-danger btn-lg"><i
						class="uil uil-trash-alt"></i> Delete</button>

						</form>
						
				</div>
			</div>
		</div>
		<hr class="mt-5 line w-75 mx-auto">


	</div>


	<jsp:include page="body_include.html" />
</body>
</html>