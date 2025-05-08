package com.example.plazoleta.ms_trazabilidad.application.dto.response;

import java.util.List;

public record OrderTraceResponse(
        String assignedEmployeeId,
        List<OrderTrackResponse> orderTrack,
        Long orderDurationInMilliseconds
) {
}
