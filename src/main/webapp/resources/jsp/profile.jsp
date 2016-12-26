<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>${profile.name}</title>
    <link rel="shortcut icon" href="../../resources/static/image/favicon.png"/>
    <link rel="stylesheet" type="text/css"
          href="<c:url value='/resources/static/css/profile.css' />"/>
</head>
<body>
<div>
    <div class="profile-content">
        <div class="profile-photo">
            <img src="${profile.urlPhoto}"
                 width="240px" height="330px" class="frame"/>
        </div>
        <div class="profile-info">
            <div class="profile-name">
                ${profile.name}
            </div>
            <div class="profile-phone">
                ${profile.phone}
            </div>
            <div class="profile-mail">
                ${profile.mail}
            </div>
        </div>
        <c:if test="${showButton == true}">
            <div class="button-label">
                <form:form method="POST" action="/newproduct" name="out">
                    <input type="submit" class="add-button" value="add product">
                </form:form>
                <form:form method="POST" action="/logout" name="out">
                    <input type="submit" class="logout-button" value="logout">
                </form:form>
            </div>
        </c:if>
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

            <c:forEach items="${profile.products}" var="product">
                <div class="product">
                    <div class="product product-name">
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
                            <c:when test="${product.description.length() > 62}">
                                ${product.description.substring(0, 59)}
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
</body>
</html>
