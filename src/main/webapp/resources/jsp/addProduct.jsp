<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add Product</title>
    <link rel="shortcut icon" href="../../resources/static/image/favicon.png"/>
    <link rel="stylesheet" type="text/css"
          href="<c:url value='/resources/static/css/addProduct.css' />"/>
</head>
<body>
<div class="content">
    <div class="input-field">
        <div class="title-label">
            Add a new product
        </div>
        <div class="error-label">
            ${errorMessage}
        </div>
        <form:form method="POST" action="/addproduct" modelAttribute="product">
            <div class="name">
                <div class="label">
                    name
                </div>
                <form:input path="name" cssClass="input-name" required="required" autocomplete="off"/>
            </div>
            <div class="category">
                <div class="label">
                    category
                </div>
                <form:select path="category" cssClass="input-category" required="required" autocomplete="off">
                    <form:option value="Electrical engineering"/>
                    <form:option value="Household goods"/>
                    <form:option value="Furniture"/>
                </form:select>
            </div>
            <div class="price">
                <div class="label">
                    price
                </div>
                <form:input path="price" cssClass="input-price" required="required" autocomplete="off" />
            </div>
            <div class="photo">
                <div class="label">
                    url photo
                </div>
                <form:input path="urlPhoto" cssClass="input-photo" required="required" autocomplete="off"/>
            </div>
            <div class="description">
                <div class="label">
                    description
                </div>
                <form:textarea path="description" cssClass="input-description" required="required" autocomplete="off"/>
            </div>
            <input type="submit" class="add-button"
                   value="Add product"/>
        </form:form>
    </div>
</div>
</body>
</html>
