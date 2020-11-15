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

function idCheck() {
// 	alert(joinForm.check.value);	
	if(joinForm.id.value=="") {
		$("#user_id").focus();
		alert("아이디를 입력해주세요");
		return false;
	} else {
		if(joinForm.idCheck.value == "denied"){
			$("#user_id").focus();
			alert("올바른 아이디를 입력해주세요")
			return false;
		} else {
			return true;
		}
	}
}
function passCheck() {
	if(joinForm.pass.value=="") {
		$("#pass").focus();
		alert("비밀번호를 입력해주세요");
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
function nameCheck() {
	if(joinForm.name.value=="") {
		$("#name").focus();
		alert("이름을 입력해주세요");
		return false;
	}else{
		if(joinForm.nameCheck.value == "denied"){
			$("#name").focus();
			alert("올바른 이름을 입력해주세요")
			return false;
		} else {
			return true;
		}
	}	
}
function genderCheck() {
	if($("#gender option:selected").val() == "성별") {
		$("#gender").focus();
		alert("성별을 선택해주세요");
		return false;
	}else{
		return true;
	}	
}

function ageCheck() {
	if(joinForm.age.value=="") {
		$("#age").focus();
		alert("나이를 입력해주세요");
		return false;
	}else{
		if(joinForm.ageCheck.value == "denied"){
			$("#age").focus();
			alert("올바른 나이를 입력해주세요")
			return false;
		} else {
			return true;
		}
	}	
}

// function ageCheck() {
// 	if(joinForm.age.value=="") {
// 		$("#age").focus();
// 		alert("나이를 입력해주세요");
// 		return false;
// 	} else {
// 		return true;
// 	}
// }		

function emailCheck() {
	if(joinForm.email.value=="") {
		$("#email").focus();
		alert("이메일을 입력해주세요");
		return false;
	}else{
		if(joinForm.emailCheck.value == "denied"){
			$("#email").focus();
			alert("올바른 이메일을 입력해주세요")
			return false;
		} else {
			return true;
		}
	}	
}
function phoneCheck() {
	if(joinForm.phone.value=="") {
		$("#phone").focus();
		alert("휴대폰 번호를 입력해주세요");
		return false;
	}else{
		if(joinForm.phoneCheck.value == "denied"){
			$("#phone").focus();
			alert("올바른 휴대폰번호를 입력해주세요")
			return false;
		} else {
			return true;
		}
	}
}
function typeCheck() {
	if($("#type option:selected").val() == "관심사") {
		$("#type").focus();
		alert("관심사를 선택해주세요");
		return false;
	}else{
		
		return true;
	}
}

function joinCheck() {
	if(idCheck() && 
	   passCheck() &&
	   repassCheck() &&
	   nameCheck() &&
	   genderCheck() &&
	   ageCheck() &&
	   emailCheck() &&
	   phoneCheck() &&
	   typeCheck()) {
		
// 		var formData = $("form[name=joinForm]").serialize() ;
//     index에서 띄워줘야하는데 alert 하나때문에 action페이지 하나 생성해야해서 일단 여기서 처리
	   if(confirm("입력하신 정보로 가입하시겠습니까?")){
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
        <h1>회원가입</h1>
    </div>

    <section id="contact-page" style="height: 1500px;">

<form action="MemberJoinPro.me" method="POST" name="joinForm" onsubmit="return joinCheck()">
  <div class="joinContainer">
	
    <label for="id"><b>아이디</b></label>
    <input type="text" placeholder="아이디를 입력하세요" name="id" id="user_id">
    <span id="id_check"></span>
    
    <label for="pass"><b>비밀번호</b></label>
    <input type="password" placeholder="비밀번호를 입력하세요" name="pass" id="pass">
    <span id="passChk"></span>
    
    <label for="repass"><b>비밀번호 재확인</b></label>
    <input type="password" placeholder="비밀번호를 입력하세요" name="repass" id="repass">
    <span id="repassChk"></span>
    
    <label for="name"><b>이름</b></label>
    <input type="text" placeholder="이름를 입력하세요" name="name" id="name">
    <span id="nameChk"></span>
    
    <label for="gender"><b>성별</b></label>
    <select name="gender" id="gender">
       <option value="성별">성별</option>
   	   <option value="남">남자</option>
	   <option value="여">여자</option>
    </select>
    <span id="genderChk"></span>
    
    <label for="age"><b>나이</b></label>
    <input type="text" placeholder="나이를 입력하세요" name="age" id="age">
    <span id="ageChk"></span>
    
    <label for="email"><b>이메일</b></label>
    <input type="text" placeholder="이메일을 입력하세요" name="email" id="email">
    <span id="emailChk"></span>
    
    <label for="phone"><b>휴대전화</b></label><div id="div1">※   하이픈 ( ‐ )을 생략한 번호를 기입해주세요.</div>
    <input type="text" placeholder="휴대전화를 입력하세요" name="phone" id="phone">
    <span id="phoneChk"></span>
    
    <label for="type"><b>관심사</b></label>
    <select name="type" id="type">
   	   <option value="관심사">관심사</option>
	   <option value="맛집">맛집</option>
	   <option value="체험">체험</option>
	   <option value="관광">관광</option>
	   <option value="역사">역사</option>
    </select>
	<span id="typeChk"></span>


    <div class="clearfix">
      <button type="submit" class="signupbtn">회원가입</button>
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
//blur 를 바로 동작하는걸로 바꾸기 ##########

//아이디제어
$("#user_id").blur(function() {
	var formData = $("#user_id").serialize() ;
	
	if(!/^[A-za-z0-9]{5,12}$/.test($('#user_id').val())) {
		$("#id_check").text("5~12자 영문의 아이디 입력하세요");
		$("#id_check").css("color","red");
		$("#idCheck").attr("value", "denied");
		
	} else {
		
		$.ajax({
		url : 'MemberJoinCheck.me',
		type : 'post',
		data : formData,
		success : function(data) {
			if(data == 1) {
				$("#id_check").text("사용중인 아이디입니다.");
				$("#id_check").css("color","red");
				$("#idCheck").attr("value", "denied");
			} else {
				$("#id_check").text("사용 가능 ✔");
				$("#id_check").css("color","blue");
				$("#idCheck").attr("value", "ok");
			}
		}, error : function() {
				console.log("실패");
		}
	});	
	}
});

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

//이름 제어
$("#name").blur(function() {                                 
	if(!/^[가-힣]{2,4}$/.test($('#name').val()) && !/^[a-zA-Z]{2,10}$/.test($('#name').val())) {
		$("#nameChk").text("2~4자 한글 또는 2~10자 영문 이름을 입력해주세요");
		$("#nameChk").css("color","red");
		$("#nameCheck").attr("value", "denied");
	} else {
		$("#nameChk").text("");
		$("#nameCheck").attr("value", "ok");
	}
});

//나이제어 date 로 바꾸면서 주석처리
$("#age").blur(function() {                               
	if(!/^[0-9]{1,3}$/.test($('#age').val()) || $('#age').val() == 0) {
		$("#ageChk").text("올바른 나이를 기입해주세요");
		$("#ageChk").css("color","red");
		$("#ageCheck").attr("value", "denied");
	} else {
		$("#ageChk").text("");
		$("#ageCheck").attr("value", "ok");
	}
});

//이메일제어
$("#email").blur(function() {   
	var formData2 = $("#email").serialize() ;
//  다른 이메일 정규식 - (현재꺼 작동 잘하면 나중에 지워도 OK)
// 	if(!/^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/.test($('#email').val())) {
	if(!/^[\w\.-]{1,12}@[\w\.-]{1,18}\.\w{2,4}$/.test($('#email').val())) {
		$("#emailChk").text("올바른 이메일형식으로 기입해주세요");
		$("#emailChk").css("color","red");
		$("#emailCheck").attr("value", "denied");
	} else {
		$("#emailChk").text("");
		$("#emailCheck").attr("value", "ok");
		
		$.ajax({
			url : 'MemberJoinCheck.me',
			type : 'post',
			data : formData2,
			success : function(data) {
				if(data == 1) {
					$("#emailChk").text("사용중인 이메일입니다.");
					$("#emailChk").css("color","red");
					$("#emailCheck").attr("value", "denied");
				} else {
					$("#emailChk").text("사용 가능 ✔");
					$("#emailChk").css("color","blue");
					$("#emailCheck").attr("value", "ok");
				}
			}, error : function() {
					console.log("실패");
			}
		});	
	}
});
 
//휴대전화제어
$("#phone").blur(function() {                                
	var formData3 = $("#phone").serialize() ;
	
	if(!/^(?:(010\d{4})|(01[1|6|7|8|9]\d{3,4}))\d{4}$/.test($('#phone').val())) {
		$("#phoneChk").text("올바른 휴대폰번호를 입력해주세요");
		$("#phoneChk").css("color","red");
		$("#phoneCheck").attr("value", "denied");
	} else {
		$("#phoneChk").text("");
		$("#phoneCheck").attr("value", "ok");
		
		$.ajax({
			url : 'MemberJoinCheck.me',
			type : 'post',
			data : formData3,
			success : function(data) {
				if(data == 1) {
					$("#phoneChk").text("사용중인 휴대폰번호입니다.");
					$("#phoneChk").css("color","red");
					$("#phoneCheck").attr("value", "denied");
				} else {
					$("#phoneChk").text("사용 가능 ✔");
					$("#phoneChk").css("color","blue");
					$("#phoneCheck").attr("value", "ok");
				}
			}, error : function() {
					console.log("실패");
			}
		});	
	}
});
</script>
</html>