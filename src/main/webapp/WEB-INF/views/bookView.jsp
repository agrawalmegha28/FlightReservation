<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
	<link rel="stylesheet" type="text/css" href="resources/css/nav.css" />
	<title>Book This Flight</title>
</head>
<body>
<h1>
	<fmt:message key="flight.detail"/>
</h1>
<form:form action="./makeReservation.html" method="POST" commandName="reservation">
    <form:input type="hidden" path="flight.id" />
    <form:input type="hidden" path="flight.price" />
    <form:input type="hidden" path="flight.from" />
    <form:input type="hidden" path="flight.to" />
    <form:input type="hidden" path="flight.name" />
    <form:input type="hidden" path="flight.depart" />
    <form:errors path="flight.depart" cssClass="error" />
    
    
    
    <table>
    	<tr>
	    	<th>From</th>
	    	<th>To</th>
	    	<th>Depart</th>
	    	<th>Flight</th>
	    	<th>Price</th>
        </tr>
    	<tr>
    		<td>${reservation.flight.from}</td>
      		<td>${reservation.flight.to}</td>
      		<td>${reservation.flight.depart}</td>
      		<td>${reservation.flight.name}</td>
      		<td>${reservation.flight.price}</td>
    	</tr>
    </table>
    <h1>
		<fmt:message key="bookflight.title"/>
	</h1>
    <table>
    	<tr>
    		<th>First Name</th>
    		<th>Middle Name</th>
    		<th>Last Name</th>
    		<th>Date of Birth</th>
    		<th>Gender</th>
    	</tr>
     <c:forEach var="passenger" items="${reservation.passenger}" varStatus="status">
      <tr>
      	<td><input name="passenger[${status.index}].fName" value="${passenger.fName}"/></td>
      	<td><input name="passenger[${status.index}].mName" value="${passenger.mName}"/></td>
      	<td><input name="passenger[${status.index}].lName" value="${passenger.lName}"/></td>
      	<td><input name="passenger[${status.index}].dob" value="${passenger.dob}"/></td>
      	<td><input name="passenger[${status.index}].gender" value="${passenger.gender}"/></td>
      	<form:errors path="passenger[${status.index}].gender" cssClass="error" />
      </tr>
    </c:forEach>
    </table>
    <input type="submit" value="Confirm"/> 
</form:form>
</body>
</html>