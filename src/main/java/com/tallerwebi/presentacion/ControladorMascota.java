package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.excepcion.EnergiaInsuficiente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.tallerwebi.dominio.ServicioMascotaImp;
import com.tallerwebi.dominio.Usuario;

public class ControladorMascota {

    private ServicioMascotaImp servicioMascota;

    @Autowired
    public ControladorMascota(ServicioMascotaImp servicioMascota) {
        this.servicioMascota = servicioMascota;
    }

    @RequestMapping(path = "/mascota", method = RequestMethod.POST)
    public ModelAndView crearMascota(String nombre, Usuario usuario) {
        ModelMap modelo = new ModelMap();
        MascotaDTO mascotaCreada = servicioMascota.crearMascota(nombre,usuario);
        modelo.put("nombre", mascotaCreada.getNombre());
        modelo.put("energia", mascotaCreada.getEnergia());
        return new ModelAndView("mascota", modelo);
    }

    @RequestMapping(path = "/mascota/jugar", method = RequestMethod.POST)
    public ModelAndView jugar(MascotaDTO mascota) {
        ModelMap modelo = new ModelMap();
        try {
            servicioMascota.jugar(mascota);
        } catch (EnergiaInsuficiente energiaInsuficiente) {
            modelo.put("error","No podés jugar, te falta energía");
        }

        modelo.put("nombre", mascota.getNombre());
        modelo.put("energia", mascota.getEnergia());

        return new ModelAndView("mascota",modelo);
    }
}
