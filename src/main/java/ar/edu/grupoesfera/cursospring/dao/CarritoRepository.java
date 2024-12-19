package ar.edu.grupoesfera.cursospring.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import ar.edu.grupoesfera.cursospring.modelo.Carrito;

public interface CarritoRepository extends JpaRepository<Carrito, Integer> {
    Optional<Carrito> findByUsuarioId(int usuarioId);

}
