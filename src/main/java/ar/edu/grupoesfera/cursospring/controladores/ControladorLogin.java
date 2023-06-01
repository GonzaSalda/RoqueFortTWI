package ar.edu.grupoesfera.cursospring.controladores;

import ar.edu.grupoesfera.cursospring.modelo.*;
import ar.edu.grupoesfera.cursospring.servicios.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;
import java.util.List;


@Controller
public class ControladorLogin {
    @Inject
    private ServicioLogin servicioLogin;
    @Inject
    private ServicioPizza servicioPizza;


    @Autowired
    public ControladorLogin(ServicioLogin servicioLogin) {
        this.servicioLogin = servicioLogin;
    }


    @RequestMapping(path = "/registro")
    public ModelAndView irAlRegistro() {
        ModelMap model = new ModelMap();
        DatosRegistro datosRegistro = new DatosRegistro();
        model.put("datosRegistro", datosRegistro);
        return new ModelAndView("registro", model);
    }

    @RequestMapping(path = "/registrar", method = RequestMethod.POST)
    public ModelAndView registrar(@ModelAttribute("datosRegistro") DatosRegistro datosRegistro) {
        ModelMap model = new ModelMap();
        String viewName = "";
        if (servicioLogin.buscarUsuarioPorEmail(datosRegistro.getEmail()) == null) {
            try {
                servicioLogin.registrar(datosRegistro);
                model.put("datosLogin", new DatosLogin());
                viewName = "login";
            }
            catch (Exception e) {
                model.put("error", "Las contraseñas no son iguales");
                model.put("datosRegistro", datosRegistro);
                viewName = "registro";
            }
        }
        else {
            model.put("error", "Debe llenar los campos");
            model.put("datosRegistro", datosRegistro);
            viewName = "registro";
        }

        return new ModelAndView(viewName, model);
    }

    @RequestMapping("/login")
    public ModelAndView irALogin() {
        ModelMap modelo = new ModelMap();
        DatosLogin datosLogin = new DatosLogin();
        modelo.put("datosLogin", datosLogin);
        return new ModelAndView("login", modelo);
    }

    @RequestMapping(path = "/validar-login", method = RequestMethod.POST)
    public ModelAndView validarLogin(@ModelAttribute("datosLogin") DatosLogin datosLogin, HttpSession session) {
        ModelMap model = new ModelMap();
        try {
            // Se obtiene un usuario por email y contraseña, si no existe, devuelve una excepcion
            Usuario usuarioBuscado = servicioLogin.consultarUsuario(datosLogin.getEmail(), datosLogin.getPassword());

            // Se guardan los datos del usuario en la sesion
            session.setAttribute("idUsuario", usuarioBuscado.getId());
            session.setAttribute("nombreUsuario", usuarioBuscado.getNombre());
            session.setAttribute("ROL", usuarioBuscado.getRol());
            session.setAttribute("imgUsuario", usuarioBuscado.getImagen());
            session.setAttribute("user", usuarioBuscado);

            List<Pizza> pizzas = servicioPizza.getPizza();

            model.put("lista_pizzas", pizzas);
            return new ModelAndView("seccionPizzas", model);
        }
        catch (Exception e) {
            model.put("error", "Usuario o clave incorrectos");
        }

        return new ModelAndView("login", model);
    }

    @RequestMapping(path = "/cerrarSesion")
    public ModelAndView cerrarSesion(HttpSession session) {

        // Se eliminan los datos de la sesion iniciado del usuario y se lo redirije al
        // home
        session.removeAttribute("idUsuario");
        session.removeAttribute("nombreUsuario");
        session.removeAttribute("ROL");

        return new ModelAndView("redirect:/");
    }



}
