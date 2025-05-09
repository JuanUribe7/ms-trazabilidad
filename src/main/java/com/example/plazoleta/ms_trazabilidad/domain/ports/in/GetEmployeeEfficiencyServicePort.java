package com.example.plazoleta.ms_trazabilidad.domain.ports.in;

import com.example.plazoleta.ms_trazabilidad.domain.model.EmployeeEfficiency;

import java.util.List;

public interface GetEmployeeEfficiencyServicePort {
    List<EmployeeEfficiency> execute(List<Long> employeeIds);
}
