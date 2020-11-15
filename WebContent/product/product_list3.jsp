<%@page import="product.vo.ProductBean"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">
<title>Home | Product</title>

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


<link rel="shortcut icon" href="images/ico/favicon.ico">
<link rel="apple-touch-icon-precomposed" sizes="144x144"
	href="images/ico/apple-touch-icon-144-precomposed.png">
<link rel="apple-touch-icon-precomposed" sizes="114x114"
	href="images/ico/apple-touch-icon-114-precomposed.png">
<link rel="apple-touch-icon-precomposed" sizes="72x72"
	href="images/ico/apple-touch-icon-72-precomposed.png">
<link rel="apple-touch-icon-precomposed"
	href="images/ico/apple-touch-icon-57-precomposed.png">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
	<script type="text/javascript">
   	// 메뉴 액티브
   $(document).ready(function() {
	  $(".nav5").addClass("active"); 
   });
   </script>
<script src="js/jquery-3.5.0.js"></script>
<script type="text/javascript">

</script>

</head>
<!--/head-->
<body>
	<jsp:include page="/inc/top.jsp" />
	<!--/header-->

	<div class="page-title"
		style="background-image: url(images/top/Busan2.jpg); background-size: cover; background-position: center;">
		<h1>여행상품</h1>
	</div>

	<section id="portfolio" style="background-color: #FAFAFA">


		<!-- 			data-filter 수정해야함 ~ test용 -->
		<ul class="portfolio-filter text-center">
			<li><a class="btn btn-default active" href="#" data-filter="*">전체보기</a></li>
			<li><a class="btn btn-default" href="#" data-filter=".관광">관광</a></li>
			<li><a class="btn btn-default" href="#" data-filter=".맛집">맛집</a></li>
			<li><a class="btn btn-default" href="#" data-filter=".역사">역사</a></li>
			<li><a class="btn btn-default" href="#" data-filter=".체험">체험</a></li>
			<li></li>
		</ul>

		<section id="team-area">

        <div class="container">
        <div style="margin-bottom: 10px; text-align: right;">
		<input type="button" class="btn btn-primary btn-lg" id="btnCa"value="장바구니" onclick="location.href='ProductCartList.ca'">
		</div>
        <c:if test="${ListCount<=0}">
            <div class="center fadeInDown">
                <h4>등록된 상품이 없습니다</h4>
                <p class="lead"></p>
            </div>
        </c:if>
            <div class="row">

           <div class="portfolio-items">
	            <c:choose>
					<c:when test="${pageInfo.listCount>0}">
					<c:forEach var="list" items="${productList }" varStatus="vs">
	                <div onclick="location.href='productDetail.pr?p_num=${list.p_num }'" class="col-md-4 col-sm-6 single-team portfolio-item ${list.p_theme }  col-xs-12 col-sm-4 col-md-3 single-work">
	                <input type="hidden" value="${list.p_num }" name="p_num">
	                    <div class="inner">
	                        <div class="team-img">
	                           <img src="product/productUpload/${list.p_image }" width="200px" height="200px">
	                        </div>
	                        <div class="team-content">
	                            <h4>${list.p_name }</h4>
	                            <span class="desg">${list.p_theme }</span>
	                            <div class="team-social">
	                               <span style="color: #F76;"><fmt:formatNumber value="${list.p_price }"
									pattern="###,###,###" /> 원</span>
	                            </div>
	                        </div>
	                    </div>
	                </div>
	                </c:forEach>
	             </c:when>						
				</c:choose>
					</div>

            </div>
            
                        <!--/.row   페이징 처리-->
            <div class="row">
                <div class="col-md-12 text-center">
                    <ul class="pagination pagination-lg">
                    
                    	<c:choose>
                    	
                    		<c:when test="${pageInfo.page <= 1 }">
                    			<li><a href="#"><i class="fa fa-long-arrow-left"></i></a></li>
                    		</c:when>
                    		<c:otherwise>
                    			<li><a href="productList.pr?page=${pageInfo.page - 1 }"><i class="fa fa-long-arrow-left"></i></a></li>
                    		</c:otherwise>
                    	</c:choose>
                    
                    	<c:forEach var="a" begin="${pageInfo.startPage }" end="${pageInfo.endPage }" step="1">
                    		
                    		<c:choose>
                    		
                    			<c:when test="${a == pageInfo.page }">
                    				<li class="active"><a>${a }</a></li>
                    			</c:when>
                    			<c:otherwise>
									<li><a href="productList.pr?page=${a }">${a }</a></li>
                    			</c:otherwise>
                    		</c:choose>
                    	
                    	</c:forEach>
                    		
                    		<c:choose>
                    		
                    			<c:when test="${pageInfo.page >= pageInfo.maxPage }">
                    				<li><a href="#"><i class="fa fa-long-arrow-right"></i></a></li>
                    			</c:when>
                    			<c:otherwise>
                    				<li><a href="productList.pr?page=${pageInfo.page + 1 }"><i class="fa fa-long-arrow-right"></i></a></li>
                    			</c:otherwise>
                    		</c:choose>
                    </ul>
                    <!--/.pagination-->
                </div>
            </div>
            <!--/.row   페이징 처리-->
        </div>
    </section>

	
	</section>
	<jsp:include page="/inc/bottom.jsp" />
	<!--/#footer-->
	<script src="js/product_modal.js"></script>
	<script src="js/jquery.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/jquery.prettyPhoto.js"></script>
	<script src="js/owl.carousel.min.js"></script>
	<script src="js/jquery.isotope.min.js"></script>
	<script src="js/main.js"></script>
</body>
</html>