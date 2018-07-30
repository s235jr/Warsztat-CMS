<%@ taglib prefix="c" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: tomasz
  Date: 25.07.18
  Time: 18:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Authors</title>
</head>
<body>

<a href=/articles>
    <button>Articles</button>
</a>
<a href="/authors">
    <button>Authors</button>
</a>
<a href="/categories">
    <button>Categories</button>
</a>

<form:form method="post" modelAttribute="author">
    <div>First name
        <form:input path="firstName"/>
        <form:errors path="firstName"/>
    </div>
    <div>Last name
        <form:input path="lastName"/>
        <form:errors path="lastName"/>
    </div>
    <input type="submit" value="Save">
</form:form>


</body>
</html>
