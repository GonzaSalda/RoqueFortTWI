
package ar.edu.grupoesfera.cursospring.servicios;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.grupoesfera.cursospring.dao.CarritoPizzaRepository;
import ar.edu.grupoesfera.cursospring.dao.CarritoRepository;
import ar.edu.grupoesfera.cursospring.dao.ExtraRepository;
import ar.edu.grupoesfera.cursospring.dao.PizzaRepository;
import ar.edu.grupoesfera.cursospring.dao.UsuarioRepository;
import ar.edu.grupoesfera.cursospring.modelo.Carrito;
import ar.edu.grupoesfera.cursospring.modelo.Carrito_Pizza;
import ar.edu.grupoesfera.cursospring.modelo.Extra;
import ar.edu.grupoesfera.cursospring.modelo.Pizza;
import ar.edu.grupoesfera.cursospring.modelo.Usuario;
import jakarta.transaction.Transactional;

@Service
public class CarritoService {

    @Autowired
    private CarritoRepository carritoRepository;

    @Autowired
    private CarritoPizzaRepository carritoPizzaRepository;

    @Autowired
    private PizzaRepository pizzaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ExtraRepository extraRepository;

    public Carrito obtenerOCrearCarrito(int usuarioId) {
        return carritoRepository.findByUsuarioId(usuarioId).orElseGet(() -> {
            Usuario usuario = usuarioRepository.findById(usuarioId)
                    .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
            Carrito nuevoCarrito = new Carrito();
            nuevoCarrito.setUsuario(usuario);
            return carritoRepository.save(nuevoCarrito);
        });
    }

    public void agregarPizzaAlCarrito(int carritoId, int pizzaId, int cantidad) {
        Carrito carrito = carritoRepository.findById(carritoId)
                .orElseThrow(() -> new RuntimeException("Carrito no encontrado"));
        Pizza pizza = pizzaRepository.findById(pizzaId)
                .orElseThrow(() -> new RuntimeException("Pizza no encontrada"));

        // Buscar si ya existe una entrada para esta combinación de carrito y pizza
        Optional<Carrito_Pizza> carritoPizzaOptional = carritoPizzaRepository.findByCarritoIdAndPizzaId(carritoId,
                pizzaId);

        if (carritoPizzaOptional.isPresent()) {
            // Si existe, sumar la cantidad
            Carrito_Pizza carritoPizza = carritoPizzaOptional.get();
            carritoPizza.setCantidad(carritoPizza.getCantidad() + cantidad);
            carritoPizzaRepository.save(carritoPizza);
        } else {
            Carrito_Pizza carritoPizza = new Carrito_Pizza();
            carritoPizza.setCarrito(carrito);
            carritoPizza.setPizza(pizza);
            carritoPizza.setCantidad(cantidad);
            carritoPizzaRepository.save(carritoPizza);
        }
    }

    @Transactional
    public void agregarExtraAlCarrito(int carritoId, int extraId, int cantidad) {
        Carrito carrito = carritoRepository.findById(carritoId)
                .orElseThrow(() -> new RuntimeException("Carrito no encontrado"));
        Extra extra = extraRepository.findById(extraId)
                .orElseThrow(() -> new RuntimeException("Extra no encontrado"));

        if (extra.getStock() < cantidad) {
            throw new RuntimeException("Stock insuficiente para el extra solicitado.");
        }

        Optional<Carrito_Pizza> carritoExtraOptional = carritoPizzaRepository.findByCarritoIdAndExtraId(carritoId,
                extraId);

        Carrito_Pizza carritoExtra;

        if (carritoExtraOptional.isPresent()) {
            carritoExtra = carritoExtraOptional.get();
            carritoExtra.setCantidadExtra(carritoExtra.getCantidadExtra() + cantidad);
        } else {
            carritoExtra = new Carrito_Pizza();
            carritoExtra.setCarrito(carrito);
            carritoExtra.setExtra(extra);
            carritoExtra.setCantidadExtra(cantidad);
        }
        extra.setStock(extra.getStock() - cantidad);

        carritoPizzaRepository.save(carritoExtra);
        extraRepository.save(extra);
    }

    public List<Carrito_Pizza> obtenerPizzasDelCarrito(int carritoId) {
        List<Carrito_Pizza> carritoPizzas = carritoPizzaRepository.findByCarritoId(carritoId);
        return carritoPizzas;
    }

    public void actualizarCantidadPizza(int carritoId, int pizzaId, int cantidad) {
        Carrito_Pizza carritoPizza = carritoPizzaRepository.findByCarritoIdAndPizzaId(carritoId, pizzaId)
                .orElseThrow(() -> new RuntimeException("Carrito y Pizza no encontrados"));

        // Actualizar la cantidad (asegúrate de que no sea menor que 1)
        int nuevaCantidad = carritoPizza.getCantidad() + cantidad;

        if (nuevaCantidad > 0) {
            carritoPizza.setCantidad(nuevaCantidad);
            carritoPizzaRepository.save(carritoPizza);
        } else {
            // Si la cantidad llega a 0, elimina la pizza del carrito
            carritoPizzaRepository.delete(carritoPizza);
        }
    }

    @Transactional
    public void actualizarCantidadExtra(int carrito_id, int extraId, int cantidad) {
        Carrito_Pizza carritoExtra = carritoPizzaRepository.findByCarritoIdAndExtraId(carrito_id, extraId)
                .orElseThrow(() -> new RuntimeException("Carrito y Extra no encontrados"));
        Extra extra = extraRepository.findById(extraId).orElseThrow(() -> new RuntimeException("Extra no encontrado"));

        int nuevaCantidad = carritoExtra.getCantidadExtra() + cantidad;

        // Validar si hay suficiente stock antes de sumar
        if (cantidad > 0 && cantidad > extra.getStock()) {
            throw new RuntimeException("Stock insuficiente para agregar esta cantidad.");
        }

        if (nuevaCantidad > 0) {
            carritoExtra.setCantidadExtra(nuevaCantidad);
            extra.setStock(extra.getStock() - cantidad);
            carritoPizzaRepository.save(carritoExtra);
            extraRepository.save(extra);
        } else if (nuevaCantidad == 0) {
            carritoPizzaRepository.delete(carritoExtra);
            extra.setStock(extra.getStock() + carritoExtra.getCantidadExtra());
            extraRepository.save(extra);
        } else {
            throw new RuntimeException("Cantidad inválida. No se puede tener una cantidad negativa en el carrito.");
        }
    }

    public Double calcularTotalPizzas(int carritoId) {
        List<Carrito_Pizza> carritoPizzas = carritoPizzaRepository.findByCarritoId(carritoId);
        Double total = 0.0;
        for (Carrito_Pizza carritoPizza : carritoPizzas) {
            if (carritoPizza.getPizza() != null) {
                total += carritoPizza.getPizza().getPrecio() * carritoPizza.getCantidad();
            }
        }
        return total;
    }

    public Double calcularTotalExtras(int carritoId) {
        List<Carrito_Pizza> carritoExtras = carritoPizzaRepository.findByCarritoId(carritoId);
        Double total = 0.0;
        for (Carrito_Pizza carritoExtra : carritoExtras) {
            if (carritoExtra.getExtra() != null) {
                total += carritoExtra.getExtra().getPrecio() * carritoExtra.getCantidadExtra();
            }
        }
        return total;
    }

    public void eliminarPizzaDelCarrito(int carritoId, int pizzaId) {
        Carrito_Pizza carritoPizza = carritoPizzaRepository.findByCarritoIdAndPizzaId(carritoId, pizzaId)
                .orElseThrow(() -> new RuntimeException("Carrito y Pizza no encontrados"));
        carritoPizzaRepository.delete(carritoPizza);
    }

    @Transactional
    public void eliminarExtraDelCarrito(int carritoId, int extraId) {
        Carrito_Pizza carrito_Pizza = carritoPizzaRepository.findByCarritoIdAndExtraId(carritoId, extraId)
                .orElseThrow(() -> new RuntimeException("Carrito y Extra no encontrados"));
        Extra extra = extraRepository.findById(extraId).orElseThrow(() -> new RuntimeException("Extra no encontrado"));

        carritoPizzaRepository.delete(carrito_Pizza);
        extra.setStock(extra.getStock() + carrito_Pizza.getCantidadExtra());
        extraRepository.save(extra);
    }
}
