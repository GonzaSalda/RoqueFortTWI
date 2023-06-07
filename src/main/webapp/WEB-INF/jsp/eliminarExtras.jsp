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
    <div class="bg-[#1C1919] bg-opacity-80 w-[750px] min-h-[200px] h-[auto] p-4 mx-auto rounded-xl flex items-center justify-center flex-col">
        <h2 class="text-white font-bold text-[25px]">
            Eliminar Stock
        </h2>
        <table class="my-6 w-full">
            <tr class="text-white font-bold text-[15px] ">
                <td>Nombre</td>
                <td>Precio</td>
                <td>Cantidad</td>
                <td>Enviar</td>
            </tr>
            <c:forEach items="${tabla}" var="producto">
                <form action="eliminarIngExtra" method="POST">
                    <tr class="text-white font-bold text-[15px]">
                        <td>${producto.key.nombre}</td>
                        <input type="text" name="nombre" value="${producto.key.nombre}"
                               id="nombre" hidden/>
                        <td>$ ${producto.key.precio}</td>
                        <input type="text" name="precio" value="${producto.key.precio}"
                               id="precio" hidden/>
                        <td>Cant: ${producto.value}</td>
                        <td><input class="cursor-pointer" type="submit" value="Eliminar"></td>
                    </tr>
                </form>
            </c:forEach>
        </table>
    </div>
</div>

</body>
</html>