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
<title>Review Task List</title>
</head>
<body>

<!-- Header -->
<section class="header">
<%@ include file="partials/header.jsp" %>
</section>

<!-- MainBody -->
<main class="container">


<section>
<h1 style="text-align: center;">Review Tasks</h1>
<p id="editmsg2" class="moveup" style="text-align: center;">
Here is a list of your tasks.
</p>

	<!-- decide what to do based on if they have any tasks yet -->
	<!-- Otherwise just tell them to add a task -->
	<c:choose>
	<c:when test="${listsize > 0}">
	<table id="tasks" class="table table-hover">
	<thead>
  	
    <tr>
      <th scope="col">Task #</th>
      <th scope="col">Description</th>
      <th scope="col">Date</th>
      <th scope="col">Complete?</th>
    </tr>
  </thead>
  <tbody>
  
  <c:set var = "number" scope = "session" value = "1"/>
  
  <c:forEach var="task" items="${tasks}">
  
  
    <tr>
      <th scope="row">
      ${number}
      <c:set var = "number" scope = "session" value = "${number + 1}"/>
      </th>
      <td>${task.description}</td>
      <td>${task.date}</td>
      
      <td class="com">
      <c:choose>
        <c:when test="${task.complete == 1}">Yes</c:when>
        <c:otherwise>No</c:otherwise>
	  </c:choose>
      
      </td>
    </tr>
    </c:forEach>
    
    
    
    </tbody>
    </table>
    </c:when>
    
    
    
    <c:otherwise>
    
    <section class="card-header">
		
					
	</section>
	<section class="card-body">
		<table id="tableform">
		<tr>
		<td>
		<h1 style="font-size: 20px;">${user.name}, you have not added any tasks yet.
		<br>Would you like to add one?</h1>
		</td>
		</tr>
		
		<tr>
		<td>
		<a href="/add" id="btnedit"><button type="button" id="a"
class="btn btn-light btn-lg">Add a Task</button></a>
		</td>
		</tr>
		</table>
	</section>
				
    </c:otherwise>
  </c:choose>


</section>


</main>

</body>
</html>