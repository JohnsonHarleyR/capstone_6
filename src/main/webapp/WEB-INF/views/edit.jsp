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
<title>Edit User Info</title>
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
					<h2>Edit User Info</h2>
					
				</section>
				<section class="card-body">

					<p id="editmsg2">
					<i>${message}</i>
					</p>
					<p>
					<form action="/user/edit/submit" method="post">
					<input type="hidden" class="edit" name="userid" value="${user.id}"/>

					<label id="i1">Username: </label><br>
					<input id="t1" class="edit" type="text" name="username" value="${user.username}" required/>
					</p>
					<p>
					<label id="i2">Email: </label><br>
					<input id="t2" class="edit" type="text" name="email" value="${user.email}" required/>
					</p>
					<p>
					<label id="i3">New Password: </label><br>
					<input id="t3" class="edit" type="password" name="password1" value="${user.password}" required/>
					</p>
					<p>
					<label id="i4">Re-enter password: </label><br>
					<input id="t4" class="edit" type="password" name="password2" value="${user.password}" required/>
					</p>
					<p>
					<label id="i5">First name: </label><br>
					<input id="t5" type="text" name="name" value="${user.name}" required/>
					</p>
					<p  id="sub">
					<button id="btnsub" type="submit" class="btn btn-light btn-lg">Submit Changes</button>
					</p>
					</form>
				</section>
			</article>


</main>

</body>
</html>