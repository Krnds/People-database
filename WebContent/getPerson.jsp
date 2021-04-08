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
<h2>Current entry</h2>
<body>
	<table class="getPerson-table">
			<tr>
				<td>ID</td>
				<td>${getPerson.id}</td>
			<tr>
				<td>Firstname</td>
				<td>${getPerson.firstname}</td>
			</tr>
			<tr>
				<td>Lastname</td>
				<td>${getPerson.lastname}</td>
			</tr>
			<tr>
				<td>Birthdate</td>
				<td>${getPerson.birthdate}</td>
			</tr>
			<tr>
				<td>Adress</td>
				<td>${getPerson.adress}</td>
			</tr>
			<tr>
				<td>Postal Code</td>
				<td>${getPerson.postalCode}</td>
			</tr>
			<tr>
				<td>City</td>
				<td>${getPerson.city}</td>
			</tr>
			<tr>
				<td>Phone Number</td>
				<td>${getPerson.phoneNumber}</td>
			</tr>
	</table>
	<div class="backtodatabase-button">
			<a class="backtodatabase" href="database"> Back to the database</a>
	</div>

</body>
</html>