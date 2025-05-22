package com.tallerwebi.dominio;

import org.springframework.beans.factory.annotation.Autowired;

import com.tallerwebi.dominio.excepcion.MascotaExistenteExcepction;
import com.tallerwebi.presentacion.MascotaDTO;

public class ServicioMascotaImp implements ServicioMascota {

    /*
     * private RepositorioMascota repositorioMascota;
     * 
     * @Autowired
     * public ServicioMascotaImp(RepositorioMascota repositorioMascota) {
     * this.repositorioMascota = repositorioMascota;
     * }
     */

    public MascotaDTO crearMascota(String nombre, Usuario usuario) throws MascotaExistenteExcepction {

        //CREO QUE ES UNA FORMA FORZADA DE VERIFICAR, PORQUE NO ESTOY CONSULTANDO CON EL REPOSITORIO PARA QUE 
        //OBTENGA EL DATO DESDE LA BASE DE DATOS... 
        
        if (usuario.getMascota() != null) {
            throw new MascotaExistenteExcepction("Ya contas con tu mascota, solo debes hacer click en jugar");
        } else {
            MascotaDTO mascota = new MascotaDTO(nombre);
            usuario.setMascota(mascota);
            return usuario.getMascota();
        }

    }

    public void alimentarMascota(MascotaDTO mascota) {
        Integer hambreActualizada = mascota.getHambre() - 25;
        mascota.setHambre(hambreActualizada);
    }

}
