<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Groups</title>
<%@ include file="/META-INF/header.jspf"%>
</head>
<body>
		
	
	<table style="border: 1px solid black">
		<c:forEach items="${users}" var="user">
			<tr>
				<td><c:out value="${user.username }"/></td>
				<td><c:out value="${user.email }"/></td>
				<td><a href="DetailsUser?id=${user.id }">Szczególy</a></td>
			</tr>
		</c:forEach>
	</table>
	<%@	include file="/META-INF/footer.jspf"%>
</body>
</html>