package com.example.plazoleta.ms_trazabilidad.application.mappers;

import com.example.plazoleta.ms_trazabilidad.application.dto.response.OrderTraceResponse;
import com.example.plazoleta.ms_trazabilidad.domain.model.OrderTraceability;

import java.util.stream.Collectors;

public class OrderTraceResponseMapper {

    private OrderTraceResponseMapper() {
        throw new UnsupportedOperationException("Utility class");
    }

    public static OrderTraceResponse toResponse(OrderTraceability model) {
        return new OrderTraceResponse(
                model.getAssignedEmployeeId(),
                model.getOrderTrack().stream()
                        .map(OrderTrackDtoMapper::toResponse)
                        .collect(Collectors.toList()),
                model.getOrderDurationInMilliseconds()
        );
    }
}
