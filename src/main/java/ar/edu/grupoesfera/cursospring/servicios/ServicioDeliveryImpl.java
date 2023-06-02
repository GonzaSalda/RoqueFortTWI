package ar.edu.grupoesfera.cursospring.servicios;

import ar.edu.grupoesfera.cursospring.dao.MotoDao;
import ar.edu.grupoesfera.cursospring.dao.UsuarioDao;
import ar.edu.grupoesfera.cursospring.modelo.Moto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ServicioDeliveryImpl implements ServicioDelivery{

    @Autowired
    private MotoDao motoDao;


    public boolean realizarEntrega(String direccion) {
        List<Moto> motosDisponibles = motoDao.obtenerListaTotalMoto();
        if (!motosDisponibles.isEmpty()) {
            Moto moto = motosDisponibles.get(0);
            System.out.println("Se asignó la moto " + moto.getId() + " para entregar el pedido a la dirección " + direccion);
           System.out.println("motosDisponibles" + motosDisponibles);
            return true;
        } else {
            System.out.println("No hay motos disponibles para realizar la entrega");
            return false;
        }
    }

}
