package com.tallerwebi.infraestructura;

import com.tallerwebi.dominio.RepositorioMascota;
import com.tallerwebi.dominio.entidades.Mascota;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;

@Repository
public class RepositorioMascotaImpl implements RepositorioMascota {

    private SessionFactory sessionFactory;

    @Autowired
    public RepositorioMascotaImpl(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Boolean crear(Mascota mascota) {
        this.sessionFactory.getCurrentSession().save(mascota);
        return true;
    }

    @Override
    public Mascota obtenerPor(Long id) {
        String hql = "FROM Mascota WHERE id = :id";
        Query query = this.sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter("id", id);
        return (Mascota)query.getSingleResult();
    }

    @Override
    public List<Mascota> obtenerListaDeMascotas() {
        String hql = "FROM Mascota";
        Query query = this.sessionFactory.getCurrentSession().createQuery(hql);
        return query.getResultList();
    }

    @Override
    public void actualizar(Mascota mascota) {
        String hql = "UPDATE Mascota SET nombre = :nombre, energia = :energia WHERE id = :id ";
        Query query = this.sessionFactory.getCurrentSession().createQuery(hql);

        query.setParameter("id", mascota.getId());
        query.setParameter("energia", mascota.getEnergia());
        query.setParameter("nombre", mascota.getNombre());
        int cantidadDeActualizaciones = query.executeUpdate();

        if(cantidadDeActualizaciones > 1){
            // rollback
            // throw new MuchosRegistrosAfectados("Actualizo mas de uno");
        }
    }
}
