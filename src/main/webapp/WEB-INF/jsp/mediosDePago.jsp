<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Medios de pago</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
</head>
<body>
<%@ include file="header.jsp" %>

<div class="container mx-auto my-4">
    <div class="rounded-xl bg-[#1C1919] bg-opacity-80 w-[780px] h-[650] p-6 mx-auto flex flex-col items-center justify-center gap-4">
        <div class="text-4xl uppcase text-white font-bold">
            <h1>Medios de pago</h1>
        </div>
        <div class=" flex flex-col gap-2">
            <a class="font-semibold text-2xl p-2 rounded text-white bg-red-400 hover:text-black hover:bg-gray-100 flex items-center justify-center w-[350px]"
               href="comprar?id_pizza=${pizza.id}&precio=${precioPizza}&delivery=true">
                Pagar con tarjeta
            </a>
            <a class="font-semibold text-2xl p-2 rounded text-white bg-red-400 hover:text-black hover:bg-gray-100 flex items-center justify-center w-[350px]"
               href="pagoMP?idPizza=${pizza.id}&precioTotal=${precioPizza}&delivery=true">
                Pagar con mercado pago
            </a>
        </div>
    </div>
</div>


</body>
</html>