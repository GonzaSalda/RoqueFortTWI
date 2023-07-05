<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page pageEncoding="UTF-8" %>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>RoqueFort</title>
  <!-- Tailwind -->
  <script src="https://cdn.tailwindcss.com"></script>
<body>
<h1>Lista de Motos</h1>

<table>
  <tr>
    <th>ID</th>
    <th>Marca</th>
    <th>Estado</th>
  </tr>
  <c:forEach items="${motos}" var="moto">
    <tr>
      <td>${moto.id}</td>
      <td>${moto.marca}</td>
      <td>${moto.disponible ? 'Disponible' : 'Ocupada'}</td>
      <td>${moto.horarioSalidaPlanificado}</td>
      <td>${moto.horarioSalida}</td>
    </tr>
  </c:forEach>
</table>
</body>
</html>
