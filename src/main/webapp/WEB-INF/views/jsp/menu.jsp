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
					    <li class="active"><a href="#menu">Menu</a></li>
						<li><a href="profile">profile</a></li>
						<li><a href="myCourses">My Courses</a></li>
					</c:if>
					<c:if test="${Type_of_user=='organizer'}">
					    <li class="active"><a href="#menu">Menu</a></li>
						<li><a href="profile">profile</a></li>
						<li><a href="myCourses">My Courses</a></li>
						<li><a href="createCourse">Create Course</a></li>
						<li><a href="updateCourse">Update Course</a></li>
						<li><a href="createSubject">Create Subject</a></li>
						<li><a href="createCategory">Create Enrollment Category</a></li>
						<li><a href="createQualification">Create Qualification</a></li>
						<li><a href="analysisReport">Analysis Report</a></li>
						<li><a href="attendeeData">List Attendee data</a></li>
						<li><a href="updateAttendance">Update Attendance</a></li>
					</c:if>

				</ul>
				<br>

			</div>
		</c:when>
	</c:choose>
</body>
</html>