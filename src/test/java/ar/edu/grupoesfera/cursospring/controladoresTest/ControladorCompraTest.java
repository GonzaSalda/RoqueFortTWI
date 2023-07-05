package ar.edu.grupoesfera.cursospring.controladoresTest;

import ar.edu.grupoesfera.cursospring.controladores.ControladorCompra;
import ar.edu.grupoesfera.cursospring.modelo.Moto;
import ar.edu.grupoesfera.cursospring.modelo.Pizza;
import ar.edu.grupoesfera.cursospring.modelo.Usuario;
import ar.edu.grupoesfera.cursospring.modelo.Usuario_Pizza;
import ar.edu.grupoesfera.cursospring.servicios.*;
import com.google.maps.errors.ApiException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import java.io.IOException;

@RunWith(MockitoJUnitRunner.class)
public class ControladorCompraTest {

    @Mock
    private HttpSession session;

    @Mock
    private ServicioLogin servicioLogin;

    @Mock
    private ServicioPizza servicioPizza;

    @Mock
    private ServicioMoto servicioMoto;

    @Mock
    private ServicioDelivery servicioDelivery;

    @Mock
    private ModelMap modelMap;

    @Mock
    private ServicioGoogleMaps servicioGoogleMaps;

    @Mock
    private Model model;

    @InjectMocks
    private ControladorCompra controladorCompra;



    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        session = new MockHttpSession();
        session.setAttribute("idUsuario", 1);
    }


    @Test
    public void testVerificarCompraEntregaExitosa() throws IOException, InterruptedException, ApiException {
        // Preparación
        int nroTarjeta = 123456;
        String direccion = "Calle Principal";
        int pizzaId = 1;
        boolean delivery = true;
        Usuario usuario = new Usuario();
        Pizza pizza = new Pizza();
        Usuario_Pizza usuarioPizza = new Usuario_Pizza();
        Moto motoAsignada = new Moto();
        boolean entregaExitosa = true;
        String informacionDeEntrega = "Información de entrega";
        String viewName = "resultadoEntrega";

        when(session.getAttribute("idUsuario")).thenReturn(1);
        when(servicioLogin.buscarUsuarioPorId(1)).thenReturn(usuario);
        when(servicioPizza.buscarPizzaPorId(pizzaId)).thenReturn(pizza);
        when(servicioLogin.obtenerUsuarioPizza(pizza, usuario)).thenReturn(usuarioPizza);
        when(servicioMoto.asignarMotoDisponible()).thenReturn(motoAsignada);
        when(servicioDelivery.realizarEntrega(direccion,motoAsignada, 15)).thenReturn(entregaExitosa);
        when(servicioGoogleMaps.GoogleMapsAPIConfiguration(eq(direccion))).thenReturn(informacionDeEntrega);

        // Ejecución
        ModelAndView modelAndView = controladorCompra.verificarCompra(nroTarjeta, direccion, pizzaId, delivery, session);

        // Verificación
        assertEquals(viewName, modelAndView.getViewName());
        assertEquals(modelMap, modelAndView.getModel());
        verify(servicioLogin, times(1)).verificarTarjetaUsuario(usuario, nroTarjeta);
        verify(servicioMoto, times(1)).asignarMotoDisponible();
        verify(servicioDelivery, times(1)).realizarEntrega(direccion,motoAsignada, 15);
        verify(servicioLogin, times(1)).guardarPizzaEnListaUsuario(pizza, usuario);
        verify(modelMap, times(1)).addAttribute("informacionDeEntrega", informacionDeEntrega);
        verify(modelMap, times(1)).addAttribute("entregaExitosa", entregaExitosa);
    }
    @Test
    public void testVerificarCompraEntregaNoExitosa() throws IOException, InterruptedException, ApiException {
        // Preparación
        int nroTarjeta = 123456;
        String direccion = "Calle Principal";
        int pizzaId = 1;
        boolean delivery = true;
        Usuario usuario = new Usuario();
        Pizza pizza = new Pizza();
        Usuario_Pizza usuarioPizza = new Usuario_Pizza();
        Moto motoAsignada = null;
        boolean entregaExitosa = false;
        String viewName = "resultadoEntrega";

        when(session.getAttribute("idUsuario")).thenReturn(1);
        when(servicioLogin.buscarUsuarioPorId(1)).thenReturn(usuario);
        when(servicioPizza.buscarPizzaPorId(pizzaId)).thenReturn(pizza);
        when(servicioLogin.obtenerUsuarioPizza(pizza, usuario)).thenReturn(usuarioPizza);
        when(servicioMoto.asignarMotoDisponible()).thenReturn(motoAsignada);

        // Ejecución
        ModelAndView modelAndView = controladorCompra.verificarCompra(nroTarjeta, direccion, pizzaId, delivery, session);

        // Verificación
        assertEquals(viewName, modelAndView.getViewName());
        assertEquals(modelMap, modelAndView.getModel());
        verify(servicioLogin, times(1)).verificarTarjetaUsuario(usuario, nroTarjeta);
        verify(servicioMoto, times(1)).asignarMotoDisponible();
        verify(modelMap, times(1)).addAttribute("entregaExitosa", entregaExitosa);
    }

    @Test
    public void testVerificarCompraSinDelivery() throws IOException, InterruptedException, ApiException {
        // Preparación
        int nroTarjeta = 123456;
        int pizzaId = 1;
        boolean delivery = false;
        Usuario usuario = new Usuario();
        Pizza pizza = new Pizza();
        Usuario_Pizza usuarioPizza = new Usuario_Pizza();
        String viewName = "compraRealizada";

        when(session.getAttribute("idUsuario")).thenReturn(1);
        when(servicioLogin.buscarUsuarioPorId(1)).thenReturn(usuario);
        when(servicioPizza.buscarPizzaPorId(pizzaId)).thenReturn(pizza);
        when(servicioLogin.obtenerUsuarioPizza(pizza, usuario)).thenReturn(usuarioPizza);

        // Ejecución
        ModelAndView modelAndView = controladorCompra.verificarCompra(nroTarjeta, null, pizzaId, delivery, session);

        // Verificación
        assertEquals(viewName, modelAndView.getViewName());
        assertEquals(modelMap, modelAndView.getModel());
        verify(servicioLogin, times(1)).verificarTarjetaUsuario(usuario, nroTarjeta);
        verify(servicioLogin, times(1)).guardarPizzaEnListaUsuario(pizza, usuario);
    }

    @Test(expected = Exception.class)
    public void testVerificarCompraTarjetaIncorrecta() throws IOException, InterruptedException, ApiException {
        // Preparación
        int nroTarjeta = 123456;
        String direccion = "Calle Principal";
        int pizzaId = 1;
        boolean delivery = true;
        Usuario usuario = new Usuario();
        Pizza pizza = new Pizza();
        Usuario_Pizza usuarioPizza = new Usuario_Pizza();
        String viewName = "verificacionCompra";

        when(session.getAttribute("idUsuario")).thenReturn(1);
        when(servicioLogin.buscarUsuarioPorId(1)).thenReturn(usuario);
        when(servicioPizza.buscarPizzaPorId(pizzaId)).thenReturn(pizza);
        when(servicioLogin.obtenerUsuarioPizza(pizza, usuario)).thenReturn(usuarioPizza);
        doThrow(new Exception("El número de tarjeta ingresado es incorrecto.")).when(servicioLogin).verificarTarjetaUsuario(usuario, nroTarjeta);

        // Ejecución
        ModelAndView modelAndView = controladorCompra.verificarCompra(nroTarjeta, direccion, pizzaId, delivery, session);

        // Verificación
        assertEquals(viewName, modelAndView.getViewName());
        assertEquals(modelMap, modelAndView.getModel());
        verify(modelMap, times(1)).put("tarjetaIncorrecta", "El número de tarjeta ingresado es incorrecto.");
    }
}
