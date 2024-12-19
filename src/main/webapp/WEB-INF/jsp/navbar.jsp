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
                <link rel="stylesheet"
                    href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
                <link href="/output.css" rel="stylesheet">
            </head>

            <body class="bg-bgCustom3 bg-cover bg-no-repeat  min-h-screen">

                <nav class="bg-bgHeader bg-opacity-80 border-gray-200 ">
                    <div class=" flex flex-wrap items-center justify-between mx-auto p-4">



                        <c:if test='<%=session.getAttribute("ROL").equals("cliente")%>'>
                            <div>

                                <ul class="py-2 text-sm text-gray-700 dark:text-gray-200 flex gap-x-4 "
                                    aria-labelledby="avatarButton">
                                    <!-- Inicio -->
                                    <li>
                                        <a href="menu" class="btn-primary">Inicio</a>
                                    </li>
                                    <!-- Extra -->
                                    <li>
                                        <a href="ExtraCarrito" class="btn-primary">Añadir
                                            extra</a>
                                    </li>
                                </ul>
                            </div>

                        </c:if>

                        <c:if test='<%=session.getAttribute("ROL").equals("admin")%>'>
                            <button data-collapse-toggle="navbar-multi-level" type="button"
                                class="inline-flex items-center p-2 w-10 h-10 justify-center text-sm text-gray-500 rounded-lg md:hidden hover:bg-gray-100 focus:outline-none focus:ring-2 focus:ring-gray-200 dark:text-gray-400 dark:hover:bg-gray-700 dark:focus:ring-gray-600"
                                aria-controls="navbar-multi-level" aria-expanded="false">
                                <span class="sr-only">Open main menu</span>
                                <svg class="w-5 h-5" aria-hidden="true" xmlns="http://www.w3.org/2000/svg" fill="none"
                                    viewBox="0 0 17 14">
                                    <path stroke="currentColor" stroke-linecap="round" stroke-linejoin="round"
                                        stroke-width="2" d="M1 1h15M1 7h15M1 13h15" />
                                </svg>
                            </button>
                            <div class="hidden w-full md:block md:w-auto" id="navbar-multi-level">
                                <ul
                                    class="flex flex-col font-medium p-4 md:p-0 mt-4 border border-gray-100 rounded-lg  md:space-x-8 rtl:space-x-reverse md:flex-row md:mt-0 md:border-0 ">
                                    <!-- Inicio -->
                                    <li>
                                        <a href="menu" class="btn-primary">Inicio</a>
                                    </li>
                                    <!-- Agregar Pizza -->
                                    <li>
                                        <a href="crearPizza" class="btn-primary">Agregar Pizza</a>
                                    </li>
                                    <!-- Boton 2 -->
                                    <li>
                                        <button id="dropdownNavbarLink" data-dropdown-toggle="dropdownNavbar"
                                            class="btn-primary flex justify-between items-center">Extras
                                            <svg class="w-2.5 h-2.5 ms-2.5" aria-hidden="true"
                                                xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 10 6">
                                                <path stroke="currentColor" stroke-linecap="round"
                                                    stroke-linejoin="round" stroke-width="2" d="m1 1 4 4 4-4" />
                                            </svg></button>
                                        <!-- Dropdown menu -->
                                        <div id="dropdownNavbar"
                                            class="z-10 hidden font-normal bg-white divide-y divide-gray-100 rounded-lg shadow w-44 dark:bg-gray-700 dark:divide-gray-600">
                                            <ul class="py-2 text-sm text-gray-700 dark:text-gray-200"
                                                aria-labelledby="dropdownLargeButton">
                                                <li>
                                                    <a href="agregarExtra"
                                                        class="block px-4 py-2 hover:bg-gray-100 dark:hover:bg-gray-600 dark:hover:text-white">Agregar
                                                        Extra</a>
                                                </li>
                                                <li>
                                                    <a href="agregarStock"
                                                        class="block px-4 py-2 hover:bg-gray-100 dark:hover:bg-gray-600 dark:hover:text-white">Agregar
                                                        Stock</a>
                                                </li>
                                                <li aria-labelledby="dropdownNavbarLink">
                                                    <a href="stockExistentes"
                                                        class="block px-4 py-2 hover:bg-gray-100 dark:hover:bg-gray-600 dark:hover:text-white">Consultar
                                                        Stock</a>
                                                <li>
                                                    <a href="eliminarExtra"
                                                        class="block px-4 py-2 hover:bg-gray-100 dark:hover:bg-gray-600 dark:hover:text-white">Eliminar
                                                        extra</a>
                                                </li>
                                            </ul>
                                        </div>
                                    </li>
                                    <!-- Boton Motos -->
                                    <li>
                                        <a href="listarMotos" class="btn-primary">Motos</a>
                                    </li>
                                    <!-- Boton Pedidos-->
                                    <li>
                                        <a href="verPedidosEnCola" class="btn-primary">Pedidos</a>
                                    </li>
                                </ul>
                            </div>
                        </c:if>

                        <div>
                            <c:if test='<%=session.getAttribute("idUsuario") != null%>'>
                                <div class="flex justify-end items-center w-full gap-x-4">
                                    <c:if test='<%=session.getAttribute("ROL").equals("cliente")%>'>
                                        <%-- Carrito --%>
                                            <div>
                                                <a class="text-white w-[15px]" href="carrito">
                                                    <p>Carrito</p>
                                                </a>
                                            </div>
                                    </c:if>
                                    <img id="foto-perfil4" type="button" data-dropdown-toggle="userDropdown4"
                                        data-dropdown-placement="bottom-start"
                                        class="w-10 h-10 rounded-full cursor-pointer"
                                        src="https://static.vecteezy.com/system/resources/previews/005/545/335/original/user-sign-icon-person-symbol-human-avatar-isolated-on-white-backogrund-vector.jpg"
                                        alt="User dropdown">

                                    <div id="userDropdown4"
                                        class="z-10 hidden bg-white divide-y divide-gray-100 rounded-lg shadow w-44 dark:bg-gray-700 dark:divide-gray-600"
                                        id="usuario-perfil4">
                                        <div class="px-4 py-3 text-sm text-gray-900 dark:text-white">
                                            <p>
                                                <%=session.getAttribute("nombreUsuario")%>
                                            </p>
                                        </div>
                                        <c:if test='<%=session.getAttribute("ROL").equals("cliente")%>'>
                                            <ul class="py-2 text-sm text-gray-700 dark:text-gray-200"
                                                aria-labelledby="avatarButton">
                                                <li>
                                                    <a href="historial"
                                                        class="block px-4 py-2 hover:bg-gray-100 dark:hover:bg-gray-600 dark:hover:text-white">Historial</a>
                                                </li>
                                                <li>
                                            </ul>
                                        </c:if>
                                        <div class="py-1">
                                            <a href="cerrarSesion"
                                                class="block px-4 py-2 text-sm text-gray-700 hover:bg-gray-100 dark:hover:bg-gray-600 dark:text-gray-200 dark:hover:text-white">Cerrar
                                                Sesion</a>
                                        </div>
                                    </div>
                                </div>
                            </c:if>
                        </div>


                    </div>
                </nav>

                <script src="https://cdnjs.cloudflare.com/ajax/libs/flowbite/1.6.5/flowbite.min.js"></script>

            </body>

            </html>