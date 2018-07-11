<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Questions</title>
<link rel="stylesheet" type="text/css" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css">

<style><%@include file="/resources/css/style.css"%></style>
</head>
<body>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>    

<h1>${question.name}</h1>
<h2>Tags: </h2>
<c:forEach items="${question.tags}" var="tag">
	<div class="box"><c:out value="${tag.name}"/></div> 
</c:forEach>
<table class="table">
    <thead>
        <tr>
            <th>Answers</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach items="${question.answers}" var="answer">
        <tr>
            <td><c:out value="${answer.name}"/></td>
        </tr>
        </c:forEach>
    </tbody>
</table>
<form:form action="/questions/${question.id}" modelAttribute="answer" method="POST">
	<form:label path="name">Answer</form:label>
	<form:errors path="name" class="red"/>
	<form:input path="name"/>
	<form:input type="hidden" value="${question.id}" path="question"/>
    <input type="submit" value="Submit" class="btn"/>
</form:form>
</body>
</html>