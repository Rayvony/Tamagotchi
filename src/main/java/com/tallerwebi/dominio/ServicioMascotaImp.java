package com.tallerwebi.dominio;

import com.tallerwebi.dominio.entidades.Mascota;
import com.tallerwebi.dominio.excepcion.EnergiaInsuficiente;
import com.tallerwebi.dominio.excepcion.EnergiaMaxima;
import com.tallerwebi.presentacion.MascotaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ServicioMascotaImp implements ServicioMascota {

    private RepositorioMascota repositorioMascota;

    @Autowired
    public ServicioMascotaImp(RepositorioMascota repositorioMascota) {
        this.repositorioMascota = repositorioMascota;
    }

    @Override
    public MascotaDTO crear(MascotaDTO mascota) {
        MascotaDTO mascotaCreada = this.traerUnaMascota(this.repositorioMascota.crear(mascota.obtenerEntidad()));
        return mascotaCreada;
    }

    @Override
    public List<Mascota> traerMascotas() {
        return this.repositorioMascota.obtenerListaDeMascotas();
    }

    @Override
    public MascotaDTO traerUnaMascota(Long id) {

        MascotaDTO mascota = new MascotaDTO(this.repositorioMascota.obtenerPor(id));
        return mascota;
    }

    @Override
    public void actualizarMascota(MascotaDTO mascota) {
        this.repositorioMascota.actualizar(mascota.obtenerEntidad());
    }

    public MascotaDTO crearMascota(String nombre) {
        return new MascotaDTO(nombre);
    }

    public MascotaDTO jugar(MascotaDTO mascota) {
        Double energiaADescontarPorJuego = 25.00;
        Double energiaActual = mascota.getEnergia();
        if (energiaActual >= energiaADescontarPorJuego) {
            mascota.setEnergia(energiaActual - energiaADescontarPorJuego);

            //actualizamos en base de datos
            this.actualizarMascota(mascota);
        } else {
            throw new EnergiaInsuficiente("No podés jugar, te falta energía");
        }

        return mascota;
    }

    @Override
    public MascotaDTO dormir(MascotaDTO mascota) throws EnergiaMaxima {
        Double energiaASumar = 25.00;
        Double energiaActual = mascota.getEnergia();
        if (energiaActual < 100.00) {
            mascota.setEnergia(Math.min(100.00, energiaActual + energiaASumar));
            mascota.setUltimaSiesta(LocalDateTime.now());
            //actualizamos en base de datos
            this.actualizarMascota(mascota);
        } else {
            throw new EnergiaMaxima("No se puede dormir porque no tiene sueño");
        }

        return mascota;
    }
}
