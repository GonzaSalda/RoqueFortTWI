package ar.edu.grupoesfera.cursospring.controladoresTest;

import ar.edu.grupoesfera.cursospring.controladores.ControladorPizzas;
import ar.edu.grupoesfera.cursospring.modelo.Pizza;
import ar.edu.grupoesfera.cursospring.servicios.ServicioPizza;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ControladorPizzasTest {

    @Mock
    private ServicioPizza servicioPizza;

    @Mock
    private HttpSession session;

    @InjectMocks
    private ControladorPizzas controladorPizzas;

    @Test
    public void queSeMuestreLaListaDePizzas() {

        // Preparación
        List<Pizza> pizzas = new ArrayList<>();
        pizzas.add(new Pizza());
        pizzas.add(new Pizza());
        pizzas.add(new Pizza());

        when(servicioPizza.getPizza()).thenReturn(pizzas);

        // Ejecución
        ModelAndView mav = controladorPizzas.verListaPizzas(null, null, session);

        // Comprobación
        assertThat(mav.getViewName()).isEqualTo("seccionPizzas");
        assertThat(mav.getModel()).containsKey("lista_pizzas");

        List<Pizza> listaPizzas = (List<Pizza>) mav.getModel().get("lista_pizzas");
        assertThat(listaPizzas).hasSize(3);
    }

    @Test
    public void pizzaCreada_DebeGuardarPizza() {
        // Preparación
        Pizza pizza = new Pizza();

        // Configurar el comportamiento esperado del servicio
        doNothing().when(servicioPizza).savePizza(pizza);

        // Ejecución
        ModelAndView resultModelAndView = controladorPizzas.pizzaCreada(pizza);
        ModelAndView expectedModelAndView = new ModelAndView("crearPizza");

        // Verificación
        assertThat(resultModelAndView.getViewName()).isEqualTo(expectedModelAndView.getViewName());
        verify(servicioPizza, times(1)).savePizza(pizza);
    }
}
