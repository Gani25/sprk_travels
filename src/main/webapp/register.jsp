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
		<div class="w-75 mx-auto mt-5">
		<c:set var="hotel" value="<%=request.getAttribute(\"hotel\") %>" />
			<form method="post" action="property">
				<div class="row">
					<div class="mb-4 col">
						<label for="title" class="form-label">Property Name<span
							class="text-danger">*</span>
						</label> <input type="text" class="form-control" id="title"
							name="property_name" value="${hotel.propertyName }">
							 
							<c:set var="nameError" value="<%=request.getAttribute(\"nameError\")%>"/> 
							<c:if test="${not empty nameError }">
							<div class="text-danger fs-6">${nameError}</div>

						</c:if>
					</div>
					<div class="mb-4 col">
						<label for="link" class="form-label">Property Image Url<span
							class="text-danger">*</span>
						</label> <input type="text" class="form-control" id="link"
							name="property_url" value="${hotel.propertyUrl }">
							<c:set var="urlError" value="<%=request.getAttribute(\"urlError\")%>"/> 
							<c:if test="${not empty urlError }">
							<div class="text-danger fs-6">${urlError}</div>

						</c:if>
					</div>
					<div class="mb-4 col">
						<label for="price" class="form-label">Property Price<span
							class="text-danger">*</span>
						</label> <input type="text" class="form-control" id="link"
							name="property_price"
							value="${hotel.propertyPrice}">
							<c:set var="priceError" value="<%=request.getAttribute(\"priceError\")%>"/> 
							<c:if test="${not empty priceError }">
							<div class="text-danger fs-6">${priceError}</div>

						</c:if>
					</div>
				</div>
				<div class="mb-4">
					<label for="desc" class="form-label">Property Description<span
						class="text-danger">*</span>
					</label>
					<textarea class="form-control" id="desc"
						name="property_description" rows="5">${hotel.propertyDescription}</textarea>
						<c:set var="descError" value="<%=request.getAttribute(\"descError\")%>"/> 
							<c:if test="${not empty descError }">
							<div class="text-danger fs-6">${descError}</div>

						</c:if>
				</div>
				<div class="text-center">
					<button type="submit" class="btn btn-outline-dark">Save
						Property</button>
				</div>
			</form>
		</div>

	</div>


	<jsp:include page="body_include.html" />
</body>
</html>