package com.example.plazoleta.ms_trazabilidad.domain.ports.in;

import com.example.plazoleta.ms_trazabilidad.application.dto.response.EmployeeEfficiencyResponse;

import java.util.List;

public interface GetEmployeeEfficiencyServicePort {
    List<EmployeeEfficiencyResponse> execute(List<Long> employeeIds);
}
