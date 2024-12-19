package ar.edu.grupoesfera.cursospring.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import ar.edu.grupoesfera.cursospring.modelo.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
}
