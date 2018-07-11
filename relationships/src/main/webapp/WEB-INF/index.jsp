<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Licenses</title>
  <link rel="stylesheet" type="text/css" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css">

<style><%@include file="/resources/css/style.css"%></style>
</head>
<body>
<h1>All Licenses</h1>  
<table class="table">
    <thead>
        <tr>
            <th>First Name</th>
            <th>Last Name</th>
            <th>License #</th>
            <th>State</th>
            <th>Expiration Date</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach items="${licenses}" var="license">
        <tr>
            <td><c:out value="${license.person.firstName}"/></td>
            <td><c:out value="${license.person.lastName}"/></td>
            <td><c:out value="${license.number}"/></td>
            <td><c:out value="${license.state}"/></td>
            <td><c:out value="${license.expiration_date}"/></td>
        </tr>
        </c:forEach>
    </tbody>
</table>
</body>
</html>