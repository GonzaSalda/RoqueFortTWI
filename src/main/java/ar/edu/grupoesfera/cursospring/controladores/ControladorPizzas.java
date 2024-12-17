package ar.edu.grupoesfera.cursospring.controladores;

import ar.edu.grupoesfera.cursospring.modelo.Pizza;
import ar.edu.grupoesfera.cursospring.servicios.ServicioCarrito;
import ar.edu.grupoesfera.cursospring.servicios.ServicioLogin;
import ar.edu.grupoesfera.cursospring.servicios.ServicioPizza;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpSession;
import java.util.List;

@Controller
public class ControladorPizzas {

    private ServicioLogin servicioLogin;
    private ServicioPizza servicioPizza;
    private ServicioCarrito servicioCarrito;

    @Autowired
    public ControladorPizzas(ServicioLogin servicioLogin, ServicioPizza servicioPizza, ServicioCarrito servicioCarrito) {
        this.servicioLogin = servicioLogin;
        this.servicioPizza = servicioPizza;
        this.servicioCarrito = servicioCarrito;
    }


    @RequestMapping(path= "/verListaPizzas", method= RequestMethod.GET)
    public ModelAndView verListaPizzas(@ModelAttribute("msj_exito") String msj_exito, @ModelAttribute("msj_error") String msj_error, HttpSession session) {
        ModelMap model = new ModelMap();
        List<Pizza> pizzas = servicioPizza.getPizza();
        model.put("lista_pizzas", pizzas);
        model.put("msj_exito", msj_exito);
        model.put("msj_error", msj_error);
        return new ModelAndView("seccionPizzas", model);
    }

    @RequestMapping("/crearPizza")
    public ModelAndView cargarVista(){
        ModelMap model = new ModelMap();
        Pizza pizza = new Pizza();
        model.put("datosCrearPizza", pizza);

        return new ModelAndView("crearPizza", model);
    }

    @RequestMapping(path ="/guardarPizza", method= RequestMethod.POST)
    public ModelAndView pizzaCreada(@ModelAttribute("datosCrearPizza") Pizza datosPizza){
        ModelMap model = new ModelMap();
        servicioPizza.savePizza(datosPizza);
        return new ModelAndView("crearPizza", model);
    }


   }
