<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="../js/jquery-3.5.0.js"></script>
<script type="text/javascript">

	
function minuscount(num) {
			var count = Number($("#amount"+num).val()) - 1;
			$("#amount"+num).val(count);
	}
	
function pluscount(num) {

			var count = Number($("#amount"+num).val()) + 1;
			$("#amount"+num).val(count);	
	}

</script>
<title>Insert title here</title>
</head>
<body>
<button class="btnCalc minus" id="minus${1}" onclick="minuscount(${1})">-</button>
      <input type="text" id="amount${1}" name="amount" value="1" class="tx_num" title="구매수량">
      <button class="btnCalc plus" id="plus${1}" onclick="pluscount(${1})">+</button>
      
      <button class="btnCalc minus" id="minus${2}" onclick="minuscount(${2})">-</button>
      <input type="text" id="amount${2}" name="amount" value="1" class="tx_num" title="구매수량">
      <button class="btnCalc plus" id="plus${2}" onclick="pluscount(${2})">+</button>
</body>
</html>