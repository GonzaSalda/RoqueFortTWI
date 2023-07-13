package ar.edu.grupoesfera.cursospring.servicios;

import ar.edu.grupoesfera.cursospring.modelo.CompraCola;
import ar.edu.grupoesfera.cursospring.modelo.Moto;
import com.google.maps.errors.ApiException;

import java.io.IOException;
import java.util.List;
import java.util.Queue;

public interface ServicioDelivery {

    boolean realizarEntrega(String direccion, Moto moto, long tiempoEstimado) throws IOException, InterruptedException, ApiException;

    void agregarCompraCola(CompraCola compra);

    List<CompraCola> obtenerPedidosEnCola();

    void eliminarPedidoEnCola(CompraCola compra);

    CompraCola obtenerUltimaCompraCola();
}
