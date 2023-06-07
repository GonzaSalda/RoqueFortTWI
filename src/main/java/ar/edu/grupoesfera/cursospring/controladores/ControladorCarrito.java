
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


import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;


@Controller
public class ControladorCarrito {
	@Autowired
	private ServicioPizza servicioPizza;

	@Autowired
	private ServicioLogin servicioLogin;

	@Autowired
	private ServicioCarrito servicioCarrito;

	@Autowired
	private ServicioDelivery servicioDelivery;

	@Autowired
	ServicioMoto servicioMoto;


	@RequestMapping(path ="/vistaCarrito", method = RequestMethod.GET)
	public ModelAndView vistaCarrito(HttpSession sesion) {
		ModelMap model = new ModelMap();
		String view = "";
		if (sesion.getAttribute("idUsuario") != null) {
			try {
				int id_user = (int) sesion.getAttribute("idUsuario");

				/*Obtengo el carrito del usuario*/
				Carrito carrito = servicioCarrito.obtenerCarritoPorIdUsuario(id_user);

				/*Obtengo las pizzas de ese carrito*/
				List<Pizza> pizzas = servicioCarrito.obtenerPizzasDelCarrito(carrito);

				/*Obtengo el precio total de las pizzas*/
				double preciosTotal = servicioCarrito.getTotalDePrecios(pizzas);

				/*Lo muestro en las vistas al array*/
				model.put("lista_pizzas_carrito", pizzas);
				model.put("precio_total", preciosTotal);
				view = "carrito";
			}
			catch (Exception e) {
				model.put("msj", "El carrito se encuentra vacio.");
				view = "carrito";
			}
		}
		else {
			view = "redirect:/";
		}
		return new ModelAndView(view, model);
	}

	@RequestMapping(path = "/comprarporTarjeta", method = RequestMethod.GET)
	public ModelAndView comprarporTarjeta(@RequestParam(value = "delivery", required = false) Boolean delivery, @RequestParam("precioTotal") Double precioTotal, HttpSession session) {

		ModelMap model = new ModelMap();
		String viewName = "";
			Extras extras = Extras.getInstance();
			Double total;
			total = extras.total();
			Double totalFinalizado = total + precioTotal;
			model.put("delivery", delivery);
			model.put("extra", total);
			model.put("totalFinalizado", totalFinalizado);
			model.put("precioPizza", precioTotal);
			viewName = "verificacionCompraPorTarjeta";

		return new ModelAndView(viewName, model);
	}


	@RequestMapping(path = "/verificarCompraPorTarjeta", method = RequestMethod.POST)
	public ModelAndView verificarCompraPorTarjeta(@RequestParam("nroTarjeta") Integer nroTarjeta, @RequestParam("direccion") String direccion,  @RequestParam(value = "delivery", required = false) boolean delivery, HttpSession session) throws IOException, InterruptedException, ApiException {
		ModelMap model = new ModelMap();
		int id_user = Integer.parseInt(session.getAttribute("idUsuario").toString());
		Usuario usuario = servicioLogin.buscarUsuarioPorId(id_user);
		String viewName = "";

		Carrito carrito = servicioCarrito.obtenerCarritoPorIdUsuario(id_user);

		// Se agregan las pizzas a la lista del usuario
		List<Pizza> pizzas = servicioCarrito.obtenerPizzasDelCarrito(carrito);




		try {
			servicioLogin.verificarTarjetaUsuario(usuario, nroTarjeta);
			if (delivery) {
				Moto motoAsignada = servicioMoto.asignarMotoDisponible();
				if (motoAsignada != null) {
					boolean entregaExitosa = servicioDelivery.realizarEntrega(direccion);
/*
                    String informacionDeEntrega = servicioGoogleMaps.GoogleMapsAPIConfiguration(direccion);

                    model.addAttribute("informacionDeEntrega", informacionDeEntrega);*/
					model.addAttribute("entregaExitosa", entregaExitosa);
					servicioCarrito.comprarPizzasDelCarrito(pizzas, usuario);
					// Se vacia la lista del carrito
					List<Carrito_Pizza> pizzasCarrito = servicioCarrito.obtenerCarritoPizzas(carrito);
					servicioCarrito.vaciarPizzaDelCarrito(pizzasCarrito);
					viewName = "resultadoEntrega";
					return new ModelAndView (viewName,model);
				} else {
					model.addAttribute("entregaExitosa", false);
					viewName = "resultadoEntrega";
					return new ModelAndView (viewName,model);
				}
			}
			servicioCarrito.comprarPizzasDelCarrito(pizzas, usuario);
			// Se vacia la lista del carrito
			List<Carrito_Pizza> pizzasCarrito = servicioCarrito.obtenerCarritoPizzas(carrito);
			servicioCarrito.vaciarPizzaDelCarrito(pizzasCarrito);
			viewName = "compraRealizada";
		}
		catch (Exception e) {
			model.put("tarjetaIncorrecta", "El número de tarjeta ingresado es incorrecto.");
			viewName = "verificacionCompraPorTarjeta";
		}

		return new ModelAndView(viewName, model);
	}



	@RequestMapping(path ="/agregarPizzaAlCarrito", method = RequestMethod.GET)
	public ModelAndView agregarPizzaAlCarrito(@RequestParam("id_pizza") int idPizza, HttpSession session) {
		ModelMap model = new ModelMap();
		if(session.getAttribute("idUsuario")!=null)
		{
			int id_user = Integer.parseInt(session.getAttribute("idUsuario").toString());
			/*Obtengo el id de la pizza*/
			Pizza pizza_obtenida = servicioPizza.buscarPizzaPorId(idPizza);

			/*Obtengo el carrito del usuario por el id*/
			Carrito carrito = servicioCarrito.obtenerCarritoPorIdUsuario(id_user);

			/*Guardo el id de la pizza y el carrito del usuario logueado en el carrito*/
			servicioCarrito.agregarPizzaAlCarrito(pizza_obtenida, carrito);
			model.put("msj_exito", "Se agrego la pizza al carrito con exito!");
		}else {
			model.addAttribute("msj_error", "Para comprar necesitas ingresar a tu cuenta.");
		}

		return new ModelAndView("redirect:/verListaPizzas", model);
	}

	@RequestMapping(path ="/eliminarPizzaDeListaCarrito", method = RequestMethod.GET)
	public ModelAndView eliminarPizzaDeListaCarrito(@RequestParam("pizza_id") int pizza_id, HttpSession sesion) {

		int id_user = (int) sesion.getAttribute("idUsuario");
		Pizza curso = servicioPizza.buscarPizzaPorId(pizza_id);
		Carrito carrito = servicioCarrito.obtenerCarritoPorIdUsuario(id_user);
		Carrito_Pizza carritoPizza = servicioCarrito.obtenerCarritoPizza(carrito, curso);

		if (carritoPizza != null) {
			servicioCarrito.eliminarPizzaDelCarrito(carritoPizza);
		}

		return new ModelAndView("redirect:/vistaCarrito");
	}


}
