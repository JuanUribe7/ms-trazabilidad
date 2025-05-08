package com.example.plazoleta.ms_trazabilidad.application.dto.response;

import java.util.List;

public record EmployeeEfficiencyResponse(
        Long employeeId,
        Long averageEfficiencyInMilliseconds,
        List<OrderEfficiencyDto> orders
) {
}
