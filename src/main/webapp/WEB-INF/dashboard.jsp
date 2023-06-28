<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page isErrorPage="true"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Table Master</title>
    <!-- Bootstrap -->
    <link
            href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
            rel="stylesheet"
            integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
            crossorigin="anonymous">

</head>
<body>
<div class="container">
    <div class="d-flex justify-content-between">
        <h1>Welcome back, ${user.firstName}!</h1>
        <a href="/logout">Logout</a>
    </div>
<h3>Your tables</h3>
    <table class="table table-striped">
        <thead>
        <tr>
            <th>Guest name</th>
            <th>#Guests</th>
            <th>Arrived at</th>
            <th>Actions</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="tableM" items="${assigned}">

            <tr>
                <td>${tableM.name}</td>
                    <td><c:out value="${tableM.numGuest}"/></td>
                    <td>${tableM.createdAt}</td>
                    <td><c:if test = "${tableM.lead.id==user.id}"><a href="/tables/delete/${tableM.id}">finished</a>
                         | <a href="/tables/edit/${tableM.id}">edit</a>
                        </c:if>
                        <c:if test="${tableM.lead.id!=user.id}">
                            <a href="/home/giveup/${tableM.id}"> Give Up Table</a>
                        </c:if>
                        </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <a href="/tables/new" class="btn border-dark">+new table</a>
</div>

<a href="/tables">See other tables</a>
</body>
</html>