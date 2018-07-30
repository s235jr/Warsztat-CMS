<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--
  Created by IntelliJ IDEA.
  User: tomasz
  Date: 25.07.18
  Time: 18:02
  To change this template use File | Settings | File Templates.
--%>

<html>
<head>
    <title>Articles</title>
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
<a href="/drafts">
    <button>Draft</button>
</a>

<div>
<table border="3px">
    <c:forEach items="${categories}" var="category">
        <tr><a href="/articles/${category.id}">
            <button>${category.name}</button>
        </a></tr>
    </c:forEach>
</table>
</div>

<table border="5px">
    <tr>
        <td colspan="5">Article</td>
        <td colspan="2">Author</td>
        <td colspan="1">Categories</td>
        <td colspan="2">Option</td>
    </tr>
    <tr>
        <td>Id</td>
        <td>Title</td>
        <td>Content</td>
        <td>Created</td>
        <td>Updated</td>
        <td>First name</td>
        <td>Last name</td>
        <td>Categories</td>
        <td>Edit</td>
        <td>Delete</td>
    </tr>
    <c:forEach items="${articles}" var="article">
        <tr>
            <td>${article.id}</td>
            <td>${article.title}</td>
            <td>${article.content}</td>
            <td>${article.created}</td>
            <td>${article.updated}</td>
            <td>${article.author.firstName}</td>
            <td>${article.author.lastName}</td>
            <td>
                <c:forEach items="${article.categories}" var="category">
                    ${category.name}<br>
                </c:forEach>
            </td>
            <td><a href="/editarticle/${article.id}">Edit</a></td>
            <td><a href="/delarticle/${article.id}">Delete</a></td>
        </tr>
    </c:forEach>
</table>
<div>
    <a href="/addarticle">
        <button>Add Article</button>
    </a>
</div>
</body>
</html>
