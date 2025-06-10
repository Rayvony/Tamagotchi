package com.tallerwebi.presentacion;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.tallerwebi.dominio.ServicioMascota;
import com.tallerwebi.dominio.entidades.Usuario;

@Controller
public class ControladorMascota {
    
    private ServicioMascota servicioMascota;

    @Autowired
    public ControladorMascota(ServicioMascota servicioMascota) {
        this.servicioMascota = servicioMascota;
    }
    
    @RequestMapping(path = "/crear-mascota", method = RequestMethod.POST)
    public ModelAndView crearMascota(@RequestParam("nombreMascota") String nombreMascota, Usuario usuario) {

        // PARAMETRO QUE ELIMINE HttpSession session
        //Long id_usuario = (Long) session.getAttribute("id");

        ModelMap modelo = new ModelMap();

        MascotaDTO mascotaDTO = this.servicioMascota.crearMascota(nombreMascota, usuario);

        modelo.put("nombreMascota", mascotaDTO.getNombre());
        return new ModelAndView("redirect:/mascota", modelo);
    }
}
