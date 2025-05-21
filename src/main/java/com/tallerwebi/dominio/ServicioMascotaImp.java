package com.tallerwebi.dominio;

import com.tallerwebi.presentacion.MascotaDTO;

import java.time.Duration;
import java.time.LocalDateTime;

public class ServicioMascotaImp {

    public MascotaDTO crearMascota(String nombre) {
        return new MascotaDTO(nombre);
    }


    public void higienizarMascota(MascotaDTO mascota) {
        mascota.setHigiene(100.0);
        mascota.setUltimaHigiene(LocalDateTime.now());
    }

    public void actualizarHigiene(MascotaDTO mascota) {
        mascota.setHigiene(calcularHigiene(mascota, LocalDateTime.now()));
    }

    public double calcularHigiene(MascotaDTO mascota, LocalDateTime horarioAComparar) {
        double minutos = (double) Duration.between(mascota.getUltimaHigiene(), horarioAComparar).toMinutes();
        double higienePerdida = minutos * 0.09;
        double higieneActual = mascota.getHigiene() - higienePerdida;

        return Math.max(higieneActual, 0.0);
    }


}
