package com.tallerwebi.presentacion;

import org.springframework.web.servlet.ModelAndView;

import com.tallerwebi.dominio.ServicioMascotaImp;
import com.tallerwebi.dominio.Usuario;
import com.tallerwebi.dominio.excepcion.MascotaExistenteExcepction;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ControladorMascotaTest {

    private ServicioMascotaImp servicioMascota;
    private ControladorMascota controladorMascota;
    /*
     * private HttpServletRequest requestMock;
     * private HttpSession sessionMock;
     * private ServicioLogin servicioLoginMock;
     */
    private Usuario usuarioMock;

    @BeforeEach
    public void inicializar() {
        servicioMascota = new ServicioMascotaImp();
        controladorMascota = new ControladorMascota(servicioMascota);
        usuarioMock = mock(Usuario.class);
    }

    @Test
    public void queSePuedaCrearUnaMascotaConSuNombre() throws MascotaExistenteExcepction {
        // PREPARACION
        MascotaDTO mascota = new MascotaDTO("nombreCualquiera");
        Usuario nuevoUsuario = new Usuario();

        // EJECUCION
        ModelAndView modelAndView = controladorMascota.crearMascota(mascota.getNombre(), nuevoUsuario);

        // VERIFICACION
        String vistaEsperada = "mascota";
        String nombreMascotaEsperado = "nombreCualquiera";

        assertThat(vistaEsperada, equalTo(modelAndView.getViewName()));
        assertThat(nombreMascotaEsperado, equalTo(modelAndView.getModel().get("nombre")));

    }
    
    @Test
    public void queUnUsuarioConMascotaNoPuedaCrearOtraYDevuelvaError() throws MascotaExistenteExcepction {
        // PREPARACION
        MascotaDTO mascota = new MascotaDTO("nombreCualquiera");
        MascotaDTO mascotaNueva = new MascotaDTO("otroNombre");
        when(usuarioMock.getMascota()).thenReturn(mascota);

        // EJECUCION
        ModelAndView modelAndView = controladorMascota.crearMascota(mascotaNueva.getNombre(), usuarioMock);

        // VERIFICACION
        String vistaEsperada = "lobby";
        String mensajeError = "Ya contas con tu mascota, solo debes hacer click en jugar";

        assertThat(vistaEsperada, equalTo(modelAndView.getViewName()));
        assertThat(modelAndView.getModel().get("error").toString(), equalTo(mensajeError));

    }

    @Test 
    public void queUnaMascotaPuedaAlimentarseYDisminuyaSuHambre () {
        //PREPARACION
        MascotaDTO mascota = new MascotaDTO("nombreCualquiera");
        mascota.setHambre(95);

        ModelAndView modelAndView = controladorMascota.alimentarMascota(mascota);
        String vistaEsperada = "mascota";
        Integer hambreEsperada = 70;

        MascotaDTO mascotaObtenida = (MascotaDTO) modelAndView.getModel().get("mascota");


        assertThat(vistaEsperada, equalTo(modelAndView.getViewName()));
        assertThat(hambreEsperada, equalTo(mascotaObtenida.getHambre()));


    }

}
