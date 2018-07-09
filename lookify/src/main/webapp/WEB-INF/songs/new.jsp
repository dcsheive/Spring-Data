<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>New Song</title>
  <link rel="stylesheet" type="text/css" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css">

<style><%@include file="/resources/css/style.css"%></style>
</head>
<body>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>    
<a href="/dashboard">Dashboard</a>
<h1>New Song</h1>
<form:form action="/songs/new" method="post" modelAttribute="song">
    <p>
        <form:label path="title">Title</form:label>
        <form:errors path="title" class="red"/>
        <form:input path="title" class="form-control"/>
    </p>
    <p>
        <form:label path="artist">Artist</form:label>
        <form:errors path="artist" class="red"/>
        <form:input path="artist" class="form-control"/>
    </p>
    <p>
        <form:label path="rating">Rating</form:label>
        <form:errors path="rating" class="red"/>
        <form:input type="number" path="rating" class="form-control"/>
    </p>    
    <input type="submit" value="Submit" class="btn"/>
</form:form>   
</body>
</html>