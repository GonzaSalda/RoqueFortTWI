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
<%@ include file="header.jsp" %>


<div  class="bg-[#1C1919] bg-opacity-80 w-[750px] min-h-[200px] h-[auto] p-4 mx-auto rounded-xl flex items-center justify-center flex-col gap-y-4 my-8 text-white">
  <h1 class="font-bold text-[20px] tracking-[0.15em]">Lista de Motos</h1>
  <table class="text-white w-[550px] text-center">
    <tr>
      <th>Moto</th>
      <th>Estado</th>
      <th>horario de salida</th>
      <th>horario de entrega</th>
      <th>horario aproximado de regreso al local</th>

    </tr>
    <c:forEach items="${motos}" var="moto">
      <tr>
        <td>${moto.marca}</td>
        <td style="color: ${moto.disponible ? 'green' : 'red'}">${moto.disponible ? 'Disponible' : 'Ocupada'}</td>
        <td>${moto.horarioSalidaPlanificado}</td>
        <td>${moto.horarioEntrega}</td>
        <td>${moto.horarioVueltaNegocio}</td>

      </tr>
    </c:forEach>
  </table>
</div>

</body>
</html>
