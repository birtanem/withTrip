# withTrip

> 부산의 여행지를 소개하고 상품을 파는 웹사이트

[실행화면 유튜브로 보기](https://www.youtube.com/watch?v=sXzx_DXEpVU)  
[호스팅 주소로 보기](http://itwillbs10.cafe24.com/withTrip)

## 개발 환경
**Front-end**
  * HTML5
  * CSS3
  * JavaSript
  * jquery
  
**Back-end**
  * Java1.8
  * MySQL5.7

**Tools**
  * Eclipse
  * apache/tomcat8.0
  * MySQL Workbench
 
 
## 핵심 기능 ( 담당 기능 )

### 팀프로젝트는 MVC2 패턴으로 개발  

> MVC2 패턴이란 ?   
> * MVC1 과는 달리 브라우저의 요청을 하나의 서블릿이 받아서 controller, action, service, dao 단으로 나누어 처리한다. 

### 유스케이스

### DB  
DBCP : 커넥션풀에서 커넥션을 꺼내 쓰고 반환하는 방식




### 결제 전 이메일인증
이메일 인증은 정보처리기사 자격증을 공부하다가 암호화를 공부하면서 호기심이 생겨 구현해보았다.

SHA-256 이란?
* 해쉬를 이용한 알고리즘, 단방향 알고리즘으로 복호화가 불가능하다.  
인증할 때 이메일을 SHA256 으로 암호화하고 암호화한 값을 비교해서 인증을 진행한다.

``` java
public class SHA256 {

public static String getEncrypt(String email) {
 // 암호화한 결과값을 16진수로 변환해서 담을 변수
 String result = "";
 // 문자열을 바이트코드로 인코딩
 byte[] b = email.getBytes();

 try {
  // SHA-256 암호화
  MessageDigest md = MessageDigest.getInstance("SHA-256");
  md.update(b);
  byte[] bResult = md.digest();
   
  // 암호화된 바이트를 16진수 정수 String 으로 다시 변환
  StringBuffer sb = new StringBuffer();
  for (byte data : bResult) {
   sb.append(Integer.toString(data & 0xff, 16));
  }
  result = sb.toString();
  } catch (Exception e) {
   e.printStackTrace();
  }
  return result;
  }
}

```
<img src="https://user-images.githubusercontent.com/64389409/99179974-9cc0e700-2765-11eb-8c5a-8dc50f00feac.PNG" width="40%" heigth="40%">  
암호화의 결과값인 resulst는 하이퍼링크로 유저 이메일로 보내준다.
유저가 이메일에서 링크를 클릭하면 result 값이 맞는지 확인하고 인증을 완료한다.  
  
  
    
### 주문번호  
주문번호는 오늘의 날짜와 주문순서를 조합하여 생성하려고 한다. ex) 20220710 + 0001  
날짜는 java.util.Date를 사용하면 되고 주문순서는 시퀀스테이블을 만들어서 auto_increamet 하는 값을 가져오기로 한다.  
하지만 여기서 고민이 생겼다 !?!?  
날짜가 변경되면 주문순서를 다시 0001 번으로 돌리고 싶었다. 수동으로 할 수 있지만 매일 하는것이 번거롭다.   
그래서 검색하던 중 **MySQL event scheduler** 를 찾았다.  
이벤트 스케쥴러는 매 특정시간마다 등록해놓은 프로시저를 실행시킨다.  
이걸 이용하면 자동으로 시퀀스테이블 초기화가 가능할것이다.
아래는 이벤트스케쥴러 사용을 위해 정리해놓은 메모장이다.  
``` text
주문번호 생성 DB 구문.txt

시퀀스 테이블 생성
create table order_seq (
num int primary key auto_increment 
);

서버를 껏다 켜도 이벤트 스케쥴러가 항시 on 되게 설정하는법
my.ini 안에
[mysqld] 밑에
event_scheduler = on 추가

이벤트 스케쥴러 ON
set GLOBAL event_scheduler = on;

이벤트 생성(프로시저 실행)
DELIMITER //
create event reset
on schedule
EVERY 1 DAY
STARTS '2020-06-18 09:10:00'
DO
BEGIN
delete from order_seq;
alter table order_seq auto_increment=1;
insert into order_seq values(null);
END
//

이벤트 확인
select * from information_schema.events;

이벤트 스케쥴러 on/off 확인
show variables like 'event%'
```
``` sql
주문순서를 000X 로 4자리로 만들어서 가져와서 날짜랑 조합하였다.
SELECT LPAD(MAX(num),4,0) num FROM order_seq
```
<img src="https://user-images.githubusercontent.com/64389409/99180638-3e970280-276b-11eb-9bfc-f6fc1c89021c.PNG" width="40%" heigth="40%">  






### 주문내역 

주문번호 하나에 여러개의 상품을 주문할 수 있기 때문에 테이블을 order 와 order_detail 로 나누었다.  
주문내역은 order와 order_detail 을 JOIN 하여 정보를 가져온다.    
GROUP BY 절을 사용하는 이유는 하나의 주문안에 여러개의 상품이 있기 때문에 중복되는 주문내역이 생김을 방지하기 위해서이다.
``` sql
SELECT * 
FROM `order` o JOIN order_detail od 
ON o.o_num = od.order_num 
WHERE member_id = ? 
GROUP BY od.order_num 
ORDER BY o.o_date desc limit ?,?

```

주문상세내역을 가져오는 쿼리문이다. 주문번호에 의한 상세 제품들의 정보를 가져온다.
```sql
SELECT * 
FROM `order` o JOIN order_detail od
ON o.o_num = od.order_num 
WHERE o.o_num = ?
```

검색은 BETWEEN AND 절을 사용했다.
``` sql
BETWEEN date AND date
```

### 추천리스트
추천은 랜덤함수를 사용했다.
``` sql
rand() LIMIT 4
```

### 이벤트
사용자가 포인트를 모아서 뽑기를 할 수 있는 컨텐츠를 만들고 싶어서 구현해보았다.  
jquery Datepicker 를 사용해서 시작시간, 종료시간을 선택할 수 있고 countdown.js 로 종료시간이 끝나면 뽑기가 종료되게 구현하였다.  

<img src="https://user-images.githubusercontent.com/64389409/99182523-c0415d00-2778-11eb-9bf2-5a51036e8b51.PNG" width="20%" heigth="20%"><img src="https://user-images.githubusercontent.com/64389409/99182527-c2a3b700-2778-11eb-8b28-90d2a64f38a6.PNG" width="50%" heigth="50%">  


테이블을 뽑기함이라고 생각하고 event_box 테이블을 생성했다. 테이블안에 행들은 당첨 또는 꽝이 적힌 쪽지라고 생각하면 된다.  
rank 는 당첨과 꽝을 나누는 컬럼이고 pull은 뽑은 것과 뽑지않은 것을 나누는 컬럼이다.
아래 테이블은 100개중에 3개가 당첨이라고 생각할 수 있겠다.    
num = 1 을  뽑으면 1등, num = 2는 2등, num = 99는 3등이고 나머지는 꽝일 것이다.   
|num|rank|pull|
|------|---|---|
|1|1|0|
|2|2|0|  
|3|0|0|  
|.|||  
|.|||
|98|0|0|
|99|3|0|
|100|0|0|  

유저의 액션에 의한 디비 컬럼 변화를 봐보자.     
1. 유저가 뽑기 버튼을 누르면 pull = 0 인 행에서 랜덤으로 행(2)을 한개 뽑는다.  
2. 뽑힌 행(2)은 pull = 1 로 변경된다. (뽑을때 pull=0 을 가져오기 때문에 뽑힌 행은 다음부턴 뽑을 수 없다)  
3. 뽑힌 행의 rank 를 확인하여 당첨인지 아닌지를 확인한다 (num = 2가 뽑혔으니 2등)
4. 만약 rank != 0 & pull = 0  인 행이 없다는것은 다 뽑힌것이기 때문에 뽑기는 종료되어야한다. (재시작하면 pull = 0 으로 변경)

|num|rank|pull|
|------|---|---|
|1|1|0|
|2|2|1|  
|3|0|0|  
|.|||  
|.|||
|98|0|0|
|99|3|0|
|100|0|0|

뽑기의 확률을 조정하려면 디비에서 직접 작업해야하므로 불편하다.  
웹페이지에서 관리자가 직접 조정할 수 있도록 생각해 볼 필요가 있다.


### 통계
* 차트
관심사와 일별 가입자 수는 [AMCHARTS](https://www.amcharts.com/) 를 사용했다.  
원하는 차트 예제를 가져와서 데이터만 넣어주니까 차트가 어려움 없이 잘 표현되었다.
``` javascript
$(document).ready(function() {	
  am4core.ready(function() {
  // Themes begin
  am4core.useTheme(am4themes_animated);
  // Themes end

  // Create chart instance
  var chart = am4core.create("chartdiv", am4charts.PieChart);

  // Add data
  // 아래 부분에 서버에서 가져온 값들 넣어준다.
  chart.data = [ {
  "취향": "관광",
  "litres": ($("#htype1").val()/$("#totalCount").val()*100)		 
  }, {
  "취향": "맛집",
  "litres": ($("#htype2").val()/$("#totalCount").val()*100)
  }, {
  "취향": "역사",
  "litres": ($("#htype3").val()/$("#totalCount").val()*100)
  },{
  "취향": "체험",
  "litres": ($("#htype4").val()/$("#totalCount").val()*100)
  }];
  // Add and configure Series
  var pieSeries = chart.series.push(new am4charts.PieSeries());
  pieSeries.dataFields.value = "litres";
  pieSeries.dataFields.category = "취향";
  pieSeries.slices.template.stroke = am4core.color("#fff");
  pieSeries.slices.template.strokeWidth = 2;
  pieSeries.slices.template.strokeOpacity = 1;

  // This creates initial animation
  pieSeries.hiddenState.properties.opacity = 1;
  pieSeries.hiddenState.properties.endAngle = -90;
  pieSeries.hiddenState.properties.startAngle = -90;
  }); // end am4core.ready()
});

// 차트가 보여짐
<div id="chartdiv" class="row" ></div>
```
<img src="https://user-images.githubusercontent.com/64389409/99184089-f33d1e00-2783-11eb-9eb1-42ba13e56ad9.PNG" width="100%" heigth="100%">

* 증감율
통계페이지에 주식처럼 증감율이 있으면 괜찮겠다는 생각에 구현해봤다.
로그 테이블을 만들었고 이벤트 스케쥴러로 일별 데이터들이 저장되게 프로시저를 작성했다.



``` sql
// 프로시저 등록
DELIMITER $$
create procedure log_insert()
  BEGIN
  DECLARE joincount int;
  DECLARE revenue int;
  DECLARE readcount int;

  // 가입자수 조회
  SELECT count(id) 
  INTO joincount
  FROM member 
  WHERE date_format(date, '%Y%m%d') =  date_format(date_add(now(), interval -1 day), '%Y%m%d');

  // 판매액 조회 
  SELECT SUM(o_price)
  INTO revenue
  FROM `order`
  WHERE date_format(o_date, '%Y%m%d') =  date_format(date_add(now(), interval -1 day), '%Y%m%d');

  // 조회수 조회
  SELECT SUM(pl_readcount) - (SELECT MAX(l_readcount) FROM log) 
  INTO readcount
  FROM place;

  // 조회한 값들을 log 테이블 넣기
  INSERT INTO log VALUES(null, joincount, revenue, readcount, date_add(now(), interval -1 day));
END$$
DELIMITER ;

// 스케쥴러 등록
DELIMITER //
create event log_insert
on schedule
EVERY 24 HOUR
STARTS '2020-06-23 00:10:00'
DO
call log_insert();
//
```
이제 log 테이블 일별 데이터들을 가지고 증감율을 구하면 된다.

|num|joincount|revenue|readcount|date
|------|---|---|------|---|
|1|20|180000|100|2020-06-22
|2|40|200000|120|2020-06-23  

증감율 계산
```
(증가량 or 감소량 / 시작 값) × 100 
```

위 테이블에서 22일과 23일의 조회수 증감율을 계산해보자
```
( 120 - 100 ) / 100 * 100 = 20
```
20% 가 증가했다.    

이렇게 두개의 행을 연산하기 위해서 SELF JOIN 을 사용했다. truncate 는 소수점을 없애기 위해 사용하였다.  

``` sql

SELECT truncate((next.readcount - prev.readcount) / prev.readcount * 100, 0) AS readcount
FROM log next JOIN log prev
Where next.num = prev.num + 1 

```
prev는 num = 1 튜플을, next는 num = 2 튜플을 가리킨다.  
```
( next.readcount(120) - prev.readcount(100) ) / prev.readcount(100) * 100 = 20 %
```
20% 로 결과값이 잘 나온것을 확인할 수 있다.  


<img src="https://user-images.githubusercontent.com/64389409/99184088-f2a48780-2783-11eb-8441-bb94d9457f43.PNG" width="100%" heigth="100%">

## 화면 캡처


