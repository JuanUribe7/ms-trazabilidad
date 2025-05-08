package com.example.plazoleta.ms_trazabilidad.application.dto.response;


import com.example.plazoleta.ms_trazabilidad.domain.model.OrderStates;

import java.time.Instant;

public record OrderTrackResponse(
        Instant timeStamp,
        OrderStates state
) {

}
