<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Songs</title>
  <link rel="stylesheet" type="text/css" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css">

<style><%@include file="/resources/css/style.css"%></style>
</head>
<body>
<h1>All Songs</h1>
<a href="songs/top">Top Ten</a>
<form action="/search" method="post">
	<input type="text" name="search">
	<button type="submit">Search</button>
</form>   
<table class="table">
    <thead>
        <tr>
            <th>Title</th>
            <th>Artist</th>
            <th>Rating</th>
            <th>Actions</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach items="${songs}" var="song">
        <tr>
            <td><a href="/songs/${song.id}"><c:out value="${song.title}"/></a></td>
            <td><a href="/search/${song.artist}"><c:out value="${song.artist}"/></a></td>
            <td><c:out value="${song.rating}"/></td>
            <td><a href="/songs/${song.id}/delete">Delete</a></td>  
        </tr>
        </c:forEach>
    </tbody>
</table>
<a href="/songs/new">New Song</a>
</body>
</html>