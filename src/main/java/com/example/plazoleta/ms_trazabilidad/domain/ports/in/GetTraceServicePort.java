package com.example.plazoleta.ms_trazabilidad.domain.ports.in;

import com.example.plazoleta.ms_trazabilidad.domain.model.OrderTraceability;

public interface GetTraceServicePort {
    OrderTraceability execute(Long orderId, Long clientId );

}
