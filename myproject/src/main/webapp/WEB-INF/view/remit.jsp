<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>      
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>송금하기</title>
<!--  
<style>
table, th, td {
	border: 1px solid black;
 	border-collapse: seperate;
}
</style>
-->
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
<center>
[송금하기]
</center>
<br>
<form action="remitProgress.do" method="post">
<table border="1" cellpadding="0" cellspacing="0" width="70%" style="margin-left: auto; margin-right: auto;">
<tr height="30px">
	<td align="center" bgcolor="#DAE3E6">출금계좌</td>
	<td align="center">${accountNo}</td>
</tr>
<tr height="30px">
	<td align="center" bgcolor="#DAE3E6">은행</td>
	<td align="center">
		<select style="width: 68%;" name="bank">
		 <c:forEach items="${bankList}" var="bank">
			<option value="${bank.code}">${bank.name}
		</c:forEach>
		</select>
	</td>
</tr>
<tr height="30px">
	<td align="center" bgcolor="#DAE3E6">계좌번호</td>
	<td align="center"><input type="text" name="txAccountNo" size="20" maxlength="10"></td>
</tr>
<tr height="30px">
	<td align="center" bgcolor="#DAE3E6">이체금액</td>
	<td align="center"><input type="number" name="txAmount"></td>
</tr>
<tr height="50px">
	<td align="center" colspan="2"><input type="submit" value="확인"></td>
</tr>
</table>
</form>

<br><br>
{ 대한은행 김철수 계좌 : 0000006747 <br> 
&nbsp 케이은행 김단비 계좌 : 0000000928 }
</div>
</div>

</body>
</html>