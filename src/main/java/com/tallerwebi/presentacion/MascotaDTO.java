package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.Mascota;

public class MascotaDTO {

    private String nombre;
    private Integer hambre;

    public MascotaDTO(String nombre) {
        this.nombre = nombre;
    }

   

    public String getNombre() {
        return this.nombre;
    }

    public void setHambre(Integer hambre) {
        this.hambre = hambre;
    }

    public Integer getHambre() {
        return this.hambre;
    }

     public MascotaDTO(Mascota mascota) {
        this.nombre = mascota.getNombre();
        this.hambre = mascota.getHambre();
    }

    public Mascota aEntidad (){
        Mascota mascota = new Mascota(this.nombre);
        mascota.setHambre(this.hambre);
        return mascota;
    }

}
