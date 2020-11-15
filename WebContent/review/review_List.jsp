
<%@page import="review.vo.ReviewPageInfo"%>
<%@page import="review.vo.ReviewBean"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:set var="pageInfo" value="${pageinfo }"  /> 
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
    <link href="css/review.css" rel="stylesheet">
    <!--[if lt IE 9]>
    <script src="js/html5shiv.js"></script>
    <script src="js/respond.min.js"></script>
    <![endif]-->
    <link rel="shortcut icon" href="images/ico/favicon.ico">
    <link rel="apple-touch-icon-precomposed" sizes="144x144" href="images/ico/apple-touch-icon-144-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="114x114" href="images/ico/apple-touch-icon-114-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="72x72" href="images/ico/apple-touch-icon-72-precomposed.png">
    <link rel="apple-touch-icon-precomposed" href="images/ico/apple-touch-icon-57-precomposed.png">
    <script src="js/jquery-3.5.0.js"></script>
    	<script type="text/javascript">
   	// 메뉴 액티브
   $(document).ready(function() {
	  $(".nav4").addClass("active"); 
   });
   </script>
</head>
<body>
    
<jsp:include page="/inc/top.jsp" />

    <div class="page-title" style="background-image: url(images/page-title.png)">
        <h1>후기게시판</h1>
    </div>
    
    <section id="blog">

        <div class="blog container">
            <div class="row">
                <div class="col-md-8">
                	<table>
                		<c:forEach var="article" items="${articleList }" >
		                	<div class="blog-item" style="box-shadow: 1px 1px 20px #ddd;">
		                        <div class="blog-content" 
		                           onclick="location.href='Review_Content.re?&r_num=${article.r_num }'">
		                            <a class="blog_cat" style="margin-bottom: 0px;">[${article.r_name }]</a><br>
		                            <a class="readmore">작성자&nbsp; : &nbsp;${article.r_id } &nbsp;&nbsp; </a><br>
		                            <a class="readmore">작성날짜&nbsp; : &nbsp;${article.r_date }</a>&nbsp;&nbsp;
		                            <a class="readmore">조회수&nbsp; : &nbsp;${article.r_readcount }&nbsp;&nbsp;</a>
		                            <a class="readmore">
                                       <img src="review/love.png" width="20px" height="20px">&nbsp;${article.r_likecount }
                                    </a>
		                            <h2>
		                            <a>${article.r_subject }</a>
		                            <c:if test="${article.r_image != null }">
		                              <img src="review/icon.png" width="40px" height="40px">
		                            </c:if>
		                            &nbsp;(${article.r_cnt })
		                            </h2>
		                        </div>
		                    </div>
                		</c:forEach>
				</table>
                    
                </div>
                <!--/.col-md-8-->
                <aside class="col-md-4">
                    <div class="widget search" style="box-shadow: 1px 1px 20px #ddd;" >
                        <form action="Review_List.re" role="form">
                            <input type="text" name="r_search" class="form-control search_box" autocomplete="off" placeholder="Search Here" >
                            <button type="submit" style="margin: 25px 35px 0 0;"><i class="fa fa-search"></i></button>
                        </form>
                    </div>
                    <!--/.search-->
 
                    <!--글 쓰기-->
                    <div class="form-group">
                    <c:if test="${sessionScope.id != null}">
                    	<input type="button" class="btn btn-primary btn-lg" value="리뷰 글 쓰기" style="width: 100%;" onclick="location.href='Review_WriteForm.re'">
                    </c:if>
                    </div>
                    <!--글 쓰기-->
                    
					<!--카테고리-->
                    <div class="widget archieve" style="box-shadow: 1px 1px 20px #ddd;">
                        <h3>Categories</h3>
                        <div class="row">
                            <div class="col-sm-12" >
                                <ul class="blog_archieve" >
                                <li><a href="Review_List.re?r_code=17" style="float: right; margin-right: 50px">기타지역(부산외) &nbsp; </a></li>
                                	<li><a href="Review_List.re">전체 글 &nbsp; (${pageInfo.listCount })</a> <span style="float: right; margin-right: 50px"><a href="Review_List.re?r_code=9">사상구 &nbsp; </a></span></li>
                                    <li><a href="Review_List.re?r_code=1">강서구 &nbsp; </a> <span style="float: right; margin-right: 50px"><a href="Review_List.re?r_code=10">사하구 &nbsp; </a></span></li>
                                    <li><a href="Review_List.re?r_code=2">금정구 &nbsp; </a> <span style="float: right; margin-right: 50px"><a href="Review_List.re?r_code=11">서구 &nbsp; </a></span></li>
                                    <li><a href="Review_List.re?r_code=3">기장군 &nbsp; </a> <span style="float: right; margin-right: 50px"><a href="Review_List.re?r_code=12">수영구 &nbsp; </a></span></li>
                                    <li><a href="Review_List.re?r_code=4">남구 &nbsp; </a>  <span style="float: right; margin-right: 50px"><a href="Review_List.re?r_code=13">연제구&nbsp; </a></span></li>
                                    <li><a href="Review_List.re?r_code=5">동구 &nbsp; </a> <span style="float: right; margin-right: 50px"><a href="Review_List.re?r_code=14">영도구 &nbsp; </a></span></li>
                                    <li><a href="Review_List.re?r_code=6">동래구 &nbsp; </a> <span style="float: right; margin-right: 50px"><a href="Review_List.re?r_code=15">중구 &nbsp; </a></span></li>
                                    <li><a href="Review_List.re?r_code=7">부산진구 &nbsp; </a> <span style="float: right; margin-right: 50px"><a href="Review_List.re?r_code=16">해운대구&nbsp; </a></span></li>
                                    <li><a href="Review_List.re?r_code=8">북구 &nbsp; </a></li>
                                    
                                    
                                </ul>
                            </div>
                        </div>
                    </div>
					<!--카테고리-->


                    <!-- 인기 여행지 -->
                   <div class="widget popular_post" style="box-shadow: 1px 1px 20px #ddd;">
                        <h3>인 기 여 행 지</h3>
                        <ul style=" text-align: center;">
                        <c:forEach var="list" items="${placeList }" begin="0" end="2">
                             <li style="margin-bottom: 10px">               
                               	  <a href="PlaceDetail.pl?pl_num=${list.pl_num }" title="${list.pl_name }">
	                                  <span>	 
	                                   <img src="placeUpload/${list.pl_image }"  width="200" height="130" style="margin-bottom: 10px"><br>                               
	                                  <c:choose>
											<c:when test="${fn:length(list.pl_name) gt '20'}">
												&nbsp; ${fn:substring(list.pl_name,0,20) }&nbsp;...	
											</c:when>
											<c:otherwise>
												&nbsp; ${list.pl_name }										
											</c:otherwise>
									</c:choose>
									</span>											                                 
	                              </a>                         
                            </li>                  
                        </c:forEach>
         
                        </ul>
                    </div>
                    <!--/.archieve-->
                    
                    <div class="widget blog_gallery">
                        <h3>추 천 상 품</h3>
                        <ul class="sidebar-gallery clearfix">
     						 <c:forEach var="list" items="${productList }" varStatus="stat">                    	                                 		
                        		<li>
                            		<a href="productDetail.pr?p_num=${list.p_num }"><img src="product/productUpload/${list.p_image }" title="${list.p_name}" width="50" height="100"/>
                            			 <c:choose>
											<c:when test="${fn:length(list.p_name) gt 9}">
												&nbsp; ${fn:substring(list.p_name,0,9) }...	
											</c:when>
											<c:otherwise>
												&nbsp; ${list.p_name }									
											</c:otherwise>
										</c:choose>
                            		</a>                            
                           	 	</li>                   	                  	
                      		  </c:forEach>                         
                        </ul>
                    </div>
                    
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
                    			<li><a href="Review_List.re?page=${pageInfo.page - 1 }"><i class="fa fa-long-arrow-left"></i></a></li>
                    		</c:otherwise>
                    	</c:choose>
                    
                    	<c:forEach var="a" begin="${pageInfo.startPage }" end="${pageInfo.endPage }" step="1">
                    		
                    		<c:choose>
                    		
                    			<c:when test="${a == pageInfo.page }">
                    				<li class="active"><a>${a }</a></li>
                    			</c:when>
                    			<c:otherwise>
									<li><a href="Review_List.re?page=${a }">${a }</a></li>
                    			</c:otherwise>
                    		</c:choose>
                    	
                    	</c:forEach>
                    		
                    		<c:choose>
                    		
                    			<c:when test="${pageInfo.page >= pageInfo.maxPage }">
                    				<li><a href="#"><i class="fa fa-long-arrow-right"></i></a></li>
                    			</c:when>
                    			<c:otherwise>
                    				<li><a href="Review_List.re?page=${pageInfo.page + 1 }"><i class="fa fa-long-arrow-right"></i></a></li>
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