<%@page import="com.karinedias.servlets.DeletePerson"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="style.css">
<link href="https://use.fontawesome.com/releases/v5.15.2/css/all.css"
	rel="stylesheet">
<title>People database project</title>
</head>
<body>
	<c:forEach items="${id}" var="id">
		<h2>Do you want this person to be deleted ?</h2>
		<a href="/delete?id=${id}%>"></a>
	</c:forEach>

</body>
</html>