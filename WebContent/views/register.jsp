<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<div class="panel panel-success">
	<div class="panel-heading">
		<h3>New User sign up</h3>
	</div>
	<div class="panel-body">
		<div class="container-fluid">
			<form:form action="register.spring" method="post"
				modelAttribute="user" class="form-horizontal">
				<div class="form-group">
					<label>Title</label>
					<form:select path="title" class="form-control">
						<option value="Mr.">Mr.
						<option value="Mrs.">Mrs.
						<option value="Miss">Miss
						<option value="Dr">Dr.
					</form:select>
				</div>
				<div class="form-group">
					<label>First Name</label>
					<form:input type="text" path="firstName" class="form-control"
						placeholder="First Name" />
					<span class="text-danger"> <form:errors path="firstName" /></span>
				</div>
				<div class="form-group">
					<label>Last Name</label>
					<form:input type="text" path="lastName" class="form-control"
						placeholder="Last Name" />
					<span class="text-danger"> <form:errors path="lastName" /></span>
				</div>
				<div class="form-group">
					<label>MailId</label>
					<form:input type="text" path="email" class="form-control"
						placeholder="MailId" />
					<span class="text-danger"> <form:errors path="email" /></span>
				</div>
				<div class="form-group">
					<label>Password</label>
					<form:input type="password" path="password" class="form-control"
						placeholder="Password" />
					<span class="text-danger"> <form:errors path="password" /></span>
				</div>
				<div class="form-group">
					<label>Register Me as</label>
					<form:select path="userType" class="form-control">
						<option value="1">Professional
						<option value="2">Client
					</form:select>
				</div>
				<div class="form-group">
					<button type="submit" class="btn btn-primary">Register</button>
				</div>
			</form:form>
		</div>
	</div>

