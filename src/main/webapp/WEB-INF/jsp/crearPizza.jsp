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
<h1 class="text-4xl font-bold m-4  text-center">Crear Pizza</h1>
    <div id="formularioCrearPizza"
         class="bg-[#1C1919] bg-opacity-80 w-[750px] min-h-[200px] h-[auto] p-4 mx-auto rounded-xl flex items-center justify-center flex-col ">

        <form:form action="guardarPizza" method="POST" modelAttribute="datosCrearPizza">
            <div class="flex flex-col gap-2">
                <label for="imagen">Imagen de la pizza</label>
                <input id="imagen" type="file" name="imagen">

                <label for="nombre">Nombre de la pizza</label>
                <input id="nombre" type="text" name="nombre">

                <label for="descripcion">Descripción</label>
                <input id="descripcion" type="text" name="descripcion">

                <label for="precio">Precio</label>
                <input id="precio" type="number" name="precio">
            </div>

            <input class="font-semibold  text-sm px-4 py-2 leading-none rounded bg-red-400  hover:text-gray-900 hover:bg-gray-100  mt-4 text-white flex items-center justify-between w-full md:w-auto"
                   id="guarda" type="submit" value="Guardar">
        </form:form>
    </div>
</div>


</body>
</html>