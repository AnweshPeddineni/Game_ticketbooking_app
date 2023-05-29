<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<!DOCTYPE html>
<html>
<head>
	<title>Home</title>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
</head>
<body class="container">
	<a href="/games/cart">My Cart</a>
	<a class="btn btn-warning" href="/games/logout">Logout</a>
	<h1>Welcome ${user.name}!</h1>
	<p>You are now logged in.</p>

	<div>
		<table class="table table-bordered">
			<caption><h2>Choose the games you like to play!</h2></caption>
			<tr>
				<th>Image</th>
				<th>Name</th>
				<th>Description</th>
				<th>Date</th>
				<th>Actions</th>
			</tr>
			<c:forEach var="event" items="${eventList}">
				<tr>
					<td><img alt="img" src="data:image/jpeg;base64,${event.imageAsBase64}"/></td>
					<td><c:out value="${event.eventname}" /></td>
					<td><c:out value="${event.description}" /></td>
					<td><c:out value="${event.eventdate}" /></td>
					<td><a class="btn btn-primary" href="/games/see-details?eventid=${event.eventid}">See Details</a></td>
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>
