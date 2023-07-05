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
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">

    <!-- Tailwind -->
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="bg-[url('https://images.unsplash.com/photo-1565299624946-b28f40a0ae38?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=1381&q=80')] max-w-[100%] bg-cover bg-no-repeat">

<div class="bg-[#1C1919] bg-opacity-80 w-full">
    <div class="flex w-full p-4 justify-center items-center ">
        <div class="flex justify-between">
            <a class="inline-block font-semibold mr-6 text-sm px-4 py-2 leading-none rounded text-black  bg-red-400 hover:border-transparent hover:text-gray-900 hover:bg-gray-100 mt-4 lg:mt-0 text-white flex items-center justify-between w-full md:w-auto"
               href="verListaPizzas">Inicio</a>

            <c:if test='<%=session.getAttribute("ROL").equals("cliente")%>'>
                <div>
                    <button id="foto-perfil" type="button" data-dropdown-toggle="userDropdown"
                            data-dropdown-placement="bottom-start"
                            class="inline-block font-semibold mr-6 text-sm px-4 py-2 leading-none rounded text-black  bg-red-400 hover:border-transparent hover:text-gray-900 hover:bg-gray-100 mt-4 lg:mt-0 text-white flex items-center justify-between w-full md:w-auto"
                    >Extras
                        <svg class="w-4 h-4 ml-1" fill="currentColor" viewBox="0 0 20 20"
                             xmlns="http://www.w3.org/2000/svg">
                            <path fill-rule="evenodd"
                                  d="M5.293 7.293a1 1 0 011.414 0L10 10.586l3.293-3.293a1 1 0 111.414 1.414l-4 4a1 1 0 01-1.414 0l-4-4a1 1 0 010-1.414z"
                                  clip-rule="evenodd"></path>
                        </svg>
                    </button>

                    <div id="userDropdown"
                         class="z-10 hidden bg-white divide-y divide-gray-100 rounded-lg shadow w-44 dark:bg-gray-700 dark:divide-gray-600"
                         id="usuario-perfil">
                        <ul class="py-2 text-sm text-gray-700 dark:text-gray-200" aria-labelledby="avatarButton">
                            <li>
                                <a href="agregarExtra"
                                   class="block px-4 py-2 hover:bg-gray-100 dark:hover:bg-gray-600 dark:hover:text-white">Agregar
                                    extra</a>
                            </li>
                        </ul>
                    </div>
                </div>

            </c:if>
            <c:if test='<%=session.getAttribute("ROL").equals("admin")%>'>
                <div>
                    <button id="foto-perfil2" type="button" data-dropdown-toggle="userDropdown2"
                            data-dropdown-placement="bottom-start"
                            class="inline-block font-semibold mr-6 text-sm px-4 py-2 leading-none rounded text-black  bg-red-400 hover:border-transparent hover:text-gray-900 hover:bg-gray-100 mt-4 lg:mt-0 text-white flex items-center justify-between w-full md:w-auto"
                    >Stock
                        <svg class="w-4 h-4 ml-1" fill="currentColor" viewBox="0 0 20 20"
                             xmlns="http://www.w3.org/2000/svg">
                            <path fill-rule="evenodd"
                                  d="M5.293 7.293a1 1 0 011.414 0L10 10.586l3.293-3.293a1 1 0 111.414 1.414l-4 4a1 1 0 01-1.414 0l-4-4a1 1 0 010-1.414z"
                                  clip-rule="evenodd"></path>
                        </svg>
                    </button>

                    <div id="userDropdown2"
                         class="z-10 hidden bg-white divide-y divide-gray-100 rounded-lg shadow w-44 dark:bg-gray-700 dark:divide-gray-600"
                         id="usuario-perfil2">
                        <ul class="py-2 text-sm text-gray-700 dark:text-gray-200" aria-labelledby="avatarButton">
                            <li>
                                <a href="agregarStock"
                                   class="block px-4 py-2 hover:bg-gray-100 dark:hover:bg-gray-600 dark:hover:text-white">Agregar</a>
                            </li>
                            <li>
                                <a href="stockExistentes"
                                   class="block px-4 py-2 hover:bg-gray-100 dark:hover:bg-gray-600 dark:hover:text-white">Consultar</a>
                            </li>
                            <li>
                                <a href="eliminarIngredienteExtra"
                                   class="block px-4 py-2 hover:bg-gray-100 dark:hover:bg-gray-600 dark:hover:text-white">Eliminar</a>
                            </li>
                        </ul>
                    </div>
                </div>

                <div  class="inline-block font-semibold mr-6 text-sm px-4 py-2 leading-none rounded text-black  bg-red-400 hover:border-transparent hover:text-gray-900 hover:bg-gray-100 mt-4 lg:mt-0 text-white flex items-center justify-between w-[120px]">
                    <a href="crearPizza">Agregar Pizza</a>
                </div>


                <div>
                    <button id="foto-perfil3" type="button" data-dropdown-toggle="userDropdown3"
                            data-dropdown-placement="bottom-start"
                            class="inline-block font-semibold mr-6 text-sm px-4 py-2 leading-none rounded text-black  bg-red-400 hover:border-transparent hover:text-gray-900 hover:bg-gray-100 mt-4 lg:mt-0 text-white flex items-center justify-between w-full md:w-auto"
                    >Extras
                        <svg class="w-4 h-4 ml-1" fill="currentColor" viewBox="0 0 20 20"
                             xmlns="http://www.w3.org/2000/svg">
                            <path fill-rule="evenodd"
                                  d="M5.293 7.293a1 1 0 011.414 0L10 10.586l3.293-3.293a1 1 0 111.414 1.414l-4 4a1 1 0 01-1.414 0l-4-4a1 1 0 010-1.414z"
                                  clip-rule="evenodd"></path>
                        </svg>
                    </button>

                    <div id="userDropdown3"
                         class="z-10 hidden bg-white divide-y divide-gray-100 rounded-lg shadow w-44 dark:bg-gray-700 dark:divide-gray-600"
                         id="usuario-perfil3">
                        <ul class="py-2 text-sm text-gray-700 dark:text-gray-200" aria-labelledby="avatarButton">
                            <li>
                                <a href="agregarIngredienteExtra"
                                   class="block px-4 py-2 hover:bg-gray-100 dark:hover:bg-gray-600 dark:hover:text-white">Agregar</a>
                            </li>
                        </ul>
                    </div>
                </div>

                <div  class="inline-block font-semibold mr-6 text-sm px-4 py-2 leading-none rounded text-black  bg-red-400 hover:border-transparent hover:text-gray-900 hover:bg-gray-100 mt-4 lg:mt-0 text-white flex items-center justify-between w-[120px]">
                    <a href="listarMotos">Motos</a>
                </div>
            </c:if>
        </div>
        <%-- Usuario --%>
        <!-- Aca se valida si el usuario inicio sesión -->
        <c:if test='<%=session.getAttribute("idUsuario") != null%>'>
            <div class="flex justify-end items-center w-full gap-x-4">
                <c:if test='<%=session.getAttribute("ROL").equals("cliente")%>'>
                    <%-- Carrito --%>
                <div>
                    <a class="text-white w-[15px]" href="vistaCarrito">
                       <p>Carrito</p>
                    </a>
                </div>
                </c:if>
                <img id="foto-perfil4" type="button" data-dropdown-toggle="userDropdown4"
                     data-dropdown-placement="bottom-start" class="w-10 h-10 rounded-full cursor-pointer"
                     src="https://static.vecteezy.com/system/resources/previews/005/545/335/original/user-sign-icon-person-symbol-human-avatar-isolated-on-white-backogrund-vector.jpg"
                     alt="User dropdown">

                <div id="userDropdown4"
                     class="z-10 hidden bg-white divide-y divide-gray-100 rounded-lg shadow w-44 dark:bg-gray-700 dark:divide-gray-600"
                     id="usuario-perfil4">
                    <div class="px-4 py-3 text-sm text-gray-900 dark:text-white">
                        <p><%=session.getAttribute("nombreUsuario")%>
                        </p>
                    </div>
                    <c:if test='<%=session.getAttribute("ROL").equals("cliente")%>'>
                    <ul class="py-2 text-sm text-gray-700 dark:text-gray-200" aria-labelledby="avatarButton">
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


<script src="https://cdnjs.cloudflare.com/ajax/libs/flowbite/1.6.5/flowbite.min.js"></script>

</body>
</html>