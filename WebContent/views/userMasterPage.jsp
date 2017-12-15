<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
		<!--header row ends -->
		<hr />
		<div class="row">
			<div class="col-md-2">
				<c:choose>
					<c:when test="${user.userType ==1 }">
						<jsp:include page="sideMenuLawyer.jsp" />
					</c:when>
					<c:when test="${user.userType ==2 }">
						<jsp:include page="sideMenuClient.jsp" />
					</c:when>
					<c:otherwise>
						<jsp:include page="sideMenuAdmin.jsp" />
					</c:otherwise>
				</c:choose>
			</div>
			<div class="col-md-9 col-md-offset-1">
				<tiles:insertAttribute name="contents" />
			</div>
		</div>
		<!--contents row ends -->
		<hr />
		<div class="row">
			<tiles:insertAttribute name="footer" />
		</div>
		<!--footer row ends -->
	</div>
	<!--container div ends -->
</body>

</html>