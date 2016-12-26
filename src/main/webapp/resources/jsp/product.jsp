<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>${product.name}</title>
    <link rel="shortcut icon" href="../../resources/static/image/favicon.png"/>
    <link rel="stylesheet" type="text/css"
          href="<c:url value='/resources/static/css/product.css' />"/>
</head>
<body>
<div class="product">
    <div class="photo">
        <img src="${product.urlPhoto}"
             width="240px" height="330px" class="frame"/>
    </div>
    <div class="info">
        <div class="info-field-name">
            ${product.name}
        </div>
        <div class="info-field-price">
            ${product.price}
        </div>
        <div class="info-field-description">
            <div class="info-field-description-inside">
                ${product.description}
            </div>
        </div>
        <form:form action="/add" method="POST">
            <div class="label-button">
                Add to basket
            </div>
            <input type="hidden" name="id" value="${product.id}"/>
            <input class="add-button" type="submit" value="+"/>
        </form:form>
    </div>
    <div class="seller-info">
        <form:form action="/id${customerId}" method="GET">
            <input type="submit" class="seller-info-button" value="show seller">
        </form:form>
    </div>
</div>
</body>
</html>
