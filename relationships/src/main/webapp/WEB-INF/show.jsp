<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>${song.title}</title>
<link rel="stylesheet" type="text/css" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css">

<style><%@include file="/resources/css/style.css"%></style>
</head>
<body>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<h1><c:out value="${person.firstName}"/> <c:out value="${person.lastName}"/></h1>
<p>License Number: <c:out value="${person.license.number}"/></p>
<p>State: <c:out value="${person.license.state}"/></p>
<p>Expiration Date: <c:out value="${person.license.expiration_date}"/></p>
</body>
</html>