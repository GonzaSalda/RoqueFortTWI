<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="css/EstilosRegistrarUsuario.css" rel="stylesheet" type="text/css">
    <title>Registrarse</title>
</head>

<body class="bg-[url(https://images.unsplash.com/photo-1513104890138-7c749659a591?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=870&q=80)] bg-cover bg-no-repeat ">
<%@ include file="headerInicio.jsp"%>

<div class="caja-login">
    <h2 class="text-semibold text-[20px] tracking-[0.15em] uppercase">Registrar Usuario</h2>
    <form:form action="registrar" method="POST" modelAttribute="datosRegistro">
        <div class="caja-usuario">
            <form:input path="nombre" type="text" name="nombre" id="nombre"/>
            <label class="text-white " for="nombre">Nombre</label>
        </div>
        <div class="caja-usuario">
            <form:input path="email"  type="email" name="email" id="email"/>
            <label for="email">Email</label>
        </div>
        <div class="caja-usuario">
            <form:input path="contrasenia" type="password" name="contrasenia" id="contrasenia"/>
            <label for="contrasenia">Contraseña</label>
        </div>
        <div class="caja-usuario">
            <form:input path="repetirContrasenia" type="password" name="repetirContrasenia" id="repetirContrasenia"/>
            <label for="repetirContrasenia">Repetir contraseña</label>
        </div>
        <form:button type="submit" class="a text-white">
            Registrar
        </form:button>
    </form:form>
    <c:if test="${not empty error}">
        <h4><span style="background: #ff000080; text: #fff;">${error}</span></h4>
        <br>
    </c:if>
</div>

</body>
</html>
