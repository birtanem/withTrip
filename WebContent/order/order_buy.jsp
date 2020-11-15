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

<link href="css/order.css" rel="stylesheet">

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

<script type="text/javascript"
	src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
<script type="text/javascript"
	src="https://cdn.iamport.kr/js/iamport.payment-1.1.5.js"></script>

<script src="js/jquery-3.5.0.js"></script>
<script type="text/javascript">
function paytest(){
	requestPay();
}
</script>
	<script type="text/javascript">
   	// 메뉴 액티브
//    $(document).ready(function() {
// 	  $(".nav5").addClass("active"); 
//    });
   </script>
<script type="text/javascript">
//결제API
function requestPay(){
var IMP = window.IMP; // 생략가능
var buyer_name=document.getElementById("o_name").value;
var name = document.getElementsByName("name").value;
var buyer_tel=document.getElementById("o_phone").value;
var buyer_email=document.getElementById("email").value;
var amount=document.getElementById("total_p").value;

IMP.init('imp05249928'); // 'iamport' 대신 부여받은 "가맹점 식별코드"를 사용

IMP.request_pay({
    pg : 'html5_inicis',
    pay_method : 'card',
    merchant_uid : 'merchant_' + new Date().getTime(),
    name : name,
    amount : 100, //임의의 가격 100원
    buyer_email : 'iamport@siot.do',
    buyer_name : buyer_name,
    buyer_tel : buyer_tel,
}, function(rsp) {
    if ( rsp.success ) {
//         var msg = '결제가 완료되었습니다.';
//         msg += '고유ID : ' + rsp.imp_uid;
//         msg += '상점 거래ID : ' + rsp.merchant_uid;
//         msg += '결제 금액 : ' + rsp.paid_amount;
//         msg += '카드 승인번호 : ' + rsp.apply_num;
        payData();
   		
	} else {
        var msg = '결제에 실패하였습니다.';
        msg += '에러내용 : ' + rsp.error_msg;
    alert(msg);
    }
});
}
</script>
<script type="text/javascript">
function preCheck(){	
// 		if($("#emailcheck").html() != "인증 완료") {
// 			alert("이메일 인증이 필요합니다.");
// 			return false;
// 		}
		if($('input[name="payMethod"]:checked').val() == null) {
			alert("결제수단을 선택해주세요!");
			return false;
		}
// 		paytest();
		payData();
}
</script>
<script type="text/javascript">
function payData(){	
	$(document).ready(function(){
	 	var total = commasWithNumber($("#total_tn").html());
		var point = commasWithNumber($("#desc_point").html());
		var pay = $('input[name="payMethod"]:checked').val();
		var num = document.getElementsByName("num");
		var img = document.getElementsByName("img");
		var name = document.getElementsByName("name");
		var amount = document.getElementsByName("amount");
		var price = document.getElementsByName("price");
		var testList = new Array();
		
		for (var i = 0; i < name.length; i++) {
			// 객체 생성
			var data = new Object();
			// 리스트에 생성된 객체 삽입
			data.num = num[i].value;
			data.img = img[i].value;
			data.name = name[i].value;
			data.amount = amount[i].value;
			data.price = price[i].value;
			testList.push(data);
		}
		// String 형태로 변환
		var jsonData = JSON.stringify(testList);
	
		$.ajax("orderAdd.or", {
			type : "POST",
			data : {
				"jsonData" : jsonData,
				"total" : total,
				"point" : point,
				"pay" : pay
			},
			success : function(rdata) {
				if(rdata != "" ) {
					alert(rdata)
					return false;
				}else {
					location.href = "orderResult.or"	
				}
				
			}
		});
	});
	}
</script>
<script type="text/javascript">
		// , 빼기
		 function commasWithNumber(x) {
			    return x.toString().replace(/\,/g,"");
			}
		// , 넣기
		function comma(x){
			return x.toString().replace(/\B(?=(\d{3})+(?!\d))/g,",");
		}	
		$(document).ready(function(){
			// comma 사용하니까 포인트 입력된 상태에서 다시 수정하려니까 최종결제금액에 NaN 떠서 #inPoint 콤마 일단 지움
			$('.btn_point').click(function(){
				
				$('.msg').html("");
				
				var inPoint = ${sessionScope.info.point };
				var total=${sessionScope.total};
				var p_total=total-inPoint;
				
				if(total >= inPoint) {
			
					document.getElementById("inPoint").value=inPoint;
					$('#desc_point').html(comma(inPoint));
// 					$('#total_tn').html(comma(p_total));
					$('#total_tn').html(comma(total - commasWithNumber($('#desc_point').html())));
				}else {
					
					document.getElementById("inPoint").value=total;
					$('#desc_point').html(comma(total));
// 					$('#total_tn').html(comma(total));
					$('#total_tn').html(comma(total - commasWithNumber($('#desc_point').html())));
				}

			});
			// comma 사용하니까 포인트 입력된 상태에서 다시 수정하려니까 최종결제금액에 NaN 떠서 #inPoint 콤마 일단 지움
			$('#inPoint').on('change',function(){
			
				var point = ${sessionScope.info.point };
				var inPoint=$('#inPoint').val();
				var total=${sessionScope.total};
				var p_total=total-inPoint;
				document.getElementById("inPoint").value=inPoint;

				if(point < inPoint) {
					$('.msg').html(" 사용 가능한 포인트를 초과했습니다.").css('color','red');
					document.getElementById("inPoint").value="";
					$('#total_tn').html(comma(total));
				}else if(p_total<0) {
					$('.msg').html(" 사용한 포인트가 결제 금액을 초과했습니다.").css('color','red');
					document.getElementById("inPoint").value="";
					$('#desc_point').html(0);
					$('#total_tn').html(comma(total));
				}else{
					$('.msg').html("");
					$('#desc_point').html(comma(inPoint));
					$('#total_tn').html(comma(total - commasWithNumber($('#desc_point').html())));
					
				}
				
			
			});
		});
		
		$("input:radio[name=payMethod]").click(function(){
			
			$(".pm").css("display","none")
			
			var payMethod = $("input[name=payMethod]:checked").val();
			
	        if(payMethod == "1"){
	        	
	        	$(".t_card").css("display","block")
	        	
	        }else if(payMethod == "2") {
	        	$(".t_at").css("display","block")
	        }else if(payMethod == "3") {
	        	$(".t_dps").css("display","block")
	        }else if(payMethod == "4") {
	        	$(".t_ph").css("display","block")	        	
	        }else if(payMethod == "5") {
	        	$(".t_cul").css("display","block")	        	
	        }else if(payMethod == "6") {
	        	$(".t_book").css("display","block")	        	
	        }else if(payMethod == "7") {
	        	$(".t_payco").css("display","block")	        	
	        }else if(payMethod == "8") {
	        	$(".t_kakao").css("display","block")
	        	
	        }
	       
	    });
		
		$("#email").blur(function() {
			if($("#email").val() != $("#hemail").val()) {
				$("#emailcheck").html("인증 필요")
				$("#emailcheck").css("color","red");	
			}else {
				$("#emailcheck").html("인증 완료")
				$("#emailcheck").css("color","blue");
			}
			
		});
		
</script>
<script type="text/javascript">
function emailCheck() {
	alert("이메일을 확인하여 인증해주세요")
	$.ajax({
		url: "orderAuthentication.or",
		type: "POST",
		data: {"email":$("#email").val()},
		success: function() {
		}
	})
}
</script>

</head>
<body>
	<jsp:include page="/inc/top.jsp" />
	<div class="page-title"
		style="background-image: url(images/page-title.png);">
		<h1>주문/결제</h1>
	</div>
	<section id="portfolio">
		<div class="center">
			<p class="lead">주문목록</p>
		</div>
		<div class="container">
			<div class="o_info">
				<h2>주문자정보</h2>
				<table class="ot_info">
					<tr>
						<th>주문자명</th>
						<td><input type="text" name="o_name" id="o_name"
							value="${sessionScope.info.name}" class="o_input"></td>
					</tr>
					<tr>
						<th>휴대폰</th>
						<td><input type="text" name="o_phone" id="o_phone"
							value="${sessionScope.info.phone} " class="o_input"></td>
					</tr>
					<tr>
						<th>이메일</th>
						<td><c:choose>
								<c:when test="${not empty sessionScope.email}">
									<input type="text" id="email" name="o_name"
										value="${sessionScope.email}" class="o_input">
									<!-- 이메일 변경 시 확인용 -->
									<input type="hidden" id="hemail" name="o_name"
										value="${sessionScope.email}" class="o_input">
								</c:when>
								<c:otherwise>
									<input type="text" id="email" name="o_name"
										value="${sessionScope.info.email}" class="o_input">

								</c:otherwise>
							</c:choose> <input type="button" value="인증하기" onclick="emailCheck()">
							<c:if test="${!empty sessionScope.emailOk}">
								<p id="emailcheck" style="color: blue; margin: 5px;">인증 완료</p>
							</c:if></td>
					</tr>
				</table>
			</div>
			<div class="o_list">
				<h2>배송상품</h2>
				<table class="ot_list">
					<tr>
						<th colspan="2">상품정보</th>
						<th>가격</th>
						<th>수량</th>
						<th>합계</th>
					</tr>
					<c:forEach var="p" items="${sessionScope.arrayList }"
						varStatus="status">
						<tr>
							<td><img src="product/productUpload/${p.p_image }"
								width="200" height="100"><input type="hidden" name="img"
								value="${p.p_image }"></td>
							<td>${p.p_name }<input type="hidden" name="name"
								value="${p.p_name }"><input type="hidden" name="num"
								value="${p.p_num }"></td>
							<td class="price"><fmt:formatNumber value="${p.p_price/p.p_amount }"
									pattern="###,###,###" />원<input type="hidden" name="price"
								value="<fmt:formatNumber value="${p.p_price/p.p_amount }" pattern="0"/>"></td>
							<td>${p.p_amount }<input type="hidden" name="amount"
								value="${p.p_amount }"></td>
							<td class="price"><fmt:formatNumber value="${p.p_price}"
									pattern="###,###,###" />원</td>
						</tr>
					</c:forEach>
				</table>
			</div>
			<div class="o_point">
				<h2>포인트사용</h2>
				<table class="ot_point">
					<tr>
						<th>withTrip 포인트 : <span class="price point"><fmt:formatNumber value="${sessionScope.info.point }" pattern="###,###,###" /></span></th>
						<td><input type="text" value="" class="o_input" id="inPoint">
							<input type="button" value="전액사용" class="btn btn_point"><span
							class="msg"></span></td>
					</tr>
				</table>
			</div>
			<div class="payment">
				<div class="o_pay">
					<h2>결제수단 선택</h2>
					<table class="ot_pay">
						<tr>
							<td><input type="radio" name="payMethod" value="신용카드">신용카드</td>
							<td><input type="radio" name="payMethod" value="계좌이체">계좌이체</td>
							<td><input type="radio" name="payMethod" value="무통장입금">무통장입금</td>
							<td><input type="radio" name="payMethod" value="휴대폰결제">휴대폰결제</td>
						</tr>
						<tr>
							<td><input type="radio" name="payMethod" value="문화상품권">문화상품권</td>
							<td><input type="radio" name="payMethod" value="도서상품권">도서상품권</td>
							<td><input type="radio" name="payMethod" value="PAYCO">PAYCO</td>
							<td><input type="radio" name="payMethod" value="카카오페이">카카오페이</td>
						</tr>
					</table>
					<div>
						<!-- 신용카드 선택시 -->
						<table class="pm t_card" style="border: 1ox solid;">
							<tr>
								<th>카드종류</th>
								<td><select id="pm_card" name="pm_card">
										<option value="">카드를 선택해주세요.</option>
										<option value="BCC">BC카드</option>
										<option value="HNB">하나비자</option>
										<option value="PHB">우리카드</option>
										<option value="SYH">신협카드</option>
										<option value="NFF">수협카드</option>
										<option value="CBB">전북카드</option>
										<option value="CIT">씨티카드</option>
										<option value="WIN">삼성카드</option>
										<option value="LGC">신한카드</option>
										<option value="KJB">광주비자</option>
										<option value="CJB">제주카드</option>
										<option value="DIN">현대카드</option>
										<option value="AMX">롯데카드</option>
										<option value="CNB">KB카드</option>
										<option value="NLC">NH카드</option>
										<option value="KEB">외환카드</option>
								</select></td>
							</tr>
							<tr>
								<th>할부종류</th>
								<td><select id="pm_card_mt" name="pm_card">
										<option value="00">일시불</option>
										<option value="01">3개월</option>
										<option value="02">6개월</option>
										<option value="03">12개월</option>
								</select></td>
							</tr>
						</table>

						<!-- 계좌이체 선택시 -->
						<table class="pm t_at">
							<tr>
								<th>결제안내</th>
								<td><ul>
										<li>계좌이체로 결제 완료시 본인 계좌에서 즉시 이체 처리됩니다.</li>
										<li>실시간 계좌이체는 은행별 이용시간이 다를 수 있습니다.</li>
									</ul></td>
						</table>

						<!-- 무통장입금 선택시 -->
						<%
							Calendar cal = Calendar.getInstance();
							cal.add(Calendar.DATE, +7);
							Date calDate = cal.getTime();
						%>
						<c:set var="addDate">
							<fmt:formatDate value="<%=calDate%>" pattern="yyyy.MM.dd" />
						</c:set>
						<table class="pm t_dps">
							<tr>
								<th>은행명</th>
								<td><select id="pm_depositBank" name="pm_depositBank">
										<option value="003">기업</option>
										<option value="004">국민</option>
										<option value="007">수협</option>
										<option value="011">농협</option>
										<option value="020">우리</option>
										<option value="031">대구</option>
										<option value="032">부산</option>
										<option value="039">경남</option>
										<option value="081">하나</option>
										<option value="088">신한</option>
								</select></td>
							</tr>
							<tr>
								<th>입금기한</th>
								<td>${addDate}</td>
							</tr>
							<tr>
								<th>입금자명</th>
								<td><input type="text" value="${sessionScope.info.name}"
									class="o_input"></td>
							<tr>
								<td><ul>
										<li>은행별로 입금가능 시간이 다를 수 있습니다.</li>
										<li>주문일 기준 다음날(24시간 이내)까지 입금이 되지 않으면 주문취소 처리 됩니다.</li>
									</ul></td>
							</tr>
						</table>
						<!-- 					휴대폰 결제 선택 -->
						<table class="pm t_ph">
							<tr>
								<th>결제안내</th>
								<td><ul>
										<li>휴대폰 결제는 50만원까지 결제가 가능합니다.</li>
										<li>한도문의는 모빌리언스(1600-0523), 다날(1566-3355)로 문의주시기 바랍니다.</li>
									</ul></td>
							</tr>
						</table>
						<!-- 					문화상품권 선택시  -->
						<table class="pm t_cul">
							<tr>
								<td><span><h3>보유하신 컬쳐캐쉬(문화상품권) 내역 조회 후 사용이
											가능합니다.</h3></span> <input type="button" value="조회" class="btn"></td>
							</tr>
							<tr>
								<td>
									<ul>
										<li>문화상품권을 컬쳐캐쉬로 충전 후 사용하실 수 있습니다.</li>
										<li>컬쳐캐쉬 충전 및 사용내역, 현금영수증은 컬쳐랜드 홈페이지에서 확인하실 수 있습니다.</li>
										<li>컬쳐캐쉬는 결제금액 전액으로만 사용이 가능합니다.</li>
									</ul>
								</td>
							</tr>
						</table>
						<!-- 					도서상품권 선택시  -->
						<table class="pm t_book">
							<tr>
								<td><span><h3>보유하신 북앤라이프 캐쉬(도서상품권) 내역 조회 후 사용이
											가능합니다.</h3></span> <input type="button" value="조회" class="btn"></td>
							</tr>
							<tr>
								<td>
									<ul>
										<li>도서상품권을 북앤라이프 캐쉬로 충전 후 사용하실 수 있습니다.</li>
										<li>북앤라이프 캐쉬 충전 및 사용내역은 북앤라이프 홈페이지에서 확인하실 수 있습니다.</li>
										<li>북앤라이프 캐쉬는 결제금액 전액으로만 사용이 가능합니다.</li>
									</ul>
								</td>
							</tr>
						</table>
						<!-- 					PAYCO 선택시 -->
						<table class="pm t_payco">
							<tr>
								<td><ul>
										<li>PAYCO는 NHN엔터테인먼트에서 제공하는 안전한 간편결제 서비스로 withTrip에서는
											신용카드 결제가 가능합니다.</li>
										<li>신용카드 등록 시 휴대폰과 카드명의자가 동일해야합니다. (모든 신용/체크카드 가능)</li>
										<li>무이자할부는 PAYCO 결제창에서 확인하실 수 있습니다.</li>
									</ul></td>
							</tr>
						</table>
						<!-- 					카카오페이 선택시 -->
						<table class="pm t_kakao">
							<tr>
								<td><ul>
										<li>카카오페이는 주식회사 카카오페이에서 제공하는 안전한 간편결제 서비스로 withTrip에서는
											신용카드 결제가 가능합니다.</li>
										<li>무이자할부는 카카오페이 모바일 결제창에서 선택하실 수 있습니다.</li>
										<li>휴대폰과 카드명의자가 동일해야 결제 가능합니다.</li>
										<li>결제 증빙내역은 카카오페이 홈페이지에서 확인 가능합니다.(카카오페이 홈 > 설정 > 결제내역)</li>
										<li>카카오페이 고객센터 : 1644-7405</li>
									</ul></td>
							</tr>
						</table>
					</div>
				</div>
			</div>
			<div class="o_right">
				<h2>최종 결제정보</h2>
				<ul class="total_pmBox">
					<li><span class="total_t">총 상품금액</span><span class="total_cnt"><span
							class="total_tn"><fmt:formatNumber
									value="${sessionScope.total}" pattern="###,###,###" /> 원</span></span><input
						type="hidden" id="total" value="${sessionScope.total}"></li>
					<div class="both"></div>
					<li><span class="total_t">포인트 사용</span> <span
						class="total_cnt"><span class="total_tn"><span
								id="desc_point">0</span>원</span></span></li>
					<div class="both"></div>
					<li class="total"><span class="total_t">최종 결제금액</span><span
						class="total_cnt"> <span class="total_tn" id="total_tn"><fmt:formatNumber
									value="${sessionScope.total}" pattern="###,###,###" /></span>원</span>
									<input type="hidden" id="total_p" value="${sessionScope.total}"></li>
					<div class="both"></div>
					<li><input type="button" class="btn tpm" id="orderBtn"
						value="결제하기" onclick="preCheck()"></li>
				</ul>
			</div>
		</div>
	</section>




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