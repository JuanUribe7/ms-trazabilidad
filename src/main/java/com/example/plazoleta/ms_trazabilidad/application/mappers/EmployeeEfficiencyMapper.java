package com.example.plazoleta.ms_trazabilidad.application.mappers;


import com.example.plazoleta.ms_trazabilidad.application.dto.response.EmployeeEfficiencyResponse;
import com.example.plazoleta.ms_trazabilidad.application.dto.response.OrderEfficiencyDto;
import com.example.plazoleta.ms_trazabilidad.domain.model.EmployeeEfficiency;

import java.util.List;

public class EmployeeEfficiencyMapper {

    private EmployeeEfficiencyMapper() {
        throw new UnsupportedOperationException("Utility class");
    }

    public static EmployeeEfficiencyResponse toResponse(EmployeeEfficiency model) {
        List<OrderEfficiencyDto> orders = model.getOrders().stream()
                .map(o -> new OrderEfficiencyDto(o.getOrderId(), o.getDurationInMilliseconds()))
                .toList();

        return new EmployeeEfficiencyResponse(model.getEmployeeId(), model.getAverageEfficiencyInMilliseconds(), orders);
    }

}
