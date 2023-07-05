package ar.edu.grupoesfera.cursospring.servicios;

import ar.edu.grupoesfera.cursospring.modelo.Moto;

import java.util.List;

public interface ServicioMoto {
    Moto asignarMotoDisponible();
    List<Moto> obtenerMotos();
}
