package ar.edu.grupoesfera.cursospring.dao;

import ar.edu.grupoesfera.cursospring.modelo.Pizza;
import ar.edu.grupoesfera.cursospring.modelo.Usuario;
import ar.edu.grupoesfera.cursospring.modelo.Usuario_Pizza;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;


@Repository
public class UsuarioDaoImpl implements UsuarioDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Transactional
    @Override
    public Usuario buscarUsuario(String email, String password) {
        Session session = sessionFactory.getCurrentSession();

        Usuario usuario = (Usuario) session.createCriteria(Usuario.class)
                .add(Restrictions.eq("email", email))
                .add(Restrictions.eq("password", password))
                .uniqueResult();

        return usuario;
    }

    @Transactional
    @Override
    public Usuario buscarUsuarioPorEmail(String email) {

        Session sesion = sessionFactory.getCurrentSession();
        Usuario usuario = (Usuario) sesion.createCriteria(Usuario.class)
                .add(Restrictions.eq("email", email))
                .uniqueResult();

        return usuario;
    }

    @Transactional
    @Override
    public Usuario buscarPorId(int id) {
        final Session session = sessionFactory.getCurrentSession();
        return (Usuario) session.createCriteria(Usuario.class)
                .add(Restrictions.eq("id", id))
                .uniqueResult();
    }

    @Transactional
    @Override
    public void guardarUsuario(Usuario usuario) {
        sessionFactory.getCurrentSession().save(usuario);
    }

    @Transactional
    @Override
    public Usuario_Pizza obtenerUsuarioPizza(Pizza Pizza_obtenida, Usuario usuario) {
        Session sesion = sessionFactory.getCurrentSession();
        Usuario_Pizza usuarioPizza = (Usuario_Pizza) sesion.createCriteria(Usuario_Pizza.class)
                .add(Restrictions.eq("usuario", usuario))
                .add(Restrictions.eq("pizza", Pizza_obtenida))
                .uniqueResult();
        return usuarioPizza;
    }

    @Transactional
    @Override
    public void guardarPizzaDelUsuario( Usuario usuario, Pizza pizza_obtenida) {
        Session sesion = sessionFactory.getCurrentSession();
        Usuario_Pizza usuarioPizza = new Usuario_Pizza(usuario, pizza_obtenida);
        usuarioPizza.setFecha_incio_compra(LocalDate.now());
        usuarioPizza.setHora(LocalTime.now());
        sesion.save(usuarioPizza);
    }




}
