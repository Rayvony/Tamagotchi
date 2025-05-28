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
        }

        modelo.put("nombre", mascota.getNombre());
        modelo.put("energia", mascota.getEnergia());

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
