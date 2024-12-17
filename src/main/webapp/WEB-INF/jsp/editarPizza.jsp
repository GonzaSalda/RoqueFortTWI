<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

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

                <!--<%@ include file="navbar.jsp" %>-->
                <div class="container  mx-auto my-6 text-white">
                    <div id="formularioCrearPizza"
                        class="bg-bgHeader bg-opacity-80 w-[750px] h-[300px] px-4 mx-auto rounded-xl flex items-center justify-center flex-col ">

                        <form:form action="pizzaActualizado" class="w-full" method="POST"
                            modelAttribute="datosCrearPizza">
                            <h1
                                class="text-[16px] sm:text-[18px] font-semibold tracking-[0.12em] text-center uppercase mb-4">
                                Editar pizza</h1>
                            <div
                                class="grid grid-cols-1 gap-x-6  gap-y-8 sm:grid-cols-6  max-w-[550px] justify-center items-center">
                                <div class="col-span-3 ">
                                    <label for="nombre">Nombre de la pizza</label>
                                    <input class="input-short " id="nombre" type="text" name="nombre"
                                        value="${nombrePizza}">
                                </div>
                                <div class="col-span-3 ">
                                    <label for="precio">Precio</label>
                                    <input class="input-short" id="precio" type="number" name="precio"
                                        value="${precioPizza}">
                                </div>
                                <div class="col-span-6 ">
                                    <label for="descripcion">Descripción</label>
                                    <input class="input" id="descripcion" type="text" name="descripcion"
                                        value="${descPizza}">
                                </div>
                            </div>
                            <input type="hidden" name="id_pizza" value="${pizzaId}">
                            <div class="mt-6 flex items-center justify-end gap-x-2">
                                <a href="/roquefort" class="btn-register">Cancelar</a>
                                <button id="añadir" type="submit" class="btn-login">Guardar</button>
                            </div>
                        </form:form>
                    </div>
                </div>


            </body>

            </html>