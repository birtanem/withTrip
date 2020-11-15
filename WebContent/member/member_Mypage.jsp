<%@page import="member.vo.MemberBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
// String id = (String)session.getAttribute("id");
MemberBean memberInfo = (MemberBean)request.getAttribute("memberInfo");

//정부수정 완료후 -> 마이페이지이동 -> 수정완료 알람창 띄우기
String alertOn = "false";                             //alertOn 값 기본 false 설정
if(session.getAttribute("alertOn") != null){          //회원가입 완료후 페이지 이동시 alertOn에 세션 true 가져옴
	alertOn = (String)session.getAttribute("alertOn");
	}
if(alertOn.equals("true")){                           //alertOn 세션 true 일 경우 "회원가입 완료!" 알람 띄우기
	%>
	<script>
	alert("수정 완료!")
	</script>
	<%
	session.removeAttribute("alertOn");               //alertOn 세션 값 제거 
}

if(alertOn.equals("pass")){                           //alertOn 세션 true 일 경우 "회원가입 완료!" 알람 띄우기
	%>
	<script>
	alert("비밀번호 변경 완료!")
	</script>
	<%
	session.removeAttribute("alertOn");               //alertOn 세션 값 제거 
}

%>

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
<style type="text/css">

#welcom1 {
	text-align: initial;
    padding-left: 30px;
    font-size: 18px;
    font-style: oblique;
    font-weight: bold;
    color: brown;
}

#infoContainer {
	width: 350px;
}

#info {
	height: 180px;
}

#infoH{
	margin-top: 10px;
    font-size: 20px;
    font-family: initial;
    font-weight: bold;
    border-bottom-style: outset;
    width: 300px;
    padding-bottom: 4px;
}

#infocontent1 {
	display: inline-block;
    font-weight: 700;
    width: 70px;
    padding: 2px;
}

#infocontent {
    display: inline-block;
/*     margin-left: 30px; */
	font-size: 15px;
    margin-left: 15px;
    color: green;
}

#S_infocontent {
    display: inline-block;
/*     margin-left: 30px; */
/*     font-size: 14px; */
    margin-left: 15px;
    font-size: 12px;
    color: green;
}


#btndiv {
/* 	float: right; */
/*     margin-right: 15px; */
/*     margin-top: 85px; */
	float: left;
    margin-top: 50px;
    margin-top: 93px;
}

#infoChangeBtn {
	margin-right: 10px;
	background-color: #80808057;
    font-weight: bold;
    margin-top: 4px;
}

#passChangeBtn {
    margin-right: 10px;
	background-color: #80808057;
    font-weight: bold;
    margin-top: 4px;
}

#img {
	display: block;
    height: auto;
    max-width: 60%;
    margin-bottom: 30px;
}


#btnIcon1 {
	border: 
}

#btnIcon2 {

}

#btnIcon3 {

}

#btnIcon4 {

}
</style>
</head>
<body>
	<jsp:include page="/inc/top.jsp" />
	
    <div class="page-title" style="background-image: url(images/page-title.png)">
        <h1>마이페이지</h1>
    </div>

        <section id="services" class="service-item">
        <div class="container">
            <div class="center fadeInDown">
                <p id="welcom1">안녕하세요!  <%= memberInfo.getId() %> 님<br> </p>
                <p id="welcom1">With ♥ Trip 을 이용해 주셔서 감사합니다 !!</p>
            </div>

            <div class="row">

                <div class="col-sm-6 col-md-4">
                    <div class="media h_Mypage-wrap fadeInDown">
                        <div class="media-body">
                        	<h3 id="infoH">회원정보</h3>
                        		<div id="info">
			                	<span id="infocontent1">아이디</span><span id="infocontent"><%=memberInfo.getId() %></span><br>
								<span id="infocontent1">이름</span><span id="infocontent"><%=memberInfo.getName() %></span><br>
								<span id="infocontent1">나이</span><span id="infocontent"><%=memberInfo.getAge() %></span><br>
								<span id="infocontent1">성별</span><span id="infocontent"><%=memberInfo.getGender() %></span><br>
								<span id="infocontent1">선택타입</span><span id="infocontent"><%=memberInfo.getType() %></span><br>
								<span id="infocontent1">포인트</span><span id="infocontent"><%=memberInfo.getPoint() %></span><br>
								<span id="infocontent1">가입날짜</span><span id="S_infocontent"><%=memberInfo.getDate() %></span><br>
								</div>
							<div id="btndiv">
								<input type="button" value="회원정보 변경" onclick="location.href='UpdateForm.me?where=1'" id="infoChangeBtn">
								<input type="button" value="비밀번호 변경" onclick="location.href='UpdatePassForm.me?where=2'" id="passChangeBtn">
							</div>
                        </div>
                    </div>
                </div>
				
				
                <div class="col-sm-6 col-md-4">
                <a href="./MemberWriteList.me">
                    <div class="media mypage-wrap fadeInDown">
                        <div class="pull-left">
                            <img id="img" src="images/services/mypageIcon1.jpeg">
                        </div>
                            <h3 class="media-heading">나의 작성 글</h3>
                    </div>
				</a>
                </div>
                
                <div class="col-sm-6 col-md-4">
				<a href="./MemberReplyList.me">
                    <div class="media mypage-wrap fadeInDown">
                        <div class="pull-left">
                            <img id="img" src="images/services/mypageIcon2.jpeg">
                        </div>
                            <h3 class="media-heading">나의 작성 댓글</h3>
                    </div>
                </a>
                </div>
                
                <div class="col-sm-6 col-md-4">
				<a href="./MemberEventList.me">
                    <div class="media mypage-wrap fadeInDown">
                        <div class="pull-left">
                            <img id="img" src="images/services/mypageIcon4.jpeg">
                        </div>
                            <h3 class="media-heading">이벤트 내역</h3>
                    </div>
                </a>
                </div>
                
                <div class="col-sm-6 col-md-4">
				<a href="orderList.or">
                    <div class="media mypage-wrap fadeInDown">
                        <div class="pull-left">
                            <img id="img" src="images/services/mypageIcon3.jpeg">
                        </div>
                            <h3 class="media-heading">결제 내역</h3>
                    </div>
                </a>
                </div>

            </div>
            <!--/.row-->
        </div>
        <!--/.container-->
    </section>
    <!--/#services-->


    <footer id="footer" class="midnight-blue">
        <div class="container">
            <div class="row">
                <div class="col-sm-6">
                    &copy; 2013 <a target="_blank" href="http://shapebootstrap.net/" title="Free Twitter Bootstrap WordPress Themes and HTML templates">ShapeBootstrap</a>. All Rights Reserved.
                </div>
                <div class="col-sm-6">
                    <ul class="pull-right">
                        <li><a href="#">Home</a></li>
                        <li><a href="#">About Us</a></li>
                        <li><a href="#">Faq</a></li>
                        <li><a href="#">Contact Us</a></li>
                    </ul>
                </div>
            </div>
        </div>
    </footer>
    <!--/#footer-->

    <script src="js/jquery.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/jquery.prettyPhoto.js"></script>
    <script src="js/owl.carousel.min.js"></script>
    <script src="js/jquery.isotope.min.js"></script>
    <script src="js/main.js"></script>
</body>

</html>
