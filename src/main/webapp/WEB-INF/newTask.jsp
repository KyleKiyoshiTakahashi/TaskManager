<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%> 
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>  
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>New Task</title>
</head>
<body>
	<h1>New Task</h1>
	<form:form action="/tasks" method="post" modelAttribute="task">
	    <p>
	        <form:label path="description">Task:</form:label>
	        <form:errors path="description"/>
	        <form:input path="description"/>
	    </p> 
	    <form:select path="assignee">
			<c:forEach items="${allUsers}" var="u">
				<form:option value="${u.id}" label="${u.name}" />
			</c:forEach>
	    </form:select>
	 	<form:select path="priority">
				<form:option value="high" label="High" />
				<form:option value="medium" label="Medium" />
				<form:option value="low" label="Low" />
	    </form:select>
	   
	    <input type="submit" value="Submit"/>
	</form:form> 
</body>
</html>