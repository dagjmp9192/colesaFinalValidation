<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="panel panel-success">
	<div class="panel-heading">
		<h3>Your profile</h3>
	</div>
	<div class="panel-body">
		<div class="container-fluid">
			<form action="updateProfile.spring" method="post"
				class="form-horizontal">
				<input type="hidden" name="id" value="${user.id}">
				<div class="form-group">
					<label>Title</label> <select name="title" class="form-control">
						<c:choose>
						
							<c:when test="${user.title eq 'Mrs.'}">
								<option value="Mr.">Mr.</option>
								<option value="Mrs." selected="true">Mrs.</option>
								<option value="Miss">Miss</option>
								<option value="Dr">Dr.</option>
							</c:when>
							<c:when test="${user.title eq 'Miss'}">
								<option value="Mr.">Mr.</option>
								<option value="Mrs.">Mrs.</option>
								<option value="Miss" selected="true">Miss</option>
								<option value="Dr">Dr.</option>
							</c:when>
							<c:otherwise>
								<option value="Mr.">Mr.</option>
								<option value="Mrs.">Mrs.</option>
								<option value="Miss">Miss</option>
								<option value="Dr" selected="true">Dr.</option>
							</c:otherwise>
						</c:choose>
					</select>
				</div>
				<div class="form-group">
					<label>First Name</label> <input type="text" name="firstName"
						class="form-control" value="${user.firstName}">
				</div>
				<div class="form-group">
					<label>Last Name</label> <input type="text" name="lastName"
						class="form-control" value="${user.lastName}">
				</div>
				<div class="form-group">
					<label>MailId</label> <input type="text" name="email"
						class="form-control" value="${user.email}">
				</div>
				<div class="form-group">
					<label>Mobile No.</label> <input type="text" name="mobileNo"
						class="form-control" value="${user.mobileNo}">
				</div>
				<div class="form-group">
					<label>City</label> <input type="text" name="city"
						class="form-control" value="${user.city}">
				</div>
				<c:if test="${user.userType==1}">
					<div class="form-group">
						<label>Your Expertise</label> <select name="expertise"
							class="form-control">
							<c:choose>
								<c:when test="${user.expertise==1}">
									<option value="1" selected="true">Civil</option>
									<option value="2">Criminal</option>
									<option value="3">Company</option>
									<option value="4">Divorce</option>
									<option value="5">Juvenile</option>
								</c:when>
								<c:when test="${user.expertise==2}">
									<option value="1">Civil</option>
									<option value="2" selected="true">Criminal</option>
									<option value="3">Company</option>
									<option value="4">Divorce</option>
									<option value="5">Juvenile</option>
								</c:when>
								<c:when test="${user.expertise==3}">
									<option value="1">Civil</option>
									<option value="2">Criminal</option>
									<option value="3" selected="true">Company</option>
									<option value="4">Divorce</option>
									<option value="5">Juvenile</option>
								</c:when>
								<c:when test="${user.expertise==4}">
									<option value="1">Civil</option>
									<option value="2">Criminal</option>
									<option value="3">Company</option>
									<option value="4" selected="true">Divorce</option>
									<option value="5">Juvenile</option>
								</c:when>
								<c:otherwise>
									<option value="1">Civil</option>
									<option value="2">Criminal</option>
									<option value="3">Company</option>
									<option value="4">Divorce</option>
									<option value="5" selected="true">Juvenile</option>
								</c:otherwise>
							</c:choose>
						</select>
					</div>
					<div class="form-group">
						<label>Court</label> <select name="court" class="form-control">
							<option value="1">Session
							<option value="2">High Court
							<option value="3">Supreme Court
							<option value="4">Consumer Court
							<option value="5">Juvenile Court
						</select>
					</div>
				</c:if>
				<div class="form-group">
					<label>Profile Info</label>
					<textarea name="profile" class="form-control">
${user.profile}
</textarea>
				</div>
				<div class="form-group">
					<button class="btn btn-primary">Update</button>
				</div>
			</form>
		</div>
	</div>
</div>