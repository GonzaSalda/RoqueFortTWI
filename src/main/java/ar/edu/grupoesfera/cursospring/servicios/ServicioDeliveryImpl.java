package ar.edu.grupoesfera.cursospring.servicios;

import ar.edu.grupoesfera.cursospring.dao.MotoDao;
import ar.edu.grupoesfera.cursospring.dao.UsuarioDao;
import ar.edu.grupoesfera.cursospring.modelo.Moto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Service
@Transactional
public class ServicioDeliveryImpl implements ServicioDelivery{

    @Autowired
    private MotoDao motoDao;


    public boolean realizarEntrega(String direccion, Moto moto, long tiempoEstimado) {
        if (moto != null) {
            System.out.println("Se asignó la moto " + moto.getId() + " para entregar el pedido a la dirección " + direccion);
            // Ejecutar el proceso de entrega en un hilo separado
            ExecutorService executor = Executors.newSingleThreadExecutor();
            executor.execute(() -> {
                // Simular el tiempo de entrega de 60 segundos
                try {
                    TimeUnit.MILLISECONDS.sleep(tiempoEstimado);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                // Actualizar el estado de la moto y volverla disponible nuevamente
                moto.setDisponible(true);
                motoDao.actualizarMoto(moto);

                System.out.println("La entrega ha sido realizada con éxito");
            });

            // Finalizar el executor
            executor.shutdown();
            return true;
        } else {
            System.out.println("No hay motos disponibles para realizar la entrega");
            return false;
        }
    }
}
