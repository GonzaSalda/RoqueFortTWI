package ar.edu.grupoesfera.cursospring.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import ar.edu.grupoesfera.cursospring.modelo.Carrito_Pizza;

public interface CarritoPizzaRepository extends JpaRepository<Carrito_Pizza, Integer> {
    List<Carrito_Pizza> findByCarritoId(int carritoId);

    Optional<Carrito_Pizza> findByCarritoIdAndPizzaId(int carritoId, int pizzaId);

    Optional<Carrito_Pizza> findByCarritoIdAndExtraId(int carritoId, int extraId);

    /*
     * Forma con SQL atómicas
     * 
     * @Modifying
     * 
     * @Transactional
     * 
     * @Query(value = "UPDATE carrito_pizza cp\n" +
     * "JOIN extras e ON cp.extra_id = e.id\n" +
     * "SET \n" +
     * "    cp.cantidad_extra = cp.cantidad_extra + :cantidad,\n" +
     * "    e.stock = e.stock - :cantidad\n" +
     * "WHERE \n" +
     * "    cp.carrito_id = :carritoId \n" +
     * "    AND cp.extra_id = :extraId\n" +
     * "    AND (e.stock - :cantidad) >= 0\n" +
     * "    AND (cp.cantidad_extra + :cantidad) >= 0", nativeQuery = true)
     * int actualizarCantidadExtra(@Param("carritoId") int carritoId,
     * 
     * @Param("extraId") int extraId,
     * 
     * @Param("cantidad") int cantidad);
     */
}
