package ar.edu.grupoesfera.cursospring.dao;

import ar.edu.grupoesfera.cursospring.modelo.Extra;
import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class ExtraDaoImpl implements ExtraDao {

    @PersistenceContext
    private EntityManager entityManager;

    // Obtener todos los extras
    public List<Extra> obtenerTodos() {
        return entityManager.createQuery("FROM Extra", Extra.class).getResultList();
    }

    // Buscar por ID
    public Extra buscarPorId(Long id) {
        return entityManager.find(Extra.class, id);
    }

    // Guardar un nuevo extra
    public void guardar(Extra extra) {
        entityManager.persist(extra);
    }

    // Actualizar un extra existente
    public void actualizar(Extra extra) {
        entityManager.merge(extra);
    }

    // Eliminar por ID
    public void eliminar(Long id) {
        Extra extra = buscarPorId(id);
        if (extra != null) {
            entityManager.remove(extra);
        }
    }
}
