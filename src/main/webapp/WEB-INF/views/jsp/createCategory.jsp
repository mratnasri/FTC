<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="true"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<c:if test="${sessionScope.Type_of_user=='organizer'}">
	
	<h1 align="center">Create Enrollment Category</h1>

	<br>
<h5 align = "center" style="color: green;">${successMsg}</h5>
<h5 align = "center" style="color: red;">${errorMsg}</h5>
	<form Class="form-horizontal" action="saveCategory" method="post" autocomplete="off">
		<fieldset>
	<div class="form-group">

				<label class="control-label col-sm-4" for="name">Enrollment Category Name:
				</label> 
				<div class="col-sm-4">
				<input placeholder="Enrollment Category Name" class="form-control" id="name"
					name="name" type="text" required autofocus/>
             </div>
			</div>

			<div class="form-group">

				<label class="control-label col-sm-4" for="description">Enrollment Category Description:
				</label> 
				<div style= height:auto; class="col-sm-4">
				<input placeholder="Enrollment Category Description" class="form-control" name="description"
					type="text" />
				</div>

			</div>
			 <div class="form-group"> 
			<div class="col-sm-offset-4 col-sm-8">
			<button type="Submit" class="btn btn-default">Create</button>
			</div>
			</div>
			<br>
			
		</fieldset>
	</form>
</c:if>
</body>
</html>