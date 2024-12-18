<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
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
				<link href="/output.css" rel="stylesheet">
				<script src="https://cdnjs.cloudflare.com/ajax/libs/flowbite/1.6.5/flowbite.min.js"></script>
			</head>

			<body>
				<%@ include file="navbar.jsp" %>

					<div class="container mx-auto flex justify-center items-center mt-6">
						<div
							class="bg-[#1C1919] bg-opacity-80  w-[350px] md:w-[550px] h-auto p-4 rounded-xl flex justify-center items-center">

							<form class="flex flex-col  items-center h-full w-full" action="insertarExtra"
								method="POST">
								<div>
									<h2 class="text-white font-bold text-[20px] md:text-[25px]">
										Agregar extra
									</h2>
								</div>
								<div class="text-[18px] text-white font-semibold my-2 w-full ">
									<input type="text" name="nombre" id="nombre" placeholder="nombre"
										class="input text-black p-2 rounded-xl outline-0 mb-4" />
									<input type="text" name="precio" placeholder="precio" id="precio"
										class="input text-black rounded-xl outline-0 p-2 " />
								</div>
								<div>
									<input class="btn-primary" type="submit" name="btnAceptar" value="Aceptar">
								</div>
							</form>
						</div>
					</div>

			</body>

			</html>