<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<!DOCTYPE html>
<html>
<head>
	<title>removeOk</title>
</head>
<body>
	<h1>MEMBER REMOVE OK</h1>

	ID : ${member.memId}<br />

	<P>The time on the server is ${serverTime}.</P>

	<a href="${cp}/">MAIN</a>
</body>
</html>