package com.tallerwebi.dominio;

import com.tallerwebi.dominio.excepcion.MascotaExistenteExcepction;
import com.tallerwebi.presentacion.MascotaDTO;

import java.time.LocalDate;
import java.time.LocalDateTime;

public interface ServicioMascota {

    public MascotaDTO crearMascota(String nombre, Usuario usuario) throws MascotaExistenteExcepction;

    Double verHigiene(MascotaDTO mascota);
    void higienizarMascota(MascotaDTO mascota);
    void actualizarHigiene(MascotaDTO mascota);
    double calcularHigiene(MascotaDTO mascota, LocalDateTime horarioAComparar);
}
