package com.example.plazoleta.ms_trazabilidad.domain.usecases;


import com.example.plazoleta.ms_trazabilidad.domain.model.OrderTraceability;
import com.example.plazoleta.ms_trazabilidad.domain.ports.in.RegisterTrackServicePort;
import com.example.plazoleta.ms_trazabilidad.domain.ports.out.OrderTracePersistencePort;
public class RegisterTrackUseCase implements RegisterTrackServicePort {

    private final OrderTracePersistencePort port;

    public RegisterTrackUseCase(OrderTracePersistencePort port) {
        this.port = port;
    }

    @Override
    public void execute(OrderTraceability orderTraceability) {
        port.save(orderTraceability);
    }
}
