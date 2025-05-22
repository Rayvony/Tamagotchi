package com.tallerwebi.presentacion;


import com.tallerwebi.dominio.ServicioMascotaImp;

import com.tallerwebi.dominio.Usuario;
import com.tallerwebi.dominio.excepcion.MascotaExistenteExcepction;
import org.junit.jupiter.api.Test;
import java.time.Duration;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;


public class ServicioMascotaImpTest {

    ServicioMascotaImp servicioMascota = new ServicioMascotaImp();

    @Test
    public void dadoQueSeCreaUnaMascotaSuHigieneSeIniciaEnCien() throws MascotaExistenteExcepction {
        assertEquals(100.0, servicioMascota.crearMascota("Charly", new Usuario()).getHigiene());
    }

    @Test
    public void dadoQueLaMascotaSeHigienizaSeGuardiaLaHoraDeUltimaHigiene() throws MascotaExistenteExcepction {
        MascotaDTO mascota = servicioMascota.crearMascota("Charly", new Usuario());

        servicioMascota.higienizarMascota(mascota);

        LocalDateTime horaUltimaHigiene = mascota.getUltimaHigiene();
        LocalDateTime ahora = LocalDateTime.now();

        long diferencia = Duration.between(horaUltimaHigiene, ahora).toSeconds();

        assertTrue(diferencia >= 0 && diferencia < 4, "La diferencia en segundos debe ser minima para garantizar que se almaceno la ultima higiene correctamente");


    }

    @Test
    public void dadoQuePasaElTiempoLaHigieneDisminuye(){
        MascotaDTO mascota = new MascotaDTO("Charly");
        LocalDateTime horarioDePrueba = LocalDateTime.now().plusHours(3);

        double higieneActual = servicioMascota.calcularHigiene(mascota, horarioDePrueba);

        assertTrue(higieneActual < mascota.getHigiene(), "Luego de unas horas, la higiene inicial deberia disminuir");

    }

    @Test
    public void dadoQuePasaMuchoTiempoLaHigieneDisminuyePeroNoEsMenorQueCero(){
        MascotaDTO mascota = new MascotaDTO("Charly");
        LocalDateTime horarioDePrueba = LocalDateTime.now().plusDays(10);

        double higieneActual = servicioMascota.calcularHigiene(mascota, horarioDePrueba);

        assertEquals(0.0, higieneActual, "Luego de un año desde la ultima higiene, la higiene actual no es menor que cero");

    }





}
