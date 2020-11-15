<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
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


    <link rel="shortcut icon" href="images/ico/favicon.ico">
    <link rel="apple-touch-icon-precomposed" sizes="144x144" href="images/ico/apple-touch-icon-144-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="114x114" href="images/ico/apple-touch-icon-114-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="72x72" href="images/ico/apple-touch-icon-72-precomposed.png">
    <link rel="apple-touch-icon-precomposed" href="images/ico/apple-touch-icon-57-precomposed.png">
    
    <script src="js/jquery-3.5.0.js"></script>


<style type="text/css">
#loginWrap {
	min-height: 100%;
	position: relative;
}
/* Full-width inputs */
.loginInput {

  width: 100%;
  padding: 12px 20px;
  margin: 8px 0;
  display: inline-block;
  border: 1px solid #ccc;
  box-sizing: border-box;
}

/* Set a style for all buttons */
#loginBtn {
  background-color: black;
  color: white;
  padding: 14px 20px;
  margin: 40px 0;
  border: none;
  cursor: pointer;
  width: 100%;
}

/* Add a hover effect for buttons */
#loginBtn:hover {
  opacity: 0.8;
}


/* Add padding to containers */
.loginContainer {
	position: absolute;
	top: 50%;
	left: 50%;
  padding: 16px;
  width: 450px;
  height: 300px;
  margin: -70px 0 0 -225px;
  text-align: center;
}
.loginContainer label {
 	width: 100%;
	text-align: left;
	margin-top: 20px;
}
.loginContainer span {
	display: block;
	text-align: left;
	color: red;
}



/* The "Forgot password" text */
span.psw {
  float: right;
  padding-top: 16px;
}

/* Change styles for span and cancel button on extra small screens */
@media screen and (max-width: 300px) {
  span.psw {
    display: block;
    float: none;
  }

}
#footer {
	position: absolute;
	bottom: 0;
	width: 100%;
}
</style>
<%
//회원가입 완료후 -> 로그인폼 페이지이동 -> 회원가입완료 알람창 띄우기
String alertOn = "false";                             //alertOn 값 기본 false 설정
if(session.getAttribute("alertOn") != null){          //회원가입 완료후 페이지 이동시 alertOn에 세션 true 가져옴
	alertOn = (String)session.getAttribute("alertOn");
	}
if(alertOn.equals("true")){                           //alertOn 세션 true 일 경우 "회원가입 완료!" 알람 띄우기
	%>
	<script>
	alert("회원가입 완료!")
	</script>
	<%
	session.removeAttribute("alertOn");               //alertOn 세션 값 제거 
}
%>
<script type="text/javascript">

function idCheck() {
	
	if(loginForm.id.value =="") {
		$("#idChk").html("필수 입력")
		return false;
	}else {
		return true;
	}
	
	
}
function passCheck() {
	if(loginForm.pass.value=="") {
		$("#passChk").html("필수 입력")
		return false;
	}else{
		
		return true;
	}
	

	
}
function loginCheck() {
	
	if(idCheck() && passCheck()) {
		
		var formData = $("form[name=loginForm]").serialize() ;

		$.ajax({
            type : 'post',
            url : 'MemberLoginPro.me',
            data : formData,
            success : function(re){
        	
            	var rdata = re.trim();
            	
            	$(".loginContainer span").html("")
            	
				if(rdata == "1") {
					location.href = "main.co"
				}else if(rdata == "-1") {
					$("#passChk").html("비밀번호 불일치")
				}else {
					$("#idChk").html("아이디 불일치")
				}
            },
            error: function(xhr, status, error){
                alert(error);
            }
        });
		
	}else{
		alert("로그인 실패")
		return false;
	}
}
</script>
</head>
<body>
<div id="loginWrap">
<jsp:include page="../inc/top.jsp"></jsp:include>

    <div class="page-title" style="background-image: url(images/page-title.png)">
        <h1>로그인</h1>
    </div>

    <section id="contact-page" style="height: 700px;">
	
		<form action="MemberLoginPro.me" method="post" name="loginForm">


  <div class="loginContainer">
    <label for="id"><b>아이디</b></label><br>
    <input class="loginInput" type="text" placeholder="아이디를 입력하세요" name="id" ><br>
	<span id="idChk"></span>
    <label for="pass"><b>비밀번호</b></label><br>
    <input class="loginInput" type="password" placeholder="비밀번호를 입력하세요" name="pass"><br>
	<span id="passChk"></span>
    <input type="button" value="로그인" id="loginBtn" onclick="return loginCheck()"><br>

    <a style="color: black;" href="#" onclick="alert('안해요~')">아이디찾기</a> ㅣ
 	 <a style="color: black;" href="#" onclick="alert('안해요~')">비밀번호찾기</a> ㅣ
 	 <a style="color: black;" href="MemberJoinForm.me">회원가입</a>
 
  </div>


</form>

    </section>
    <!--/#bottom-->
	<jsp:include page="../inc/bottom.jsp"></jsp:include>
    <!--/#footer-->
</div>

    <script src="js/bootstrap.min.js"></script>
    <script src="js/jquery.prettyPhoto.js"></script>
    <script src="js/owl.carousel.min.js"></script>
    <script src="js/jquery.isotope.min.js"></script>
    <script src="js/main.js"></script>

</body>
</html>