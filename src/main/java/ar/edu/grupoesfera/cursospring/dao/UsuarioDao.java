package ar.edu.grupoesfera.cursospring.dao;

import ar.edu.grupoesfera.cursospring.modelo.Pizza;
import ar.edu.grupoesfera.cursospring.modelo.Usuario;
import ar.edu.grupoesfera.cursospring.modelo.Usuario_Pizza;

public interface UsuarioDao {
    Usuario buscarUsuario(String email, String password);

    Usuario buscarUsuarioPorEmail(String email);

    Usuario buscarPorId(int id);

    void guardarUsuario(Usuario usuario);

    Usuario_Pizza obtenerUsuarioPizza(Pizza pizza_obtenida, Usuario usuario);

    void guardarPizzaDelUsuario(Usuario usuario, Pizza pizza_obtenida);

}
