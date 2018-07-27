<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="true"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

</head>
<body>
<div class="container-fluid text-center">    
 			
    <div class="col-sm-10">
    <h1> ${course.course_name}</h1>
    <div align="center"><img src="<c:url value="/resources/images/${course.banner_path}"/>" class="img-responsive" style="width:50%" align = "middle" alt="Image"></div>
    <h3><b>${course.start_date} to ${course.end_date}</b></h3>
    <%-- <p>${course.tagline}</p> --%>
    <div align= "justify">
    <c:forEach var= "para" items= "${description}">
     <p>${para}</p>
    </c:forEach>
    </div>
    <c:if test="${sessionScope.Type_of_user=='attendee'}">
    <a href="enroll" class="btn btn-info">Enroll for the Course</a>
  <a href="payment" class="btn btn-info">Proceed for Payment</a>
  </c:if>
  </div>
  </div>
    <br>
</body>
</html>