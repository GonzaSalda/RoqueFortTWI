package ar.edu.grupoesfera.cursospring.controladores;

import ar.edu.grupoesfera.cursospring.modelo.Moto;
import ar.edu.grupoesfera.cursospring.servicios.ServicioDelivery;
import ar.edu.grupoesfera.cursospring.servicios.ServicioMoto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ControladorDelivery {
    @Autowired
    private ServicioDelivery servicioDelivery;

    @Autowired
    ServicioMoto servicioMoto;

    @RequestMapping(value = "/realizarEntrega", method = RequestMethod.GET)
    public String realizarEntrega(@RequestParam("direccion") String direccion, Model model) {
        Moto motoAsignada = servicioMoto.asignarMotoDisponible();
        if (motoAsignada != null) {
            boolean entregaExitosa = servicioDelivery.realizarEntrega(direccion);
            model.addAttribute("entregaExitosa", entregaExitosa);
        }else{
            model.addAttribute("entregaExitosa", false);
        }

        return "resultadoEntrega";
    }
}
