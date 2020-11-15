<%@page import="suggestion.vo.SuggestionBean"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%

int count1 = 0;
int count2 = 0;
int count3 = 0;
int count4 = 0;

if(request.getAttribute("listCount1") != null 
	&& request.getAttribute("listCount2") != null
	&& request.getAttribute("listCount3") != null
	&& request.getAttribute("listCount4") != null) {
	
//----------현재 데이터 ---------------------------------
		count1 = (Integer)request.getAttribute("listCount1"); // 플레이스(명소) 소개 글 개수
		count2 = (Integer)request.getAttribute("listCount2"); // 리뷰 글 개수
		count3 = (Integer)request.getAttribute("listCount3"); // 상품 개수
		count4 = (Integer)request.getAttribute("listCount4"); // 총 회원수
}
//-----------목표치 설정--(### 목표치 증가시 여기서 값 변경 ####)--------------------
int goal1 = 40;   // 여행지(목표치 : 500)
int goal2 = 80;   // 리뷰와 피드백(목표치 : 1000)
int goal3 = 60;   // 여행 상품(목표치 : 500)
int goal4 = 20;   // 사용자(목표치 : 20,000)
//-----------------------------------------
float percent11 = 0;  // 여행지 퍼센트
float percent22 = 0;  // 리뷰와 피드백 퍼센트
float percent33 = 0;  // 여행상품 퍼센트
float percent44 = 0;  // 사용자 퍼센트

String percent1 = null; //소수점 포멧용 
String percent2 = null; //소수점 포멧용 
String percent3 = null; //소수점 포멧용  
String percent4 = null; //소수점 포멧용 

//----------목표치에 따른 현재 데이터 백분율 계산 ---------------
// 목표값 이상인경우 퍼센트 100으로 고정                         아니면       // 퍼센테이지 백분율 계산                                               //소수점 1자리만 남기기위해 소수점 포멧
if(count1 >= goal1) { percent11 = 100;} else { percent11 = (float)count1/goal1*100; percent1 = String.format("%.1f",percent11);}
if(count2 >= goal2) { percent22 = 100;} else { percent22 = (float)count2/goal2*100; percent2 = String.format("%.1f",percent22);}
if(count3 >= goal3) { percent33 = 100;} else { percent33 = (float)count3/goal3*100; percent3 = String.format("%.1f",percent33);}
if(count4 >= goal4) { percent44 = 100;} else { percent44 = (float)count4/goal4*100; percent4 = String.format("%.1f",percent44);}


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

#img1 {
	height: 520px;
}

#withtrip_img{
	width: 60%;
	margin-bottom: 16px;
}
</style>
</head>
<body>
	<jsp:include page="/inc/top.jsp" />
	<!--/header-->


    <div class="page-title" style="background-image: url(images/page-title.png)">
        <h1>About us</h1>
    </div>

    <section id="about-us">
        <div class="container">
            <div class="row">
                <div class="col-md-7">
                    <div class="about-img">
                        <img src="images/img11.png" alt="" id="img1">
                    </div>
                </div>
                <div class="col-md-5">
                    <div class="about-content">
                        <h2><img src="images/logo.png" id="withtrip_img"> 소개</h2>
                        <p> 
							 획기적인 부산 여행 플랫폼인 WITHTRIP은  축척된 데이터를 기반으로 
							사용자에게 부산의 여행명소, 맞춤 여행지, 상품 등을 추천하며
							매년 부산을 방문하는 2600만 명의 여행자를 도와 모든 여행이 최적의 여행이 되도록 합니다.<br><br>
							수많은 부산 여행자들은 WITHTRIP 사이트를 이용하여 다양한 명소와 맛집, 체험공간에 대한 리뷰와 의견을 검색합니다. <br><br>
							여행을 계획하든 여행 중이든 여행자들은 WITHTRIP의 도움을 받아 언제든 정보를 받아 볼 수 있고 저렴한 가격에 티켓을 구매할수 있습니다. 
							인기 투어와 관광명소를 예약하고, 훌륭한 음식점을 추천받으세요. 
							최고의 부산 여행 동반자 WITHTRIP은 언제나 함께 합니다.
                        </p>
                    </div>
                </div>
            </div>
        </div>
    </section>


    <section id="middle" class="skill-area" style="background-image: url(images/banner_bg.png)">
        <div class="container">
            <div class="row">
                <div class="col-sm-12 fadeInDown">
                    <div class="skill">
                        <h2>데이터 수집과 목표</h2>
                        <p>WithTrip 은 방대한 여행지, 상품에 대한 데이터를 수집하고 이를 사용자들에게 제공하는데 의의를 둡니다. <br> 사용자들에게 제공할 방대한 데이터들을 꾸준히 업데이트하고 지속적인 피드백을 통해 이를 정제하고 제공할것입니다. </p>
                    </div>
                </div>
                <!--/.col-sm-6-->
                <div class="col-sm-6">
                    <div class="progress-wrap">
                        <div class="progress">
                            <div class="progress-bar  color1" role="progressbar" aria-valuenow="40" aria-valuemin="0" aria-valuemax="100" style="width: <%=percent1%>%">
                                <span class="bar-width"><%=percent1%>%</span>
                            </div>
                        </div>
                        <h3>여행지(목표치 : <%=goal1 %>)</h3>
                    </div>

                    <div class="progress-wrap">
                        <div class="progress">
                            <div class="progress-bar color2" role="progressbar" aria-valuenow="20" aria-valuemin="0" aria-valuemax="100" style="width: <%=percent3%>%">
                                <span class="bar-width"><%=percent3%>%</span>
                            </div>
                        </div>
                        <h3>여행 상품(목표치 : <%=goal3 %>)</h3>
                    </div>
                </div>

                <div class="col-sm-6">
                    <div class="progress-wrap">
                        <div class="progress">
                            <div class="progress-bar color3" role="progressbar" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100" style="width: <%=percent2%>%">
                                <span class="bar-width"><%=percent2%>%</span>
                            </div>
                        </div>
                        <h3>리뷰와 피드백(목표치 : <%=goal2 %>)</h3>
                    </div>

                    <div class="progress-wrap">
                        <div class="progress">
                            <div class="progress-bar color4" role="progressbar" aria-valuenow="80" aria-valuemin="0" aria-valuemax="100" style="width: <%=percent4%>%">
                                <span class="bar-width"><%=percent4%>%</span>
                            </div>
                        </div>
                        <h3>사용자(목표치 : <%=goal4 %>)</h3>
                    </div>
                </div>
            </div>
            <!--/.row-->
        </div>
        <!--/.container-->
    </section>
    <!--/#middle-->
    
    <section id="team-area">
        <div class="container">
            <div class="center fadeInDown">
                <h2>개발자</h2>
<!--                 <p class="lead">블라블라 블라블라블라블라 블라블라 블라블라블라블라  라블라블라블라 블라블라 블라블라블라블라라블라블라블라 블라블라 블 <br> 라블라블라블라 블라블라 블라블라블라블라라블라블라블라 블라블라 블</p> -->
            </div>
            <div class="row">
                <div class="col-md-4 col-sm-6 single-team">
                    <div class="inner">
                        <div class="team-img">
                            <img src="images/team_4.png" alt="">
                        </div>
                        <div class="team-content">
                            <h4>김진영</h4>
                            <span class="desg">팀장</span>
                            <div class="team-social">
                                <a class="fa fa-facebook" href="#"></a>
                                <a class="fa fa-twitter" href="#"></a>
                                <a class="fa fa-linkedin" href="#"></a>
                                <a class="fa fa-pinterest" href="#"></a>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-md-4 col-sm-6 single-team">
                    <div class="inner">
                        <div class="team-img">
                            <img src="images/team_6.png" alt="">
                        </div>
                        <div class="team-content">
                            <h4>엄대정</h4>
                            <span class="desg">부팀장</span>
                            <div class="team-social">
                                <a class="fa fa-facebook" href="#"></a>
                                <a class="fa fa-twitter" href="#"></a>
                                <a class="fa fa-linkedin" href="#"></a>
                                <a class="fa fa-pinterest" href="#"></a>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-md-4 col-sm-6 single-team">
                    <div class="inner">
                        <div class="team-img">
                            <img src="images/team_7.png" alt="">
                        </div>
                        <div class="team-content">
                            <h4>배하나</h4>
                            <span class="desg">팀원</span>
                            <div class="team-social">
                                <a class="fa fa-facebook" href="#"></a>
                                <a class="fa fa-twitter" href="#"></a>
                                <a class="fa fa-linkedin" href="#"></a>
                                <a class="fa fa-pinterest" href="#"></a>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-md-4 col-sm-6 single-team">
                    <div class="inner">
                        <div class="team-img">
                            <img src="images/team_5.png" alt="">
                        </div>
                        <div class="team-content">
                            <h4>류종호</h4>
                            <span class="desg">팀원</span>
                            <div class="team-social">
                                <a class="fa fa-facebook" href="#"></a>
                                <a class="fa fa-twitter" href="#"></a>
                                <a class="fa fa-linkedin" href="#"></a>
                                <a class="fa fa-pinterest" href="#"></a>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-md-4 col-sm-6 single-team">
                    <div class="inner">
                        <div class="team-img">
                            <img src="images/team_3.png" alt="">
                        </div>
                        <div class="team-content">
                            <h4>김민건</h4>
                            <span class="desg">팀원</span>
                            <div class="team-social">
                                <a class="fa fa-facebook" href="#"></a>
                                <a class="fa fa-twitter" href="#"></a>
                                <a class="fa fa-linkedin" href="#"></a>
                                <a class="fa fa-pinterest" href="#"></a>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-md-4 col-sm-6 single-team">
                    <div class="inner">
                        <div class="team-img">
                            <img src="images/team_1.png" alt="">
                        </div>
                        <div class="team-content">
                            <h4>김나은</h4>
                            <span class="desg">팀원</span>
                            <div class="team-social">
                                <a class="fa fa-facebook" href="#"></a>
                                <a class="fa fa-twitter" href="#"></a>
                                <a class="fa fa-linkedin" href="#"></a>
                                <a class="fa fa-pinterest" href="#"></a>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-md-4 col-sm-6 single-team">
                    <div class="inner">
                        <div class="team-img">
                            <img src="images/team_2.png" alt="">
                        </div>
                        <div class="team-content">
                            <h4>김기일</h4>
                            <span class="desg">팀원</span>
                            <div class="team-social">
                                <a class="fa fa-facebook" href="#"></a>
                                <a class="fa fa-twitter" href="#"></a>
                                <a class="fa fa-linkedin" href="#"></a>
                                <a class="fa fa-pinterest" href="#"></a>
                            </div>
                        </div>
                    </div>
                </div>
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