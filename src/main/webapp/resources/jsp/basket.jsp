<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>
        <title>Basket</title>
        <link rel="stylesheet" type="text/css"
              href="<c:url value='/resources/static/css/basket.css' />"/>
    </title>
</head>
<body>
<div class="content-wrapper">
    <div class="functional">
        <form:form method="POST" action="/check">
            <div class="categories-label">
                <input type="submit" name="category" class="categories-label categories-button"
                       value="Buy Products"/>
            </div>
        </form:form>
        <form:form method="POST" action="/back">
            <div class="categories-label">
                <input type="submit" name="back" class="categories-label categories-button"
                       value="Go to products"/>
            </div>
        </form:form>
    </div>
    <div class="products-wrapper">
        <div class="products">
            <div class="label">
                <div class="label label-name">
                    Name
                </div>
                <div class="label label-description">
                    Description
                </div>
                <div class="label label-price">
                    Price
                </div>
            </div>
            <c:forEach items="${products}" var="product">
                <form:form method="POST" action="/remove">
                    <div class="product">
                        <div class="product product-name">
                            <input type="hidden" name="id" value="${product.id}">
                            <input type="submit" class="product-button"
                                   value="-"/>
                            <c:out value="${product.name}"/>
                        </div>
                        <div class="product product-description">
                            <c:out value="${product.description}"/>
                        </div>
                        <div class="product product-price">
                            <c:out value="${product.price}"/>
                        </div>
                    </div>
                </form:form>
            </c:forEach>
        </div>
    </div>
</div>
</body>
</html>
