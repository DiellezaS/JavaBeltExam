<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page isErrorPage="true"%>

<html>
<head>
    <title>New Table</title>
  <link
          href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
          rel="stylesheet">
</head>
<body>
<h1>New Table</h1>

<form:form action="/tables/new"  method="post" modelAttribute="tableMaster">
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
    <form:errors path="lead" class="error"/>
    <form:input type="hidden" path="lead" value="${userId}" class="form-control"/>
    <div>
        <p><a href="/home" type="button">Cancel</a></p>
        <input value="Submit" type="submit"/>
    </div>
</form:form>


</body>
</html>
