<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Breakdown: IPL - Masterminds</title>
	<link rel="stylesheet" href="/css/bootstrap_4_5_2.css">
	<script src="/js/jquery_3_5_1.js"></script>
	<script src="/js/bootstrap_4_5_2.js"></script>
	<link rel="stylesheet" type="text/css" href="https://fonts.googleapis.com/css?family=Ubuntu:regular,bold&subset=Latin">
	<script type="text/javascript" src="/js/preload_ipl.js"></script>
</head>
<body>
	<jsp:include page="navbar.jsp" />
	
	<div class="container pt-3">
		<h1>Points Table</h1>
		<span class="float-right" style="color:red;"><b>Note:</b> Click on any row to view each player points of the participant.</span>
		<table class="table table-dark table-hover">
			<thead>
				<tr>
					<th>MasterMinds</th>
					<th>Total Points</th>
				</tr>
			</thead>
			<tbody id="tBodyParticipants">
				<c:forEach items="${pointsTable}" var="point">
					<tr>
						<td>${point.participant}</td>
						<td>${point.points}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<script type="text/javascript" src="/js/ipl.js"></script>
</body>
</html>