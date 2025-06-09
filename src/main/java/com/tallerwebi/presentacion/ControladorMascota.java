package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.excepcion.EnergiaInsuficiente;
import com.tallerwebi.dominio.excepcion.MascotaHambrientaException;
import com.tallerwebi.dominio.implementaciones.ServicioMascotaImp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.tallerwebi.dominio.entidades.Mascota;
import com.tallerwebi.dominio.entidades.Usuario;


public class ControladorMascota {

    private ServicioMascotaImp servicioMascota;

    @Autowired
    public ControladorMascota(ServicioMascotaImp servicioMascota) {
        this.servicioMascota = servicioMascota;
    }

    // CREO QUE DEBE ESTAR EN UNA VISTA (PATH) LLAMADA "LOBY" 

    @RequestMapping(path = "/mascota", method = RequestMethod.POST)
    public ModelAndView crearMascota(String nombre, Usuario usuario) {
        ModelMap modelo = new ModelMap();
        try {
        MascotaDTO mascotaCreada = servicioMascota.crearMascota(nombre,usuario);
        modelo.put("nombre", mascotaCreada.getNombre());
        modelo.put("energia", mascotaCreada.getEnergia());
        } catch (Exception mascotaExistenteException) {
            modelo.put("error", mascotaExistenteException.getMessage());
            return new ModelAndView("lobby", modelo);
        }
        return new ModelAndView("mascota", modelo);
    }

    @RequestMapping(path = "/mascota/jugar", method = RequestMethod.POST)
    public ModelAndView jugar(MascotaDTO mascota) {
        ModelMap modelo = new ModelMap();
        try {
            servicioMascota.jugar(mascota);
        } catch (EnergiaInsuficiente energiaInsuficiente) {
            modelo.put("error","No podés jugar, te falta energía");
        } catch (MascotaHambrientaException mascotaHambrientaException) {
            modelo.put("error","No podés jugar, tu mascota esta hambrienta");
        }

        modelo.put("nombre", mascota.getNombre());
        modelo.put("energia", mascota.getEnergia());
        modelo.put("hambre", mascota.getHambre());

        return new ModelAndView("mascota",modelo);
    }

    @RequestMapping(path = "/mascota/alimentar", method = RequestMethod.POST)
    public ModelAndView alimentar(MascotaDTO mascota) {
        ModelMap modelo = new ModelMap();
        MascotaDTO mascotaAlimentada = servicioMascota.alimentar(mascota);
        modelo.put("hambre", mascotaAlimentada.getHambre());
        modelo.put("ultimaAlimentacion", mascotaAlimentada.getUltimaAlimentacion());
        return new ModelAndView("mascota", modelo);
    }
}
