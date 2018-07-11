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
<h1>Questions</h1>
<table class="table">
    <thead>
        <tr>
            <th>Question</th>
            <th>Tags</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach items="${questions}" var="license">
        <tr>
            <td><a href="/questions/${license.id}"><c:out value="${license.name}"/></a></td>
            <td>
            	<c:forEach items="${license.tags}" var="tag">
            		<c:out value="${tag.name}"/><span>,</span> 
            	</c:forEach>
            </td>
        </tr>
        </c:forEach>
    </tbody>
</table>
<a href="/questions/new">New Question</a>
</body>
</html>