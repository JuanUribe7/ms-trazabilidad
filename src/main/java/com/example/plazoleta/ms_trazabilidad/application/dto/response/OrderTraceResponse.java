package com.example.plazoleta.ms_trazabilidad.application.dto.response;

import java.util.List;

public record OrderTraceResponse(
        Long assignedEmployeeId,
        List<OrderTrackResponse> orderTrack,
        Long orderDurationInMilliseconds
) {
}
