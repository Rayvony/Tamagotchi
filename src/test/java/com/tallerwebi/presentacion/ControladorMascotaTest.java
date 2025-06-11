package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.ServicioMascota;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

import com.tallerwebi.dominio.ServicioMascotaImp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class ControladorMascotaTest {
    private ServicioMascota servicioMascotaMock;
    private ControladorMascota controladorMascota;

    @BeforeEach
    public void inicializar() {
        servicioMascotaMock = mock(ServicioMascota.class);
        controladorMascota = new ControladorMascota(servicioMascotaMock);
    }


    @Test
    public void queSePuedaCrearUnaMascotaConUnNombre(){

        //Usuario usuarioPrueba = new Usuario();
        String nombreMascota = "Firulais";
        MascotaDTO mascotaDTOPrueba = new MascotaDTO(nombreMascota);
        when(this.servicioMascotaMock.crearMascota(anyString())).thenReturn(mascotaDTOPrueba);

        ModelAndView modelAndView = controladorMascota.crearMascota(nombreMascota);

        String vistaEsperada = "mascota";

        assertThat(vistaEsperada, equalTo(modelAndView.getViewName()));

    }



}
