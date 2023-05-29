<!DOCTYPE html>
<html>

<head>
	<meta charset="UTF-8">
	<title>User Sign Up</title>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
</head>

<body>
	<div class="container">
		<h1>User Sign Up</h1>
		<form method="post" action="signup">
			<div class="form-group">
				<label for="name">Name:</label>
				<input type="text" id="name" name="name"  class="form-control" required>
			</div>
			<div class="form-group">
				<label for="email">Email:</label>
				<input type="email" id="email" name="email" class="form-control" required>
			</div>
			<div class="form-group">
				<label for="password">Password:</label>
				<input type="password" id="password" name="password" class="form-control" required>
			</div>
			<button type="submit" class="btn btn-primary">Sign Up </button>
		</form>
	</div>
</body>
</html>