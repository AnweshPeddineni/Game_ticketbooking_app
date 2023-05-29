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
	<a href="/games/user" class="btn btn-primary">Go back to home page</a>

	<div>
		<table class="table table-bordered">
			<tr>
				<th>Image</th>
				<th>Name</th>
				<th>Description</th>
				<th>Date</th>
			</tr>
			
				<tr>
					<td><img alt="img" src="data:image/jpeg;base64,${image}"/></td>
					<td><c:out value="${event.eventname}" /></td>
					<td><c:out value="${event.description}" /></td>
					<td><c:out value="${event.eventdate}" /></td>

				</tr>
			
		</table>
	</div>
    <div>
		<table class="table table-bordered">
			<caption><h2>Select timeslot for ${event.eventname}</h2></caption>
			<tr>
				<th>Start Time</th>
				<th>End Time</th>
				<th>Positions</th>
				<th>Price</th>
				<th>Actions</th>
			</tr>
			<c:forEach var="timeslot" items="${event.timeslots}">
				<tr>
					<td><c:out value="${timeslot.startTime}" /></td>
					<td><c:out value="${timeslot.endTime}" /></td>
					<td><c:out value="${timeslot.availablePositions}" /></td>
					<td><c:out value="${timeslot.price}" /></td>
					<td><a href="/games/book-ticket?eventid=${event.eventid}&timeslotid=${timeslot.timeslotid}">Book Now</a></td>
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>
