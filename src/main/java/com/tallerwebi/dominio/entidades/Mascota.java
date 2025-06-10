package com.tallerwebi.dominio.entidades;

import com.tallerwebi.dominio.Usuario;

import javax.persistence.*;

@Entity

public class Mascota {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, nullable = false)
    private String nombre;
    @Column
    private Double energia;

    @ManyToOne
    Usuario usuario;

    public Mascota(){};
    public Mascota(Long id, String nombre, Double energia) {
        this.id = id;
        this.nombre = nombre;
        this.energia = energia;
    }

    public Long getId() {
        return this.id;
    }

    public String getNombre() {
        return this.nombre;
    }

    public Double getEnergia() {
        return energia;
    }

    public void setEnergia(Double energia) {
        this.energia = energia;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


}
