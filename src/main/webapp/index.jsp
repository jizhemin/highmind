<html>
<body>
<h2>Hello World!</h2>
token:<input type="text" id="token">
<input type="button" OnClick="PostToken()">
</body>
<script src="https://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>
<script>
	var PostToken = function(){
		var token=$("#token").val();
		$.ajax({
	        type: "POST",
	        url: "http://127.0.0.1:8080/HM/getInfo",
	        data:{token:token},
	        success: function (data) {
	        	
	        },
	        error: function (message) {
	            console.log(message);
	        }
	    });
	};
</script>
</html>
