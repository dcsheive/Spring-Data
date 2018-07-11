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
<h1>New Product</h1>
<form:form action="/products/new" method="post" modelAttribute="product">
    <p>
        <form:label path="name">Name</form:label>
        <form:errors path="name" class="red"/>
        <form:input path="name" class="form-control"/>
    </p>
    <p>
        <form:label path="description">Description</form:label>
        <form:errors path="description" class="red"/>
        <form:input path="description" class="form-control"/>
    </p>
    <p>
        <form:label path="price">Price</form:label>
        <form:errors path="price" class="red"/>
        <form:input type="number" path="price" class="form-control"/>
    </p>    
    <input type="submit" value="Submit" class="btn"/>
</form:form>   
</body>
</html>