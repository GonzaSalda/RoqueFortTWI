package ar.edu.grupoesfera.cursospring.controladores;

import ar.edu.grupoesfera.cursospring.modelo.Extra;
import ar.edu.grupoesfera.cursospring.modelo.Stock;
import ar.edu.grupoesfera.cursospring.servicios.ServicioExtra;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class ControladorExtras {

    @Autowired
    private ServicioExtra servicioExtra;

    // Mostrar la lista de extras
    @RequestMapping(value = "/stockExistentes")
    public ModelAndView mostrarStockExistente() {
        ModelMap model = new ModelMap();
        List<Extra> listaExtras = servicioExtra.listarTodos();
        model.put("tabla", listaExtras);
        return new ModelAndView("mostrarStock", model);
    }

    // Vista para agregar un nuevo Extra
    @RequestMapping(value = "/agregarExtra")
    public ModelAndView vistaAgregarExtra() {
        return new ModelAndView("agregarExtraAdmin", "command", new Extra());
    }

    // Agregar un nuevo Extra
    @RequestMapping(value = "/insertarExtra", method = RequestMethod.POST)
    public String agregarExtra(@ModelAttribute("command") Extra extra, ModelMap model) {
        servicioExtra.agregarExtra(extra);
        model.put("tabla", servicioExtra.listarTodos());
        return "tablaExtras";
    }

    @RequestMapping(value = "/agregarStock")
    public ModelAndView devolver() {
        // Obtén la lista de todos los extras (productos) desde el servicio
        List<Extra> extras = servicioExtra.listarTodos();

        // Pasa la lista "extras" al modelo con el nombre "tabla"
        return new ModelAndView("agregarStock", "tabla", extras);
    }

    @RequestMapping(value = "/insertarStock", method = RequestMethod.POST)
    public String addStock(@RequestParam("id") Long id,
            @RequestParam("stock") Integer stock,
            ModelMap model) {
        // Llama al servicio para agregar el stock
        servicioExtra.agregarStock(id, stock);

        // Obtener la lista actualizada de todos los extras
        List<Extra> extras = servicioExtra.listarTodos();
        System.out.println("Cantidad de extras: " + extras.size()); // Esto te ayudará a verificar si hay datos

        // Pasar la lista actualizada al modelo
        model.put("tabla", extras);

        // Retorna la vista con la tabla actualizada
        return "agregarStock";
    }

    // Vista para eliminar un Extra
    @RequestMapping("/eliminarExtra/{id}")
    public String eliminarExtra(@PathVariable("id") Long id, ModelMap model) {
        servicioExtra.eliminarExtra(id);
        model.put("tabla", servicioExtra.listarTodos());
        return "tablaExtras";
    }
}
