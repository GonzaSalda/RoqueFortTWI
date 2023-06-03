<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>RoqueFort</title>
    <!-- Tailwind -->
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body>

<%@ include file="header.jsp"%>

<main class="container w-full rounded-xl bg-[#1C1919] bg-opacity-80 text-white m-auto pb-2">
    <h1 class="text-4xl font-bold m-4  text-center" >Historial</h1>
    <div class="">
        <nav class="border w-fit mx-auto">
                    <ul class="flex font-medium justify-between">
                        <li class="p-4 ${activetodo}">
                                                    <a href="historial">
                                                    Todas</a>
                                                </li>
                        <li class="p-4 ${active24}">
                            <a href="ultimas-24hs">
                            Ultimas 24hs</a>
                        </li>
                        <li class="p-4 ${active48}">
                            <a href="ultimas-48hs">
                            Ultimas 48hs</a>
                        </li>
                        <li class="p-4 ${activerango}" id="mostrar-input-fecha">
                            <a href="rango-fecha">
                            Rango de fecha</a>
                          </li>
                    </ul>

        </nav>
        <form action="rango-fecha-enviar" class=" w-fit mx-auto flex flex-col ${formestado} mb-3" id="rango-fecha" method="get">
                    <label class="flex justify-between p-4">Fecha Inicial  <input name="fechaInicial" class="text-black" type="date"></label>
                    <label class="flex justify-between p-4">Fecha Final  <input name="fechaFinal" class="text-black" type="date"></label>
                    <input type="submit" value="Buscar" class=" w-48 h-12 m-auto text-center font-semibold text-sm bg-sky-600 text-white rounded-md shadow-sm">
        </form>
    </div>
         <div class="w-fit mx-auto flex">

                        <c:forEach var="pizzaComprada" items="${pizzas}">
                             <div class="evento border m-2">
                                <div class="flex justify-between p-4 border font-bold">
                                    <p>Fecha Pedido ${pizzaComprada.fecha_incio_compra}</p>
                                    <p>Hora ${pizzaComprada.hora}</p>
                                </div>
                                <div class=" p-4">
                                    <div class="font-bold flex justify-between">
                                        <div class="rounded-xl  w-[320px]  mx-auto">
                                            <div class=" flex items-center flex-row  text-white">
                                                <div class="m-auto">
                                                    <a href="descripcionPizza?id_pizza=1">
                                                        <img class="rounded-xl max-w-[100%] w-[150px] m-auto" src="imagenes/pizzas/${pizzaComprada.pizza.imagen}">
                                                    </a>
                                                    <div class="h-[140px] flex flex-col items-center justify-center">
                                                        <p class="text-[15px] text-center font-semibold  tracking-[0.12em]">${pizzaComprada.pizza.nombre}</p>
                                                        <p class="text-[12px] max-w-[250px] font-semibold  tracking-[0.12em]">${pizzaComprada.pizza.descripcion}</p>
                                                        <p class="text-[18px] text-center font-bold ">$ ${pizzaComprada.pizza.precio}</p>
                                                    </div>
                                                </div>

                                            </div>
                                        </div>
                                    </div>
                   <!--                 <div class="mt-4 flex justify-between">
                                        <p>MODERNA 2 x $3000</p>
                                        <div class="text-end mb-2 font-semibold">
                                            <p>$3000</p>
                                        </div>
                                    </div>
                                    <div class="mt-4 flex justify-between">
                                        <p>VEGETARIANA 2 x $3000</p>
                                        <div class="text-end mb-2 font-semibold">
                                            <p>$3000</p>
                                        </div>
                                    </div> --!>
                                    <hr>
                                    <div class="mt-4 flex justify-between">
                                        <p>Total</p>
                                        <div class="text-end mb-2 font-semibold">
                                            <p>$ ${pizzaComprada.pizza.precio}</p>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
${error}
         </div>

    </div>

<script src="https://cdnjs.cloudflare.com/ajax/libs/flowbite/1.6.5/flowbite.min.js"></script>

</body>
</html>