package ar.edu.grupoesfera.cursospring.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.grupoesfera.cursospring.dao.ExtraDao;
import ar.edu.grupoesfera.cursospring.modelo.Extra;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class ServicioExtraImpl implements ServicioExtra {
    @Autowired
    private ExtraDao extraDao;

    public List<Extra> listarTodos() {
        return extraDao.obtenerTodos();
    }

    public Extra buscarPorId(Long id) {
        return extraDao.buscarPorId(id);
    }

    public void agregarExtra(Extra extra) {
        extraDao.guardar(extra);
    }

    public void agregarStock(Long id, int cantidad) {
        Extra extra = extraDao.buscarPorId(id); // Buscar el extra por su ID
        if (extra.getStock() == null) {
            extra.setStock(cantidad);

        } else {
            extra.setStock(extra.getStock() + cantidad);
            extraDao.actualizar(extra);
        }
    }

    public void eliminarExtra(Long id) {
        extraDao.eliminar(id);
    }

}
