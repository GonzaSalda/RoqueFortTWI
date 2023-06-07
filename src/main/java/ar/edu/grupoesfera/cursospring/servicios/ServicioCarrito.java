package ar.edu.grupoesfera.cursospring.servicios;

import ar.edu.grupoesfera.cursospring.modelo.Carrito;
import ar.edu.grupoesfera.cursospring.modelo.Carrito_Pizza;
import ar.edu.grupoesfera.cursospring.modelo.Pizza;
import ar.edu.grupoesfera.cursospring.modelo.Usuario;
import java.util.List;

public interface ServicioCarrito {

    Carrito buscarCarritoPorId(int id_carrito);

    double getTotalDePrecios(List<Pizza> pizzas);

	void agregarPizzaAlCarrito(Pizza pizza_obtenida, Carrito carrito);

	Carrito obtenerCarritoPorIdUsuario(int id_user);

	List<Pizza> obtenerPizzasDelCarrito(Carrito carrito);

	Carrito_Pizza obtenerCarritoPizza(Carrito carrito, Pizza pizza);

	void comprarPizzasDelCarrito(List<Pizza> pizzas, Usuario usuario);

	List<Carrito_Pizza> obtenerCarritoPizzas(Carrito carrito);
    
	void eliminarPizzaDelCarrito(Carrito_Pizza carritoPizza);

	void vaciarPizzaDelCarrito(List<Carrito_Pizza> pizzasCarrito);


}
