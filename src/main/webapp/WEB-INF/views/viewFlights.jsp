<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<link rel="stylesheet" type="text/css" href="resources/css/nav.css" />
	<title>Search</title>
</head>
<body>
<h1>
	Book Your Flight  
</h1>
 <table>
    <tr>
    	<th>&nbsp;</th>
    	<th>From</th>
    	<th>To</th>
    	<th>Depart</th>
    	<th>Flight</th>
    	<th>Price</th>
    </tr>
    <c:forEach var="flight" items="${flightList}">
      <tr>
      	<td><input type="button" value="Book" onclick="location.href='book.html?flightId=${flight.id}';"></td>
      	<td>${flight.from}</td>
      	<td>${flight.to}</td>
      	<td>${flight.depart}</td>
      	<td>${flight.name}</td>
      	<td>${flight.price}</td>
      </tr>
    </c:forEach>
 </table>
</body>
</html>
