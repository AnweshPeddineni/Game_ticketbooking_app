<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Admin Home</title>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
</head>
<body class="container">
	<div class="container">
		<h1>Update Event Details</h1>
		<form method="post" action="updateevent" enctype="multipart/form-data">
			<div class="form-group">
				<label for="eventname">Event Name:</label>
				<input name="eventname" value="${event.eventname}" id="eventname" type="text" class="form-control" required="true"/>
			</div>
			<div class="form-group">
				<label for="description">Description:</label>
				<input name="description" value="${event.description}" id="description" type="text" class="form-control" required="true"/>
			</div>
			<div class="form-group">
				<label for="eventdate">Event Date:</label>
                <!-- <fmt:formatDate value="${event.eventdate}" pattern="MM/dd/yyyy" var="formattedDate" /> -->
				<input name="eventdate" value="${event.eventdate}" id="eventdate" type="date" class="form-control" required="true"/>
			</div>
			<div class="form-group">
				<label for="image">Image:</label>
				<input name="img" value="data:image/jpeg;base64,${event.imageAsBase64}" id="image" type="file" class="form-control" required="true"/>
            </div>
            <input type="hidden" name="eventid" value="${event.eventid}">
			<button type="submit" class="btn btn-primary">Update Event</button>
		</form>
	</div>
</body>
</html>
