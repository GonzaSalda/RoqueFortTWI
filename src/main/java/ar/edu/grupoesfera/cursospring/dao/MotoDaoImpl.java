package ar.edu.grupoesfera.cursospring.dao;

import ar.edu.grupoesfera.cursospring.modelo.Moto;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
@Transactional
@Repository
public class MotoDaoImpl implements MotoDao{

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Moto> obtenerListaTotalMoto() {
        Session sesion = sessionFactory.getCurrentSession();
        List<Moto> lista_moto = sesion.createCriteria(Moto.class).list();
        return lista_moto;
    }

    @Override
    public Moto obtenerMotoPorID(Long id) {
        Session sesion = sessionFactory.getCurrentSession();
        Moto moto = sesion.get(Moto.class, id);
        return moto;
    }

    @Override
    public void actualizarMoto(Moto moto) {
        sessionFactory.getCurrentSession().update(moto);
    }


    @Override
    public Moto findMotoDisponible() {
        Session sesion = sessionFactory.getCurrentSession();
        List<Moto> lista_moto = sesion.createCriteria(Moto.class).list();
        for (Moto moto : lista_moto) {
            if (moto.isDisponible()) {
                return moto;
            }
        }
        return null;
    }
}
