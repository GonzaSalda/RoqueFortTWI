package ar.edu.grupoesfera.cursospring.dao;

import ar.edu.grupoesfera.cursospring.modelo.Pizza;

import java.util.List;

public interface PizzaDao {

     List<Pizza> obtenerListaTotalPizzas();

     Pizza obtenerPizzaPorID(int id);

     void actualizarPizza(Pizza pizza);

     void guardarPizza(Pizza pizza);

}
