<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="col-md-2">
	<img src="images/colesa.png" alt="Colesa">
</div>
<div class="col-md-3 col-md-offset-3">
	<br />
	<br />
	<h4>
		<span class="glyphicon glyphicon-phone"></span> &nbsp;0120-454-9462
	</h4>
</div>
<div class="col-md-2 col-md-offset-2">
	<br />
	<br /> <span class="glyphicon glyphicon-user"></span>&nbsp;
	${user.firstName}&nbsp;${user.lastName}
	<c:choose>
		<c:when test="${user.userType ==1}">
			<span class="text-primary">&nbsp;(Lawyer)</span>
		</c:when>
		<c:when test="${user.userType ==2}">
			<span class="text-primary">&nbsp;(client)</span>
		</c:when>
		<c:otherwise>
			<span class="text-primary">&nbsp;(Admin)</span>
		</c:otherwise>
	</c:choose>
	</a>
</div>