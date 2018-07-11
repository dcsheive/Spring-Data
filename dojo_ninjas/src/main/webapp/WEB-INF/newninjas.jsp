<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
        <%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>New Ninja</title>
  <link rel="stylesheet" type="text/css" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css">

<style><%@include file="/resources/css/style.css"%></style>
</head>
<body>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>    
<h1>New Ninja</h1>
<form:form action="/ninjas/new" method="post" modelAttribute="ninja">
    <p>
        <form:label path="dojo">Dojo </form:label>
        <form:errors path="dojo" class="red"/>
        <form:select  path="dojo">
            <form:option value="none">--Select--</form:option>
        	<c:forEach items="${dojos}" var="dojo">
            	<form:option value="${dojo.id}">${dojo.name}</form:option>
        	</c:forEach>
        </form:select>
    </p>
    <p>
        <form:label path="firstName">First Name</form:label>
        <form:errors path="firstName" class="red"/>
        <form:input path="firstName" class="form-control"/>
    </p>
    <p>
        <form:label path="lastName">Last Name</form:label>
        <form:errors path="lastName" class="red"/>
        <form:input path="lastName" class="form-control"/>
    </p>
    <p>
        <form:label path="age">Age</form:label>
        <form:errors path="age" class="red"/>
        <form:input type="number" path="age" class="form-control"/>
    </p>    
    <input type="submit" value="Submit" class="btn"/>
</form:form>   
</body>
</html>