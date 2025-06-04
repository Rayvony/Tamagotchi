package com.tallerwebi.dominio.excepcion;

public class MascotaHambrientaException extends RuntimeException {   
    private static final long serialVersionUID = 1L;

    public MascotaHambrientaException(String mensajeDeErrorEsperado) {
        super(mensajeDeErrorEsperado);
    }

}
