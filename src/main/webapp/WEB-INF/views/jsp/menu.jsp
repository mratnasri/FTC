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
	
	<c:choose>
		<c:when test="${isLoggedIn != null}">
			<div class="col-sm-2 sidenav">
				<!-- <h4>Menu</h4> -->
				<ul class="nav nav-pills nav-stacked">
					<c:if test="${Type_of_user=='attendee'}">
						<li class="active"><a href="profile">profile</a></li>
						<li><a href="#section2">My Courses</a></li>
						
					</c:if>
					<c:if test="${Type_of_user=='organizer'}">
						<li class="active"><a href="profile">profile</a></li>
						<li><a href="#section2">My Courses</a></li>
						<li><a href="#section2">Create Course</a></li>
						<li><a href="#section3">Update Course</a></li>
						<li><a href="#section2">Create Subject</a></li>
						<li><a href="#section2">Create Enrollment Category</a></li>
						<li><a href="#section2">Create Qualification</a></li>
						<li><a href="#section2">Analysis Report</a></li>
						<li><a href="#section2">List Attendee data</a></li>
						<li><a href="#section2">Update Attendance</a></li>
					</c:if>

				</ul>
				<br>

			</div>
		</c:when>
	</c:choose>
</body>
</html>