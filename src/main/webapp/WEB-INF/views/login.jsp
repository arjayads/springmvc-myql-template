<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Firefly | Login</title>

    <!-- Page title set in pageTitle directive -->
    <title page-title></title>
    <!-- Bootstrap and Fonts -->
    <link href="<c:url value="/resources/css/bootstrap/bootstrap.min.css" />" rel="stylesheet">
    <link href="<c:url value="/resources/css/fa/font-awesome.min.css" />" rel="stylesheet">

</head>
<body>
    <div class="container">
        <div class="row" style="margin-top: 100px;">
            <div class="col-md-12">
                <div class="col-md-3 col-md-offset-4" style="border: solid 1px lightblue; padding: 20px;">
                    <div class="text-center"><a href="/">Home</a></div>

                    <h4 class="text-center text-info">Noreco1 Automated Mapping and Facilities Management</h4>
                    <form method="post" action="${pageContext.request.contextPath}/login${param.target == null ? '' : '?target='}${param.target == null ? '' : param.target}">
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                        <fieldset>
                            <c:if test="${param.error != null}">
                                <div class="alert alert-danger">
                                    Invalid username and password.
                                </div>
                            </c:if>
                            <c:if test="${param.logout != null}">
                                <div class="alert alert-info">
                                    You have been logged out.
                                </div>
                            </c:if>
                            <div class="form-group">
                                <input type="text" class="form-control"  required="" id="username" name="username" placeholder="Username" />
                            </div>
                            <div class="form-group">
                                <input type="password" class="form-control"  required="" id="password" name="password" placeholder="Password" />
                            </div>
                            <button type="submit" class="btn btn-primary center-block">Login</button>
                        </fieldset>
                    </form>
                </div>

            </div>
        </div>
    </div>
</body>
</html>

