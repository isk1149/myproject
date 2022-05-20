<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>       
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>이자내역</title>
<jsp:include page="/headerStyle.jspf" flush="false"></jsp:include>
</head>
<jsp:include page="/header.jspf" flush="false"></jsp:include>
<body>
<div style="text-align:right">
	<c:if test="${empty user}"><a href="login.do" >[로그인]</a></c:if>
	<c:if test="${!empty user}"><a href="logout.do">[로그아웃]</a></c:if>
</div>
<div style="width:100%; height:100%; text-align:center;">
<div style="width:500px; height:100%;  padding: 10px 10px 10px 10px; border:1px solid black; text-align:left; display:inline-block;">

[이자내역]<br><br>

<c:forEach items="${interestList}" var="interest">
<div style=" margin-right:20px; width:200px;">${interest.txStringDate}</div>

<div style="float:left; margin-right:10px; width:100px;">${interest.txBankName}</div>
<div style="float:left; margin-right:10px; width:100px;">${interest.txAccountName}</div>
<div style="float:left; margin-right:10px; width:150px; text-align: right;">${interest.txStringAmount}원</div>
<br>
<div style=" height:10px;"></div>
</c:forEach>


</div>
</div>

</body>
</html>