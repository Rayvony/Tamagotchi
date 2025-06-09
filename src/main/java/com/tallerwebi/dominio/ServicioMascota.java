package com.tallerwebi.dominio;

import com.tallerwebi.dominio.entidades.Usuario;
import com.tallerwebi.presentacion.MascotaDTO;

public interface ServicioMascota {

    public MascotaDTO crearMascota(String nombre,Usuario usuario);
    public MascotaDTO jugar(MascotaDTO mascota);
    public MascotaDTO alimentar(MascotaDTO mascota);
    public void actualizarHambreDeMascotas();
}
