package ar.edu.grupoesfera.cursospring.servicios;

import ar.edu.grupoesfera.cursospring.dao.UsuarioPizzaDao;
import ar.edu.grupoesfera.cursospring.modelo.Usuario_Pizza;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ServicioUsuarioPizzaImpl implements ServicioUsuarioPizza{
    @Autowired
    private UsuarioPizzaDao usuarioPizzaDao;

    @Override
    public List<Usuario_Pizza> buscarHistorialPorIdUsuario(Integer id) {
        return usuarioPizzaDao.obtenerPizzasCompradasPorUsuario(id);
    }

    @Override
    public List<Usuario_Pizza> buscarHistorialPorIdUserltimas24hs(Long id) {
        return usuarioPizzaDao.obtenerPizzasCompradasPorUsuario24hs(id);
    }

    @Override
    public List<Usuario_Pizza> buscarHistorialPorIdUserltimas48hs(Long id) {
        return usuarioPizzaDao.obtenerPizzasCompradasPorUsuario48hs(id);
    }

    @Override
    public List<Usuario_Pizza> buscarHistorialPorIdRangoFecha(Long id, LocalDate fechaInicial, LocalDate fechaFinal) {
        return usuarioPizzaDao.obtenerPizzasCompradasPorRangoDeFecha(id, fechaInicial, fechaFinal);
    }
}
