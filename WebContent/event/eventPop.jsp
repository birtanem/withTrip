<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="http://code.jquery.com/jquery-1.7.1.min.js"></script>
<script type="../js/jquery-3.5.0.js"></script>
<script type="text/javascript"></script>
<script type="text/javascript">

$(document).ready(function() {
	$.ajax("eventWinList.ev", {
		success: function(rdata) {
		}
	})
});

</script>

</head>
<body>
<%=request.getParameter("articleList")%>
<h1>여기는 당첨내역</h1>

<select>
<option>1</option>
<option>2</option>
<option>3</option>
</select>
<table border="1">
<tr><td>당첨자</td><td>당첨품</td><td>당첨일</td></tr>
<c:forEach var="articleList" items="${articleList}">

<tr><td>${articleList.member_id}</td><td>${articleList.ew_50000}</td><td>${articleList.date}</td></tr>

</c:forEach>


</table>

<input type="button" id ="btn" onclick="fun1()">
</body>
</html>