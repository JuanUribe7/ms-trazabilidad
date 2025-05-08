package com.example.plazoleta.ms_trazabilidad.domain.ports.in;

public interface CancelOrderServicePort {

    void execute(Long orderId);
}
