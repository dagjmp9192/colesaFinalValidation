<%@ taglib uri="http://java.sun.com/jsp/jstl/core" 
prefix="c" %>

<div class="panel panel-success">
<div class="panel-heading">
<div class="row">
<div class="col-md-4">
<h3>${user.title}&nbsp;${user.firstName}
&nbsp;${user.lastName}</h3>
</div>
<div class="col-md-2 col-md-offset-6">
<br/>
<h4 class="text-danger pull-right">${user.city}</h4>
</div>
</div>
</div>
<div class="panel-body">
<div class="container-fluid">
<div class="row">
<div class="col-md-2">
<div class="thumbnail">
<img src="uploads/${user.imageUrl}" alt="Profile Image">
</div>
<form class="form-horizontal" method="post" 
enctype="multipart/form-data"
action="uploadImg.spring">
<div class="form-group">
<input type="file" name="profileImage">
</div>
<div class="form-group">
<button type="submit" class="btn btn-success">upload</button>
</div>
</form>
</div>
<div class="col-md-8 col-md-offset-2">
<div class="form-group">
<label>Profile Description</label>
<textarea class="form-control" readonly>
${user.profile}
</textarea>
</div>
 
</div>
</div>

</div>
</div>
<div class="panel-footer">
<a href="editProfile.spring" class="btn btn-warning">Edit Profile</a>
</div>
</div>