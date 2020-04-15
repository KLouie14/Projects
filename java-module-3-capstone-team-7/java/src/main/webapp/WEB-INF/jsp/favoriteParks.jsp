<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<c:import url="/WEB-INF/jsp/common/header.jsp"></c:import>



<c:forEach var="park" items="${parks}">
<div id="favorites-row">
<div id="favorite-image-td">
<c:url value="/img/parks/${fn:toLowerCase(park.key.parkCode)}.jpg" var="logoURL"></c:url> <!-- changed parkCodes to lowercase so that they could be match the file name in eclipse -->
<img src="${logoURL}" id="logo-favorite-parks"/>		 <!-- created url and passed in variable to img src in el expression -->		
</div>
<div id="favorites-park-name">${park.key.parkName}</div>
<div id="favorites-park-surveys">${park.value}</div>
</div>
</c:forEach>
















<c:import url="/WEB-INF/jsp/common/footer.jsp"></c:import>


