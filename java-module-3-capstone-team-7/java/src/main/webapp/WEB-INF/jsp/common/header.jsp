<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

    <c:url value="/css/site.css" var="cssHref" />
    <link rel="stylesheet" href="${cssHref}">

<title>National Park Geek</title>
</head>
<body>



<div>
    <c:url value="/img/logo.png" var="logoURL" />    <!-- created url with variable logourl and passed in img src as el expression -->
    <img src="${logoURL}" id="logo-header" />

</div>


<ul>


<li>
<c:if test="${empty appCurrentUser}">		<!-- if appcurrentuser is empty, display the lists that contains  -->
<c:url var="loginUrl" value="/login"/>      <!-- the links loginurl and registerurl that will take you to login and register,respectively -->
<li><a href="${loginUrl}">Login</a></li>
<c:url var="registerUrl" value="/register"/>
<li><a href="${registerUrl}">Register</a></li>
</c:if>

<c:if test="${not empty appCurrentUser}">  <!-- if appcurrentuser is not empty, display the lists that contains the links -->
<li>										<!-- home that takes user to home, survey, and logoff -->
<c:url value="/home" var="home" /> 
<a href="${home}">Home</a>
</li>

<li>
<c:url value="/survey" var="survey" /> 
<a href="${survey}">Survey</a>
</li>
<li>
<c:url value="/logoff" var ="logoff"/>
<form id="logoff-form" action="${logoff}" method="POST">
<a href="javascript:{}" onclick="document.getElementById('logoff-form').submit();">Log off</a>
</form>
</li>
<li><a><c:out value="${appCurrentUser.username}"></c:out></a></li>
</c:if>
</li>

</ul>
