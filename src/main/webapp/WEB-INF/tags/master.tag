<%@ tag %>

<%@ attribute name="head" fragment="true" %>
<%@ attribute name="body" fragment="true" %>
<%@ taglib prefix="my" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en" ng-app="inspinia">
<head>
    <my:header/>
    <jsp:invoke fragment="head"/>
</head>
<body>

<div class="container">

    <c:if test="${not empty pageContext.request.userPrincipal}">
        <my:nav-account/>
    </c:if>
    <c:if test="${empty pageContext.request.userPrincipal}">
        <my:nav/>
    </c:if>

    <jsp:invoke fragment="body"/>

</div>
<my:footer/>

<!-- jQuery and Bootstrap -->
<script src="<c:url value="/resources/js/jquery/jquery-2.1.1.min.js" />"></script>
<script src="<c:url value="/resources/js/jquery/jquery-ui-1.10.4.min.js" />"></script>
<script src="<c:url value="/resources/js/jquery/jquery-ui.custom.min.js" />"></script>
<script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>

</body>
</html>



