<%@page import="suggestion.vo.SuggestionBean"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
SuggestionBean article = (SuggestionBean)request.getAttribute("article");
int su_num  = Integer.parseInt(request.getParameter("su_num"));
String id = (String)request.getAttribute("id");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>Home | With Trip</title>

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

#span_Info3 {
	display: inline-block;
    color: black;
    font-family: none;
    font-weight: bold;
    color: blue;
}

#span_Info4 {
	display: inline-block;
    color: black;
    font-family: none;
    font-weight: bold;
    color: red;
}

#btn_Goback {
	margin-left: 10px;
}

#btn_MySuggestion {
	float: right;
	background-color: dimgray;
}

#content {
	height: 260px;
}
</style>
</head>
<body>
	<jsp:include page="/inc/top.jsp" />
	<!--/header-->


    <div class="page-title" style="background-image: url(images/page-title.png)">
        <h1>내 건의사항</h1>
    </div>

    <section id="contact-page">
        <div class="container">
            <div class="row contact-wrap"> 
                <div class="status alert alert-success" style="display: none"></div>
<!--                <form id="main-contact-form" class="contact-form" name="contact-form" method="post" action="sendemail.php">                     -->
                	<form action="Suggestion_WritePro.su" method="post" name="contentinfo" style="margin-top:20px;" onsubmit="return checkValue()">
	                    <div class="col-sm-13 col-sm-offset-13">
	                        <div class="form-group">
	                        	<span id="span_Info">작성자 ID </span><span id="span_Info2"><%=article.getId()%></span>
	                        </div>
	                        <div class="form-group">
	                       	    <span id="span_Info">작성자 Email </span><span id="span_Info2"><%=article.getEmail()%></span>
	                        </div>
	                        <%
	                        if(article.getCheck().equals("답변완료")){
	                        	%>
	                        	<div class="form-group">
	                       	    <span id="span_Info">답변 여부 </span><span id="span_Info3"><%=article.getCheck()%></span>
	                       		 </div>
		                        <div class="form-group">
		                       	    <span id="span_Info">답변 날짜 </span><span id="span_Info2"><%=article.getDate_r()%></span><br><br>
		                        </div>
	                        	<%
	                        } else {
	                        	%>
	                        	<div class="form-group">
	                       	    <span id="span_Info">답변 여부 </span><span id="span_Info4"><%=article.getCheck()%></span>
	                       		</div>
	                        	<%
	                        }
	                        %>
	                        <div class="form-group">
	                            <label>제목 *</label>
	                            <input type="text" readonly="readonly" class="form-control" value="<%=article.getSubject() %>">
	                        </div>
	                        <div class="form-group">
	                            <label>건의내용 *</label>
	                            <input type="text" id="content" readonly="readonly" class="form-control" height="500px" value="<%=article.getContent()%>">
	                        </div>
	                        <%
	                        if(article.getCheck().equals("답변완료")){
	                       	    %>
		                        <div class="form-group">
		                            <label>답변내용 *</label>
		                            <input type="text" id="content" readonly="readonly" class="form-control" height="500px" value="<%=article.getContent_r()%>">
		                        </div>      
	                        	<%
	                        }
	                        %>
	                        <div class="form-group">
	                            <button type="button" class="btn btn-primary btn-lg" onclick="location.href='Suggestion_WriteForm.su'">건의하러가기</button>
	                            <button type="button" name="btn1" class="btn btn-primary btn-lg" id="btn_MySuggestion" onclick="location.href='Suggestion_List.su'">목록</button>
	                        </div>
	                    </div>
                </form> 
            </div><!--/.row-->
        </div><!--/.container-->
    </section><!--/#contact-page-->
    <!--/#bottom-->
    <!--/#blog-->

    <!--/#bottom-->
<jsp:include page="/inc/bottom.jsp"/>
    <!--/#footer-->
    
    <script src="../js/jquery.js"></script>
    <script src="../js/bootstrap.min.js"></script>
    <script src="../js/jquery.prettyPhoto.js"></script>
    <script src="../js/owl.carousel.min.js"></script>
    <script src="../js/jquery.isotope.min.js"></script>
    <script src="../js/main.js"></script>
</body>
</html>