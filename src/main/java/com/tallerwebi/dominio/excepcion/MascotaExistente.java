package com.tallerwebi.dominio.excepcion;

public class MascotaExistente extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public MascotaExistente(String mensaje) {
        super(mensaje);
    }

}
