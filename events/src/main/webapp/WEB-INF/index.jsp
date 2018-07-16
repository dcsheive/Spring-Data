<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>    
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Registration Page</title>
    <link rel="stylesheet" href="/css/bootstrap.min.css">
	<link rel="stylesheet" href="/css/style.css">
</head>
<body>
<div class="box">
	<h1>Login</h1>
    <p class="red"><c:out value="${error}" /></p>
    <form method="post" action="/login">
        <p>
            <label for="email">Email</label>
            <input type="email" id="email" name="email"/>
        </p>
        <p>
            <label for="password">Password</label>
            <input type="password" id="password" name="password"/>
        </p>
        <input type="submit" value="Login!"/>
    </form>   
</div>
<div class="box">
    <h1>Register!</h1>    
    <form:form method="POST" action="/registration" modelAttribute="user">
        <p>
            <form:label path="email">Email:</form:label>
            <form:input type="email" path="email"/>
            <form:errors path="email" class="red"/>
        </p>
        <p>
            <form:label path="firstName">First Name:</form:label>
            <form:input path="firstName"/>
            <form:errors path="firstName" class="red"/>
        </p>
        <p>
            <form:label path="lastName">Last Name:</form:label>
            <form:input path="lastName"/>
            <form:errors path="lastName" class="red"/>
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
            <form:label path="password">Password:</form:label>
            <form:password path="password"/>
            <form:errors path="password" class="red"/>
        </p>
        <p>
            <form:label path="passwordConfirmation">Password Confirmation:</form:label>
            <form:password path="passwordConfirmation"/>
			<form:errors path="passwordConfirmation" class="red"/>
        </p>
        <input type="submit" value="Register!"/>
    </form:form>
</div>
</body>
</html>