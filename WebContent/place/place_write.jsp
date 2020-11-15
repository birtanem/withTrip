<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>WithTrip | AdminPage | PlaceWrite</title>
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
	<!-- <section id="writeForm"">
>>>>>>> refs/remotes/origin/master
 	<div class="blog container">
		<h1>장소 소개 글 등록</h1>
		<form action="PlaceWritePro.pl" method="post" enctype="multipart/form-data" name="placeForm">
			<table style="border : 1px solid">
			<tr><td>장소명 :</td><td> <input type="text" name="pl_name" required="required"/></td></tr>
			<tr><td>장소<br>소개<br>내용 </td><td><textarea id="summernote" name="pl_content" required="required" >소개글을 등록해주세요</textarea></td></tr>
			<tr><td>주소 : </td><td><input type="text" name="pl_address" required="required"/></td></tr>
			<tr><td>대표 사진 : </td><td><input type="file" name="pl_image" required="required"/></td></tr>
			<tr><td>주제 : </td><td><input type="text" name="pl_theme" required="required"/></td></tr>
			<tr><td>지역 : </td><td>
						<select name="region_code">
		            			<option value="0">지역 선택하세요</option>
		            			<option value="1">강서구</option>
		            			<option value="2">금정구</option>
		            			<option value="3">기장군</option>
		            			<option value="4">남구</option>
		            			<option value="5">동구</option>
		            			<option value="6">동래구</option>
		            			<option value="7">부산진구</option>
		            			<option value="8">북구</option>
		            			<option value="9">사상구</option>
		            			<option value="10">사하구</option>
		            			<option value="11">서구</option>
		            			<option value="12">수영구</option>
		            			<option value="13">연제구</option>
		            			<option value="14">영도구</option>
		            			<option value="15">중구</option>
		            			<option value="16">해운대구</option>
		            			<option value="17">기타지역(부산외)</option>
		                 	</select></td></tr>
				
				<tr><td colspan=2><input type="submit" value="등록">
								<input type="reset" value="다시쓰기"></td></tr>
			</table>
		</form>
		</div>
	</section> -->

 <section id="contact-page">
        <div class="container">
        <br>
            <div class="row contact-wrap"> 
                <div class="status alert alert-success" style="display: none"></div>
                <!-- <form id="main-contact-form" class="contact-form" name="contact-form" method="post" action="sendemail.php"> -->
                
                
                <form action="PlaceWritePro.pl" method="post" enctype="multipart/form-data" name="placeForm">
                    <div class="col-sm-offset-1">
                    <div class="col-sm-5">
                        <div class="form-group">
                            <label>장소명 : </label>
                            <input type="text" name="pl_name" class="form-control" required="required"/>
                        </div>
                        <div class="form-group">
                            <label>지역 : </label>
                            <select name="region_code" class="form-control" required="required">
		            			<option value="0">지역 선택하세요</option>
		            			<option value="1">강서구</option>
		            			<option value="2">금정구</option>
		            			<option value="3">기장군</option>
		            			<option value="4">남구</option>
		            			<option value="5">동구</option>
		            			<option value="6">동래구</option>
		            			<option value="7">부산진구</option>
		            			<option value="8">북구</option>
		            			<option value="9">사상구</option>
		            			<option value="10">사하구</option>
		            			<option value="11">서구</option>
		            			<option value="12">수영구</option>
		            			<option value="13">연제구</option>
		            			<option value="14">영도구</option>
		            			<option value="15">중구</option>
		            			<option value="16">해운대구</option>
		            			<option value="17">기타지역(부산외)</option>
		                 	</select>
                        </div>
                        </div>
                        
                        <div class="col-sm-5">
                        <div class="form-group">
                            <label>주제 : </label>
                            <select name="pl_theme" class="form-control" required="required">
				            	  <option value="관심사">관심사</option>
								   <option value="맛집">맛집</option>
								   <option value="체험">체험</option>
								   <option value="관광">관광</option>
								   <option value="역사">역사</option>
		                 	</select>
                        </div>
                        <div class="form-group">
                            <label>주소 : *</label>
                            <input type="text" name="pl_address" class="form-control" required="required"/>
                        </div>   
                        </div>                     
                         
                        
                     
                      <div class="col-sm-10 ">
                      <div class="form-group ">
                            <label>대표 사진 : </label>
                            <input type="file" name="pl_image" class="form-control" required="required"/>
                        </div>
                            <label>장소 소개 글</label>
                            <textarea id="summernote" name="pl_content" required="required" >소개글을 등록해주세요</textarea>
                            
                            <div class="form-group">
                            <button type="submit" name="submit" class="btn btn-primary btn-lg" >소개 글 등록</button>
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