<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"
	integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk"
	crossorigin="anonymous">
<link href="/style.css" rel="stylesheet" />

<meta charset="ISO-8859-1">
<title>Modify Tasks</title>
</head>
<body>

<!-- Header -->
<section class="header">
<%@ include file="partials/header.jsp" %>
</section>

<!-- MainBody -->
<main class="container">


<section>
<h1 style="text-align: center;">Modify Tasks</h1>
<p id="editmsg2" class="moveup" style="text-align: center;">
Complete, add, modify, or delete tasks.
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
      <th scope="col"></th>
    </tr>
  </thead>
  <tbody>
  
  <c:set var = "number" scope = "session" value = "1"/>
  
  <!-- Make a form where you can edit or delete each task.
  Allow checkboxes under "complete", then add a submit button
  at the bottom, in the "complete" column. -->
  
  <!-- Scratch that -->
  
  
  <c:forEach var="task" items="${tasks}">
  
  <form action="/edit/save" method="post">
  
 
  
    <tr>
      <th scope="row">
      ${number}
      <c:set var = "number" scope = "session" value = "${number + 1}"/>
      </th>
      <td>
      <input type="text" name="description" value="${task.description}" required/>
      </td>
      <td>
      <input type="date" name="date" value="${task.date}" required/>
      </td>
      
      <td>
      <label class="com">
        <input type="checkbox" name="complete" 
        value="1" <c:if test="${task.complete == 1}">checked</c:if>/>
        <input type="hidden" value="0" name="complete">
        </label>
      
      </td>
    <td>
     <input type="hidden" name="taskid" value="${task.id}"/>
	<button type="submit" id="si"class="btn btn-light">Save Task</button>
	<a class="btn btn-light" id="at" href="/delete/${task.id}">Delete</a>
    </td>
    </tr>
    </form>
    </c:forEach>
    
    <tr>
    <td>
    
    </td>
    
    
    <td>
    </td>
    <!-- Add Task -->
    <td >
    <a href="/add" class="add" id="btnedit"><button type="button" id="ad"
		class="btn btn-light btn-lg">Add Task</button></a>
    </td>
    
    <td>
    </td>
    
    <td>
    </td>
    </tr>
    
    </tbody>
    </table>
    </c:when>
    
    
    
    <c:otherwise>
    
    <section class="card-header">
		
	<!-- This is before a user adds any tasks -->
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
		<a href="/modify-tasks/add" id="btnedit"><button type="button" id="su"
		class="btn btn-light btn-lg">Add a Task</button></a>
		</td>
		</tr>
		</table>
	</section>
				
    </c:otherwise>
  </c:choose>



</section>


</main>



</main>

</body>
</html>