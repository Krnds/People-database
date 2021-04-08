<%@page import="com.karinedias.dao.MemoryDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="style.css">
<title>People database project</title>
</head>

<body>

	<h2>Choose your database type</h2>

	<div class="choosedao-button">
		<form action="chooseDao?choice=${daoChoice}" method="post">
			<c:set var="Memorychoice" value="memory"></c:set>
			<c:set var="JDBCchoice" value="jdbc"></c:set>

			<input type="submit" id="daoChoice" name="daoChoice" value="Memory">

			<input type="submit" id="daoChoice" name="daoChoice" value="JDBC">

		</form>

	</div>

	<div>
	</div>


</body>

</html>