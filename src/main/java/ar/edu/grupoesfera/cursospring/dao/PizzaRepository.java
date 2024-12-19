package ar.edu.grupoesfera.cursospring.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import ar.edu.grupoesfera.cursospring.modelo.Pizza;

public interface PizzaRepository extends JpaRepository<Pizza, Integer> {
}
