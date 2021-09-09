<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Rules to play IPL - Masterminds</title>
	<link rel="stylesheet" href="/css/bootstrap_4_5_2.css">
	<script src="/js/jquery_3_5_1.js"></script>
	<script src="/js/bootstrap_4_5_2.js"></script>
	<link rel="stylesheet" type="text/css" href="https://fonts.googleapis.com/css?family=Ubuntu:regular,bold&subset=Latin">
	<script type="text/javascript" src="/js/preload_ipl.js"></script>
</head>
<body>
	<jsp:include page="navbar.jsp" />
	
	<div class="container pt-3">
		<h1>Rules to play IPL - Masterminds Fantasy League</h1>
		<hr />
		<ol>
			<li>Each team price limit is 120 Crores. You should pick players within the limit</li>
			<li>Each members should pick minimum 15 and maximum up to 25</li>
			<li>Each members should pick minimum 9 overseas player for a team. Minimum no limit</li>
			<li>During Auction, Call your price carefully. Because you cannot able to reduce the bid price</li>
			<li>Minimum bid price start from 5 lakhs. Maximum no limit</li>
			<li>During biding, increasing amount range in lakh should be 5 lakhs(5, 10, 15, 20,...)</li>
			<li>Once the price amount reached crore, increasing amount range in crore should be 20 lakhs(1Cr, 1.2Cr, 1.4Cr, 1.6Cr,...)</li>
			<li>You can directly call your price for your favourite player within your amount limit. If no one is ready to bid for the player, you can directly pick the player</li>
			<li>If the team doesn't pick minimum 15 players for the team, they will be disqualified and team players will again get auction</li>
			<li>One the team has finalized, their team points will be calculated based on IPL Fantasy League points</li>
		</ol>
	</div>
	<script type="text/javascript" src="/js/ipl.js"></script>
</body>
</html>