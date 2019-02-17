<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Home Page</title>
</head>
<body>
	<h1>Welcome, <c:out value="${user.name}" /></h1>
	<a href="/logout">Logout</a>
	
	<table>
		<thead>
			<tr>
				<th>Task</th>
				<th>Creator</th>
				<th>Assignee</th>
				<th>Priority</th>
			</tr>
		</thead>
		<tbody>
			<!-- after you get all the tasks which are being stored in "tasks" -->
			<!-- 	<c:forEach items="${tasks}" var="task" > -->
			<c:forEach items="${users.tasks}" var="task" >
			<tr>
				<!-- the task.description will need to be in an a tag  -->
				<!-- <td><a href="/tasks/${task.id}"><c:out value="${task.description}" /></a></td> -->
				<td><c:out value="${task.description}" /></td>
				<td><c:out value="${task.creator}" /></td>
				<td><c:out value="${task.assignee}" /></td>
				<td><c:out value="${task.priority}" /></td>
			</tr>
			</c:forEach>
		</tbody>
	</table>
	
	<a href="/tasks/new" >Add Task</a>
</body>
</html>