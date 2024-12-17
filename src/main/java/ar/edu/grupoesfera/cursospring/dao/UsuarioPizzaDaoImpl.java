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
import java.util.List;

@Repository
public class UsuarioPizzaDaoImpl implements UsuarioPizzaDao {
    @PersistenceContext
    private EntityManager entityManager;

    private CriteriaQuery<Usuario_Pizza> crearConsultaPizzasPorUsuario(Long id, LocalDate fechaInicial, LocalDate fechaFinal) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Usuario_Pizza> query = builder.createQuery(Usuario_Pizza.class);
        Root<Usuario_Pizza> rootUsuarioPizza = query.from(Usuario_Pizza.class);
        Join<Usuario_Pizza, Pizza> joinPizza = rootUsuarioPizza.join("pizza");
        Join<Usuario_Pizza, Usuario> joinUsuario = rootUsuarioPizza.join("usuario");

        query.select(rootUsuarioPizza);
        Predicate condiciones = builder.and(
                builder.equal(joinUsuario.get("id"), id),
                builder.equal(rootUsuarioPizza.get("isComprada"), true)
        );

        if (fechaInicial != null && fechaFinal != null) {
            condiciones = builder.and(condiciones, builder.between(rootUsuarioPizza.get("fecha_inicio_compra"), fechaInicial, fechaFinal));
        }
        
        query.where(condiciones);
        return query;
    }

    @Transactional
    @Override
    public List<Usuario_Pizza> obtenerPizzasCompradasPorUsuario(Integer usuarioId) {
        CriteriaQuery<Usuario_Pizza> query = crearConsultaPizzasPorUsuario(Long.valueOf(usuarioId), null, null);
        return entityManager.createQuery(query).getResultList();
    }

    @Transactional
    @Override
    public List<Usuario_Pizza> obtenerPizzasCompradasPorUsuario24hs(Long id) {
        LocalDate fechaActual = LocalDate.now();
        LocalDate fechaInicial = fechaActual.minusDays(1);

        CriteriaQuery<Usuario_Pizza> query = crearConsultaPizzasPorUsuario(id, fechaInicial, fechaActual);
        return entityManager.createQuery(query).getResultList();
    }

    @Transactional
    @Override
    public List<Usuario_Pizza> obtenerPizzasCompradasPorUsuario48hs(Long id) {
        LocalDate fechaActual = LocalDate.now();
        LocalDate fechaInicial = fechaActual.minusDays(2);

        CriteriaQuery<Usuario_Pizza> query = crearConsultaPizzasPorUsuario(id, fechaInicial, fechaActual);
        return entityManager.createQuery(query).getResultList();
    }

    @Transactional
    @Override
    public List<Usuario_Pizza> obtenerPizzasCompradasPorRangoDeFecha(Long id, LocalDate fechaInicial, LocalDate fechaFinal) {
        CriteriaQuery<Usuario_Pizza> query = crearConsultaPizzasPorUsuario(id, fechaInicial, fechaFinal);
        return entityManager.createQuery(query).getResultList();
    }
}
