<%@ tag %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<footer class="footer">
    <div class="container">
        <div style="margin: 20px 0;">

            <c:if test="${not empty pageContext.request.userPrincipal}">
                <form id="logout" class="" action="${pageContext.request.contextPath}/logout" method="post">
                    <input type="submit" value="Log out" />
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                </form>
            </c:if>
        </div>
    </div>
</footer>
