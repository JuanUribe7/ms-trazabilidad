package com.example.plazoleta.ms_trazabilidad.application.dto.request;

import java.util.List;

public record EmployeeIdsRequest(
    List<Long> employeeIds
) {}
