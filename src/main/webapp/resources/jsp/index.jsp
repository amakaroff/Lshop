<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Lshop</title>
    <link rel="shortcut icon" href="../../resources/static/image/favicon.png"/>
    <link rel="stylesheet" type="text/css"
          href="<c:url value='/resources/static/css/home.css' />"/>
</head>
<body>
<div class="header">
    <div class="logo-image">
        <img srcset="../../resources/static/image/favicon.png"
             width="50px" height="50px" class="frame"/>
    </div>
    <div class="logo">
        Shop
    </div>
    <c:choose>
        <c:when test="${isLogin == false}">
            <form:form method="POST" action="/signup">
                <input type="submit" class="button signup-button"
                       value="Sign up"/>
            </form:form>
            <form:form method="POST" action="/" modelAttribute="profileEntity">
                <input type="submit" class="button signin-button"
                       value="Sign in"/>
                <form:password path="password" cssClass="button signin-input" required="required" tabindex="2"
                               placeholder="password"/>
                <form:input path="login" cssClass="button signin-input" required="required" placeholder="login"
                            tabindex="1"/>
                <c:if test="${error == true}">
                    <div class="button error-message">
                        Invalid login or password
                    </div>
                </c:if>
            </form:form>
        </c:when>
        <c:otherwise>
            <form:form method="POST" action="/logout" name="out">
                <input type="submit" class="button signup-button" value="Logout">
            </form:form>
            <form:form method="GET" action="/id${selectProfile.id}" name="out">
                <input type="submit" class="button signin-button" value="${selectProfile.name}">
            </form:form>
        </c:otherwise>
    </c:choose>
</div>
<div class="content-wrapper">
    <div class="content">
        <div class="categories-wrapper">
            <div class="categories">
                <form:form method="GET" action="/">
                    <div class="categories-label">
                        <input type="submit" name="category" class="categories-label categories-button"
                               value="All"/>
                    </div>
                    <div class="categories-label">
                        <input type="submit" name="category" class="categories-label categories-button"
                               value="Electrical engineering"/>
                    </div>
                    <div class="categories-label">
                        <input type="submit" name="category" class="categories-label categories-button"
                               value="Household goods"/>
                    </div>
                    <div class="categories-label">
                        <input type="submit" name="category" class="categories-label categories-button"
                               value="Furniture"/>
                    </div>
                </form:form>
                <div class="categories-basket-label">
                    <form:form method="GET" action="/basket">
                        <input type="submit" class="button basket-button"
                               value="Basket"/>
                    </form:form>
                </div>
            </div>
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
                        <form:form method="GET" action="/">
                            <c:if test="${selectCategory ne null}">
                                <input type="hidden" name="category" value="${selectCategory}">
                            </c:if>
                            <input type="hidden" name="sort" value="do"/>
                            <input type="submit" class="label label-price label-price-button"
                                   value="Price"/>
                        </form:form>
                    </div>
                </div>

                <c:forEach items="${products}" var="product">
                    <div class="product">
                        <div class="product product-name">
                            <form:form method="POST" action="/add">
                                <input type="hidden" name="id" value="${product.id}">
                                <input type="hidden" name="selectCategory" value="${selectCategory}">
                                <input type="submit" class="product-button"
                                       value="+"/>
                            </form:form>
                            <form:form method="GET" action="/product${product.id}">
                                <c:choose>
                                    <c:when test="${product.name.length() > 19}">
                                        <input type="submit" class="to-product-button"
                                               value="${product.name.substring(0, 15)}..."/>
                                    </c:when>
                                    <c:otherwise>
                                        <input type="submit" class="to-product-button" value="${product.name}"/>
                                    </c:otherwise>
                                </c:choose>
                            </form:form>
                        </div>
                        <div class="product product-description">
                            <c:choose>
                                <c:when test="${product.description.length() > 70}">
                                    ${product.description.substring(0, 64)}
                                    ${"..."}
                                </c:when>
                                <c:otherwise>
                                    <c:out value="${product.description}"/>
                                </c:otherwise>
                            </c:choose>
                        </div>
                        <div class="product product-price">
                            <c:out value="${product.price}"/>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
    </div>
</div>
</body>
</html>