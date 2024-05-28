<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	th{
		width : 20%;
		padding : 5px;
	}
	table {
		width : 80%;
	}
	
	table, td, th {
		border : 1px solid black;
		border-collapse : collapse;
	} 
</style>
</head>
<body>

<table>
	<tr>
		<th rowspan="5"><img src="${detail.poster}" width="200px"/></th>
		<td><b>순위 : </b>${detail.rank}</td>
	</tr>
	<tr>
		<td><b>제목 : </b>${detail.title}</td>
	</tr>
	<tr>
		<td><b>예매율 : </b>${detail.percent}</td>
	</tr>

	<tr>
		<td><b>정보 : </b>${detail.spec}</td>
	</tr>
	<tr>
		<td><b>줄거리 : </b>${detail.story}</td>
	</tr>
	
	
</table>
</body>
</html>