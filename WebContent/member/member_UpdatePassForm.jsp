<%@page import="member.vo.MemberBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
//String id = (String)session.getAttribute("id");
MemberBean memberInfo = (MemberBean)request.getAttribute("memberInfo");

%>
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
body {font-family: Arial, Helvetica, sans-serif;}
* {box-sizing: border-box}

/* Full-width input fields */
.joinContainer input, .joinContainer select {
  width: 100%;
  padding: 15px;
  margin: 5px 0 3px 0;
  display: inline-block;
  border: none;
  background: #f1f1f1;
}

.joinContainer input:focus {
  background-color: #ddd;
  outline: none;
}

hr {
  border: 1px solid #f1f1f1;
  margin-bottom: 25px;
}

/* Set a style for all buttons */
button {
  background-color: black;
  color: white;
  padding: 14px 20px;
  margin: 50px 0;
  border: none;
  cursor: pointer;
  width: 100%;
  opacity: 0.9;
}

button:hover {
  opacity:0.8;
}

/* Extra styles for the cancel button */
.cancelbtn {
  padding: 14px 20px;
  background-color: gray;
}

/* Float cancel and signup buttons and add an equal width */
.cancelbtn, .signupbtn {
  float: left;
  width: 50%;
}
/* Add padding to container elements */
.joinContainer {

  	position: absolute;
	top: 50%;
	left: 50%;
  padding: 16px;
  width: 450px;
  height: 1200px;
  margin: -500px 0 0 -225px;
  text-align: center;

}
.joinContainer label {
	text-align: left;
	width: 100%;
	margin-top: 20px;
	
}
.joinContainer span {
	display: block;
	text-align: left;
	color: red;
}

#div1 {
	text-align: end;
    color: gray;
    font-size: 11px;
    float: right;
}

/* Clear floats */
.clearfix::after {
  content: "";
  clear: both;
  display: table;
}

/* Change styles for cancel button and signup button on extra small screens */
@media screen and (max-width: 300px) {
  .cancelbtn, .signupbtn {
     width: 100%;
  }
}
#footer {
	position: absolute;
	bottom: 0;
	width: 100%;
}
</style>

<script type="text/javascript">

function goback() {
	if(confirm("이전 페이지로 돌아가시겠습니까?")){
		history.back();
	}
}

function nowPassCheck() {
	if(joinForm.nowPass.value=="") {
		$("#nowPass").focus();
		alert("현재 비밀번호를 입력해주세요");
		return false;
	}else {
		
		if(joinForm.nowPass.value==<%=memberInfo.getPass()%>) {
			return true;
		}else{
			if(joinForm.num.value == "1"){  //비밀번호 3회 실패시 마이페이지로 돌리기
				$("#num").attr("value", "2");
				$("#nowPass").focus();
				var num = $('#num').val();
				alert("현재 비밀번호가 틀립니다!\n" + num + "회  실패 ( ※ 3회 실패 시 이전 페이지로 돌아갑니다)");
				return false
			} else if(joinForm.num.value == "2"){
				$("#num").attr("value", "3");
				var num = $('#num').val();
				alert("현재 비밀번호가 틀립니다!\n" + num + "회  실패 ( ※ 3회 실패 시 이전 페이지로 돌아갑니다)\n");
				location.href="MemberMypage.me";
			}  else if(joinForm.num.value == "0"){
				$("#num").attr("value", "1");
				$("#nowPass").focus();
				var num = $('#num').val();
				alert("현재 비밀번호가 틀립니다!\n" + num + "회  실패 ( ※ 3회 실패 시 이전 페이지로 돌아갑니다)");
				return false
			}
		}	
	}
}

function passCheck() {
	if(joinForm.pass.value=="") {
		$("#pass").focus();
		alert("변경할 비밀번호를 입력해주세요");
		return false;
	}else{
		if(joinForm.passCheck.value == "denied"){
			$("#pass").focus();
			alert("올바른 비밀번호를를 입력해주세요")
			return false;
		} else {
			return true;
		}
	}	
}
function repassCheck() {
	if(joinForm.repass.value != joinForm.pass.value) {
		$("#repass").focus();
		alert("비밀번호 확인 오류!");
		return false;
	}else{
		
		return true;
	}	
}

function joinCheck() {
	if(nowPassCheck() &&
	   passCheck() &&
	   repassCheck()) {
		
// 		var formData = $("form[name=joinForm]").serialize() ;
//     index에서 띄워줘야하는데 alert 하나때문에 action페이지 하나 생성해야해서 일단 여기서 처리
	   if(confirm("비밀번호 변경을 완료하시겠습니까?")){
		   return true;
	   } else {
		   return false;
	   }
		
	}else{
	
		return false;
	}
}
</script>
</head>
<body>
<div id="loginWrap">
<jsp:include page="../inc/top.jsp"></jsp:include>

    <div class="page-title" style="background-image: url(images/page-title.png)">
        <h1>비밀번호 변경</h1>
    </div>

    <section id="contact-page" style="height: 1500px;">

<form action="MemberUpdatePro.me?where=2"method="POST" name="joinForm" onsubmit="return joinCheck()">
  <div class="joinContainer">
  	
  	<input type="hidden" value="0" id="num" name="num">
	<input type="hidden" value="<%=memberInfo.getId()%>" id="nowId" name="nowId">
	 	
    <label for="nowPass"><b>현재 비밀번호</b></label>
    <input type="password" placeholder="현재 비밀번호를 입력하세요" name="nowPass" id="nowPass">
    <span id="nowPass"></span>
    
    <label for="pass"><b>비밀번호</b></label>
    <input type="password" placeholder="비밀번호를 입력하세요" name="pass" id="pass">
    <span id="passChk"></span>
    
    <label for="repass"><b>비밀번호 재확인</b></label>
    <input type="password" placeholder="비밀번호를 입력하세요" name="repass" id="repass">
    <span id="repassChk"></span>

    <div class="clearfix">
      <button type="submit" class="signupbtn">변경 완료</button>
      <button type="button" class="cancelbtn" onclick="goback()">돌아가기</button>
<!-- 원래 판별용 하나로 하려고 했으나, 순서가 바뀌면 뛰어넘고 처리하는 경우가 있어서 각 값마다 아이디를 줌 -->
      <input type="hidden" id="idCheck" name="idCheck" value="">
      <input type="hidden" id="passCheck" name="passCheck" value="">
      <input type="hidden" id="emailCheck" name="emailCheck" value="">
      <input type="hidden" id="ageCheck" name="ageCheck" value="">
      <input type="hidden" id="phoneCheck" name="phoneCheck" value="">
      <input type="hidden" id="nameCheck" name="nameCheck" value="">
<!-- 원래 판별용 하나로 하려고 했으나, 순서가 바뀌면 뛰어넘고 처리하는 경우가 있어서 각 값마다 아이디를 줌 -->
    </div>
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
<script>
//blur 를 바로 동작하는걸로 바꾸기 #########

//비밀번호제어
$("#pass").blur(function() {                                 
	if(!/^(?=.*[A-Za-z])(?=.*\d)(?=.*[$@$!%*#?&])[A-Za-z\d$@$!%*#?&]{5,12}$/.test($('#pass').val())) {
		$("#passChk").text("5~12자 영문,숫자,특수문자를 포함하는 비밀번호를 입력해주세요");
		$("#passChk").css("color","red");
		$("#passCheck").attr("value", "denied");
	} else {
		$("#passChk").text("사용가능 ✔");
		$("#passChk").css("color","blue");
		$("#passCheck").attr("value", "ok");
	}
});
</script>
</html>