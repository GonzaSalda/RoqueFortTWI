package ar.edu.grupoesfera.cursospring.controladores;

import ar.edu.grupoesfera.cursospring.modelo.Pizza;
import ar.edu.grupoesfera.cursospring.servicios.PizzaService;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class ControladorAdmin {

    @Autowired
    private PizzaService pizzaService;

    @GetMapping("/editarPizza")
    public String mostrarFormularioEdicion(@RequestParam int id_pizza, Model model) {
        Pizza pizza = pizzaService.buscarPizzaPorId(id_pizza);
        if (pizza == null) {
            throw new RuntimeException("Pizza no encontrada.");
        }
        model.addAttribute("pizza", pizza);
        return "/editarPizza";
    }

    @PostMapping(path = "/actualizarPizza")
    public String actualizarPizza(@RequestParam int id_pizza,
            @RequestParam String nombre,
            @RequestParam String descripcion,
            @RequestParam double precio,
            @RequestParam MultipartFile imagen) {
        pizzaService.actualizarPizza(id_pizza, nombre, descripcion, precio, imagen);
        return "redirect:/menu";
    }

    @GetMapping("/crearPizza")
    public String mostrarFormularioCreacion(Model model) {
        Pizza pizza = new Pizza();
        model.addAttribute("pizza", pizza);
        return "crearPizza";
    }

    @PostMapping(path = "/crearPizza")
    public String crearPizza(@RequestParam String nombre,
            @RequestParam String descripcion,
            @RequestParam double precio,
            @RequestParam MultipartFile imagen) {
        try {
            pizzaService.crearPizza(nombre, descripcion, precio, imagen);
        } catch (IOException e) {
            e.printStackTrace();
            return "error";
        }
        return "redirect:/menu";
    }

    @PostMapping(path = "/eliminarPizza")
    public String eliminarPizza(@RequestParam int id_pizza) {
        pizzaService.eliminarPizza(id_pizza);
        return "redirect:/menu";
    }

}
