package ar.edu.grupoesfera.cursospring.dao;

import ar.edu.grupoesfera.cursospring.modelo.Carrito;
import ar.edu.grupoesfera.cursospring.modelo.Carrito_Pizza;
import ar.edu.grupoesfera.cursospring.modelo.Extra;
import ar.edu.grupoesfera.cursospring.modelo.Pizza;

import java.util.List;

public interface CarritoDao {

	Carrito buscarCarritoPorID(int id_carrito);

	void agregarPizzaALista(Pizza pizza_obtenida, Carrito carrito);

	void agregarExtraALista(Extra extra_obtenido, Carrito carrito);

	Carrito obtenerCarritoPorIdUsuario(int id_user);

	void guardarCarrito(Carrito carrito);

	List<Pizza> obtenerPizzasDelCarrito(Carrito carrito);

	List<Extra> obtenerExtrasDelCarrito(Carrito carrito);

	Carrito_Pizza obtenerCarritoPizza(Carrito carrito, Pizza pizza);

	void eliminarPizzaDelCarrito(Carrito_Pizza carritoPizza);

	List<Carrito_Pizza> obtenerCarritoPizzas(Carrito carrito);

}
