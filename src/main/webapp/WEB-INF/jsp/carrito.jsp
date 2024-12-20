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

                        <div class="container mx-auto p-4">
                            <div class="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-3 gap-6">
                                <c:forEach var="carritoPizza" items="${carritoPizzas}">
                                    <!-- Contenedor para cada pizza y sus extras -->
                                    <div
                                        class="rounded-xl bg-[#1C1919] bg-opacity-80 p-4 flex flex-col items-center justify-center">
                                        <!-- Pizza -->
                                        <c:if test="${not empty carritoPizza.pizza}">
                                            <div class="flex flex-col items-center gap-y-2">
                                                <!-- Imagen -->
                                                <img class="w-36 h-36 md:w-48 md:h-48 object-cover rounded-lg"
                                                    src="imagenes/pizzas/${carritoPizza.pizza.imagen}"
                                                    alt="${carritoPizza.pizza.nombre}">

                                                <!-- Información de la Pizza -->
                                                <h3 class="text-xl font-semibold text-red-400">
                                                    ${carritoPizza.pizza.nombre}</h3>
                                                <p class="text-lg text-white">Precio: $${carritoPizza.pizza.precio}
                                                </p>

                                                <!-- Controles de cantidad -->
                                                <div class="flex items-center gap-4">
                                                    <a href="actualizarCantidadPizza?pizza_id=${carritoPizza.pizza.id}&accion=restar"
                                                        class="bg-red-500 text-white px-2 py-1 rounded hover:bg-red-600">
                                                        -
                                                    </a>
                                                    <span class="text-white">Cantidad: ${carritoPizza.cantidad}</span>
                                                    <a href="actualizarCantidadPizza?pizza_id=${carritoPizza.pizza.id}&accion=agregar"
                                                        class="bg-green-500 text-white px-2 py-1 rounded hover:bg-green-600">
                                                        +
                                                    </a>
                                                </div>

                                                <!-- Botón de eliminar -->
                                                <div>
                                                    <a class="text-red-400 font-semibold hover:text-red-600"
                                                        href="eliminarPizzaDeCarrito?pizza_id=${carritoPizza.pizza.id}">
                                                        Sacar del carrito
                                                    </a>
                                                </div>
                                            </div>
                                        </c:if>

                                        <!-- Extras -->
                                        <c:if test="${not empty carritoPizza.extra}">
                                            <div
                                                class="rounded-lg p-3 flex flex-col items-center justify-center sm:col-span-1">
                                                <div class="text-white text-center">
                                                    <p class="text-red-400 font-semibold">${carritoPizza.extra.nombre}
                                                    </p>
                                                    <p>$${carritoPizza.extra.precio}</p>
                                                </div>
                                                <div class="flex items-center gap-4">
                                                    <a href="actualizarCantidadExtra?extra_id=${carritoPizza.extra.id}&accion=restar"
                                                        class="bg-red-500 text-white px-2 py-1 rounded hover:bg-red-600">
                                                        -
                                                    </a>
                                                    <p class="text-white">Cantidad: ${carritoPizza.cantidadExtra}</p>
                                                    <a href="actualizarCantidadExtra?extra_id=${carritoPizza.extra.id}&accion=sumar"
                                                        class="bg-green-500 text-white px-2 py-1 rounded  hover:bg-green-600">
                                                        +
                                                    </a>
                                                </div>
                                                <!-- Botón de eliminar -->
                                                <div>
                                                    <a class="text-red-400 font-semibold hover:text-red-600"
                                                        href="eliminarExtraDeCarrito?extra_id=${carritoPizza.extra.id}">
                                                        Sacar del carrito
                                                    </a>

                                                </div>
                                            </div>
                                        </c:if>


                                    </div>
                                </c:forEach>
                            </div>
                        </div>



                        <div class="rounded-xl bg-[#1C1919] bg-opacity-80 w-[520px] h-full p-4 mx-auto my-4">
                            <div
                                class="text-white font-bold w-full h-full flex flex-col gap-y-4 justify-center items-center">
                                <h2 class="tracking-[0.12em] h-full flex items-start">TOTAL DEL CARRITO</h2>
                                <div class="h-full flex flex-col gap-y-2" id="total-precio">
                                    <p>Extra: $${totalExtra}</p>
                                    <p>Pizza: $${totalPizza} </p>
                                    <p>Total: $${totalFinalizado}</p>
                                </div>
                                <div class=" flex flex-col gap-2 my-2">
                                    <a class="font-semibold text-2xl p-2 rounded text-white bg-red-400 hover:text-black hover:bg-gray-100 flex items-center justify-center w-[350px]"
                                        href="comprarporTarjeta?&precioTotal=${precio_total}&delivery=true">
                                        Pagar con tarjeta
                                    </a>
                                </div>
                            </div>
                        </div>


                        <!-- Mostrar el mensaje de error -->
                        <c:if test="${not empty errorMessage}">
                            <div class="alert alert-danger">${errorMessage}</div>
                        </c:if>

                        </div>

                </body>

                </html>