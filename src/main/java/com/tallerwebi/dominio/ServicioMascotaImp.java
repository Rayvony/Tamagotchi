package com.tallerwebi.dominio;

import java.time.LocalDateTime;

import org.springframework.cglib.core.Local;
import org.springframework.scheduling.annotation.Scheduled;

import com.tallerwebi.dominio.excepcion.MascotaExistente;
import com.tallerwebi.dominio.excepcion.MascotaHambrientaException;
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
        Mascota mascotaEntidad = null;

        if (mascota.getHambre() >= 75.0) {
            throw new MascotaHambrientaException("No podés jugar, tu mascota esta hambrienta");
        } else {
            mascota.jugar();
            mascotaEntidad = mascota.aEntidad();
            mascotaEntidad.jugar();
        }

        return mascota;
    }

    @Override
    public MascotaDTO alimentar(MascotaDTO mascota) {
        
        mascota.alimentar();
        mascota.setUltimaAlimentacion(LocalDateTime.now());

        return mascota;
    }

    @Override
   @Scheduled(cron = "0 0 * * * *") 
    public void actualizarHambreDeMascotas()  {
    // buscar todas las mascotas
    // calcular si pasó el tiempo suficiente desde la última alimentación
    // aumentar el hambre si corresponde
    // guardar los cambios
    }
}
