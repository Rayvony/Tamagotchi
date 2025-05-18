package com.tallerwebi.presentacion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.tallerwebi.dominio.ServicioMascotaImp;

public class ControladorMascota {

    private ServicioMascotaImp servicioMascota;

    @Autowired
    public ControladorMascota(ServicioMascotaImp servicioMascota) {
        this.servicioMascota = servicioMascota;
    }

    @RequestMapping(path = "/mascota", method = RequestMethod.POST)
    public ModelAndView crearMascota(String nombre) {
        ModelMap modelo = new ModelMap();
        MascotaDTO mascotaCreada = servicioMascota.crearMascota(nombre);
        modelo.put("nombre", mascotaCreada.getNombre());
        return new ModelAndView("mascota", modelo);

    }

}
