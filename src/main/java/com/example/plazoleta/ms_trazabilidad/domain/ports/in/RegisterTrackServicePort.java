package com.example.plazoleta.ms_trazabilidad.domain.ports.in;


import com.example.plazoleta.ms_trazabilidad.domain.model.OrderTraceability;

public interface RegisterTrackServicePort {

    void execute(OrderTraceability orderTraceability);
}
