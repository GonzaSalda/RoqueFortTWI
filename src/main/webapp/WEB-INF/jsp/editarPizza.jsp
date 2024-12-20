<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
        <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
            <!DOCTYPE html>
            <html lang="es">

            <head>
                <meta charset="UTF-8">
                <title>Editar Pizza</title>
                <!-- Tailwind -->
                <link href="/output.css" rel="stylesheet">
                <script src="https://cdnjs.cloudflare.com/ajax/libs/flowbite/1.6.5/flowbite.min.js"></script>
                <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
            </head>

            <body>

                <%@ include file="navbar.jsp" %>
                    <div class="container mx-auto my-6 text-white">
                        <div id="formularioCrearPizza"
                            class="bg-bgHeader bg-opacity-80 w-full sm:w-[350px] md:w-[450px] lg:w-[600px] h-[auto] px-4 mx-auto rounded-xl flex items-center justify-center flex-col">

                            <form:form action="actualizarPizza" method="POST" enctype="multipart/form-data">
                                <h1
                                    class="text-[16px] sm:text-[18px] font-semibold tracking-[0.12em] text-center uppercase my-4">
                                    Editar pizza
                                </h1>
                                <div
                                    class="grid grid-cols-1 gap-x-6 gap-y-8 sm:grid-cols-6 max-w-[550px] justify-center items-center">
                                    <div class="col-span-3">
                                        <label for="nombre" class="block text-sm">Nombre de la pizza</label>
                                        <input class="input-short w-full" id="nombre" type="text" name="nombre"
                                            value="${pizza.nombre}">
                                    </div>
                                    <div class="col-span-3">
                                        <label for="precio" class="block text-sm">Precio</label>
                                        <input class="input-short w-full" id="precio" type="number" name="precio"
                                            value="${pizza.precio}">
                                    </div>
                                    <div class="col-span-6">
                                        <label for="descripcion" class="block text-sm">Descripción</label>
                                        <input class="input w-full" id="descripcion" type="text" name="descripcion"
                                            value="${pizza.descripcion}">
                                    </div>
                                    <div class="col-span-6">
                                        <label for="imagen">Imagen:</label>
                                        <input type="file" id="imagen" name="imagen">
                                    </div>
                                    <input type="hidden" name="id_pizza" value="${pizza.id}">
                                    <div class="my-6 col-span-6 flex items-center justify-end gap-x-2">
                                        <a href="/menu" class="btn-register">Cancelar</a>
                                        <button id="añadir" type="submit" class="btn-login">Guardar</button>
                                    </div>
                                </div>
                            </form:form>
                        </div>
                    </div>



            </body>

            </html>