<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Find Reservation Code</title>
</head>
<body>
<h1>
	Enter the Reservation Code 
</h1>
<form name="GetReservationForm" action="./listReservation.html" method="post">
Reservation Code: <input type="TEXT" name="reservationCode"> 
<input type="submit" value="Lookup">
</form>
</body>
</html>
