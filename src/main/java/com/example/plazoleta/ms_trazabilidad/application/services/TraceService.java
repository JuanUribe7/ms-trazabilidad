package com.example.plazoleta.ms_trazabilidad.application.services;

import com.example.plazoleta.ms_trazabilidad.application.dto.request.*;
import com.example.plazoleta.ms_trazabilidad.application.dto.response.EmployeeEfficiencyResponse;
import com.example.plazoleta.ms_trazabilidad.application.dto.response.OrderTraceResponse;

import java.util.List;

public interface TraceService {
    void registerOrder(OrderTraceabilityRequest request);

    void assignOrder(AssignOrderRequest request);

    void markAsReady(ReadyOrderRequest request);

    void deliverOrder(OrderDeliveryRequest request);

    void cancelOrder(CancelOrderRequest request);

    OrderTraceResponse getOrderTrace(Long orderId, Long clientId);

    List<EmployeeEfficiencyResponse> getEmployeesEfficiency(EmployeeIdsRequest request, Long ownerId);
}
