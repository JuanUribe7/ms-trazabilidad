package com.example.plazoleta.ms_trazabilidad.infrastructure.mappers;

import com.example.plazoleta.ms_trazabilidad.domain.model.OrderTraceability;
import com.example.plazoleta.ms_trazabilidad.domain.model.OrderTrackingModel;
import com.example.plazoleta.ms_trazabilidad.infrastructure.documents.OrderTraceabilityDocument;
import com.example.plazoleta.ms_trazabilidad.infrastructure.documents.OrderTracking;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderTraceabilityEntityMapper {

    public OrderTraceabilityDocument toDocument(OrderTraceability model) {
        List<OrderTracking> trackings = model.getOrderTrack().stream()
                .map(t -> new OrderTracking(
                        t.getTimeStamp() != null ? t.getTimeStamp() : Instant.now(),
                        t.getState()
                ))
                .toList();

        return new OrderTraceabilityDocument(
                model.getId(),
                model.getAssignedEmployeeId(),
                model.getClientId(),
                model.getOrderId(),
                model.getSecurityPin(),
                model.getOrderDurationInMilliseconds(),
                trackings
        );
    }

    public OrderTraceability toModel(OrderTraceabilityDocument document) {
        List<OrderTrackingModel> trackings = document.getOrderTrack().stream()
                .map(t -> new OrderTrackingModel(t.getState(), t.getTimeStamp()))
                .collect(Collectors.toList());

        return new OrderTraceability(
                document.getId(),
                document.getAssignedEmployeeId(),
                document.getClientId(),
                document.getOrderId(),
                document.getSecurityPin(),
                document.getOrderDurationInMilliseconds(),
                trackings
        );
    }

    private Long parseLong(String value) {
        try {
            return value != null ? Long.parseLong(value) : null;
        } catch (NumberFormatException e) {
            return null;
        }
    }
}
