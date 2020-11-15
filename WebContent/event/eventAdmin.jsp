<%@page import="event.vo.EventWinBean"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
/*

포인트 불러오기
포인트 교환권 사용하기
당첨내역 불러오기
당첨등록

*/



%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>Home | Corlate</title>

    <!-- core CSS -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/font-awesome.min.css" rel="stylesheet">
    <link href="css/animate.min.css" rel="stylesheet">
    <link href="css/prettyPhoto.css" rel="stylesheet">
    <link href="css/owl.carousel.min.css" rel="stylesheet">
    <link href="css/icomoon.css" rel="stylesheet">
    <link href="css/main.css" rel="stylesheet">
    <link href="css/responsive.css" rel="stylesheet">

<!-- 이벤트 css, js -->
<link href="css/event.css" rel="stylesheet">
<link rel="stylesheet" href="event/assets/countdown/jquery.countdown.css" />

<script src="http://code.jquery.com/jquery-1.7.1.min.js"></script>
<script src="event/assets/countdown/jquery.countdown.js"></script>
<script src="<c:url value="js/event.js" />"></script>
<script src="<c:url value="js/eventPop.js" />"></script>
<script src="<c:url value="js/jquery-3.5.0.js" />"></script>

<!-- /이벤트 css, js -->

    <link rel="shortcut icon" href="images/ico/favicon.ico">
    <link rel="apple-touch-icon-precomposed" sizes="144x144" href="images/ico/apple-touch-icon-144-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="114x114" href="images/ico/apple-touch-icon-114-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="72x72" href="images/ico/apple-touch-icon-72-precomposed.png">
    <link rel="apple-touch-icon-precomposed" href="images/ico/apple-touch-icon-57-precomposed.png">

    <script type="text/javascript">
   		// 메뉴 액티브
	   $(document).ready(function() {
		  $(".nav1").addClass("active"); 
	   });
   </script>
   
<style>
table {
  font-family: arial, sans-serif;
  border-collapse: collapse;
  width: 100%;
}

td, th {
  border: 1px solid #dddddd;
  text-align: left;
  padding: 8px;
}

tr:nth-child(even) {
  background-color: #dddddd;
}
/* The container must be positioned relative: */

#sel {
width: 200px;
padding: .5em .5em;
border: 1px solid #999;
font-family: inherit;
background: url('images/arrow.jpg') no-repeat 95% 50%;
border-radius: 0px;
-webkit-appearance: none;
-moz-appearance: none;
appearance: none;
margin: 0 0 50px 0;
}

#sel::-ms-expand {
    display: none;
}
#sel:hover {
	
}
#sel option {
	font-family: inherit;
	font-size: 1.1em;
}
span.choose {
  color: #555;
  padding: 5px 0 10px 10px;
  display: inherit
}
.sel-container {
  width: 500px;
  margin: 50px auto 0;
  text-align: center
}
h1 {
	margin-top: 50px;
	text-align: center;
}
h2 {
	margin-left: 10px;
}

</style>
</head>

<script type="text/javascript">

function eventWinList(sel) {
	
	if($("#sel option:selected").val() != "선택") {
		sel = 
		$.ajax({  

		    type : "POST",
		    url : "eventChangeList.ev",  
		    data : {"sel": sel},  
		    dataType : "json",  
		    success : function(rdata){
		    	
		    	$("#table1 tr:not(:first)").empty();
		    	$("#table2 tr:not(:first)").empty();
		    	
		    	$("#table1").append("<tr><td>"+rdata[1].e_round+"</td><td>"+rdata[1].e_sdate+"</td><td>"+rdata[1].e_edate+"</td></tr>");
		    	
		    	$.each(rdata[0], function(index, item) {
		    		$("#table2").append("<tr><td>"+item.member_id+"</td><td>"+item.ew_date+"</td><td>"+item.ew_cp_3+"</td><td>"+item.ew_cp_5+"</td><td>"+item.ew_cp_10+"</td></tr>");
		    	});
		    }  
		});
	}else {
		
    	$("#table1 tr:not(:first)").empty();
    	$("#table2 tr:not(:first)").empty();
    	$("#table1").append("<tr><td colspan='3' style='text-align: center;' >회차를 선택하세요</td></tr>");
    	$("#table2").append("<tr><td colspan='5' style='text-align: center;'>회차를 선택하세요</td></tr>");
  
	}

}

</script>
<script type="text/javascript">

$(document).ready(function() {
	
	eventWinList();

	$("#sel").change(function() {		
		var sel = $("#sel option:selected").val();		
		eventWinList(sel);

	});
	$("#selectBtn").click(function() {		
		var sel = $("#selboxDirect").val();
		eventWinList(sel);	
	});
}); 
</script>


<body>
<jsp:include page="../inc/top.jsp"></jsp:include>

    <div class="page-title" style="background-image: url(images/page-title.png)">
        <h1>이벤트 관리</h1>
    </div>

    <section id="contact-page" style="height: 1000px;">

<h1>이벤트 내역</h1>
<!-- <input type="text" id="selboxDirect" name="selboxDirect"/> -->

<div class="sel-container" style="width:200px;">
<span class="choose">회차를 선택하세요</span>
<select id="sel">
<option value="선택">선택</option>

<c:forEach var="wList" items="${eventList}" varStatus="status">
<option>${status.count}</option>
</c:forEach>
</select>
</div>



<!-- <input type="button" value="확인" id="selectBtn"/> -->


<h2>이벤트 회차</h2>

<table border="1" id="table1">
<tr><td>회차</td><td>시작일</td><td>종료일</td></tr>
</table>

<h2>당첨내역</h2>


<table border="1" id="table2">
<tr><td>당첨자</td><td>당첨일</td><td>30000P</td><td>50000P</td><td>100000P</td></tr>
</table>


    </section>
    <!--/#bottom-->
	<jsp:include page="../inc/bottom.jsp"></jsp:include>
    <!--/#footer-->

<!--     <script src="js/jquery.js"></script> -->

    <script src="js/bootstrap.min.js"></script>
    <script src="js/jquery.prettyPhoto.js"></script>
    <script src="js/owl.carousel.min.js"></script>
    <script src="js/jquery.isotope.min.js"></script>
    <script src="js/main.js"></script>

</body>
</html>