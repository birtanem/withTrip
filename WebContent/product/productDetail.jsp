<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">
<title>Home | ProductDetail</title>

<!-- core CSS -->
<link href="css/productDetail.css" rel="stylesheet">
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
<script src="js/jquery-3.5.0.js"></script>

	<script type="text/javascript">
   	// 메뉴 액티브
   $(document).ready(function() {
	  $(".nav5").addClass("active"); 
   });
   </script>
   
<script type="text/javascript">
$(document).ready(function(){
	$('#minus').click(function(){
		var count=Number(document.getElementById("amount").value)-1;
		var meg="주문가능한 최소 수량은 1 입니다.";
		var max=${productDetail.p_amount};
		if(count<1){
			count=1;
			$(".detailCheck").css('color','red').html(meg);
			return false;
		}else if(count>max){
			$(".detailCheck").css('color','red').html("주문 가능한 수량을 초과했습니다.");
		}else{
			$(".detailCheck").html("");
		}
		document.getElementById("amount").value=count;
	});
	$('#plus').click(function(){
		var count=Number(document.getElementById("amount").value)+1;
		var max=${productDetail.p_amount};
		var meg="주문 가능한 수량을 초과했습니다.";
		if(count>max){
			$(".detailCheck").css('color','red').html(meg);
			return false;
		}else{
			$(".detailCheck").html("");
		}
		document.getElementById("amount").value=count;
	});
	
	 $("#orderBtn").click(function() {
		 			 	 
			if($("#amountcheck").html() == "품절") {
				alert("품절된 상품입니다.")
				return false;
				
			}

		 	var num = $("#num").val();
		 	var amount = $("#amount").val();
		 	var price = $("#price").val()*$("#amount").val();
		    var testList = new Array() ;
		         
		        // 객체 생성
		        var data = new Object() ;
		         
		     // 리스트에 생성된 객체 삽입
		        	
		        	data.num =  num
				    data.amount = amount
				    data.price = price
				    testList.push(data)
				    
//		     // String 형태로 변환
		    var jsonData = JSON.stringify(testList) ;
		     
			$.ajax("orderFront.or", {
				type:"POST",
				data: {"jsonData": jsonData,
					   "total": price*amount},
				success: function() {
					location.href="orderForm.or"
				}
			});
	});
	
	
	
	
});
function goCart(){
	
	if($("#amountcheck").html() == "품절") {
		var c = confirm("품절된 상품입니다. \n장바구니로 이동하시겠습니까?");
		if(c == true) {
			location.href="ProductCartList.ca";
		}else {
			return false;
		}
		
	}
	var id='admin'; 
	var amount=Number(document.getElementById("amount").value);
	var max=${productDetail.p_amount};
	if(id==null || id=="null"){
		alert("로그인 후 이용해주세요");
		location.href="MemberLoginForm.me";
		return false;
	}if(amount>max){
		document.getElementById("detailCheck").innerText="주문 가능한 수량을 초과했습니다.";
		return false;
	}
	location.href="ProductCartAdd.ca?p_num="+${productDetail.p_num}+"&p_amount="+amount;
}


</script>
<style type="text/css">
#portfolio p {font-size: 14pt; height: 40px;}
#product_detail {width: 500px; height: 450px;}
</style>
</head>
<body>
	<!-- 탑 -->
	<jsp:include page="/inc/top.jsp" />
	<div class="page-title"
		style="background-image: url(images/page-title.png)">
		<h1>여행상품</h1>
	</div>
	<!-- 본문 -->

<section id="portfolio">
	<div class="container">
		<div id="product_img">
			<img src="product/productUpload/${productDetail.p_image }"
				alt="product">
		</div>
		<div id="product_detail" style="margin-bottom: 200px;">
			<div>
				<input type="hidden" id="num" value="${productDetail.p_num}">
				<strong class="pd kw">#${productDetail.region_name }&nbsp;#${productDetail.p_theme }</strong>
				<p class="pd pn" style="font-size: 20pt; font-weight: 600; margin-top: 20px;">${productDetail.p_name }</p>
				<c:choose>
				<c:when test="${productDetail.p_amount>0 }">
					<p>보유 수량:&nbsp; <span id="amountcheck" style="color: red;">${productDetail.p_amount }</span>개</p>
					<input type="hidden" id="amount2" value="${productDetail.p_amount }">
				</c:when>
				<c:otherwise>
					<p>보유 수량:&nbsp; <span id="amountcheck" style="color: red; ">품절</span></p>
				</c:otherwise>
				
				</c:choose>
				
				<p>가격: &nbsp; <span style="color: #F77;"><fmt:formatNumber value="${productDetail.p_price }" pattern="###,###,###" />원</span></p>
				<input type="hidden" id="price" value="${productDetail.p_price }">
				<div style="border: 1px dashed #ddd; height: 200px; padding: 10px;">${productDetail.p_content }</div>
			</div>
			<div style="margin-top: 30px;">
				<span style="font-size: 14pt; margin-top: 100px;">선택 수량 &nbsp; </span> <input type="button" value="-" class="btn"
					id="minus"> <input type="text" value="1" id="amount"
					name="amount"> <input type="button" value="+" class="btn"
					id="plus"><br>
				<span class="detailCheck" id="detailCheck"></span>
			</div>
			<div style="margin: 50px;">
				<input type="button" class="btn btn-primary btn-lg"  value="구매하기" class="btn" id="orderBtn"> 
				<input type="button" class="btn btn-primary btn-lg"  value="장바구니" class="btn" onclick="goCart()">
				<input type="button" class="btn btn-primary btn-lg"  value="목록" onclick="history.back()">
			</div>
		</div>
	</div>

	</section>
	              
	<!--/#bottom-->
	<footer id="footer" class="midnight-blue">
		<div class="container">
			<div class="row">
				<div class="col-sm-6">
					&copy; 2013 <a target="_blank" href="http://shapebootstrap.net/"
						title="Free Twitter Bootstrap WordPress Themes and HTML templates">ShapeBootstrap</a>.
					All Rights Reserved.
				</div>
				<div class="col-sm-6">
					<ul class="pull-right">
						<li><a href="#">Home</a></li>
						<li><a href="#">About Us</a></li>
						<li><a href="#">Faq</a></li>
						<li><a href="#">Contact Us</a></li>
					</ul>
				</div>
			</div>
		
		</div>
	
	</footer>
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