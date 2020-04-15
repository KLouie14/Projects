<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:import url="/WEB-INF/jsp/common/header.jsp"></c:import>

<c:set scope="request" var="park" value="${park}"></c:set>  		<!-- set variable park to grab values from the map with key park -->

<div>
<c:url value="/img/parks/${fn:toLowerCase(park.parkCode)}.jpg" var="parkImage"></c:url>
<img src="${parkImage}" id="detail-park-image"/>
</div>

<div>
<h3 id="detail-park-name">${park.parkName}</h3>
</div>

<div>
<p id="detail-park-desc">${park.parkDescription}</p>
</div>

<div>
<p id="detail-park-quote">${park.inspirationalQuote}</p>
<p id="detail-park-quotesource">-${park.inspirationalQuoteSource}</p>
</div>

<div>
<table>
<tr>
</tr>
<tr id="detail-table-headings">
<tr>
<td id="table-header">State</td>
<td>${park.state}</td>
</tr>
<tr>

<td id="table-header">Acreage</td>
<td>${park.acreage}</td>
</tr>
<tr>
<td id="table-header">Elevation in feet</td>
<td>${park.elevationInFeet}</td>
</tr>

<tr>
<td id="table-header">Miles of trail</td>
<td>${park.milesOfTrail}</td>
</tr>

<tr>
<td id="table-header">Number of campsites</td>
<td>${park.numberOfCampSites}</td>
</tr>
<tr>
<td id="table-header">Climate</td>
<td>${park.climate}</td>
</tr>
<tr>
<td id="table-header">Year founded</td>
<td>${park.yearFounded}</td>
</tr>
<tr>
<td id="table-header">Annual visitor count</td>
<td>${park.annualVisitorCount}</td>
</tr>
<tr>
<td id="table-header">Number of animal species</td>
<td>${park.numberOfAnimalSpecies}</td>
<tr>
<td id="table-header">Entry fee</td>
<td>${park.entryFee}</td>
</tr>

</table>

</div>


<c:url var="detailPage" value="/detailPage">				<!-- param called parkCode in /detailpage url that will be obtained when user inputs data -->
<c:param name="parkCode" value="${park.parkCode}"/>
</c:url>
<form action="${detailPage}" method="POST">					<!-- Post data from user in detailpage -->


<input type="radio" id="Celsius" name="degreeType" value="Celsius"> 		<!-- create button for celsius and fahrenheit with a submit button -->
<label for="Celsius">Celsius</label>
<input type="radio" id="Fahreneit" name="degreeType" value="Fahreneit">
<label for="Fahreneit">Fahrenheit</label>
<input type="submit" name="submit"/>
</form>





<p>${degreeType}</p>							
<div id="detail-forecast">
<c:forEach var="forecast" items="${forecasts}">				<!-- loop through forecast session map and set a variable called forecast-->
<c:set var="high" value="${forecast.high}"/>				<!-- set variables for high and low forecasts -->
<c:set var="low" value="${forecast.low}"/>
<c:if test="${degreeType == 'Celsius'}">					<!-- if the degreeType is celsius, forecast values from database are calculated into celsius values -->			

<fmt:formatNumber type="number" var="high" maxFractionDigits="0" value="${(forecast.high-32)*(5/9)}"/>
<fmt:formatNumber type="number" var="low" maxFractionDigits="0" value="${(forecast.low-32)*(5/9)}"/>
</c:if>
		
	<c:set var="id" value ="${(forecast.fiveDayForecastValue == 1) ? 'today' : 'notToday'}"/> 		<!-- turnary operator used to create a different id (when iterated through the loop) to create a different div to be manipulated in css-->
				<!-- through the loop, if fivedayforecase is 1, id will be 'today' -->
				<!-- through the loop, if fivedayforecase is not 1, id be 'notToday'-->
<div id="${id}">										
	<c:if test="${forecast.fiveDayForecastValue == 1}">
	<div>
		<p id="today-header"> Today </p>
		</div>
	</c:if>
	<div id="forecast-image${id}">
	<c:choose>																				
		<c:when test="${forecast.forecast == 'partly cloudy'}">									<!-- if forecast = partly cloudy, used set to replace C and the space so we could match the name from the database to the image file -->
		<c:set value="${fn:replace(forecast.forecast,'c', 'C')}" var="pcImage"/>
		<c:url value="/img/weather/${fn:replace(pcImage,' ', '')}.png" var="forecastImage"/> <!-- insert the el expression (which gave the name of image we want) in the value to get the correct path to the image-->
		<img src="${forecastImage}"/>
		</c:when>
		<c:otherwise>
		<c:url value="/img/weather/${forecast.forecast}.png" var="forecastImage"/>  <!-- else, just use regular names given from the database -->
		<img src="${forecastImage}"/>
		</c:otherwise>
		</c:choose>

	</div>
	
	<div id="high-low${id}">
		<div id="high${id}">
			<p>High </p> <p id="temp${id}">${high}</p>
		</div>
		<div id="low${id}">
			<p>Low </p> <p id="temp${id}">${low}</p>
		</div>
	</div>
	<div id="advisories">
	<c:if test="${forecast.fiveDayForecastValue == 1}">		
		<!-- if the fivedayforecastvalue = 1 (for today), used if statements to display messages for types of forecasts-->

	<c:if test="${forecast.forecast == 'snow'}">
		<p>Pack your snowshoes.</p>
	</c:if>
	<c:if test="${forecast.forecast == 'rain'}">
		<p>Pack your raingear and waterproof shoes.</p>
	</c:if>
	<c:if test="${forecast.forecast == 'thunderstorms'}">
		<p>Seek shelter and avoid hiking on exposed ridges.</p>
	</c:if>
	<c:if test="${forecast.forecast == 'sunny'}">
		<p>Pack your sunblock.</p>
	</c:if>
	<c:if test="${forecast.high > 75}">
		<p>Bring an extra gallon of water.</p>
	</c:if>
	<c:if test="${forecast.high - forecast.low > 20}">
		<p>Wear breathable layers.</p>
	</c:if>
	<c:if test="${forecast.low < 20}">
		<p>Be careful of exposure to frigid temperatures.</p>
	</c:if>
</c:if>
</div>
</div>

</c:forEach>



</div>




<c:import url="/WEB-INF/jsp/common/footer.jsp"></c:import>