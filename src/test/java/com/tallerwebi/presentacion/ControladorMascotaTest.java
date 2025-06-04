package com.tallerwebi.presentacion;

import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

import com.tallerwebi.dominio.Mascota;
import com.tallerwebi.dominio.ServicioMascotaImp;
import com.tallerwebi.dominio.Usuario;
import com.tallerwebi.dominio.excepcion.EnergiaInsuficiente;
import com.tallerwebi.dominio.excepcion.MascotaExistente;
import com.tallerwebi.dominio.excepcion.MascotaHambrientaException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.instanceOf;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class ControladorMascotaTest {

    private ServicioMascotaImp servicioMascotaMock;
    private ControladorMascota controladorMascota;
    private Usuario usuarioMock;
    private MascotaDTO mascotaMock;

    @BeforeEach
    public void inicializar() {
        servicioMascotaMock = mock(ServicioMascotaImp.class);
        controladorMascota = new ControladorMascota(servicioMascotaMock);
        usuarioMock = mock(Usuario.class);
        mascotaMock = mock(MascotaDTO.class);
    }

    @Test
    public void queUnUsuarioCreeSuMascotaConUnNombre() {
        // PREPARACION

        // Simulando que ingresa el inpput del nombre de la mascota
        Mascota mascota = new Mascota("nombreCualquiera");
        when(usuarioMock.getMascota()).thenReturn(mascota);
        when(servicioMascotaMock.crearMascota(mascota.getNombre(), usuarioMock)).thenReturn(mascota.aDTO());

        // EJECUCION
        ModelAndView modelAndView = controladorMascota.crearMascota(mascota.getNombre(), usuarioMock);

        // VERIFICACION
        String vistaEsperada = "mascota";
        String nombreMascotaEsperado = "nombreCualquiera";

        assertThat(vistaEsperada, equalTo(modelAndView.getViewName()));
        assertThat(nombreMascotaEsperado, equalTo(modelAndView.getModel().get("nombre")));
    }
    
     @Test
     public void queUnUsuarioConMascotaNoPuedaCrearOtra(){
        // PREPARACION
        Mascota mascota = new Mascota("nombreCualquiera");
        String mensajeDeErrorEsperado = "Ya contas con tu mascota, debes hacer click en jugar";

        when(usuarioMock.getMascota()).thenReturn(mascota);
        when(servicioMascotaMock.crearMascota(mascota.getNombre(), usuarioMock)).thenThrow(new MascotaExistente(mensajeDeErrorEsperado));

        // EJECUCION
        ModelAndView modelAndView = controladorMascota.crearMascota(mascota.getNombre(), usuarioMock);

        // VERIFICACION
        assertThat(modelAndView.getViewName(), equalTo("lobby"));
        assertThat(modelAndView.getModel().get("error"), equalTo(mensajeDeErrorEsperado));

     }
     
    @Test
    public void queAlCrearseUnaMascotaTengaCienDeEnergiaAsignadaPorDefecto() {
        // PREPARACION
        Mascota mascota = new Mascota("tamagotcha");
        String vistaEsperada = "mascota";
        Double energiaEsperada = 100.00;
        when(servicioMascotaMock.crearMascota(mascota.getNombre(), usuarioMock)).thenReturn(mascota.aDTO());

        // EJECUCION
        ModelAndView modelAndView = controladorMascota.crearMascota(mascota.getNombre(), usuarioMock);

        // VERIFICACION
        assertThat(modelAndView.getViewName(), equalTo(vistaEsperada));
        assertThat(modelAndView.getModel().get("energia"), equalTo(energiaEsperada));
    }

    @Test
    public void queAlJugarSeDescuenten25PuntosDeEnergia() {
        // PREPARACION
        String vistaEsperada = "mascota";
        Double energiaEsperada = 75.00;

        MascotaDTO mascota = new MascotaDTO("tamagotcha");


        // SUGERENCIA DE LA IA || PREGUNTARLE A GER | COMO EL METODO NO DEVUELVE NADA DEBE EXISTIR OTRA FORMA DE 
        // SIMULAR EL JUGAR 
        
        doAnswer(invocation -> {
            MascotaDTO m = invocation.getArgument(0);
            m.setEnergia(mascota.getEnergia()- 25.00);
            return null;
        }).when(servicioMascotaMock).jugar(mascota);

        // EJECUCION
        ModelAndView modelAndView = controladorMascota.jugar(mascota);

        // VERIFICACION
        assertThat(modelAndView.getViewName(), equalTo(vistaEsperada));
        assertThat(modelAndView.getModel().get("energia"), equalTo(energiaEsperada));
    }

    @Test
    public void quelIntentarJugarSinEnergiaSuficienteDevueltaLaVistaConUnMensajeDeError() {
        // PREPARACION
        MascotaDTO mascota = new MascotaDTO("tamagotcha");
        String vistaEsperada = "mascota";
        Double energiaEsperada = 0.00;
        String mensajeDEErrorEsperado = "No podés jugar, te falta energía";
        

        doAnswer(invocation -> {
        MascotaDTO m = invocation.getArgument(0);
        double energiaActual = m.getEnergia();
        if (energiaActual >= 25.0) {
            m.setEnergia(energiaActual - 25.0);
        } else {
            m.setEnergia(0.0);
        }
        return null;
    }).when(servicioMascotaMock).jugar(mascota);

        // EJECUCION
        controladorMascota.jugar(mascota);
        controladorMascota.jugar(mascota);
        controladorMascota.jugar(mascota);
        controladorMascota.jugar(mascota);

        when(servicioMascotaMock.jugar(mascota)).thenThrow(new EnergiaInsuficiente(mensajeDEErrorEsperado));
        ModelAndView modelAndView = controladorMascota.jugar(mascota);
        // VERIFICACION

        assertThat(modelAndView.getViewName(), equalTo(vistaEsperada));
        assertThat(modelAndView.getModel().get("energia"), equalTo(energiaEsperada));
        assertThat(modelAndView.getModel().get("error"), equalTo(mensajeDEErrorEsperado));

    }


    
    @Test
    public void queUnUsuarioPresionaAlimentarYLaMascotaDisminuyaSuHambreYRegistreSuHorarioDeAlimentacion(){
        // PREPARACION 
        MascotaDTO mascota = new MascotaDTO("tamagotcha");

        
        doAnswer(invocation -> {
            MascotaDTO m = invocation.getArgument(0);
            m.setHambre(m.getHambre() - 25.00); 
            m.setUltimaAlimentacion(LocalDateTime.now());
            return m;
        }).when(servicioMascotaMock).alimentar(mascota);

        // EJECUCION
        ModelAndView modelAndView = controladorMascota.alimentar(mascota);

        // VERIFICACION
        String vistaEsperada = "mascota";
        Double hambreEsperada = 50.00; 
        assertThat(modelAndView.getViewName(), equalTo(vistaEsperada));
        assertThat(modelAndView.getModel().get("hambre"), equalTo(hambreEsperada));
        assertThat(modelAndView.getModel().get("ultimaAlimentacion"), instanceOf(LocalDateTime.class));
    }


    @Test
    public void queAlIntentarJugarConHambreDevuelvaUnMensajeDeErrorLaVista(){
        
        // PREPARACION 
        String mensajeDeErrorEsperado = "No podés jugar, tu mascota esta hambrienta";
        MascotaDTO mascota = new MascotaDTO("tamagotcha");
        when(servicioMascotaMock.jugar(mascota)).thenThrow(new MascotaHambrientaException(mensajeDeErrorEsperado));

        // EJECUCION
        ModelAndView modelAndView = controladorMascota.jugar(mascota);

        // VERIFICACION 
        String vistaEsperada = "mascota";
        Double hambreEsperada = 75.0;

        assertThat(modelAndView.getViewName(), equalTo(vistaEsperada));
        assertThat(modelAndView.getModel().get("hambre"), equalTo(hambreEsperada));
        assertThat(modelAndView.getModel().get("error"), equalTo(mensajeDeErrorEsperado));
        
    }
}
