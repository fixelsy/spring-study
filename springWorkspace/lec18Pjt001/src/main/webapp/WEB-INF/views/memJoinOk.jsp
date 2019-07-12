<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>memJoinOk</title>
</head>
<body>
	<h1>memJoinOk</h1>
	<%--	기존
	ID : ${memId}<br />
	PW : ${memPw}<br />
	Mail : ${memMail} <br />
	Phone : ${memPhone} <br />
	--%>

	<%--	Command객체 이용
	ID : ${member.memId}<br />
	PW : ${member.memPw}<br />
	Mail : ${member.memMail} <br />
	Phone : ${member.memPhone1}-${member.memPhone2}-${member.memPhone3}<br />
	--%>

	<%--	Command객체 이용 + 객체명 변경 --%>
	ID : ${mem.memId}<br />
	PW : ${mem.memPw}<br />
	Mail : ${mem.memMail} <br />
	Phone : ${mem.memPhone.memPhone1}-${mem.memPhone.memPhone2}-${mem.memPhone.memPhone3}<br />


	<a href="/lec18/resources/html/memLogin.html"> Go MemberLogin </a>

</body>
</html>
