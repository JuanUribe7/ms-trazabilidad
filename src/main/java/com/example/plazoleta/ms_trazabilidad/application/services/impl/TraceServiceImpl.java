package com.example.plazoleta.ms_trazabilidad.application.services.impl;

import com.example.plazoleta.ms_trazabilidad.application.dto.request.*;
import com.example.plazoleta.ms_trazabilidad.application.dto.response.EmployeeEfficiencyResponse;
import com.example.plazoleta.ms_trazabilidad.application.dto.response.OrderTraceResponse;
import com.example.plazoleta.ms_trazabilidad.application.mappers.OrderTraceResponseMapper;
import com.example.plazoleta.ms_trazabilidad.application.mappers.OrderTraceabilityDtoMapper;
import com.example.plazoleta.ms_trazabilidad.application.services.TraceService;
import com.example.plazoleta.ms_trazabilidad.domain.model.OrderTraceability;
import com.example.plazoleta.ms_trazabilidad.domain.ports.in.*;
import com.example.plazoleta.ms_trazabilidad.domain.ports.out.RestaurantValidationPort;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Transactional
@RequiredArgsConstructor
@Service
public class TraceServiceImpl implements TraceService {

    private final RegisterTrackServicePort registerService;
    private final UpdateTrackWithEmployeeServicePort updateEmployeeService;
    private final MarkReadyServicePort markReadyService;
    private final DeliverTrackServicePort deliverService;
    private final CancelOrderServicePort cancelService;

    private final GetTraceServicePort getTraceService;
    private final GetEmployeeEfficiencyServicePort getEmployeeEfficiencyService;
    private final RestaurantValidationPort restaurantValidationPort;

    @Override
    public void registerOrder(OrderTraceabilityRequest request) {
        OrderTraceability model = OrderTraceabilityDtoMapper.toModel(request);
        registerService.execute(model);
    }

    @Override
    public void assignOrder(AssignOrderRequest request) {
        updateEmployeeService.execute(request.orderId(), request.employeeId());
    }

    @Override
    public void markAsReady(ReadyOrderRequest request) {
        markReadyService.execute(request.orderId(), request.phoneNumber());
    }

    @Override
    public void deliverOrder(OrderDeliveryRequest request) {
        deliverService.execute(request.orderId(), request.pin());
    }

    @Override
    public void cancelOrder(CancelOrderRequest request) {
        cancelService.execute(request.orderId());
    }

    @Override
    public OrderTraceResponse getOrderTrace(Long orderId, Long clientId) {

        return OrderTraceResponseMapper.toResponse(getTraceService.execute(orderId, clientId));
    }

    @Override
    public List<EmployeeEfficiencyResponse> getEmployeesEfficiency(EmployeeIdsRequest request, Long ownerId) {
        List<Long> validEmployeeIds = restaurantValidationPort.getEmployeeIdsByOwner(ownerId);
        List<Long> filtered = request.employeeIds().stream()
                .filter(validEmployeeIds::contains)
                .toList();
        return getEmployeeEfficiencyService.execute(filtered);
    }


}
