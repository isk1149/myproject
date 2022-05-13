<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>myproject</title>
<jsp:include page="/headerStyle.jspf" flush="false"></jsp:include>
</head>
<jsp:include page="/header.jspf" flush="false"></jsp:include>
<body>
	<div style="text-align:right">
		<c:if test="${empty user}"><a href="login.do" >[로그인]</a></c:if>
		<c:if test="${!empty user}"><a href="logout.do">[로그아웃]</a></c:if>
	</div>
	1. 파킹통장<br> 
	&nbsp&nbsp&nbsp - 일단위로 이자 계산되는 파킹통장 구현 <br>
	&nbsp&nbsp&nbsp - 파킹통장 페이지 요청 시 이자 계산 <br><br>

</body>
</html>