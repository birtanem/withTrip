<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String id = null;
String email = null;
if(session.getAttribute("id") != null){
	id = (String)session.getAttribute("id");
	email = (String)request.getAttribute("email");
}
%>
<!DOCTYPE html>
<html lang="en">
<head>

<script type="text/javascript">
function checkValue(){
	var form = document.contentinfo;
	
	if(!form.subject.value){
		alert("제목을 입력하세요.");
		return false;
	}
	
	if(!form.content.value){
		alert("내용을 입력하세요.");
		return false;
	}
}
</script>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>건의하기</title>

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
    <link rel="shortcut icon" href="images/ico/favicon.ico">
    <link rel="apple-touch-icon-precomposed" sizes="144x144" href="images/ico/apple-touch-icon-144-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="114x114" href="images/ico/apple-touch-icon-114-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="72x72" href="images/ico/apple-touch-icon-72-precomposed.png">
    <link rel="apple-touch-icon-precomposed" href="images/ico/apple-touch-icon-57-precomposed.png">
    
            <script src="js/jquery-3.5.0.js"></script>
    
        <script type="text/javascript">
   		// 메뉴 액티브
	   $(document).ready(function() {
		  $(".nav6").addClass("active"); 
	   });
   </script>
   

<style type="text/css">

#span_Info {
	display: inline-block;
    width: 113px;
    color: gray;
    font-weight: bold;
}

#span_Info2 {
	display: inline-block;
    color: black;
    font-family: none;
    font-weight: bold;
    color: green;
}

#btn_Goback {
	margin-left: 10px;
}

#btn_MySuggestion {
	float: right;
	background-color: dimgray;
}

</style>
</head>
<body>
	<jsp:include page="/inc/top.jsp" />
	
    <div class="page-title" style="background-image: url(images/page-title.png)">
        <h1>건의하기</h1>
    </div>
<!--     ------------------------------------------------------------------------------------------------------ -->
    <section id="contact-page">
        <div class="container">
            <div class="large-title text-center">        
                <h2>WithTrip 을 사랑해주셔서 감사합니다!!</h2>
                <p>당신의 톡톡 튀는 의견을 자유롭게 건의해주세요!</p>
            </div> 
            <div class="row contact-wrap"> 
                <div class="status alert alert-success" style="display: none"></div>
<!--                <form id="main-contact-form" class="contact-form" name="contact-form" method="post" action="sendemail.php">                     -->
                	<form action="Suggestion_WritePro.su" method="post" name="contentinfo" style="margin-top:20px;" onsubmit="return checkValue()">
	                		<input type="hidden" value="<%= id%>" name="id">
							<input type="hidden" value="<%= email%>" name="email">
	                    <div class="col-sm-13 col-sm-offset-13">
	                        <div class="form-group">
	                        	<span id="span_Info">작성자 ID </span><span id="span_Info2"><%= id%></span>
	                        </div>
	                        <div class="form-group">
	                       	    <span id="span_Info">작성자 Email </span><span id="span_Info2"><%= email%></span><br><br>
	                        </div>
	                        
	                        <div class="form-group">
	                            <label>제목 *</label>
	                            <input type="text" name="subject" id="subject" class="form-control" required="required">
	                        </div>
	                        <div class="form-group">
	                            <label>건의내용 *</label>
	                            <textarea name="content" id="content" required="required" class="form-control" rows="8"></textarea>
	                        </div>                        
	                        <div class="form-group">
	                            <button type="submit" name="submit" class="btn btn-primary btn-lg" required="required">건의하기</button>
	                            <button type="button" name="btn1" class="btn btn-primary btn-lg" id="btn_MySuggestion" onclick="location.href='Suggestion_List.su'">내 건의사항</button>
	                            <button type="reset" name="btn1" class="btn btn-primary btn-lg" id="btn_Goback">취소</button>
	                        </div>
	                    </div>
                </form> 
            </div><!--/.row-->
        </div><!--/.container-->
    </section><!--/#contact-page-->
<!--     ------------------------------------------------------------------------------------------------------ -->

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
