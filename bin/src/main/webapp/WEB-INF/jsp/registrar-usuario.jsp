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

<body>

<div class="caja-login">
    <h2>Registrar Usuario</h2>
    <form:form action="registrar" method="POST" modelAttribute="usuario">
        <div class="caja-usuario">
            <form:input path="nombre" type="text" name="nombre" id="nombre"/>
            <label for="nombre">Nombre</label>
        </div>
        <div class="caja-usuario">
            <form:input path="apellido" type="text" name="apellido" id="apellido"/>
            <label for="apellido">Apellido</label>
        </div>
        <div class="caja-usuario">
            <form:input path="dni" type="number" name="dni" id="dni"/>
            <label for="dni">DNI</label>
        </div>
        <div class="caja-usuario">
            <form:input path="nacimiento" type="date" name="fecha-nacimiento" id="fecha-nacimiento"/>
            <label for="fecha-nacimiento">Fecha de nacimiento</label>
        </div>
        <div class="caja-usuario">
            <select id="provincia" name="provincia" style="
                    width: 100%;
                    padding: 10px 0;
                    font-size: 16px;
                    margin-bottom: 30px;
                    border: none;
                    border-bottom: 1px solid #fff;
                    background: #444;
                    color: #fff;">
                <option value="" style="background: transparent;">Provincia</option>
                <option value="buenos-aires" style="background: transparent;">Buenos Aires</option>
                <option value="caba" style="background: transparent;">CABA</option>
                <option value="catamarca" style="background: transparent;">Catamarca</option>
                <option value="chubut" style="background: transparent;">Chubut</option>
                <option value="cordoba" style="background: transparent;">Córdoba</option>
                <option value="corrientes" style="background: transparent;">Corrientes</option>
                <option value="entre-rios" style="background: transparent;">Entre Ríos</option>
                <option value="formosa" style="background: transparent;">Formosa</option>
                <option value="jujuy" style="background: transparent;">Jujuy</option>
                <option value="la-pampa" style="background: transparent;">La Pampa</option>
                <option value="la-rioja" style="background: transparent;">La Rioja</option>
                <option value="mendoza" style="background: transparent;">Mendoza</option>
                <option value="misiones" style="background: transparent;">Misiones</option>
                <option value="neuquen" style="background: transparent;">Neuquén</option>
                <option value="rio-negro" style="background: transparent;">Río Negro</option>
                <option value="salta" style="background: transparent;">Salta</option>
                <option value="san-juan" style="background: transparent;">San Juan</option>
                <option value="santa-cruz" style="background: transparent;">Santa Cruz</option>
                <option value="santa-fe" style="background: transparent;">Santa Fe</option>
                <option value="santiago-del-estero" style="background: transparent;">Santiago del Estero</option>
                <option value="tierra-del-fuego" style="background: transparent;">Tierra del Fuego, Antártida e Islas
                    del Atlántico Sur
                </option>
                <option value="tucuman" style="background: transparent;">Tucumán</option>
            </select>
        </div>
        <div class="caja-usuario">
            <form:input path="domicilio" type="text" name="domicilio" id="domicilio"/>
            <label for="domicilio">Domicilio</label>
        </div>
        <div class="caja-usuario">
            <form:input path="email" type="email" name="email" id="email"/>
            <label for="email">Email</label>
        </div>
        <div class="caja-usuario">
            <form:input path="password" type="password" name="password" id="password"/>
            <label for="password">Contraseña</label>
        </div>
        <div class="caja-usuario">
            <form:input path="repetirPassword" type="password" name="repetirPassword" id="repetirPassword"/>
            <label for="password">Repetir contraseña</label>
        </div>
        <form:button type="submit" class="a">
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
