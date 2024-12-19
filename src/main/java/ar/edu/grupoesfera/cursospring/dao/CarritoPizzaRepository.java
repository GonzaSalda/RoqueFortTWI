package ar.edu.grupoesfera.cursospring.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import ar.edu.grupoesfera.cursospring.modelo.Carrito_Pizza;

public interface CarritoPizzaRepository extends JpaRepository<Carrito_Pizza, Integer> {
    List<Carrito_Pizza> findByCarritoId(int carritoId);

    Optional<Carrito_Pizza> findByCarritoIdAndPizzaId(int carritoId, int pizzaId);

    Optional<Carrito_Pizza> findByCarritoIdAndExtraId(int carritoId, int extraId);

}
