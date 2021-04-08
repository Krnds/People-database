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

	<h2>Change person infos</h2>
	<form action="update" method="post">

		<c:set var="id"><%=request.getParameter("id")%></c:set>
		<c:set var="firstname"><%=request.getParameter("firstname")%></c:set>

		<fieldset>
			<table>
				<tr>
					<td>Lastname :</td>
					<td><input type="text" name="lastname"
						value="lastname"></td>
				</tr>
				<tr>
					<td>Firstname :</td>
					<td><input type="text" name="firstname"
						value="firstname">
						</td>
				</tr>
				<tr>
					<td>Adress :</td>
					<td><input type="text" name="adress"
						value="adress"></td>
				</tr>
				<tr>
					<td>Postal code:</td>
					<td><input type="text" name="postalCode" value="postalCode"></td>
				</tr>
				<tr>
					<td>City :</td>
					<td><input type="text" name="city"
						value="city"></td>
				</tr>
				<tr>
					<td>Phone number :</td>
					<td><input type="text" name="phoneNumber"
						value="phone number">
					<td>
				</tr>
				<tr>
					<td></td>
					<td><input type="submit" value="Envoyer" /> <input
						type="reset" value="Reset" /></td>
				</tr>
			</table>
		</fieldset>
		<input type="hidden" value=<%=request.getParameter("id")%> name="id">
	</form>
	<div class="backtodatabase-button">
			<a class="backtodatabase" href="database"> Back to the database</a>
	</div>
</body>
</html>
