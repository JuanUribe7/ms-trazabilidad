package com.example.plazoleta.ms_trazabilidad.domain.usecases;

import com.example.plazoleta.ms_trazabilidad.application.dto.response.EmployeeEfficiencyResponse;
import com.example.plazoleta.ms_trazabilidad.application.mappers.EmployeeEfficiencyMapper;
import com.example.plazoleta.ms_trazabilidad.domain.model.OrderTraceability;
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
    public List<EmployeeEfficiencyResponse> execute(List<Long> employeeIds) {
        return employeeIds.stream()
                .map(id -> {
                    List<OrderTraceability> orders = port.findAllDeliveredByEmployeeId(id);
                    return EmployeeEfficiencyMapper.map(id, orders);
                })
                .sorted(Comparator.comparingLong(EmployeeEfficiencyResponse::averageEfficiencyInMilliseconds))
                .toList();
    }
}
