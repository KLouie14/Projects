<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:import url="/WEB-INF/jsp/common/header.jsp" />


<div>
<h1 id="detail-brewery-name">${theBrewery.name}</h1>
</div>

<div class="breweryDetailPageImage">
<c:url value="/img/${theBrewery.id}.jpg" var="breweryPicSrc" />

<img src="${breweryPicSrc}"
	alt="Brewery Picture" />

</div>

<div id="web-address">
<a href="https://www.${theBrewery.webAddress}">
<h4>${theBrewery.webAddress}</h4>
</a>
</div>

<div id="hours">
<h4>Hours of Operation</h4>
<p>${theBrewery.timeOfOperation}</p>
</div>

<div id="contact">
<h4>Contact</h4>
<p>${theBrewery.phoneNumber}</p>
</div>

<div id ="address">
<p>${theBrewery.address}</p>
</div>

<div id="history">
<p>${theBrewery.history}</p>
</div>




<div class="beerName">
		<table class="table">
		<tr>
			<th>Our Beer List</th>
		</tr>
		<c:forEach items="${allBeers}" var="beer">
		
		
<c:url value="/beerDetails" var="beerDetails"> <!-- variable for the brewery detail page... -->
	<c:param name="beerId" value="${beer.beerId}" /> <!-- passing the brewery id as a parameter (param name has to be the same as the parameter passed in the method)-->
	<c:param name="userName" value="${currentUser.userName}"/>
</c:url>
			<tr>			
				<td><a href="${beerDetails}"><c:out value="${beer.name}"/></a></td>
			</tr>
		</c:forEach>
		</table>
	</div>


	
<c:url value="/updateBeerList" var="updateBeerList"> 
	<c:param name="breweryId" value="${theBrewery.id}" /> <!-- passing the brewery id as a parameter (param name has to be the same as the parameter passed in the method)-->
	<c:param name="userName" value="${currentUser.userName}"/>
</c:url>

<c:if test="${(currentUser.role=='brewer') || (currentUser.role=='admin')}">
<div class="updateBeer">
	<a href="${updateBeerList}">Update Beer List</a>
</div>
</c:if>

<c:url value="/updateBreweryInfo" var="updateBreweryInfo"> 
	<c:param name="breweryId" value="${theBrewery.id}" /> <!-- passing the brewery id as a parameter (param name has to be the same as the parameter passed in the method)-->
	<c:param name="userName" value="${currentUser.userName}"/>
</c:url>

<c:if test="${(currentUser.role=='brewer') || (currentUser.role=='admin')}">
<div class="updateBreweryInfo">
	<a href="${updateBreweryInfo}">Update Brewery Info</a>
</div>
</c:if>





<c:import url="/WEB-INF/jsp/common/footer.jsp" />