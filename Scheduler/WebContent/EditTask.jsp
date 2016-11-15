<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Scheduler - Edit Task</title>
</head>
<body>
<form action="EditTask" method="post">

<c:forEach items="${datalist}" var="datalist">
<input type="hidden" name="taskid" value="${datalist.gettaskid()}" />
Task: <input type="text" name="task" value="${datalist.gettask()}" />
Duration : <input type="text" name="duration" value="${datalist.getduration()}" />
<br>
<br>
<input type="submit" name="submit" value="Save"/>
<input type="submit" name="submit" value="Delete" />


</c:forEach>




</form>

</body>
</html>