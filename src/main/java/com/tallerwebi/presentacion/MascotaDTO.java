package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.entidades.Mascota;
import com.tallerwebi.dominio.excepcion.EnergiaInsuficiente;

public class MascotaDTO {
    private Long id;
    private String nombre;
    private Double energia;

    public void setEnergia(Double energia) {
        this.energia = energia;
    }

    public MascotaDTO(String nombre) {
        this.nombre = nombre;
        this.energia = 100.00;
    }

    public MascotaDTO(Mascota mascota) {
        this.id = mascota.getId();
        this.nombre = mascota.getNombre();
        this.energia = mascota.getEnergia();
    }

    public Long getId(){return this.id;};

    public String getNombre() {
        return this.nombre;
    }

    public Double getEnergia() {
        return this.energia;
    }

    public Mascota obtenerEntidad(){
        Mascota mascota = new Mascota();
        return this.obtenerEntidad(mascota);
    }

    public Mascota obtenerEntidad(Mascota mascota){
        if(this.id != null){
            mascota.setId(this.id);
        }
        mascota.setNombre(this.nombre);
        mascota.setEnergia(this.energia);
        return mascota;
    }

}
