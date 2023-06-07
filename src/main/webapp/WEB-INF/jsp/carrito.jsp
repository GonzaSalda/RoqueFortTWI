<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Carrito</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="shortcut icon" href="imagenes/favicon.ico">

</head>
<body>

<%@ include file="header.jsp" %>

<div>
    <!-- Se valida si la lista de cursos no esta vacía -->
    <c:if test="${not empty lista_pizzas_carrito}">
        <div class="flex gap-2 my-2 flex-wrap">
            <c:forEach var="pizza" items="${lista_pizzas_carrito}">
                <div class="rounded-xl bg-[#1C1919] bg-opacity-80 w-[320px] h-[350px] p-4 mx-auto flex flex-col  h-full items-center justify-center">
                    <div class="mx-auto">
                        <img class="max-w-[100%] w-[250px] rounded-2xl" src="imagenes/pizzas/${pizza.imagen}">
                    </div>
                    <div class="flex my-2 gap-2 text-white text-[15px] font-semibold">
                        <div>
                            <p>${pizza.nombre}</p>
                        </div>

                        <div>
                            <p>$${pizza.precio}</p>
                        </div>

                        <div>
                            <a href="eliminarPizzaDeListaCarrito?pizza_id=${pizza.id}">
                                x
                            </a>
                        </div>
                    </div>
                </div>


            </c:forEach>
        </div>
        <div class="rounded-xl bg-[#1C1919] bg-opacity-80 w-[520px] h-[150px] p-4 mx-auto my-4">

            <div class="text-white font-bold w-full h-full flex flex-col justify-center items-center">
                <h2 class="tracking-[0.12em] h-full flex items-start">TOTAL DEL CARRITO</h2>
                <div class="h-full" id="total-precio">
                    <p>Total: $${precio_total } </p>
                </div>
                <form action="comprarPizzasDelCarrito" method="GET" id="realizar-compra">
                    <input class="font-semibold text-sm cursor-pointer p-2 rounded bg-red-400 hover:border-transparent hover:text-black hover:bg-gray-100 mt-4 lg:mt-0 text-white flex items-center justify-between  w-full md:w-auto"
                           type="submit" name="realizarCompra" value="Realizar compra">
                </form>
                <div class=" flex flex-col gap-2">
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
            <p class=" tracking-[0.10em] text-white font-bold text-[30px] " id="msj_error_carrito">${msj}</p>
        </div>
    </c:if>
</div>

</body>
</html>