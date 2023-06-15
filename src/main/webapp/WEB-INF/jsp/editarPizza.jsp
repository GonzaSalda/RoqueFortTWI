<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Editar Pizza</title>
    <!-- Tailwind -->
    <script src="https://cdn.tailwindcss.com"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/flowbite/1.6.5/flowbite.min.js"></script>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
</head>
<body>

<!--<%@ include file="header.jsp" %>-->
<div class="container mx-auto my-6 text-white">
    <div id="formularioCrearPizza"
         class="bg-[#1C1919] bg-opacity-80 w-[750px] min-h-[200px] h-[auto] p-4 mx-auto rounded-xl flex items-center justify-center flex-col ">

        <form:form action="pizzaActualizado" method="POST" modelAttribute="datosCrearPizza">
            <div class="flex flex-col gap-2">
                <label for="nombre">Nombre de la pizza</label>
                <input class="p-2 rounded-xl outline-0 text-black" id="nombre" type="text" name="nombre" value="${nombrePizza}">

                <label for="descripcion">Descripción</label>
                <input class="p-2 rounded-xl outline-0 text-black" id="descripcion" type="text" name="descripcion" value="${descPizza}">

                <label for="precio">Precio</label>
                <input class="p-2 rounded-xl outline-0 text-black" id="precio" type="number" name="precio" value="${precioPizza}">
            </div>


            <input type="hidden" name="id_pizza" value="${pizzaId}">

            <input class="font-semibold  text-sm px-4 py-2 leading-none rounded bg-red-400  hover:text-gray-900 hover:bg-gray-100  mt-4 text-white flex items-center justify-between w-full md:w-auto"
                   id="añadir" type="submit" value="Actualizar">
        </form:form>
    </div>
</div>


</body>
</html>