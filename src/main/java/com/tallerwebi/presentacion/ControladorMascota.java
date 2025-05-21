package com.tallerwebi.presentacion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.tallerwebi.dominio.ServicioMascotaImp;
import com.tallerwebi.dominio.Usuario;
import com.tallerwebi.dominio.excepcion.MascotaExistenteExcepction;

public class ControladorMascota {

    private ServicioMascotaImp servicioMascota;

    @Autowired
    public ControladorMascota(ServicioMascotaImp servicioMascota) {
        this.servicioMascota = servicioMascota;
    }

    @RequestMapping(path = "/lobby", method = RequestMethod.POST)
    public ModelAndView crearMascota(String nombre, Usuario usuario) throws MascotaExistenteExcepction {
        ModelMap modelo = new ModelMap();
        try {
            MascotaDTO mascotaCreada = servicioMascota.crearMascota(nombre, usuario);
            modelo.put("nombre", mascotaCreada.getNombre());
            return new ModelAndView("mascota", modelo);
        } catch (MascotaExistenteExcepction e) {
            modelo.put("error", e.getMessage());
            return new ModelAndView("lobby", modelo);
        }

    }





}
