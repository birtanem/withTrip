<%@page import="product.vo.ProductBean"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품관리</title>

<!-- core CSS -->
<link href="css/product.css" rel="stylesheet">
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
<script src="js/jquery-3.5.0.js"></script>
<style type="text/css">
.p_file {
	display: none;
}
</style>
<script src="js/jquery-3.5.0.js"></script>

    <script type="text/javascript">
   		// 메뉴 액티브
	   $(document).ready(function() {
		  $(".nav1").addClass("active"); 
	   });
   </script>
   
<script type="text/javascript">
function changeRegionCode(p_num, regionCode,idx){
	location.href="productUpdatePro.pr?p_num="+p_num+"&region_region_code="+regionCode;
	alert("내용 변경됨");
}
function changeTheme(p_num, theme){
	location.href="productUpdatePro.pr?p_num="+p_num+"&p_theme="+theme;
	alert("내용 변경됨");
}
function popImg(p_num){
	window.open("productimage.pr?p_num="+p_num,"","width=400,height=150,left=200,top=300,scrollbars=yes,resizable=yes");
}
function popContent(idx){
	document.getElementById('myModal'+idx).style.display = "block";
}
function closepop(idx){
	document.getElementById('myModal'+idx).style.display = "none";
}
function updateContent(p_num){
	window.open("productContentUpdate.pr?p_num="+p_num,"","width=500,height=500,left=200,top=300,scrollbars=yes,resizable=yes");
}
function reload(){
	location.reload();
}
function del(p_num){
	alert("정말 삭제하시겠습니까?");
	location.href="productDelete.pr?p_num="+p_num;
}
function pop(){
	window.open("productRegistForm.pr","","width=1000,height=800,left=500,top=150,scrollbars=yes,resizable=yes");
}

</script>
<script type="text/javascript">
$(document).ready(function(){
	$.ajax("getRegion.co",{
		dataType:"json",
		success:function(rdata){
			$.each(rdata,function(index,code){
				var option=$('<option value="'+code.rCode+'">'+code.rName+'</option>');
				$('.rCode').append(option);
			});
		}
	});
});
</script>
<style type="text/css">
.pt_list{
}
</style>
</head>

<body>

	<jsp:include page="/inc/top.jsp" />
<section id="portfolio">
	<div class="container">
	<div class="pt_list">
	<div>
	<input type="button" class="btn apBtn" value="관리자:상품등록"onclick="pop()" />
	</div>
		<c:choose>
			<c:when test="${pageInfo.listCount>0 && productList !=null}">
				<table>
					<tr>
						<th>번호</th>
						<th>지역코드</th>
						<th>관심사</th>
						<th>상품명</th>
						<th>이미지</th>
						<th>내용</th>
						<th>가격</th>
						<th>수량</th>
						<th colspan="2">관리</th>
					</tr>
					<c:forEach var="list" items="${productList }" varStatus="vs">
						<tr>
							<td>${list.p_num}</td>

							<td><select name="regionCode" class="rCode" id="regionCode${vs.count }"
								onchange="changeRegionCode('${list.p_num }',this.value,${vs.count });">
									<option selected value="${list.region_region_code }">${list.region_name }</option>
							</select></td>
							<td><select name="theme"
								onchange="changeTheme('${list.p_num }',this.value);">
									<option selected value=${list.p_theme }>${list.p_theme }</option>
									<option value="관광">관광</option>
									<option value="맛집">맛집</option>
									<option value="역사">역사</option>
									<option value="체험">체험</option>
							</select></td>
							<form action="productUpdatePro.pr" method="post" name="fr">
								<td><input type="hidden" name="p_num"
									value="${list.p_num }"> <input type="text" name="name" id="p_name"
									value="${list.p_name }"></td>
								<td><input type="text" name="img" value="${list.p_image }"
									id="p_img${vs.count }" readonly="readonly"
									onclick="popImg(${list.p_num})"></td>
								<td><input type="text" name="p_content" id="p_content"
									value="${list.p_content }" onclick="popContent(${vs.count})"></td>
								<td><input type="text" id="inPrice" name="price"
									value="${list.p_price }"></td>
								<td><input type="text" name="amount"
									value="${list.p_amount }"></td>
								<td><input type="submit" value="수정" id="myBtn" class="btn"></td>
							</form>
							<td><input type="button" value="삭제" class="btn" onclick="del(${list.p_num})"></td>
						</tr>
						
						<!-- Modal content -->
						<div id="myModal${vs.count }" class="modal">
							<div class="modal-content">
								<div class="modal-header">
									<span class="close" onclick="closepop(${vs.count})">&times;</span>
									<h2>${list.p_name}</h2>
								</div>
									<div class="modal-body">
										${list.p_content}
									</div>
								<div class="modal-footer">
									<h3>
										<input type="button" value="수정" class="btn"
											onclick="updateContent(${list.p_num})"> <input
											type="button" value="취소" class="btn"
											onclick="closepop(${vs.count})">
									</h3>
								</div>
							</div>
						</div>
					</c:forEach>
				</table>
				
				                       <!--/.row   페이징 처리-->
            <div class="row">
                <div class="col-md-12 text-center">
                    <ul class="pagination pagination-lg">
                    	<c:choose>
                    		<c:when test="${pageInfo.page <= 1 }">
                    			<li><a href="#"><i class="fa fa-long-arrow-left"></i></a></li>
                    		</c:when>
                    		<c:otherwise>
                    			<li><a href="adminProduct.ad?page=${pageInfo.page - 1 }"><i class="fa fa-long-arrow-left"></i></a></li>
                    		</c:otherwise>
                    	</c:choose>
                    	<c:forEach var="a" begin="${pageInfo.startPage }" end="${pageInfo.endPage }" step="1">
                    		<c:choose>
                    			<c:when test="${a == pageInfo.page }">
                    				<li class="active"><a>${a }</a></li>
                    			</c:when>
                    			<c:otherwise>
									<li><a href="adminProduct.ad?page=${a }">${a }</a></li>
                    			</c:otherwise>
                    		</c:choose>
                    	</c:forEach>
                    		<c:choose>
                    			<c:when test="${pageInfo.page >= pageInfo.maxPage }">
                    				<li><a href="#"><i class="fa fa-long-arrow-right"></i></a></li>
                    			</c:when>
                    			<c:otherwise>
                    				<li><a href="adminProduct.ad?page=${pageInfo.page + 1 }"><i class="fa fa-long-arrow-right"></i></a></li>
                    			</c:otherwise>
                    		</c:choose>
                    </ul>
                    <!--/.pagination-->
                </div>
            </div>
            <!--/.row   페이징 처리-->
            
			</c:when>
			<c:otherwise>
				<section id="emptyArea">등록된 상품이 없습니다.</section>
			</c:otherwise>
		</c:choose>
</div>




	</div>



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
	</section>
	<!--/#footer-->

	<script src="js/bootstrap.min.js"></script>
	<script src="js/jquery.prettyPhoto.js"></script>
	<script src="js/owl.carousel.min.js"></script>
	<script src="js/jquery.isotope.min.js"></script>
	<script src="js/main.js"></script>
</body>
</html>