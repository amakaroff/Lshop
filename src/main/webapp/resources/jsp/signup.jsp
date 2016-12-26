<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Sign Up</title>
    <link rel="shortcut icon" href="../../resources/static/image/favicon.png"/>
    <link rel="stylesheet" type="text/css"
          href="<c:url value='/resources/static/css/signup.css' />"/>
</head>
<body>
<div class="content">
    <c:if test="${error == true}">
        ${errorMessage}
    </c:if>
    <div class="input-zone">
        <div class="title">
            Sign Up
        </div>
        <div class="label-error">
            ${errorMessage}
        </div>
        <form:form method="POST" action="/complete" modelAttribute="profile">
            <div class="label">
                login
                <form:input path="login" cssClass="input" required="required" autocomplete="off"/>
            </div>
            <div class="label">
                password
                <form:password path="password" cssClass="input" required="required" autocomplete="off"/>
            </div>
            <div class="label">
                full name
                <form:input path="name" cssClass="input" required="required" autocomplete="off"/>
            </div>
            <div class="label">
                url photo
                <form:input path="urlPhoto" cssClass="input" required="required" autocomplete="off"/>
            </div>
            <div class="label">
                phone number
                <form:input path="phone" cssClass="input" required="required" autocomplete="off"/>
            </div>
            <div class="label">
                email
                <form:input path="mail" cssClass="input" required="required" autocomplete="off"/>
            </div>
            <input type="submit" class="complete-button"
                   value="Sign up"/>
        </form:form>
    </div>
</div>
</body>
</html>
