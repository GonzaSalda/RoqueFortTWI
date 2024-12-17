<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="es">
<head>
	<meta charset="UTF-8">
	<title>VerificacionCompra</title>
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<!-- Tailwind -->
	<script src="https://cdn.tailwindcss.com"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/flowbite/1.6.5/flowbite.min.js"></script>
</head>
<body>

	<%@ include file="navbar.jsp"%>

	<main class="container mx-auto my-6">
		<div class="text-white text-xl  w-full h-full flex flex-col items-center justify-center ">
			<form class="bg-[#1C1919] bg-opacity-80 w-[500px] h-full py-2 rounded-2xl flex items-center justify-center flex-col gap-6 my-6"
				  action="verificarCompra" method="post">
				<h3 class="text-4xl font-bold">Resumen</h3>
				<input type="hidden" name="pizzaId" value="${idPizza}"> <%--Obtengo el idpizza del metodo verificacionCompra y luego
				llamo como quiera en el name para pasarselo a otra vista con el model.put--%>
				<div>
					<p>Extras: $${extra}</p>
					<p>Pizza: $${precioPizza}</p>
					<p>Total: $${totalFinalizado}</p>
				</div>

				<a class="font-normal text-[15px] p-2 rounded text-white bg-red-400 hover:text-black hover:bg-gray-100 flex items-center justify-center w-[250px]"
				   href="agregarExtra">Agregar ingredientes extras</a>
				<a class="font-normal text-[15px] p-2 rounded text-white bg-red-400 hover:text-black hover:bg-gray-100 flex items-center justify-center w-[250px]"
				   href="vaciarIngExtrasCompraDirecta">Sacar ingredientes extras</a>

				<div>
					<div id="mapaInputContainer" style="display: none;">
						<%@ include file="mapaInput.jsp"%>
					</div>
					<div>
						<label for="delivery">Entrega a domicilio</label>
						<input type="checkbox" name="delivery" id="delivery" value="true">
					</div>
				</div>
				<h3 id="pagarTarjeta">Pagar con tarjeta</h3>
				<label for="nroTarjeta">Número de tarjeta</label>
				<input class="text-black font-semibold p-2 outline-0 rounded-2xl" id="nroTarjeta" type="number" name="nroTarjeta">
				<input class="bg-black rounded-xl p-2 text-white " id="comprar" type="submit" value="Realizar compra">
			</form>


			<c:if test="${not empty tarjetaIncorrecta}">
				<div class="error">${tarjetaIncorrecta}</div>
			</c:if>
		</div>
	</main>

</body>
</html>


<script>
	const deliveryCheckbox = document.getElementById("delivery");
	const mapaInputContainer = document.getElementById("mapaInputContainer");

	deliveryCheckbox.addEventListener("change", function() {
		if (this.checked) {
			mapaInputContainer.style.display = "block";
		} else {
			mapaInputContainer.style.display = "none";
		}
	});
</script>