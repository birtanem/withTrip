<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


        <header id="header">
        <div class="top-bar">
            <div class="container">
                <div class="row">
                    <div class="col-sm-6 col-xs-12">
                        <div class="top-number">
                            <p><i class="fa fa-phone-square"></i> +051 123 4567</p>
                        </div>
                    </div>
                    <div class="col-sm-6 col-xs-12">
                        <div class="social">
                            <ul class="social-share">
                                <li><a href="#"><i class="fa fa-facebook"></i></a></li>
                                <li><a href="#"><i class="fa fa-twitter"></i></a></li>
                                <li><a href="#"><i class="fa fa-linkedin"></i></a></li>
                                <li><a href="#"><i class="fa fa-dribbble"></i></a></li>
                                <li><a href="#"><i class="fa fa-skype"></i></a></li>
                            </ul>
<!--                             <div class="search"> -->
<!--                                 <form role="form"> -->
<!--                                     <input type="text" class="search-form" autocomplete="off" placeholder="Search"> -->
<!--                                     <i class="fa fa-search"></i> -->
<!--                                 </form> -->
<!--                             </div> -->
                        </div>
                    </div>
                    <div class="col-sm-6 col-xs-12">
                    	<div class="login">
                    	<section id="login">
	<!-- 세션에 저장된 ID 가 있을 경우 "로그아웃"(MemberLogout.me) 페이지 링크 -->
	<!-- 세션에 저장된 ID 가 없을 경우 "로그인"(MemberLoginForm.me) 페이지 링크
	해야한다. -->

	<c:choose>
	    <c:when test="${empty sessionScope.id}">
	    	<a href="MemberLoginForm.me">로그인</a> | 
			<a href="MemberJoinForm.me">회원가입</a>
	    </c:when>
	
	    <c:otherwise>
	        <span style="color: white;">${sessionScope.id}&nbsp;&nbsp;님</span> |
			<a href="MemberMypage.me">마이페이지</a> | 
			<a href="MemberLogout.me">로그아웃</a>
	    </c:otherwise>
	</c:choose>
	

                        
                        

	</section>
                    	
                    	</div>
                    </div>
                </div>
            </div>
            <!--/.container-->
        </div>
        <!--/.top-bar-->

        <nav class="navbar navbar-inverse" role="banner">
            <div class="container">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a class="navbar-brand" href="main.co?#main-map"><img src="images/logo.png" alt="logo" width="150"></a>
                    
                </div>

                <div class="collapse navbar-collapse navbar-right">
                    <ul class="nav navbar-nav">
                    <!-- 관리자 'admin' 일 경우 관리자 메뉴 노출 -->
                    
						<c:if test="${sessionScope.id == 'admin'}">	 	
						<li class="dropdown nav1">
						        <a href="#" class="dropdown-toggle" data-toggle="dropdown">관리자 메뉴 <i class="fa fa-angle-down"></i></a>
						        <ol class="dropdown-menu">
<!-- 						            <li><a href="adminPage.ad">관리자 페이지</a></li> -->
						            <li><a href="MemberList.me">회원 관리</a></li>
						            <li><a href="PlaceWriteForm.pl?">여행지 등록</a></li>
						            
						            <li><a href="adminProduct.ad">상품 관리</a></li>
						            <li><a href="adminSuggestion_List.su">문의 관리</a></li>
						            
		
						            <li><a href="adminEvent.ad">이벤트 내역</a></li>
						            <li><a href="adminLog.ad">통계</a></li>
						        </ol>
						    </li>
						</c:if>
                        <li class="nav2"><a href="index.html">메인</a></li>
                        <li class="nav3"><a href="PlaceList.pl?check=2">여행지</a></li>
                        <li class="nav4"><a href="Review_List.re">후기게시판</a></li>
                        <li class="nav5"><a href="productList.pr">여행상품</a></li>
                        <li class="nav6"><a href="Suggestion_Menu.su">고객센터</a></li>
                        <li class="nav7"><a href="event.ev">이벤트</a></li>
                    </ul>
                </div>
            </div>
            <!--/.container-->
        </nav>
        <!--/nav-->

    </header>