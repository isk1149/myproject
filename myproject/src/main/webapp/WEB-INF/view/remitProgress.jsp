<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>송금하기</title>
<jsp:include page="/headerStyle.jspf" flush="false"></jsp:include>
</head>
<jsp:include page="/header.jspf" flush="false"></jsp:include>
<body>

<div style="width:100%; height:100%; text-align:center;">
<div style="width:500px; height:100%;  padding: 10px 10px 10px 10px; border:1px solid black; text-align:left; display:inline-block;">
<center>
[송금하기]
</center>
<br>
<form action="remitCheck.do" method="post">
<table border="1" cellpadding="0" cellspacing="0" width="70%" style="margin-left: auto; margin-right: auto;">

<tr height="30px">
	<td align="center" bgcolor="#DAE3E6">출금계좌</td>
	<td align="center">${accountNo}</td>
</tr>

<tr height="30px">
	<td align="center" bgcolor="#DAE3E6">거래은행</td>
	<td align="center">${transactionHistoryVO.txBankName}
					   <input type="hidden" value="${transactionHistoryVO.txBank}" name="txBank">
					   <input type="hidden" value="${transactionHistoryVO.txBankName}" name="txBankName">
	</td>
</tr>
<tr height="30px">
	<td align="center" bgcolor="#DAE3E6">거래계좌</td>
	<td align="center">${txAccountNo}<input type="hidden" value="${transactionHistoryVO.txAccountNo}" name="txAccountNo"></td>
</tr>
<tr height="30px">
	<td align="center" bgcolor="#DAE3E6">이체금액</td>
	<td align="center">${txAmountFormat}원<input type="hidden" value="${transactionHistoryVO.txAmount}" name="txAmount"></td>
</tr>
<tr height="30px">
	<td align="center" bgcolor="#DAE3E6">받는 분 통장 표기</td>
	<td align="center"><input type="text" name="bankAssocAcctNm"></td>
</tr>
<tr height="30px">
	<td align="center" bgcolor="#DAE3E6">내 통장 표기</td>
	<td align="center"><input type="text" value="${transactionHistoryVO.txAccountName}" name="txAccountName"></td>
</tr>

<tr height="50px">
	<td align="center" colspan="2"><input type="submit" value="확인"></td>
</tr>
</table>
</form>
<c:if test="${error.emptyAccount}">계좌를 입력해주세요.<br></c:if>
<c:if test="${error.noAmount}">금액을 입력해주세요.<br></c:if>
<c:if test="${error.noAccount}">존재하지 않는 계좌입니다.<br></c:if>
<c:if test="${error.lackDeposit}">잔액이 부족합니다.<br></c:if>

</div>
</div>

</body>
</html>