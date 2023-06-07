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
                           @RequestParam("cantidad") Integer cantidad, ModelMap modelo) {
        Stock tabla = Stock.getInstance();
        tabla.agregarStock(ingrediente, cantidad);
        modelo.put("tabla", tabla.obtenerStock());
        return "agregarStock";
    }

    @RequestMapping("/agregarIngrediente")
    public ModelAndView insertarIngrediente() {
        return new ModelAndView("agregarIngrediente", "command", new Ingrediente());
    }

    @RequestMapping(value = "/insertarIngrediente", method = RequestMethod.POST)
    public String addProduct(@ModelAttribute("command") Ingrediente ingredienteAgregar, ModelMap modelo) {
        Stock tabla = Stock.getInstance();
        tabla.agregarIngrediente(ingredienteAgregar);
        modelo.put("tabla", tabla.listarIngredientesDisponibles());
        return "tablaIngredientes";
    }



    @RequestMapping(value = "/stockExistentes")
    public ModelAndView stockExistentes() {
        ModelMap miMapa = new ModelMap();
        Stock tabla = Stock.getInstance();
        miMapa.put("tabla", tabla.obtenerStock());
        ModelAndView miVista = new ModelAndView();
        miVista.addAllObjects(miMapa);
        miVista.setViewName("mostrarStock");
        return miVista;

    }

    @RequestMapping("/eliminarIngrediente")
    public ModelAndView eliminarIngrediente() {
        return new ModelAndView("eliminarIngredientes", "command", new Ingrediente());
    }

    @RequestMapping(value = "/eliminarIng", method = RequestMethod.POST)
    public String eliminarIngredientes(
            @ModelAttribute("command") Ingrediente ingrediente, ModelMap modelo) {
        Stock tabla = Stock.getInstance();
        tabla.eliminarIngrediente(ingrediente);
        modelo.put("tabla", tabla.obtenerStock());
        return "eliminarIngredientes";
    }

        @RequestMapping(value = "/agregarExtra")
    public ModelAndView agregarExtra() {
        ModelMap miMapa = new ModelMap();
        Stock tabla = Stock.getInstance();
        miMapa.put("tabla", tabla.obtenerStock());
        ModelAndView miVista = new ModelAndView("agregarExtra", "command",
                new Ingrediente());
        miVista.addAllObjects(miMapa);
        return miVista;
    }

    @RequestMapping(value = "/insertarProductoAlCarrito", method = RequestMethod.POST)
    public String addProductToCarrito(@ModelAttribute("command") Ingrediente productoAgregar) {
        ModelMap modelo = new ModelMap();
        Extras carrito = Extras.getInstance();
        carrito.agregarIngrediente(productoAgregar);
        Double total;
        total = carrito.total();
        modelo.put("total", total);
        modelo.put("tabla", carrito.verIngredientes());
        modelo.put("cantProductosAgregados", carrito.verIngredientes().size());
        Stock tabla = Stock.getInstance();
        tabla.agregarStock(productoAgregar, -1);
        modelo.put("tabla", carrito.verIngredientes());
        return "agregarExtra";
    }

    @RequestMapping("/vaciarCarrito")
    public ModelAndView vaciarCarrito() {
        ModelMap miMapa = new ModelMap();

        Extras carrito = Extras.getInstance();
        Stock stock = Stock.getInstance();
        Iterator<Ingrediente> iterator = carrito.verIngredientes().iterator();

        while (iterator.hasNext()) {
            Ingrediente cadaElemento = iterator.next();
            stock.agregarStock(cadaElemento, 1);
        }

        carrito.vaciar();
        miMapa.put("cantProductosAgregados", carrito.verIngredientes().size());
        miMapa.put("tabla", carrito.verIngredientes());
        ModelAndView miVista = new ModelAndView();
        miVista.addAllObjects(miMapa);
        miVista.setViewName("carrito");
        return miVista;

    }

}
