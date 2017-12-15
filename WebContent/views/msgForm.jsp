<div class="panel panel-success">
	<div class="panel-heading">
		<h3>You are asking question to ${receiverName}</h3>
	</div>
	<div class="panel-body">
		<div class="container-fluid">
			<form action="sendMessage.spring" method="post"
				class="form-horizontal">
				<input type="hidden" name="sender" value="${user.id}"> <input
					type="hidden" name="receiver" value="${receiver}">
				<div class="form-group">
					<label>Title</label> <input type="text" name="title"
						class="form-control" placeholder="Message Title">
				</div>
				<div class="form-group">
					<label>Text</label>
					<textarea name="text" class="form-control"
						placeholder="Message Text"></textarea>
				</div>
				<div class="form-group">
					<button class="btn btn-primary">Send</button>
				</div>
			</form>
		</div>
	</div>
</div>