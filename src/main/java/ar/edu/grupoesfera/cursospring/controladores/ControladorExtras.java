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

    @RequestMapping(value = "/stockExistentes")
    public ModelAndView mostrarStockExistente() {
        List<Extra> listaExtras = servicioExtra.listarTodos();
        return new ModelAndView("mostrarStock", "tabla", listaExtras);
    }

    @RequestMapping(value = "/agregarExtra")
    public ModelAndView vistaAgregarExtra() {
        return new ModelAndView("agregarExtraAdmin", "command", new Extra());
    }

    @RequestMapping(value = "/insertarExtra", method = RequestMethod.POST)
    public String agregarExtra(@ModelAttribute("command") Extra extra, ModelMap model) {
        servicioExtra.agregarExtra(extra);
        model.put("tabla", servicioExtra.listarTodos());
        return "tablaExtras";
    }

    @RequestMapping(value = "/agregarStock")
    public ModelAndView devolver() {
        List<Extra> extras = servicioExtra.listarTodos();
        return new ModelAndView("agregarStock", "tabla", extras);
    }

    @RequestMapping(value = "/insertarStock", method = RequestMethod.POST)
    public String addStock(@RequestParam("id") Long id,
            @RequestParam("stock") Integer stock,
            ModelMap model) {
        servicioExtra.agregarStock(id, stock);
        List<Extra> extras = servicioExtra.listarTodos();
        model.put("tabla", extras);
        return "agregarStock";
    }

    @RequestMapping(value = "/eliminarExtra")
    public ModelAndView eliminarExtras() {
        List<Extra> listaExtras = servicioExtra.listarTodos();
        return new ModelAndView("eliminarExtras", "tabla", listaExtras);
    }

    @RequestMapping("/eliminarExtra/{id}")
    public String eliminarExtra(@PathVariable("id") Long id, ModelMap model) {
        servicioExtra.eliminarExtra(id);
        return "redirect:/stockExistentes"; 

    }
}
