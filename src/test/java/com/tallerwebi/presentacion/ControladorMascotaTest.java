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





}
