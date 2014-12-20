<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Search</title>
</head>
<body>
<h1>
	Search Your Flight  
</h1>
<table>
    <c:forEach var="flight" items="${flightList}">
      <tr>
      <td>${flight.from}</td>
      <td>${flight.to}</td>
      <td>${flight.depart}</td>
      </tr>
      </c:forEach>
      </table>
</body>
</html>
