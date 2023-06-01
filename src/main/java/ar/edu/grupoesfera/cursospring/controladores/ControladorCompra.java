package ar.edu.grupoesfera.cursospring.controladores;

import ar.edu.grupoesfera.cursospring.modelo.Pizza;
import ar.edu.grupoesfera.cursospring.modelo.Extras;
import ar.edu.grupoesfera.cursospring.modelo.Usuario;
import ar.edu.grupoesfera.cursospring.modelo.Usuario_Pizza;
import ar.edu.grupoesfera.cursospring.servicios.ServicioLogin;
import ar.edu.grupoesfera.cursospring.servicios.ServicioMercadoPago;
import ar.edu.grupoesfera.cursospring.servicios.ServicioPizza;
import com.mercadopago.resources.Preference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
public class ControladorCompra {

    @Autowired
    private ServicioPizza servicioPizza;

    @Autowired
    private ServicioLogin servicioLogin;


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
    public ModelAndView verificacionCompra(@RequestParam("id_pizza") int idPizza, @RequestParam("precio") Double precioPizza, HttpSession session) {

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
    public ModelAndView verificarCompra(@RequestParam("nroTarjeta") Integer nroTarjeta, @RequestParam("pizzaId") int pizza_id, HttpSession session) {

        ModelMap model = new ModelMap();
        int id_user = Integer.parseInt(session.getAttribute("idUsuario").toString());
        Usuario usuario = servicioLogin.buscarUsuarioPorId(id_user);
        Pizza pizza_obtenida = servicioPizza.buscarPizzaPorId(pizza_id);
        Usuario_Pizza usuarioPizza = servicioLogin.obtenerUsuarioPizza(pizza_obtenida, usuario);
        String viewName = "";

        try {
            servicioLogin.verificarTarjetaUsuario(usuario, nroTarjeta);
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
    public ModelAndView pagoMP(@RequestParam("idPizza") int idPizza, @RequestParam("precioTotal") Double precioTotal, HttpSession session) {
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
                viewName = "pagoMP";
            }
            else {
                viewName = "redirect:/verListaPizzas";
            }
        return new ModelAndView(viewName, model);
    }

    @RequestMapping(path = "/pagoRealizadoMP", method = RequestMethod.GET)
    public ModelAndView pagoRealizadoMP(@RequestParam("idPizza") int idPizza,  HttpSession session) {
        ModelMap model = new ModelMap();
        int id_user = Integer.parseInt(session.getAttribute("idPizza").toString());
        Usuario usuario = servicioLogin.buscarUsuarioPorId(id_user);
        Pizza pizza_obtenida = servicioPizza.buscarPizzaPorId(idPizza);
        Usuario_Pizza usuarioPizza = servicioLogin.obtenerUsuarioPizza(pizza_obtenida, usuario);
        servicioLogin.guardarPizzaEnListaUsuario(pizza_obtenida, usuario);
        return new ModelAndView("compraRealizada", model);
    }

}