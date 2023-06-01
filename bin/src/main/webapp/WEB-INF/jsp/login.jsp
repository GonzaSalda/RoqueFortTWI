<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" type="text/css" href="css/Estilos.css">
    <title>Login</title>
</head>

<body>
<div class="caja-login">
    <h2>Bienvenido</h2>
    <form:form action="validar-login" method="post" modelAttribute="usuario">

        <div class="caja-usuario">
            <form:input path="email" type="text" name="" required=""/>
            <label>Email</label>
        </div>
        <div class="caja-usuario">
            <form:input path="password" type="password" name="" required=""/>
            <label>Contraseña</label>
        </div>

        <form:button class="a">
            iniciar sesion
        </form:button>

        <a class="a" href="registrar-usuario">No tenés cuenta?</a>

    </form:form>

    <c:if test="${not empty error}">
        <p class="error">${error}</p>
    </c:if>
</div>
</body>
</html>
