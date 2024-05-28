<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	.movie-box {
		border : 1px solid black;
		display : inline-block;
		margin : 15px;
		width : 300px;
		height : 550px;
	}

</style>
</head>
<body>

	<div id="cgvlist"></div>
	
</body>
<script src="https://code.jquery.com/jquery-3.7.1.js" integrity="sha256-eKhayi8LEQwp4NKxN+CfCh+3qOVUtJn3QNZ0TciWLP4=" crossorigin="anonymous"></script>
<script>

$.ajax({
	type : "POST",
	url : "cgvList",
	dataType : "json",
	success : (list) => { cgvList(list); }, 
	error : ()=>{ alert('영화 불러오기 실패!') }
});

function cgvList(list){
	let output = "";
	let j = 1;
	
	output += "<div id='movie-wrap'>";
	
	for(let i in list){
		
		output += "<div class='movie-box'>";
		output += "<a href='detailView?rank=" + list[i].rank + "'>"
		output += "순위 : " + list[i].rank;
		output += "<br/>";
		if(list[i].title.length > 15){
			list[i].title1 = list[i].title.substring(0, 15) + "..."; 
		} else {
			list[i].title1 = list[i].title;
		}
		output += "제목 : " + list[i].title1;
		
		output += "<br/>";
		output += "예매율 : " + list[i].percent;
		output += "<br/>";
		output += "개봉일 : " + list[i].release_date;
		output += "<br/>";
		output += "<img src='" + list[i].poster + "' width='250px' />";		
		output += "</a>"		
		output += "</div>";	
		if(j % 5 == 0){
			output += "<br/>"
		}
		j++;
		
	}
	output += "</div>";
	
	$('#cgvlist').html(output);
}



</script>



</html>













