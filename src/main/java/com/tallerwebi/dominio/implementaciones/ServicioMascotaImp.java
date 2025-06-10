package com.tallerwebi.dominio.implementaciones;



import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tallerwebi.dominio.RepositorioMascota;
import com.tallerwebi.dominio.ServicioMascota;
import com.tallerwebi.dominio.entidades.Mascota;
import com.tallerwebi.dominio.entidades.Usuario;
import com.tallerwebi.presentacion.MascotaDTO;

@Service
@Transactional
public class ServicioMascotaImp implements ServicioMascota {
    
    private RepositorioMascota repositorioMascota;

    @Autowired
    public ServicioMascotaImp(RepositorioMascota repositorioMascota) {
        this.repositorioMascota = repositorioMascota;
    }

    @Override
    public MascotaDTO crearMascota(String nombre, Usuario usuario) {
            Mascota mascota = new Mascota(nombre);
            MascotaDTO mascotaDTO = mascota.aDTO();
            this.repositorioMascota.crearMascota(mascota, usuario);
            return mascotaDTO; 
    }
}
