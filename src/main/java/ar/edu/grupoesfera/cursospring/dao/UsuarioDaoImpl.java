package ar.edu.grupoesfera.cursospring.dao;

import ar.edu.grupoesfera.cursospring.modelo.Pizza;
import ar.edu.grupoesfera.cursospring.modelo.Usuario;
import ar.edu.grupoesfera.cursospring.modelo.Usuario_Pizza;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Repository
public class UsuarioDaoImpl implements UsuarioDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    @Override
    public Usuario buscarUsuario(String email, String password) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Usuario> criteriaQuery = builder.createQuery(Usuario.class);
        Root<Usuario> root = criteriaQuery.from(Usuario.class);
        criteriaQuery.select(root);
        criteriaQuery.where(
                builder.equal(root.get("email"), email),
                builder.equal(root.get("password"), password));
        List<Usuario> usuarios = entityManager.createQuery(criteriaQuery).getResultList();
        return usuarios.isEmpty() ? null : usuarios.get(0);
    }

    @Transactional
    @Override
    public Usuario buscarUsuarioPorEmail(String email) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Usuario> criteriaQuery = builder.createQuery(Usuario.class);
        Root<Usuario> root = criteriaQuery.from(Usuario.class);
        criteriaQuery.select(root);
        criteriaQuery.where(builder.equal(root.get("email"), email));
        List<Usuario> usuarios = entityManager.createQuery(criteriaQuery).getResultList();
        return usuarios.isEmpty() ? null : usuarios.get(0);
    }

    @Transactional
    @Override
    public Usuario buscarPorId(int id) {
        return entityManager.find(Usuario.class, id);
    }

    @Transactional
    @Override
    public void guardarUsuario(Usuario usuario) {
        entityManager.persist(usuario);
    }

    @Transactional
    @Override
    public Usuario_Pizza obtenerUsuarioPizza(Pizza pizza, Usuario usuario) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Usuario_Pizza> criteriaQuery = builder.createQuery(Usuario_Pizza.class);
        Root<Usuario_Pizza> root = criteriaQuery.from(Usuario_Pizza.class);
        Join<Usuario_Pizza, Usuario> usuarioJoin = root.join("usuario");
        Join<Usuario_Pizza, Pizza> pizzaJoin = root.join("pizza");
        criteriaQuery.select(root);
        criteriaQuery.where(
                builder.equal(usuarioJoin, usuario),
                builder.equal(pizzaJoin, pizza));
        List<Usuario_Pizza> usuarioPizzas = entityManager.createQuery(criteriaQuery).getResultList();
        return usuarioPizzas.isEmpty() ? null : usuarioPizzas.get(0);
    }

    @Transactional
    @Override
    public void guardarPizzaDelUsuario(Usuario usuario, Pizza pizza) {
        Usuario_Pizza usuarioPizza = new Usuario_Pizza(usuario, pizza);
        usuarioPizza.setIsComprada(Boolean.TRUE);
        usuarioPizza.setFecha_incio_compra(LocalDate.now());
        usuarioPizza.setHora(LocalTime.now());
        entityManager.persist(usuarioPizza);
    }
}
