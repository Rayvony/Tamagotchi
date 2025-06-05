package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.ServicioMascota;
import com.tallerwebi.dominio.entidades.Mascota;
import com.tallerwebi.dominio.excepcion.EnergiaInsuficiente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


@Controller
public class ControladorMascota {

    private ServicioMascota servicioMascota;
    private ModelMap modelo = new ModelMap();
    private MascotaDTO mascota;

    @Autowired
    public ControladorMascota(ServicioMascota servicioMascota) {
        this.servicioMascota = servicioMascota;
    }

    @RequestMapping(path = "/mascota/crearconpost", method = RequestMethod.POST)
    public ModelAndView crearMascota(String nombre) {
        //crea el DTO esto es necesario?
        this.mascota = servicioMascota.crearMascota(nombre);
        //guarda en bd
        this.servicioMascota.crear(this.mascota);
        modelo.put("nombre", mascota.getNombre());
        modelo.put("energia", mascota.getEnergia());
        return new ModelAndView("mascota", modelo);
    }

    @RequestMapping(path = "/mascota/jugar", method = RequestMethod.POST)
    public ModelAndView jugar() {

        try {
            servicioMascota.jugar(this.mascota);
        } catch (EnergiaInsuficiente energiaInsuficiente) {
            modelo.put("error","No podés jugar, te falta energía");
        }

        this.servicioMascota.actualizarMascota(mascota);
        modelo.put("energia", this.mascota.getEnergia());

        return new ModelAndView("mascota",modelo);
    }

    @RequestMapping(path = "/mascota/traerlistado", method = RequestMethod.GET)
    public ModelAndView mostrarListadoDeMascotas() {
        List<Mascota> mascotas = this.servicioMascota.traerMascotas();
        modelo.put("mascotas",mascotas);
        return new ModelAndView("inicio", modelo);
    }

    @RequestMapping(path = "/mascota/ver", method = RequestMethod.POST)
    public ModelAndView verMascota(Long id) {

        this.mascota = servicioMascota.traerUnaMascota(id);

        modelo.put("nombre", mascota.getNombre());
        modelo.put("energia", mascota.getEnergia());
        return new ModelAndView("mascota", modelo);
    }
}
