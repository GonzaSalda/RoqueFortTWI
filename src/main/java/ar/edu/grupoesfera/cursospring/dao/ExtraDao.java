package ar.edu.grupoesfera.cursospring.dao;

import java.util.List;

import ar.edu.grupoesfera.cursospring.modelo.Extra;

public interface ExtraDao {

    public List<Extra> obtenerTodos();

    public Extra buscarPorId(Long id);

    public void guardar(Extra extra);

    public void actualizar(Extra extra);

    public void eliminar(Long id);


}
