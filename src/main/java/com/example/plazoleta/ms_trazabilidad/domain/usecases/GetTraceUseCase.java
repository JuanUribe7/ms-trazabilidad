package com.example.plazoleta.ms_trazabilidad.domain.usecases;

import com.example.plazoleta.ms_trazabilidad.commons.constants.ExceptionMessages;
import com.example.plazoleta.ms_trazabilidad.commons.exceptions.UnauthorizedAccessException;
import com.example.plazoleta.ms_trazabilidad.domain.model.OrderTraceability;
import com.example.plazoleta.ms_trazabilidad.domain.ports.in.GetTraceServicePort;
import com.example.plazoleta.ms_trazabilidad.domain.ports.out.OrderTracePersistencePort;

public class GetTraceUseCase implements GetTraceServicePort {

    private final OrderTracePersistencePort orderTracePersistence;

    public GetTraceUseCase(OrderTracePersistencePort orderTracePersistence) {
        this.orderTracePersistence = orderTracePersistence;
    }

    @Override
    public OrderTraceability execute(Long orderId, Long clientId) {
        OrderTraceability model = orderTracePersistence.getByOrderId(orderId);

        if(!model.getClientId().equals(String.valueOf(clientId))){
            throw new UnauthorizedAccessException(ExceptionMessages.CLIENT_NOT_OWNER);
        }

        return model;
    }
}
