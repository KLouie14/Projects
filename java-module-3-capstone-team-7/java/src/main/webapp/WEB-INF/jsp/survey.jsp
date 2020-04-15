<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:import url="/WEB-INF/jsp/common/header.jsp"></c:import>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


<h2>National Park Survey</h2>

<c:url var="survey" value="/survey"/>
<form:form action="${survey}" method="POST" modelAttribute="Survey">
<div id="survey-favorite-national-park">
<label for="parkCode">Favorite National Park</label>
<form:select path="parkCode">
<c:forEach var="aPark" items="${parks}">
<form:option value="${aPark.parkCode}">${aPark.parkName}</form:option>
</c:forEach>
</form:select>
<form:errors path="parkCode" cssClass="bg-danger"/>
</div>

<div id="survey-email-address">
<label for="emailAddress">Email Address</label>
<form:input path="emailAddress"/>
<form:errors path="emailAddress" cssClass="bg-danger"/>
</div>


<div id="survey-state">
<label for="state">State of Residence</label>
<form:select path="state">
<form:option value=""></form:option>
<form:option value="AL">Alabama</form:option>
<form:option value="AK">Alaska</form:option>
<form:option value="AZ">Arizona</form:option>
<form:option value="AR">Arkansas</form:option>
<form:option value="CA">California</form:option>
<form:option value="CO">Colorado</form:option>
<form:option value="CT">Connecticut</form:option>
<form:option value="DE">Delaware</form:option>
<form:option value="DC">District Of Columbia</form:option>
<form:option value="FL">Florida</form:option>
<form:option value="GA">Georgia</form:option>
<form:option value="HI">Hawaii</form:option>
<form:option value="ID">Idaho</form:option>
<form:option value="IL">Illinois</form:option>
<form:option value="IN">Indiana</form:option>
<form:option value="IA">Iowa</form:option>
<form:option value="KS">Kansas</form:option>
<form:option value="KY">Kentucky</form:option>
<form:option value="LA">Louisiana</form:option>
<form:option value="ME">Maine</form:option>
<form:option value="MD">Maryland</form:option>
<form:option value="MA">Massachusetts</form:option>
<form:option value="MI">Michigan</form:option>
<form:option value="MN">Minnesota</form:option>
<form:option value="MS">Mississippi</form:option>
<form:option value="MO">Missouri</form:option>
<form:option value="MT">Montana</form:option>
<form:option value="NE">Nebraska</form:option>
<form:option value="NV">Nevada</form:option>
<form:option value="NH">New Hampshire</form:option>
<form:option value="NJ">New Jersey</form:option>
<form:option value="NM">New Mexico</form:option>
<form:option value="NY">New York</form:option>
<form:option value="NC">North Carolina</form:option>
<form:option value="ND">North Dakota</form:option>
<form:option value="OH">Ohio</form:option>
<form:option value="OK">Oklahoma</form:option>
<form:option value="OR">Oregon</form:option>
<form:option value="PA">Pennsylvania</form:option>
<form:option value="RI">Rhode Island</form:option>
<form:option value="SC">South Carolina</form:option>
<form:option value="SD">South Dakota</form:option>
<form:option value="TN">Tennessee</form:option>
<form:option value="TX">Texas</form:option>
<form:option value="UT">Utah</form:option>
<form:option value="VT">Vermont</form:option>
<form:option value="VA">Virginia</form:option>
<form:option value="WA">Washington</form:option>
<form:option value="WV">West Virginia</form:option>
<form:option value="WI">Wisconsin</form:option>
<form:option value="WY">Wyoming</form:option>
</form:select>
<form:errors path="state" cssClass="bg-danger"/>
</div>

<div id="survey-activity-level">
<div>
<p>Activity Level</p>
</div>
<table id="survey-activity-table">
<tr>
<td><form:radiobutton path="activityLevel" value="low"/>Inactive</td>
<td><form:radiobutton path="activityLevel" value="medium"/>Sedentary</td>
<td><form:radiobutton path="activityLevel" value="high"/>Active</td>
<td><form:radiobutton path="activityLevel" value="high"/>Extremely Active</td>
</tr>
</table>
<form:errors path="activityLevel" cssClass="bg-danger"/>
</div>


<div id="survey-submit">
<button type="submit">Submit Survey</button>
</div>




</form:form>

<c:import url="/WEB-INF/jsp/common/footer.jsp"></c:import>