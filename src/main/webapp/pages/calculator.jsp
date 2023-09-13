<%--
  Created by IntelliJ IDEA.
  User: ilyamoiseenko
  Date: 13.09.23
  Time: 19:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Calculator</title>
</head>
<body>
  <jsp:include page="/pages/_header.jsp" />

  <div class="container">
    <form class="justify-content-center" action="/calculator" method="post">
      <div class="form-floating mb-3">
        <input name="num1" type="text" class="form-control" id="floatingInput" placeholder="name@example.com">
        <label for="floatingInput">Num 1</label>
      </div>

      <div class="form-floating mb-2">
        <select name="type" class="form-select" id="floatingSelect" aria-label="Floating label select example">
          <option selected>Operation type</option>
          <option value="SUM">SUM</option>
          <option value="SUB">SUB</option>
          <option value="MUL">MUL</option>
          <option value="DIV">DIV</option>
        </select>
        <label for="floatingSelect">Works with selects</label>
      </div>

      <div class="form-floating">
        <input name="num2" type="text" class="form-control" id="floatingPassword" placeholder="Password">
        <label for="floatingPassword">Num 2</label>
      </div>

      <button type="submit" class="btn btn-success mt-2">Success</button>
    </form>
  </div>

</body>
</html>
