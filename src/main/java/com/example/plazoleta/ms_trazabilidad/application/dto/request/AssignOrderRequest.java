package com.example.plazoleta.ms_trazabilidad.application.dto.request;

import jakarta.validation.constraints.NotNull;

public record AssignOrderRequest(
    @NotNull Long orderId,
    @NotNull Long employeeId
) {}
