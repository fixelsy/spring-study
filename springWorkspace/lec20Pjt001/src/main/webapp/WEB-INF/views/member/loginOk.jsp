<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<!DOCTYPE html>
<html>
<head>
	<title>loginOk</title>
</head>
<body>
	<h1>MEMBER LOGIN OK</h1>

	ID : ${member.memId}<br />
	PW : ${member.memPw}<br />

	<P>The time on the server is ${serverTime}.</P>

	<a href="${cp}/">MAIN</a>
</body>
</html>