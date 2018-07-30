<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: tomasz
  Date: 25.07.18
  Time: 18:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Draft</title>
</head>
<body>
${infomessage}
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
<
<table border="5px">
    <tr>
        <td colspan="3">Draft</td>
        <td colspan="2">Option</td>
    </tr>
    <tr>
        <td>Id</td>
        <td>Title</td>
        <td>Content</td>
        <td>Edit</td>
        <td>Delete</td>
    </tr>
    <c:forEach items="${articles}" var="draft">
        <tr>
            <td>${draft.id}</td>
            <td>${draft.title}</td>
            <td>${draft.content}</td>
            <td><a href="/editdraft/${draft.id}">Edit</a></td>
            <td><a href="/deldraft/${draft.id}">Delete</a></td>
        </tr>
    </c:forEach>
</table>
<div>
    <a href="/adddraft">
        <button>Add Draft</button>
    </a>
</div>
</body>
</html>
