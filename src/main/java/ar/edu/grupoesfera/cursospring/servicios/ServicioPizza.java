package ar.edu.grupoesfera.cursospring.servicios;

import ar.edu.grupoesfera.cursospring.modelo.Pizza;

import java.util.List;

public interface ServicioPizza {
    List<Pizza> getPizza();

    Pizza buscarPizzaPorId(int id);
    void actualizarPizza(int idPizza, String nombre, String descripcion, Double precio);

}
