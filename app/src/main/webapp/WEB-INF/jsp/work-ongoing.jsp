<!DOCTYPE html>
<html>
<head>
	<title>Work in Progress</title>
	<style>
		body {
			margin: 0;
			padding: 0;
			font-family: Arial, sans-serif;
			background-color: #f7f7f7;
		}

		.container {
			display: flex;
			flex-direction: column;
			justify-content: center;
			align-items: center;
			height: 100vh;
		}

		.message {
			font-size: 36px;
			font-weight: bold;
			color: #444;
			margin-bottom: 20px;
		}

		.spinner {
			border: 16px solid #f3f3f3;
			border-top: 16px solid #3498db;
			border-radius: 50%;
			width: 120px;
			height: 120px;
			animation: spin 2s linear infinite;
			margin-bottom: 20px;
		}

		@keyframes spin {
			0% { transform: rotate(0deg); }
			100% { transform: rotate(360deg); }
		}
	</style>
</head>
<body>
	<div class="container">
		<div class="message">Work in Progress</div>
		<div class="spinner"></div>
	</div>
</body>
</html>
