



$(document).ready(function() {

	$('#btn').click(function() {
		
			$.ajax("eventMinusPoint.ev",{
				success: function(rdata) {
					alert(rdata)
					if(rdata == 0) {
						alert("포인트가 부족합니다")
						return;
					}else {
						
						$("#myCoupon").html(rdata);
						
						$.ajax({
							url: 'eventPull.ev',
							success: function(rdata) {
								var point = rdata.trim()/10000;
							
								if(rdata == 30000 || rdata == 50000 || rdata == 100000) {
									
									if(rdata == 30000) {
										$('.p1').html("<img src='images/eventCoupon.png' class='img'><br>");
									}else if(rdata == "50000") {
										$('.p1').html("<img src='images/eventCoupon2.png' class='img'><br>");
									}else{
										$('.p1').html("<img src='images/eventCoupon3.png' class='img'><br>");
									}					
									$.ajax("eventWin.ev",{
										data: {"point": point},
										success: function() {
											
											setTimeout(function() {
												alert("당첨!!")								
					    					}, 2000);							
										}
									})					
						    		$.ajax("eventPullCheck.ev", {
						    			success: function(rdata) {
//						    				if(rdata == "0") {
//						    					alert("엔드")
//						    					setTimeout(function() {
//						    						$.ajax("eventEnd.ev",{
//							    						success: function() {
//							    							location.reload();			    							
//							    						}
//							    					});										
//						    					}, 2000);	
//						    				}
						    			}
						    		});
								}else if(rdata == 1) {
									$('.p1').html("<img src='images/x.PNG' class='img'><br>");
									
						    		$.ajax("eventPullCheck.ev", {
						    			success: function(rdata) {
						    				alert("엔드2")
//						    				if(rdata == "0") {
//						    					setTimeout(function() {
//						    						$.ajax("eventEnd.ev",{
//							    						success: function() {
//							    							location.reload();			    							
//							    						}
//							    					});										
//						    					}, 2000);
//						    				}
						    			}
						    		});
								}				
								$("#btn").css('opacity','0').css('pointer-events','none');
								
								setTimeout(function() {
									
									$(".p1").html("<img src='images/box.png' id='img'><br>");
									$("#btn").css('opacity','1').css('pointer-events','auto');
									
									}, 4000);
							},
							error: function() {
								alert("실패");				

							}
						});
					}
					
				}
			})
			

	});

	

	
});


	$(document).ready(function() {
				
		var addDate = document.getElementById("hid").value;		
		var ts = new Date(addDate);
		var newYear = true;

    	if((new Date()) > ts){

			$('.p1').html("<img src='images/a.png' class='img'><br>");
			$("#btn").css('opacity','0').css('pointer-events','none');
 		
    		newYear = false;
    	}
    	
    	$('#countdown').empty();
    	
    	$('#countdown').countdown({
    		timestamp	: ts,
    		callback	: function(days, hours, minutes, seconds){
    		
    		}
    });
});