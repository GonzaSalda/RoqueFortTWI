package ar.edu.grupoesfera.cursospring.controladores;

import ar.edu.grupoesfera.cursospring.modelo.CompraCola;
import ar.edu.grupoesfera.cursospring.modelo.Moto;
import ar.edu.grupoesfera.cursospring.servicios.ServicioDelivery;
import ar.edu.grupoesfera.cursospring.servicios.ServicioMoto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class ControladorDelivery {

    @Autowired
    private ServicioMoto servicioMoto;

    @Autowired
    private ServicioDelivery servicioDelivery;

    @RequestMapping(path = "/listarMotos", method = RequestMethod.GET)
    public ModelAndView listarMotos() {
        List<Moto> motos = servicioMoto.obtenerMotos();
        List<CompraCola> pedidosEnCola = servicioDelivery.obtenerPedidosEnCola();
        ModelMap model = new ModelMap();
        model.addAttribute("motos", motos);
        model.addAttribute("pedidosEnCola", pedidosEnCola);
        return new ModelAndView("motos", model);
    }


    @RequestMapping(path = "/verPedidosEnCola", method = RequestMethod.GET)
    public ModelAndView verColas() {
        List<CompraCola> pedidosEnCola = servicioDelivery.obtenerPedidosEnCola();
        ModelMap model = new ModelMap();
        model.addAttribute("pedidosEnCola", pedidosEnCola);
        return new ModelAndView("pedidosEnCola", model);
    }
}
