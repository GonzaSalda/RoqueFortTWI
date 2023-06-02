package ar.edu.grupoesfera.cursospring.dao;

import ar.edu.grupoesfera.cursospring.modelo.Pizza;
import ar.edu.grupoesfera.cursospring.modelo.Usuario;
import ar.edu.grupoesfera.cursospring.modelo.Usuario_Pizza;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import java.time.LocalDate;
import java.util.ArrayList;

@Repository
public class UsuarioPizzaDaoImpl implements UsuarioPizzaDao{
    @Autowired
    private SessionFactory sessionFactory;
    @Transactional
    @Override
    public ArrayList<Usuario_Pizza> obtenerPizzasCompradasPorUsuario(Long usuarioId) {
        final Session sesion = sessionFactory.getCurrentSession();
        CriteriaBuilder builder = sesion.getCriteriaBuilder();

        CriteriaQuery<Usuario_Pizza> query = builder.createQuery(Usuario_Pizza.class);
        Root<Usuario_Pizza> rootUsuarioPizza = query.from(Usuario_Pizza.class);
        Join<Usuario_Pizza, Pizza> joinPizza = rootUsuarioPizza.join("pizza");
        Join<Usuario_Pizza, Usuario> joinUsuario = rootUsuarioPizza.join("usuario");

        query.select(rootUsuarioPizza);
        query.where(
                builder.and(
                        builder.equal(joinUsuario.get("id"), usuarioId),
                        builder.equal(rootUsuarioPizza.get("isComprada"), true)
                )
        );

        ArrayList<Usuario_Pizza> pizzasCompradas = (ArrayList<Usuario_Pizza>) sesion.createQuery(query)
                .getResultList();

        return pizzasCompradas;
    }
    @Transactional
    @Override
    public ArrayList<Usuario_Pizza> obtenerPizzasCompradasPorUsuario24hs(Long id) {

        LocalDate fechaActual = LocalDate.now();
        LocalDate fechaInicial = fechaActual.minusDays(id);

        final Session sesion = sessionFactory.getCurrentSession();
        CriteriaBuilder builder = sesion.getCriteriaBuilder();

        CriteriaQuery<Usuario_Pizza> query = builder.createQuery(Usuario_Pizza.class);
        Root<Usuario_Pizza> rootUsuarioPizza = query.from(Usuario_Pizza.class);
        Join<Usuario_Pizza, Pizza> joinPizza = rootUsuarioPizza.join("pizza");
        Join<Usuario_Pizza, Usuario> joinUsuario = rootUsuarioPizza.join("usuario");

        query.select(rootUsuarioPizza);
        query.where(
                builder.and(
                        builder.equal(joinUsuario.get("id"), id),
                        builder.equal(rootUsuarioPizza.get("isComprada"), true),
                        builder.between(rootUsuarioPizza.get("fecha_incio_compra"), fechaInicial, fechaActual)
                )
        );

        ArrayList<Usuario_Pizza> pizzasCompradas = (ArrayList<Usuario_Pizza>) sesion.createQuery(query).getResultList();
        return pizzasCompradas;
    }
    @Transactional
    @Override
    public ArrayList<Usuario_Pizza> obtenerPizzasCompradasPorUsuario48hs(Long id) {

        LocalDate fechaActual = LocalDate.now();
        LocalDate fechaInicial = fechaActual.minusDays(2);

        final Session sesion = sessionFactory.getCurrentSession();
        CriteriaBuilder builder = sesion.getCriteriaBuilder();

        CriteriaQuery<Usuario_Pizza> query = builder.createQuery(Usuario_Pizza.class);
        Root<Usuario_Pizza> rootUsuarioPizza = query.from(Usuario_Pizza.class);
        Join<Usuario_Pizza, Pizza> joinPizza = rootUsuarioPizza.join("pizza");
        Join<Usuario_Pizza, Usuario> joinUsuario = rootUsuarioPizza.join("usuario");

        query.select(rootUsuarioPizza);
        query.where(
                builder.and(
                        builder.equal(joinUsuario.get("id"), id),
                        builder.equal(rootUsuarioPizza.get("isComprada"), true),
                        builder.between(rootUsuarioPizza.get("fecha_incio_compra"), fechaInicial, fechaActual)
                )
        );

        ArrayList<Usuario_Pizza> pizzasCompradas = (ArrayList<Usuario_Pizza>) sesion.createQuery(query).getResultList();
        return pizzasCompradas;
    }

    @Transactional
    @Override
    public ArrayList<Usuario_Pizza> obtenerPizzasCompradasPorRangoDeFecha(Long id, LocalDate fechaInicial, LocalDate fechaFinal) {
        final Session sesion = sessionFactory.getCurrentSession();
        CriteriaBuilder builder = sesion.getCriteriaBuilder();

        CriteriaQuery<Usuario_Pizza> query = builder.createQuery(Usuario_Pizza.class);
        Root<Usuario_Pizza> rootUsuarioPizza = query.from(Usuario_Pizza.class);
        Join<Usuario_Pizza, Pizza> joinPizza = rootUsuarioPizza.join("pizza");
        Join<Usuario_Pizza, Usuario> joinUsuario = rootUsuarioPizza.join("usuario");

        query.select(rootUsuarioPizza);
        query.where(
                builder.and(
                        builder.equal(joinUsuario.get("id"), id),
                        builder.equal(rootUsuarioPizza.get("isComprada"), true),
                        builder.between(rootUsuarioPizza.get("fecha_incio_compra"), fechaInicial, fechaFinal)
                )
        );

        ArrayList<Usuario_Pizza> pizzasCompradas = (ArrayList<Usuario_Pizza>) sesion.createQuery(query).getResultList();
        return pizzasCompradas;
    }
}
