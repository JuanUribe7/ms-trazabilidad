package com.example.plazoleta.ms_trazabilidad.domain.helpers;

import com.example.plazoleta.ms_trazabilidad.commons.constants.ExceptionMessages;
import com.example.plazoleta.ms_trazabilidad.domain.model.EmployeeEfficiency;
import com.example.plazoleta.ms_trazabilidad.domain.model.OrderEfficiency;
import com.example.plazoleta.ms_trazabilidad.domain.model.OrderTraceability;

import java.util.List;

public class EmployeeEfficiencyHelper {

    private EmployeeEfficiencyHelper() {
        throw new UnsupportedOperationException(ExceptionMessages.UTILITY_CLASS_INSTANTIATION);
    }

    public static EmployeeEfficiency buildEfficiency(Long employeeId, List<OrderTraceability> orders) {
        long total = 0;
        List<OrderEfficiency> efficiencies = new java.util.ArrayList<>();

        for (OrderTraceability o : orders) {
            Long duration = o.getOrderDurationInMilliseconds();
            if (duration != null) {
                total += duration;
                efficiencies.add(new OrderEfficiency(Long.parseLong(o.getOrderId()), duration));
            }
        }

        long avg = efficiencies.isEmpty() ? 0 : total / efficiencies.size();
        return new EmployeeEfficiency(employeeId, avg, efficiencies);
    }

}
