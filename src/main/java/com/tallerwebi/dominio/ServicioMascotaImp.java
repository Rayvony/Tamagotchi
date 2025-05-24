package com.tallerwebi.dominio;

import com.tallerwebi.dominio.excepcion.MascotaExistente;
import com.tallerwebi.presentacion.MascotaDTO;

public class ServicioMascotaImp implements ServicioMascota {

    @Override
    public MascotaDTO crearMascota(String nombre, Usuario usuario) {
        if (usuario.getMascota() != null) {
            throw new MascotaExistente("Ya contas con tu mascota, debes hacer click en jugar");
        } else {
            Mascota mascota = new Mascota(nombre);
            usuario.setMascota(mascota);
        }
        
        return new MascotaDTO(nombre);
    }

    @Override
    public MascotaDTO jugar(MascotaDTO mascota) {
        // TAMBIEN DEBE JUGAR CON LA MASCOTA REAL
        mascota.jugar();

        return mascota;
    }
}
