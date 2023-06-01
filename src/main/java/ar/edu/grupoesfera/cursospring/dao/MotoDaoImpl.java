package ar.edu.grupoesfera.cursospring.dao;

import ar.edu.grupoesfera.cursospring.modelo.Moto;
import ar.edu.grupoesfera.cursospring.modelo.Pizza;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

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
}
