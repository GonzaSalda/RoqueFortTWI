package ar.edu.grupoesfera.cursospring.servicios;

import ar.edu.grupoesfera.cursospring.dao.UsuarioPizzaDao;
import ar.edu.grupoesfera.cursospring.modelo.Pizza;
import ar.edu.grupoesfera.cursospring.modelo.Usuario_Pizza;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class ServicioUsuarioPizzaImpl implements ServicioUsuarioPizza{
    @Inject
    private UsuarioPizzaDao usuarioPizzaDao;

    @Override
    public List<Usuario_Pizza> buscarHistorialPorIdUsuario(Long id) {
            return usuarioPizzaDao.obtenerPizzasCompradasPorUsuario(id);
    }

    @Override
    public ArrayList<Usuario_Pizza> buscarHistorialPorIdUserltimas24hs(Long id) {
        return usuarioPizzaDao.obtenerPizzasCompradasPorUsuario24hs(id);
    }

    @Override
    public ArrayList<Usuario_Pizza> buscarHistorialPorIdUserltimas48hs(Long id) {
        return usuarioPizzaDao.obtenerPizzasCompradasPorUsuario48hs(Long.valueOf(id));
    }

    @Override
    public ArrayList<Usuario_Pizza> buscarHistorialPorIdRangoFecha(Long id, LocalDate fechaInicial, LocalDate fechaFinal) {
        return usuarioPizzaDao.obtenerPizzasCompradasPorRangoDeFecha(id, fechaInicial, fechaFinal);
    }
}
