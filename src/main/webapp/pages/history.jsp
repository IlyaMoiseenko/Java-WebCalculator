<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: ilyamoiseenko
  Date: 13.09.23
  Time: 20:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>History</title>
</head>
<body>
  <jsp:include page="/pages/_header.jsp" />

  <div class="container">
    <h1>${user}</h1>
    <ul>
      <c:forEach items="${history}" var="item">
        <li>${item}</li>
      </c:forEach>
    </ul>
  </div>
</body>
</html>
