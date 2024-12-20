package ar.edu.grupoesfera.cursospring.controladores;

import ar.edu.grupoesfera.cursospring.modelo.Pizza;
import ar.edu.grupoesfera.cursospring.modelo.Usuario;
import ar.edu.grupoesfera.cursospring.servicios.PizzaService;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ControladorPizzas {

    @Autowired
    private PizzaService pizzaService;

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
