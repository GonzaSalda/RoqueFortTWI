package ar.edu.grupoesfera.cursospring.controladores;

import ar.edu.grupoesfera.cursospring.modelo.*;
import ar.edu.grupoesfera.cursospring.servicios.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class ControladorCompra {

    private ServicioPizza servicioPizza;

    private ServicioExtra servicioExtra;

    @Autowired
    public ControladorCompra(ServicioPizza servicioPizza, ServicioExtra servicioExtra) {
        this.servicioPizza = servicioPizza;
        this.servicioExtra = servicioExtra;
    }

    @RequestMapping(path = "/verMediosDePago", method = RequestMethod.POST)
    public ModelAndView verMediosDePago(@RequestParam("precio") Double precioPizza,
            @RequestParam("id_pizza") int id_pizza) {
        ModelMap model = new ModelMap();
        Pizza pizza = servicioPizza.buscarPizzaPorId(id_pizza);
        // Obtener los extras y calcular el total
        List<Extra> extras = servicioExtra.listarTodos();
        double totalExtras = extras.stream().mapToDouble(Extra::getStock).sum(); // Ajusta si `stock` es un precio.
        model.put("tabla", extras);
        model.put("total", totalExtras);
        model.put("precioPizza", precioPizza);
        model.put("pizza", pizza);
        return new ModelAndView("mediosDePago", model);
    }

}
