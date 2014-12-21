<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page session="false" %>
<html>
<head>
	<link rel="stylesheet" type="text/css" href="resources/css/nav.css" />
	<title>Search Flights</title>
</head>
<body>
<h1>
	<fmt:message key="home.title"/>
</h1>
<form:form action="./searchFlight.html" method="POST" commandName="flight">
    <table>
    	<tr>
			<td><form:label path="from">From:</form:label></td><td><form:input path="from"/></td>
			<td><form:errors path="from" cssClass="error" /></td>
		</tr>	
		<tr>
			<td><form:label path="to">To:</form:label></td><td><form:input path="to"/></td>
			<td><form:errors path="to" cssClass="error" /></td>
		</tr>
		<tr>		
			<td><form:label path="depart">Depart:</form:label></td><td><form:input path="depart"/></td>
			<td><form:errors path="depart" cssClass="error" /></td>
		</tr>
		<tr>
			<td><input type="submit" value="search"/></td>
		</tr>	
	</table> 
</form:form>
</body>
</html>
