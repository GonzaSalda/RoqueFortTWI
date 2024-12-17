package ar.edu.grupoesfera.cursospring.dao;

import ar.edu.grupoesfera.cursospring.modelo.Pizza;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.*;
import java.util.List;

@Repository
public class PizzaDaoImpl implements PizzaDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    @Override
    public List<Pizza> obtenerListaTotalPizzas() {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Pizza> criteriaQuery = builder.createQuery(Pizza.class);
        Root<Pizza> root = criteriaQuery.from(Pizza.class);
        criteriaQuery.select(root);
        return entityManager.createQuery(criteriaQuery).getResultList();
    }

    @Transactional
    @Override
    public Pizza obtenerPizzaPorID(int id) {
        return entityManager.find(Pizza.class, id);
    }

    @Transactional
    @Override
    public void actualizarPizza(Pizza pizza) {
        entityManager.merge(pizza);
    }

    @Transactional
    @Override
    public void guardarPizza(Pizza pizza) {
        entityManager.persist(pizza);
    }
}
