package ar.edu.grupoesfera.cursospring.dao;

import ar.edu.grupoesfera.cursospring.modelo.Pizza;
import ar.edu.grupoesfera.cursospring.modelo.Usuario_Pizza;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;

public interface UsuarioPizzaDao {
    ArrayList<Usuario_Pizza> obtenerPizzasCompradasPorUsuario(Integer usuarioId);

    ArrayList<Usuario_Pizza> obtenerPizzasCompradasPorUsuario24hs(Long id);

    ArrayList<Usuario_Pizza> obtenerPizzasCompradasPorUsuario48hs(Long id);

    ArrayList<Usuario_Pizza> obtenerPizzasCompradasPorRangoDeFecha(Long id, LocalDate fechaInicial, LocalDate fechaFinal);
}
