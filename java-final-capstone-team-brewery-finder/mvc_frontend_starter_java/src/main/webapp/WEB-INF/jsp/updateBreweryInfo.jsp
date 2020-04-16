<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<c:import url="/WEB-INF/jsp/common/header.jsp" />

<div class="wholeBody">

<c:url var="updateBreweryInfo" value="/updateBreweryInfo">
	<c:param name="id" value="${brewery.id}" /> <!-- passing the brewery id as a parameter (param name has to be the same as the parameter passed in the method)-->
	<c:param name="userName" value="${currentUser.userName}"/>
</c:url>
<form action="${updateBreweryInfo}" method="POST" >
	<input type="hidden" name="CSRF_TOKEN" value="${CSRF_TOKEN}"/>
	
		
	
<div class="input-name" >
		<label for="name">Name:</label>
		<input name="name" value="${brewery.name}"/>
	</div>
	<div class="input-address">
		<label for="address">Address:</label>
		<input name="address" value="${brewery.address}"/>
	</div>
	<div class="input-history">
		<label for="history">Brewery History:</label>
		<input name="history" value="${brewery.history}"/>
	</div>
	<div class="input-webAddress" >
		<label for="webAddress">Web Address:</label>
		<input name="webAddress" value="${brewery.webAddress}"/>
	</div>
	<div class="input-phoneNumber" >
		<label for="phoneNumber">Phone Number:</label>
		<input name="phoneNumber" value="${brewery.phoneNumber}"/>
	</div>
	<div class="input-timeOfOperation">
		<label for="timeOfOperation">Hours of Operation:</label>
		<input name="timeOfOperation" value="${brewery.timeOfOperation}"/>
	</div>
	
	
	

	



	<button type="submit" class="btn btn-default">Update Information</button>
</form>


</div>



<c:import url="/WEB-INF/jsp/common/footer.jsp" />