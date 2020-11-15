<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
                   
                    
					<div class="widget search">
                        <form role="form" action="PlaceSearch.pl">
                            <input type="text" class="form-control search_box" autocomplete="off" placeholder="Search Here" name="search">
                            <button type="submit" style="margin: 25px 35px 0 0;"><i class="fa fa-search"></i></button>
                        </form>
                    </div>
                    <!--/.search-->
                    
                                                               <!-- 날씨 -->
                    <div class="widget social_icon">
						<div class="weather">
							<h3>주간 날씨</h3>
							<div class="day"></div>
						</div>
                    </div>
                    <!-- /날씨 -->
                    
  <div class="widget archieve">
                        <h3>테마별 관광 명소</h3>
                        <div class="row">
                            <div class="col-sm-12">
                                <ul class="blog_archieve">
                                    <li><a href="PlaceSearch.pl?search=관광">관광 <span class="pull-right"></span></a></li>
                                    <li><a href="PlaceSearch.pl?search=맛집">맛집 <span class="pull-right"></span></a></li>
                                    <li><a href="PlaceSearch.pl?search=역사">역사  <span class="pull-right"></span></a></li>
                                    <li><a href="PlaceSearch.pl?search=체험">체험 <span class="pull-right"></span></a></li>
                                </ul>
                            </div>
                        </div>
                    </div>
                    <!--/.archieve-->

                    <div class="widget popular_post">
                        <h3>인 기 리 뷰</h3>
                        <ul>
                        <c:forEach var="list" items="${reviewList }">
                             <li style="margin-bottom: 10px">               
                               	  <a href="Review_Content.re?r_num=${list.r_num }" title="${list.r_subject}">
	                                  <span>
	                                  ${list.r_num }.
	                                  <c:choose>
											<c:when test="${fn:length(list.r_subject) gt '10'}">
												&nbsp; ${fn:substring(list.r_subject,0,10) }&nbsp;...	
											</c:when>
											<c:otherwise>
												&nbsp; ${list.r_subject }									
											</c:otherwise>
									</c:choose>
									</span>										
	                                  <span style="float: right;">${list.r_date } </span>
	                              </a>
                           
                            </li>                  
                        </c:forEach>
         
                        </ul>
                    </div>
                    <!--/.archieve-->
                    

                    <div class="widget blog_gallery">
                        <h3>추 천 상 품</h3>
                        <ul class="sidebar-gallery clearfix">
                       
     						 <c:forEach var="list" items="${productList }" varStatus="stat"> 
     					                  	                                 		
                        		<li>
                            		<a href="productDetail.pr?p_num=${list.p_num }"><img src="product/productUpload/${list.p_image }" title="${list.p_name}" width="50" height="100"/>
                            			 <c:choose>
											<c:when test="${fn:length(list.p_name) gt 9}">
												&nbsp; ${fn:substring(list.p_name,0,9) }...	
											</c:when>
											<c:otherwise>
												&nbsp; ${list.p_name }									
											</c:otherwise>
										</c:choose>
                            		</a>                            
                           	 	</li>                   	                  	
                      		  </c:forEach>                         
                        </ul>
                    </div>

                    <!--/.blog_gallery-->
                    
                     <script type="text/javascript">
	$(document).ready(function() {

// 			var apiURL = 'http://api.openweathermap.org/data/2.5/forecast?q=Busan,KR&appid=69dfa3d384134e76fbafdfc2dcf8765e&units=metric&cnt=8';
// 			$.getJSON(apiURL,function(rdata) {
				
// 				$.each(rdata.list,function(index,item) {
					
// 					var today = new Date(item.dt_txt);
// 					var icon = item.weather[0].icon;
// 					var hour = today.getHours();
					
// 						$('.time').append("<div style='float:left; width: 65px; height: 100px; text-align: center; color: black;'>"
// 										+ hour
// 										+ '시'
// 										+ '<br>'
// 										+ '<img src="icon/'+icon+'.png" width = "50" height = "50" />'
// 										+ item.main.temp.toFixed(0)
// 										+ "˚C"
// 										+ "</div>");
// 								});

// 							});

						var apiURL = 'http://api.openweathermap.org/data/2.5/forecast?q=Busan,KR&appid=69dfa3d384134e76fbafdfc2dcf8765e&units=metric';
						var week = new Array('일', '월', '화', '수', '목', '금', '토');

						$.getJSON(apiURL,function(rdata) {
							$.each(rdata.list,function(index,item) {

								if (index % 8 == 2) {

								var icon = item.weather[0].icon;
								var date = new Date(item.dt_txt).getDay();
								var label = week[date];

								$('.day').append("<div style='float:left; width: 60px; height: 100px; text-align: center; color: black;'>"
												+ label
												+ "<br>"
												+ '<img src="icon/'+icon+'.png" width = "50" height = "50" />'
												+ '<br>'
												+ item.main.temp.toFixed(0)
												+ "˚C"
												+ "</div>");
										}
									});
								});
							});
</script>
                    

                    
 