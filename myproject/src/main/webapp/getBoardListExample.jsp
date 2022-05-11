<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %><!-- 일반적인 날짜 형태로 표시할 수 있게 해줌 -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글 목록</title>
</head>
<body>
<center>
<h1><spring:message code="message.board.list.mainTitle"/></h1>
<h3>${userName}<spring:message code="message.board.list.welcomeMsg"/>..<a href="logoutExample.do">logout</a></h3>

<!-- 검색 시작 -->
<form action="getBoardListExample.do" method="post">
<table border="1" cellpadding="0" cellspacing="0" width="700">
<tr>
	<td align="right">
		<select name="searchCondition">
		<c:forEach items="${conditionMap}" var="option">
			<option value="${option.value}">${option.key}
		</c:forEach>
		</select>
		<input name="searchKeyword" type="text"/>
		<input type="submit" value="<spring:message code="message.board.list.search.condition.btn"/>"/>	
	</td>
</tr>
</table>
</form>
<!-- 검색 종료 -->

<table border="1" cellpadding="0" cellspacing="0" width="700">
<tr>
	<th bgcolor="orange" width="100"><spring:message code="message.board.list.table.head.seq"/></th>
	<th bgcolor="orange" width="200"><spring:message code="message.board.list.table.head.title"/></th>
	<th bgcolor="orange" width="150"><spring:message code="message.board.list.table.head.writer"/></th>
	<th bgcolor="orange" width="150"><spring:message code="message.board.list.table.head.regDate"/></th>
	<th bgcolor="orange" width="100"><spring:message code="message.board.list.table.head.cnt"/></th>
</tr>

<%--
<% for(BoardVO board : boardList) { %>
<tr>
	<td><%= board.getSeq() %></td>
	<td align="left"><a href="getBoard.do?seq=<%= board.getSeq() %>"><%= board.getTitle() %></a></td>
	<td><%= board.getWriter() %></td>
	<td><%= board.getRegDate() %></td>
	<td><%= board.getCnt() %></td>
</tr>
<% } %>
--%>

<c:forEach items="${boardList}" var="board">
<tr>
	<td>${board.seq}</td>
	<td align="left"><a href="getBoardExample.do?seq=${board.seq}">${board.title}</a></td>
	<td>${board.writer}</td>
	<td><fmt:formatDate value="${board.regDate}" pattern="yyyy-MM-dd"/></td><!-- ${board.regDate} -->
	<td>${board.cnt}</td>
</tr>
</c:forEach>

</table>
<br>
<a href="insertBoardExample.jsp"><spring:message code="message.board.list.link.insertBoard"/></a>
</center>
</body>
</html>