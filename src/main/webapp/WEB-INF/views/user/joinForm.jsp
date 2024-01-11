<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp"%>

<div class="container">
	<form>
		<div class="form-group">
			<label for="username">Username:</label> <input type="text"	class="form-control" placeholder="Enter username" id="username">
		</div>
		<div class="form-group">
			<label for="password">Password:</label> <input type="password" class="form-control" placeholder="Enter password" id="password">
		</div>
		<div class="form-group">
			<label for="email">Email address:</label> <input type="email"	class="form-control" placeholder="Enter email" id="email">
		</div>
	</form>
	<button id="btn-save" class="btn btn-primary">회원가입</button>
</div>
<script type="text/javascript">

$("#btn-save").on("click", () => {
	let data = {
			username: $("#username").val(),
			password: $("#password").val(),
			email: $("#email").val()
	};
	
	$.ajax({
		type: "POST",
		url: "/blog/api/user",
		data: JSON.stringify(data), // http body 데이터
		contentType: "application/json; charset=utf-8", // body데이터가 어떤타입인지(MIME)
		dataType: "json" // 요청을 서버로해서 응답이 왔을 때 기본적으로 모든 것이 문자열이라고 생각 생긴게 json이라면 javascript object로 변환 	
	}).done(function(resp){
		alert("회원가입이 완료되었습니다.");
		location.href="/blog";
	}).fail(function(error){
		alert(JSON.stringify(error));
	}); // ajax 통신을 이용해서 3개의 데이터를 json으로 변경하여 insert요청
})

</script>
<%@ include file="../layout/footer.jsp"%>
</body>
</html>


