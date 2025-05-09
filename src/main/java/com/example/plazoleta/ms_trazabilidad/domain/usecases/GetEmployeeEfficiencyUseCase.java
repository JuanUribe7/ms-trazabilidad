package com.example.plazoleta.ms_trazabilidad.domain.usecases;

import com.example.plazoleta.ms_trazabilidad.domain.helpers.EmployeeEfficiencyHelper;
import com.example.plazoleta.ms_trazabilidad.domain.model.EmployeeEfficiency;
import com.example.plazoleta.ms_trazabilidad.domain.ports.in.GetEmployeeEfficiencyServicePort;
import com.example.plazoleta.ms_trazabilidad.domain.ports.out.OrderTracePersistencePort;

import java.util.Comparator;
import java.util.List;

public class GetEmployeeEfficiencyUseCase implements GetEmployeeEfficiencyServicePort {

    private final OrderTracePersistencePort port;

    public GetEmployeeEfficiencyUseCase(OrderTracePersistencePort port) {
        this.port = port;
    }

    @Override
    public List<EmployeeEfficiency> execute(List<Long> employeeIds) {
        return employeeIds.stream()
                .map(id -> EmployeeEfficiencyHelper.buildEfficiency(id, port.findAllDeliveredByEmployeeId(id)))
                .sorted(Comparator.comparingLong(EmployeeEfficiency::getAverageEfficiencyInMilliseconds))
                .toList();
    }
}
