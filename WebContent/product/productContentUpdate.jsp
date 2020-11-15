<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css" rel="stylesheet">
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

<link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.js"></script>
<script src="js/summernote-ko-KR.js"></script>	
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
</head>
<body>
<form action="productContentUpdatePro.pr" method="post" name="fr">
<input type="hidden" value="${param.p_num }" name="p_num">
<textarea id="summernote" name="content"></textarea>
<input type="submit" class="btn" value="수정">
</form>
	  <script>
            $(document).ready(function() {
                $('#summernote').summernote({
                    height: 400,lang: 'ko-KR',
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