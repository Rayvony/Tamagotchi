package com.tallerwebi.dominio;

import com.tallerwebi.dominio.entidades.Mascota;
import com.tallerwebi.dominio.entidades.Usuario;

public interface RepositorioMascota {
    public Boolean crearMascota(Mascota mascota, Usuario usuario);
    /* CAPAZ NO HACE FALTA POR LAS NOTACIONES
    public void guardarMascotaEnUsuario(Mascota mascota, Usuario usuario);*/
}
