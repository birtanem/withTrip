<%@page import="place.vo.PlacePageInfo"%>
<%@page import="place.vo.PlaceBean"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="pageInfo" value="${pageInfo }"  /> 
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>Home | Place | Place_Content</title>

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
    <link rel="shortcut icon" href="../images/ico/favicon.ico">
    <link rel="apple-touch-icon-precomposed" sizes="144x144" href="images/ico/apple-touch-icon-144-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="114x114" href="images/ico/apple-touch-icon-114-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="72x72" href="images/ico/apple-touch-icon-72-precomposed.png">
    <link rel="apple-touch-icon-precomposed" href="images/ico/apple-touch-icon-57-precomposed.png">
    
    <script src="js/jquery-3.5.0.js"></script>
    
    
   <script type="text/javascript">
   	// 메뉴 액티브
   $(document).ready(function() {
	  $(".nav3").addClass("active"); 
   });
   </script>


</head>
<body>
    
<jsp:include page="/inc/top.jsp" />

    <div class="page-title" style="background-image: url(images/top/Busan1.jpg); background-size: cover; background-position: center;">
        <h1>여행지</h1>
        
    </div>
    
    <section id="blog">

        <div class="blog container">
            <div class="row">
                <div class="col-md-8">
                		<c:forEach var="article" items="${articleList }" >


						<div class="blog-item">
                        <a href="PlaceDetail.pl?pl_num=${article.pl_num }&page=${pageInfo.page }"><img class="img-responsive img-blog" src="placeUpload/${article.pl_image }" width="100%" alt="" /></a>
                        <div class="blog-content">
                            <a href="PlaceDetail.pl?pl_num=${article.pl_num }&page=${pageInfo.page }" class="blog_cat">테마(주제) : ${article.pl_theme}</a>
                            <a href="PlaceDetail.pl?pl_num=${article.pl_num }&page=${pageInfo.page }">
                            <h2> 장소명 : ${article.pl_name} </h2>
                            <h3>&nbsp;&nbsp;작성일&nbsp;&nbsp;${article.pl_date }&nbsp;&nbsp;조회수&nbsp;${article.pl_readcount } &nbsp;&nbsp;<span style="color: red; font-size: 18pt;">★</span>
                               <c:choose>
                                     <c:when test="${article.pl_likecount == 0 }">
                                       0
                                    </c:when>
                                    <c:otherwise>
                                       ${article.pl_likeAvg }
                                    </c:otherwise>
                                </c:choose>
                           </h3></a>
                            <a href="PlaceDetail.pl?pl_num=${article.pl_num }&page=${pageInfo.page }"> 주 소 : ${article.pl_address }<br>Read More (자세히 보기 / 클릭)<i class="fa fa-long-arrow-right"></i></a>
                        </div>
                    </div>
                		
                		</c:forEach>
				
                    
                </div>
                <!--/.col-md-8-->

                <aside class="col-md-4">
                    
                <jsp:include page="/inc/includePlace.jsp" />                        
       
                </aside>
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
                    			<li><a href="PlaceList.pl?check=2&page=${pageInfo.page - 1 }"><i class="fa fa-long-arrow-left"></i></a></li>
                    		</c:otherwise>
                    	</c:choose>
                    
                    	<c:forEach var="a" begin="${pageInfo.startPage }" end="${pageInfo.endPage }" step="1">
                    		
                    		<c:choose>
                   			<c:when test="${a == pageInfo.page }">
                    				<li class="active"><a>${a }</a></li>
                    			</c:when>
                    			<c:otherwise>
									<li><a href="PlaceList.pl?check=2&page=${a }">${a }</a></li>
                    			</c:otherwise>
                    		</c:choose>
                    	
                    	</c:forEach>
                    		
                    		<c:choose>
                    		
                    			<c:when test="${pageInfo.page >= pageInfo.maxPage }">
                    				<li><a href="#"><i class="fa fa-long-arrow-right"></i></a></li>
                    			</c:when>
                    			<c:otherwise>
                    				<li><a href="PlaceList.pl?page=${pageInfo.page + 1 }"><i class="fa fa-long-arrow-right"></i></a></li>
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
    <script src="js/jquery.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/jquery.prettyPhoto.js"></script>
    <script src="js/owl.carousel.min.js"></script>
    <script src="js/jquery.isotope.min.js"></script>
    <script src="js/main.js"></script>
</body>
</html>