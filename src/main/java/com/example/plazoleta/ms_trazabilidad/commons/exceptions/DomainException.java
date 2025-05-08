
package com.example.plazoleta.ms_trazabilidad.commons.exceptions;

public abstract class DomainException extends RuntimeException {
    public DomainException(String message) {
        super(message);
    }
}
