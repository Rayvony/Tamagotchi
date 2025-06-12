package com.tallerwebi.infraestructura;

import com.tallerwebi.dominio.RepositorioMascota;
import com.tallerwebi.dominio.entidades.Mascota;
import com.tallerwebi.infraestructura.config.HibernateInfraestructuraTestConfig;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import javax.persistence.Query;

import javax.transaction.Transactional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {HibernateInfraestructuraTestConfig.class})
@Transactional
public class RepositorioMascotaTest {
    @Autowired
    private SessionFactory sessionFactory;
    private RepositorioMascota repositorioMascota;

    @BeforeEach
    public void init() {
        this.repositorioMascota = new RepositorioMascotaImpl(this.sessionFactory);
    }

    @Test
    @Rollback
    public void cuandoCreoUnaMascotaConNombreEntoncesSeGuardaEnLaBaseDeDatos(){

        Mascota mascota = new Mascota();
        mascota.setNombre("Tamagotcha");
        this.sessionFactory.getCurrentSession().save(mascota);

        Long idMascotaGuardada = this.repositorioMascota.crear(mascota);

        String hql = "FROM Mascota WHERE nombre = :nombre";

        Query query = this.sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter("nombre", "Tamagotcha");
        Mascota obtenida = (Mascota)query.getSingleResult();

        assertThat(idMascotaGuardada, instanceOf(Long.class));
        assertThat(obtenida, equalTo(mascota));
    }

    @Test
    @Rollback
    public void dadoQueExisteUnaMascotaEnLaBDCuandoLaObtengoPorIdMeDevuelveLaMascotaCorrespondiente(){
        Mascota mascota = new Mascota();
        mascota.setNombre("Tamagotcha");
        this.sessionFactory.getCurrentSession().save(mascota);

        String hql = "FROM Mascota WHERE nombre = :nombre";
        Query query = this.sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter("nombre", "Tamagotcha");
        Mascota guardada = (Mascota)query.getSingleResult();

        Mascota obtenida = this.repositorioMascota.obtenerPor(guardada.getId());

        assertThat(obtenida, equalTo(mascota));
    }

}
