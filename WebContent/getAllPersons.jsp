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
<h2>Current Database</h2>
<body>
	<table class="persons-table">

		<thead>
			<tr>
				<td>ID</td>
				<td>Firstname</td>
				<td>Lastname</td>
				<td>Birthdate</td>
				<td>Adress</td>
				<td>Postal Code</td>
				<td>City</td>
				<td>Phone number</td>
				<td>Actions</td>
			</tr>
		</thead>
		<tbody>

			<c:forEach items="${allPersons}" var="currentPerson">
				<tr>
					<td>${currentPerson.id}</td>
					<td>${currentPerson.firstname}</td>
					<td>${currentPerson.lastname}</td>
					<td>${currentPerson.birthdate}</td>
					<td>${currentPerson.adress}</td>
					<td>${currentPerson.postalCode}</td>
					<td>${currentPerson.city}</td>
					<td>${currentPerson.phoneNumber}</td>
					<td class="icons"><a href="getPerson.jsp"> <i
							style="color: #757373" class="far fa-eye"></i></a>

						<form class="submit-button" name="update" method="post"
							action="updatePerson.jsp?id=${currentPerson.id}">
							<button class="test" type="submit">
								<i style="color: #EDA768" class="fas fa-pencil-alt"> </i>
							</button>
						</form>
						<form class="submit-button" name="delete" method="post"
							action="delete?id=${currentPerson.id}"
							onSubmit="return confirm('Do you want to delete this person?') & window.location.reload()">
							<button class="test" type="submit">
								<i style="color: #993333" class="fas fa-trash"> </i>
							</button>
						</form></td>
				</tr>
				
				<form method="post" action="updatePerson.jsp">
   <input type="hidden" name="firstname" value="${currentPerson.firstname}" />
   <input type="hidden" name="var1" value="val1" />
</form>
				
			</c:forEach>

		</tbody>

	</table>



	<div class="addPerson-button">
		<a class="addNewPerson" href="addPerson.jsp">Add new person</a>

	</div>

</body>
</html>