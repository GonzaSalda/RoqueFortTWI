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

@Repository
public class CarritoDaoImpl implements CarritoDao {
	
	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private UsuarioDao usuarioDao;


	@Transactional
	@Override
	public Carrito buscarCarritoPorID(int id_carrito) {

		Session sesion = sessionFactory.getCurrentSession();
				
		Carrito carrito = sesion.get(Carrito.class, id_carrito);
		
		return carrito;
	}


	@Transactional
	@Override
	public void agregarPizzaALista(Pizza pizza_obtenida, Carrito carrito) {

        Session sesion = sessionFactory.getCurrentSession();

        Carrito_Pizza carritoPizza = new Carrito_Pizza();

		carritoPizza.setCarrito(carrito);
		carritoPizza.setPizza(pizza_obtenida);
		sesion.saveOrUpdate(carrito);
		sesion.save(carritoPizza);
	}


	@Transactional
	@Override
	public Carrito obtenerCarritoPorIdUsuario(int id_user) {
		Session sesion = sessionFactory.getCurrentSession();

		Criteria criteria = sesion.createCriteria(Carrito.class);
		criteria.add(Restrictions.eq("usuario.id", id_user));
		criteria.setMaxResults(1); // Limitar los resultados a uno
		Carrito carrito = (Carrito) criteria.uniqueResult();
		if (carrito == null) {
			System.out.println("No se encontró el carrito para el usuario con ID: " + id_user);
		}

		return carrito;
	}



	@Transactional
	@Override
	public void guardarCarrito(Carrito carrito) {
		sessionFactory.getCurrentSession().save(carrito);
	}


	@Transactional
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

	@Transactional
	@Override
	public Carrito_Pizza obtenerCarritoPizza(Carrito carrito, Pizza pizza) {
		
		Session sesion = sessionFactory.getCurrentSession();

		Criteria criteria = sesion.createCriteria(Carrito_Pizza.class);
		criteria.add(Restrictions.eq("carrito", carrito));
		criteria.add(Restrictions.eq("pizza", pizza));
		criteria.setMaxResults(1); // Limitar el resultado a 1

		return (Carrito_Pizza) criteria.uniqueResult();
	}

	@Transactional
	@Override
	public void eliminarPizzaDelCarrito(Carrito_Pizza carritoPizza) {
		sessionFactory.getCurrentSession().delete(carritoPizza);
	}


	@Transactional
	@Override
	public List<Carrito_Pizza> obtenerCarritoPizzas(Carrito carrito) {
		
		Session sesion = sessionFactory.getCurrentSession();

		List<Carrito_Pizza> pizzasCarrito = sesion.createCriteria(Carrito_Pizza.class)
											.add(Restrictions.eq("carrito", carrito))
											.list();
		
		return pizzasCarrito;
	}


}
