<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page isErrorPage="true"%>

<html>
<head>
    <title>Title</title>
  <!-- Bootstrap -->
  <link
          href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
          rel="stylesheet">
</head>
<body>
<h2><a href="/logout">Logout</a></h2>

<h1>Edit Table</h1>

<form:form action="/tables/edit/${tableMaster.id}"  method="post" modelAttribute="tableMaster">
    <div>
        <form:errors path="name"/>
        <form:label path="name" for="title">Guest name:</form:label>
        <form:input path="name" type="text"/>
    </div>
    <div>
        <form:errors path="numGuest"/>
        <form:label path="numGuest" >Number of Guests:</form:label>
        <form:input path="numGuest" type="number" min="1" max="10" step="1"/>
    </div>
    <div>
        <form:errors path="notes"/>
        <form:label path="notes" >Notes</form:label>
        <form:input path="notes" type="text"/>
    </div>

    <div class="d-flex gap-2 mt-2">
        <p><a href="/tables/delete/${tableMaster.id}" type="button"class="btn border-dark btn-danger ">Delete</a></p>
        <p><a href="/home" type="button" class="btn border-dark border-primary">Cancel</a></p>
        <input value="Submit" type="submit" class="btn btn-primary"/>
    </div>
</form:form>

</body>
</html>