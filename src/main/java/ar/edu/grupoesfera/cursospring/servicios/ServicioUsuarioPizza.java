package ar.edu.grupoesfera.cursospring.servicios;

import ar.edu.grupoesfera.cursospring.modelo.Pizza;
import ar.edu.grupoesfera.cursospring.modelo.Usuario_Pizza;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public interface ServicioUsuarioPizza {

    List<Usuario_Pizza> buscarHistorialPorIdUsuario(Integer id);

    List<Usuario_Pizza> buscarHistorialPorIdUserltimas24hs(Long id);

    List<Usuario_Pizza> buscarHistorialPorIdUserltimas48hs(Long id);

    List<Usuario_Pizza> buscarHistorialPorIdRangoFecha(Long id, LocalDate fechaInicial, LocalDate fechaFinal);
}

