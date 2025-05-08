package com.example.plazoleta.ms_trazabilidad.application.mappers;

import com.example.plazoleta.ms_trazabilidad.application.dto.request.OrderTraceabilityRequest;
import com.example.plazoleta.ms_trazabilidad.domain.model.OrderStates;
import com.example.plazoleta.ms_trazabilidad.domain.model.OrderTraceability;
import com.example.plazoleta.ms_trazabilidad.domain.model.OrderTrackingModel;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class OrderTraceabilityDtoMapper {

    private OrderTraceabilityDtoMapper() {
        throw new UnsupportedOperationException("Utility class");
    }

    public static OrderTraceability toModel(OrderTraceabilityRequest request) {

        List<OrderTrackingModel> tracking = new ArrayList<>();
        tracking.add(new OrderTrackingModel(OrderStates.PENDING, Instant.now()));
        return new OrderTraceability(
                null,
                null,
                String.valueOf(request.clientId()),
                String.valueOf(request.orderId()),
                null,
                null,
                tracking

        );


    }


}
