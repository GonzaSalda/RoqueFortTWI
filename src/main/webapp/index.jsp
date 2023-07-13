<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <script src="https://cdn.tailwindcss.com"></script>
    <script src="https://kit.fontawesome.com/ccb0f90ff9.js" crossorigin="anonymous"></script>
    <title>RoqueFort</title>
</head>

<body class="bg-[url(https://images.unsplash.com/photo-1513104890138-7c749659a591?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=870&q=80)] bg-cover bg-no-repeat ">

<header class="bg-[#1C1919] bg-opacity-80 mb-8">
    <div class="container mx-auto flex items-center justify-between p-4">
        <div>
            <h1 class="font-extrabold text-[25px] text-white uppercase" >RoqueFort</h1>
        </div>
        <div  id="registro">
            <a href="login"
               class="inline-block font-semibold mr-6 text-sm px-4 py-2 leading-none rounded text-black  bg-red-400 hover:border-transparent hover:text-gray-900 hover:bg-gray-100 mt-4 lg:mt-0 text-white"
               id="iniciar-sesion">
                Iniciar sesión
            </a>
            <a href="registro"
               class="inline-block font-semibold text-sm px-4 py-2 leading-none  rounded text-white  hover:border-transparent hover:text-gray-900 hover:bg-white mt-4 lg:mt-0"
               id="registrar">
                Registrate
            </a>
        </div>
    </div>
</header>


<main class="flex items-center justify-center m-4">
    <div class="relative w-[1137px] h-[503px] bg-[#1C1919] bg-opacity-80 flex flex-col p-6 gap-y-6">
            <div>
                <img class="max-w-[100%] w-[650px]" src="https://kentucky.com.ar/imgs/grafico_amamos.svg" alt="Amamos">
            </div>
            <div class="flex justify-end">
                <img class="max-w-[100%] w-[550px]"  src="https://s3.sa-east-1.amazonaws.com/static-content.betwarrior.bet/Product/Landing_page/5158/img/app.svg" alt="Decargá nuestra app">
            </div>
    </div>
</main>

</body>
</html>