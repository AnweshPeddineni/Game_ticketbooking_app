<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>User Details</h1>
        User ID: ${sessionScope.user.userid}<br/>
        User Name: ${sessionScope.name}<br/>
        Email: ${sessionScope.user.email}<br/>
    </body>
</html>