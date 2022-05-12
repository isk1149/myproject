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
<center>
<h2>로그인</h2>

<form action="login.do" method="post">
<table border="1" cellpadding="0" cellspacing="0">
	<tr>
		<td bgcolor="#DAE3E6">아이디</td>
		<td><input type="text" name="user_id" value="${userVO.user_id}"/></td> <!--  -->
	</tr>
	
	<tr>
		<td bgcolor="#DAE3E6">비밀번호</td>
		<td><input type="password" name="pwd" value="${userVO.pwd}"/></td> <!--  -->
	</tr>
	
	<tr>
		<td colspan="2" align="center">
			<input type="submit" value="로그인"/>
		</td>
	</tr>
</table>
</form>
<c:if test="${error.emptyId}">아이디를 입력해주세요.</c:if>
<c:if test="${error.failLogin}">로그인 정보가 틀렸습니다.</c:if>
</center>
</body>
</html>