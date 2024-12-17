package ar.edu.grupoesfera.cursospring.dao;

import ar.edu.grupoesfera.cursospring.modelo.Moto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.*;
import java.util.List;

@Repository
@Transactional
public class MotoDaoImpl implements MotoDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Moto> obtenerListaTotalMoto() {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Moto> criteriaQuery = builder.createQuery(Moto.class);
        Root<Moto> root = criteriaQuery.from(Moto.class);
        criteriaQuery.select(root);
        return entityManager.createQuery(criteriaQuery).getResultList();
    }

    @Override
    public Moto obtenerMotoPorID(Long id) {
        return entityManager.find(Moto.class, id);
    }

    @Override
    public void actualizarMoto(Moto moto) {
        entityManager.merge(moto);
    }

    @Override
    public Moto findMotoDisponible() {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Moto> criteriaQuery = builder.createQuery(Moto.class);
        Root<Moto> root = criteriaQuery.from(Moto.class);
        criteriaQuery.select(root).where(builder.isTrue(root.get("disponible")));
        List<Moto> motos = entityManager.createQuery(criteriaQuery).getResultList();
        return motos.isEmpty() ? null : motos.get(0);
    }

    @Override
    public List<Moto> findAll() {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Moto> criteriaQuery = builder.createQuery(Moto.class);
        Root<Moto> root = criteriaQuery.from(Moto.class);
        criteriaQuery.select(root);
        return entityManager.createQuery(criteriaQuery).getResultList();
    }
}
