package ar.edu.grupoesfera.cursospring.servicios;

import ar.edu.grupoesfera.cursospring.dao.MotoDao;
import ar.edu.grupoesfera.cursospring.modelo.Moto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ServicioMotoImpl implements ServicioMoto{

    @Autowired
    private MotoDao motoDao;

    public Moto asignarMotoDisponible() {
        // Lógica para encontrar una moto disponible
        Moto motoDisponible = motoDao.findMotoDisponible();
        if (motoDisponible != null) {
            motoDisponible.setDisponible(false);
            motoDao.actualizarMoto(motoDisponible);
        }
        return motoDisponible;
    }

    public List<Moto> obtenerMotos() {
        return motoDao.findAll();
    }
}
