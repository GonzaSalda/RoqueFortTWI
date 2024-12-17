<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="/output.css" rel="stylesheet">
    <title>Registrarse</title>
</head>

<body class="bg-bgCustom2 bg-cover bg-no-repeat ">
<%@ include file="header.jsp"%>

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
