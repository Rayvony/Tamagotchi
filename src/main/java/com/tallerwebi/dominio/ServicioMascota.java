package com.tallerwebi.dominio;

import com.tallerwebi.dominio.excepcion.MascotaExistenteExcepction;
import com.tallerwebi.presentacion.MascotaDTO;

public interface ServicioMascota {

     public MascotaDTO crearMascota(String nombre, Usuario usuario) throws MascotaExistenteExcepction;
     public void alimentarMascota(MascotaDTO mascota);
}
