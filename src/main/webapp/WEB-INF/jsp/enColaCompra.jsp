<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<html>
<head>
    <title>RoqueFort</title>
    <!-- Tailwind -->
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body>

<%@ include file="navbar.jsp" %>
<div  class="bg-[#1C1919] bg-opacity-80 w-[750px] min-h-[200px] h-[auto] p-4 mx-auto rounded-xl flex items-center justify-center flex-col gap-y-4 my-8 text-white">

<h1 class="font-bold text-[20px] tracking-[0.15em]">En cola de compra</h1>
<c:if test="${enCola}">
    <p id="tiempoEstimado">Tiempo estimado de espera: ${tiempoEstimado} minutos</p>
</c:if>
</div>
</body>
</html>

<script>
    <c:if test="${enCola}">
    var tiempoRestante = ${tiempoEstimado} * 60; // Convertir minutos a segundos
    var tiempoMostrado = document.getElementById("tiempoEstimado");

    var intervalo = setInterval(function() {
        var minutos = Math.floor(tiempoRestante / 60);
        var segundos = tiempoRestante % 60;

        var tiempoFormateado = minutos.toString().padStart(2, "0") + ":" + segundos.toString().padStart(2, "0");
        tiempoMostrado.innerHTML = "Tiempo estimado de espera: " + tiempoFormateado;

        if (tiempoRestante <= 0) {
            clearInterval(intervalo);
            tiempoMostrado.innerHTML = "¡Tiempo de espera finalizado!";
            location.reload();
        }

        tiempoRestante--;
    }, 1000); // Actualizar cada segundo (1000 milisegundos)
    </c:if>
</script>
