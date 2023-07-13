<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>RoqueFort</title>
	<!-- Tailwind -->
	<script src="https://cdn.tailwindcss.com"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/flowbite/1.6.5/flowbite.min.js"></script>
</head>
<body>
<%@ include file="header.jsp" %>

<div class="container mx-auto my-6">
	<div class="bg-[#1C1919] bg-opacity-80 w-[750px] min-h-[200px] h-[auto] p-4 mx-auto rounded-xl ">
		<form class="flex flex-col justify-center items-center w-full h-auto " action="insertarIngredienteExtra" method="POST">
			<div>
				<h2 class="text-white font-bold text-[25px]">
					Agregar extra
				</h2>
			</div>
			<div class="text-[18px] text-white font-semibold my-2 ">
				<input type="text" name="nombre" id="nombre" placeholder="nombre"
					   class="text-black p-2 rounded-xl outline-0"></input>
				<input
						type="text" name="precio" placeholder="precio" id="precio" class="text-black   rounded-xl outline-0 p-2"></input>
			</div>
			<div>
				</br> <input class="font-semibold  text-sm px-4 py-2 leading-none rounded bg-red-400  hover:text-gray-900 hover:bg-gray-100 mt-4 lg:mt-0 text-white flex items-center justify-between w-full md:w-auto" type="submit" name="btnAceptar"
							 Value="Aceptar">
			</div>
		</form>
	</div>
</div>
</body>
</html>