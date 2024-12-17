package ar.edu.grupoesfera.cursospring.controladores;

import ar.edu.grupoesfera.cursospring.modelo.Usuario_Pizza;
import ar.edu.grupoesfera.cursospring.servicios.ServicioUsuarioPizza;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ControladorHistorial {

    private final ServicioUsuarioPizza servicioUsuarioPizza;

    @Autowired
    public ControladorHistorial(ServicioUsuarioPizza servicioUsuarioPizza) {
        this.servicioUsuarioPizza = servicioUsuarioPizza;
    }


    @RequestMapping("/historial")
    public ModelAndView mostrarHistorialComprasSimple(HttpServletRequest request) {
        Integer id = (Integer) request.getSession().getAttribute("idUsuario");
        ModelMap modelo = new ModelMap();
        if (id == null) {
            return new ModelAndView("redirect:/");
        }

        try {
            ArrayList<Usuario_Pizza> pizzasCompradas = (ArrayList<Usuario_Pizza>) servicioUsuarioPizza.buscarHistorialPorIdUsuario(id);
            modelo.put("activetodo", "bg-indigo-400 text-white");
            modelo.put("formestado", "hidden");
            modelo.put("pizzas", pizzasCompradas);
        } catch (Exception e) {
            return new ModelAndView("error", modelo);
        }


        return new ModelAndView("historial", modelo);
    }
    @RequestMapping("/ultimas-24hs")
    public ModelAndView devolverUltimas24hs( HttpServletRequest request)
    {
        Integer id = (Integer) request.getSession().getAttribute("idUsuario");

        if(id == null){
            return new ModelAndView("redirect:/");
        }
        ModelMap modelo = new ModelMap();
        try{
            List<Usuario_Pizza> pizzasComprados = servicioUsuarioPizza.buscarHistorialPorIdUserltimas24hs(Long.valueOf(id));
            modelo.put("active24", "bg-indigo-400 text-white");
            modelo.put("formestado", "hidden");
            modelo.put("pizzas", pizzasComprados);
        } catch (Exception e) {
            modelo.put("error", e);
            return new ModelAndView("historial", modelo);
        }

        return new ModelAndView("historial", modelo);
    }

    @RequestMapping("/ultimas-48hs")
    public ModelAndView devolverUltimas48hs(HttpServletRequest request)
    {
        Integer id = (Integer) request.getSession().getAttribute("idUsuario");

        if(id == null){
            return new ModelAndView("redirect:/");
        }
        ModelMap modelo = new ModelMap();
        try{
            List<Usuario_Pizza> pizzasCompradas = servicioUsuarioPizza.buscarHistorialPorIdUserltimas48hs(Long.valueOf(id));

            modelo.put("active48", "bg-indigo-400 text-white");
            modelo.put("formestado", "hidden");
            modelo.put("pizzas", pizzasCompradas);
        } catch (Exception e) {
            modelo.put("error", e);
            return new ModelAndView("historial", modelo);
        }
        return new ModelAndView("historial", modelo);
    }

    @RequestMapping("/rango-fecha-enviar")
    public ModelAndView devolverApuestasDeRangoDeFecha(HttpServletRequest request, @RequestParam("fechaInicial") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate fechaInicial,
                                                       @RequestParam("fechaFinal") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate fechaFinal)
    {
        Integer id = (Integer) request.getSession().getAttribute("idUsuario");
        ModelMap modelo = new ModelMap();
        if(id == null){
            return new ModelAndView("redirect:/");
        }

        try{
            List<Usuario_Pizza> pizzasCompradas = servicioUsuarioPizza.buscarHistorialPorIdRangoFecha(Long.valueOf(id), fechaInicial, fechaFinal);
            modelo.put("activerango", "bg-indigo-400 text-white");

            modelo.put("pizzas", pizzasCompradas);
        } catch (Exception e) {
            modelo.put("error", e);
            return new ModelAndView("historial", modelo);
        }
        return new ModelAndView("historial", modelo);
    }

    @RequestMapping("/rango-fecha")
    public ModelAndView devolverApuestasDeRangoActivo(HttpServletRequest request) {
        Integer id = (Integer) request.getSession().getAttribute("idUsuario");
        if(id == null){
            return new ModelAndView("redirect:/");
        }
        ModelMap modelo = new ModelMap();

        modelo.put("activerango", "bg-indigo-400 text-white");

        return new ModelAndView("historial", modelo);
    }

}
