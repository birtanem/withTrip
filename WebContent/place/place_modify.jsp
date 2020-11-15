<%@page import="place.vo.PlaceBean"%>
<%@page import="place.vo.PlacePageInfo"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <c:set var="article" value="${article }" />
    <c:set var="nowPage" value="${page }" />
    <c:set var="region_code" value="${article.region_code }"/>
    <%String id = (String)session.getAttribute("id");
      String nowPage = request.getParameter("page");
      String adminid = "admin";%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">
<title>Home | AdminPage</title>
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

<script src="js/product_modal.js"></script>
<script src="js/jquery.prettyPhoto.js"></script>
<script src="js/owl.carousel.min.js"></script>
<script src="js/jquery.isotope.min.js"></script>
<script src="js/main.js"></script>

<link rel="shortcut icon" href="images/ico/favicon.ico">
<link rel="apple-touch-icon-precomposed" sizes="144x144"
	href="images/ico/apple-touch-icon-144-precomposed.png">
<link rel="apple-touch-icon-precomposed" sizes="114x114"
	href="images/ico/apple-touch-icon-114-precomposed.png">
<link rel="apple-touch-icon-precomposed" sizes="72x72"
	href="images/ico/apple-touch-icon-72-precomposed.png">
<link rel="apple-touch-icon-precomposed"
	href="images/ico/apple-touch-icon-57-precomposed.png">
	
<script src="js/summernote-ko-KR.js"></script>	
<!-- 썸머노트 필수 스크립트 링크 코드 -->
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-lite.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-lite.min.js"></script>
	
	<script type="text/javascript">
   	// 메뉴 액티브
   $(document).ready(function() {
	  $(".nav1").addClass("active"); 
   });
   </script>
   
	<script type="text/javascript">
        /* summernote에서 이미지 업로드시 실행할 함수 */
	 	function sendFile(file, editor) {
            // 파일 전송을 위한 폼생성
	 		data = new FormData();
	 	    data.append("uploadFile", file);
	 	    $.ajax({ // ajax를 통해 파일 업로드 처리
	 	        data : data,
	 	        type : "POST",
	 	        url : "ImageCallback.pl",
	 	        cache : false,
	 	        contentType : false,
	 	        processData : false,
	 	        success : function(data) { // 처리가 성공할 경우
                    // 에디터에 이미지 출력
	 	        	$(editor).summernote('editor.insertImage', data.url);
	 	        }
	 	    });
	 	}
	</script>
	
</head>
<!--/head-->
<body>
	<jsp:include page="/inc/top.jsp" />
	<!--/header-->
	<div class="page-title"
		style="background-image: url(images/page-title.png)">
		<h1>여행지</h1>
	</div>
	<section id="contact-page">
        <div class="container">
            <div class="large-title text-center">        
                <h2>여행지 소개 글 수정</h2>
            </div> 
            <div class="row contact-wrap"> 
                <div class="status alert alert-success" style="display: none"></div>
                
                <form action="PlaceUpdatePro.pl" method="post" enctype="multipart/form-data" name="placeForm">
                    <input type="hidden" name="pl_num" value="${article.pl_num }">
                    <input type="hidden" name="page" value="<%=nowPage %>">
                    <div class="col-sm-offset-1">
                    <div class="col-sm-5">
                        <div class="form-group">
                            <label>장소명 : </label>
                            <input type="text" name="pl_name" class="form-control" value="${article.pl_name }" required="required"/>
                        </div>
                        <div class="form-group">
                            <label>지역 : </label>
                            <select name="region_code" class="form-control" required="required">
		            			<option value="0" <c:if test="${article.region_code  == 0}">selected</c:if>>지역 선택하세요</option>
		            			<option value="1" <c:if test="${article.region_code  == 1}">selected</c:if>>강서구</option>
		            			<option value="2" <c:if test="${article.region_code  == 2}">selected</c:if>>금정구</option>
		            			<option value="3" <c:if test="${article.region_code  == 3}">selected</c:if>>기장군</option>
		            			<option value="4" <c:if test="${article.region_code  == 4}">selected</c:if>>남구</option>
		            			<option value="5" <c:if test="${article.region_code  == 5}">selected</c:if>>동구</option>
		            			<option value="6" <c:if test="${article.region_code  == 6}">selected</c:if>>동래구</option>
		            			<option value="7" <c:if test="${article.region_code  == 7}">selected</c:if>>부산진구</option>
		            			<option value="8" <c:if test="${article.region_code  == 8}">selected</c:if>>북구</option>
		            			<option value="9" <c:if test="${article.region_code  == 9}">selected</c:if>>사상구</option>
		            			<option value="10" <c:if test="${article.region_code == 10}">selected</c:if>>사하구</option>
		            			<option value="11" <c:if test="${article.region_code  == 11}">selected</c:if>>서구</option>
		            			<option value="12" <c:if test="${article.region_code  == 12}">selected</c:if>>수영구</option>
		            			<option value="13" <c:if test="${article.region_code  == 13}">selected</c:if>>연제구</option>
		            			<option value="14" <c:if test="${article.region_code  == 14}">selected</c:if>>영도구</option>
		            			<option value="15" <c:if test="${article.region_code  == 15}">selected</c:if>>중구</option>
		            			<option value="16" <c:if test="${article.region_code  == 16}">selected</c:if>>해운대구</option>
		            			<option value="17" <c:if test="${article.region_code == 17}">selected</c:if>>기타지역(부산외)</option>
		                 	</select>
                        </div>
                        </div>
                        
                        <div class="col-sm-5">
                        <div class="form-group">
                            <label>주제 : </label>
                            <input type="text" name="pl_theme" class="form-control" value="${article.pl_theme }" required="required"/>
                        </div>
                        <div class="form-group">
                            <label>주소 : *</label>
                            <input type="text" name="pl_address" class="form-control" value="${article.pl_address }" required="required"/>
                        </div>   
                        </div>                     
                         
                        
                     
                      <div class="col-sm-10 ">
                      <div class="form-group ">
                            <label>대표 사진 : </label>
                            <c:out value="${article.pl_image }"/><input type="file" name="pl_image" class="form-control" >
                            <input type="hidden" name="oldfile" value="${article.pl_image }">
                        </div>
                            <label>장소 소개 글</label>
                            <textarea id="summernote" name="pl_content" required="required" >${article.pl_content }</textarea>
                            
                            <div class="form-group">
                            <button type="submit" name="submit" class="btn btn-primary btn-lg" >소개 글 수정 등록</button>
                            <button type="reset" name="reset" class="btn btn-primary btn-lg" >다시 쓰기</button>
                        </div>
                        </div>    
                      </div>
           		    </form> 
                 </div>
            </div><!--/.row-->
        <!--/.container-->
    </section><!--/#contact-page-->
	
	<jsp:include page="/inc/bottom.jsp" />
    
	<!--/#footer-->


	  <script>
            $(document).ready(function() {
                $('#summernote').summernote({ // summernote를 사용하기 위한 선언
                    height: 400,lang: 'ko-KR',
					callbacks: { // 콜백을 사용
                        // 이미지를 업로드할 경우 이벤트를 발생
					    onImageUpload: function(files, editor, welEditable) {
						    sendFile(files[0], this);
						    
						}
					}
				});
			});
		</script>
		


</body>
</html>