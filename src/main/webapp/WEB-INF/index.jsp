<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page isErrorPage="true"%>

<html>

<head>
    <meta charset="UTF-8">
    <title>Login and Registration</title>
    <!-- Bootstrap -->
    <link
            href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
            rel="stylesheet">

</head>

<body>
<div class="container">
    <div
            class="d-flex flex-column justify-content-center align-items-center gap-2">
        <h1>Table Master!</h1>
    </div>
    <div
            class="d-flex justify-content-center gap-5 mt-3">
        <div class = "bg-light p-5">
            <h3>New waitstaff</h3>
            <%--@elvariable id="newUser" type="java"--%>
            <form:form class="form d-flex flex-column gap-3" action="/register"
                       method="post" modelAttribute="newUser">
                <div
                        class=" d-flex flex-column justify-content-center align-items-center gap-2">
                    <form:errors path="firstName" class="errors text-danger"/>
                    <form:label path="firstName">Name: </form:label>
                    <form:input class="form-control" type="text" path="firstName"/>
                </div>

                <div
                        class=" d-flex flex-column  justify-content-center align-items-center gap-2">
                    <form:errors path="email" class="errors text-danger"/>
                    <form:label path="email">Email: </form:label>
                    <form:input class="form-control" type="text" path="email"/>
                </div>
                <div
                        class=" d-flex flex-column  justify-content-center align-items-center gap-2">
                    <form:errors path="password" class="errors text-danger"/>
                    <form:label path="password">Password: </form:label>
                    <form:input class="form-control" type="password" path="password"/>
                </div>
                <div
                        class=" d-flex flex-column  justify-content-center align-items-center gap-2">
                    <form:errors path="confirm" class="errors text-danger"/>
                    <form:label path="confirm">Confirm Password: </form:label>
                    <form:input class="form-control" type="password" path="confirm"/>
                </div>
                <input class="w-50 btn btn-primary" type="submit" value="Register" />
            </form:form>
        </div>
        <div class = "bg-light p-5">
            <h3>Log In</h3>
            <%--@elvariable id="newLogin" type="java"--%>
            <form:form class="form d-flex flex-column gap-3" action="/login"
                       method="post" modelAttribute="newLogin">
                <div
                        class=" d-flex flex-column  justify-content-center align-items-center gap-2">
                    <form:errors path="email" class="errors text-danger"/>
                    <form:label path="email">Email: </form:label>
                    <form:input class="form-control" type="text" path="email"/>
                </div>
                <div
                        class=" d-flex flex-column  justify-content-center align-items-center gap-2">

                    <form:label path="password">Password: </form:label>
                    <form:input class="form-control" type="password" path="password"/>
                    <form:errors path="password" class="errors text-danger"/>
                </div>
                <input class="w-50 btn btn-primary" type="submit" value="Login" />
            </form:form>
        </div>
    </div>
</div>

</body>

</html>
