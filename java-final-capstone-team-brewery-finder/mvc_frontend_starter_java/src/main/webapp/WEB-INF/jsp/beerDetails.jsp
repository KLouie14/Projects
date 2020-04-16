<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<c:import url="/WEB-INF/jsp/common/header.jsp" />


	<div>
		
		<div class="beerDetails">
			
			<c:set value="${beers}" var="beer"></c:set>
			<ul>
				<li class="nameOfBeer"><strong><c:out value="${beer.name}"/></strong></li>
				<li><strong>Description: </strong><c:out value="${beer.description}"/></li>
				<li><strong>ABV: </strong><c:out value="${beer.abv}"/></li>
				<li><strong>Style: </strong><c:out value="${beer.beerStyle}"/></li>
				<c:set value="${beerRating}" var="rating"></c:set>
				<li><strong>Average Rating: </strong>${rating}</li>
			</ul>		
			
		
		</div>
		
		
		
	<!-- 	<table class="table">
		<tr>
			<th>Name</th>
			<th>Description</th>
			<th>ABV</th>
			<th>Beer Style</th>
		
		</tr>
		<c:set value="${beers}" var="beer"></c:set>
			<tr>			
				<td><c:out value="${beer.name}"/></td>
				<td><c:out value="${beer.description}"/></td>
				<td><c:out value="${beer.abv}"/></td>
				<td><c:out value="${beer.beerStyle}"/></td>
				
				
			</tr>		-->
		
		
		
		
	</div>
	
	<!-- add feature that shows a count of each rating there is -->
	<div>
	<table>
	<c:forEach items="${listOfReviews}" var="aReview">
	<tr>
	<td>
	<p>UserName: ${aReview.reviewerName}</p>
	<p>Review: ${aReview.beerReview}</p>
	<p>Rating</p>
	<c:forEach begin="1" end="${aReview.rating}">
	<img src="img/star.png">
	</c:forEach>
	</td>
	</tr>
	
	</c:forEach>
	</table>
	</div>
	
	
	
	<div>

	<c:url var="leaveReview" value="/beerDetails" />
	<form action="${leaveReview}" method="POST">
	<input type="hidden" name="CSRF_TOKEN" value="${CSRF_TOKEN}"/>
	
	<!-- <div class="input-review-hidden">
		<label for="reviewId"></label>
		<input type="hidden" name="reviewId" />
	</div> --> 
	
	<div class="input-review-hidden">
		<label for="reviewerName"></label>
		<input type="hidden" name="reviewerName" value="${currentUser.userName}"/>
	</div> 
	
	<div class="input-review-hidden">
		<label for="beerId"></label>
		<input type="hidden" name="beerId" value="${beer.beerId}"/>
	</div> 
	
	<h4>Leave a Review!</h4>
	
	<div class="input-review">
		<label for="rating">Rating</label>
		<select name="rating">
		<option value="1">1</option>
		<option value="2">2</option>
		<option value="3">3</option>
		<option value="4">4</option>
		<option value="5">5</option>
		</select>
		
	</div>
	
	<div class="input-review">
		<label for="beerReview">Review</label>
		<input name="beerReview" placeholder="Review"/>
	</div> 
	
	 <input type="submit" class="btn btn-default">Add Review</input>
	
	</form>
	</div>
	
	
	<c:import url="/WEB-INF/jsp/common/footer.jsp" />