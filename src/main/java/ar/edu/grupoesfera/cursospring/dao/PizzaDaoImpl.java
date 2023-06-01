package ar.edu.grupoesfera.cursospring.dao;

import ar.edu.grupoesfera.cursospring.modelo.Pizza;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Repository
public class PizzaDaoImpl implements PizzaDao {


    @Autowired
    private SessionFactory sessionFactory;

    @Transactional
    @Override
    public List<Pizza> obtenerListaTotalPizzas() {
        Session sesion = sessionFactory.getCurrentSession();
        List<Pizza> lista_pizzas = sesion.createCriteria(Pizza.class).list();
        return lista_pizzas;
    }
    @Transactional
    @Override
    public Pizza obtenerPizzaPorID(int id) {
        Session sesion = sessionFactory.getCurrentSession();
        Pizza pizza = sesion.get(Pizza.class, id);
        return pizza;
    }
    @Transactional
    @Override
    public void actualizarPizza(Pizza pizza) {
        sessionFactory.getCurrentSession().update(pizza);
    }


}
