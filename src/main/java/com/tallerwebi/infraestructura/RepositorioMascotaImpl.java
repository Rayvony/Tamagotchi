package com.tallerwebi.infraestructura;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tallerwebi.dominio.RepositorioMascota;
import com.tallerwebi.dominio.entidades.Mascota;
import com.tallerwebi.dominio.entidades.Usuario;

@Repository
public class RepositorioMascotaImpl implements RepositorioMascota {

    private SessionFactory sessionFactory;

    @Autowired
    public RepositorioMascotaImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Boolean crearMascota(Mascota mascota, Usuario usuario) {
        this.sessionFactory.getCurrentSession().save(mascota);
        return true;
    }

    /* CAPAZ NO HACE FALTA POR LAS NOTACIONES
    @Override
    public void guardarMascotaEnUsuario(Mascota mascota, Usuario usuario) {
        String hql = "UPDATE Usuario SET nombre = :nombre FROM Carta WHERE id = :id ";
        Query query = this.sessionFactory.getCurrentSession().createQuery(hql);

    }*/


    
}
