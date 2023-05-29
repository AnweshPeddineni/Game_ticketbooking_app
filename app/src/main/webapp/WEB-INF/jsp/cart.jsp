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
<body class="container-fluid">
    <h1>My Cart</h1>
    <a href="/games/user" class="btn btn-primary">Go back to home page</a>
    <div>
        <table cellpadding="5" class="table table-bordered">
            <caption><h2>List of Tickets</h2></caption>
            <tr>
                <th>Event Name</th>
                <th>Start Time</th>
                <th>End Time</th>
                <th>Positions Booked</th>
                <th>Status</th>
                <th>Action</th>
            </tr>
            <c:forEach var="ticket" items="${tickets}">
                <tr>
                    <td><c:out value="${ticket.event.eventname}" /></td>
                    <td><c:out value="${ticket.timeslot.startTime}" /></td>
                    <td><c:out value="${ticket.timeslot.endTime}" /></td>ō
                    <td><c:out value="${ticket.positions}" /></td>
                    <td><c:out value="${ticket.status}" /></td>
                    <td><a href="/games/cancel?ticketid=${ticket.ticketid}">Cancel Ticket</a></td>
                </tr>
            </c:forEach>
        </table>
    </div>
</body>
</html>
