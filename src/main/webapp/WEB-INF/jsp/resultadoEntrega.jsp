<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page pageEncoding="UTF-8" %>
<html>
<head>
  <meta charset="UTF-8">
  <title>Resultado de entrega</title>
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
  <!-- Tailwind -->
  <script src="https://cdn.tailwindcss.com"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/flowbite/1.6.5/flowbite.min.js"></script>
</head>
<body>

<main class="h-full flex items-center justify-center">
  <div class="bg-[#1C1919] bg-opacity-80 w-[750px] min-h-[200px] h-[auto] p-4 mx-auto rounded-xl flex items-center justify-center flex-col ">
    <c:if test="${entregaExitosa}">
      <h2 class="text-4xl font-bold text-white text-center">Compra exitosa!</h2>
      <p class="text-xl font-bold">La entrega se realizó exitosamente.</p>
      <p class="text-white font-semibold">${informacionDeEntrega}</p>
      <div class="flex items-center justify-center">
        <form action="misPedidos">
          <input class="text-2xl font-semibold text-red-400 cursor-pointer" type="submit" name="misPedidos" value="Ir a mis pedidos">
        </form>
      </div>
    </c:if>
    <c:if test="${!entregaExitosa}">
      <p class="text-2xl font-semibold">No hay motos disponibles para realizar la entrega.</p>
    </c:if>
  </div>
</main>


</body>
</html>
