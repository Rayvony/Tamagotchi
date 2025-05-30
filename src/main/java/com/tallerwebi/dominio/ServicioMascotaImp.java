package com.tallerwebi.dominio;

import com.tallerwebi.dominio.excepcion.ServicioMascota;
import com.tallerwebi.presentacion.MascotaDTO;
import org.springframework.stereotype.Service;

@Service("servicioMascota")
public class ServicioMascotaImp implements ServicioMascota {

    public MascotaDTO crearMascota(String nombre) {
        return new MascotaDTO(nombre);
    }

    public MascotaDTO jugar(MascotaDTO mascota) {

        mascota.jugar();

        return mascota;
    }
}
