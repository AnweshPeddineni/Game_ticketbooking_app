<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Add Time Slot</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/5.0.2/css/bootstrap.min.css">
</head>
<body>
    <div class="container">
        <h1 class="mt-5">Add time slots for event ${event.eventname} with eventid ${eventid} on ${event.eventdate}</h1>
        <hr>
        <h2 class="mb-4">Add Time Slot</h2>
        <form method="post" action="savetimeslot">
            <div class="mb-3">
                <label for="startTime" class="form-label">Start Time:</label>
                <input type="time" id="startTime" name="startTime" class="form-control">
            </div>
            <div class="mb-3">
                <label for="endTime" class="form-label">End Time:</label>
                <input type="time" id="endTime" name="endTime" class="form-control">
            </div>
            <div class="mb-3">
                <label for="price" class="form-label">Price:</label>
                <input type="number" id="price" name="price" step="0.01" class="form-control">
            </div>
            <div class="mb-3">
                <label for="availablePositions" class="form-label">Available Positions:</label>
                <input type="number" id="availablePositions" name="availablePositions" step="1" class="form-control">
            </div>
            <input type="hidden" name="eventid" value="${eventid}">
            <button type="submit" class="btn btn-primary">Save</button>
        </form>
    </div>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/5.0.2/js/bootstrap.bundle.min.js"></script>
</body>
</html>

