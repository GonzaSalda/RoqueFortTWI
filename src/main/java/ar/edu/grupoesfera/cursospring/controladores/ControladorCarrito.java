
package ar.edu.grupoesfera.cursospring.controladores;

import ar.edu.grupoesfera.cursospring.modelo.*;
import ar.edu.grupoesfera.cursospring.servicios.*;
import com.google.maps.errors.ApiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@Controller
public class ControladorCarrito {
	private ServicioPizza servicioPizza;

	private ServicioLogin servicioLogin;

	private ServicioCarrito servicioCarrito;

	private ServicioDelivery servicioDelivery;

	private ServicioMoto servicioMoto;
	private ServicioExtra servicioExtra;

	@Autowired
	public ControladorCarrito(ServicioPizza servicioPizza, ServicioLogin servicioLogin, ServicioCarrito servicioCarrito,
			ServicioDelivery servicioDelivery, ServicioMoto servicioMoto, ServicioExtra servicioExtra) {
		this.servicioPizza = servicioPizza;
		this.servicioLogin = servicioLogin;
		this.servicioCarrito = servicioCarrito;
		this.servicioDelivery = servicioDelivery;
		this.servicioMoto = servicioMoto;
		this.servicioExtra = servicioExtra;
	}

	private ServicioGoogleMaps servicioGoogleMaps = new ServicioGoogleMaps();

	/*
	 * private ServicioMercadoPago servicioMercadoPago = new ServicioMercadoPago();
	 */

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

	@RequestMapping(path = "/comprarporTarjeta", method = RequestMethod.GET)
	public ModelAndView comprarporTarjeta(@RequestParam(value = "delivery", required = false) Boolean delivery,
			@RequestParam("precioTotal") Double precioTotal, HttpSession session) {
		ModelMap model = new ModelMap();
		String viewName = "";
		// Obtener extras desde el servicio
		List<Extra> extras = servicioExtra.listarTodos();
		double totalExtras = extras.stream().mapToDouble(Extra::getStock).sum(); 
		double totalFinalizado = totalExtras + precioTotal;
		model.put("delivery", delivery);
		model.put("extra", totalExtras);
		model.put("totalFinalizado", totalFinalizado);
		model.put("precioPizza", precioTotal);
		viewName = "verificacionCompraPorTarjeta";
		return new ModelAndView(viewName, model);
	}

	@RequestMapping(path = "/verificarCompraPorTarjeta", method = RequestMethod.POST)
	public ModelAndView verificarCompraPorTarjeta(@RequestParam("nroTarjeta") Integer nroTarjeta,
			@RequestParam("direccion") String direccion,
			@RequestParam(value = "delivery", required = false) boolean delivery, HttpSession session)
			throws IOException, InterruptedException, ApiException {
		ModelMap model = new ModelMap();
		int id_user = Integer.parseInt(session.getAttribute("idUsuario").toString());
		Usuario usuario = servicioLogin.buscarUsuarioPorId(id_user);
		String viewName = "";

		Carrito carrito = servicioCarrito.obtenerCarritoPorIdUsuario(id_user);
		// Obtengo las pizzas y las guardo en una variable lista
		List<Pizza> pizzas = servicioCarrito.obtenerPizzasDelCarrito(carrito);
		boolean enCola = false;

		try {
			servicioLogin.verificarTarjetaUsuario(usuario, nroTarjeta);
			long tiempoEstimadoPreparacion = 2;
			long tiempoViajeMinutos = servicioGoogleMaps.obtenerTiempoViajeMinutos(direccion);
			tiempoEstimadoPreparacion += tiempoViajeMinutos;
			if (delivery) {
				Moto motoAsignada = servicioMoto.asignarMotoDisponible();
				if (motoAsignada != null) {
					boolean entregaExitosa = servicioDelivery.realizarEntrega(direccion, motoAsignada,
							tiempoEstimadoPreparacion);
					String informacionDeEntrega = servicioGoogleMaps.GoogleMapsAPIConfiguration(direccion);
					model.addAttribute("informacionDeEntrega", informacionDeEntrega);
					model.addAttribute("entregaExitosa", entregaExitosa);
					model.addAttribute("tiempoEstimado", Long.toString(tiempoEstimadoPreparacion));
					servicioCarrito.comprarPizzasDelCarrito(pizzas, usuario);
					// Se vacia la lista del carrito
					List<Carrito_Pizza> pizzasCarrito = servicioCarrito.obtenerCarritoPizzas(carrito);
					servicioCarrito.vaciarCarrito(pizzasCarrito);
					viewName = "resultadoEntrega";
					return new ModelAndView(viewName, model);
				} else {
					enCola = true;
					CompraCola compraCola = new CompraCola(usuario, pizzas, direccion, delivery, tiempoViajeMinutos);
					CompraCola compraColaAnterior = servicioDelivery.obtenerUltimaCompraCola(); // Obtener la última
																								// compra en la cola
					if (compraColaAnterior != null) {
						long tiempoViajeMinutosAnterior = compraColaAnterior.getTiempoViajeMinutos();
						tiempoViajeMinutos += tiempoViajeMinutosAnterior;
					}
					servicioDelivery.agregarCompraCola(compraCola);
					model.addAttribute("tiempoEstimado", tiempoViajeMinutos);
					model.addAttribute("enCola", enCola);
					viewName = "enColaCompra";
					return new ModelAndView(viewName, model);
				}
			}
			servicioCarrito.comprarPizzasDelCarrito(pizzas, usuario);
			// Se vacia la lista del carrito
			List<Carrito_Pizza> pizzasCarrito = servicioCarrito.obtenerCarritoPizzas(carrito);
			servicioCarrito.vaciarCarrito(pizzasCarrito);
			viewName = "compraRealizada";
		} catch (Exception e) {
			model.put("tarjetaIncorrecta", "El número de tarjeta ingresado es incorrecto.");
			viewName = "verificacionCompraPorTarjeta";
		}
		return new ModelAndView(viewName, model);
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
