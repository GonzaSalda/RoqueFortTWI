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

                <%@ include file="navbar.jsp" %>
                    <div class="container mx-auto flex justify-center my-6 text-white">
                        <div id="formularioCrearPizza"
                            class="bg-[#1C1919] bg-opacity-80  w-full sm:w-[350px] md:w-[450px] lg:w-[500px] min-h-[200px] h-auto p-4  rounded-xl flex items-center justify-center flex-col overflow-hidden">
                            <h1 class="text-xl sm:text-2xl md:text-3xl lg:text-4xl font-bold m-4 text-center">Crear
                                Pizza</h1>

                            <form:form action="/crearPizza" method="POST" modelAttribute="pizza"
                                enctype="multipart/form-data">
                                <div class="flex flex-col gap-2 w-full">
                                    <label for="nombre" class="text-sm sm:text-base">Nombre de la pizza</label>
                                    <input class="input rounded-xl outline-0 text-black p-2" id="nombre" type="text"
                                        name="nombre">

                                    <label for="descripcion" class="text-sm sm:text-base">Descripción</label>
                                    <input class="input rounded-xl outline-0 text-black p-2" id="descripcion"
                                        type="text" name="descripcion">

                                    <label for="precio" class="text-sm sm:text-base">Precio</label>
                                    <input class="input rounded-xl outline-0 text-black p-2" id="precio" type="number"
                                        name="precio">
                                        
                                    <label for="imagen" class="text-sm sm:text-base">Imagen de la pizza</label>
                                    <input id="imagen" type="file" name="imagen"
                                        class="text-black rounded-xl outline-0 p-2">

                                </div>

                                <div class="flex items-center justify-end gap-x-2 mt-6">
                                    <input class="btn-primary " id="guarda" type="submit" value="Guardar">
                                </div>

                            </form:form>
                        </div>
                    </div>


            </body>

            </html>