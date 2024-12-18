package ar.edu.grupoesfera.cursospring.servicios;

import ar.edu.grupoesfera.cursospring.modelo.*;

public interface ServicioLogin {
    Usuario consultarUsuario(String email, String password);

    Usuario registrar(DatosRegistro datosRegistro);

    Usuario buscarUsuarioPorEmail(String email);

    Usuario buscarUsuarioPorId(int id);

    Usuario_Pizza obtenerUsuarioPizza(Pizza pizza_obtenida, Usuario usuario);

    void guardarPizzaEnListaUsuario(Pizza pizza_obtenido, Usuario usuario);

}
