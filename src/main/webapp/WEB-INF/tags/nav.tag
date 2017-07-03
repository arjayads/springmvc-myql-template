<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<nav class="navbar navbar-default">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="#">It's-A-Map</a>
        </div>
        <ul class="nav navbar-nav pull-right">
            <li class="active"><a href="#">Home</a></li>
            <li><a href="#">FAQs</a></li>
            <li><a href="#">Support</a></li>
            <c:if test="${empty pageContext.request.userPrincipal}">
                <li><a href="/login">Login</a></li>
            </c:if>
        </ul>
    </div>
</nav>
