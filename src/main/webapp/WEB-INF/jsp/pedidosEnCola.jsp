<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page pageEncoding="UTF-8" %>
<html>
<head>
  <title>Lista de Pedidos en Cola</title>
  <script src="https://cdn.tailwindcss.com"></script>
</head>
<body>
<%@ include file="header.jsp" %>

<div  class="bg-[#1C1919] bg-opacity-80 w-[750px] min-h-[200px] h-[auto] p-4 mx-auto rounded-xl flex items-center justify-center flex-col gap-y-4 my-8 text-white">

<h1 class="mx-auto">Pedidos en cola</h1>
  <table class="min-w-full text-left text-sm font-light text-white">
  <tr>
    <th>Usuario</th>
    <th>Pizza</th>
    <th>Dirección</th>
    <th>Delivery</th>
  </tr>
  <c:forEach items="${pedidosEnCola}" var="pedido">
    <tr class="">
      <td>${pedido.usuario.nombre}</td>
      <td>${pedido.pizza.nombre}</td>
      <td>${pedido.direccion}</td>
      <td>${pedido.delivery ? 'Sí' : 'No'}</td>
    </tr>
  </c:forEach>
</table>
</div>
</body>
</html>