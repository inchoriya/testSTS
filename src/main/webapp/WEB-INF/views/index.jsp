<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>파이썬 데이터 테스트 중..</h2>
	<button id="mvlist">영화목록</button>
	<button id="crawling">파이썬 크롤링 실행</button>
</body>

<script src="https://code.jquery.com/jquery-3.7.1.js" integrity="sha256-eKhayi8LEQwp4NKxN+CfCh+3qOVUtJn3QNZ0TciWLP4=" crossorigin="anonymous"></script>
<script>

	$('#mvlist').click(()=>{
		location.href = "mvList";
	});
	
	$('#crawling').click(()=>{
		location.href = "crawling";
	});
	
</script>
</html>









