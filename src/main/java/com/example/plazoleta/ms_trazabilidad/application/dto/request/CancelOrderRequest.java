package com.example.plazoleta.ms_trazabilidad.application.dto.request;

import jakarta.validation.constraints.NotNull;

public record CancelOrderRequest(
    @NotNull Long orderId
) {}
