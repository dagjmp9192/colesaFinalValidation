<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:forEach items="${messages}" var="msg">
	<div class="panel panel-success">
		<div class="panel-heading">
			<div class="row">
				<div class="col-md-6">
					<h3>${msg.title}</h3>
				</div>
				<div class="col-md-5 col-md-offset-1">
					<br /> <span class="text-danger"><b>Asked to:
							${msg.receiverName} on ${msg.msgDate}</b></span>
				</div>
			</div>
		</div>
		<div class="panel-body">
			<div class="container-fluid">
				<div class="form-group">
					<label>Question:</label>
					<textarea readonly class="form-control">${msg.text}</textarea>
				</div>
				<h4>Replies</h4>
				<c:choose>
					<c:when test="${fn:length(msg.replies) gt 0}">
						<c:forEach items="${msg.replies}" var="rep">
							<div class="form-group">
								<textarea readonly class="form-control">${rep.text}</textarea>
								<span class="text-warning pull-right"><b>Sent On
										${rep.date}</b></span>
							</div>
						</c:forEach>
					</c:when>
					<c:otherwise>
						<span class="text-warning"><b>Don't have any replies.</b></span>
					</c:otherwise>
				</c:choose>
			</div>
		</div>
	</div>
</c:forEach>
