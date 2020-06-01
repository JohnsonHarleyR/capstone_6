<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"
	integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk"
	crossorigin="anonymous">
<link href="/style.css" rel="stylesheet" />

<meta charset="ISO-8859-1">
<title>Add a Task</title>
</head>
<body>

<!-- Header -->
<section class="header">
<%@ include file="partials/header.jsp" %>
</section>

<!-- MainBody -->
<main class="container">

<article>
				<section class="card-header">
					<h2>Add a Task</h2>
					
				</section>
				<section class="card-body">
				<table id="tableform">
				
					<tr>
					<td>
					<form action="/add/submit" method="post">
					
					<input type="hidden" name="user" value="${user.username}"/>
					
					<label id="i1">Task description: </label><br>
					<input id="t1" type="text" name="description" required/>
					</td>
					</tr>
					<tr>
					<td>
					<label id="i2">Date: </label><br>
					<input id="t2" type="date" name="date" required/>
					</td>
					</tr>
					<tr>
					<td>
					<button type="submit" id="su"
					 class="btn btn-light btn-lg">Submit</button>
					</form>
					</td>
					</tr>
					</table>
				</section>
			</article>

</main>

</body>
</html>