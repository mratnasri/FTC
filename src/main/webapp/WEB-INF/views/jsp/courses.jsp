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
<div id="div" class="container-fluid text-center">    
  <h2>Upcoming Courses</h2><br>

<c:forEach var= "course" items= "${courses}">
					
    <div class="col-sm-3">
    <h3>
   <a href="course?selectedCourse=${course.course_id}">
    <img src='<c:url value = "/resources/images/${course.banner_path}"/>' class="img-responsive" style="width:100%" alt="Image">
   ${course.course_name}</a></h3>
   
    <h4><b>${course.start_date} to ${course.end_date}</b></h4>
    <div align="justify">
    <p>${course.tagline}</p>
    </div>
      <%-- <pre>${course.description}</pre>   --%>   
    </div>
    
   </c:forEach>

  </div>
  
<script type="text/javascript">
		
			  /* function addCourseId() {
				
				var input = document.createElement("input");
				input.setAttribute("name", "selectedCourse");
				input.setAttribute("id", "selectedCourse");
				input.setAttribute("value",document.getElementById());
				document.getElementById("div").appendChild(input);
				
			} */
</script>
</body>
</html>