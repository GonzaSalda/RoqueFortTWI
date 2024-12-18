<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
        <!DOCTYPE html>
        <html lang="en">

        <head>
            <meta charset="utf-8">
            <meta http-equiv="X-UA-Compatible" content="IE=edge">
            <meta name="viewport" content="width=device-width, initial-scale=1">
            <title>RoqueFort</title>
            <link href="/output.css" rel="stylesheet">

            <script src="https://cdnjs.cloudflare.com/ajax/libs/flowbite/1.6.5/flowbite.min.js"></script>
        </head>

        <body>
            <%@ include file="navbar.jsp" %>

                <div class="container mx-auto w-full mt-6">
                    <div
                        class="bg-[#1C1919] bg-opacity-80 w-[350px] md:w-[750px] min-h-[200px] h-[auto] p-4 mx-auto rounded-xl flex items-center justify-center flex-col">

                        <c:if test="${empty tabla}">
                            <h1 class="text-white font-semibold text-md md:text-4xl">
                                No hay extras agregados en el sistema.
                            </h1>
                        </c:if>
                        <c:if test="${!empty tabla}">
                            <h2 class="text-white font-bold text-[25px] text-center">
                                Agregar Stock
                            </h2>
                            <table class="my-6 w-full">
                                <tr class="text-white font-bold text-[15px]">
                                    <td>Nombre</td>
                                    <td>Precio</td>
                                    <td>Stock</td>
                                    <td>Cantidad</td>
                                    <td>Enviar</td>
                                </tr>
                                <c:forEach items="${tabla}" var="producto">
                                    <form action="insertarStock" method="POST">
                                        <tr class="text-white font-bold text-[15px]">
                                            <td>${producto.nombre}</td>
                                            <td>$ ${producto.precio}</td>
                                            <td>${producto.stock}</td>
                                            <td><input type="number" name="stock" value="0" min="0" id="stock"
                                                    class="input max-w-[70px]"></input></td>
                                            <td>
                                                <input type="text" name="id" value="${producto.id}" hidden />
                                                <input
                                                    class="font-semibold text-sm px-4 py-2 leading-none rounded bg-red-400 hover:text-gray-900 hover:bg-gray-100 mt-4 lg:mt-0 text-white flex items-center justify-between w-full md:w-auto"
                                                    type="submit" value="Agregar">
                                            </td>
                                        </tr>
                                    </form>
                                </c:forEach>
                            </table>
                        </c:if>

                    </div>
                </div>
        </body>

        </html>