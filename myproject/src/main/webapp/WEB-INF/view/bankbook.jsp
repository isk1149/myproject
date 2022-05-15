<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>파킹통장</title>
	<style>
	table, th, td {
	  border: 1px solid black;
	  border-collapse: collapse;
	}
	th {width : 100px;}
	</style>
<jsp:include page="/headerStyle.jspf" flush="false"></jsp:include>
</head>
<jsp:include page="/header.jspf" flush="false"></jsp:include>
<body>
<div style="text-align:right">
	<c:if test="${empty user}"><a href="login.do" >[로그인]</a></c:if>
	<c:if test="${!empty user}"><a href="logout.do">[로그아웃]</a></c:if>
</div>
${user.user_nm}님, 예금 정보입니다.
<br><br>
<div style="width:100%; text-align:center;">
<div style="width:500px; height:500px; padding: 10px 10px 10px 10px; border:1px solid black; text-align:left; display:inline-block;">
[계좌번호]<br>
<b>${accountNo}</b>
<br><br>

[예금액]<br>
<b style="font-size : 20px;">${deposit}원</b><br>
<div style="float:left; margin-right:10px;">
<form action="remittance.do" method="get"><input type="submit" value="계좌이체"></form>
</div>
<div style="float:left; margin-right:10px;">
<form action="allTransactionHistory.do" method="get"><input type="submit" value="거래내역 전체조회"></form>
</div>
<div style="float:left; margin-right:10px;">
<form action="monthlyInterest.do" method="get"><input type="submit" value="이자월별조회"></form>
</div>
<br><br>

[지급가능이자]<br>
<form action="getInterest.do" method="post">
<b>${interestApplyCount}</b>일 동안 <b>${interest}원</b>이 쌓였습니다.
<input type="submit" value="지금받기">
</form>
<br>

[거래내역]<br>


</div>
</div>
</body>
</html>