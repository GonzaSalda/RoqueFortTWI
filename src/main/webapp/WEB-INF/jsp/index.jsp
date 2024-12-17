<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <%@ page pageEncoding="UTF-8" %>

            <!DOCTYPE html>
            <html lang="es">

            <head>
                <meta charset="UTF-8">
                <meta http-equiv="X-UA-Compatible" content="IE=edge">
                <meta name="viewport" content="width=device-width, initial-scale=1.0">
                <script src="https://kit.fontawesome.com/ccb0f90ff9.js" crossorigin="anonymous"></script>
                <link href="/output.css" rel="stylesheet">
                <title>RoqueFort</title>
            </head>

            <body class="bg-bgCustom3 bg-cover bg-no-repeat min-h-screen">

                <!-- Incluir el header desde otro archivo JSP -->
                <jsp:include page="/WEB-INF/jsp/header.jsp" />

                <main class="flex items-center justify-center m-4">
                    <div class="relative w-[1137px] h-[403px] md:h-[503px] bg-bgHeader bg-opacity-80 flex flex-col  p-6 gap-y-6">
                        <div class="flex items-center h-full">
                            <img class="max-w-[100%] w-[650px]" src="https://kentucky.com.ar/imgs/grafico_amamos.svg"
                                alt="Amamos">
                        </div>
                        <div class="flex justify-end">
                            <img class="max-w-[100%] w-[550px]"
                                src="https://s3.sa-east-1.amazonaws.com/static-content.betwarrior.bet/Product/Landing_page/5158/img/app.svg"
                                alt="Decargá nuestra app">
                        </div>
                    </div>
                </main>

            </body>

            </html>