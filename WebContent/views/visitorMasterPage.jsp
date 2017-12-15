<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<html>
<head>
<title>Colesa: Company &amp; Legal Services Aggregator</title>
<jsp:include page="resources.jsp" />
</head>
<body>
	<div class="container">
		<div class="row">
			<tiles:insertAttribute name="header" />
		</div>
		<!--*******************Header row ends******************* -->
		<hr />
		<div class="row">
			<tiles:insertAttribute name="contents" />
		</div>
		<!--*******************Contents row ends***************** -->
		<hr />
		<div class="row">
			<tiles:insertAttribute name="footer" />
		</div>
		<!--*******************Footer row ends******************** -->
	</div>
	<!--container div ends -->
</body>

</html>