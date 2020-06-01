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
<title>Login Page</title>
</head>

<body>

<!-- Header -->
<section class="header">
<%@ include file="partials/header.jsp" %>
</section>

<!-- MainBody -->
<main class="container">
	
	<c:choose>
		<c:when test="${loggedin == false}">
			<article>
				<section class="card-header">
					<h2>Please Sign In</h2>
					
				</section>
				<section class="card-body">
				<table id="tableform">
				
					<tr>
					<td id="editmsg2">
					${message}
					</td>
					</tr>
					<tr>
					<td>
					<form action="/login/submit" method="post">
					
					<label id="i1">Username or E-mail: </label><br>
					<input id="t1" type="text" name="identity" required/>
					</td>
					</tr>
					<tr>
					<td>
					<label id="i2">Password: </label><br>
					<input id="t2" type="password" name="password" required/>
					</td>
					</tr>
					<tr>
					<td>
					<button id="si" type="submit" class="btn btn-light btn-lg">Sign In</button>
					<a href="/sign-up"><button type="button" id="su" style="float: right;"
					 class="btn btn-light btn-lg">Sign Up</button></a>
					</form>
					</td>
					</tr>
					</table>
				</section>
			</article>
		</c:when>
		
		<c:otherwise>
			<section>
				<h1>${user.name}, you are already logged in!</h1>
			</section>
			<section id="panda">
				<img width=300px src="/confused-panda-brown.png">
			</section>
		</c:otherwise>
	</c:choose>
	
	

</main>

</body>
</html>