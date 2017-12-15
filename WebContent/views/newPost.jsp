<div class="panel panel-primary">
<div class="panel-heading">
<h3>What In Your Mind</h3>
</div>
<div class="panel-body">
<div class="container-fluid">
<form action="savePost.spring" method="post" class="form-horizontal">
<div class="form-group">
<label>Title</label>
<input type="hidden" name="userId" value="${from.id}"/>
<input class="form-control" type="text" name="title">
</div>

<div class="form-group">
<label>Description</label>
<textarea class="form-control" 
name="contents" cols="300" rows="10">
</textarea>
</div>
<div class="form-group">
<button type="submit" class="btn btn-primary">Submit</button>
</div>
</form>
</div>
</div>
</div>