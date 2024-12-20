package ar.edu.grupoesfera.cursospring.controladores;

import ar.edu.grupoesfera.cursospring.modelo.Pizza;
import ar.edu.grupoesfera.cursospring.modelo.Usuario;
import ar.edu.grupoesfera.cursospring.servicios.PizzaService;
import ar.edu.grupoesfera.cursospring.servicios.ServicioPizza;
import org.springframework.ui.Model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpSession;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ControladorPizzas {

    private ServicioPizza servicioPizza;

    @Autowired
    private PizzaService pizzaService;

    @Autowired
    public ControladorPizzas(ServicioPizza servicioPizza) {
        this.servicioPizza = servicioPizza;
    }

    @GetMapping("/menu")
    public String verPizzas(Model model, HttpSession session) {
        Usuario usuario = (Usuario) session.getAttribute("user");
        if (usuario != null) {
            List<Pizza> pizzas = pizzaService.getPizza();
            model.addAttribute("lista_pizzas", pizzas);
            return "seccionPizzas";
        }

        return "redirect:/login";
    }


 

}
