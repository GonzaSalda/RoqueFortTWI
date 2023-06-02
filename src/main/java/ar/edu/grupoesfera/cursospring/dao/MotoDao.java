package ar.edu.grupoesfera.cursospring.dao;


import ar.edu.grupoesfera.cursospring.modelo.Moto;

import java.util.List;

public interface MotoDao {
    List<Moto> obtenerListaTotalMoto();

    Moto obtenerMotoPorID(Long id);

    void actualizarMoto(Moto moto);

    Moto findMotoDisponible();

}
