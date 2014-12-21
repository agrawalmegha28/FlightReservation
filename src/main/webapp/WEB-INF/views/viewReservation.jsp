<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
	<title>Reservation Details</title>
	<link rel="stylesheet" type="text/css" href="resources/css/nav.css" />
</head>
<body>
<h1>
	Reservation Details
</h1>
    <table>
    <c:if test="${not empty code}">
    	<tr>
	      <td>
	      	Reservation Code:
	      </td>
	      <td>
	         ${code}
	      </td>
      </tr>
	</c:if>
    <tr>
	      <td>
	      	Flight Name:
	      </td>
	      <td>
	         ${flight.name}
	      </td>
      </tr>
      <tr>
	      <td>
	      	Flight From:
	      </td>
	      <td>
	         ${flight.from}
	      </td>
    </tr>
    <tr>
	      <td>
	      	Flight To:
	      </td>
	      <td>
	         ${flight.to}
	      </td>
    </tr>
    <tr>
	      <td>
	      	Flight Depart:
	      </td>
	      <td>
	         ${flight.depart}
	      </td>
      </tr>
      <tr>
	      <td>
	      	Flight Price:
	      </td>
	      <td>
	         ${flight.price}$
	      </td>
      </tr>
      
      <c:forEach var="curPassenger" items="${passengerList}">
      <tr>
	      <td>${curPassenger.fName}</td>
	      <td>${curPassenger.lName}</td>
      </tr>
      </c:forEach>
    
    </table>
</body>
</html>