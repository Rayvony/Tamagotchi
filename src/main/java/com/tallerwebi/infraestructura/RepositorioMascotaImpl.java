package com.tallerwebi.infraestructura;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tallerwebi.dominio.RepositorioMascota;
import com.tallerwebi.dominio.entidades.Mascota;

@Repository
public class RepositorioMascotaImpl implements RepositorioMascota {

    private SessionFactory sessionFactory;

    @Autowired
    public RepositorioMascotaImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Boolean crear(Mascota mascota) {
        this.sessionFactory.getCurrentSession().save(mascota);
        return true;
    }
    
}
