<%@page import="java.util.Calendar"%>
<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- core CSS -->
<link href="css/product_modal.css" rel="stylesheet">
<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/font-awesome.min.css" rel="stylesheet">
<link href="css/animate.min.css" rel="stylesheet">
<link href="css/prettyPhoto.css" rel="stylesheet">
<link href="css/owl.carousel.min.css" rel="stylesheet">
<link href="css/icomoon.css" rel="stylesheet">
<link href="css/main.css" rel="stylesheet">
<link href="css/responsive.css" rel="stylesheet">
<link href="css/style.css" rel="stylesheet">

<link href="css/order.css" rel="stylesheet">

<script src="js/jquery-3.5.0.js"></script>

<style type="text/css">
.o_info {background: #f4f4f4;}
.loginInput {
  
  
  padding: -5px 10px;
  border: 1px solid #ccc;
  margin-bottom: 5px;
 
}
.ot_list td, .ot_list th {border-right: 1px solid #ddd;}
.ot_list tr, .ot_list th  {text-align: center;}
.th {padding-right: 20px;}
</style>
</head>
<body>
	<jsp:include page="/inc/top.jsp" />
	<div class="page-title"
		style="background-image: url(images/page-title.png);">
		<h1>주문 상세</h1>
	</div>
	<section id="portfolio">

		<div class="container" style=" margin-top: -50px;">
			<div class="o_info o_input" style="font-size: 12pt;">
				<h2><span>구매일시: <fmt:parseDate var="dateString" value="${obj.date}" pattern="yyyy-MM-dd HH:mm:ss" /><fmt:formatDate value="${dateString}" pattern="yyyy-MM-dd HH:mm:ss" /></span></h2>
			</div>
			<div class="o_list">
				<h2>결제내역</h2>
				
				<table class="ot_list">
					<tr>
						<th colspan="2">상품정보</th>	
						<th class="th">가격</th>
						<th class="th">수량</th>
						<th class="th">합계</th>
					</tr>
					<c:forEach var="list" items="${list }">
					<tr>
						<td class="list-font" style="border-right: none;"><img src="product/productUpload/${list.image }" width="200px" height="100px"></td>
						<td class="list-font" style="text-align: left;">${list.name }</td>
						<td class="list-font price "><fmt:formatNumber value="${list.price }" pattern="###,###,###" /> 원</td>
						<td class="list-font" ><fmt:formatNumber value="${list.amount }" pattern="###,###,###" /></td>
						<td class="list-font price" ><fmt:formatNumber value="${list.amount*list.price }" pattern="###,###,###" /> 원</td>
					</tr>
					</c:forEach>
				</table>
			</div>
			<div class="o_list">
				<h2>결제정보</h2>
				<table class="ot_list">
					<tr style="height: 200px;">
						
						<td style="border-right: 1px solid #e6e6e6; text-align: center; width: 33%;  font-size: 16pt; font-weight: 600;"><p style="height: 30px; margin-top: -30px; mar">총 상품 금액</p><span class="price" ><fmt:formatNumber value="${obj.total+obj.point}" pattern="###,###,###" />원</span></td>
						<td style="border-right: 1px solid #e6e6e6; text-align: center; width: 33%; font-size: 16pt; font-weight: 600;"><p style="height: 30px; margin-top: -30px;">포인트 사용</p> <span class="price" ><fmt:formatNumber value="${obj.point}" pattern="###,###,###" />원</span></td>
						<td style="text-align: center; width: 33%; font-size: 16pt; font-weight: 600;"><p style="height: 30px;">결제</p><p class="price" style="height:20px;"><fmt:formatNumber value="${obj.total}" pattern="###,###,###" /> 원</p><p style="height: 20px; margin-top:50px ;margin-bottom: -40px; font-size: 12pt; color: gray;">└&nbsp;&nbsp;${obj.pay}</p></td>
					</tr>
					
					<tr style="height: 100px; font-size: 18pt;" >
						<th colspan="3" style="text-align: right; padding-right: 20px;">총 결제금액&nbsp;&nbsp;<span style="color: red;"><fmt:formatNumber value="${obj.total}" pattern="###,###,###" />  원</span></th>
					</tr>
					<tr style="text-align: right;">
						<td colspan="3" >withTrip 적립 포인트:&nbsp; <span style="color: orange;"><fmt:formatNumber value="${obj.total*0.01}" pattern="###,###,###"/>P</span></td>
					</tr>
					
				</table>
			</div>
			<div style="text-align: center; margin: 30px;">
			<input type="button" class="btn btn-primary btn-lg"  value="목록" onclick="history.back()">
			</div>
	</div>
	</section>



	<jsp:include page="/inc/bottom.jsp" />
	<!--/#footer-->
	<script src="js/jquery.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/jquery.prettyPhoto.js"></script>
	<script src="js/owl.carousel.min.js"></script>
	<script src="js/jquery.isotope.min.js"></script>
	<script src="js/main.js"></script>

</body>
</html>