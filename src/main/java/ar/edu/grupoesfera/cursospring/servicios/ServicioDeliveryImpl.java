package ar.edu.grupoesfera.cursospring.servicios;

import ar.edu.grupoesfera.cursospring.dao.MotoDao;
import ar.edu.grupoesfera.cursospring.dao.UsuarioDao;
import ar.edu.grupoesfera.cursospring.modelo.Moto;
import com.google.maps.errors.ApiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.time.*;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Service
@Transactional
public class ServicioDeliveryImpl implements ServicioDelivery{

    @Autowired
    private MotoDao motoDao;

    @Autowired
    private ServicioGoogleMaps servicioGoogleMaps;
    @Override
    public boolean realizarEntrega(String direccion, Moto moto, long tiempoEstimado) throws IOException, InterruptedException, ApiException {
        LocalTime horaSalidaPlanificada = LocalTime.now();
        moto.setHorarioSalidaPlanificado(horaSalidaPlanificada);
        moto.setHorarioEntrega(null);
        moto.setHorarioVueltaNegocio(null);
        motoDao.actualizarMoto(moto);

        if (moto != null) {
            ExecutorService executor = Executors.newSingleThreadExecutor();
            executor.execute(() -> {
                try {
                    TimeUnit.MILLISECONDS.sleep(tiempoEstimado);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                LocalTime horarioEntrega = LocalTime.now();
                moto.setHorarioEntrega(horarioEntrega);

                Duration duracionAdicional = Duration.ofMinutes(1);
                LocalTime horarioVueltaNegocio = horarioEntrega.plus(duracionAdicional);
                moto.setHorarioVueltaNegocio(horarioVueltaNegocio);


                LocalTime horaActual = LocalTime.now();

                System.out.println("Hora actual: " + horaActual);
                System.out.println("Horario de vuelta de negocio: " + moto.getHorarioVueltaNegocio());
                if (horaActual.isBefore(moto.getHorarioVueltaNegocio())) {
                    moto.setDisponible(true);
                    motoDao.actualizarMoto(moto);
                }
                motoDao.actualizarMoto(moto);
            });
            executor.shutdown();

            return true;
        } else {
            return false;
        }
    }
}
