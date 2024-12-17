package ar.edu.grupoesfera.cursospring.servicios;

import java.util.List;

import ar.edu.grupoesfera.cursospring.modelo.Extra;

public interface ServicioExtra {
    public List<Extra> listarTodos();

    public Extra buscarPorId(Long id);

    public void agregarExtra(Extra extra);

    public void agregarStock(Long id, int cantidad);

    public void eliminarExtra(Long id);
}
