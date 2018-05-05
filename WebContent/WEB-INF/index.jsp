<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Home page</title>
<%@ include file="/META-INF/header.jspf"%>
</head>
<body>
		
	
	<table style="border: 1px solid black">
		<c:forEach items="${SolutionGetLimited}" var="solution">
			<tr>
				<td><c:out value="${solution.created }"/></td>
				<td><c:out value="${solution.updated }"/></td>
				<td><c:out value="${solution.description }"/></td>
				<td><c:out value="${solution.exercise_id }"/></td>
				<td><c:out value="${solution.users_id }"/></td>
				<td><a href = "Szczegoly?id=${solution.id }">Szczegóły</a></td>
			</tr>
		</c:forEach>
	</table>
	<%@	include file="/META-INF/footer.jspf"%>
</body>
</html>