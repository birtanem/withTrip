<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    
    /* summernote에서 이미지 업로드시 실행할 함수 */
    function sendFile(file, editor) {
        // 파일 전송을 위한 폼생성
        data = new FormData();
        data.append("uploadFile", file);
        $.ajax({ // ajax를 통해 파일 업로드 처리
            data : data,
            type : "POST",
            url : "ImageCallback.re",
            cache : false,
            contentType : false,
            processData : false,
            success : function(data) { // 처리가 성공할 경우
                // 에디터에 이미지 출력
                $(editor).summernote('editor.insertImage', data.url);
            }
        });
    }
    
    function regionCheck(){
    	
        if ($("#r_code option:selected").val() == "0") {
            alert("지역 선택해주세요!");
            return false;
        }else {
            return true;
        }
    }
    
    function subjectCheck(){
    	
        if ($("#r_subject").val() == "") {
            alert("제목 입력해주세요!");
            return false;
        }else {
            return true;
        }
    }
    
    function contentCheck(){
    	
        if ($(".r_content").val() == "") {
            alert("내용 입력해주세요!");
            return false;
        }else {
            return true;
        }
    }
    
    function writeCheck(){
    	
        if (regionCheck() &&
        	subjectCheck() &&
        	contentCheck()) {
            
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
        <h1>후기작성</h1>
    </div>
    
	<section id="blog">
		
		 <div class="container">
            <div class="row contact-wrap"> 
                <div class="status alert alert-success" style="display: none"></div>
                
                <form action="Review_WritePro.re" name="writeForm" enctype="multipart/form-data" method="post" onsubmit="return writeCheck()">
                    <div class="col-sm-offset-1">
                    <div class="col-sm-5">
                        <div class="form-group">
                        
                            <label>작성자 : </label>
                            <input type="text" name="r_id" value="${sessionScope.id }" class="form-control" readonly/>
                        </div>

                        </div>
                        
                        <div class="col-sm-5">
                        <div class="form-group">
                        <label>지역 : </label>
                            <select id="r_code" name="r_code" class="form-control">
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
                      <div class="col-sm-10 ">
                      <div class="form-group ">
                            <label>주제 : </label>
                            <input type="text" id="r_subject" name="r_subject" class="form-control" required/>
                        </div>
                            <label>글 내용</label>
                            <textarea id="summernote" name="r_content" class="r_content" required>글 등록해주세요</textarea>
                            
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
                    callbacks: { // 콜백을 사용
                        // 이미지를 업로드할 경우 이벤트를 발생
                        onImageUpload: function(files, editor, welEditable) {
                            sendFile(files[0], this);
                            
                        }
                    }
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