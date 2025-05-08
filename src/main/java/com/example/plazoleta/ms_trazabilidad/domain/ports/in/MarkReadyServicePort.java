package com.example.plazoleta.ms_trazabilidad.domain.ports.in;

public interface MarkReadyServicePort {

    void execute(Long orderId, String phone);

}
