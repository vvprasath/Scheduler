<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Scheduler</title>
</head>
<body>
<form action="DisplayServ" method="post">
Task: <input type="text" name="task">
<br>
Duration: <input type="text" name="duration">
<br>
<input type="submit" name="submit" value="Add task">

<br>
<br>

<table border='1'>
	<tr>
		<th> Task ID </th>
		<th> Task </th>
		<th> Duration </th>
		<th> </th>				
	</tr>
	
	<c:forEach items="${datalist}" var="datalist">
		<tr>
			<th> ${datalist.gettaskid()} </th>
			<th> ${datalist.gettask()} </th>
			<th> ${datalist.getduration()} </th>
			<th> <a href="EditTask?id=${datalist.gettaskid()}"> Edit/Delete </a></th>
					
		</tr>
	</c:forEach>
	

</table>

</form>
</body>
</html>