package com.example.plazoleta.ms_trazabilidad.domain.ports.in;

public interface DeliverTrackServicePort {

    void execute(Long orderId, String pin);
}
