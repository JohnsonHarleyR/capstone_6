<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>

<!-- I just remembered it was mentioned that it's best not to use tables
to establish a layout on the page. I will have to review the alternatives
for the future. -->

<head>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"
	integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk"
	crossorigin="anonymous">
<link href="/style.css" rel="stylesheet" />

<meta charset="ISO-8859-1">
<title>User Settings</title>
</head>
<body>

<!-- Header -->
<section class="header">
<%@ include file="partials/header.jsp" %>
</section>

<!-- MainBody -->
<main class="container">


<table>
<tr>
<th>
<article>
	<section class="card-header">
		<h1>User Info</h1>			
	</section>
</article>
</th>
</tr>
<tr>
<td>
<section class="card-body" id="info">
<p id="editmsg">
<i>${message}</i>
</p>

<p>
<b>Username: </b> ${user.username}
</p>
<p>
<b>Email: </b> ${user.email}
</p>
<p>
<b>Password: </b> ${password}
</p>
<p>
<b>First name: </b> ${user.name}
</p>

<p>
<a href="/user/edit" id="btnedit"><button type="button" id="su"
class="btn btn-light btn-lg">Edit Info</button></a>
</p>

</section>

</td>
</tr>
</table>
</main>

</body>
</html>