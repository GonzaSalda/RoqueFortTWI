<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <%@ page pageEncoding="UTF-8" %>

            <!DOCTYPE html>
            <html>

            <head>
                <meta charset="UTF-8">
                <meta http-equiv="X-UA-Compatible" content="IE=edge">
                <meta name="viewport" content="width=device-width, initial-scale=1.0">
                <link href="/output.css" rel="stylesheet">
                <title>Registrarse</title>
            </head>

            <body class="bg-bgCustom2 bg-cover bg-no-repeat  min-h-screen">
                <%@ include file="header.jsp" %>

                    <div class="w-full h-full flex justify-center">
                        <div
                            class="bg-bgHeader/80 w-[450px] rounded-2xl h-full flex flex-col justify-center items-center mx-5 px-6 py-12 lg:px-8">
                            <h2 class=" text-3xl md:text-4xl text-white font-bold my-4">Registrar Usuario</h2>
                            <form:form action="registrar" method="POST" modelAttribute="datosRegistro"
                                class="flex flex-col gap-y-4 w-full">
                                <div class="flex flex-col">
                                    <label class="block text-sm/6 font-medium text-white" for="nombre">Nombre</label>

                                    <form:input path="nombre" type="text" name="nombre" id="nombre" class="input" />
                                </div>
                                <div class="flex flex-col">
                                    <label class="block text-sm/6 font-medium text-white" for="email">Email</label>

                                    <form:input path="email" type="email" name="email" id="email" class="input" />
                                </div>
                                <div class="flex flex-col">
                                    <label class="block text-sm/6 font-medium text-white"
                                        for="contrasenia">Contraseña</label>

                                    <form:input path="contrasenia" type="password" name="contrasenia" id="contrasenia"
                                        class="input" />
                                </div>
                                <div class="flex flex-col">
                                    <label class="block text-sm/6 font-medium text-white"
                                        for="repetirContrasenia">Repetir contraseña</label>

                                    <form:input path="repetirContrasenia" type="password" name="repetirContrasenia"
                                        id="repetirContrasenia" class="input" />
                                </div>
                                <form:button type="submit" class="w-full btn-login">
                                    Registrar
                                </form:button>
                            </form:form>

                            <c:if test="${not empty error}">
                                <h4><span class="text-red-600">${error}</span></h4>
                                <br>
                            </c:if>
                        </div>
                    </div>

            </body>

            </html>