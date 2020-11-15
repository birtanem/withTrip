
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
<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/font-awesome.min.css" rel="stylesheet">
<link href="css/animate.min.css" rel="stylesheet">
<link href="css/prettyPhoto.css" rel="stylesheet">
<link href="css/owl.carousel.min.css" rel="stylesheet">
<link href="css/icomoon.css" rel="stylesheet">
<link href="css/main.css" rel="stylesheet">
<link href="css/responsive.css" rel="stylesheet">

<link href="css/order.css" rel="stylesheet">

<script src="js/jquery-3.5.0.js"></script>
	<script type="text/javascript">
   	// 메뉴 액티브
   $(document).ready(function() {
	  $(".nav5").addClass("active"); 
   });
   </script>
<style type="text/css">
.fa2 {
font-size: 12pt; margin: 10px; font-weight: 550; color: #FFFFF;
}
</style>
</head>
<body>
	<jsp:include page="/inc/top.jsp" />


    <div class="page-title" style="background-image: url(images/page-title.png)">
        <h1>주문완료</h1>
    </div>
    
    <section class="pricing">
        <div class="large-title text-center">   
  
            <h2><span style="color: green;">주문이 정상적으로 완료</span>되었습니다.</h2>
            <span></span>
        </div> 
        <div class="container">
            <div class="row">
                <div class="col-md-10 col-md-offset-1 text-center">

                    <div class="single-pricing featured">
                    <div class="clearfix">
                    <ul>
                    <li><i class="fa fa-paper-plane"></i><i class="fa fa2" >주문번호: ${sessionScope.orderNum }</i></li>
                    <li><i class="fa fa-paper-plane"></i><i class="fa fa2" >주문자: ${sessionScope.id }</i></li>
                    <li><i class="fa fa-paper-plane"></i><i class="fa fa2" >이메일: ${sessionScope.email}</i></li>
                    <li><i class="fa fa-paper-plane"></i><i class="fa fa2" >결제수단: ${sessionScope.ob.o_pay }</i></li>
                    <li><i class="fa fa-paper-plane"></i><i class="fa fa2" >결제일: <fmt:parseDate var="dateString" value="${sessionScope.ob.o_num}" pattern="yyyyMMdd" /><fmt:formatDate value="${dateString }" pattern="yyyy-MM-dd" /></i></li>
                    </ul>
                     </div>
                        
                         <span></span><br>
                        <span>총 결제금액</span>
                        <h1 style="transform: scale(0.8)">
                            <span></span>
                            <fmt:formatNumber value="${sessionScope.ob.o_price }" pattern="###,###,###" />                          
                            <span>원</span>
                        </h1>
                        <div class="clearfix" style="margin-top: 20px;">
                        <a href="orderList.or">결제내역 </a>
                        </div>
                       
                    </div>

                </div>
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