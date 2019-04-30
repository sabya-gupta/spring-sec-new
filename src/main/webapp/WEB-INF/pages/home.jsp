<%@ taglib prefix="form" uri = "http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring_sec" uri = "http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="ISO-8859-1">
	<title>Spring Security</title>
	</head>
	<body>
	 Home - SS
	 <hr>
	 <p>
	 User Id: <spring_sec:authentication property="principal.username"/>
	 </p>
	 <hr>
	 User Role(s): <spring_sec:authentication property="principal.authorities"/>
	 <spring_sec:authorize access="hasRole('MANAGER')">
		 <hr>
		 <p>
		 	<a href= "${pageContext.request.contextPath}/leaders" >Leaders</a>
		 </p>
		 <hr>
	 </spring_sec:authorize>
	 <spring_sec:authorize access="hasRole('ADMIN')">
		 <p>
		 	<a href= "${pageContext.request.contextPath}/systems" >Admins</a>
		 </p>
		 <hr>
	 </spring_sec:authorize>
	 <form:form action="${pageContext.request.contextPath}/logout" method="POST" >
	 	<input type="submit" value="Logout" />
	 </form:form>
	</body>
</html>