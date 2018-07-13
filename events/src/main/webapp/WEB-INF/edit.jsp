<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="/css/bootstrap.min.css">
	<link rel="stylesheet" href="/css/style.css">
</head>
<body>
<%@ page isErrorPage="true" %>    
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>     
<h1>Edit Book</h1>
<form:form action="/events/${event.id}/edit" method="post" modelAttribute="event">
    <input type="hidden" name="_method" value="put">
     <p>
         <form:label path="name">Name:</form:label>
         <form:input path="name"/>
         <form:errors path="name" class="red"/>
     </p>
     <p>
         <form:label path="city">Location:</form:label>
         <form:input path="city"/>
         <form:errors path="city" class="red"/>
     </p>
     <p>
         <form:label path="state">State:</form:label>
         <form:select path="state">
             <c:forEach items="${states}" var="state">
                 <form:option value="${state}">${state}</form:option>
             </c:forEach>
         </form:select>
     </p>
     <p>
         <form:label path="date">Date:</form:label>
         <form:input type="date" path="date"/>
         <form:errors path="date" class="red"/>
     </p>
        <form:input type="hidden" path="poster" value="${event.poster.id}"/>
        <input type="submit" value="Update"/>
</form:form>
</body>
</html>