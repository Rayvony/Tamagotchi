package com.tallerwebi.presentacion;

import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

import com.tallerwebi.dominio.ServicioMascotaImp;
import com.tallerwebi.dominio.Usuario;
import com.tallerwebi.dominio.excepcion.MascotaExistenteExcepction;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

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
    public void queAlSolicitarLaHigieneLaDevuelveCorrectamente() throws MascotaExistenteExcepction {
        //PREPARACION
        String vistaEsperada = "mascota";
        Double higieneEsperada = 100.0;

        MascotaDTO mascota = servicioMascota.crearMascota("charly", new Usuario());

        //EJECUCION
        ModelAndView mav = controladorMascota.verHigiene(mascota);

        //VERIFICACION
        assertThat(mav.getViewName(), equalTo(vistaEsperada));
        assertThat(mav.getModel().get("higiene"), equalTo(higieneEsperada));
    }

    @Test
    public void queAlHigienizarseElValorDeHigieneVuelvaACien() throws MascotaExistenteExcepction {
        // PREPARACION
        String vistaEsperada = "mascota";
        MascotaDTO mascota = servicioMascota.crearMascota("tama", new Usuario());

        //Simular Higiene vieja
        mascota.setUltimaHigiene(LocalDateTime.now().minusHours(5));

        //EJECUCION
        ModelAndView mav = controladorMascota.higienizar(mascota);

        //VERIFICACION
        assertThat(mav.getViewName(), equalTo(vistaEsperada));
        assertThat(mav.getModel().get("higiene"), equalTo(100.0));


    }



}
