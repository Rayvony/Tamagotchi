package com.tallerwebi.presentacion;

import org.springframework.web.servlet.ModelAndView;

import com.tallerwebi.dominio.ServicioMascotaImp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;


public class ControladorMascotaTest {
    
    private ServicioMascotaImp servicioMascota;
    private ControladorMascota controladorMascota;

    @BeforeEach 
    public void inicializar(){
        servicioMascota = new ServicioMascotaImp();
        controladorMascota = new ControladorMascota(servicioMascota);
    }

    @Test
    public void queSePuedaCrearUnaMascotaConSuNombre(){
        //  PREPARACION

        //Simulando que ingresa el inpput del nombre de la mascota
        MascotaDTO mascota = new MascotaDTO("nombreCualquiera");

        // EJECUCION 
        ModelAndView modelAndView = controladorMascota.crearMascota(mascota.getNombre());

        // VERIFICACION
        String vistaEsperada = "mascota";
        String nombreMascotaEsperado = "nombreCualquiera";

        assertThat(vistaEsperada,equalTo(modelAndView.getViewName()));
        assertThat(nombreMascotaEsperado, equalTo(modelAndView.getModel().get("nombre")));
    }
    /*
    @Test
    public void queUnUsuarioConMascotaNoPuedaCrearOtra(){

    }*/

    @Test
    public void queAlCrearseUnaMascotaTengaCienDeEnergiaAsignadaPorDefecto(){
        //  PREPARACION
        MascotaDTO mascota = new MascotaDTO("tamagotcha");
        String vistaEsperada = "mascota";
        Double energiaEsperada = 100.00;

        // EJECUCION
        ModelAndView modelAndView = controladorMascota.crearMascota(mascota.getNombre());

        // VERIFICACION
        assertThat(modelAndView.getViewName(),equalTo(vistaEsperada));
        assertThat(modelAndView.getModel().get("energia"), equalTo(energiaEsperada));
    }

    @Test
    public void queAlJugarSeDescuenten25PuntosDeEnergia(){
        //  PREPARACION
        String vistaEsperada = "mascota";
        Double energiaEsperada = 75.00;

        MascotaDTO mascota = new MascotaDTO("tamagotcha");

        // EJECUCION
        ModelAndView modelAndView = controladorMascota.jugar(mascota);

        // VERIFICACION
        assertThat(modelAndView.getViewName(),equalTo(vistaEsperada));
        assertThat(modelAndView.getModel().get("energia"), equalTo(energiaEsperada));
    }

    @Test
    public void quelIntentarJugarSinEnergiaSuficienteDevueltaLaVistaConUnMensajeDeError(){
        //  PREPARACION
        MascotaDTO mascota = new MascotaDTO("tamagotcha");
        String vistaEsperada = "mascota";
        Double energiaEsperada = 0.00;
        String mensajeDEErrorEsperado = "No podés jugar, te falta energía";

        // EJECUCION
        controladorMascota.jugar(mascota);
        controladorMascota.jugar(mascota);
        controladorMascota.jugar(mascota);
        controladorMascota.jugar(mascota);

        ModelAndView modelAndView = controladorMascota.jugar(mascota);
        // VERIFICACION

        assertThat(modelAndView.getViewName(),equalTo(vistaEsperada));
        assertThat(modelAndView.getModel().get("energia"), equalTo(energiaEsperada));
        assertThat(modelAndView.getModel().get("error"), equalTo(mensajeDEErrorEsperado));

    }




}
