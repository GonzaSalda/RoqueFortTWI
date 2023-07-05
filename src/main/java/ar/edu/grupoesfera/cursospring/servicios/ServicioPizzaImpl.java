package ar.edu.grupoesfera.cursospring.servicios;

import ar.edu.grupoesfera.cursospring.dao.PizzaDao;
import ar.edu.grupoesfera.cursospring.modelo.Pizza;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

@Service("servicioPizza")
public class ServicioPizzaImpl implements ServicioPizza {
    @Inject
    private PizzaDao pizzaDao;

    @Override
    public List<Pizza> getPizza() {
        List<Pizza> lista_pizzas = pizzaDao.obtenerListaTotalPizzas();
        return lista_pizzas;
    }
    @Override
    public Pizza buscarPizzaPorId(int id){
       if (pizzaDao.obtenerPizzaPorID(id) != null) {
           return pizzaDao.obtenerPizzaPorID(id);
       }
       else {
           throw new PizzaInexistenteException();
       }
   }

    @Override
    public void actualizarPizza(int idPizza, String nombre, String descripcion, Double precio) {
        Pizza pizza = pizzaDao.obtenerPizzaPorID(idPizza);
        pizza.setNombre(nombre);
        pizza.setDescripcion(descripcion);
        pizza.setPrecio(precio);

        pizzaDao.actualizarPizza(pizza);
    }

    @Override
    public void savePizza(Pizza pizza) {
       pizzaDao.guardarPizza(pizza);
    }

}
