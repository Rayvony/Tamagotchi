package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.Usuario;
import com.tallerwebi.dominio.excepcion.EnergiaInsuficiente;
import com.tallerwebi.dominio.excepcion.MascotaExistenteExcepction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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

    @PostMapping("/higienizar")
    public ModelAndView higienizar(MascotaDTO mascota) {
        servicioMascota.higienizarMascota(mascota);
        ModelAndView mav = new ModelAndView("mascota");
        mav.addObject("higiene", 100.0);
        return mav;
    }

    @GetMapping("/higiene")
    public ModelAndView verHigiene(MascotaDTO mascota) {
        ModelAndView mav = new ModelAndView("mascota");
        Double higiene = servicioMascota.verHigiene(mascota);
        mav.addObject("higiene", higiene);
        return mav;
    }

}
