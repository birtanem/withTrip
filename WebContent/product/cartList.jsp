<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>

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


<script src="js/jquery-3.5.0.js"></script>

	<script type="text/javascript">
   	// 메뉴 액티브
   $(document).ready(function() {
	  $(".nav5").addClass("active"); 
   });
   </script>
   
<script type="text/javascript">


// 모든 체크박스 선택
function checkAll(obj) {
	 
	 var chkObj = document.getElementsByName("rowCheck");
	 var rowCnt = chkObj.length - 1;
	 var check = obj.checked;
	 
	      if (check) {﻿
	          for (var i=0; i<=rowCnt; i++){
	           if(chkObj[i].type == "checkbox")
	               chkObj[i].checked = true;
	          }
	      } else {
	          for (var i=0; i<=rowCnt; i++) {
	           if(chkObj[i].type == "checkbox"){
	               chkObj[i].checked = false;
	           }
	      }
	  }
} 
// 장바구니 삭제
function deleteCart() {

		var message = "";
		var messageChk = document.getElementsByName("rowCheck");
		var check = false;
		var indexMessage = false;
		
		for(i=0;i<messageChk.length;i++) {
			if(messageChk[i].checked) {
				if(indexMessage) {
					message = message + "-";
				}
				message = message + messageChk[i].value;
				indexMessage = true;
			}
		}	
		if(!indexMessage) {
			alert("삭제할 상품을 선택하세요.");
			return false;
		}
		
		c= confirm("선택한 상품을 삭제하시겠습니까?")
		
		if(c) {
			
			$.ajax("ProductCartRemove.ca", {
				type:"POST",
				data: {"message": message},
				success: function() {
					alert("선택한 상품이 삭제되었습니다");	
					location.reload();
				}
			});
		}else {
			return false;
		}
		

 }

$(document).ready(function(){
	
	// 페이지 열때 모든 체크박스 체크
	$("input[type=checkbox]").prop("checked", true);
	// 선택체크박스 선택 시 올체크박스 해제
	 $("#rowCheck").change(function(){
	      if(!$("#rowCheck").is(":checked")){
	      		$("#allCheck").prop("checked", false);
	      }
	 });

	
	 $("#orderBtn").click(function() {
		 	
		 	var messageChk = document.getElementsByName("rowCheck");
		 	var amountChk = document.getElementsByName("amount");
		 	var priceChk = document.getElementsByName("price");
		 	var total = 0;
		 	var indexMessage = false;
		    var testList = new Array() ;
	
		        // 객체 생성
		        
		     // 리스트에 생성된 객체 삽입
		   for(var i=0;i<messageChk.length;i++) {
			   	
		        if(messageChk[i].checked) {
		        	var data = new Object() ;
		        	total += Number(commasWithNumber(priceChk[i].value));
		        	data.num =  messageChk[i].value;
		        	
		        	if(amountChk[i].value == "품절") {
		        		alert("품절된 상품은 주문할 수 없습니다!")
		        		return false;
		        	}
		        	
				    data.amount = amountChk[i].value;
				    data.price = commasWithNumber(priceChk[i].value);
				    testList.push(data) ;
				    indexMessage = true;
		        }
		   }

			if(!indexMessage) {
				alert("주문할 상품을 선택하세요.");
				return
			}
//		     // String 형태로 변환

		    var jsonData = JSON.stringify(testList) ;
		    
		    console.log(jsonData);
		     
			$.ajax("orderFront.or", {
				type:"POST",
				data: {"jsonData": jsonData,
					"total":total,
					"cart": 0},
				success: function() {
					location.href="orderForm.or"
				}
			});
	});

});

// 수량 변경
 function minuscount(num, a) {
	 
		var amount = commasWithNumber(document.getElementById("amount"+num).value);
		var price = commasWithNumber(document.getElementById("price"+num).value);
		var count = Number(amount) + a;
		var total = 0;
		
		if(count<1) {
			return false;
		}
		
		if(count > $("#hamount"+num).val()) {
			$("#amountcheck"+num).html("판매수량 초과")
			$("#amountcheck"+num).css("color","red")
			return false;
		}
		
		document.getElementById("amount"+num).value = count;
		
		document.getElementById("td"+num).value = numberWithCommas(count * price);


		total = Number(commasWithNumber(document.getElementById("total").value)) + a*price	;
		
		document.getElementById("total").value = numberWithCommas(total);
		
		}
	
 // , 넣기
 function numberWithCommas(x) {
	 
	    return x.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
	}
//, 빼기
 function commasWithNumber(x) {
	    return x.toString().replace(/\,/g,"");
	}

</script>

<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
.ot_list td, .ot_list th {
	border-right: 1px solid #ddd;
}

.ot_list tr, .ot_list th {
	text-align: center;
}

.ot_list {
	border-left: 1px solid #ddd;
}
</style>
</head>

<body>
	<jsp:include page="/inc/top.jsp" />

	<div class="page-title"
		style="background-image: url(images/page-title.png);">
		<h1>장바구니</h1>
	</div>
	<section id="portfolio">
		<div class="container" style="margin-top: -50px;">

			<div class="o_list" style="clear: both;">
				<table class="ot_list">
					<tr>
						<th style="padding-right: 20px;"><input type="checkbox"
							id="allCheck" onclick="checkAll(this)" /></th>
						<th>카테고리</th>
						<th colspan="2">상품정보</th>
						<th style="padding-right: 20px;">가격</th>
						<th style="padding-right: 20px;">수량</th>
						<th style="padding-right: 20px;">합계</th>
					</tr>
					<c:forEach var="p" items="${productList }" varStatus="status">
						<tr>
							<td>
							<input type="checkbox" name="rowCheck" id="rowCheck" value="${cartList[status.index].c_p_num }" />
							</td>
							<td>${p.p_theme }</td>
							<td style="border-right: none;"><img src="product/productUpload/${p.p_image }" width="200" height="100"></td>
							<td style="text-align: left;">${p.p_name }</td>
							<td class="price"><fmt:formatNumber value="${p.p_price }" pattern="###,###,###" />원</td>
							<td> 
							<input type="hidden" value="${p.p_amount }" id="hamount${status.count }"> 
							<c:choose>
								<c:when test="${cartList[status.index].c_p_amount <= p.p_amount }">
									<input type="button" value="-" onclick="minuscount(${status.count },-1)">
									<input style="border: 1px solid #ddd; width: 50px; text-align: left;" type="text" id="amount${status.count }" name="amount" value="${cartList[status.index].c_p_amount }" title="구매수량" onfocus="this.blur()">
									<input type="button" value="+" onclick="minuscount(${status.count },1)">
								</c:when>
								<c:when test="${p.p_amount le 0}">
									<input style="color: red; width: 50px; outline: 0;" type="text" id="amount${status.count }" name="amount" value="품절" title="구매수량" onfocus="this.blur()">
								</c:when>
								<c:otherwise>
									<input type="button" value="-" onclick="minuscount(${status.count },-1)">
									<input style="border: 1px solid #ddd; width: 50px; text-align: left;" type="text" id="amount${status.count }" name="amount" value="${p.p_amount }" title="구매수량" onfocus="this.blur()">
									<input type="button" value="+" onclick="minuscount(${status.count },1)">
								</c:otherwise>
							</c:choose>  
							<input type="hidden" id="price${status.count }" value="${p.p_price}">
							<p id="amountcheck${status.count }"></p></td>
							<td class="price">
							<c:choose>
								<c:when test="${p.p_amount le 0}">
									<input style="width: 130px; color: #F77; text-align: right; outline: 0;" type="text" id="td${status.count }" name="price" value="0" />원
								</c:when>
								<c:when test="${cartList[status.index].c_p_amount gt p.p_amount}">
									<input style="width: 130px; color: #F77; text-align: right; outline: 0;" type="text" id="td${status.count }" name="price" value="<fmt:formatNumber value="${p.p_amount * p.p_price}" pattern="###,###,###" />" />원
								</c:when>
								<c:otherwise>
									<input style="width: 130px; color: #F77; text-align: right; outline: 0;" type="text" id="td${status.count }" name="price" value="<fmt:formatNumber value="${cartList[status.index].c_p_amount * p.p_price}" pattern="###,###,###" />">원	
								</c:otherwise>
							</c:choose>
							</td>
						</tr>
					</c:forEach>

				</table>
		            
				<div>
					<input type="button" class="fun-btn btn btn-primary btn-lg"
						style="text-align: center" value="선택상품 삭제" id="deleteButton"
						onclick="return deleteCart()">
				</div>
				
				

			            
			            
			</div>



			<div class="o_list">
				<table class="ot_list">


					<tr style="height: 100px; font-size: 18pt;">
						<th colspan="3" style="text-align: right; padding-right: 20px;">
						총 결제예상금액 <c:set var="totalmoney" value="0" /> 
						<c:forEach var="p" items="${productList }" varStatus="status">
						<c:choose>
							<c:when test="${cartList[status.index].c_p_amount gt p.p_amount}">
								<c:set var="money" value="${p.p_amount * p.p_price}" />	
							</c:when>
							<c:otherwise>
								<c:set var="money" value="${cartList[status.index].c_p_amount * p.p_price}" />
							</c:otherwise>
						</c:choose>
						
						<c:set var="totalmoney" value="${totalmoney + money }" />
						</c:forEach> 
						<span id="span" style="color: red; font-size: 16pt;"> 
						<input type="text" value="<fmt:formatNumber value="${totalmoney}" pattern="###,###,###" />" id="total" onfocus="this.blur()" style="font-size: 16pt; width: 130px; color: red; text-align: right; background-color: #f4f4f4;">원
						</span>
						</th>
					</tr>
					
				</table>
			</div>

			<div class="center" style="margin-top: 50px;">
				<nav style="text-align: center">
					<input type="button" class="fun-btn btn btn-primary btn-lg"
						style="text-align: center" value="쇼핑 계속하기"
						onclick="location.href='productList.pr'"> <input
						type="button" id="orderBtn" class="fun-btn btn btn-primary btn-lg"
						style="text-align: center" value="주문하기">

				</nav>

			</div>

	

		

		</div>


	</section>

	<jsp:include page="/inc/bottom.jsp" />

</body>
</html>