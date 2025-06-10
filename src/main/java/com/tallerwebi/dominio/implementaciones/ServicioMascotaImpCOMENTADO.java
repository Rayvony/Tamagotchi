/*package com.tallerwebi.dominio.implementaciones;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;

import com.tallerwebi.dominio.RepositorioMascota;
import com.tallerwebi.dominio.ServicioMascota;
import com.tallerwebi.dominio.entidades.Mascota;
import com.tallerwebi.dominio.entidades.Usuario;
import com.tallerwebi.dominio.excepcion.MascotaExistente;
import com.tallerwebi.dominio.excepcion.MascotaHambrientaException;
import com.tallerwebi.presentacion.MascotaDTO;

public class ServicioMascotaImp implements ServicioMascota {

    private RepositorioMascota repositorioMascota;

    @Autowired
    public ServicioMascotaImp(RepositorioMascota repositorioMascota) {
        this.repositorioMascota = repositorioMascota;
    }

    @Override
    public MascotaDTO crearMascota(String nombre, Usuario usuario) {
        if (usuario.getMascota() != null) {
            throw new MascotaExistente("Ya contas con tu mascota, debes hacer click en jugar");
        } else {
            Mascota mascota = new Mascota(nombre);
            usuario.setMascota(mascota);
        }

        return new MascotaDTO(nombre);
    }

    @Override
    public MascotaDTO jugar(MascotaDTO mascota) {
        // TAMBIEN DEBE JUGAR CON LA MASCOTA REAL
        Mascota mascotaEntidad = null;

        if (mascota.getHambre() >= 75.0) {
            throw new MascotaHambrientaException("No podés jugar, tu mascota esta hambrienta");
        } else {
            mascota.jugar();
            mascotaEntidad = mascota.aEntidad();
            mascotaEntidad.jugar();
        }

        return mascota;
    }

    @Override
    public MascotaDTO alimentar(MascotaDTO mascota) {

        mascota.alimentar();
        mascota.setUltimaAlimentacion(LocalDateTime.now());

        return mascota;
    }

   @Override
    public void actualizarHambreDeMascotas(){}
 */   
    // METOOD PARA AUMENTAR EL HAMBRE CADA 15 MIN Y EN 2HS SEAN 25.00 PTOS HASTA LLEGAR A 100.00
    //@Scheduled(cron = "0 */15 * * * *") // cada 15 minutos
    //public void actualizarHambreDeMascotas() {
        //List<Mascota> mascotas = repositorioMascota.obtenerTodas();
        //for (Mascota mascota : mascotas) {
      //      double nuevoHambre = Math.min(mascota.getHambre() + 3.125, 100.0);
      //      mascota.setHambre(nuevoHambre);
     //       repositorioMascota.actualizar(mascota);
      //  }
    //}

    

