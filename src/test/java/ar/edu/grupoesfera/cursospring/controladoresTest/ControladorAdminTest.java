package ar.edu.grupoesfera.cursospring.controladoresTest;

import ar.edu.grupoesfera.cursospring.controladores.ControladorAdmin;
import ar.edu.grupoesfera.cursospring.controladores.ControladorPizzas;
import ar.edu.grupoesfera.cursospring.modelo.DatosCreacionPizza;
import ar.edu.grupoesfera.cursospring.servicios.ServicioPizza;
import org.assertj.core.api.AbstractBooleanAssert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class ControladorAdminTest {

    @Mock
    private HttpSession session;
    @InjectMocks
    private ControladorAdmin controladorAdmin;

    @InjectMocks
    private ControladorPizzas controladorPizzas;
    @Test
    public void irAEditarPizza() {
        // Preparación
        int pizzaId = 1;
        String nombrePizza = "Pizza Margarita";
        String descPizza = "Deliciosa pizza con tomate, mozzarella y albahaca";
        Double precioPizza = 1500.0;
        ModelMap expectedModel = new ModelMap();
        DatosCreacionPizza datosCrearPizza = new DatosCreacionPizza();
        expectedModel.put("datosCrearPizza", datosCrearPizza);
        expectedModel.put("nombrePizza", nombrePizza);
        expectedModel.put("descPizza", descPizza);
        expectedModel.put("precioPizza", precioPizza);
        expectedModel.put("pizzaId", pizzaId);

        // Ejecución
        ModelAndView mav = controladorAdmin.irAEditarPizza(pizzaId, nombrePizza, descPizza, precioPizza);

        // Comprobación
        assertEquals("editarPizza", mav.getViewName());
        ModelMap actualModel = mav.getModelMap();
        assertEquals(datosCrearPizza, actualModel.get("datosCrearPizza"));

    }


}
