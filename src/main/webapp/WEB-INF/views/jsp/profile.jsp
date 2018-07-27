<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="true"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Profile</title>
</head>
<body>


<div class="table-responsive">
<p align="center"><b>Profile</b> 

<a href="editProfile" class="btn btn-info btn-sm">
          <span class="glyphicon glyphicon-edit"></span> Edit
        </a>
        </p>
        
<table class="table">

        <tr>
            <td>Username: </td><td> ${profile.username}</td>
        </tr>
        <tr>
            <td>Gender: </td><td> ${profile.gender}</td>
        </tr>
        <tr>
            <td>Address: </td><td> ${profile.address}</td>
        </tr>
        <tr>
            <td>Hobbies: </td><td> ${profile.hobbies}</td>
        </tr>
        <tr>
            <td>Skills: </td><td> ${profile.skills}</td>
        </tr>
        <tr>
            <td>Specialization: </td><td> ${profile.specialization}</td>
        </tr>
        <tr>
            <td>Qualification: </td><td> <c:forEach var= "qualification" items= "${quals}">
						  <c:if test="${qualification.id==profile.qualId}">${qualification.name}</c:if>
						</c:forEach></td>
        </tr>
        <tr>
            <td>Organization: </td><td> ${profile.organization} </td>
        </tr>
        <tr>
            <td>Designation: </td><td> ${profile.designation} </td>
        </tr>

    </table>
    </div>

</body>
</html>