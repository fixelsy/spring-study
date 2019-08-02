<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Buy Ticket</title>
</head>
<body>

	<p>카드 결제</p>

	<form action="buy_ticket_card_tsX">
		고객 아이디 : <input type="text" name="consumerId"> <br/>
		티켓 구매수 : <input type="text" name="amount"> <br/>
		에러 발생여부 : <input type="text" name="error" value="0"> <br/>
		<input type="submit" value="구매(Transaction X)"><br />
	</form>

	<hr>

	<form action="buy_ticket_card_tsManager">
		고객 아이디 : <input type="text" name="consumerId"> <br/>
		티켓 구매수 : <input type="text" name="amount"> <br/>
		에러 발생여부 : <input type="text" name="error" value="0"> <br/>
		<input type="submit" value="구매(Transaction Manager)"><br />
	</form>

	<hr>
	<form action="buy_ticket_card_tsTemplate">
		고객 아이디 : <input type="text" name="consumerId"> <br/>
		티켓 구매수 : <input type="text" name="amount"> <br/>
		에러 발생여부 : <input type="text" name="error" value="0"> <br/>
		<input type="submit" value="구매(Transaction Template)"><br />
	</form>

	<hr> 에러 발생 여부에 1을 입력하면 에러가 발생합니다. <hr>
</body>
</html>