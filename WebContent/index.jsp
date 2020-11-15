<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">
<title>Home | With Trip</title>

<!-- core CSS -->
<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/font-awesome.min.css" rel="stylesheet">
<link href="css/animate.min.css" rel="stylesheet">
<link href="css/prettyPhoto.css" rel="stylesheet">
<link href="css/owl.carousel.min.css" rel="stylesheet">
<link href="css/icomoon.css" rel="stylesheet">
<link href="css/main.css" rel="stylesheet">
<link href="css/responsive.css" rel="stylesheet">

<!--[if lt IE 9]>
    <script src="js/html5shiv.js"></script>
    <script src="js/respond.min.js"></script>
    <![endif]-->
<link
	href="https://fonts.googleapis.com/css2?family=Covered+By+Your+Grace&family=Gochi+Hand&family=Noto+Sans+KR:wght@300&display=swap"
	rel="stylesheet">
<link rel="shortcut icon" href="images/ico/favicon.ico">
<link rel="apple-touch-icon-precomposed" sizes="144x144"
	href="images/ico/apple-touch-icon-144-precomposed.png">
<link rel="apple-touch-icon-precomposed" sizes="114x114"
	href="images/ico/apple-touch-icon-114-precomposed.png">
<link rel="apple-touch-icon-precomposed" sizes="72x72"
	href="images/ico/apple-touch-icon-72-precomposed.png">
<link rel="apple-touch-icon-precomposed"
	href="images/ico/apple-touch-icon-57-precomposed.png">

<!--  맵 css, js -->
<link href="css/map.css" rel="stylesheet">

<script src="<c:url value="/js/map.js" />"></script>
<script src="js/jquery-3.5.0.js"></script>
<!--  맵 css, js -->



<script src="js/jquery-3.5.0.js"></script>






<!-- 실시간검색어 -->

<style type="text/css">
#realcontent {
	width: 250px;
	margin-top: 30px;
	margin-left: 120px;
	padding: 10px;
	background: white;
	text-align: center;
}

#rank-list a {
	color: black;
	text-decoration: none;
}

#rank-list a:hover {
	text-decoration: underline;
}

#rank-list {
	overflow: hidden;
	width: 250px;
	text-align: left;
	height: 20px;
	margin: 0;
}

#rank-list dd {
	position: relative;
	margin-left: 100px;
	
}

#rank-list ol {
	position: absolute;
	top: 0;
	left: 0;
	margin: 0;
	padding: 0;
	list-style-type: none;
	
}

#rank-list li {
	width: 200px;
	text-align: left;
	height: 20px;
	line-height: 20px;
}

.rankingcontent {
	display: none;
	padding: 6px 12px;
	border: 1px solid #ccc;
	border-top: none;
	width: 250px;
	left: 0;
}

#maincontent {
	border: 5px solid;
}

#mainImg {
	width: 100%;
	height: 1200px;
	background-position: center;
	background-attachment: fixed;
}

#bgImg {
	width: 110%;
	height: 100%;
}

.mainText {
	text-align: center;
	padding-top: 300px;
	padding-bottom: 300px;
}

.mainBox span {
	padding-top: 5px;
	color: white;
	font-size: 32px;
	color: white;
}

.mainBox h2 {
	margin-top: 40%;
}

.mainBox {
	width: 500px;
	height: 500px;
	border: 3px solid white;
	text-align: center;
	margin: 0 auto;
}

.topH {
	position: absolute;
	width: 100%;
	margin-left: 80px;
}

a:hover {
	color: #EC5538;
}

.topLogin {
	float: right;
	margin-top: 20px;
	margin-right: 200px;
}

.logo {
	margin-top: 50px;
	margin-left: 40px;
	float: left;
}

.topli {
	margin-top: 50px;
	float: right;
}

.pl_t {
	font-family: 'Noto Sans KR', sans-serif;
	font-size: 30px;
	margin-bottom: 35px;
	font-weight: bold;
}

.eng {
	font-family: 'Covered By Your Grace', cursive;
	letter-spacing: 5px;
}

.place {
	width: 800px;
	margin: 0 auto;
}

#mig {
	margin-right: 10px;
	margin-bottom: 10px;
}

#mig:hover {
	box-shadow: 0px 0 5px 5px #ccc;
	transition: 0.3s;
}

.single-slide a {
	display: inline-block;
}

/* .rc_img { */
/* 	height: 270px; */
/* 	width: 320px; */
/* } */
.rcbox{
margin-top: 150px;}
</style>

    <script type="text/javascript">
   		// 메뉴 액티브
	   $(document).ready(function() {
		  $(".nav2").addClass("active"); 
	   });
   </script>
   
<script type="text/javascript">
	$(function() {
		var count = $('#rank-list li').length;
		var height = $('#rank-list li').height();

		function step(index) {
			$('#rank-list ol').delay(2000).animate({
				top : -height * index,
			}, 300, function() {
				step((index + 1) % count);
			});
		}
		step(1);
	});
</script>

<!-- placeList main -->
<script type="text/javascript">
	// 메인사진 랜덤 출력
	$(document).ready(function() {
		var imgRan = Math.round(Math.random() * 13);
		var imgPath = ('images/main/busan' + imgRan + '.jpg');
		// 	document.getElementById('bgImg').src=imgPath;
		$('#mainImg').css('background-image', 'url(' + imgPath + ')');
	});
</script>
</head>
<!--/head-->

<body class="homepage">

<jsp:include page="/inc/top.jsp" />

	<section id="main-map" class="no-margin">
		<div id="mainImg" class="mainImg">
			<div class="mainText">
				<div class="mainBox">
					<h2>
						<span>당신과 함께, 부산</span>
					</h2>

					<div id="realcontent">
						<dl id="rank-list">

						<dt>인기 여행지</dt>
						<dd>
							<ol>
								<c:forEach var="list" items="${list }" begin="0" end="10" varStatus="stat">
									<li><a href="PlaceDetail.pl?pl_num=${list.pl_num}">
									
										<c:choose>
											<c:when test="${fn:length(list.pl_name) gt '10'}">
												<b>${stat.count }</b>&nbsp; ${fn:substring(list.pl_name,0,10) }&nbsp;...	
											</c:when>
											<c:otherwise>
														<b>${stat.count }</b>&nbsp; ${list.pl_name }											
											</c:otherwise>
												</c:choose>

										</a></li>
								</c:forEach>
							</ol>
						</dd>

						</dl>
					</div>
				</div>


			</div>
		</div>
		<!-- 				<img id="bgImg"> -->

	</section>

	<section id="testimonial">
		<!-- 추천 장소-->
		<!-- 		<div class="place"> -->
		<div class="container rcbox">
			<c:choose>
				<c:when test="${empty sessionScope.id }">
					<div class="fadeInDown" style="text-align: left;">
						<p class="pl_t">
							<span class="kor">방문자 </span>&nbsp;님을 위한 추천 여행지
						</p>
					</div>
					<div class="testimonial-slider owl-carousel">
						<c:forEach var="list" items="${list }" varStatus="stat">
							<!-- 							<div id="here" class="row isotope-item here"></div> -->
							<a href="PlaceDetail.pl?pl_num=${list.pl_num }"><img
								src="placeUpload/${list.pl_image }" alt="rc_pList" width="360px" height="270px"
								class="rc_img"></a>
						</c:forEach>
					</div>
				</c:when>

				<c:otherwise>
					<div class="fadeInDown" style="text-align: left;">
						<p class="pl_t">
							<span class="eng">${sessionScope.id }</span> &nbsp;님을 위한 추천 여행지
						</p>
					</div>
					<div class="testimonial-slider owl-carousel">
						<c:forEach var="list" items="${list }" varStatus="stat">
							<%-- 								alert(${list.pl_theme eq sessionScope.session }); --%>
							<c:if test="${list.pl_theme eq sessionScope.session }">
								<!-- 							<div id="here" class="row isotope-item here"></div> -->
								<a href="PlaceDetail.pl?pl_num=${list.pl_num }"><img
									src="placeUpload/${list.pl_image }" alt="${list.pl_theme }" width="360px" height="270px"
									class="rc_img"></a>
							</c:if>
						</c:forEach>
					</div>
				</c:otherwise>
			</c:choose>
		</div>



		<!-- 		</div> -->


		<!-- 			<section id="testimonial"> -->
		<!-- 		<div class="container"> -->
		<!-- 			<div class="center fadeInDown"> -->
		<!-- 				<h2>Testimonials</h2> -->
		<!-- 			</div> -->
		<!-- 			<div class="testimonial-slider owl-carousel" width="360" height="370"> -->
		<!-- 				<a herf="#"><img src="images/client1.jpg" alt=""></a> -->
		<!-- 				<img src="images/client1.jpg" alt=""> -->
		<!-- 				<img src="images/client1.jpg" alt=""> -->
		<!-- 			</div> -->
		<!-- 		</div> -->
		<!-- 	</section> -->




		<!-- 		추천 상품 -->
		<div class="container rcbox">
				<c:choose>
					<c:when test="${empty sessionScope.id }">
						<div class="fadeInDown" style="text-align: left;">
							<p class="pl_t">
								<span class="kor">방문자 </span>&nbsp;님을 위한 추천 상품
							</p>
						</div>
						<div class="testimonial-slider owl-carousel">
							<c:forEach var="pdList" items="${pdList }" varStatus="stat">
							<div>
								<a href="productDetail.pr?p_num=${pdList.p_num }"><img
									src="product/productUpload/${pdList.p_image }" alt="rc_pList"
									height="200px" width="320px"></a>
								   <h5>${pdList.p_name }</h5>
	                            <span class="desg">${pdList.p_theme }</span><br>
	                               <span style="color: #F76;"><fmt:formatNumber value="${pdList.p_price }"
									pattern="###,###,###" /> 원</span>
	                            </div>
							</c:forEach>
						</div>
					</c:when>
					<c:otherwise>
						<div class="fadeInDown" style="text-align: left;">
							<p class="pl_t">
								<span class="eng">${sessionScope.id }</span> &nbsp;님을 위한 추천 상품
							</p>
						</div>
						<div class="testimonial-slider owl-carousel">
							<c:forEach var="pdList" items="${pdList }" varStatus="stat">
								<c:if test="${pdList.p_theme eq sessionScope.session }">
								<div>
									<a href="productDetail.pr?p_num=${pdList.p_num }"><img
										src="product/productUpload/${pdList.p_image }"
										alt="${pdList.p_theme }" height="270px" width="320px"></a>
										   <h5>${pdList.p_name }</h5>
	                            <span class="desg">${pdList.p_theme }</span><br>
	                               <span style="color: #F76;"><fmt:formatNumber value="${pdList.p_price }"
									pattern="###,###,###" /> 원</span>
	                            </div>
								</c:if>
							</c:forEach>
						</div>
					</c:otherwise>
				</c:choose>
			</div>
	</section>


	<section id="partner">
		<div class="container">

			<div class="partners">
				<ul>
					<li><a href="http://www.busan.go.kr/open/index.jsp"
						target="_blank"><img class="img-responsive fadeInDown"
							data-wow-duration="1000ms" data-wow-delay="300ms"
							src="images/partners/부산.gif"></a></li>
					<li><a href="http://www.bfo.or.kr/main/index.asp"
						target="_blank"><img class="img-responsive fadeInDown"
							data-wow-duration="1000ms" data-wow-delay="600ms"
							src="images/partners/관광.jpg"></a></li>
					<li><a href="https://bto.or.kr/kor/Main.do" target="_blank"><img
							class="img-responsive fadeInDown" data-wow-duration="1000ms"
							data-wow-delay="900ms" src="images/partners/공사.jpg"></a></li>
					<li><a href="http://busan.grandculture.net/?local=busan"
						target="_blank"><img class="img-responsive fadeInDown"
							data-wow-duration="1000ms" data-wow-delay="1500ms"
							src="images/partners/역사.jpg"></a></li>
					<li><a href="https://korean.visitkorea.or.kr/main/main.do"
						target="_blank"><img class="img-responsive fadeInDown"
							data-wow-duration="1000ms" data-wow-delay="300ms"
							src="images/partners/구석.jpg"></a></li>
				</ul>
			</div>
		</div>
		<!--/.container-->
	</section>
	<!--/#partner-->


	<jsp:include page="inc/bottom.jsp"></jsp:include>
	<!--/#footer-->

	<script src="js/jquery.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/jquery.prettyPhoto.js"></script>
	<script src="js/owl.carousel.min.js"></script>
	<script src="js/jquery.isotope.min.js"></script>
	<script src="js/main.js"></script>
</body>

</html>
