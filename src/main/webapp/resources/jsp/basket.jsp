<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Basket</title>
    <link rel="shortcut icon" href="../../resources/static/image/favicon.png"/>
    <link rel="stylesheet" type="text/css"
          href="<c:url value='/resources/static/css/basket.css' />"/>
</head>
<body>
<div class="content-wrapper">
    <div class="functional">
        <c:choose>
            <c:when test="${error == false}">
                <form:form method="POST" action="/basket/check.pdf">
                    <div class="categories-label">
                        <input type="submit" name="category" class="categories-label categories-button"
                               value="Buy Products"/>
                    </div>
                </form:form>
                <form:form method="GET" action="/">
                    <div class="categories-label">
                        <input type="submit" name="back" class="categories-label categories-button"
                               value="Go to products"/>
                    </div>
                </form:form>
            </c:when>
            <c:otherwise>
                <div class="error-label">
                        ${errorMessage}
                    <form:form method="GET" action="/basket">
                        <input type="submit" name="errorButton" class="error-label-button" value="Ok"/>
                    </form:form>
                </div>
                <div class="categories-label">
                    <input type="submit" name="category" class="categories-label categories-button"
                           value="Buy Products"/>
                </div>
                <div class="categories-label">
                    <input type="submit" name="back" class="categories-label categories-button"
                           value="Go to products"/>
                </div>
            </c:otherwise>
        </c:choose>
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
                            <c:choose>
                                <c:when test="${isDeleted.get(product) == true}">
                                    <input type="submit" class="product-button" value="-"/>
                                    <c:choose>
                                        <c:when test="${product.name.length() > 17}">
                                            ${product.name.substring(0, 13)}
                                            ${"..."}
                                        </c:when>
                                        <c:otherwise>
                                            ${product.name}
                                        </c:otherwise>
                                    </c:choose>
                                </c:when>
                                <c:otherwise>
                                    <input type="submit" class="product-button product-button-false" value="-"/>
                                    <c:choose>
                                        <c:when test="${product.name.length() > 17}">
                                            ${product.name.substring(0, 13)}
                                            ${"..."}
                                        </c:when>
                                        <c:otherwise>
                                            ${product.name}
                                        </c:otherwise>
                                    </c:choose>
                                </c:otherwise>
                            </c:choose>
                        </div>
                        <div class="product product-description">
                            <c:choose>
                                <c:when test="${product.description.length() > 65}">
                                    ${product.description.substring(0, 62)}
                                    ${"..."}
                                </c:when>
                                <c:otherwise>
                                    <c:out value="${product.description}"/>
                                </c:otherwise>
                            </c:choose>
                        </div>
                        <div class="product product-price">
                                ${product.price}
                        </div>
                    </div>
                </form:form>
            </c:forEach>
        </div>
    </div>
</div>
</body>
</html>
