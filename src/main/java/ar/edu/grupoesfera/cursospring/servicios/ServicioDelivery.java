package ar.edu.grupoesfera.cursospring.servicios;

import ar.edu.grupoesfera.cursospring.modelo.Moto;

public interface ServicioDelivery {

    boolean realizarEntrega(String direccion, Moto moto, long tiempoEstimado);

}
