<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Lshop</title>
        <link rel="stylesheet" type="text/css"
              href="<c:url value='/resources/static/css/home.css' />"/>
    </head>
    <body>
        <div class="header">
            <form:form method="POST" action="/signup">
                <input type="submit" class="button signup-button"
                       value="Sign up"/>
            </form:form>
            <form:form method="POST" action="/" modelAttribute="profileEntity">
                <input type="submit" class="button signin-button"
                       value="Sign in"/>
                <form:input path="password" cssClass="button signin-input" required="required"/>
                <form:input path="login" cssClass="button signin-input" required="required"/>
            </form:form>
        </div>
        <div class="content-wrapper">
            <div class="content">
                <div class="categories-wrapper">
                    <div class="categories">

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
                                Price
                            </div>
                        </div>
                        <c:forEach items="${products}" var="product">
                            <div class="product">
                                <div class="product product-name">
                                    <input class="product product-checkbox" type="checkbox" value="checkbox"/>
                                    <c:out value="${product.name}"/>
                                </div>
                                <div class="product product-description">
                                    <c:out value="${product.description}"/>
                                </div>
                                <div class="product product-price">
                                    <c:out value="${product.price}"/>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                    <form:form method="POST" action="/basket">
                        <input type="submit" class="button basket-button"
                               value="Basket"/>
                    </form:form>
                </div>
            </div>
        </div>
    </body>
</html>