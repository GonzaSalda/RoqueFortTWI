package ar.edu.grupoesfera.cursospring.servicios;

import ar.edu.grupoesfera.cursospring.dao.CarritoDao;
import ar.edu.grupoesfera.cursospring.dao.UsuarioDao;
import ar.edu.grupoesfera.cursospring.modelo.Carrito;
import ar.edu.grupoesfera.cursospring.modelo.Carrito_Pizza;
import ar.edu.grupoesfera.cursospring.modelo.Extra;
import ar.edu.grupoesfera.cursospring.modelo.Pizza;
import ar.edu.grupoesfera.cursospring.modelo.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Service
@Transactional
public class ServicioCarritoImpl implements ServicioCarrito {
	@Autowired
	private CarritoDao carritoDao;
	@Autowired
	private UsuarioDao usuarioDao;

	@Override
	public Carrito buscarCarritoPorId(int id_carrito) {
		return carritoDao.buscarCarritoPorID(id_carrito);
	}


	@Override
	public double getTotalDePrecios(List<Pizza> pizzas) {
		
		double resultadoTotal = 0;
		
		for (Pizza pizza : pizzas) {
			resultadoTotal += pizza.getPrecio();
		}
		
		return resultadoTotal;
	}


	@Override
	public void agregarPizzaAlCarrito(Pizza pizza_obtenida, Carrito carrito) {
		carritoDao.agregarPizzaALista(pizza_obtenida, carrito);
	}

	@Override
	public void agregarExtraAlCarrito(Extra extra_obtenido, Carrito carrito) {
		carritoDao.agregarExtraALista(extra_obtenido, carrito);
	}


	@Override
	public Carrito obtenerCarritoPorIdUsuario(int id_user) {
		return carritoDao.obtenerCarritoPorIdUsuario(id_user);
	}


	@Override
	public List<Pizza> obtenerPizzasDelCarrito(Carrito carrito) {
		List<Pizza> pizzasCarrito = carritoDao.obtenerPizzasDelCarrito(carrito);
		if (!pizzasCarrito.isEmpty()) {
			return pizzasCarrito;
		}
		else {
			throw new ListaCarritoException();
		}
	}

	@Override
	public List<Extra> obtenerExtrasDelCarrito(Carrito carrito) {
		List<Extra> extrasCarrito = carritoDao.obtenerExtrasDelCarrito(carrito);
		if (!extrasCarrito.isEmpty()) {
			return extrasCarrito;
		}
		else {
			throw new ListaCarritoException();
		}
	}


	@Override
	public void eliminarPizzaDelCarrito(Carrito_Pizza carritoPizza) {
		carritoDao.eliminarPizzaDelCarrito(carritoPizza);
	}


	@Override
	public Carrito_Pizza obtenerCarritoPizza(Carrito carrito, Pizza pizza) {
		return carritoDao.obtenerCarritoPizza(carrito, pizza);
	}


	/*Agarro las pizzas del carrito y las guardo en la base de datos*/
	@Override
	public void comprarPizzasDelCarrito(List<Pizza> pizzas, Usuario usuario) {

		for (Pizza pizza : pizzas) {
				usuarioDao.guardarPizzaDelUsuario(usuario, pizza);
		}
	}


	@Override
	public List<Carrito_Pizza> obtenerCarritoPizzas(Carrito carrito) {
		return carritoDao.obtenerCarritoPizzas(carrito);
	}


	@Override
	public void vaciarCarrito(List<Carrito_Pizza> carritoPizzas) {
		
		for (Carrito_Pizza carritoPizza : carritoPizzas) {
			carritoDao.eliminarPizzaDelCarrito(carritoPizza);
		}
	}


}
