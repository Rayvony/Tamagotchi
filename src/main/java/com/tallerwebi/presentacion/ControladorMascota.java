package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.excepcion.EnergiaInsuficiente;
import com.tallerwebi.dominio.excepcion.ServicioMascota;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class ControladorMascota {

    private ServicioMascota servicioMascota;
    private ModelMap modelo = new ModelMap();
    private MascotaDTO mascota;

    @Autowired
    public ControladorMascota(ServicioMascota servicioMascota) {
        this.servicioMascota = servicioMascota;
    }

    @RequestMapping(path = "/mascota", method = RequestMethod.GET)
    public ModelAndView crearMascotaConGet() {
        String nombre = "tamagotcha";

        this.mascota = servicioMascota.crearMascota(nombre);
        modelo.put("nombre", mascota.getNombre());
        modelo.put("energia", mascota.getEnergia());
        return new ModelAndView("mascota", modelo);
    }

    @RequestMapping(path = "/mascotapost", method = RequestMethod.POST)
    public ModelAndView crearMascota(String nombre) {
        ModelMap modelo = new ModelMap();
        MascotaDTO mascotaCreada = servicioMascota.crearMascota(nombre);
        modelo.put("nombre", mascotaCreada.getNombre());
        modelo.put("energia", mascotaCreada.getEnergia());
        return new ModelAndView("mascota", modelo);
    }

    @RequestMapping(path = "/mascota/jugar", method = RequestMethod.POST)
    public ModelAndView jugar() {

        try {
            servicioMascota.jugar(this.mascota);
        } catch (EnergiaInsuficiente energiaInsuficiente) {
            modelo.put("error","No podés jugar, te falta energía");
        }

        //modelo.put("nombre", mascota.getNombre());
        modelo.put("energia", this.mascota.getEnergia());

        return new ModelAndView("mascota",modelo);
    }
}
