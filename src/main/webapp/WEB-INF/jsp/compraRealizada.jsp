<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="es">
<head>
	<meta charset="UTF-8">
	<!-- Tailwind -->
	<script src="https://cdn.tailwindcss.com"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/flowbite/1.6.5/flowbite.min.js"></script>
	<title>CompraRealizada</title>
	<meta name="viewport"
		  content="width=device-width, initial-scale=1, maximum-scale=1">
	<link rel="shortcut icon" href="imagenes/favicon.ico">
</head>
<body>

<%@ include file="navbar.jsp"%>


<main class="h-full flex items-center justify-center">
	<div class="bg-[#1C1919] bg-opacity-80 w-[750px] min-h-[200px] h-[auto] p-4 my-2 mx-auto rounded-xl flex items-center justify-center flex-col ">
		<h2 class="text-4xl font-bold text-white">Compra exitosa!</h2>
		<div>
			<form action="historial">
				<input class="text-2xl font-semibold text-red-400 cursor-pointer" type="submit" name="misCompras" value="Ver mis compras">
			</form>
		</div>
	</div>
</main>


</body>
</html>