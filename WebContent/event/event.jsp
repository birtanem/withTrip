<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
/*

event_win 쿠폰 컬럼 삭제
e_edate default 값 설정 

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
<%-- <script src="<c:url value="js/event.js" />"></script> --%>
<script src="<c:url value="js/eventPop.js" />"></script>

<!-- /이벤트 css, js -->

    <link rel="shortcut icon" href="images/ico/favicon.ico">
    <link rel="apple-touch-icon-precomposed" sizes="144x144" href="images/ico/apple-touch-icon-144-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="114x114" href="images/ico/apple-touch-icon-114-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="72x72" href="images/ico/apple-touch-icon-72-precomposed.png">
    <link rel="apple-touch-icon-precomposed" href="images/ico/apple-touch-icon-57-precomposed.png">
    <script type="text/javascript">
   		// 메뉴 액티브
	   $(document).ready(function() {
		  $(".nav7").addClass("active"); 
	   });
   </script>
   
<script type="text/javascript">

$(document).ready(function() {

	$('#btn').keydown(function() {
		
		if( $('#enter').val() != "admin") {
			
			if (event.keyCode == 13) {
			    event.preventDefault();
			  };
		}
		  
	});
	
	$('#btn').click(function() {
		
		
		$.ajax({
			
			url: "eventPull.ev",
			dataType: "json",
			success: function(rdata) {

				if(rdata.check == 30000 ) {
					
					$('.p1').html("<img src='images/eventCoupon.png' class='img'><br>");
					
					$('#myCoupon').html(rdata.point)
					$('#c3').html(rdata.coupon);
// 					alert("30000P 쿠폰에 당첨되셨습니다!");
				}else if(rdata.check == 50000) {
					
					$('.p1').html("<img src='images/eventCoupon2.png' class='img'><br>");
					
					$('#myCoupon').html(rdata.point)
					$('#c5').html(rdata.coupon);
// 					alert("50000P 쿠폰에 당첨되셨습니다!");
				}else if(rdata.check == 100000) {
					
					$('.p1').html("<img src='images/eventCoupon3.png' class='img'><br>");
					
					$('#myCoupon').html(rdata.point)
					$('#c10').html(rdata.coupon);
// 					alert("100000P 쿠폰에 당첨되셨습니다!");
				}else if(rdata.check == 1) {
					$('.p1').html("<img src='images/x.PNG' class='img'><br>");
					$('#myCoupon').html(rdata.point)

				}else if(rdata.check == 0){
					
					alert("종료되었습니다!")
					location.reload();
				}else if (rdata == 0) {
					
					alert("포인트가 부족합니다!")
					return false;
				}
				
				$("#btn").css('opacity','0').css('pointer-events','none');
				
				setTimeout(function() {
					
					$(".p1").html("<img src='images/box.png' id='img'><br>");
					$("#btn").css('opacity','1').css('pointer-events','auto');
					
				}, 3000);
				

			}
		})

    });
	
	// 버튼 hover img 효과
// 	$('#btn').mouseover(function() {
		
// 		$('#img').css('-webkit-animation','wobble-hor-bottom 0.8s infinite alternate').css('animation','wobble-hor-bottom 0.8s infinite alternate')
// 	});
// 	$('#btn').mouseout(function() {
		
// 		$('#img').css('-webkit-animation','').css('animation','');
// 	});
});
</script>
<script type="text/javascript">
$(document).ready(function() {
	
	var addDate = document.getElementById("hid").value;		
	var ts = new Date(addDate);
	var newYear = true;

	if((new Date()) > ts){

		$('.p1').html("<img src='images/a.png' class='img'><br>");
		$("#btn").css('opacity','0').css('pointer-events','none');
		
		newYear = false;
	}
	
	$('#countdown').empty();
	
	$('#countdown').countdown({
		timestamp	: ts,
		callback	: function(days, hours, minutes, seconds){
		
		}
	});
});

function adminPop() {
	
	window.open("eventAdminPop.ev", "관리자", "width=500,height=500,top=150,left=610,resizable=no");

}
</script>
</head>

<body>
<input type="hidden" id="enter" value="${sessionScope.id }">
<jsp:include page="../inc/top.jsp"></jsp:include>

    <div class="page-title" style="background-image: url(images/top/Busan5.jpg); background-size: cover; background-position: center;">
        <h1>무료 이벤트</h1>
    </div>

    <section id="contact-page" >
<br>

    	<div class="event">
    	<input type="hidden" id="hid" value="${date}">
    	
      	
    		<c:choose>
    		<c:when test="${sessionScope.id eq 'admin'}">
    		<p class="p">${sessionScope.id } 님의 포인트  <em><i id="myCoupon">${article.point}</i>점</em></p>
    		<a href="javascript:void(0);" class="fun-btn btn btn-primary btn-lg"  onclick="return adminPop()">관리자</a>
    		</c:when>
    		<c:when test="${!empty sessionScope.id}">
    		<p class="pp">나의 포인트  <em><i id="myCoupon">${article.point}</i>점</em></p>
    		</c:when>
    		<c:otherwise>
    		<p class="ppp">포인트를 모아 뽑기에 도전해보세요!</p>
    		</c:otherwise>
    		</c:choose>			
			<c:if test="${sessionScope.id == 'admin'}">
			
			</c:if>
		</div>
		
		<p class="description">* <b>1회</b>당 <b>100포인트</b>가 차감됩니다.</p>
		<div style="border: 1px solid; width: 800px; height: 920px;margin: auto; background-image: url('images/event8.png'); background-size: contain;">
		<div id="countdown" style="margin-top: 50px;"></div>
		<ul class="ul">
		<li class="li">Day</li>
		<li class="li">Hour</li>
		<li class="li">Minute</li>
		<li class="li">Second</li>
		</ul>
		<h2 id="sample02"></h2>
		
		<p class="p1"><img src="images/box.png" id="img"></p>
		<div id="eventBox">
		<c:if test="${sessionScope.id != null}">
		<input type="button" value="지금 뽑기" id="btn" width="100" height="100" class="fun-btn btn btn-primary btn-lg"><br>
		</c:if>
		</div>
		</div>	
				<div class="couponArea" id="cpArea">
				<div class="cpStatus " class="row" >
					<ul>
						<li style="background-image: url('images/event10.png'); background-size: contain;">
						
								<i class="ico"></i>
								<strong class="str">포인트 30000</strong>
			
								<p class="p">보유쿠폰: <em id="c3"><c:if test="${sessionScope.id == null}">0</c:if>${article.cp_3}</em> </p>
								<c:if test="${sessionScope.id != null}">
								
								<input class="fun-btn btn btn-primary btn-lg" type="button" value="교환" onclick="location.href='eventExchangePoint.ev?point=3'">
								</c:if>
					
						</li>
						<li class="review  emth5">
							
								<i class="ico"></i>
								<strong class="str">포인트 50000</strong>
								
								<p class="p">보유쿠폰: <em id="c5"><c:if test="${sessionScope.id == null}">0</c:if>${article.cp_5}</em> </p>
								<c:if test="${sessionScope.id != null}">							
								<input class="fun-btn btn btn-primary btn-lg" type="button" value="교환" onclick="location.href='eventExchangePoint.ev?point=5'">
								</c:if>
						</li>
						<li class="ad  emth8">
							
								<i class="ico"></i>
								<strong class="str">포인트 100000</strong>
								
								<p class="p">보유쿠폰: <em id="c10"><c:if test="${sessionScope.id == null}">0</c:if>${article.cp_10}</em> </p>	
								<c:if test="${sessionScope.id != null}">							
								<input class="fun-btn btn btn-primary btn-lg" type="button" value="교환" onclick="location.href='eventExchangePoint.ev?point=10'">
								</c:if>
						</li>
					</ul>
				</div>

			</div>

					<div class="warning">
				<h3>이벤트 응모 시 유의사항</h3>
				<ul class="first">
					<li>비정상적으로 참여하거나 상업적 용도로 참여하는 경우 당첨에 제외되며, 취소될 수 있습니다. </li>
					<li>포인트 쿠폰은 당첨 즉시 사용 가능합니다.</li>		
					<li>본 이벤트는 부득이한 사정에 의해 조기종료 될 수 있습니다.</li>
				</ul>
							</div>

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