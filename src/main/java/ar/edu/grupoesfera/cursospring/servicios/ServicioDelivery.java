package ar.edu.grupoesfera.cursospring.servicios;

import ar.edu.grupoesfera.cursospring.modelo.Moto;
import com.google.maps.errors.ApiException;

import java.io.IOException;

public interface ServicioDelivery {

    boolean realizarEntrega(String direccion, Moto moto, long tiempoEstimado) throws IOException, InterruptedException, ApiException;

}
