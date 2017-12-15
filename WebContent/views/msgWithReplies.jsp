<%@ taglib uri="http://java.sun.com/jsp/jstl/core" 
prefix="c" %>


<div class="panel panel-success">
<div class="panel-heading">
<div class="row">
<div class="col-md-6">
<h3>${message.title}</h3>
</div>
<div class="col-md-5 col-md-offset-1">
<br/>
<span class="text-danger"><b>Asked to: ${message.receiverName} on ${message.msgDate}</b></span>
</div>
</div>
</div>
<div class="panel-body">
<div class="form-group">
<label>Question:</label>
<textarea readonly class="form-control">${message.text}</textarea>
</div>
<c:forEach items="${replies}" var="reply">
<div class="form-group">
<label>Replies:</label>
<textarea readonly class="form-control">${reply.text}</textarea>
</div>
</c:forEach>
</div>
