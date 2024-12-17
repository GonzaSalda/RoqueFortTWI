package ar.edu.grupoesfera.cursospring.controladores;

import ar.edu.grupoesfera.cursospring.modelo.DatosCreacionPizza;
import ar.edu.grupoesfera.cursospring.modelo.Pizza;
import ar.edu.grupoesfera.cursospring.servicios.ServicioPizza;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ControladorAdmin {

    @Autowired
    private ServicioPizza servicioPizza;

    @RequestMapping("/editarPizza")
    public ModelAndView irAEditarPizza(@RequestParam("id_pizza") int pizzaId) {
        Pizza pizza = servicioPizza.buscarPizzaPorId(pizzaId);

        ModelMap modelo = new ModelMap();
        DatosCreacionPizza datosCrearPizza = new DatosCreacionPizza();
        modelo.put("datosCrearPizza", datosCrearPizza);
        modelo.put("nombrePizza", pizza.getNombre());
        modelo.put("descPizza", pizza.getDescripcion());
        modelo.put("precioPizza", pizza.getPrecio());
        modelo.put("pizzaId", pizza.getId());
        return new ModelAndView("editarPizza", modelo);

    }

    @RequestMapping(path = "/pizzaActualizado", method = RequestMethod.POST)
    public ModelAndView actualizarPizza(@RequestParam("id_pizza") int idPizza,
            @ModelAttribute("datosCrearPizza") DatosCreacionPizza datosCrearPizza) {
        ModelMap modelo = new ModelMap();

        servicioPizza.actualizarPizza(idPizza, datosCrearPizza.getNombre(), datosCrearPizza.getDescripcion(),
                datosCrearPizza.getPrecio());
        modelo.put("datosCrearCurso", new DatosCreacionPizza());

        return new ModelAndView("pizzaActualizado", modelo);
    }

}
