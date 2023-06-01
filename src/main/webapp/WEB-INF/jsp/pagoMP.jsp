<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>VerificacionCompra</title>
    <meta name="viewport"
          content="width=device-width, initial-scale=1, maximum-scale=1">
	<!-- Tailwind -->
	<script src="https://cdn.tailwindcss.com"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/flowbite/1.6.5/flowbite.min.js"></script>
</head>
<body>

<%@ include file="header.jsp" %>

<main>
    <div class="container mx-auto my-6" >
        <div class="bg-[#1C1919] bg-opacity-80 w-[750px] min-h-[200px] h-[auto] p-4 mx-auto rounded-xl flex items-center justify-center flex-col ">

            <form class="text-white font-semibold text-2xl" action="" method="post">

                <input type="hidden" name="idPizza" value="${idPizza}">

                <h3 id="resumen">Resumen</h3>

                <div id="precioTotal">
                    <span>Total:</span>
                    <p class="text-white font-bold text-[16px]">${precioTotal}$</p>
                </div>
            </form>

            <form class="text-white font-semibold text-2xl" action="pagoRealizadoMP" method="get" class="d-inline">

                <input type="hidden" name="idPizza" value="${idPizza}">

                <script
                        src="https://www.mercadopago.com.ar/integrations/v1/web-payment-checkout.js"
                        data-preference-id="${preference.id}">
                </script>
            </form>
        </div>
    </div>
</main>


</body>
</html>