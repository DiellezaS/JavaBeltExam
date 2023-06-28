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
  <title>Dashboard</title>
  <!-- Bootstrap -->
  <link
          href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
          rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
          crossorigin="anonymous">

</head>
<body>
<div class="container">
  <!-- Beginning of Container -->
  <div class="d-flex justify-content-between">
    <h1>Open tables</h1>
    <a href="/home">Home</a>
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
    <c:forEach var="tableM" items="${unassigned}">
      <tr>
        <c:if test="${tableM.lead.id!=user.id}">
          <td>${tableM.name}</td>
          <td><c:out value="${tableM.numGuest}"/></td>
          <td>${tableM.createdAt}</td>
          <td><a href="/home/pickup/${tableM.id}">Pick Up Table</a> </td>
        </c:if>
      </tr>
    </c:forEach>
    </tbody>
  </table>

</div>
</body>
</html>