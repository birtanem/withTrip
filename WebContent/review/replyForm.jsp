<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="article" value="${article }" />
<!DOCTYPE html>
<html lang="en">
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
    
    <script src="js/summernote-ko-KR.js"></script>  
    
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
	<link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-lite.min.css" rel="stylesheet">
	<script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-lite.min.js"></script>
    
    <script type="text/javascript">
   		// 메뉴 액티브
	   $(document).ready(function() {
		  $(".nav4").addClass("active"); 
	   });
   </script>
   
    <script type="text/javascript">
    
    function contentCheck(){
    	
        if ($(".r_content").val() == "") {
            alert("내용 입력해주세요!");
            return false;
        }else {
            return true;
        }
    }
    
    function writeCheck(){
    	
        if (contentCheck()) {
            
        	if (confirm("입력하신 정보로 작성하시겠습니까?")) {
        	    return true;
			}else {
				return false;
			}
        	
        }else {
            return false;
        }
        
    }

    </script>

</head>
<body>
    <c:if test="${sessionScope.id == null}">
    	<c:redirect url="MemberLoginForm.me" />
    </c:if>
<jsp:include page="/inc/top.jsp" />

    <div class="page-title" style="background-image: url(images/page-title.png)">
        <h1>Review</h1>
    </div>
    
	<section id="blog">
		
		 <div class="container">
            <div class="row contact-wrap"> 
                <div class="status alert alert-success" style="display: none"></div>
                
                <form action="Comment_ReplyPro.re" name="writeForm" method="post" onsubmit="return writeCheck()">
                    <div class="col-sm-offset-1">
                        
                      <div class="col-sm-10 ">

						  	<input type="hidden" name="r_num" value="${article.r_num }">
						  	<input type="hidden" name="rc_num" value="${param.rc_num }">
						  	<input type="hidden" name="rc_ref" value="${article.rc_ref }">
						  	<input type="hidden" name="rc_seq" value="${article.rc_seq }">
						  	<input type="hidden" name="rc_lev" value="${article.rc_lev }">
						    <input type="hidden" name="id" value="${sessionScope.id }">
                            
                            <textarea id="summernote" name="rc_content" class="r_content" required>[RE]&nbsp; &nbsp;</textarea>
                            
                            <div class="form-group">
                            <button type="submit" name="submit" class="btn btn-primary btn-lg" >글 등록</button>
                            <button type="reset" name="reset" class="btn btn-primary btn-lg" >다시 쓰기</button>
                        </div>
                        </div>    
                      </div>
                    </form> 
                 </div>
            </div>
		
    </section>

<jsp:include page="/inc/bottom.jsp" />

    <script>
            $(document).ready(function() {
                $('#summernote').summernote({ // summernote를 사용하기 위한 선언
                    height: 400,lang: 'ko-KR',
                });
            });
   </script>

    <script src="js/bootstrap.min.js"></script>
    <script src="js/jquery.prettyPhoto.js"></script>
    <script src="js/owl.carousel.min.js"></script>
    <script src="js/jquery.isotope.min.js"></script>
    <script src="js/main.js"></script>
</body>
</html>