<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <%@ page pageEncoding="UTF-8" %>
            <!DOCTYPE html>
            <html lang="en">

            <head>
                <meta charset="utf-8">
                <meta http-equiv="X-UA-Compatible" content="IE=edge">
                <meta name="viewport" content="width=device-width, initial-scale=1">
                <link href="/output.css" rel="stylesheet">

                <title>RoqueFort</title>
            </head>

            <body>

                <%@ include file="navbar.jsp" %>
                    <!-- Banner -->
                    <div
                        class="w-full flex items-center justify-center h-[200px] sm:h-[300px] md:h-[350px] my-8 sm:my-12">
                        <img class="w-full max-w-[90%] sm:max-w-[80%] md:max-w-[70%] h-auto object-contain"
                            src="https://kentucky.com.ar/imgs/grafico-tentate.svg" alt="tentate">
                    </div>

                    <div class="container mx-auto  my-6">
                        <div class="grid grid-cols-1 max-w-[640px] w-full gap-6 lg:grid-cols-3 lg:max-w-none lg:mx-0">


                            <c:forEach var="pizza" items="${lista_pizzas}">
                                <div
                                    class="rounded-xl bg-[#1C1919] bg-opacity-80 w-full max-w-[320px] sm:max-w-[400px] md:max-w-[480px] h-auto p-4 mx-auto">
                                    <div class="flex items-center justify-center flex-col text-white">
                                        <!-- Imagen -->
                                        <div class="w-full flex justify-center ">
                                            <a href="descripcionPizza?id_pizza=${pizza.id}">
                                                <img class="rounded-xl w-[120px] h-[120px] sm:w-[150px] sm:h-[150px] object-cover"
                                                    src="/imagenes/pizzas/${pizza.imagen}">
                                            </a>
                                        </div>

                                        <!-- Contenido -->
                                        <div
                                            class="min-h-[206px] flex flex-col items-center justify-center gap-y-4 text-center px-2">
                                            <p class="text-[16px] sm:text-[18px] font-semibold tracking-[0.12em]">
                                                ${pizza.nombre}
                                            </p>
                                            <p class="text-[12px] sm:text-[14px] leading-tight max-w-[250px]">
                                                ${pizza.descripcion}
                                            </p>
                                            <p class="text-[18px] sm:text-[20px] font-bold">
                                                $${pizza.precio}
                                            </p>
                                        </div>

                                        <!-- Botones -->
                                        <div class="w-full flex justify-center">
                                            <!-- Admin -->
                                            <c:if test='<%=session.getAttribute("ROL").equals("admin")%>'>
                                                <form action="editarPizza" method="get">
                                                    <input type="hidden" name="id_pizza" value="${pizza.id}">
                                                    <input class="btn-login cursor-pointer w-[120px] sm:w-[150px]"
                                                        type="submit" name="editarPizza" value="Editar pizza">
                                                </form>
                                            </c:if>

                                            <!-- Cliente -->
                                            <c:if test='<%=session.getAttribute("ROL").equals("cliente")%>'>
                                                <form action="/agregarPizzaAlCarrito" method="POST">
                                                    <input type="hidden" name="id_pizza" value="${pizza.id}">
                                                    <input type="hidden" name="precio" value="${pizza.precio}">
                                                    <input
                                                        class="font-semibold text-sm cursor-pointer p-2 rounded bg-red-400 hover:border-transparent hover:text-black hover:bg-gray-100 text-white w-[100px] sm:w-[150px]"
                                                        type="submit" name="pagar" value="Agregar al carrito">
                                                </form>
                                            </c:if>
                                        </div>
                                    </div>
                                </div>

                            </c:forEach>

                        </div>

                    </div>

            </body>

            </html>