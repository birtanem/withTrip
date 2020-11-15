<%@page import="place.vo.PlacePageInfo"%>
<%@page import="place.vo.PlaceBean"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="pageInfo" value="${pageinfo }"  /> 
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>나의 당첨내역</title>

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
    
    <link href="css/order.css" rel="stylesheet">

    <link rel="shortcut icon" href="../images/ico/favicon.ico">
    <link rel="apple-touch-icon-precomposed" sizes="144x144" href="images/ico/apple-touch-icon-144-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="114x114" href="images/ico/apple-touch-icon-114-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="72x72" href="images/ico/apple-touch-icon-72-precomposed.png">
    <link rel="apple-touch-icon-precomposed" href="images/ico/apple-touch-icon-57-precomposed.png">
<style type="text/css">

#table1 {
	color: black;
}

/* 테이블 td조정 */

#td_title {
	color: blue;
}

#td1 {
	width: 68px;
}

#td2 {
	width: 78px;
}

#td3 {
	width: 95px;
}

#td4 {
	width: 110px;
	font-weight: bold;
	color: grey;
}

#spanCount1{
	font-size: 23px;
}

#spanCount2{
	font-size: 23px;
    font-weight: bold;
    color: black;
}

#btnBack{
	float: right;
    margin-right: 5px;
}


</style>
</head>
<body>    
<jsp:include page="/inc/top.jsp" />

    <div class="page-title" style="background-image: url(images/page-title.png)">
        <h1>나의 당첨내역</h1>
        
    </div>
    
    <section id="blog">
        <div class="blog container">
            <div class="row">
                <div class="col-sm-10">
                
                	<div class="blog-item">
                        <div class="blog-content">
	                    	<div>
	                    		<span id="spanCount1">당첨내역 </span>
<%-- 	                    		<span id="spanCount2"> ${pageInfo.listCount }</span> --%>
	                    		<input type="button" id="btnBack" value="돌아가기" onclick="location.href='./MemberMypage.me'">
	                    	</div>        		   
                        </div>
                    </div>
               <div class="blog-item">
				<div class="o_list" style="clear: both;">
					<table class="ot_list">
						<tr>
							<th>회차</th>
							<th>당첨일</th>
							<th style="padding-right: 20px;">30000P</th>
							<th style="padding-right: 20px;">50000P</th>
							<th style="padding-right: 20px;">100000P</th>
						</tr>
						<c:forEach var="wList" items="${eventWinList }">
							<c:if test="${wList.member_id eq sessionScope.id }">
								<tr>
									<td>${wList.event_round }</td>
									<td>${wList.ew_date }</td>
									<td style="padding-right: 20px;">${wList.ew_cp_3 }</td>
									<td style="padding-right: 20px;">${wList.ew_cp_5 }</td>
									<td style="padding-right: 20px;">${wList.ew_cp_10 }</td>
								</tr>
							</c:if>
						
						</c:forEach>

					</table>
			   		            
				</div>
				</div>
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
                    			<li><a href="MemberWriteList.me?page=${pageInfo.page - 1 }"><i class="fa fa-long-arrow-left"></i></a></li>
                    		</c:otherwise>
                    	</c:choose>
                    
                    	<c:forEach var="a" begin="${pageInfo.startPage }" end="${pageInfo.endPage }" step="1">
                    		
                    		<c:choose>
                    		
                    			<c:when test="${a == pageInfo.page }">
                    				<li class="active"><a>${a }</a></li>
                    			</c:when>
                    			<c:otherwise>
									<li><a href="MemberWriteList.me?page=${a }">${a }</a></li>
                    			</c:otherwise>
                    		</c:choose>
                    	
                    	</c:forEach>
                    		
                    		<c:choose>
                    		
                    			<c:when test="${pageInfo.page >= pageInfo.maxPage }">
                    				<li><a href="#"><i class="fa fa-long-arrow-right"></i></a></li>
                    			</c:when>
                    			<c:otherwise>
                    				<li><a href="MemberWriteList.me?page=${pageInfo.page + 1 }"><i class="fa fa-long-arrow-right"></i></a></li>
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