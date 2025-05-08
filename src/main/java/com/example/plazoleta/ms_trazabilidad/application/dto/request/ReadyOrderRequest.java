package com.example.plazoleta.ms_trazabilidad.application.dto.request;

import jakarta.validation.constraints.NotNull;

public record ReadyOrderRequest(
    @NotNull Long orderId,
    @NotNull String phoneNumber
) {}
