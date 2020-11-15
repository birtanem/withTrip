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

**장바구니** 



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
``` java
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		Calendar cal = Calendar.getInstance( );
		
		Calendar cal2 = Calendar.getInstance( );
		
		int betweenDay = Integer.parseInt(request.getParameter("betweenDay"));
		int betweenDay2 = Integer.parseInt(request.getParameter("betweenDay2"));
		
		cal.add (Calendar.DAY_OF_MONTH, - betweenDay); // 이전 일
		
		String day = sdf.format(cal.getTime());
		
		System.out.println(day);
		
		cal2.add (Calendar.DAY_OF_MONTH, - betweenDay2); // 이전 일
		
		String day2 = sdf.format(cal2.getTime());
		
		System.out.println(day2);
```

### 추천리스트 

### 이벤트  

### 통계  



