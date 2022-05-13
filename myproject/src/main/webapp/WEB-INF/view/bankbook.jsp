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
${user.user_nm}님, 예금 정보입니다.<br><br>
[계좌번호]<br>
<b>${accountNo}</b>

</body>
</html>