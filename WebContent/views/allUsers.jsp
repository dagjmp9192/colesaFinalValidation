<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:choose>
	<c:when test="${fn:length(users) gt 0}">
		<div class="table-responsive">
			<table class="table table-bordered table-hover table-striped">

				<thead>
					<tr class="info">
						<th>Title</th>
						<th>FirstName</th>
						<th>LastName</th>
						<th>Role</th>
						<th>Action</th>
					</tr>
				</thead>
				<c:forEach items="${users}" var="cuser">
					<tbody>
						<tr>
							<td>${cuser.title}</td>
							<td>${cuser.firstName}</td>
							<td>${cuser.lastName}</td>
							<td><c:choose>
									<c:when test="${cuser.userType ==1}">Lawyer
                    </c:when>
									<c:otherwise>Client
                    </c:otherwise>
								</c:choose></td>
							<td>
								<form action="deleteUser.spring" method="post">
									<input type="hidden" name="id" value="${cuser.id}" />
									<div class="from-group">
										<button class="btn btn-warning btn-small">Delete</button>
									</div>
								</form>
							</td>
						</tr>
					</tbody>

				</c:forEach>
			</table>
		</div>
	</c:when>
	<c:otherwise>
		<div class="alert alert-warning">
			<h3>No users are found.</h3>
		</div>
	</c:otherwise>
</c:choose>