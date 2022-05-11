<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
		<td><input type="text" name="id"/></td><!-- value="${userVO.id}" -->
	</tr>
	
	<tr>
		<td bgcolor="#DAE3E6">비밀번호</td>
		<td><input type="password" name="password"/></td><!-- value="${userVO.password}" -->
	</tr>
	
	<tr>
		<td colspan="2" align="center">
			<input type="submit" value="로그인"/>
		</td>
	</tr>
</table>
</form>
</center>
</body>
</html>