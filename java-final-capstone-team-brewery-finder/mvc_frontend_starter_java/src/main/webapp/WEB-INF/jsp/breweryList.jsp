<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<c:import url="/WEB-INF/jsp/common/header.jsp" />

<title>Brewery Finder - List of Breweries</title>
<!-- What shows up in the tab in the web browser -->


<body>
	
	
	<h2 class="text">Thirsty?</h2>
	<h4 class="text">Use BrewFinder to locate breweries, rate beers, and more!</h4>

	  <c:url value="/breweryDetails" var="breweryDetails"> <!-- variable for the brewery detail page... -->
			<c:param name="userName" value="${currentUser.userName}" /> <!-- passing the brewery id as a parameter (param name has to be the same as the parameter passed in the method)-->
		</c:url> 
 	<form action="${breweryDetails}">
	
	<label for="breweryList">Select a Brewery:</label> 
	
		<select name="id" id="breweryDropDown">
	
			<c:forEach items="${allBreweries}" var="aBrewery">
	
			<option value="${aBrewery.id}">${aBrewery.name}</option>
	
			</c:forEach>
			
		<div class="input-review-hidden">
		<input type="hidden" name="userName" value="${currentUser.userName}"/>
	   </div> 
	
		</select>
		
			<input class="forSubmitButton" type="submit" value="Go To Brewery"/>
		
		</form>
	<div class="area">
	<div class="info-text">
		<h4 class="aboutSite"> Where Beer Lovers and Brewers Unite!</h4>
		<p class="beerLoverAbout">Are you a beer lover? Sign-up today to discover your next brewery or beer!</p>
		<p class="brewerAbout">Are you a Brewer? Sign-up your brewery today to be featured on our page for all beer lovers to find!</p>
		<p class="loveABeer">Love a beer?</p>
		<p class="leaveAReview">Don't forget to leave a review!</p>
	
	</div>
	<table>
	
	
    <!-- get 'allBreweries' from controller and put it in a variable called aBrewery to iterate over -->
	<c:forEach var="aBrewery" items="${allBreweries}">
		
		
		<c:url value="/breweryDetails" var="breweryDetails"> <!-- variable for the brewery detail page... -->
			<c:param name="id" value="${aBrewery.id}" />
			<c:param name="userName" value="${currentUser.userName}" /> <!-- passing the brewery id as a parameter (param name has to be the same as the parameter passed in the method)-->
		</c:url> 
		
		<tr>
		
		<td class="breweryImage">
	
		<c:url value="/img/${aBrewery.id}.jpg" var="breweryPicSrc" />
		
		<a href="${breweryDetails}"> <img src="${breweryPicSrc}"
			alt="Brewery Picture" />
		</a>
		</td>
		
		<td>

		<a href="${breweryDetails}">
			<h2 id=breweryName>${aBrewery.name}</h2>
		</a>
		<p id=breweryAddress>${aBrewery.address}</p>
		<p id=breweryHistory>${aBrewery.history}</p>
		</td>
        </tr>
	</c:forEach>
	</table>
	</div>
	<c:import url="/WEB-INF/jsp/common/footer.jsp" />
