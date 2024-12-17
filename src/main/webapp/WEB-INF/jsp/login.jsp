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

                <title>Login</title>
            </head>

            <body class="bg-bgCustom2 bg-cover bg-no-repeat  min-h-screen">

                <%@ include file="header.jsp" %>
                    <div class="w-full h-full flex justify-center">
                        <div
                            class="bg-bgHeader/80 w-[450px] rounded-2xl h-full flex flex-col justify-center items-center mx-5 px-6 py-12 lg:px-8">
                            <h2 class=" text-3xl md:text-4xl text-white font-bold my-4">Bienvenidos</h2>
                            <form:form class="flex flex-col gap-y-4 w-full" action="validar-login" method="post"
                                modelAttribute="datosLogin">
                                <div>
                                    <label for="email" class="block text-sm/6 font-medium text-white">Email
                                    </label>
                                    <form:input path="email"
                                        class="block w-full rounded-md bg-white px-3 py-1.5 text-base text-gray-900 outline outline-1 -outline-offset-1 outline-gray-300 placeholder:text-gray-400 focus:outline focus:outline-2 focus:-outline-offset-2 focus:outline-red-500 sm:text-sm/6"
                                        type="email" name="email" placeholder="email" />
                                </div>
                                <div>
                                    <label for="password"
                                        class="block text-sm/6 font-medium text-white">Password</label>

                                    <form:input path="password"
                                        class="block w-full rounded-md bg-white px-3 py-1.5 text-base text-gray-900 outline outline-1 -outline-offset-1 outline-gray-300 placeholder:text-gray-400 focus:outline focus:outline-2 focus:-outline-offset-2 focus:outline-red-500 sm:text-sm/6"
                                        type="password" name="password" placeholder="Contraseña" />
                                </div>

                                <div>
                                    <form:button class="btn-login w-full">
                                        iniciar sesion
                                    </form:button>
                                </div>
                            </form:form>

                            <a class="text-white text-center mt-4 font-semibold text-sm" href="registro">No tenés
                                cuenta?</a>

                            <c:if test="${not empty error}">
                                <p class="error text-red-600 font-bold">${error}</p>
                            </c:if>
                        </div>
                    </div>

            </body>

            </html>