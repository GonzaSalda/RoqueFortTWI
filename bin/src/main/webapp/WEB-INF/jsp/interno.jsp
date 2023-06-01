<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="es">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <script src="https://cdn.tailwindcss.com"></script>
    <script src="https://kit.fontawesome.com/ccb0f90ff9.js" crossorigin="anonymous"></script>

    <!-- Add the slick-theme.css if you want default styling -->
    <link rel="stylesheet" type="text/css" href="//cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick.css"/>
    <!-- Add the slick-theme.css if you want default styling -->
    <link rel="stylesheet" type="text/css" href="//cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick-theme.css"/>
    <!-- JQuery-->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/cheerio/1.0.0/cheerio.min.js"></script>
</head>

<body>

<title>Document</title>
</head>

<body class=" bg-black">
<header class=" bg-indigo-900">
    <div class="container mx-auto flex flex-wrap items-center justify-between px-4 py-4">
        <div class="flex items-center flex-shrink-0 text-white ">
            <img class="h-5"
                 src="https://resources.betway.com/Resources/Betway/images/logos/Sports_Betway_InLine.png?v=34a26c39"
                 alt="Logo">
        </div>
        <div class="block lg:hidden">
            <button
                    class="flex items-center px-3 py-2 border rounded text-gray-300 border-gray-400 hover:text-white hover:border-white"
                    id="menu">
                <svg class="h-3 w-3" viewBox="0 0 20 20" xmlns="http://www.w3.org/2000/svg">
                    <title>Menu</title>
                    <path stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="2"
                          d="M4 6h12M4 10h12M4 14h12"/>
                </svg>
            </button>
        </div>
        <nav class="w-full block flex-grow lg:flex lg:items-center lg:w-auto text-center max-sm:hidden max-lg:hidden "
             id="nav-menu">
            <div class="text-sm lg:flex-grow">
                <a href="#" class=" mt-4 lg:inline-block lg:mt-0 text-gray-300 hover:text-white mr-4">
                    Deportes
                </a>
                <a href="#" class=" mt-4 lg:inline-block lg:mt-0 text-gray-300 hover:text-white mr-4">
                    Vivo
                </a>
            </div>
        </nav>
        <div class="flex mx-auto max-sm:hidden max-lg:hidden">
            <p class="font-semibold text-white my-1 mr-2">$1500,00</p>
            <img id="foto-perfil" type="button" data-dropdown-toggle="userDropdown"
                 data-dropdown-placement="bottom-start" class="w-10 h-10 rounded-full cursor-pointer"
                 src="https://static.vecteezy.com/system/resources/previews/005/545/335/original/user-sign-icon-person-symbol-human-avatar-isolated-on-white-backogrund-vector.jpg"
                 alt="User dropdown">

            <div id="userDropdown"
                 class="z-10 hidden bg-white divide-y divide-gray-100 rounded-lg shadow w-44 dark:bg-gray-700 dark:divide-gray-600"
                 id="usuario-perfil">
                <div class="px-4 py-3 text-sm text-gray-900 dark:text-white">
                    <div class="font-medium truncate">${usuario.email}</div>
                </div>
                <ul class="py-2 text-sm text-gray-700 dark:text-gray-200" aria-labelledby="avatarButton">
                    <li>
                        <a href="#"
                           class="block px-4 py-2 hover:bg-gray-100 dark:hover:bg-gray-600 dark:hover:text-white">Historial</a>
                    </li>
                    <li>
                        <a href="#"
                           class="block px-4 py-2 hover:bg-gray-100 dark:hover:bg-gray-600 dark:hover:text-white">Banco</a>
                    </li>
                    <li>
                        <a href="#"
                           class="block px-4 py-2 hover:bg-gray-100 dark:hover:bg-gray-600 dark:hover:text-white">Mensajes</a>
                    </li>
                    <li>
                        <a href="#"
                           class="block px-4 py-2 hover:bg-gray-100 dark:hover:bg-gray-600 dark:hover:text-white">Depositar</a>
                    </li>
                </ul>
                <div class="py-1">
                    <a href="/proyecto_base_spring_war_exploded/"
                       class="block px-4 py-2 text-sm text-gray-700 hover:bg-gray-100 dark:hover:bg-gray-600 dark:text-gray-200 dark:hover:text-white">Salir</a>
                </div>
            </div>

        </div>
    </div>


</header>


<main class="container mx-auto flex">
    <div class="w-1/4  text-white bg-neutral-800">
        <div class="flex flex-col justify-start ml-2">
            <div class="font-bold text-teal-400 ">Populares</div>
            <a href="#" class="p-4  hover:bg-gray-300 hover:text-black"><img
                    src="https://m.caba.codere.bet.ar/deportes/assets/img/banderas/Ico_AR.png"
                    class="h-5 inline ">
                LPF</a>
            <a href="#" class="p-4  hover:bg-gray-300  hover:text-black"><img
                    src="https://m.caba.codere.bet.ar/deportes/assets/img/river_plate.png" class="h-5 inline ">
                River</a>
            <a href="#" class="p-4 hover:bg-gray-300 hover:text-black"><i class='far fa-futbol '></i> Fútbol</a>
            <a href="#" class="p-4 hover:bg-gray-300 hover:text-black"><i class="fas fa-volleyball-ball "></i>
                Volley</a>
            <a href="#" class="p-4 hover:bg-gray-300 hover:text-black"><i class="fas fa-basketball-ball "></i>
                Basketball</a>
        </div>
        <div class="flex flex-col justify-start ml-2">
            <div class="font-bold text-teal-400 ">Deportes</div>

            <a href="#" class="p-4 hover:bg-gray-300 hover:text-black"><i class='far fa-futbol '></i> Fútbol</a>
            <a href="#" class="p-4 hover:bg-gray-300 hover:text-black"><i class="fas fa-volleyball-ball "></i>
                Volley</a>
            <a href="#" class="p-4 hover:bg-gray-300 hover:text-black"><i class="fas fa-basketball-ball "></i>
                Basketball</a>
            <a href="#" class="p-4 hover:bg-gray-300 hover:text-black"><i class="fas fa-football-ball"></i>
                Rugby</a>
            <a href="#" class="p-4 hover:bg-gray-300 hover:text-black"><i class="fas fa-golf-ball "></i>
                Golf</a>
            <a href="#" class="p-4 hover:bg-gray-300 hover:text-black"><i class="fas fa-table-tennis "></i>
                Ping-pong</a>
            <a href="#" class="p-4 hover:bg-gray-300 hover:text-black"><i class="fas fa-hockey-puck "></i>
                Hockey sobre hielo</a>
            <a href="#" class="p-4 hover:bg-gray-300 hover:text-black"><i class="fas fa-hockey-puck "></i>
                Hockey sobre hielo</a>
            <a href="#" class="p-4 hover:bg-gray-300 hover:text-black"><i class="fas fa-hockey-puck "></i>
                Hockey sobre hielo</a>
            <a href="#" class="p-4 hover:bg-gray-300 hover:text-black"><i class="fas fa-hockey-puck "></i>
                Hockey sobre hielo</a>
            <a href="#" class="p-4 hover:bg-gray-300 hover:text-black"><i class="fas fa-hockey-puck "></i>
                Hockey sobre hielo</a>
        </div>


    </div>
    <div class="w-2/3 bg-white">
        <section>
            <div class="banner-">
                <div>
                    <!-- <img src="https://mir-s3-cdn-cf.behance.net/project_modules/max_1200/6a3f7d162097293.63d133d81d53a.gif"
                        alt="" class="w-full h-48"> -->

                    <div class="w-full h-48 bg-gray-300">
                    </div>
                </div>
                <!-- <div>
                    <img
                        src="https://mir-s3-cdn-cf.behance.net/project_modules/max_1200/6a3f7d162097293.63d133d81d53a.gif"
                        alt="" class="w-full h-48"></div>
                <div>
                    <img
                        src="https://mir-s3-cdn-cf.behance.net/project_modules/max_1200/6a3f7d162097293.63d133d81d53a.gif"
                        alt="" class="w-full"></div>
                <div>
                    <img
                        src="https://mir-s3-cdn-cf.behance.net/project_modules/max_1200/6a3f7d162097293.63d133d81d53a.gif"
                        alt="" class="w-full"></div> -->
            </div>

            <%--            <h1 class="text-xl font-bold leading-tight tracking-tight  md:text-2xl ">--%>
            <%--                Hola, ${usuario.nombre}.--%>
            <%--            </h1>--%>
        </section>
        <section>
            <div class="eventos ml-2 mr-2">
                <div id="eventos" class="flex flex-wrap mt-2">

                    <div class="text-center align-middle w-1/3">
                        <p class="font-semibold text-center"><i class='far fa-futbol'></i> Fútbol</p>
                    </div>
                    <div class="w-2/3 flex justify-end">
                        <div class="w-16 font-semibold">1</div>
                        <div class="w-16 font-semibold">x</div>
                        <div class="w-16 font-semibold">2</div>
                    </div>

                    <div class="card border p-4 w-full flex bg-neutral-100">
                        <div class="text-center align-middle w-1/3">
                            <p class="font-semibold text-start">Belgrano vs talleres</p>
                            <p class="text-start">Domingo 21.30hs</p>
                        </div>
                        <div class="w-2/3 text-end">
                            <button type="button"
                                    class="rounded-none w-16 h-full bg-slate-200 cuota-partida">160
                            </button>
                            <button type="button"
                                    class="rounded-none w-16 h-full bg-slate-200 cuota-partida">120
                            </button>
                            <button type="button"
                                    class="rounded-none w-16 h-full bg-slate-200 cuota-partida">190
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </section>
    </div>
    <div class="w-1/4 bg-white">
        <section>
            <div class="eventos m-2">

                <a href="#"
                   class="block max-w-sm p-6 bg-white border border-gray-200 rounded-lg shadow hover:bg-gray-100 dark:bg-gray-800 dark:border-gray-700 dark:hover:bg-gray-700">
                    <h5 class="mb-2 text-2xl font-bold tracking-tight text-gray-900 text-teal-400 border-b">Apuesta
                    </h5>
                    <p class="font-normal text-gray-700 dark:text-gray-400">Sus selecciones serán añadidas</p>
                </a>

            </div>
        </section>
    </div>


</main>


<!-- Tailwind-->
<script src="https://cdnjs.cloudflare.com/ajax/libs/flowbite/1.6.5/flowbite.min.js"></script>


<!-- Slick -->
<script type="text/javascript" src="//cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick.min.js"></script>

<script type="text/javascript">
    $(document).ready(function () {
        $('.banner').slick({
            dots: true,
            infinite: true,
            speed: 300,
            slidesToShow: 1,
            adaptiveHeight: true,
            autoplay: true,
            arrows: false
        });
    });
</script>


<!-- Api partidos-->
<script>
    //const apiKey = 'ea254dc3d42102065c43dc894e86919f' //llego al limite
    //const apiKey = '3fdb51580260e18a500b66aad3fb10e7'
    const region = 'eu';
    const sport = 'soccer_argentina_primera_division'

    fetch(`https://api.the-odds-api.com/v4/sports/${sport}/odds/?apiKey=${apiKey}&regions=${region}&market=h2h`)
        .then(response => response.json())
        .then(data => {
            console.log(data)


            for (evento of data) {
                console.log(evento)
                let partido = document.createElement('div')
                partido.classList.add('card', 'border', 'p-4', 'w-full', 'flex', 'bg-neutral-100')
                partido.innerHTML = `
                    <div class="text-center align-middle w-1/3">
                    <p class="font-semibold text-start">${evento["home_team"]} VS ${evento["away_team"]}</p>
                    <p class="text-start">${evento["commence_time"]}</p>
                    </div> 

                  <div class="w-2/3 text-end">
                  <button type="button" class="rounded-none w-16 h-full bg-slate-200 cuota-partida">${evento['bookmakers'][0]['markets'][0]['outcomes'][0]['price']}</button>
                  <button type="button" class="rounded-none w-16 h-full bg-slate-200 cuota-partida">${evento['bookmakers'][0]['markets'][0]['outcomes'][1]['price']}</button>
                  <button type="button" class="rounded-none w-16 h-full bg-slate-200 cuota-partida">${evento['bookmakers'][0]['markets'][0]['outcomes'][2]['price']}</button>
                  </div> `
                document.querySelector("#eventos").appendChild(partido)
            }
        })
        .catch(error => console.error(error));
</script>

<!--Camba los estados de los colores de las cuotas-->
<script>
    const divs = document.querySelectorAll('.cuota-partida')

    divs.forEach((div) => {
        let isActive = false

        div.addEventListener('click', () => {
            if (isActive) {
                div.classList.remove('bg-slate-200')
                div.classList.add('bg-sky-300')
                isActive = false
            } else {
                div.classList.remove('bg-sky-300')
                div.classList.add('bg-slate-200')
                isActive = true
            }
        })
    })
</script>
</body>

</html>