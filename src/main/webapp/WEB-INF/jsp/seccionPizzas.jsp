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

<%@ include file="header.jsp" %>

<div class="max-w-[100%] w-full flex items-center justify-center h-[350px] my-12">
    <img src="https://kentucky.com.ar/imgs/grafico-tentate.svg" alt="tentate">
</div>

<div class="container mx-auto  my-6">
    <c:if test='<%=session.getAttribute("ROL").equals("admin")%>'>
        <div>
            <a href="agregarPizza" id="agregarPizza">Agregar Pizza</a>
        </div>
    </c:if>

    <div class="grid grid-cols-1 max-w-[640px] w-full gap-6 lg:grid-cols-3 lg:max-w-none lg:mx-0">


        <!-- SE INICIA UN BUCLE EN DONDE POR CADA CURSO SE VA MOSTRANDO SUS DATOS EN UN CUADRO -->
        <c:forEach var="pizza" items="${lista_pizzas}">
            <div class="rounded-xl bg-[#1C1919] bg-opacity-80 w-[320px] h-full p-4 mx-auto">
                <div class=" flex items-center justify-center flex-col text-white ">
                    <div>
                        <a href="descripcionPizza?id_pizza=${pizza.id}">
                            <img class="rounded-xl max-w-[100%] w-[150px] h-[150px]"
                                 src="imagenes/pizzas/${pizza.imagen}">
                        </a>
                    </div>

                    <div class="h-[140px] flex flex-col items-center justify-center">
                        <p class="text-[15px] text-center font-semibold  tracking-[0.12em]">${pizza.nombre}</p>
                        <p class="text-[12px] max-w-[250px] font-semibold  tracking-[0.12em]">${pizza.descripcion}</p>
                        <p class="text-[18px] text-center font-bold ">$${pizza.precio}</p>
                    </div>

                    <div class="flex items-center justify-center gap-x-4">
                        <c:if test='<%=session.getAttribute("ROL").equals("admin")%>'>
                            <form action="editarPizza" method="get">
                                <input type="hidden" name="id_pizza" value="${pizza.id}">
                                <input type="hidden" name="nombre" value="${pizza.nombre}">
                                <input type="hidden" name="descripcion" value="${pizza.descripcion}">
                                <input type="hidden" name="precio" value="${pizza.precio}">
                                <input type="submit" name="editarPizza" value="Editar pizza">
                            </form>
                        </c:if>
                        <c:if test='<%=session.getAttribute("ROL").equals("cliente")%>'>
                            <form action="verMediosDePago" method="POST">
                                <input type="hidden" name="id_pizza" value="${pizza.id}">
                                <input type="hidden" name="precio" value="${pizza.precio}">
                                <input class="font-semibold text-sm cursor-pointer p-2 rounded bg-red-400 hover:border-transparent hover:text-black hover:bg-gray-100 mt-4 lg:mt-0 text-white flex items-center justify-between max-w-[80px] w-full md:w-auto"
                                       type="submit" name="pagar" value="Comprar">
                            </form>

                            <form action="agregarPizzaAlCarrito" method="get">
                                    <input type="hidden" name="id_pizza" value="${pizza.id}">
                                        <%-- cambiar por vista de carrito --%>
                                    <input class="font-semibold text-sm cursor-pointer p-2 rounded bg-red-400 hover:border-transparent hover:text-black hover:bg-gray-100 mt-4 lg:mt-0 text-white flex items-center justify-between w-full md:w-auto"
                                           id="agregarCarrito" type="submit" value="Agregar al carrito">
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