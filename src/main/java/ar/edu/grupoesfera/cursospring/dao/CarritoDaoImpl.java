package ar.edu.grupoesfera.cursospring.dao;

import ar.edu.grupoesfera.cursospring.modelo.Carrito;
import ar.edu.grupoesfera.cursospring.modelo.Carrito_Pizza;
import ar.edu.grupoesfera.cursospring.modelo.Pizza;
import ar.edu.grupoesfera.cursospring.modelo.Usuario;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Repository("carritoDao")
@Transactional
public class CarritoDaoImpl implements CarritoDao {
	
	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private UsuarioDao usuarioDao;



	@Override
	public Carrito buscarCarritoPorID(int id_carrito) {

		Session sesion = sessionFactory.getCurrentSession();
				
		Carrito carrito = sesion.get(Carrito.class, id_carrito);
		
		return carrito;
	}


	@Override
	public void agregarPizzaALista(Pizza pizza_obtenida, Carrito carrito) {

        Session sesion = sessionFactory.getCurrentSession();

        Carrito_Pizza carritoPizza = new Carrito_Pizza();

		carritoPizza.setCarrito(carrito);
		carritoPizza.setPizza(pizza_obtenida);
		sesion.save(carritoPizza);
	}


	@Override
	public Carrito obtenerCarritoPorIdUsuario(int id_user) {
		Session sesion = sessionFactory.getCurrentSession();
		List<Carrito> carritos = sesion.createCriteria(Carrito.class).list();
		Carrito carrito = null;
		for (Carrito car : carritos) {
			if (car.getUsuario().getId() == id_user) {
				carrito = car;
				break;
			}
		}
		if (carrito == null) {
			// Si no se encontró un carrito para el usuario, crear uno nuevo
			Usuario usuario = usuarioDao.buscarPorId(id_user); // Suponiendo que existe un método en el usuarioDao para obtener un usuario por su ID
			carrito = new Carrito();
			carrito.setUsuario(usuario);
			sessionFactory.getCurrentSession().save(carrito);
		}
		return carrito;
	}



	@Override
	public void guardarCarrito(Carrito carrito) {
		sessionFactory.getCurrentSession().save(carrito);
	}


	@Override
	public List<Pizza> obtenerPizzasDelCarrito(Carrito carrito) {
		Session sesion = sessionFactory.getCurrentSession();
		List<Carrito_Pizza> carrito_pizzas = sesion.createCriteria(Carrito_Pizza.class)
				.add(Restrictions.eq("carrito", carrito))
				.list();
		List<Pizza> pizzas = new ArrayList<Pizza>();

		for (Carrito_Pizza carritoPizza : carrito_pizzas) {
			pizzas.add(carritoPizza.getPizza());
		}
		return pizzas;
	}

	@Override
	public Carrito_Pizza obtenerCarritoPizza(Carrito carrito, Pizza pizza) {
		
		Session sesion = sessionFactory.getCurrentSession();

		Carrito_Pizza carritoPizza = (Carrito_Pizza) sesion.createCriteria(Carrito_Pizza.class)
				.add(Restrictions.eq("carrito", carrito))
				.add(Restrictions.eq("pizza", pizza))
				.uniqueResult();
		return carritoPizza;
	}

	@Override
	public void eliminarPizzaDelCarrito(Carrito_Pizza carritoPizza) {
		sessionFactory.getCurrentSession().delete(carritoPizza);
	}



	@Override
	public List<Carrito_Pizza> obtenerCarritoPizzas(Carrito carrito) {
		
		Session sesion = sessionFactory.getCurrentSession();

		List<Carrito_Pizza> pizzasCarrito = sesion.createCriteria(Carrito_Pizza.class)
											.add(Restrictions.eq("carrito", carrito))
											.list();
		
		return pizzasCarrito;
	}


}
