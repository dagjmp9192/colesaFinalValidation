<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:forEach items="${lawyers}" var="lawyer">
	<div class="panel panel-success">
		<div class="panel-heading">
			<div class="row">
				<div class="col-md-4">
					<h3>${lawyer.title}&nbsp;${lawyer.firstName}
						&nbsp;${lawyer.lastName}</h3>
				</div>
				<div class="col-md-5 col-md-offset-3">
					<br /> <span class="text-warning"><b>${lawyer.displayExpertise}</b></span>&nbsp;
					<span class="text-primary"><b>${lawyer.displayCourt}</b></span>&nbsp;
					<span class="text-danger"><b>${lawyer.city}</b></span>
				</div>
			</div>
		</div>
		<div class="panel-body">
			<div class="row">
				<div class="col-md-3">
					<div class="thumbnail">
						<img src="uploads/${lawyer.imageUrl}" alt="Profile Image">
					</div>
				</div>
				<div class="col-md-6">
					<div class="form-group">
						<label>Profile Description</label>
						<textarea class="form-control" readonly>
${lawyer.profile}
</textarea>
					</div>
				</div>
			</div>
		</div>
		<div class="panel-footer">
			<a href="msgForm.spring?receiver=${lawyer.id}"
				class="btn btn-warning">Ask Question</a>
		</div>
	</div>
</c:forEach>