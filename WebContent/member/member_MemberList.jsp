<%@page import="place.vo.PlacePageInfo"%>
<%@page import="place.vo.PlaceBean"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="pageInfo" value="${pageinfo }"  /> 
<%
String type = request.getParameter("type");

%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>회원 목록</title>

    <!-- core CSS -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/font-awesome.min.css" rel="stylesheet">
    <link href="css/animate.min.css" rel="stylesheet">
    <link href="css/prettyPhoto.css" rel="stylesheet">
    <link href="css/owl.carousel.min.css" rel="stylesheet">
    <link href="css/icomoon.css" rel="stylesheet">
    <link href="css/main.css" rel="stylesheet">
    <link href="css/responsive.css" rel="stylesheet">
    <link href="css/review.css" rel="stylesheet">

    <link rel="shortcut icon" href="../images/ico/favicon.ico">
    <link rel="apple-touch-icon-precomposed" sizes="144x144" href="images/ico/apple-touch-icon-144-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="114x114" href="images/ico/apple-touch-icon-114-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="72x72" href="images/ico/apple-touch-icon-72-precomposed.png">
    <link rel="apple-touch-icon-precomposed" href="images/ico/apple-touch-icon-57-precomposed.png">
    
    <script src="js/jquery-3.5.0.js"></script>
    
<style type="text/css">


#table1 {
	color: black;
}

#spanCount1{
	font-size: 23px;
    color: black;
    font-weight: bold;
}

#spanCount2{
	font-size: 23px;
    font-weight: bold;
    color: black;
}

#spanCount3{
	font-size: 23px;
    font-weight: bold;
    color: white;
}

/* 회원 관리용 버튼  */
#btnMem {
    margin-bottom: 20px;
    margin-top: 20px;
    margin-right: 5px;
    color: white;
	background-color: #6868ff;
}

#btnMem1 {
    margin-bottom: 20px;
    margin-top: 20px;
    margin-right: 5px;
    color: white;
	background-color: #6868ff;
}

#btnMem2 {
    margin-bottom: 20px;
    margin-top: 20px;
    margin-right: 5px;
    color: white;
	background-color: #6868ff;
}

#adminPass {
	width: 206px;
}

/* 회원 정렬 및 검색 버튼 */
#btnSearch{

}

#btnSearch1{
	color: white;
	background-color: #6868ff;
}

#btnIdArray{
    margin-right: 5px;
    margin-top: 10px;
    background-color: cyan;
}

#btnAgeArray{
    margin-right: 5px;
    margin-top: 10px;
    background-color: papayawhip;
}

#btnPointArray{
    margin-right: 5px;
    margin-top: 10px;
    background-color: chartreuse;
}

/* 돌아가기 버튼 */
#btnBack{
	float: right;
}

#span1{
	display: inline-block;
    width: 100px;
}

#divBtn1 {
    margin-top: 20px;
	    
}

#divBtn2 {
    margin-top: 0px;

}

#divBtn3 {
    height: 35px;
}

#divN {
	background-color: red;
    width: 380px;
    height: 210px;
    float: right;
}

#divInfo {
	width: 280px;
    background-color: #3131ff;
    color: white;
    font-weight: bold;
    padding: 3px;
    margin-top: 10px;
}

#ddd {
	background-color: #3131ff;
	
}

</style>
<script type="text/javascript">

function ageA() {
	var type = "age ASC";
	location.href = "./MemberList.me?type="+type;
}

function ageD() {
	var type = "age DESC";
	location.href = "./MemberList.me?type="+type;
}

function idA() {
	var type = "id ASC";
	location.href = "./MemberList.me?type="+type;
}

function idD() {
	var type = "id DESC";
	location.href = "./MemberList.me?type="+type;
}

function pointA() {
	var type = "point ASC";
	location.href = "./MemberList.me?type="+type;
}

function pointD() {
	var type = "point DESC";
	location.href = "./MemberList.me?type="+type;
}

function btnSearch() {
	if(document.getElementById('searchId').value == ""){
		alert("아이디를 입력하세요");
		return false;
	} else {
		var type = document.getElementById('searchId').value;
		location.href = "./MemberList.me?type="+type+"&&search=true";
	}
}

//포인트 변경 버튼
function btnPointChange() {
	$(".pointChange").html("");
	$(".pointChange").html(
			
			'<input type="text" name="cPoint" id="cPoint" placeholder="변경할 포인트">'
			+ '<input type="button" id="btnMem1" value="변경 완료" onclick="btnChangeConfirm()">'	
	);
}

function btnDeleteId() {
	$(".pointChange").html("");
	$(".pointChange").html(
			
			'<input type="password" name="adminPass" id="adminPass" placeholder="관리자 패스워드를 입력하세요">'
			+ '<input type="button" id="btnMem2" value="삭제 확인" onclick="btnDeleteIdConfirm()">'	
	);
}

function btnChangeConfirm() {
	
	if($('#cPoint').val() == ""){
		alert("변경 포인트를 입력하세요");
		return false;
	} else if(!/^[0-9]/.test($('#cPoint').val())){
		alert("변경 포인트는 숫자값을 입력하세요");
		return false;
	}
	
	if(!confirm("포인트를 변경하시겠습니까?")){
		return false;
		
	} else {
		var params = "selectId=" + $("#selectId").val() + "&&" + "cPoint=" + $("#cPoint").val();
		
			$.ajax({
			url : 'MemberGetMemAction.me',
			type : 'post',
			data : params,
			dataType : 'json',
			success : function(rdata) {
				$(".pointChange").html("");
				$('.memInfo').html("");
		        $.each(rdata,function(index,item){
		        	
					$('.memInfo').html(
							
							'<div id="divInfo">'
							+ '<input type="hidden" id="selectId" value="'+item.get_id+'">'
	                		+ '<span id="span1">아이디  </span><span>'+item.get_id+'</span><br>'
	                		+ '<span id="span1">이름  </span><span>'+item.get_name+'</span><br>'
	                		+ '<span id="span1">나이  </span><span>'+item.get_age+'</span><br>'
	                		+ '<span id="span1">성별  </span><span>'+item.get_gender+'</span><br>'
	                		+ '<span id="span1">이메일  </span><span>'+item.get_email+'</span><br>'
	                		+ '<span id="span1">휴대폰  </span><span>'+item.get_phone+'</span><br>'
	                		+ '<span id="span1">가입날짜  </span><span>'+item.get_date+'</span><br>'
	                		+ '<span id="span1">포인트  </span><span>'+item.get_point+'</span><br>'
	                		+ '<span id="span1">타입  </span><span>'+item.get_type+'</span><br>'
	                		+ '</div>'
	                		+ '<div id="divBtn3">'
	                		+ '<input type="button" id="btnMem" value="포인트 변경" onclick="btnPointChange()">'
	                		+ '<input type="button" id="btnMem" value="아이디 탈퇴" onclick="btnDeleteId()">'
	                		+ '</div>'
	               	);
		        });
		        alert("포인트 변경 완료!");
			}, error : function() {
					console.log("실패");
			}
		});	
	}
}
///--------------------------------------------------------------
//회원 탈퇴

function btnDeleteIdConfirm() {
	
	if($('#adminPass').val() == ""){
		alert("어드민 패스워드를 입력하세요");
		return false;
	}
	
	if(!confirm("회원을 탈퇴시키겠습니까?")){
		return false;
		
	} else {
		var params = "selectId=" + $("#selectId").val() + "&&" + "adminPass=" + $("#adminPass").val();
		
			$.ajax({
			url : 'MemberGetMemAction.me',
			type : 'post',
			data : params,
			dataType : 'json',
			success : function(rdata) { //#############################################수정중##################################################
				$(".pointChange").html("");
				$('.memInfo').html("");
		        $.each(rdata,function(index,item){
		        	
					$('.memInfo').html(
							
							'<div id="divInfo">'
							+ '<input type="hidden" id="selectId" value="'+item.get_id+'">'
	                		+ '<span id="span1">아이디  </span><span>'+item.get_id+'</span><br>'
	                		+ '<span id="span1">이름  </span><span>'+item.get_name+'</span><br>'
	                		+ '<span id="span1">나이  </span><span>'+item.get_age+'</span><br>'
	                		+ '<span id="span1">성별  </span><span>'+item.get_gender+'</span><br>'
	                		+ '<span id="span1">이메일  </span><span>'+item.get_email+'</span><br>'
	                		+ '<span id="span1">휴대폰  </span><span>'+item.get_phone+'</span><br>'
	                		+ '<span id="span1">가입날짜  </span><span>'+item.get_date+'</span><br>'
	                		+ '<span id="span1">포인트  </span><span>'+item.get_point+'</span><br>'
	                		+ '<span id="span1">타입  </span><span>'+item.get_type+'</span><br>'
	                		+ '</div>'
	                		+ '<div id="divBtn3">'
	                		+ '<input type="button" id="btnMem" value="포인트 변경" onclick="btnPointChange()">'
	                		+ '<input type="button" id="btnMem" value="아이디 탈퇴" onclick="btnDeleteId()">'
	                		+ '</div>'
	               	);
		        });
		        alert("회원 탈퇴 완료!");
			}, error : function() {
					console.log("실패");
					alert("admin 패스워드가 틀립니다!");
			}
		});	
	}
}



//----------------------------------------------
//회원 선택 기능 
$(document).ready(function(){
	$("#btnSearch1").click(function() {
		var formData = $("#searchId1").serialize() ;
// 		$("#id_check").text("5~12자 영문의 아이디 입력하세요");
// 		$("#id_check").css("color","red");
// 		$("#idCheck").attr("value", "denied");
			$.ajax({
			url : 'MemberGetMemAction.me',
			type : 'post',
			data : formData,
			dataType : 'json',
			success : function(rdata) {
				$('.memInfo').html("");
	
		        $.each(rdata,function(index,item){
					$('.memInfo').html(
							
							'<div id="divInfo">'
							+ '<input type="hidden" id="selectId" value="'+item.get_id+'">'
	                		+ '<span id="span1">아이디  </span><span>'+item.get_id+'</span><br>'
	                		+ '<span id="span1">이름  </span><span>'+item.get_name+'</span><br>'
	                		+ '<span id="span1">나이  </span><span>'+item.get_age+'</span><br>'
	                		+ '<span id="span1">성별  </span><span>'+item.get_gender+'</span><br>'
	                		+ '<span id="span1">이메일  </span><span>'+item.get_email+'</span><br>'
	                		+ '<span id="span1">휴대폰  </span><span>'+item.get_phone+'</span><br>'
	                		+ '<span id="span1">가입날짜  </span><span>'+item.get_date+'</span><br>'
	                		+ '<span id="span1">포인트  </span><span>'+item.get_point+'</span><br>'
	                		+ '<span id="span1">타입  </span><span>'+item.get_type+'</span><br>'
	                		+ '</div>'
	                		+ '<div id="divBtn3">'
	                		+ '<input type="button" id="btnMem" value="포인트 변경" onclick="btnPointChange()">'
	                		+ '<input type="button" id="btnMem" value="아이디 탈퇴" onclick="btnDeleteId()">'
	                		+ '</div>'
	               	);
		        });
			}, error : function() {
					console.log("실패");
			}
		});	
	});
});
//----------------------------------------------


	


</script>
</head>
<body>    
<jsp:include page="/inc/top.jsp" />

    <div class="page-title" style="background-image: url(images/page-title.png)">
        <h1>회원 목록 및 관리</h1>
        
    </div>
    
    <section id="blog">
        <div class="blog container">
            <div class="row">
<!--                 	<div id=divN> -->
<!--                     </div> -->
                <div class="col-sm-10">
                	<div class="blog-item">
                        <div class="blog-content">
	                    	<div>
	                    		<input type="button" id="btnBack" value="돌아가기" onclick="location.href='./adminPage.ad'">
	                    		<span id="spanCount1">총 회원 수 </span>
	                    		<span id="spanCount2"> ${pageInfo.listCount }</span>
	                    		
	                    		<div id="divBtn1">
										<input type="text" name="searchId" id="searchId" placeholder="아이디 검색">
										<input type="button" id="btnSearch" value="아이디 검색" onclick="btnSearch()">
	                    		</div>
	                    		<div id="divBtn2">
		                    		<input type="button" id="btnIdArray" value="아이디 정렬" onclick="idA()">
		                    		<input type="button" id="btnIdArray" value="아이디 역정렬" onclick="idD()">
		                    		<input type="button" id="btnAgeArray" value="나이 정렬" onclick="ageD()">
		                    		<input type="button" id="btnAgeArray" value="나이 역정렬" onclick="ageA()">
		                    		<input type="button" id="btnPointArray" value="포인트 정렬" onclick="pointD()">
		                    		<input type="button" id="btnPointArray" value="포인트 역정렬" onclick="pointA()">
	                    		</div>
	                    	</div>        		   
                        </div>
                    </div>
                    
                    <div class="blog-item">
                   	 <div id="ddd">
                        <div class="blog-content">
                       		<span id="spanCount3">회원 관리 기능 </span>
                       		<span id="id_check"></span>
	                    	<div id="divBtn1">
								<input type="text" name="id" id="searchId1" placeholder="선택 아이디 입력">
								<input type="button" id="btnSearch1" value="아이디 선택">
									<!--회원정보 불러오기 -->
	                    			<div class="memInfo">
	                    			</div>
	                    			<!--회원정보 불러오기 끝-->
	                    			
	                    			<!--포인트 변경 버튼 클리깃 나타나는 창-->
	                    			<div class="pointChange">
	                    			</div>
	                    			<!--포인트 변경 버튼 클리깃 나타나는 창-->
	                    	</div>        		   
                        </div>
                     </div>
                    </div>
                    

 
                <c:forEach var="article" items="${articleList }" >
					<div class="blog-item">
                        <div class="blog-content">
                        		<span id="span1">아이디  </span><span>${article.id }</span><br>
                        		<span id="span1">이름  </span><span>${article.name }</span><br>
                        		<span id="span1">나이  </span><span>${article.age }</span><br>
                        		<span id="span1">성별  </span><span>${article.gender }</span><br>
                        		<span id="span1">이메일  </span><span>${article.email }</span><br>
                        		<span id="span1">휴대폰  </span><span>${article.phone }</span><br>
                        		<span id="span1">가입날짜  </span><span>${article.date }</span><br>
                        		<span id="span1">포인트  </span><span>${article.point }</span><br>
                        		<span id="span1">타입  </span><span>${article.type }</span><br>
                        </div>
                    </div>
                		</c:forEach>
				
                    
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
                    			<li><a href="MemberList.me?page=${pageInfo.page - 1 }&&type=<%=type%>"><i class="fa fa-long-arrow-left"></i></a></li>
                    		</c:otherwise>
                    	</c:choose>
                    
                    	<c:forEach var="a" begin="${pageInfo.startPage }" end="${pageInfo.endPage }" step="1">
                    		
                    		<c:choose>
                    		
                    			<c:when test="${a == pageInfo.page }">
                    				<li class="active"><a>${a }</a></li>
                    			</c:when>
                    			<c:otherwise>
									<li><a href="MemberList.me?page=${a }&&type=<%=type%>">${a }</a></li>
                    			</c:otherwise>
                    		</c:choose>
                    	
                    	</c:forEach>
                    		
                    		<c:choose>
                    		
                    			<c:when test="${pageInfo.page >= pageInfo.maxPage }">
                    				<li><a href="#"><i class="fa fa-long-arrow-right"></i></a></li>
                    			</c:when>
                    			<c:otherwise>
                    				<li><a href="MemberList.me?page=${pageInfo.page + 1 }&&type=<%=type%>"><i class="fa fa-long-arrow-right"></i></a></li>
                    			</c:otherwise>
                    		</c:choose>
                    </ul>
                    <!--/.pagination-->
                </div>
            </div>
            <!--/.row   페이징 처리-->
            
        </div>
    </section>
    <!--/#blog-->

    <!--/#bottom-->
<jsp:include page="/inc/bottom.jsp"/>
    <!--/#footer-->
    <script src="../js/jquery.js"></script>
    <script src="../js/bootstrap.min.js"></script>
    <script src="../js/jquery.prettyPhoto.js"></script>
    <script src="../js/owl.carousel.min.js"></script>
    <script src="../js/jquery.isotope.min.js"></script>
    <script src="../js/main.js"></script>
</body>
</html>