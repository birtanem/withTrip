<%@page import="suggestion.vo.SuggestionBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
SuggestionBean article = (SuggestionBean)request.getAttribute("article");
String showStyle = (String)request.getParameter("showStyle");
%>
<!DOCTYPE html>
<html lang="en">
<head>

<script type="text/javascript">
function checkValue(){
	var form = document.contentinfo;
	
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
    <title>건의사항 답변</title>

    <!-- core CSS -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/font-awesome.min.css" rel="stylesheet">
    <link href="css/animate.min.css" rel="stylesheet">
    <link href="css/prettyPhoto.css" rel="stylesheet">
    <link href="css/owl.carousel.min.css" rel="stylesheet">
    <link href="css/icomoon.css" rel="stylesheet">
    <link href="css/main.css" rel="stylesheet">
    <link href="css/responsive.css" rel="stylesheet">

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
	        <h1>건의사항 답글(관리자 페이지)</h1>
	    </div>

    
        <section id="contact-page">
        <div class="container">
            <div class="row contact-wrap"> 
                <div class="status alert alert-success" style="display: none"></div>
<!--                <form id="main-contact-form" class="contact-form" name="contact-form" method="post" action="sendemail.php">                     -->
                	<form action="Suggestion_ReplyPro.su?showStyle=<%=showStyle%>" method="post" name="contentinfo" style="margin-top:20px;" onsubmit="return checkValue()">
	                		<input type="hidden" value="<%= article.getNum()%>" name="num">
							<input type="hidden" value="<%= article.getId()%>" name="id">
							<input type="hidden" value="<%= article.getEmail()%>" name="email">
							<input type="hidden" value="<%= article.getSubject()%>" name="subject">
	                    <div class="col-sm-13 col-sm-offset-13">
	                        <div class="form-group">
	                        	<span id="span_Info">작성자 ID : </span><span id="span_Info2"><%= article.getId()%></span>
	                        </div>
	                        <div class="form-group">
	                       	    <span id="span_Info">작성자 Email : </span><span id="span_Info2"><%= article.getEmail()%></span>
	                        </div>
	                        <div class="form-group">
	                       	    <span id="span_Info">건의글 </span><span id="span_Info2">"<%= article.getSubject()%>" </span><span> 에 대한 답변입니다. </span><br><br>
	                        </div>
	                        
	                        <div class="form-group">
	                            <label>답변내용 *</label>
	                            <textarea name="content" id="content" required="required" class="form-control" rows="8"></textarea>
	                        </div>                        
	                        <div class="form-group">
	                            <button type="submit" name="submit" class="btn btn-primary btn-lg" required="required">답변완료</button>
	                            <button type="button" name="btn1" class="btn btn-primary btn-lg" id="btn_MySuggestion" onclick="location.href='adminSuggestion_List.su?showStyle=<%=showStyle%>'">목록</button>
	                            <button type="reset" name="btn1" class="btn btn-primary btn-lg" id="btn_Goback">취소</button>
	                        </div>
	                    </div>
                </form> 
            </div><!--/.row-->
        </div><!--/.container-->
    </section><!--/#contact-page-->
    
    
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
