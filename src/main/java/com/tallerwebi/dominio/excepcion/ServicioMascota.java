package com.tallerwebi.dominio.excepcion;

import com.tallerwebi.presentacion.MascotaDTO;

public interface ServicioMascota {

    MascotaDTO crearMascota(String nombre);
    MascotaDTO jugar(MascotaDTO mascota);
    }
