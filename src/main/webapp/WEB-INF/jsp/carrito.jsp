<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
            <%@ page pageEncoding="UTF-8" %>

                <!DOCTYPE html>
                <html lang="es">

                <head>
                    <meta charset="UTF-8">
                    <title>Carrito</title>
                    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
                    <link rel="shortcut icon" href="imagenes/favicon.ico">
                    <link href="/output.css" rel="stylesheet">

                </head>

                <body>

                    <%@ include file="navbar.jsp" %>

                        <div>
                            <!-- Se valida si la lista de cursos no esta vacía -->
                            <c:if test="${not empty lista_pizzas_carrito}">
                                <div class="flex flex-col gap-2 my-2 flex-wrap">
                                    <c:forEach var="pizza" items="${lista_pizzas_carrito}">
                                        <div
                                            class="rounded-xl bg-[#1C1919] bg-opacity-80 w-[320px] h-[350px] p-4 mx-auto flex flex-col  h-full items-center justify-center">
                                            <div class="mx-auto">
                                                <img class="max-w-[100%] w-[250px] rounded-2xl"
                                                    src="imagenes/pizzas/${pizza.imagen}">
                                            </div>
                                            <div
                                                class="flex flex-col my-2 gap-2 text-white text-[15px] font-semibold items-center justify-center">
                                                <div>
                                                    <p class="text-red-400 font-bold tracking-[0.10em]">${pizza.nombre}
                                                    </p>
                                                </div>

                                                <div>
                                                    <p> $${pizza.precio}</p>
                                                </div>
                                            </div>
                                            <div>
                                                <a class="text-white font-semibold hover:text-red-400"
                                                    href="eliminarPizzaDeListaCarrito?pizza_id=${pizza.id}">
                                                    Sacar pizza
                                                </a>
                                            </div>
                                        </div>
                                    </c:forEach>

                                    <c:forEach var="extras" items="${lista_extras_carrito}">
                                        <div
                                            class="rounded-xl bg-[#1C1919] bg-opacity-80 w-[320px] h-[350px] p-4 mx-auto flex flex-col  items-center justify-center">
                                        
                                            <div
                                                class="flex flex-col my-2 gap-2 text-white text-[15px] font-semibold items-center justify-center">
                                                <div>
                                                    <p class="text-red-400 font-bold tracking-[0.10em]">${extras.nombre}
                                                    </p>
                                                </div>

                                                <div>
                                                    <p> $${extras.precio}</p>
                                                </div>
                                            </div>

                                        </div>
                                    </c:forEach>
                                </div>
                                <c:if test="${fn:length(tabla)>0}">
                                    <div
                                        class="rounded-xl bg-[#1C1919] bg-opacity-80 w-[320px] h-[350px] p-4 mx-auto flex flex-col  h-full items-center justify-center">
                                        <p class="text-white font-bold text-[20px]">Lista de extras agregados</p>
                                        <c:forEach var="prod" items="${tabla}">
                                            <div class="flex justify-between w-full">
                                                <p class="text-white font-semibold capitalize">${prod.nombre}</p>
                                                <p class="text-white font-semibold"> $${prod.precio}</p>
                                            </div>
                                        </c:forEach>
                                    </div>
                                </c:if>
                                <div class="rounded-xl bg-[#1C1919] bg-opacity-80 w-[520px] h-full p-4 mx-auto my-4">

                                    <div
                                        class="text-white font-bold w-full h-full flex flex-col gap-y-4 justify-center items-center">
                                        <h2 class="tracking-[0.12em] h-full flex items-start">TOTAL DEL CARRITO</h2>
                                        <div class="h-full flex flex-col gap-y-2" id="total-precio">
                                            <p>Extra Ingre-Cond: $${extra}</p>
                                            <p>Pizza: $${precio_total } </p>
                                            <p>Total: $${totalFinalizado}</p>
                                            <a class="font-normal text-[15px] p-2 rounded text-white bg-red-400 hover:text-black hover:bg-gray-100 flex items-center justify-center w-[250px]"
                                                href="agregarExtra">Agregar ingredientes extras</a>
                                            <a class="font-normal text-[15px] p-2 rounded text-white bg-red-400 hover:text-black hover:bg-gray-100 flex items-center justify-center w-[250px]"
                                                href="vaciarIngExtras">Sacar ingredientes extras</a>
                                        </div>
                                        <div class=" flex flex-col gap-2 my-2">
                                            <a class="font-semibold text-2xl p-2 rounded text-white bg-red-400 hover:text-black hover:bg-gray-100 flex items-center justify-center w-[350px]"
                                                href="comprarporTarjeta?&precioTotal=${precio_total}&delivery=true">
                                                Pagar con tarjeta
                                            </a>
                                        </div>
                                    </div>
                                </div>
                            </c:if>


                            <c:if test="${not empty msj}">
                                <div class="h-[30vh] w-full flex justify-center items-center">
                                    <p class=" tracking-[0.10em] text-white font-bold text-[30px] "
                                        id="msj_error_carrito">${msj}</p>
                                </div>
                            </c:if>
                        </div>

                </body>

                </html>