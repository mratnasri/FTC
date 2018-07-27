
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

	<nav class="navbar navbar-inverse">
	<div class="container-fluid">
		<div class="navbar-header">
			<!--  <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>                        
      </button> -->
			<a class="navbar-brand" href="#">Logo</a>
		</div>

		<div class="collapse navbar-collapse" id="myNavbar">
			<ul class="nav navbar-nav">
				<li><a href="home">Home</a></li>
				
				<li><a href="courses">Courses</a></li>
				<li><a href="contact">Contact</a></li>
			</ul>

			<form class="navbar-form navbar-right" action="/action_page.php">
				<div class="form-group">
					<input type="text" class="form-control " placeholder="Search"
						name="search">
				</div>
				<button type="submit" class="btn btn-default">
					<span class="glyphicon glyphicon-search"></span>
				</button>
			</form>
				
					<c:choose>
					<c:when test="${isLoggedIn != null}">
					<ul class="nav navbar-nav navbar-right">
						<li><a href="logout"><span
								class="glyphicon glyphicon-log-out"></span> Logout</a></li>
								</ul>
					</c:when>
					
					<c:otherwise>
<ul class="nav navbar-nav navbar-right">
						<li><a href="login"><span
								class="glyphicon glyphicon-log-in"></span> Login</a></li>
						<li><a href="registration"><span
								class="glyphicon glyphicon-user"></span> Register</a></li>
</ul>
					</c:otherwise>
		</c:choose>

			</div>
	</div>
	</nav>
</body>
</html>