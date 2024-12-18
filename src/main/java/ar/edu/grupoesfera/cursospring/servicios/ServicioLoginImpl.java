package ar.edu.grupoesfera.cursospring.servicios;

import ar.edu.grupoesfera.cursospring.dao.UsuarioDao;
import ar.edu.grupoesfera.cursospring.modelo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class ServicioLoginImpl implements ServicioLogin {
    private final UsuarioDao usuarioDao;

    @Autowired
    public ServicioLoginImpl(UsuarioDao usuarioDao) {
        this.usuarioDao = usuarioDao;
    }

    @Override
    public Usuario consultarUsuario(String email, String password) {
        Usuario usuarioObtenido = usuarioDao.buscarUsuario(email, password);
        if (usuarioObtenido != null) {
            return usuarioObtenido;
        } else {
            throw new UsuarioInexistenteException();
        }
    }

    @Override
    public Usuario buscarUsuarioPorEmail(String email) {
        return usuarioDao.buscarUsuarioPorEmail(email);
    }

    @Override
    public Usuario registrar(DatosRegistro datosRegistro) {

        Usuario nuevoUsuario = new Usuario();

        // Se comprueba si las contraseñas ingresadas son iguales
        if (datosRegistro.getContrasenia().equals(datosRegistro.getRepetirContrasenia())) {

            nuevoUsuario.setNombre(datosRegistro.getNombre());
            nuevoUsuario.setEmail(datosRegistro.getEmail());
            nuevoUsuario.setPassword(datosRegistro.getContrasenia());
            nuevoUsuario.setRol("cliente");
            usuarioDao.guardarUsuario(nuevoUsuario);

            return nuevoUsuario;
        } else {
            throw new ClavesNoSonIgualesException();
        }
    }

    @Override
    public Usuario buscarUsuarioPorId(int id_user) {
        return usuarioDao.buscarPorId(id_user);
    }

    @Override
    public Usuario_Pizza obtenerUsuarioPizza(Pizza pizza_obtenida, Usuario usuario) {
        return usuarioDao.obtenerUsuarioPizza(pizza_obtenida, usuario);
    }

    @Override
    public void guardarPizzaEnListaUsuario(Pizza pizza_obtenido, Usuario usuario) {
        usuarioDao.guardarPizzaDelUsuario(usuario, pizza_obtenido);
    }

 
}
