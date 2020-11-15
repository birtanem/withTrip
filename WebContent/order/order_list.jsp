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

<style type="text/css">
.o_info {background: #f4f4f4; height: 130px;}
.loginInput {
  
  
  padding: -5px 10px;
  border: 1px solid #ccc;
  margin-bottom: 5px;
 
}
form {float: left;}
</style>




<style type="text/css">
.ot_list td, .ot_list th {border-right: 1px solid #ddd;}
.ot_list tr, .ot_list th  {text-align: center;}
.ot_list {border-left: 1px solid #ddd;}




</style>
</head>
<body>
	<jsp:include page="/inc/top.jsp" />
	<div class="page-title"
		style="background-image: url(images/page-title.png);">
		<h1>결제내역</h1>
	</div>
	<section id="portfolio">
	
		<div class="container" style="margin-top: -50px;">
			<div class="o_info o_input" style="font-size: 12pt; padding-right: 0;">
				<h2>구매기간 <input type="button" value="전체" onclick="location.href='orderList.or'"><input type="button" value="1개월" onclick='listSearch(1)'><input type="button" value="3개월" onclick="listSearch(3)"><input type="button" value="6개월" onclick="listSearch(6)"></h2><br>

				<form name="mainForm" id="mainForm" method="post">
				    <select name="dateYear" id="selYear1" class="loginInput" onChange="setDay()"></select>년&nbsp;
				    <select name="dateMonth" id="selMonth1" class="loginInput" onChange="setDay()"></select>월&nbsp;
				    <select name="dateDay" id="selDay1" class="loginInput"></select>일&nbsp;&nbsp;~&nbsp;&nbsp;

				</form>
	
				<form name="mainForm2" id="mainForm2" method="post">
				    <select name="dateYear2" id="selYear2" class="loginInput" onChange="setDay2()"></select>년&nbsp;
				    <select name="dateMonth2" id="selMonth2" class="loginInput" onChange="setDay2()"></select>월&nbsp;
				    <select name="dateDay2" id="selDay2"class="loginInput"></select>일&nbsp;
					
				</form>
				<div style=" float: right; height:125px; width:130px;margin-top: -85px; margin-left: 180px; padding: 0;" >
				<input type="button" value="조회"  id="btn" class="btn btn-primary btn-lg" onclick="listSearch(0)" style="width: 130px; height: 125px; margin: 0; padding: 0;">
				</div>		
			</div>
			
			<div class="o_list" style="clear: both;">
				<h2>결제내역</h2>
				<table class="ot_list">
					<tr>
						<th>주문번호</th>
						<th colspan="2" style="">상품정보</th>
						<th style="padding-right: 20px;">결제금액</th>
						<th style="padding-right: 20px;">결제일</th>
					
					</tr>				
					<c:forEach var="list" items="${list }">
						<tr>
							<td><a href="orderDetail.or?num=${list.orderNum }">${list.orderNum }<br>상세보기</a></td>
							<td style="border-right: none;"><img src="product/productUpload/${list.image }" width="200" height="100"></td>
							<td style="text-align: left;">${list.name }
								<c:if test="${list.amount > 1}" >
								외 ${list.amount -1}개
								</c:if>
							</td>
							<td class="price"><fmt:formatNumber value="${list.price }" pattern="###,###,###"/>원</td>
							<td><fmt:formatDate value="${list.date }" pattern="yyyy-MM-dd"/></td>
				
						</tr>				
					</c:forEach>
				</table>
			</div>
			
			
			            <!--/.row   페이징 처리-->
            <div class="row">
                <div class="col-md-12 text-center">
                    <ul class="pagination pagination-lg">
                    			
                    	<c:choose>
                    	
                    		<c:when test="${pageInfo.page <= 1 }">
                    			<li><a><i class="fa fa-long-arrow-left"></i></a></li>
                    		</c:when>
                    		<c:otherwise>
                    			<li><a href='orderList.or?page=${pageInfo.page - 1 }' onclick="return fun1(e)"><i class="fa fa-long-arrow-left"></i></a></li>
                    		</c:otherwise>
                    	</c:choose>
                                     	
                    	<c:forEach var="a" begin="${pageInfo.startPage }" end="${pageInfo.endPage }" step="1">                   		
                    		<c:choose>      							
                   				<c:when test="${a == pageInfo.page }">
		
                    				<li class="active"><a>${a }</a></li>
                				
                    			</c:when>
               				<c:otherwise>
                    			
									<li><a href='orderList.or?page=${a }' onclick="return fun1(e)">${a }</a></li>
									
                    			</c:otherwise>
  							
                    		</c:choose>
                    	
                    	</c:forEach>
                    
                    		<c:choose>
                    		
                    			<c:when test="${pageInfo.page >= pageInfo.maxPage }">
                    				<li><a><i class="fa fa-long-arrow-right"></i></a></li>
                    			</c:when>
                    			<c:otherwise>
                    				<li><a href='orderList.or?page=${pageInfo.page + 1 }' onclick="return fun1()"><i class="fa fa-long-arrow-right"></i></a></li>
                    			</c:otherwise>
                    		</c:choose>
                    </ul>
                    <!--/.pagination-->
                </div>
            </div>
            <!--/.row   페이징 처리-->
            
             <!--   조회 처리용 페이징...-->
            <div class="row">
                <div class="col-md-12 text-center">
                    <ul class="pagination2 pagination-lg">
                    
                    
                    </ul>                  
                </div>
            </div>
            <!--   조회 처리용 페이징...-->
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
	
<script type="text/javascript">

	
	function listSearch(num, page) {
		
		
		var page2 = page;

		if(page2 == undefined) {
			page2 = 1;
		}

		if(!selCheck()) {
			alert("올바른 기간을 선택해주세요!")
			return false;
		}
		
		// 날짜 조회용 변수들 선언하고
		var betweenDay = num;
		var betweenDay2 =0;
		var nowDay2 = new Date(); 		
		var btTime = 0;
		var btTime2 = 0;
		var selectedDay = 0;
		var selectedDay2 = 0;
		
		// 날짜를 셀렉트박스로 선택했을때(0) , 1달, 3달, 6달 
		if(betweenDay == 0) {	
			$(".pagination").css("display","inline-block")
			// 선택 년, 월, 일
			selectedDay = new Date($("#selYear1 option:selected").val(), $("#selMonth1 option:selected").val()-1, $("#selDay1 option:selected").val());
			selectedDay2 = new Date($("#selYear2 option:selected").val(), $("#selMonth2 option:selected").val()-1, $("#selDay2 option:selected").val());
			// 밀리초
			btTime = nowDay2.getTime() - selectedDay.getTime();
			btTime2 = nowDay2.getTime() - selectedDay2.getTime();
		}else if(betweenDay == 1) {		
			selectedDay = new Date(nowDay2.getFullYear(), nowDay2.getMonth()-1, nowDay2.getDate());
			btTime = nowDay2.getTime() - selectedDay.getTime();
		}else if(betweenDay == 3) {		
			selectedDay = new Date(nowDay2.getFullYear(), nowDay2.getMonth()-3, nowDay2.getDate());
			btTime = nowDay2.getTime() - selectedDay.getTime();		
		}else if(betweenDay == 6) {		
			selectedDay = new Date(nowDay2.getFullYear(), nowDay2.getMonth()-6, nowDay2.getDate());
			btTime = nowDay2.getTime() - selectedDay.getTime();				
		}
	
		betweenDay = Math.floor(btTime/(1000*60*60*24));
		betweenDay2 = Math.floor(btTime2/(1000*60*60*24));
	
		$.ajax({
			url: "orderListSearch.or?page="+page2,
			data: {"betweenDay" : betweenDay,
					"betweenDay2" : betweenDay2},
			dataType: "json",
			success: function(rdata) {
				
				// 첫페이지 실행될때 목록들 페이징처리 하는데 검색한거 가져와서 
				// 페이징 하려니까 잘안됨.. 일단 none 처리하고
				$(".pagination").css("display","none")
				// 검색용으로 css 복사했는데 버튼이 달라짐;;;;
				$(".pagination2").html("")
				
				// append 하려니까 기존 테이블에 추가로 행이 생성되므로 타이틀 빼고 삭제 후 append 
				$("table tr:not(:first)").empty();
				
				// 검색결과 + 페이징 숫자 JSON 으로 가져와서 뿌려줌
				$.each(rdata, function(index, item) {
					
					// 검색했을때 페이징 처리
					if(index == rdata.length-1) {
						
						if(item.page <= 1) {
							
							 $(".pagination2").append("<li><a><i class='fa fa-long-arrow-left'></i></a></li>");
						}else {
 							
							 $(".pagination2").append("<li><a onclick='listSearch("+num+","+(item.page-1)+")'><i class='fa fa-long-arrow-left'></i></a></li>");
						}
						
						
						for(var i=item.startPage;i<=item.endPage;i++) {
								
							if(i==item.page) {
 								
								$(".pagination2").append("<li class='active'><a>"+i+"</a></li>");
							}else {
 								
								$(".pagination2").append("<li><a onclick='listSearch("+num+","+i+")'>"+i+"</a></li>");
								
							}

						}
			
						if(item.page >= item.maxPage) {
							$(".pagination2").append("<li><a><i class='fa fa-long-arrow-right'></i></a></li>");
						}else {
							
							$(".pagination2").append("<li><a onclick='listSearch("+num+","+(item.page+1)+")'><i class='fa fa-long-arrow-right'></i></a></li>");
						}
						
						return;
					}					
					
					// 총 수량이 2개 이상이면 ~ 외 갯수 표기
					if(item.amount > 1) {
						
						item.name = item.name + " 외 " + (item.amount -1) + "개"
					}					
					// 검색결과 뿌려줌
					$("table").append("<tr><td><a href='orderDetail.or?num="+item.orderNum+"'>"+item.orderNum+"<br>상세보기</a></td><td style='border-right: none;'><img src='product/productUpload/"+item.image+"' width='200' height='100'></td><td style='text-align: left;'>"+item.name+"</td><td class='price'>"+comma(item.price)+"원</td><td>"+item.date+"</td></tr>");
					
					// 조회 버튼 전체조회로 변경
// 					$("#btn").css({
// 						"background-color":"green"
// 					})
// 					$("#btn").attr("value", "전체조회")

				});
				
				
			}
		});
	}
	// 날짜 올바르게 선택했는지 체크
	function selCheck() {
		
		if($("#selYear1 option:selected").val() <= $("#selYear2 option:selected").val() &&
			$("#selMonth1 option:selected").val() <= $("#selMonth2 option:selected").val() &&
			$("#selDay1 option:selected").val() <= $("#selDay2 option:selected").val()) {
			
			return true;
		}else {
			return false;
		}
	}
	// ajax 리턴 price 콤마 찍기 위함
	function comma(x){
		return x.toString().replace(/\B(?=(\d{3})+(?!\d))/g,",");
	}
	
	$(document).ready(function() {
		 $("#btn").click(function() {
			if($("#btn").val() == "전체조회") {
				location.reload();
			}
		})
	})
</script>
<script type="text/javascript">
	// 구현하기에는 아직 무리...
	// 동적 셀렉트박스 소스 가져옴
    window.onload = function() {

        /********************************************
        select1
        ********************************************/
        
        var frm = document.getElementById('mainForm');
        
        var nowDate = new Date();
        var nowYear = nowDate.getFullYear();
        var nowMonth = eval(nowDate.getMonth()) +1;
        var nowDay = eval(nowDate.getDate());
        
        /***************
         * 년 세팅
         ***************/
        var startYear    = nowYear - 10;
        for( var i=0; i<20; i++ ) {
            frm['dateYear'].options[i] = new Option(startYear+i, startYear+i);
        }
    
        /***************
         * 월 세팅
         **************/
        for (var i=0; i<12; i++) {
            frm['dateMonth'].options[i] = new Option(i+1, i+1);
        }
        
        
        /***************************************
         * 먼저 현재 년과 월을 셋팅
         * 윤년계산과 월의 마지막 일자를 구하기 위해
         ***************************************/
        frm['dateYear'].value        = nowYear;
        frm['dateMonth'].value    = nowMonth;
        
        setDay();
        /********************************************
         * 일(day)의 select를 생성하고 현재 일자로 초기화
         ********************************************/
        frm['dateDay'].value        = nowDay;
        
        
        
        /********************************************
        select2
        ********************************************/
        
        var frm2 = document.getElementById('mainForm2');
        
        var nowDate2 = new Date();
        var nowYear2 = nowDate2.getFullYear();
        var nowMonth2 = eval(nowDate2.getMonth()) +1;
        var nowDay2 = eval(nowDate2.getDate());
        
        /***************
         * 년 세팅
         ***************/
        var startYear2    = nowYear2 - 10;
        for( var i=0; i<20; i++ ) {
            frm2['dateYear2'].options[i] = new Option(startYear2+i, startYear2+i);
        }
    
        /***************
         * 월 세팅
         **************/
        for (var i=0; i<12; i++) {
            frm2['dateMonth2'].options[i] = new Option(i+1, i+1);
        }
        
        
        /***************************************
         * 먼저 현재 년과 월을 셋팅
         * 윤년계산과 월의 마지막 일자를 구하기 위해
         ***************************************/
        frm2['dateYear2'].value        = nowYear2;
        frm2['dateMonth2'].value    = nowMonth2;
        
        setDay2();
        /********************************************
         * 일(day)의 select를 생성하고 현재 일자로 초기화
         ********************************************/
        frm2['dateDay2'].value        = nowDay2;
    }

    /******************
     * selsect1 일(day) 셋팅
     ******************/
    function setDay() {
    
        var frm = document.getElementById("mainForm");
        
        var year = frm['dateYear'].value;
        var month = frm['dateMonth'].value;
        var day = frm['dateDay'].value;    
        var dateDay = frm['dateDay'];
        
        var arrayMonth = [31,28,31,30,31,30,31,31,30,31,30,31];

        /*******************************************
         * 윤달 체크 부분
         * 윤달이면 2월 마지막 일자를 29일로 변경
         *******************************************/
        if ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0) {
            arrayMonth[1] = 29;
        }

        /**************************************
         * 기존 일(day) select를 모두 삭제한다.
         **************************************/
        for( var i=dateDay.length; i>0; i--) {
            dateDay.remove(dateDay.selectedIndex);
        }
            
        /*********************************
         * 일(day) select 옵션 생성
         *********************************/
        for (var i=1; i<=arrayMonth[month-1]; i++) {
            dateDay.options[i-1] = new Option(i, i);
        }

        /*********************************************
         * 기존에 선택된 일값 유지
         * 기존 일값보다 현재 최대일값이 작을 경우
         * 현재 선택 최대일값으로 세팅
         ********************************************/
        if( day != null || day != "" ) {
            if( day > arrayMonth[month-1] ) {
                dateDay.options.selectedIndex = arrayMonth[month-1]-1;
            } else {
                dateDay.options.selectedIndex = day-1;
            }
        }
    }
    
    /******************
     * select2 일(day) 셋팅
     ******************/
    function setDay2() {
    	
        var frm2 = document.getElementById("mainForm2");
        
        var year2 = frm2['dateYear2'].value;
        var month2 = frm2['dateMonth2'].value;
        var day2 = frm2['dateDay2'].value;    
        var dateDay2 = frm2['dateDay2'];
        
        var arrayMonth2 = [31,28,31,30,31,30,31,31,30,31,30,31];

        /*******************************************
         * 윤달 체크 부분
         * 윤달이면 2월 마지막 일자를 29일로 변경
         *******************************************/
        if ((year2 % 4 == 0 && year2 % 100 != 0) || year2 % 400 == 0) {
            arrayMonth2[1] = 29;
        }

        /**************************************
         * 기존 일(day) select를 모두 삭제한다.
         **************************************/
        for( var i=dateDay2.length; i>0; i--) {
            dateDay2.remove(dateDay2.selectedIndex);
        }
            
        /*********************************
         * 일(day) select 옵션 생성
         *********************************/
        for (var i=1; i<=arrayMonth2[month2-1]; i++) {
            dateDay2.options[i-1] = new Option(i, i);
        }

        /*********************************************
         * 기존에 선택된 일값 유지
         * 기존 일값보다 현재 최대일값이 작을 경우
         * 현재 선택 최대일값으로 세팅
         ********************************************/
        if( day2 != null || day2 != "" ) {
            if( day2 > arrayMonth2[month2-1] ) {
                dateDay2.options.selectedIndex = arrayMonth2[month2-1]-1;
            } else {
                dateDay2.options.selectedIndex = day2-1;
            }
        }
    }
</script>

</body>
</html>