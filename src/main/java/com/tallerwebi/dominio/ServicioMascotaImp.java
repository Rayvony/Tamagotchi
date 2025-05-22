package com.tallerwebi.dominio;

import org.springframework.beans.factory.annotation.Autowired;

import com.tallerwebi.dominio.excepcion.MascotaExistenteExcepction;
import com.tallerwebi.presentacion.MascotaDTO;

import java.time.Duration;
import java.time.LocalDateTime;


public class ServicioMascotaImp implements ServicioMascota {

    /*
     * private RepositorioMascota repositorioMascota;
     *
     * @Autowired
     * public ServicioMascotaImp(RepositorioMascota repositorioMascota) {
     * this.repositorioMascota = repositorioMascota;
     * }
     */

    public MascotaDTO crearMascota(String nombre, Usuario usuario) throws MascotaExistenteExcepction {

        //CREO QUE ES UNA FORMA FORZADA DE VERIFICAR, PORQUE NO ESTOY CONSULTANDO CON EL REPOSITORIO PARA QUE
        //OBTENGA EL DATO DESDE LA BASE DE DATOS...

        if (usuario.getMascota() != null) {
            throw new MascotaExistenteExcepction("Ya contas con tu mascota, solo debes hacer click en jugar");
        } else {
            MascotaDTO mascota = new MascotaDTO(nombre);
            usuario.setMascota(mascota);
            return usuario.getMascota();
        }

    }


    @Override
    public void higienizarMascota(MascotaDTO mascota) {
        mascota.setHigiene(100.0);
        mascota.setUltimaHigiene(LocalDateTime.now());
    }

    @Override
    public void actualizarHigiene(MascotaDTO mascota) {
        mascota.setHigiene(calcularHigiene(mascota, LocalDateTime.now()));
    }

    @Override
    public double calcularHigiene(MascotaDTO mascota, LocalDateTime horarioAComparar) {
        double minutos = (double) Duration.between(mascota.getUltimaHigiene(), horarioAComparar).toMinutes();
        double higienePerdida = minutos * 0.09;
        double higieneActual = mascota.getHigiene() - higienePerdida;

        return Math.max(higieneActual, 0.0);
    }

    @Override
    public Double verHigiene(MascotaDTO mascota) {
        return mascota.getHigiene();
    }

    public MascotaDTO jugar(MascotaDTO mascota) {

        mascota.jugar();

        return mascota;
    }
}
