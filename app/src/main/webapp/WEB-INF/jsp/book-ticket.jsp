
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta http-equiv="X-UA-Compatible" content="ie=edge">
	<title>Book Tickets</title>
	<!-- Bootstrap CSS -->
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>
<body class="container">
	<div >
		<h1 class="my-4">Book tickets for ${event.eventname} on ${event.eventdate}!</h1>
		<form method="post" action="process-payment">
			<table class="table table-bordered">
				<tr>
					<td><label for="positions">Number of Tickets:</label></td>
					<td><input type="number" id="positions" name="positions" class="form-control" onchange="calculateTotal()"/></td>
				</tr>
				<tr>
					<td><label for="price">Price per Ticket:</label></td>
					<td>${timeslot.price}</td>
				</tr>
				<tr>
					<td><label for="totalPrice">Total Price:</label></td>
					<td><span id="totalPrice"></span></td>
				</tr>
				<tr>
					<td colspan="2"><button type="submit" class="btn btn-primary">Pay Now</button></td>
				</tr>
			</table>
            <input type="hidden" name="eventid" value="${event.eventid}">
            <input type="hidden" name="timeslotid" value="${timeslot.timeslotid}">
		</form>
	</div>

    <script>
        function calculateTotal() {
          const positionsEl = document.getElementById('positions');
          const totalPriceEl = document.getElementById('totalPrice');
          const eprice = `${timeslot.price}`;
          const quantity = positionsEl.value;
          totalPriceEl.textContent = eprice * quantity;
        }
        </script>

</body>
</html>
