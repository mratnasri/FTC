<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

		<h6 align = "center" style="color: green;">${successMsg}</h6>
	
	<h1 align="center">Login</h1>

	<br>
<h6 align = "center" style="color: red;">${errorMsg}</h6>
	<form Class="form-horizontal" action="dologin" method="post" autocomplete="off">
		<fieldset>
	<div class="form-group">

				<label class="control-label col-sm-4" for="username">Username:
				</label> 
				<div class="col-sm-4">
				<input placeholder="Email ID" class="form-control" id="username"
					name="username" type="email" required
					pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,3}$" autofocus/>
             </div>
			</div>

			<div class="form-group">

				<label class="control-label col-sm-4" for="password">Password:
				</label> 
				<div class="col-sm-4">
				<input placeholder="Password" class="form-control" name="password"
					type="password" value="" required/>
				</div>

			</div>
			 <div class="form-group"> 
			<div class="col-sm-offset-4 col-sm-8">
			<button type="Submit" class="btn btn-default">Login</button>
			</div>
			<div class="col-sm-offset-4 col-sm-8">
			<a href="#recoverPassword" >Forgot Password</a>
			</div>
			</div>
			<br>
			
		</fieldset>
	</form>
</body>
</html>