<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page session="false" %>
<html>
<head>
	<title>Search Flights</title>
</head>
<body>
<h1>
	Enter the Details
</h1>
<form:form action="./searchFlight.html" method="POST" commandName="flight">
    <table>
    	<tr>
			<td><form:label path="from">From:</form:label></td><td><form:input path="from"/></td>
			<form:errors path="from" cssClass="error" />
		</tr>	
		<tr>
			<td><form:label path="to">To:</form:label></td><td><form:input path="to"/></td>
			<form:errors path="to" cssClass="error" />
		</tr>
		<tr>		
			<td><form:label path="depart">Depart:</form:label></td><td><form:input path="depart"/></td>
			<form:errors path="depart" cssClass="error" />
		</tr>
		<tr>
			<td><input type="submit" value="search"/></td>
		</tr>	
	</table> 
</form:form>
</body>
</html>
