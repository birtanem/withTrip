<%@page import="java.util.ArrayList"%>
<%@page import="suggestion.vo.SuggestionBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
// String id = (String)session.getAttribute("id");
ArrayList<SuggestionBean> articleList=(ArrayList<SuggestionBean>)request.getAttribute("articleList");
String showStyle = (String)request.getAttribute("showStyle");

int count1 = (Integer)request.getAttribute("listCount1"); //전체글갯수 가져오기
int count2 = (Integer)request.getAttribute("listCount2"); //미완료 글갯수 가져오기


if(showStyle == null){ //보기타입 
	showStyle = "전체";	
}
String showAll = "전체";
String showNo = "미완료";
%>
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

		  $(".nav1").addClass("active"); 
	   });
   </script>
   
<style type="text/css">
#blog {
	min-height: 537px;
}
</style>
</head>
<body>
	<jsp:include page="/inc/top.jsp" />
	<!--/header-->
	
    <div class="page-title" style="background-image: url(images/page-title.png)">
        <h1>건의사항 리스트(관리자페이지)</h1>
    </div>

    <section id="blog">
        <div class="blog container">
            <div class="row">
                <div class="col-md-10">
                                	<div class="blog-item">
                        <div class="blog-content">
								<div style="width: 610px; text-align: end; font-size: 18px; color: black;">
								<label>전체글 </label>
								<label style="width: 25px; "><%=count1 %></label><br>
								<label>미완료 </label>
								<label style="width: 25px; color: red;"><%=count2 %></label>
								</div>
								<%
								if(showStyle.equals("전체")){
									%>
									<div style="width: 600px; margin-bottom: 40px; margin-top: 5px;">
										<input type="button" value="전체글보기" style="float: left; background-color: red; color: white;" onclick="location.href='adminSuggestion_List.su?showStyle=<%=showAll%>'">
										<input type="button" value="미완료 글보기" style="float: left; background-color: gray; color: white; margin-left: 9px;" onclick="location.href='adminSuggestion_List.su?showStyle=<%=showNo%>'">
									</div>
									<%
								} else if(showStyle.equals("미완료")){
									%>
									<div style="width: 600px; margin-bottom: 40px; margin-top: 5px;">
										<input type="button" value="전체글보기" style="float: left; background-color: gray; color: white;" onclick="location.href='adminSuggestion_List.su?showStyle=<%=showAll%>'">
										<input type="button" value="미완료 글보기" style="float: left; background-color: red; color: white; margin-left: 9px;" onclick="location.href='adminSuggestion_List.su?showStyle=<%=showNo%>'">
									</div>
									<%
								}
								%>
							<table border="1" style="width: 610px; position: relative; background-color: #d8d8d8;">
								<tr style="background-color: grey; border:thick;"><th style="width: 300px;">제목</th><th style="width: 180px;">작성일</th><th>작성자</th><th style="width: 150px;">답변여부</th></tr>
								<%
								for(int i=0;i<articleList.size();i++){
								%>
								<tr>
								<td>
								<a href="Suggestion_Detail.su?su_num=<%=articleList.get(i).getNum()%>&&showStyle=<%=showStyle%>">
								<%=articleList.get(i).getSubject() %>
								</a>
								</td>
								<td><%=articleList.get(i).getDate() %></td>
								<td style="width: 120px;"><%=articleList.get(i).getId() %></td>
								<%
								if(articleList.get(i).getCheck().equals("답변완료")){
									%>
									<td id="td1" style="color: blue; font-weight: bold; text-shadow: 0.5px 0.5px 0px white;"><%=articleList.get(i).getCheck() %></td>
									<%
									
								} else {
									%>
									<td id="td1" style="color: red; font-weight: bold; "><%=articleList.get(i).getCheck() %></td>
									<%
								}
								%>

								</tr>
								<%
								}
								%>
							</table>
							</div>
							</div>
					</div>
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