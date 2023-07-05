package ar.edu.grupoesfera.cursospring.controladores;

import ar.edu.grupoesfera.cursospring.modelo.*;
import ar.edu.grupoesfera.cursospring.servicios.*;
import com.google.maps.errors.ApiException;
import com.mercadopago.resources.Preference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@Controller
public class ControladorCompra {


    private ServicioPizza servicioPizza;

    private ServicioLogin servicioLogin;

    private ServicioCarrito servicioCarrito;

    private ServicioDelivery servicioDelivery;

    private ServicioMoto servicioMoto;

    @Autowired
    public ControladorCompra(ServicioPizza servicioPizza , ServicioLogin servicioLogin,ServicioCarrito servicioCarrito,ServicioDelivery servicioDelivery, ServicioMoto servicioMoto  ) {
        this.servicioPizza = servicioPizza;
        this.servicioLogin = servicioLogin;
        this.servicioCarrito = servicioCarrito;
        this.servicioDelivery = servicioDelivery;
        this.servicioMoto = servicioMoto;
    }


    private ServicioGoogleMaps servicioGoogleMaps = new ServicioGoogleMaps();

    private ServicioMercadoPago servicioMercadoPago = new ServicioMercadoPago();



    @RequestMapping(path = "/verMediosDePago", method = RequestMethod.POST)
    public ModelAndView verMediosDePago(@RequestParam("precio") Double precioPizza, @RequestParam("id_pizza") int id_pizza) {
        ModelMap model = new ModelMap();
        Pizza pizza = servicioPizza.buscarPizzaPorId(id_pizza);
        Extras tabla = Extras.getInstance();
        Double total;
        total = tabla.total();
        model.put("tabla", tabla.verIngredientes());
        model.put("total", total);
        model.put("precioPizza", precioPizza);
        model.put("pizza", pizza);
        return new ModelAndView("mediosDePago", model);
    }

    @RequestMapping(path = "/comprar", method = RequestMethod.GET)
    public ModelAndView verificacionCompraPorTarjeta(@RequestParam("id_pizza") int idPizza , @RequestParam(value = "delivery", required = false) Boolean delivery, @RequestParam("precio") Double precioPizza, HttpSession session) {

        ModelMap model = new ModelMap();
        String viewName = "";

        // Si comprueba si el usuario tiene iniciada la sesión
        if (session.getAttribute("idUsuario") != null) {
            int id_user = (int) session.getAttribute("idUsuario");
            Usuario usuario = servicioLogin.buscarUsuarioPorId(id_user);
            Pizza pizza_obtenida = servicioPizza.buscarPizzaPorId(idPizza);
            Usuario_Pizza usuarioPizza = servicioLogin.obtenerUsuarioPizza(pizza_obtenida, usuario);
            Extras extras = Extras.getInstance();
            Double total;
            total = extras.total();
            Double totalFinalizado = total + precioPizza;
            model.put("delivery", delivery);
            model.put("extra", total);
            model.put("totalFinalizado", totalFinalizado);
            model.put("idPizza", idPizza);
            model.put("precioPizza", precioPizza);
            model.put("pizza", pizza_obtenida);
            viewName = "verificacionCompra";

        }
        else {
            model.addAttribute("msj_error", "Para comprar necesitas ingresar a tu cuenta.");
            viewName = "redirect:/verListaPizzas";
        }

        return new ModelAndView(viewName, model);
    }


    @RequestMapping(path = "/verificarCompra", method = RequestMethod.POST)
    public ModelAndView verificarCompra(@RequestParam("nroTarjeta") Integer nroTarjeta, @RequestParam("direccion") String direccion, @RequestParam("pizzaId") int pizza_id,  @RequestParam(value = "delivery", required = false) boolean delivery, HttpSession session) throws IOException, InterruptedException, ApiException {
        ModelMap model = new ModelMap();
        int id_user = Integer.parseInt(session.getAttribute("idUsuario").toString());
        Usuario usuario = servicioLogin.buscarUsuarioPorId(id_user);
        Pizza pizza_obtenida = servicioPizza.buscarPizzaPorId(pizza_id);
        Usuario_Pizza usuarioPizza = servicioLogin.obtenerUsuarioPizza(pizza_obtenida, usuario);
        String viewName = "";
        long tiempoEstimadoPreparacion = 60000;
        try {
            servicioLogin.verificarTarjetaUsuario(usuario, nroTarjeta);
            if (delivery) {
                Moto motoAsignada = servicioMoto.asignarMotoDisponible();
                if (motoAsignada != null) {
                    long tiempoViajeMinutos = servicioGoogleMaps.obtenerTiempoViajeMinutos(direccion);
                    tiempoEstimadoPreparacion += tiempoViajeMinutos * 60000;

                    boolean entregaExitosa = servicioDelivery.realizarEntrega(direccion, motoAsignada, tiempoEstimadoPreparacion);
                    String informacionDeEntrega = servicioGoogleMaps.GoogleMapsAPIConfiguration(direccion);

                    model.addAttribute("informacionDeEntrega", informacionDeEntrega);
                    model.addAttribute("entregaExitosa", entregaExitosa);
                    servicioLogin.guardarPizzaEnListaUsuario(pizza_obtenida, usuario);

                    model.addAttribute("tiempoEstimado", tiempoEstimadoPreparacion);
                    viewName = "resultadoEntrega";
                    return new ModelAndView (viewName,model);
                } else {
                    model.addAttribute("entregaExitosa", false);
                    viewName = "resultadoEntrega";
                    return new ModelAndView (viewName,model);
                }
            }
            servicioLogin.guardarPizzaEnListaUsuario(pizza_obtenida, usuario);
            viewName = "compraRealizada";
        }
        catch (Exception e) {
            model.put("tarjetaIncorrecta", "El número de tarjeta ingresado es incorrecto.");
            Extras extras = Extras.getInstance();
            Double extra;
            extra = extras.total();
            Double totalFinalizado = extra + pizza_obtenida.getPrecio();
            model.put("extra", extra);
            model.put("totalFinalizado", totalFinalizado);
            model.put("idPizza", pizza_obtenida.getId());
            model.put("precioPizza", pizza_obtenida.getPrecio());
            viewName = "verificacionCompra";
        }

        return new ModelAndView(viewName, model);
    }




    @RequestMapping(path = "/pagoMP", method = RequestMethod.GET)
    public ModelAndView pagoMP(@RequestParam("idPizza") int idPizza, @RequestParam("precioTotal") Double precioTotal,   @RequestParam(value = "delivery", required = false) boolean delivery, HttpSession session) {
        ModelMap model = new ModelMap();

        String viewName = "";
        if (session.getAttribute("idUsuario") != null) {
            int id_user = (int) session.getAttribute("idUsuario");
            Usuario usuario = servicioLogin.buscarUsuarioPorId(id_user);
            Pizza pizza_obtenida = servicioPizza.buscarPizzaPorId(idPizza);
            Usuario_Pizza usuarioPizza = servicioLogin.obtenerUsuarioPizza(pizza_obtenida, usuario);
                Preference preference = servicioMercadoPago.checkout(usuario, precioTotal);
                model.put("preference", preference);
                model.put("precioTotal", precioTotal);
                model.put("idPizza", idPizza);
                model.put("delivery", delivery);
                viewName = "pagoMP";
            }
            else {
                viewName = "redirect:/verListaPizzas";
            }
        return new ModelAndView(viewName, model);
    }

/*
    @RequestMapping(path = "/pagoRealizadoMP", method = RequestMethod.GET)
    public ModelAndView pagoRealizadoMP(@RequestParam("idPizza") int idPizza,@RequestParam(value = "delivery", required = false) boolean delivery,  HttpSession session) {
        ModelMap model = new ModelMap();
        int id_user = Integer.parseInt(session.getAttribute("idPizza").toString());
        Usuario usuario = servicioLogin.buscarUsuarioPorId(id_user);
        Pizza pizza_obtenida = servicioPizza.buscarPizzaPorId(idPizza);
        Usuario_Pizza usuarioPizza = servicioLogin.obtenerUsuarioPizza(pizza_obtenida, usuario);
        String viewName = "";

        try {
            if (delivery) {
                Moto motoAsignada = servicioMoto.asignarMotoDisponible();
                if (motoAsignada != null) {
                    boolean entregaExitosa = servicioDelivery.realizarEntrega("BuenaVida2020",motoAsignada);
                    model.addAttribute("entregaExitosa", entregaExitosa);
                    servicioLogin.guardarPizzaEnListaUsuario(pizza_obtenida, usuario);
                    viewName = "resultadoEntrega";
                    return new ModelAndView (viewName,model);
                } else {
                    model.addAttribute("entregaExitosa", false);
                    viewName = "resultadoEntrega";
                    return new ModelAndView (viewName,model);
                }
            }
            servicioLogin.guardarPizzaEnListaUsuario(pizza_obtenida, usuario);
            viewName = "compraRealizada";
        }
        catch (Exception e) {
            model.put("Error", "Ocurrio un error.");
            Extras extras = Extras.getInstance();
            Double extra;
            extra = extras.total();
            Double totalFinalizado = extra + pizza_obtenida.getPrecio();
            model.put("extra", extra);
            model.put("totalFinalizado", totalFinalizado);
            model.put("idPizza", pizza_obtenida.getId());
            model.put("precioPizza", pizza_obtenida.getPrecio());
            viewName = "pagoMP";
        }

        return new ModelAndView(viewName, model);
    }
*/

}
