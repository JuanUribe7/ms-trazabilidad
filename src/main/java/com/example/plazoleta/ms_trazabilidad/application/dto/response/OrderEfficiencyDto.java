package com.example.plazoleta.ms_trazabilidad.application.dto.response;

public record OrderEfficiencyDto(
    String orderId,
    Long durationInMilliseconds
) {}
