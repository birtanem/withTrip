<%@page import="review.vo.ReviewBean"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
    <c:set var="article" value="${article }" />
    <c:set var="nowPage" value="${page }" />
    <c:set var="pageinfo" value="${pageinfo }" />
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
	<script type="text/javascript">

	$(document).ready(function(){
		
	      $('#comment').click(function(){
	    	  
				sessionStorage.setItem('id', $('#sessionID').val());
				var sessionId = sessionStorage.getItem('id');
				var id = sessionId;
				var update = "Comment_UpdateForm.re";
				
				
	            var div = $('#comments').css("display");
	            
	            if (div == "none") {
	                $('#comments').css("display","inline");
	            }else {
	                $('#comments').css("display","none");
	            }
	            
	        });
	      
		$('#commentWrite').click(function(){
			
			var formData = $("form[name=commentForm]").serialize();
			
            sessionStorage.setItem('id', $('#sessionID').val());
            var sessionId = sessionStorage.getItem('id');
            var id = sessionId;
            
            if ($("#rc_content").val() == "") {
				alert("내용 입력 해주세요!");				
				$("#rc_content").focus();
			}else{
				$.ajax({
						
					type : 'post',
					dataType : "json",
					url : 'Comment_WritePro.re',
					data : formData,
					success : function(rdata){
						
					$('.comments').html("");
			        alert("글등록 되었습니다.");
			        $('#rc_content').val("");
			        
						$.each(rdata,function(index,item){
							$('#commentCount').html(item.commentCount);
	                        if(!id ){
	                            $('.comments').append(
	
	                            '<div class="single-comment" >'
	                              +'<div class="comment-content" style="margin-left: 10px; margin-right: 10px;">'
	                              +'<h5 style="float: right;">'+item.rc_date+'</h5>'
	                              +'<h5>작성자&nbsp; : &nbsp;'+item.rc_id+'</h5>'
	                              +'<p>'+item.rc_content+'</p> </div>'
	                              +'<div class="comment-count">'
	                              +'</div>'
	                              +'</div>'
	                              
	                        );
	                        }
	                    else if (id != null && id != item.rc_id) {
	                        $('.comments').append(
	                            '<div class="single-comment" >'
	                              +'<div class="comment-content" style="margin-left: 10px; margin-right: 10px;">'
	                              +'<h5 style="float: right;">'+item.rc_date+'</h5>'
	                              +'<h5>작성자&nbsp; : &nbsp;'+item.rc_id+'</h5>'
	                              +'<p>'+item.rc_content+'</p> </div>'
	                              +'<div class="comment-count">'
	                              +'<a id ="reply" style="margin-right: 15px;" href="Comment_ReplyForm.re?r_num=${article.r_num}&rc_num='+item.rc_num+'">답글</a>'
	                              +'</div>'
	                              +'</div>'
	                        );
	                        
	                        }
	                    else if (id != null && id == item.rc_id) {
	                        $('.comments').append(                          
	
	                              '<div class="single-comment" >'
	                              +'<div class="comment-content" style="margin-left: 10px; margin-right: 10px;">'
	                              +'<h5 style="float: right;">'+item.rc_date+'</h5>'
	                              +'<h5>작성자&nbsp; : &nbsp;'+item.rc_id+'</h5>'
	                              +'<p>'+item.rc_content+'</p> </div>'
	                              +'<div class="comment-count">'
	                              +'<a id ="reply" href="Comment_ReplyForm.re?r_num=${article.r_num}&rc_num='+item.rc_num+'">답글</a>'
	                              +'<a id ="update" href="Comment_UpdateForm.re?r_num=${article.r_num}&rc_num='+item.rc_num+'">수정</a>'
	                              +'<a id ="delete" style="margin-right: 15px;" href="Comment_Delete.re?r_num=${article.r_num}&rc_num='+item.rc_num+'"> 삭제 </a>'
	                              +'</div>'
	                              +'</div>'
	                        );
	                        }   
						});
					}
				});
			}
            
		});
		
		$('#like').click(function(){

            sessionStorage.setItem('id', $('#sessionID').val());
            var sessionId = sessionStorage.getItem('id');
            var id = sessionId;
            
			if (!id) {
				alert("로그인 하세요!");
			}else {
				
			$.ajax({
				
				url : "Review_ContentLike.re?r_num=${article.r_num }&id=${sessionScope.id }",
				dataType : "json",
				success : function(rdata){
					$('#likeCount').html(rdata.likecount);
						alert("좋아요 하셨습니다.");
					},
					error:function(){
						alert("이미 좋아요 하셨습니다.");
					}
				});
			
			}
			
		});
		
	});
	
    </script>
</head>
<body>
    
<jsp:include page="/inc/top.jsp" />

    <div class="page-title" style="background-image: url(images/page-title.png)">
        <h1>후기 게시글</h1>
    </div>
    

     <section id="blog" style="min-height: 560px;">

        <div class="blog container" >
            <div class="row">
            
                <div class="col-md-8">
                
					<div class="blog-item" style="box-shadow: 1px 1px 20px #ddd;">
                            <a class="blog_cat" style="margin: 10px;">[${article.r_name }]</a>
                            <h2 style="margin-left: 10px;">${article.r_subject }</h2>
                            <div class="post-meta" style="margin-left: 10px;">
                                <p><a>${article.r_id }</a></p>
                                <p><i class="fa fa-clock-o"></i><a>${article.r_date }</a></p>
                                <p><img src="review/love.png" width="15px" height="15px">(<a id="likeCount">${article.r_likecount }</a>)</p>
                                <p><a> 조회수&nbsp; : &nbsp;(${article.r_readcount })</a></p>
                            </div>
                            <h2 style="margin-left: 10px; width: 97%;">${article.r_content }</h2>
                            
                            <div class="inner-meta">
                                <div class="social-btns" style="margin-left: 10px;">
                                    <a id="like"  href="javascript:;"> <i class="fa fa-heart"></i> Like</a>
                                    
                                    <input type="hidden" id="sessionID" value="${sessionScope.id }">
                                    
                                    <a id="comment" href="javascript:;" class="tweet-bg"> 댓글 보기</a>
                                    <c:if test="${sessionScope.id == article.r_id }">
                                    <a id="reviewUpdate" href="Review_UpdateForm.re?r_num=${article.r_num}">수정</a>
                                    <a id="reviewDelete" href="Review_DeleteForm.re?r_num=${article.r_num}">삭제</a>
                                    </c:if>
                                </div>
                            </div>
                            <br>
                          <h2 style="margin-left: 10px;">Comments&nbsp; (<a id="commentCount">${pageinfo.commentCount }</a>)</h2>
                  <!--        댓글 쓰기                    -->
                             <div id="comments" style="display: none;">
                                <div class="single-comment">
                                    <div class="comment-content comment-form" >
                                    <c:if test="${sessionScope.id != null }">
                                        <form action="Comment_WritePro.re" name="commentForm" method="post">
                                            <textarea rows="5" cols="100" id="rc_content" name="rc_content" style="box-shadow: 1px 1px 20px #ddd; border-radius: 10px 10px 10px 10px; margin-left:20px; width: 95%; "></textarea>
                                            <input type="hidden"name="id" id="id" value="${sessionScope.id }">
                                            <input type="hidden"name="r_num" id="r_num" value="${article.r_num }">
                                            <input type="button" style=" margin-left:20px;" class="btn btn-primary btn-lg" id="commentWrite" value="Comment" >
                                        </form>
                                    </c:if>    
                                    </div>
                                </div>
                    
                            <div class="comments">
                     <!--          댓글 영역                   -->
                    			 <c:forEach var="arrayList" items="${articleList }">
                     				<div class="single-comment" >
			                        <div class="comment-content" style="margin-left: 10px; margin-right: 10px;">
			                        <h5 style="float: right;">${arrayList.rc_date }</h5>
			                        <h5>작성자&nbsp; : &nbsp;${arrayList.rc_id }</h5>
			                        <p>${arrayList.rc_content }</p> </div>
					                <div class="comment-count">
					                <c:if test="${sessionScope.id != null }">
					                <a id ="reply" style="margin-right: 15px;" href="Comment_ReplyForm.re?r_num=${article.r_num}&rc_num=${arrayList.rc_num }">답글</a>
					                <c:if test="${arrayList.rc_id == sessionScope.id}">
					                <a id ="update" style="margin-right: 15px;" href="Comment_UpdateForm.re?r_num=${article.r_num}&rc_num=${arrayList.rc_num }"> 수정 </a>
			                        <a id ="delete" style="margin-right: 15px;" href="Comment_Delete.re?r_num=${article.r_num}&rc_num=${arrayList.rc_num }"> 삭제 </a>
			                        </c:if>
			                        </c:if>
			                        </div>
			                        </div>
                    			 </c:forEach>
                            </div>
                        </div>
                    </div>
                   </div> 
                    <aside class="col-md-4">
                    
					<!--카테고리-->
                    <div class="widget archieve" style="box-shadow: 1px 1px 20px #ddd;">
                        <h3>Categories</h3>
                        <div class="row">
                            <div class="col-sm-12">
                                <ul class="blog_archieve">
                                <li><a href="Review_List.re?r_code=17" style="float: right; margin-right: 50px">기타지역(부산외) &nbsp; </a></li>
                                	<li><a href="Review_List.re">전체 글 &nbsp; </a> <span style="float: right; margin-right: 50px"><a href="Review_List.re?r_code=9">사상구 &nbsp; </a></span></li>
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
              </div>
      </section>    
        
	<jsp:include page="/inc/bottom.jsp" />

    <script src="js/jquery.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/jquery.prettyPhoto.js"></script>
    <script src="js/owl.carousel.min.js"></script>
    <script src="js/jquery.isotope.min.js"></script>
    <script src="js/main.js"></script>
</body>
</html>