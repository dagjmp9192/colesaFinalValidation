<html>
<head>
<jsp:include page="resources.jsp" />
<title>Colesa</title>
</head>
<body>
	<div class="container">
		<div class="row">
			<jsp:include page="loginInline.jsp" />
		</div>
		<!-- header ends -->
		<hr />
		<div class="row">
			<div class="col-md-5">
				<img src="images/law.jpg" width="140%" height="80%" />
			</div>
			<div class="col-md-5 col-md-offset-2">
				<jsp:include page="register.jsp" />
			</div>
		</div>
		<!-- contents div ends -->
		<hr />
		<div class="row">
			<jsp:include page="footer.jsp" />
		</div>
		<!-- footer div ends -->

	</div>
	<!-- container ends -->
</body>
</html>