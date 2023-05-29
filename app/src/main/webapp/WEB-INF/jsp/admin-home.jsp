
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
	<style>
		/* Body and container styles */
body {
  background-color: #ffffff;
  color: #0e01018e;
}

.container {
  margin-top: 20px;
  max-width: 1200px;
  margin-left: auto;
  margin-right: auto;
}

/* Form styles */
.form-group {
  width: 60%;
  /* margin: 0 auto; */
}

label {
  display: block;
  font-size: 20px;
  margin-bottom: 10px;
}

input[type="text"],
input[type="date"],
input[type="file"] {
  width: 100%;
  padding: 10px;
  border-radius: 5px;
  border: rgb(81, 80, 80);
  background-color: #fefdfd;
  color: #040303;
}

input[type="text"],
input[type="date"],
input[type="file"] {
border: 1px solid rgb(133, 129, 129);
}

/* Table styles */
table {
  width: 100%;
  margin-top: 30px;
  border-collapse: collapse;
}

th,
td{
  text-align: center;
  padding: 10px;
}

h1 {
  padding: 50px;
  color: #040303;
  text-align: center;
}

th {
  background-color: #2e2e2e;
  color: #fff;
  font-size: 20px;
}

td {
  font-size: 18px;
  background-color: #fcfbfb;
  color: #0b0101d4;
}

.btn-primary {
  background-color: #0f4cbddf;
  border: none;
  border-radius: 5px;
  color: #fff;
  cursor: pointer;
  font-size: 16px;
  padding: 10px 10px;
  margin-top: 4px;
  margin-bottom: 4px;
  width: 150px;
}

.btn-warning {
  background-color: #edaa3d;
  border: none;
  border-radius: 5px;
  color: #fff;
  cursor: pointer;
  font-size: 16px;
  padding: 10px 10px;
  margin-top: 4px;
  margin-bottom: 4px;
  width: 150px;
}

.btn-danger {
  background-color: #d63f2e;
  border: none;
  border-radius: 5px;
  color: #fff;
  cursor: pointer;
  font-size: 16px;
  padding: 10px 10px;
  margin-top: 4px;
  margin-bottom: 4px;
  width: 150px;
}

.btn-info {
  background-color: #4d9ace;
  border: none;
  border-radius: 5px;
  color: #fff;
  cursor: pointer;
  font-size: 16px;
  padding: 5px 10px;
  width: 100px;
}

/* Image styles */
img {
  max-width: 100%;
  height: auto;
}

	</style>
</head>

<body class="container">
	<div class="container">
		<li style="display: inline-block;"><a class="btn btn-warning" href="/games/logout">Logout</a></li>
			<li style="display: inline-block;"><h1>Admin Page</h1></li>
		<h2>Add Event Details</h2>
		<form method="post" action="add-event" enctype="multipart/form-data">
			<div class="form-group">
				<label for="eventname">Event Name:</label>
				<input name="eventname" id="eventname" type="text" class="form-control" required="true"/>
			</div>
			<div class="form-group">
				<label for="description">Description:</label>
				<input name="description" id="description" type="text" class="form-control" required="true"/>
			</div>
			<div class="form-group">
				<label for="eventdate">Event Date:</label>
				<input name="eventdate" id="eventdate" type="date" class="form-control" required="true"/>
			</div>
			<div class="form-group">
				<label for="image">Image:</label>
				<input name="img" id="image" type="file" class="form-control" required="true"/>
            </div>
			<button type="submit" class="btn btn-primary">Add Event</button>
		</form>
		
		<div>
			<table class="table table-bordered">
				<caption><h2>List of Events</h2></caption>
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
						<td>
							<div><a class="btn btn-primary" href="/games/timeslot?eventid=${event.eventid}">Add Timeslots</a></div>
							<div><form action="delete-event" method="post" style="display: inline-block;">
								<input type="hidden" name="eventid" value="${event.eventid}">
								<button type="submit" class="btn btn-danger">Delete Event</button>
							</form></div>
							<div><a class="btn btn-warning" href="/games/update-event?eventid=${event.eventid}">Update Event</a></div>
							<br>
							<br>
						</td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>
</body>
</html>
