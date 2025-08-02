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
		<h3 class="my-5 text-center">All Hotels</h3>


		<div
			class="row row-cols-xl-4 row-cols-lg-3 row-cols-md-3 row-cols-sm-2 row-cols-1 ">
			<!-- FOR LOOP -->
			<c:set value="<%=request.getAttribute(\"hotels\")%>" var="hotels"></c:set>

			<c:forEach items="${hotels}" var="theHotel">
				<div class="col my-2">

					<c:url var="showLink" value="hotel">
						<c:param name="hotel_id" value="${theHotel.propertyId}" />
					</c:url>
					<a href="${showLink}" class="listing-link">
						<div class="card">
							<img src="${theHotel.propertyUrl}" class="card-img-top"
								alt="Listing Images">
							<div class="card-img-overlay">
								<h5 class="card-title">${theHotel.propertyName}</h5>
							</div>
							<div class="card-body">
								<h5 class="card-title">${theHotel.propertyName}</h5>
								<p class="card-text">&#8377;${theHotel.propertyPrice}/night</p>
							</div>
						</div>
					</a>
				</div>
			</c:forEach>
		</div>




	</div>


	<jsp:include page="body_include.html" />
</body>
</html>