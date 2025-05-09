package com.example.plazoleta.ms_trazabilidad.domain.model;

import java.util.List;

public class EmployeeEfficiency {

    private final Long employeeId;
    private final Long averageEfficiencyInMilliseconds;
    private final List<OrderEfficiency> orders;

    public EmployeeEfficiency(Long employeeId, Long averageEfficiencyInMilliseconds, List<OrderEfficiency> orders) {
        this.employeeId = employeeId;
        this.averageEfficiencyInMilliseconds = averageEfficiencyInMilliseconds;
        this.orders = orders;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public Long getAverageEfficiencyInMilliseconds() {
        return averageEfficiencyInMilliseconds;
    }

    public List<OrderEfficiency> getOrders() {
        return orders;
    }
}
