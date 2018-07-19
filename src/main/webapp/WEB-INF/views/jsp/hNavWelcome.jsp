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
	<c:if test="${isLoggedIn != null}">
		
			<div class="container-fluid">
			
				
					<h5 align="center">Welcome ${sessionScope.firstName}!</h5>
				
				
			</div>
		
	</c:if>

</body>
</html>