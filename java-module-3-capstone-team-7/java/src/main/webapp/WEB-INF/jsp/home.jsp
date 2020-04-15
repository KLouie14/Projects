<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<c:import url="/WEB-INF/jsp/common/header.jsp"></c:import>
<c:forEach var="park" items="${parks}">
<div class="parkInfo">

<div>
	<c:url value="/detailPage" var="detailPage">
	<c:param name="parkCode" value="${park.parkCode}"/>
	</c:url>
	<c:url value="/img/parks/${fn:toLowerCase(park.parkCode)}.jpg" var="logoURL"></c:url>
	<a href="${detailPage}"><img src="${logoURL}" id="logo"/></a>
	<hr>
</div>
<div id="home-text">
<h2>${park.parkName}</h2>
<p>${park.parkDescription}</p>
</div>
</div>
</c:forEach>








<c:import url="/WEB-INF/jsp/common/footer.jsp"></c:import>