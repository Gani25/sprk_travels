<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
			<form method="post" action="property">
			<div class="row">
				<div class="mb-4 col">
					<label for="title" class="form-label">Property Name</label> <input
						type="text" class="form-control" id="title" name="property_name">
				</div>
				<div class="mb-4 col">
					<label for="link" class="form-label">Property Image Url</label> <input
						type="text" class="form-control" id="link" name="property_url">
				</div>
			</div>
				<div class="mb-4">
					<label for="desc" class="form-label">Property Description</label>
					<textarea class="form-control" id="desc"
						name="property_description" rows="3"></textarea>
				</div>
				<div class="text-center">
				<button type="submit" class="btn btn-outline-dark">Save Property</button>
				</div>
			</form>
		</div>
		
	</div>


	<jsp:include page="body_include.html" />
</body>
</html>