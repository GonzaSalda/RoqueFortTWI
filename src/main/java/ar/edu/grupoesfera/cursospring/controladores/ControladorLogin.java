package ar.edu.grupoesfera.cursospring.controladores;

import ar.edu.grupoesfera.cursospring.modelo.*;
import ar.edu.grupoesfera.cursospring.servicios.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpSession;
import java.util.List;

@Controller
public class ControladorLogin {

    private final ServicioLogin servicioLogin;
    private final ServicioPizza servicioPizza;

    @Autowired
    public ControladorLogin(ServicioLogin servicioLogin, ServicioPizza servicioPizza) {
        this.servicioLogin = servicioLogin;
        this.servicioPizza = servicioPizza;
    }

    @GetMapping("/")
    public String irAHome() {
        return "index";
    }

    @GetMapping("/registro")
    public String irAlRegistro(Model model) {
        model.addAttribute("datosRegistro", new DatosRegistro());
        return "registro";
    }

    @PostMapping("/registrar")
    public String registrar(@ModelAttribute("datosRegistro") DatosRegistro datosRegistro, Model model) {
        if (servicioLogin.buscarUsuarioPorEmail(datosRegistro.getEmail()) == null) {
            try {
                servicioLogin.registrar(datosRegistro);
                model.addAttribute("datosLogin", new DatosLogin());
                return "login";
            } catch (Exception e) {
                model.addAttribute("error", "Las contraseñas no son iguales");
                model.addAttribute("datosRegistro", datosRegistro);
                return "registro";
            }
        } else {
            model.addAttribute("error", "Debe llenar los campos");
            model.addAttribute("datosRegistro", datosRegistro);
            return "registro";
        }
    }

    @GetMapping("/login")
    public String irALogin(Model model) {
        model.addAttribute("datosLogin", new DatosLogin());
        return "login";
    }

    @RequestMapping("/roquefort")
    public String validarLogin(@ModelAttribute("datosLogin") DatosLogin datosLogin, HttpSession session, Model model) {
        try {
            // Validación de usuario
            Usuario usuarioBuscado = servicioLogin.consultarUsuario(datosLogin.getEmail(), datosLogin.getPassword());
            // Guardar los datos en la sesión
            session.setAttribute("idUsuario", usuarioBuscado.getId());
            session.setAttribute("nombreUsuario", usuarioBuscado.getNombre());
            session.setAttribute("ROL", usuarioBuscado.getRol());
            session.setAttribute("imgUsuario", usuarioBuscado.getImagen());
            session.setAttribute("user", usuarioBuscado);

            // Obtener pizzas para mostrar
            List<Pizza> pizzas = servicioPizza.getPizza();
            model.addAttribute("lista_pizzas", pizzas);

            return "seccionPizzas";
        } catch (Exception e) {
            model.addAttribute("error", "Usuario o clave incorrectos");
            return "login";
        }
    }

    @GetMapping("/cerrarSesion")
    public String cerrarSesion(HttpSession session) {
        // Eliminar los atributos de la sesión
        session.invalidate();
        return "index";
    }
}
