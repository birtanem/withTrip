<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- core CSS -->
<link href="css/product_modal.css" rel="stylesheet">
<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/font-awesome.min.css" rel="stylesheet">
<link href="css/animate.min.css" rel="stylesheet">
<link href="css/prettyPhoto.css" rel="stylesheet">
<link href="css/owl.carousel.min.css" rel="stylesheet">
<link href="css/icomoon.css" rel="stylesheet">
<link href="css/main.css" rel="stylesheet">
<link href="css/responsive.css" rel="stylesheet">




<!-- <link href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css" rel="stylesheet"> -->
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-lite.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-lite.min.js"></script>
<script src="js/summernote-ko-KR.js"></script>	

<!-- <script src="js/jquery-3.5.0.js"></script> -->
<script type="text/javascript">
	$(document).ready(function(){
		$.ajax("getRegion.co",{
			dataType:"json",
			success:function(rdata){
				$.each(rdata,function(index,code){
					var option=$('<option value="'+code.rCode+'">'+code.rName+'</option>');
					$('#rCode').append(option);
				});
			}
		});
	});
</script>
<script type="text/javascript">
function sendFile(file, editor) {
		data = new FormData();
	    data.append("uploadFile", file);
	    $.ajax({ 
	        data : data,
	        type : "POST",
	        url : "ImageCallback.pl",
	        cache : false,
	        contentType : false,
	        processData : false,
	        success : function(data) {
	        	$(editor).summernote('editor.insertImage', data.url);
	        }
	    });
	}
</script>
<style type="text/css">
table {
	margin: auto;
}
</style>
</head>
<body>

 <section id="contact-page">
        <div class="container">
            <div class="large-title text-center">        
                <h2>상품 등록</h2>
            </div> 
            <div class="row contact-wrap" style="padding-left: 50px;"> 
                <div class="status alert alert-success" style="display: none"></div>
                <!-- <form id="main-contact-form" class="contact-form" name="contact-form" method="post" action="sendemail.php"> -->
          <form action="productRegistPro.pr" method="post" name="fr" enctype="multipart/form-data">
                    <div class="col-sm-offset-1">
                    <div class="col-sm-5">
                        <div class="form-group">
                            <label>상품명 : </label>
                            <input type="text" name="p_name" class="form-control" required="required"/>
                        </div>

                        <div class="form-group">
                            <label>지역 : </label>
                            <select name="region_region_code" id="rCode" class="form-control" required="required">
		            			
		                 	</select>
                        </div>
                         <div class="form-group">
                            <label>주제 : </label>
                            <select name="p_theme" class="form-control" required="required">
				            	  <option value="관심사">관심사</option>
								   <option value="맛집">맛집</option>
								   <option value="체험">체험</option>
								   <option value="관광">관광</option>
								   <option value="역사">역사</option>
		                 	</select>
                        </div>
                        </div>
                        
                        
                        </div>
                        
                        <div class="col-sm-5">
                        <div class="form-group">
                            <label>가격 : </label>
                            <input type="text" name="p_price" class="form-control" required="required"/>
                        </div>
                        <div class="form-group">
                            <label>상품수량 :</label>
                            <input type="number" name="p_amount" class="form-control" required="required"/>
                        </div>   

                        </div>                     
                         
                        
                     
                      <div class="col-sm-10 ">
                      <div class="form-group ">
                            <label>대표 사진 : </label>
                            <input type="file" name="p_file" class="form-control" required="required"/>
                        </div>
                            <label>상품 설명 글</label>
                            <textarea id="summernote" name="p_content" required="required" >상품 설명을 입력해주세요</textarea>
                            
                            <div class="form-group">
                            <button type="submit" name="submit" class="btn btn-primary btn-lg" >상품 등록</button>
                            <button type="reset" class="btn btn-primary btn-lg" >다시 쓰기</button>
                            <button type="reset" class="btn btn-primary btn-lg" onclick="location.href='productList.pr'">상품 목록</button>
                        </div>
                        </div>    
                      </div>
           		    </form> 
                 </div>
            </div><!--/.row-->
        <!--/.container-->
    </section><!--/#contact-page-->
  <script>
            $(document).ready(function() {
                $('#summernote').summernote({
                    height: 400,lang: 'ko-KR',
                    disableResizeEditor: true,
					callbacks: { 
					    onImageUpload: function(files, editor, welEditable) {
						    sendFile(files[0], this);
						}
					}
				});
			});
		</script>
</body>
</html>