package ar.edu.grupoesfera.cursospring.servicios;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import ar.edu.grupoesfera.cursospring.dao.PizzaRepository;
import ar.edu.grupoesfera.cursospring.modelo.Pizza;

@Service
public class PizzaService {

    @Autowired
    private PizzaRepository pizzaRepository;

    public List<Pizza> getPizza() {
        return pizzaRepository.findAll();
    }

    public void crearPizza(String nombre, String descripcion, double precio, MultipartFile imagen) throws IOException {
        Pizza pizza = new Pizza();
        pizza.setNombre(nombre);
        pizza.setDescripcion(descripcion);
        pizza.setPrecio(precio);

        // Guardar imagen si es proporcionada
        if (!imagen.isEmpty()) {
            String rutaImagen = guardarImagen(imagen);
            pizza.setImagen(rutaImagen);
        }
        pizzaRepository.save(pizza);
    }

    public Pizza buscarPizzaPorId(int id) {
        return pizzaRepository.findById(id).orElseThrow(() -> new RuntimeException("Pizza no encontrada"));
    }

    public void actualizarPizza(int pizzaId, String nombre, String descripcion, double precio, MultipartFile imagen) {
        Pizza pizza = pizzaRepository.findById(pizzaId).orElseThrow(() -> new RuntimeException("Pizza no encontrada."));

        pizza.setNombre(nombre);
        pizza.setDescripcion(descripcion);
        pizza.setPrecio(precio);

        if (!imagen.isEmpty()) {
            try {
                String rutaImagen = guardarImagen(imagen);
                pizza.setImagen(rutaImagen);
            } catch (IOException e) {
                throw new RuntimeException("Error al guardar la imagen", e);
            }
        }

        pizzaRepository.save(pizza);
    }

    private String guardarImagen(MultipartFile imagen) throws IOException {
        String nombreArchivo = System.currentTimeMillis() + "_" + imagen.getOriginalFilename();

        // Obtener el directorio actual del proyecto
        String rutaDirectorio = new File("src/main/resources/static/imagenes/pizzas/").getAbsolutePath();
        File directorio = new File(rutaDirectorio);

        // Crear el directorio si no existe
        if (!directorio.exists()) {
            directorio.mkdirs();
        }

        // Ruta completa del archivo
        File destino = new File(directorio, nombreArchivo);

        // Guardar la imagen en el destino
        imagen.transferTo(destino);

        // Devolver la ruta relativa para usarla en las vistas
        return nombreArchivo;
    }

}