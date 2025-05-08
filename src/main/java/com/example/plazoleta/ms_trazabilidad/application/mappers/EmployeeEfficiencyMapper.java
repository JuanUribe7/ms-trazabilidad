package com.example.plazoleta.ms_trazabilidad.application.mappers;


import com.example.plazoleta.ms_trazabilidad.application.dto.response.EmployeeEfficiencyResponse;
import com.example.plazoleta.ms_trazabilidad.application.dto.response.OrderEfficiencyDto;
import com.example.plazoleta.ms_trazabilidad.domain.model.OrderTraceability;

import java.util.ArrayList;
import java.util.List;

public class EmployeeEfficiencyMapper {

    private EmployeeEfficiencyMapper() {
        throw new UnsupportedOperationException("Utility class");
    }

    public static EmployeeEfficiencyResponse map(Long employeeId, List<OrderTraceability> orders) {
        long total = 0;
        List<OrderEfficiencyDto> dtoList = new ArrayList<>();
        for (OrderTraceability o : orders) {
            Long duration = o.getOrderDurationInMilliseconds();
            if (duration != null) {
                total += duration;
                dtoList.add(new OrderEfficiencyDto(o.getOrderId(), duration));
            }
        }
        long avg = dtoList.isEmpty() ? 0 : total / dtoList.size();
        return new EmployeeEfficiencyResponse(employeeId, avg, dtoList);
    }
}
