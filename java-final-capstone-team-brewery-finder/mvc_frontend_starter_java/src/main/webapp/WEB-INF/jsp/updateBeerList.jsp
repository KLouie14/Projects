<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<c:import url="/WEB-INF/jsp/common/header.jsp" />
<div class="wholeBody">
<div class="addBeerOption">
	<c:url var="addBeer" value="/updateBeerList" />
	<form action="${addBeer}" method="POST" >
	<input type="hidden" name="CSRF_TOKEN" value="${CSRF_TOKEN}"/>

	
	<div class="input-beerName">
		<label for="name">Name:</label>
		<input class="input-name-beer" name="name"/>
	</div>
	<div class="input-beerDescription">
		<label for="description">Description:</label>
		<input class="input-description" name="description"/>
	</div>
	<div class="input-beerABV">
		<label for="abv">ABV:</label>
		<input class="input-abv" name="abv"/>
	</div>
	<div class="input-beerStyle">
		<label for="beerStyle">Style:</label>
		<input class="input-style" name="beerStyle"/>
	</div>
	<div>
	<label for="breweryId"></label>
	<input type="hidden" name="breweryId"  value="${brewery.id}"/> 
	</div>


	 <button type="submit" class="btn btn-default">Add Beer</button>
	
	</form>
	
	</div>
	
	<c:url var="deleteBeer" value="/deleteBeer"></c:url>
	<form action="${deleteBeer}" method="POST" >
	<input type="hidden" name="CSRF_TOKEN" value="${CSRF_TOKEN}"/>
	<input type="hidden" name="breweryId" value="${brewery.id}"/>
	<input type="hidden" name="beerList" value="${beerList}"/>
	<!-- passing the brewery id as a parameter (param name has to be the same as the parameter passed in the method)-->
	<div class="deleteBeerOption">
	<div>
	<c:forEach items="${rankedBeers}" var="aRanking">
		<input type="checkbox" name="beerList" value="${aRanking[0]}">
		<label for="beerList">Rating: ${aRanking[2]}, Beer: ${aRanking[1]}</label><br>	
	</c:forEach>
	</div>
    </div>
	
	<button type="submit" class="btn btn-default">Delete Beer</button>
	
	</form>
	</div>
	
	
	




	<c:import url="/WEB-INF/jsp/common/footer.jsp" />