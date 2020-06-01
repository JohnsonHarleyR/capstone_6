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
<title>Sign Up Page</title>
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
					<h2>Sign Up</h2>
					
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
					<form action="/sign-up/submit" method="post">
					
					<label id="i1">Username: </label><br>
					<input id="t1" type="text" name="username" required/>
					</td>
					</tr>
					
					<tr>
					<td>
					<label id="i2">Email: </label><br>
					<input id="t2" type="email" name="email" required/>
					</td>
					</tr>
					
					<tr>
					<td>
					<label id="i2">Password: </label><br>
					<input id="t2" type="password" name="password1" required/>
					</td>
					</tr>
					
					<tr>
					<td>
					<label id="i2">Re-enter password: </label><br>
					<input id="t2" type="password" name="password2" required/>
					</td>
					</tr>
					
					<tr>
					<td>
					<label id="i2">First name: </label><br>
					<input id="t2" type="text" name="name" required/>
					</td>
					</tr>
					
					<tr>
					<td>
					<button id="su" type="submit" class="btn btn-light btn-lg">Sign Up</button>
					<a href="/login"><button type="button" id="si" style="float: right;"
					 class="btn btn-light btn-lg">Sign In</button></a>
					</form>
					</td>
					</tr>
					</table>
				</section>
			</article>
		</c:when>
		
		<c:otherwise>
			<section>
				<h1>Please sign out first.</h1>
			</section>
			<section id="panda">
				<img width=300px src="/confused-panda-brown.png">
			</section>
		</c:otherwise>
	</c:choose>

</body>
</html>