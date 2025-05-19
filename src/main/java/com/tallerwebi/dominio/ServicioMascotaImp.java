package com.tallerwebi.dominio;

import com.tallerwebi.presentacion.MascotaDTO;

public class ServicioMascotaImp {

    public MascotaDTO crearMascota(String nombre) {
        return new MascotaDTO(nombre);
    }

}
