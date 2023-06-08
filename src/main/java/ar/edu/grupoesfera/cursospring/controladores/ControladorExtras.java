package ar.edu.grupoesfera.cursospring.controladores;


import java.util.Iterator;
import java.util.Map;

import ar.edu.grupoesfera.cursospring.modelo.Ingrediente;
import ar.edu.grupoesfera.cursospring.modelo.Extras;
import ar.edu.grupoesfera.cursospring.modelo.Stock;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ControladorExtras {

    @ModelAttribute("cantProductosAgregados")
    public Integer cargarCantidad() {
        Extras carrito = Extras.getInstance();
        Integer cantProductosAgregados = carrito.verIngredientes().size();
        return cantProductosAgregados;
    }

    @ModelAttribute("tabla")
    public Map<Ingrediente, Integer> mostrarTabla() {

        Stock tabla = Stock.getInstance();
        return tabla.obtenerStock();
    }

    @RequestMapping(value = "/agregarStock")
    public ModelAndView devolver() {
        return new ModelAndView("agregarStock", "command", new Ingrediente());
    }

    @RequestMapping(value = "/insertarStock", method = RequestMethod.POST)
    public String addStock(@ModelAttribute("command") Ingrediente ingrediente,
                           @RequestParam("cantidad") Integer cantidad, ModelMap model) {
        Stock tabla = Stock.getInstance();
        tabla.agregarStock(ingrediente, cantidad);
        model.put("tabla", tabla.obtenerStock());
        return "agregarStock";
    }

    @RequestMapping("/agregarIngredienteExtra")
    public ModelAndView insertarIngrediente() {
        return new ModelAndView("agregarExtraAdmin", "command", new Ingrediente());
    }

    @RequestMapping(value = "/insertarIngredienteExtra", method = RequestMethod.POST)
    public String addProduct(@ModelAttribute("command") Ingrediente ingredienteAgregar, ModelMap model) {
        Stock tabla = Stock.getInstance();
        tabla.agregarIngrediente(ingredienteAgregar);
        model.put("tabla", tabla.listarIngredientesDisponibles());
        return "tablaExtras";
    }



    @RequestMapping(value = "/stockExistentes")
    public ModelAndView stockExistentes() {
        ModelMap model = new ModelMap();
        Stock tabla = Stock.getInstance();
        model.put("tabla", tabla.obtenerStock());
        ModelAndView miVista = new ModelAndView();
        miVista.addAllObjects(model);
        miVista.setViewName("mostrarStock");
        return miVista;

    }

    @RequestMapping("/eliminarIngredienteExtra")
    public ModelAndView eliminarIngrediente() {
        return new ModelAndView("eliminarExtras", "command", new Ingrediente());
    }

    @RequestMapping(value = "/eliminarIngExtra", method = RequestMethod.POST)
    public String eliminarIngredientes(
            @ModelAttribute("command") Ingrediente ingrediente, ModelMap modelo) {
        Stock tabla = Stock.getInstance();
        tabla.eliminarIngrediente(ingrediente);
        modelo.put("tabla", tabla.obtenerStock());
        return "eliminarExtras";
    }

        @RequestMapping(value = "/agregarExtra")
    public ModelAndView agregarExtra() {
        ModelMap model = new ModelMap();
        Stock tabla = Stock.getInstance();
            model.put("tabla", tabla.obtenerStock());
        ModelAndView miVista = new ModelAndView("agregarExtra", "command",
                new Ingrediente());
        miVista.addAllObjects(model);
        return miVista;
    }

    @RequestMapping(value = "/insertarProductoAlCarrito", method = RequestMethod.POST)
    public String addProductToCarrito(@ModelAttribute("command") Ingrediente productoAgregar) {
        ModelMap model = new ModelMap();
        Extras carrito = Extras.getInstance();
        carrito.agregarIngrediente(productoAgregar);
        Double total;
        total = carrito.total();
        model.put("total", total);
        model.put("tabla", carrito.verIngredientes());
        model.put("cantProductosAgregados", carrito.verIngredientes().size());
        Stock tabla = Stock.getInstance();
        tabla.agregarStock(productoAgregar, -1);
        return "agregarExtra";
    }

    @RequestMapping("/vaciarIngExtras")
    public ModelAndView vaciarIngExtras() {
        ModelMap model = new ModelMap();
        Extras extras = Extras.getInstance();
        Stock stock = Stock.getInstance();
        Iterator<Ingrediente> iterator = extras.verIngredientes().iterator();
        while (iterator.hasNext()) {
            Ingrediente cadaElemento = iterator.next();
            stock.agregarStock(cadaElemento, 1);
        }
        extras.vaciar();
        return new ModelAndView("redirect:/vistaCarrito", model);

    }

    @RequestMapping("/vaciarIngExtrasCompraDirecta")
    public ModelAndView vaciarIngExtrasCompraDirecta(HttpServletRequest request) {
        ModelMap model = new ModelMap();
        Extras extras = Extras.getInstance();
        Stock stock = Stock.getInstance();
        Iterator<Ingrediente> iterator = extras.verIngredientes().iterator();
        while (iterator.hasNext()) {
            Ingrediente cadaElemento = iterator.next();
            stock.agregarStock(cadaElemento, 1);
        }
        extras.vaciar();
        String currentUrl = request.getRequestURL().toString();
        return new ModelAndView("redirect:" + currentUrl, model);

    }

}
