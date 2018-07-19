<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page session="true"%>
<%@page import="com.cdac.model.Profile"%>
<%@page import="com.cdac.model.Qualification"%>


<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Profile</title>
</head>
<body>

	<c:if test="${sessionScope.isLoggedIn}">
		<div class="table-responsive">
		<div class="well">
			<p align="center">
				<b>Profile</b>

			</p>
			
			<h6 align="center" style="color: red;">${errorMsg}</h6>
			<form Class="form-horizontal" action="saveProfile" method="post" >
				

					<div class="form-group">
						<label class="control-label col-sm-4" for="username">Username:</label>
						<div class="col-sm-4">
						 <input  class="form-control" name="username" type="text" value="${username}" required /> 
						 </div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-4" for="gender">Gender:</label>
						<div class="col-sm-4">
						 <input  class="form-control" name="gender" type="text" value="${profile.gender}" /> 
						 </div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-4" for="address">Address:</label>
						<div class="col-sm-4">
						 <input  class="form-control" name="address" type="text" value="${profile.address}" /> 
						 </div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-4" for="hobbies">Hobbies:</label>
						<div class="col-sm-4">
						 <input class="form-control" name="hobbies" type="text" value="${profile.hobbies}" /> 
						 </div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-4" for="skills">Skills:</label>
						<div class="col-sm-4">
						 <input name="skills" class="form-control" name="skills" type="text" value="${profile.skills}" /> 
					</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-4" for="specialization">Specialization:</label>
						<div class="col-sm-4">
						 <input class="form-control" name="specialization" type="text" value="${profile.specialization}" />
						 </div> 
					</div>
					<div class="form-group">
						<label class="control-label col-sm-4" for="qualId">Qualification:</label>
						<%--  <form:select  class="form-control" name="qualId" 
						 path="qualId" itemValue="${profile.qualId}"> 
						 <form:option value="-1" label="Select Qualification"/>
						<form:options items="${qual}" />
						</form:select> --%>
						<div class="col-sm-4">
						<select  class="form-control" name="qualId">
						<option value="-1" label="Select Qualification"/>
						<c:forEach var= "qualification" items= "${quals}">
						 <option value= "${qualification.id}" label= "${qualification.name}" <c:if test="${qualification.id==profile.qualId}">selected</c:if>>${qualification.name}</option>
						</c:forEach>
						</select>
						</div>
						 
					</div>
					<div class="form-group">
						<label class="control-label col-sm-4" for="organization">Organization:</label>
						<div class="col-sm-4">
						 <input  class="form-control" name="organization" type="text" value="${profile.organization}" /> 
						 </div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-4" for="designation">Designation:</label>
						<div class="col-sm-4">
						 <input  class="form-control" name="designation" type="text" value="${profile.designation} " /> 
						 </div>
					</div>
					<div class="form-group">
				<div class="col-sm-offset-4 col-sm-8">
					<button type="Submit" class="btn btn-default" id="save">Save</button>
					</div>
					</div>
				


			</form>
			</div>
		</div>
	</c:if>
</body>
</html>