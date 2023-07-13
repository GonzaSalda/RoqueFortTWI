package ar.edu.grupoesfera.cursospring.dao;

import ar.edu.grupoesfera.cursospring.modelo.CompraCola;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
@Transactional
public class CompraColaDaoImpl implements CompraColaDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<CompraCola> obtenerPedidosEnCola() {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<CompraCola> criteriaQuery = builder.createQuery(CompraCola.class);
        Root<CompraCola> root = criteriaQuery.from(CompraCola.class);
        criteriaQuery.select(root);
        return session.createQuery(criteriaQuery).getResultList();
    }

    @Override
    public CompraCola obtenerUltimaCompraCola() {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<CompraCola> criteriaQuery = builder.createQuery(CompraCola.class);
        Root<CompraCola> root = criteriaQuery.from(CompraCola.class);
        criteriaQuery.select(root);
        criteriaQuery.orderBy(builder.desc(root.get("id")));
        List<CompraCola> compras = session.createQuery(criteriaQuery).setMaxResults(1).getResultList();
        if (!compras.isEmpty()) {
            return compras.get(0);
        }
        return null;
    }

    @Override
    public void agregarCompraCola(CompraCola compra) {
        Session session = sessionFactory.getCurrentSession();
        session.save(compra);
    }

    @Override
    public void eliminarCompraCola(CompraCola compra) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(compra);
    }

}
