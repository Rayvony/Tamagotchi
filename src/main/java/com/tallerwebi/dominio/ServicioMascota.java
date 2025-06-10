package com.tallerwebi.dominio;


import com.tallerwebi.dominio.entidades.Usuario;
import com.tallerwebi.presentacion.MascotaDTO;


public interface ServicioMascota {
    MascotaDTO crearMascota(String nombre, Usuario usuario);
}
