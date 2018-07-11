<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
        <%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>New License</title>
  <link rel="stylesheet" type="text/css" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css">

<style><%@include file="/resources/css/style.css"%></style>
</head>
<body>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>    
<h1>New License</h1>
<form:form action="/licenses/new" method="post" modelAttribute="license">
    <p>
        <form:select  path="person">
            <form:option value="none">--SELECT--</form:option>
        	<c:forEach items="${persons}" var="person">
            	<form:option value="${person}">${person.firstName} ${person.lastName }</form:option>
        	</c:forEach>
        </form:select>
    </p>
    <p>
        <form:label path="state">State</form:label>
        <form:errors path="state" class="red"/>
        <form:input path="state" class="form-control"/>
    </p>
    <p>
        <form:label path="expiration_date">Expiration Date</form:label>
        <form:errors path="expiration_date" class="red"/>
        <form:input type="date" path="expiration_date" class="form-control"/>
    </p>    
    <input type="submit" value="Submit" class="btn"/>
</form:form>   
</body>
</html>