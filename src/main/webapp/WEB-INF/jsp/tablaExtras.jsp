<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
        <h2 class="text-4xl text-white font-bold my-2">
            Listado de ingredientes extras
        </h2>
		<table class="my-6 w-full">
            <tr class="text-white font-bold text-[15px] py-4">
                <td>Nombre</td>
                <td>Precio</td>
            </tr>
            <c:forEach items="${tabla}" var="ingrediente">
				<tr class="text-white font-bold text-[15px] py-4">
                    <td>${ingrediente.nombre}</td>
                    <td>$ ${ingrediente.precio}</td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>

<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="js/jquery-1.11.3.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="js/bootstrap.min.js"></script>
</body>
</html>
