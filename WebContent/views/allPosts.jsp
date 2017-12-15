<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:choose>
	<c:when test="${fn:length(posts) gt 0}">

		<c:forEach items="${posts}" var="post">
			<div class="panel panel-primary">
				<div class="panel panel-heading">
					<b>${post.title}</b> <span class="pull-right"><b>posted
							on ${post.date}</b> &nbsp;by <b>${post.uname}</b></span>
				</div>

				<div class="panel-body">
					<form action="deletePost.spring">
						<input type="hidden" name="id" value="${post.id}">
						<div class="from-group">
							<label>Contents:</label>
							<textarea readonly rows="3" cols="50" class="form-control">
${post.contents}
</textarea>
						</div>

						<div class="form-group">
							<label>Comments</label>
							<c:forEach items="${post.comments}" var="comment">

								<div class="bg-warning">

									<p style="margin: 10px; padding: 10px;">
										<strong>${comment.contents}</strong>
									</p>
									<p style="padding-left: 500px; padding-bottom: 5px;">
										Submitted by <b>${comment.uname}</b> &nbsp;on <b>${comment.date}</b>
										<a href="deleteComment.spring?id=${comment.id}">&nbsp;delete</a>
									</p>


								</div>

							</c:forEach>

						</div>
						<div class="form-group">
							<button class="btn btn-info btn-small">Remove Post</button>
						</div>
					</form>
				</div>
			</div>
		</c:forEach>
	</c:when>
	<c:otherwise>
		<div class="alert alert-warning">
			<h3>No posts are found.</h3>
		</div>
	</c:otherwise>
</c:choose>