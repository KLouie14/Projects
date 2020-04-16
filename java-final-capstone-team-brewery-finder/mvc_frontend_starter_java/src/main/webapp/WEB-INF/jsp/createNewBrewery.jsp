<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<c:import url="/WEB-INF/jsp/common/header.jsp" />

<div class="addBrewery">
<c:url var="createNewBrewery" value="/addBrewery"></c:url>
<form action="${createNewBrewery}" method="POST" >
	<input type="hidden" name="CSRF_TOKEN" value="${CSRF_TOKEN}"/>
	<div class="input-name">
		<label for="name">Name:</label>
		<input name="name"/>
	</div>
	<div class="input-webAddress">
		<label for="webAddress">Web Address:</label>
		<input name="webAddress"/>
	</div>
	<div class="input-phoneNumber">
		<label for="phoneNumber">Phone Number:</label>
		<input name="phoneNumber"/>
	</div>
	<div class="input-history">
		<label for="history">Brewery History:</label>
		<input name="history"/>
	</div>
	<div class="input-timeOfOperation">
		<label for="timeOfOperation">Hours of Operation:</label>
		<input name="timeOfOperation"/>
	</div>
	<div class="input-address">
		<label for="address">Address:</label>
		<input name="address"/>
	</div>
	<div class="input-brewery-hidden">
		<label for="userName"></label>
		<input type="hidden" name="userName" value="${currentUser.userName}"/>
	</div> 


	<button type="submit" class="btn btn-default">Add Brewery</button>
</form>
</div>


<c:import url="/WEB-INF/jsp/common/footer.jsp" />