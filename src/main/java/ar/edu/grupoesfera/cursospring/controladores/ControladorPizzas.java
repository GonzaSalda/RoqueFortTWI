package ar.edu.grupoesfera.cursospring.controladores;

import ar.edu.grupoesfera.cursospring.modelo.Pizza;
import ar.edu.grupoesfera.cursospring.servicios.ServicioPizza;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class ControladorPizzas {

    @Autowired
    private ServicioPizza servicioPizza;


    @RequestMapping(path= "/verListaPizzas", method= RequestMethod.GET)
    public ModelAndView verListaPizzas(@ModelAttribute("msj_exito") String msj_exito, @ModelAttribute("msj_error") String msj_error, HttpSession session) {
        ModelMap model = new ModelMap();
        List<Pizza> pizzas = servicioPizza.getPizza();
        model.put("lista_pizzas", pizzas);
        model.put("msj_exito", msj_exito);
        model.put("msj_error", msj_error);
        return new ModelAndView("seccionPizzas", model);
    }

   }