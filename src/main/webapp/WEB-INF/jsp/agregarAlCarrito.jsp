<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>RoqueFort</title>
    <!-- Tailwind -->
    <script src="https://cdn.tailwindcss.com"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/flowbite/1.6.5/flowbite.min.js"></script>
</head>
<body>

<%@ include file="header.jsp" %>

<div class="container mx-auto my-6">
    <div class="rounded-xl bg-[#1C1919] bg-opacity-80 w-[750px] min-h-[500px] h-[auto] p-4 mx-auto">
        <table class="my-6 w-full">
            <h2 class="text-center text-4xl font-bold text-white tracking-[0.12em] p-4 my-4">
                Agregar extras a la pizza
            </h2>
            <tr class="flex justify-between items-center text-red-400 font-bold text-[16px] px-6 py-2 w-full">
                <td>Nombre</td>
                <td>Precio</td>
                <td>Opcion</td>
            </tr>
            <c:forEach items="${tabla}" var="producto">
                <form action="insertarProductoAlCarrito" method="POST">
                    <tr class="flex justify-between items-center text-white font-bold text-[16px] px-6 py-2 w-full">
                        <td>${producto.key.nombre}</td>
                        <input type="text" name="nombre" value="${producto.key.nombre}"
                               id="nombre" hidden/>
                        <td>$ ${producto.key.precio}</td>
                        <input type="text" name="precio" value="${producto.key.precio}"
                               id="precio" hidden/>

                        <td><c:if test="${producto.value < 1}">
                            <span class="text-red-400">No hay Stock</span>
                        </c:if>
                            <c:if test="${producto.value > 0}">
                                <input class="cursor-pointer" type="submit" value="Agregar">
                            </c:if></td>
                    </tr>
                </form>
            </c:forEach>
        </table>
    </div>
</div>

</body>
</html>