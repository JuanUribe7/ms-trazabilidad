package com.example.plazoleta.ms_trazabilidad.application.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record OrderDeliveryRequest(
    @NotNull Long orderId,
    @NotBlank String pin
) {}
