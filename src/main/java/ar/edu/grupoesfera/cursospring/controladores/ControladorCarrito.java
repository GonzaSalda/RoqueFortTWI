
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

import jakarta.servlet.http.HttpSession;
import java.util.List;

@Controller
public class ControladorCarrito {
	private ServicioPizza servicioPizza;

	private ServicioCarrito servicioCarrito;

	private ServicioExtra servicioExtra;

	@Autowired
	public ControladorCarrito(ServicioPizza servicioPizza, ServicioCarrito servicioCarrito,
			ServicioExtra servicioExtra) {
		this.servicioPizza = servicioPizza;
		this.servicioCarrito = servicioCarrito;
		this.servicioExtra = servicioExtra;
	}

	@RequestMapping(path = "/agregarPizzaAlCarrito", method = RequestMethod.POST)
	public ModelAndView agregarPizzaAlCarrito(@RequestParam("id_pizza") int idPizza, HttpSession session) {
		ModelMap model = new ModelMap();
		if (session.getAttribute("idUsuario") != null) {
			int id_user = Integer.parseInt(session.getAttribute("idUsuario").toString());
			Pizza pizza_obtenida = servicioPizza.buscarPizzaPorId(idPizza);
			Carrito carrito = servicioCarrito.obtenerCarritoPorIdUsuario(id_user);
			servicioCarrito.agregarPizzaAlCarrito(pizza_obtenida, carrito);
			model.put("msj_exito", "Se agrego la pizza al carrito con exito!");
		} else {
			model.addAttribute("msj_error", "Para comprar necesitas ingresar a tu cuenta.");
		}

		return new ModelAndView("redirect:/verListaPizzas", model);
	}

	@RequestMapping(path = "/agregarExtraAlCarrito", method = RequestMethod.POST)
	public ModelAndView agregarPizzaAlCarrito(@RequestParam("id_extra") Long idExtra, HttpSession session) {
		ModelMap model = new ModelMap();
		if (session.getAttribute("idUsuario") != null) {
			int id_user = Integer.parseInt(session.getAttribute("idUsuario").toString());
			Extra extra_obtenido = servicioExtra.buscarPorId(idExtra);
			Carrito carrito = servicioCarrito.obtenerCarritoPorIdUsuario(id_user);
			servicioCarrito.agregarExtraAlCarrito(extra_obtenido, carrito);
			model.put("msj_exito", "Se agrego extra al carrito con exito!");
		} else {
			model.addAttribute("msj_error", "Para comprar necesitas ingresar a tu cuenta.");
		}

		return new ModelAndView("redirect:/verListaPizzas", model);
	}

	@RequestMapping(path = "/vistaCarrito", method = RequestMethod.GET)
	public ModelAndView vistaCarrito(HttpSession sesion) {
		ModelMap model = new ModelMap();
		String view = "";
		if (sesion.getAttribute("idUsuario") != null) {
			try {
				int id_user = (int) sesion.getAttribute("idUsuario");
				Carrito carrito = servicioCarrito.obtenerCarritoPorIdUsuario(id_user);
				List<Pizza> pizzas = servicioCarrito.obtenerPizzasDelCarrito(carrito);
				List<Extra> extrass = servicioCarrito.obtenerExtrasDelCarrito(carrito);

				double preciosTotal = servicioCarrito.getTotalDePrecios(pizzas);
				model.put("lista_pizzas_carrito", pizzas);
				model.put("lista_extras_carrito", extrass);

				List<Extra> extras = servicioExtra.listarTodos();
				double totalExtras = extras.stream().mapToDouble(Extra::getStock).sum();
				double totalFinalizado = totalExtras + preciosTotal;
				/* Datos para la vista */
				model.put("lista_pizzas_carrito", pizzas);
				model.put("extra", totalExtras);
				model.put("precio_total", preciosTotal);
				model.put("totalFinalizado", totalFinalizado);
				view = "carrito";
			} catch (Exception e) {
				model.put("msj", "El carrito se encuentra vacio.");
				view = "carrito";
			}
		} else {
			view = "redirect:/";
		}
		return new ModelAndView(view, model);
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
		return new ModelAndView("redirect:/vistaCarrito");
	}

}
