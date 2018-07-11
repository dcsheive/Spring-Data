<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>${dojo.name}</title>
<link rel="stylesheet" type="text/css" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css">

<style><%@include file="/resources/css/style.css"%></style>
</head>
<body>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<h1><c:out value="${product.name}"/></h1>
<table class="table">
    <thead>
        <tr>
            <th>Category</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach items="${product.categories}" var="license">
        <tr>
            <td><c:out value="${license.name}"/></td>
        </tr>
        </c:forEach>
    </tbody>
</table>
<form action="/products/${product.id}/add" method="POST">
    <select name="category">
        <option value="none">--Select--</option>
    	<c:forEach items="${categories}" var="cate">   
        	<option value="${cate.id}">${cate.name}</option>
        </c:forEach>
    </select>
    <button type="submit">Add</button>
</form>
</body>
</html>