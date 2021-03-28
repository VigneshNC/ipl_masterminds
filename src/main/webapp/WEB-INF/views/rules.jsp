<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Rules to play IPL - Masterminds</title>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
	<link rel="stylesheet" type="text/css" href="http://fonts.googleapis.com/css?family=Ubuntu:regular,bold&subset=Latin">
</head>
<body>
	<jsp:include page="navbar.jsp" />
	
	<div class="container pt-3">
		<h1>Rules to play IPL - Masterminds Fantasy League</h1>
		<hr />
		<ol>
			<li>Each team price limit is 100 Crores. You should pick players within the limit</li>
			<li>Each members should pick minimum 14 and maximum up to 20*</li>
			<li>Each members should pick minimum 8 overseas player for a team. Minimum no limit</li>
			<li>During Auction, Call your price carefully. Because you cannot able to reduce the bid price</li>
			<li>Minimum bid price start from 10 lakhs. Maximum no limit</li>
			<li>During biding, increasing amount range in lakh should be 10 lakhs(10, 20, 30,...)</li>
			<li>Once the price amount reached crore, increasing amount range in crore should be 50 lakhs(1Cr, 1.5Cr, 2Cr, 2.5Cr,...)</li>
			<li>You can directly call your price for your favourite player within your amount limit. If no one is ready to bid for the player, you can directly pick the player</li>
			<li>If the team doesn't pick minimum 14 players for the team, they will be disqualified and team players will again get auction</li>
			<li>One the team has finalized, their team points will be calculated based on IPL Fantasy League points</li>
		</ol>
	</div>
	<script type="text/javascript" src="/js/ipl.js"></script>
</body>
</html>