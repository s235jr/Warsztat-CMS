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
    <title>Category</title>
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

<form:form method="post" modelAttribute="category">
    <div>Name<form:input path="name"/>
        <form:errors path="name"/></div>
    <div>Description<form:input path="description"/></div>
    <input type="submit" value="Save">
</form:form>


</body>
</html>
