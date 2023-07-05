package ar.edu.grupoesfera.cursospring.servicios;

import ar.edu.grupoesfera.cursospring.dao.MotoDao;
import ar.edu.grupoesfera.cursospring.dao.UsuarioDao;
import ar.edu.grupoesfera.cursospring.modelo.Moto;
import com.google.maps.errors.ApiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
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
    LocalTime horaSalidaPlanificada = LocalTime.now(); // Obtener la hora actual como la hora de salida planificada
    @Override
    public boolean realizarEntrega(String direccion, Moto moto, long tiempoEstimado) throws IOException, InterruptedException, ApiException {
        if (moto != null) {
            moto.setHorarioSalidaPlanificado(horaSalidaPlanificada);
            ExecutorService executor = Executors.newSingleThreadExecutor();
            executor.execute(() -> {
                try {
                    TimeUnit.MILLISECONDS.sleep(tiempoEstimado);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                moto.setDisponible(true);
                moto.setHorarioSalida(LocalTime.now()); // Asignar la hora de salida actual
                motoDao.actualizarMoto(moto);
            });
            executor.shutdown();
            return true;
        } else {
            return false;
        }
    }
}
