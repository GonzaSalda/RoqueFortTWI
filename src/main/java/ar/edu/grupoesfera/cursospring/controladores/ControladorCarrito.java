
package ar.edu.grupoesfera.cursospring.controladores;

import ar.edu.grupoesfera.cursospring.modelo.*;
import ar.edu.grupoesfera.cursospring.servicios.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;

import jakarta.servlet.http.HttpSession;

@Controller
public class ControladorCarrito {

	@Autowired
	private CarritoService carritoService;

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

		String errorMessage = (String) session.getAttribute("errorMessage");
		model.addAttribute("errorMessage", errorMessage);
		session.removeAttribute("errorMessage");

		Usuario usuario = (Usuario) session.getAttribute("user");
		if (usuario != null) {
			Carrito carrito = carritoService.obtenerOCrearCarrito(usuario.getId());

			Double totalPizza = carritoService.calcularTotalPizzas(carrito.getId());
			Double totalExtra = carritoService.calcularTotalExtras(carrito.getId());
			Double totalFinalizado = totalPizza + totalExtra;

			model.addAttribute("carrito", carrito);
			model.addAttribute("carritoPizzas", carrito.getCarritoPizzas());
			model.addAttribute("totalPizza", totalPizza);
			model.addAttribute("totalExtra", totalExtra);
			model.addAttribute("totalFinalizado", totalFinalizado);
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

	@GetMapping("/actualizarCantidadExtra")
	public String actualizarCantidadExtra(@RequestParam int extra_id, @RequestParam String accion,
			HttpSession session) {
		Usuario usuario = (Usuario) session.getAttribute("user");
		if (usuario != null) {
			try {
				Carrito carrito = carritoService.obtenerOCrearCarrito(usuario.getId());
				if ("sumar".equals(accion)) {
					carritoService.actualizarCantidadExtra(carrito.getId(), extra_id, 1);
				} else if ("restar".equals(accion)) {
					carritoService.actualizarCantidadExtra(carrito.getId(), extra_id, -1);
				}
			} catch (RuntimeException e) {
				// Guardar el mensaje de error en la sesión
				session.setAttribute("errorMessage", e.getMessage());
				return "redirect:/carrito";
			}
		}
		return "redirect:/carrito";
	}

	@GetMapping("/eliminarPizzaDeCarrito")
	public String eliminarPizzaDeCarrito(@RequestParam int pizza_id, HttpSession session) {
		Usuario usuario = (Usuario) session.getAttribute("user");
		if (usuario != null) {
			Carrito carrito = carritoService.obtenerOCrearCarrito(usuario.getId());
			carritoService.eliminarPizzaDelCarrito(carrito.getId(), pizza_id);
		}
		return "redirect:/carrito";
	}

	@GetMapping("/eliminarExtraDeCarrito")
	public String eliminarExtraDeCarrito(@RequestParam int extra_id, HttpSession session) {
		Usuario usuario = (Usuario) session.getAttribute("user");
		if (usuario != null) {
			Carrito carrito = carritoService.obtenerOCrearCarrito(usuario.getId());
			carritoService.eliminarExtraDelCarrito(carrito.getId(), extra_id);
		}
		return "redirect:/carrito";
	}

}
