<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
	<head>
		<meta charset="UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<title>Login</title>
		<script src="https://cdn.tailwindcss.com"></script>
	</head>
	<body>
		<section class="bg-gray-50 dark:bg-gray-900">
			<div class="flex flex-col items-center justify-center px-6 py-8 mx-auto md:h-screen lg:py-0">
				<div class="w-full bg-white rounded-lg shadow dark:border md:mt-0 sm:max-w-md xl:p-0 dark:bg-gray-800 dark:border-gray-700">
					<div class="p-6 space-y-4 md:space-y-6 sm:p-8">
						<h1 class="text-xl font-bold leading-tight tracking-tight text-gray-900 md:text-2xl dark:text-white">
							Iniciar sesión con tu cuenta
						</h1>
						<form:form action="validar-login" method="POST" modelAttribute="usuario" class="space-y-4 md:space-y-6">
							<div>
								<label for="email" class="block mb-2 text-sm font-medium text-gray-900 dark:text-white">Email</label>
								<form:input path="email" id="email" type="email" class="bg-gray-50 border border-gray-300 text-gray-900 sm:text-sm rounded-lg focus:ring-[#eff6ff] focus:border-[#eff6ff] block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500" placeholder="name@company.com" required="" />
							</div>
							<div>
								<label for="password" class="block mb-2 text-sm font-medium text-gray-900 dark:text-white">Password</label>
								<form:input path="password" type="password" id="password"  placeholder="••••••••" class="bg-gray-50 border border-gray-300 text-gray-900 sm:text-sm rounded-lg focus:ring-[#eff6ff] focus:border-[#eff6ff] block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500" required="" />
							</div>
							<button type="submit" class="w-full text-white bg-blue-700 hover:bg-blue-800 focus:ring-4 focus:outline-none focus:ring-[#eff6ff] font-medium rounded-lg text-sm px-5 py-2.5 text-center dark:bg-[#eff6ff] dark:hover:bg-[#eff6ff] dark:focus:ring-[#eff6ff]">Iniciar sesión</button>
							<p class="text-sm font-light text-gray-500 dark:text-gray-400">
								No tenés una cuenta? <a href="registrar-usuario" class="font-medium text-gray-900 dark:text-white hover:underline">Registrarse</a>
							</p>
						</form:form>
						<c:if test="${not empty error}">
							<h4><span>${error}</span></h4>
							<br>
						</c:if>
					</div>
				</div>
			</div>
		</section>
	</body>