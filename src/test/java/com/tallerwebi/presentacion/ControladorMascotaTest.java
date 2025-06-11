package com.tallerwebi.presentacion;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.servlet.ModelAndView;

import com.tallerwebi.dominio.ServicioMascota;
import com.tallerwebi.dominio.entidades.Usuario;

public class ControladorMascotaTest {
    private ServicioMascota servicioMascotaMock;
    private ControladorMascota controladorMascota;

    @BeforeEach 
    public void inicializar() {
        servicioMascotaMock = mock(ServicioMascota.class);
        controladorMascota = new ControladorMascota(servicioMascotaMock);
    }

    @Test
    public void queUnUsuarioCreeSuMascotaConUnNombre(){
        
        Usuario usuarioPrueba = new Usuario();
        String nombreMascota = "Firulais";
        MascotaDTO mascotaDTOPrueba = new MascotaDTO(nombreMascota);
        when(this.servicioMascotaMock.crearMascota(anyString(), any(Usuario.class))).thenReturn(mascotaDTOPrueba);

        ModelAndView modelAndView = controladorMascota.crearMascota(nombreMascota, usuarioPrueba);

        String vistaEsperada = "redirect:/mascota";
       
        assertThat(vistaEsperada, equalTo(modelAndView.getViewName()));

    }
}
