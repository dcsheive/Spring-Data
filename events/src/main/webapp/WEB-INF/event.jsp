<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>   
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Welcome</title>
	<link rel="stylesheet" href="/css/bootstrap.min.css">
	<link rel="stylesheet" href="/css/style.css">
<body>
	<div class="box">
		<a href="/events">Home</a>
		<h1><c:out value="${event.name}"/></h1>
		<p>Host: <c:out value="${event.poster.firstName}"/> <c:out value="${event.poster.lastName}"/></p>	
		<p>Date: <fmt:formatDate pattern="MMMMMMM dd, yyyy" value="${event.date}" /></p>	
		<p>Location: <c:out value="${event.city}"/>, <c:out value="${event.state}"/></p>	
		<p>People who are joining this trip: <c:out value="${joiners}"/></p>
	<c:if test="${!event.joiners.isEmpty()}">
		<table class="table">
	    <thead>
	        <tr>
	            <th>Name</th>
	            <th>Location</th>
	        </tr>
	    </thead>
	    <tbody>
	        <c:forEach items="${event.joiners}" var="join">
	        <tr>
	            <td><c:out value="${join.firstName}"/> <c:out value="${join.lastName}"/></td>
	            <td><c:out value="${join.city}"/>, <c:out value="${join.state}"/></td>
	        </tr>
	        </c:forEach>
	    </tbody>
	  	</table>
	</c:if>
	</div>
	
    <div class="box">
    	<h2>Messages:</h2>
    	<c:forEach items="${event.messages}" var="message">
    		<p><c:out value="${message.poster.firstName}"/> <c:out value="${message.poster.lastName}"/>: <c:out value="${message.text}"/></p>
    	</c:forEach>
	    <form:form method="POST" action="/events/${event.id}/message" modelAttribute="message">
	        <form:label path="text">Message:</form:label>
	        <p>
	            <form:textarea path="text"/>
	            <form:errors path="text" class="red"/>
	        </p>
	        <form:input type="hidden" path="poster" value="${user.id}"/>
	        <form:input type="hidden" path="event" value="${event.id}"/>
	        <input type="submit" value="New Message"/>
	    </form:form>
    </div>
</body>
</html>