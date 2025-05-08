package com.example.plazoleta.ms_trazabilidad.application.mappers;

import com.example.plazoleta.ms_trazabilidad.application.dto.response.OrderTrackResponse;
import com.example.plazoleta.ms_trazabilidad.domain.model.OrderTrackingModel;

public class OrderTrackDtoMapper {

    private OrderTrackDtoMapper() {
        throw new UnsupportedOperationException("Utility class");
    }

    public static OrderTrackResponse toResponse(OrderTrackingModel model) {
        return new OrderTrackResponse(
                model.getTimeStamp(),
                model.getState()
        );
    }
}
