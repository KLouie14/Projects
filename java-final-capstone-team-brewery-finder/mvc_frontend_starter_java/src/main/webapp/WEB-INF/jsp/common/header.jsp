
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<title>Brewery Finder</title>
<c:url var="bootstrapCss" value="/css/bootstrap.min.css" />
<c:url var="siteCss" value="/css/site.css" />

<c:url var="jQueryJs" value="/js/jquery.min.js" />
<c:url var="jqValidateJs" value="/js/jquery.validate.min.js" />
<c:url var="jqvAddMethJs" value="/js/additional-methods.min.js" />
<c:url var="jqTimeagoJs" value="/js/jquery.timeago.js" />
<c:url var="popperJs" value="/js/popper.min.js" />
<c:url var="bootstrapJs" value="/js/bootstrap.min.js" />

<link rel="stylesheet" type="text/css" href="${bootstrapCss}">
<link rel="stylesheet" type="text/css" href="${siteCss}">

<script src="${jQueryJs}"></script>
<script src="${jqValidateJs}"></script>
<script src="${jqvAddMethJs}"></script>
<script src="${jqTimeagoJs}"></script>
<script src="${popperJs}"></script>
<script src="${bootstrapJs}"></script>

<script type="text/javascript">
	$(document).ready(function() {
		$("time.timeago").timeago();

		$("#logoutLink").click(function(event) {
			$("#logoutForm").submit();
		});

		var pathname = window.location.pathname;
		$("nav a[href='" + pathname + "']").parent().addClass("active");

	});
</script>

</head>
<body>


		<div class="collapse navbar-collapse" id="navbarSupportedContent">
			<ul class="headerUl">
				

				<c:if test="${not empty currentUser}">
					<li class="headerLi"><a href="/MVC_Frontend_Java">Home</a></li>
					<c:url var="dashboardHref" value="/users/${currentUser}" />
					
			
					<c:if test="${currentUser.role=='admin'}">
					<c:url value="/addBrewery" var="addBrewery">
						<c:param name="userName" value="${currentUser.userName}"/>
					</c:url>
					<li class="headerLi">
						<a href="${addBrewery}">Create New Brewery</a>
					</li>
				</c:if>
		
				
				
					
					
				</c:if>
			</ul>
			<ul class="headerUl">
				<c:choose>
					<c:when test="${empty currentUser}">
						<c:url var="homePageHref" value="/" />
						<li class="headerLi"><a href="${homePageHref}">Home</a></li>
						<c:url var="newUserHref" value="/users/new" />
						<li class="headerLi"><a href="${newUserHref}">Sign Up</a></li>
						<c:url var="loginHref" value="/login" />
						<li class="headerLi"><a href="${loginHref}">Log In</a></li>
					</c:when>
					<c:otherwise>
						<c:url var="logoutAction" value="/logout" />
						<form id="logoutForm" action="${logoutAction}" method="POST">
							<input type="hidden" name="CSRF_TOKEN" value="${CSRF_TOKEN}" />
						</form>
						<c:url value="/logout" var="homePage" />
						<li class="logOutButtonLi"><a id="logoutLink" href='#'>Log Out</a></li>
						
					</c:otherwise>
				</c:choose>
			</ul>
			
			
		</div>
	</nav>

	<c:if test="${not empty currentUser}">
		<p id="currentUser">Current User: ${currentUser.userName}</p>
	</c:if>
	<div class="container"> 
	
 
	</div>