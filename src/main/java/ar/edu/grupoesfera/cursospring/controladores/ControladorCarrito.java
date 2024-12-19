
package ar.edu.grupoesfera.cursospring.controladores;

import ar.edu.grupoesfera.cursospring.modelo.*;
import ar.edu.grupoesfera.cursospring.servicios.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.ui.Model;

import jakarta.servlet.http.HttpSession;

@Controller
public class ControladorCarrito {
	private ServicioPizza servicioPizza;

	private ServicioCarrito servicioCarrito;

	@Autowired
	private CarritoService carritoService;

	@Autowired
	public ControladorCarrito(ServicioPizza servicioPizza, ServicioCarrito servicioCarrito,
			ServicioExtra servicioExtra) {
		this.servicioPizza = servicioPizza;
		this.servicioCarrito = servicioCarrito;
	}

	@PostMapping("/agregarPizzaAlCarrito")
	public String agregarPizzaAlCarrito(@RequestParam int id_pizza, @RequestParam double precio, HttpSession session) {
		Usuario usuario = (Usuario) session.getAttribute("user");
		if (usuario != null) {
			Carrito carrito = carritoService.obtenerOCrearCarrito(usuario.getId());

			// Llamar al servicio para agregar la pizza al carrito
			carritoService.agregarPizzaAlCarrito(carrito.getId(), id_pizza, 1);
		}
		return "redirect:/carrito";
	}

	@PostMapping(path = "/agregarExtraAlCarrito")
	public String agregarExtraAlCarrito(@RequestParam int id_extra, HttpSession session) {
		Usuario usuario = (Usuario) session.getAttribute("user");
		if (usuario != null) {
			Carrito carrito = carritoService.obtenerOCrearCarrito(usuario.getId());
			carritoService.agregarExtraAlCarrito(carrito.getId(), id_extra, 1);
		}
		return "redirect:/carrito";
	}

	@GetMapping("/carrito")
	public String verCarrito(Model model, HttpSession session) {
		Usuario usuario = (Usuario) session.getAttribute("user");
		if (usuario != null) {
			Carrito carrito = carritoService.obtenerOCrearCarrito(usuario.getId());

			model.addAttribute("carrito", carrito);
			model.addAttribute("carritoPizzas", carrito.getCarritoPizzas());
			return "carrito";
		}
		return "redirect:/login";
	}

	@GetMapping("/actualizarCantidadPizza")
	public String actualizarCantidadPizza(@RequestParam int pizza_id, @RequestParam String accion,
			HttpSession session) {
		Usuario usuario = (Usuario) session.getAttribute("user");
		if (usuario != null) {
			Carrito carrito = carritoService.obtenerOCrearCarrito(usuario.getId());

			if ("agregar".equals(accion)) {
				carritoService.actualizarCantidadPizza(carrito.getId(), pizza_id, 1);
			} else if ("restar".equals(accion)) {
				carritoService.actualizarCantidadPizza(carrito.getId(), pizza_id, -1);
			}
		}
		return "redirect:/carrito";
	}

	@RequestMapping(path = "/eliminarPizzaDeListaCarrito", method = RequestMethod.GET)
	public ModelAndView eliminarPizzaDeListaCarrito(@RequestParam("pizza_id") int pizza_id, HttpSession sesion) {
		int id_user = (int) sesion.getAttribute("idUsuario");
		Pizza pizza = servicioPizza.buscarPizzaPorId(pizza_id);
		Carrito carrito = servicioCarrito.obtenerCarritoPorIdUsuario(id_user);
		Carrito_Pizza carritoPizza = servicioCarrito.obtenerCarritoPizza(carrito, pizza);
		if (carritoPizza != null) {
			servicioCarrito.eliminarPizzaDelCarrito(carritoPizza);
		}
		return new ModelAndView("redirect:/carrito");
	}

}
