package com.example.plazoleta.ms_trazabilidad.domain.ports.out;


import com.example.plazoleta.ms_trazabilidad.domain.model.OrderTraceability;

import java.util.List;

public interface OrderTracePersistencePort {
    void save(OrderTraceability orderTraceability);

    boolean existsByOrderId(String orderId);

    OrderTraceability getByOrderId(Long orderId);

    List<OrderTraceability> findAllDeliveredByEmployeeId(Long employeeId);
}
