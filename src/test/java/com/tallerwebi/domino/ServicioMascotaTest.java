package com.tallerwebi.domino;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.tallerwebi.dominio.RepositorioMascota;
import com.tallerwebi.dominio.ServicioMascota;
import com.tallerwebi.dominio.entidades.Mascota;
import com.tallerwebi.dominio.entidades.Usuario;
import com.tallerwebi.dominio.excepcion.MascotaExistente;
import com.tallerwebi.dominio.implementaciones.ServicioMascotaImp;
import com.tallerwebi.presentacion.MascotaDTO;


public class ServicioMascotaTest {
    private RepositorioMascota repositorioMascota;
    private ServicioMascota servicioMascota;

    @BeforeEach
    public void inicializar(){
        this.repositorioMascota = mock(RepositorioMascota.class);
        this.servicioMascota = new ServicioMascotaImp(this.repositorioMascota);
    }

    @Test
    public void cuandoCreoUnaMascotaEntoncesObtengoUnResultadoExitoso() {
        Mascota mascota = new Mascota("Firulais");
        Usuario usuario = new Usuario();
        when(this.repositorioMascota.crear(mascota)).thenReturn(true);

        MascotaDTO mascotaCreadaSatisfactoriamente = this.servicioMascota.crearMascota(mascota.getNombre(), usuario);

        assertThat(mascotaCreadaSatisfactoriamente.getNombre(), equalTo(mascota.getNombre()));
    }



    /*@Test
    public void cuandoCreoUnaMascotaConNombreExistenteEntoncesObtengoUnError() {}
    */

}
