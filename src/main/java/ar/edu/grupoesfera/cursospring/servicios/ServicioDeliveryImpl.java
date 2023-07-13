package ar.edu.grupoesfera.cursospring.servicios;

import ar.edu.grupoesfera.cursospring.dao.CompraColaDao;
import ar.edu.grupoesfera.cursospring.dao.MotoDao;
import ar.edu.grupoesfera.cursospring.dao.UsuarioDao;
import ar.edu.grupoesfera.cursospring.modelo.CompraCola;
import ar.edu.grupoesfera.cursospring.modelo.Moto;
import com.google.maps.errors.ApiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.time.*;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Service
@Transactional
public class ServicioDeliveryImpl implements ServicioDelivery{

    @Autowired
    private MotoDao motoDao;

    @Autowired
    private ServicioMoto servicioMoto;

    @Autowired
    private ServicioGoogleMaps servicioGoogleMaps;

    @Autowired
    private CompraColaDao compraColaDao;


    @Override
    public List<CompraCola> obtenerPedidosEnCola() {
        return compraColaDao.obtenerPedidosEnCola();
    }

    @Override
    public void eliminarPedidoEnCola(CompraCola compra) {
        compraColaDao.eliminarCompraCola(compra);
    }

    @Override
    public void agregarCompraCola(CompraCola compra) {
        compraColaDao.agregarCompraCola(compra);
    }



    public CompraCola obtenerUltimaCompraCola() {
        return compraColaDao.obtenerUltimaCompraCola();
    }


    @Override
    public boolean realizarEntrega(String direccion, Moto moto, long tiempoEstimado) throws IOException, InterruptedException, ApiException {
        if (moto != null) {
            moto.setHorarioEntrega(null);
            moto.setHorarioVueltaNegocio(null);
            motoDao.actualizarMoto(moto);
            LocalTime horaSalidaPlanificada = LocalTime.now();
            moto.setHorarioSalidaPlanificado(horaSalidaPlanificada);

            moto.setDisponible(false); // Marcar como no disponible

            LocalTime horarioEntrega = horaSalidaPlanificada.plusMinutes(tiempoEstimado);
            moto.setHorarioEntrega(horarioEntrega);

            LocalTime horarioVueltaNegocio = horarioEntrega.plusMinutes(tiempoEstimado);
            moto.setHorarioVueltaNegocio(horarioVueltaNegocio);
            motoDao.actualizarMoto(moto);

            ExecutorService executor = Executors.newSingleThreadExecutor();
            executor.execute(() -> {
                try {
                    List<CompraCola> pedidosEnCola = compraColaDao.obtenerPedidosEnCola();
                    if (!pedidosEnCola.isEmpty()) {
                        CompraCola compra = pedidosEnCola.remove(0);
                        compraColaDao.eliminarCompraCola(compra);
                    }
                    long tiempoEstimadoEnMilisegundos = tiempoEstimado * 60000; // Convertir minutos a milisegundos
                    TimeUnit.MILLISECONDS.sleep(tiempoEstimadoEnMilisegundos);
                    moto.setDisponible(true);
                    motoDao.actualizarMoto(moto);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            executor.shutdown();
            return true;
        } else {
            return false;
        }
    }

}
