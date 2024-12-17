package ar.edu.grupoesfera.cursospring.dao;

import ar.edu.grupoesfera.cursospring.modelo.CompraCola;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.*;
import java.util.List;

@Repository
@Transactional
public class CompraColaDaoImpl implements CompraColaDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<CompraCola> obtenerPedidosEnCola() {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<CompraCola> criteriaQuery = builder.createQuery(CompraCola.class);
        Root<CompraCola> root = criteriaQuery.from(CompraCola.class);
        criteriaQuery.select(root);
        return entityManager.createQuery(criteriaQuery).getResultList();
    }

    @Override
    public CompraCola obtenerUltimaCompraCola() {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<CompraCola> criteriaQuery = builder.createQuery(CompraCola.class);
        Root<CompraCola> root = criteriaQuery.from(CompraCola.class);
        criteriaQuery.select(root);
        criteriaQuery.orderBy(builder.desc(root.get("id")));
        List<CompraCola> compras = entityManager.createQuery(criteriaQuery).setMaxResults(1).getResultList();
        if (!compras.isEmpty()) {
            return compras.get(0);
        }
        return null;
    }

    @Override
    public void agregarCompraCola(CompraCola compra) {
        entityManager.persist(compra);
    }

    @Override
    public void eliminarCompraCola(CompraCola compra) {
        entityManager.remove(entityManager.contains(compra) ? compra : entityManager.merge(compra));
    }

}
