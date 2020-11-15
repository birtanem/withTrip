<%@page import="place.vo.PlaceBean"%>
<%@page import="place.vo.PlaceCommentBean"%>
<%@page import="place.vo.PCpageInfo"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <c:set var="article" value="${article }" />
    <c:set var="commentList" value="${commentList }" />
    <c:set var="pageInfo" value="${cpageInfo }"  /> 
<%
ArrayList<PlaceCommentBean> articleList = (ArrayList<PlaceCommentBean>)request.getAttribute("commentList");
PCpageInfo pageInfo = (PCpageInfo)request.getAttribute("cpageInfo");
int nowPage = pageInfo.getPage();
int maxPage = pageInfo.getMaxPage();
int startPage = pageInfo.getStartPage();
int endPage = pageInfo.getEndPage();
int listCount = pageInfo.getListCount();
String plPage = request.getParameter("page");
%>
   
    

    
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">
<title>WithTrip | Place | Place_Content</title>

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

	
<script src="js/summernote-ko-KR.js"></script>	
<!-- 썸머노트 필수 스크립트 링크 코드 -->
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-lite.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-lite.min.js"></script>


<link rel="shortcut icon" href="images/ico/favicon.ico">
<link rel="apple-touch-icon-precomposed" sizes="144x144"
	href="images/ico/apple-touch-icon-144-precomposed.png">
<link rel="apple-touch-icon-precomposed" sizes="114x114"
	href="images/ico/apple-touch-icon-114-precomposed.png">
<link rel="apple-touch-icon-precomposed" sizes="72x72"
	href="images/ico/apple-touch-icon-72-precomposed.png">
<link rel="apple-touch-icon-precomposed"
	href="images/ico/apple-touch-icon-57-precomposed.png">    
   
   <script type="text/javascript">
   	// 메뉴 액티브
   $(document).ready(function() {
	  $(".nav3").addClass("active"); 
   });
   </script>

<script type="text/javascript">
$("#radioTest:radio[value='orange']").is(":checked") ;

function validCheck() {
	if($('#p1').is(":checked")==false  && $('#p2').is(":checked")==false 
			&& $('#p3').is(":checked")==false && $('#p4').is(":checked")==false && $('#p5').is(":checked")==false ){
		alert("하트 추천 갯수를 선택해 주세요!");
		$("#p3").focus();
		return false;
	}
	if($('#pc_content').val()==0){
		alert("댓글을 입력하여 주세요!");
		$("#pc_content").focus();
		return false;
	}


}


</script>


</head>
<body>
    
<jsp:include page="/inc/top.jsp" />

    <div class="page-title" style="background-image: url(images/page-title.png)">
        <h1>여행지</h1>
    </div>
    
            <section id="blog">
        
                <div class="blog container">
            <div class="row">
                <div class="col-md-8">

                    <div class="blog-item">
                        <a href="#"><img class="img-responsive img-blog" src="placeUpload/${article.pl_image }" width="100%" alt="" /></a>
                        <div class="blog-content">
                            <a href="#" class="blog_cat">테 마(주제) : ${article.pl_theme} / 주 소 : ${article.pl_address }</a>
                            <h2>장소 명 : ${article.pl_name }</h2>
                            <div class="post-meta">
                                <p>주 소 : ${article.pl_address }</p>
                                <p><i class="fa fa-clock-o"></i> ${article.pl_date }</p>
                                <p>조회수 : ${article.pl_readcount }</p>
                                <c:choose>
                                    <c:when test="${article.pl_likecount == 0 }">
                                        <p><span style="color: red; font-size: 16pt;">★</span>&nbsp; 0</p>
                                    </c:when>
                                    <c:otherwise>
                                        <p><span style="color: red; font-size: 16pt;">★</span>&nbsp; ${article.pl_likeAvg }</p>
                                    </c:otherwise>
                                </c:choose>
                            </div>
                            ${article.pl_content }
                            
                            <div class="inner-meta">
                            <c:if test="${sessionScope.id == 'admin'}" >
                                <ul class="tags">
                                    <li><a href="PlaceUpdateForm.pl?pl_num=${article.pl_num }&page=<%=plPage%>">수정</a></li>
                                    <li><a href="PlaceDeletePro.pl?pl_num=${article.pl_num }&page=<%=plPage%>">삭제</a></li>
                                </ul>
                            </c:if>

                                
                            </div>
                            
                            <div class="comments">
                                <h2>Comments</h2>
                                <c:if test="${id != null}" >
                                <div class="single-comment">
                                    <div class="comment-img">
                                        <img src="images/octocat.png" alt="author">
                                    </div>
                                    <div class="comment-content comment-form">
                                        <form action="PC_WritePro.pl?pl_num=${article.pl_num }" method="post" onsubmit="return validCheck()">
                                        	
                                            <input type="hidden" name="id" value="${id }">
                    						<input type="hidden" name="page" value="<%=plPage%>">
                    						별점 : 
                                            &nbsp;&nbsp;&nbsp;<input type="radio" name="pc_rank" id="p5" value="5"><span style="color:red; font-size: 16pt;">&nbsp;★★★★★</span>
                                            &nbsp;&nbsp;&nbsp;<input type="radio" name="pc_rank" id="p4" value="4"><span style="color:red; font-size: 16pt;">&nbsp;★★★★</span>
                                            &nbsp;&nbsp;&nbsp;<input type="radio" name="pc_rank" id="p3" value="3"><span style="color:red; font-size: 16pt;">&nbsp;★★★</span>
                                            &nbsp;&nbsp;&nbsp;<input type="radio" name="pc_rank" id="p2" value="2"><span style="color:red; font-size: 16pt;">&nbsp;★★</span> 
                    						&nbsp;&nbsp;&nbsp;<input type="radio" name="pc_rank" id="p1" value="1"><span style="color:red; font-size: 16pt;">&nbsp;★</span>
                                           
                                            <textarea id="pc_content" name="pc_content"></textarea>
                                            <input type="submit" value="Comment">
                                        </form>
                                    </div>
                                </div>
                                </c:if>
                                      
                  
                                      
                                <c:forEach var="commentList" items="${commentList }">                                 
                                <div class="single-comment">
                                    <div class="comment-img">
                                        <img src="images/ananimous.png" alt="author">
                                    </div>
                                    <div class="comment-content">
                                        <h5>${commentList.member_id }</h5>
                                        <p>${commentList.pc_content }</p>
                                    </div>
                                    <div class="comment-count">
                                        <a href="#"><i class="fa fa"></i> ${commentList.pc_date }</a>

                                        <a href="#"><span style="color:red; font-size: 14pt;">&nbsp;★</span> ${commentList.pc_rank }</a>

                                        
                                        <a id ="delete" href="PC_DeletePro.pl?pl_num=${article.pl_num }&pc_num=${commentList.pc_num }&page=<%=plPage%>"> 삭제 </a>

                                    </div>
                                </div>
                               
                               
                               </c:forEach>
                                                     
                            </div>
                        </div>
                    </div>
                    <!--/.blog-item-->


                </div>
                <!--/.col-md-8-->
       <aside class="col-md-4">
                     <jsp:include page="/inc/includePlace.jsp" />   

                </aside>
            </div>
            <!--/.row-->
        </div>
    </section>
    <!--/#blog-->
    
    <!--/#bottom-->
<jsp:include page="/inc/bottom.jsp" />
    
    <!--/#footer-->
	<script src="js/jquery.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/jquery.prettyPhoto.js"></script>
    <script src="js/owl.carousel.min.js"></script>
    <script src="js/jquery.isotope.min.js"></script>
    <script src="js/main.js"></script>
  
</body>
</html>