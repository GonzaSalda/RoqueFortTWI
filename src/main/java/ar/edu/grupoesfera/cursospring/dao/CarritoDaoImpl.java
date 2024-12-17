package ar.edu.grupoesfera.cursospring.dao;

import ar.edu.grupoesfera.cursospring.modelo.Carrito;
import ar.edu.grupoesfera.cursospring.modelo.Carrito_Pizza;
import ar.edu.grupoesfera.cursospring.modelo.Pizza;
import ar.edu.grupoesfera.cursospring.modelo.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

@Repository("carritoDao")
@Transactional
public class CarritoDaoImpl implements CarritoDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private UsuarioDao usuarioDao;

    @Override
    public Carrito buscarCarritoPorID(int id_carrito) {
        return entityManager.find(Carrito.class, id_carrito);
    }

    @Override
    public void agregarPizzaALista(Pizza pizza_obtenida, Carrito carrito) {
        Carrito_Pizza carritoPizza = new Carrito_Pizza();
        carritoPizza.setCarrito(carrito);
        carritoPizza.setPizza(pizza_obtenida);
        entityManager.persist(carritoPizza);
    }

    @Override
    public Carrito obtenerCarritoPorIdUsuario(int id_user) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Carrito> cq = cb.createQuery(Carrito.class);
        Root<Carrito> root = cq.from(Carrito.class);
        cq.select(root).where(cb.equal(root.get("usuario").get("id"), id_user));
        
        List<Carrito> carritos = entityManager.createQuery(cq).getResultList();
        Carrito carrito = null;

        if (carritos.isEmpty()) {
            Usuario usuario = usuarioDao.buscarPorId(id_user); // Suponiendo que existe un método en el usuarioDao para obtener un usuario por su ID
            carrito = new Carrito();
            carrito.setUsuario(usuario);
            entityManager.persist(carrito);
        } else {
            carrito = carritos.get(0);
        }
        return carrito;
    }

    @Override
    public void guardarCarrito(Carrito carrito) {
        if (carrito.getId() == 0) {
            entityManager.persist(carrito); // Crear nuevo carrito si no tiene ID
        } else {
            entityManager.merge(carrito); // Actualizar carrito si ya tiene ID
        }
    }

    @Override
    public List<Pizza> obtenerPizzasDelCarrito(Carrito carrito) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Pizza> cq = cb.createQuery(Pizza.class);
        Root<Carrito_Pizza> root = cq.from(Carrito_Pizza.class);
        cq.select(root.get("pizza")).where(cb.equal(root.get("carrito"), carrito));

        List<Pizza> pizzas = entityManager.createQuery(cq).getResultList();
        return pizzas;
    }

    @Override
    public Carrito_Pizza obtenerCarritoPizza(Carrito carrito, Pizza pizza) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Carrito_Pizza> cq = cb.createQuery(Carrito_Pizza.class);
        Root<Carrito_Pizza> root = cq.from(Carrito_Pizza.class);
        cq.select(root).where(cb.equal(root.get("carrito"), carrito), cb.equal(root.get("pizza"), pizza));
        
        List<Carrito_Pizza> resultList = entityManager.createQuery(cq).getResultList();
        return resultList.isEmpty() ? null : resultList.get(0);
    }

    @Override
    public void eliminarPizzaDelCarrito(Carrito_Pizza carritoPizza) {
        entityManager.remove(entityManager.contains(carritoPizza) ? carritoPizza : entityManager.merge(carritoPizza));
    }

    @Override
    public List<Carrito_Pizza> obtenerCarritoPizzas(Carrito carrito) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Carrito_Pizza> cq = cb.createQuery(Carrito_Pizza.class);
        Root<Carrito_Pizza> root = cq.from(Carrito_Pizza.class);
        cq.select(root).where(cb.equal(root.get("carrito"), carrito));

        List<Carrito_Pizza> pizzasCarrito = entityManager.createQuery(cq).getResultList();
        return pizzasCarrito;
    }
}
