<%@page import="java.util.ArrayList"%>
<%@page import="suggestion.vo.SuggestionBean"%>
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
    
    <link href="css/order.css" rel="stylesheet">
    
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
/* #table1 { */
/* 	color: black; */
/* 	border-style: solid; */
/* } */

/* /* 테이블 td조정 */ */
/* #td0 { */
/* 	width: 56px; */
/* 	min-width: 56px; */
/* } */

/* #td_title { */
/* 	color: black; */
/*     width: 440px; */
/*     font-weight: bold; */
/* } */

/* #td1 { */
/* 	width: 50px; */
/* } */

/* #td2 { */
/* 	width: 320px; */
/*     text-align: end; */
/*     padding-right: 20px; */
/*     min-width: 140px; */
/* } */

/* #td4 { */
/* 	width: 88px; */
/* 	color: blue; */
/* 	min-width: 60px; */
/* } */

/* #td5 { */
/* 	width: 88px; */
/* 	color: red; */
/* 	min-width: 60px; */
/* } */

/* #spanCount1{ */
/* 	font-size: 23px; */
/* } */

/* #spanCount2{ */
/* 	font-size: 23px; */
/*     font-weight: bold; */
/*     color: black; */
/* } */

/* #btn1 { */
/*  float: right; */
/*  background-color: gray;  */
/*  color: white; */
/* } */

/* #span1 { */
/* 	color: gray; */
/*     text-align: right; */
/*     font-size: 8px; */
/*     margin-left: 367px; */
/* } */


#table1 {
	color: black;
}

/* 테이블 td조정 */

#td_title {
	color: blue;
}

#td1 {
	width: 68px;
}

#td2 {
	width: 78px;
}

#td3 {
	width: 95px;
}

#td4 {
	width: 110px;
	font-weight: bold;
	color: grey;
}

#spanCount1{
	font-size: 23px;
}

#spanCount2{
	font-size: 23px;
    font-weight: bold;
    color: black;
}

#btnBack{
	float: right;
    margin-right: 5px;
}

#red {
	color: red;
}

#blue {
	color: blue;
}

#blog {
	min-height: 537px;
}


</style>
</head>
<body>
	<jsp:include page="/inc/top.jsp" />
	<!--/header-->
	
    <div class="page-title" style="background-image: url(images/page-title.png)">
        <h1>내 건의사항</h1>
    </div>
      
    <section id="blog">
        <div class="blog container">
            <div class="row">
                <div class="col-sm-10">
                
                	<div class="blog-item">
                        <div class="blog-content">
	                    	<div>
	                    		<span id="spanCount1">나의 건의사항 </span>
	                    		<input type="button" value="건의하러가기" id="btnBack" onclick="location.href='Suggestion_WriteForm.su'">
	                    	</div>        		   
                        </div>
                    </div>			
					
				<div class="blog-item">
				<div class="o_list" style="clear: both;">
					<table class="ot_list">
						<tr>
							<th>제목</th>
							<th style="padding-right: 20px;">작성일</th>
							<th style="padding-right: 20px;">답변여부</th>
						</tr>
								<c:forEach var="article" items="${articleList }" >
								<tr>
									<td><a href="Suggestion_Detail.su?su_num=${article.num }">${article.subject }</a></td>
									<td>${article.date }</td>
									<c:if test="${article.check eq '답변완료'}">
									<td style="padding-right: 20px;" id="blue">${article.check }</td>
									</c:if>
									<c:if test="${article.check eq '미완료'}">
									<td style="padding-right: 20px;" id="red">${article.check }</td>
									</c:if>
									<c:if test="${article.check eq '답변글'}">
									<td style="padding-right: 20px;" id="red">${article.check }</td>
									</c:if>
								</tr>
								</c:forEach>	
					</table>          
				</div>
				</div>					
                </div>
                <!--/.col-md-8-->

            </div>  
        </div>
    </section>    
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