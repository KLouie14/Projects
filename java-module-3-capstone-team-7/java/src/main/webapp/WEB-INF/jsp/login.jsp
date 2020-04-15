<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<c:import url="/WEB-INF/jsp/common/header.jsp"></c:import>

<button onclick="showPassword()"> Forgot Password? </button>
<div id="password-hint" style="display:none">${hint}</div>

<c:url var="loginUrl" value="/login"/>
<form action="${loginUrl}" method="POST">   <!-- created form to set method to post -->
    <div class="form-group">
        <label for="username">Username</label>
        <input type="text" class="form-control" id="username" name="username" placeholder="Username">
    </div>
    <div class="form-group">
        <label for="password">Password</label>
        <input type="password" class="form-control" id="password" name="password" placeholder="Password">
    </div>
    <button type="submit" class="btn btn-default">Login</button>
</form>
<fmt:parseDate value="${lastLogin}" pattern="yyyy-MM-dd'T'HH:mm:ss" var="parsedDateTime" type="both"/> <!-- lastlogin taken from key in controller, formatted date -->
<fmt:formatDate pattern="dd/MM/yyyy 'at' HH:mm:ss" value="${ parsedDateTime }" var="formattedDateTime"/>
<p>${message}</p>
<div id="login-date">
<p>Last login at ${formattedDateTime}</p>
</div>


<script>
function showPassword() {
	  var x = document.getElementById("password-hint");
	  if (x.style.display === "none") {
	    x.style.display = "block";
	  } else {
	    x.style.display = "none";
	  }
}
</script>

<c:import url="/WEB-INF/jsp/common/footer.jsp"></c:import>