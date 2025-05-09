package com.example.plazoleta.ms_trazabilidad.infrastructure.endpoints.rest;

import com.example.plazoleta.ms_trazabilidad.application.dto.request.*;
import com.example.plazoleta.ms_trazabilidad.application.dto.response.EmployeeEfficiencyResponse;
import com.example.plazoleta.ms_trazabilidad.application.dto.response.OrderTraceResponse;
import com.example.plazoleta.ms_trazabilidad.application.services.TraceService;
import com.example.plazoleta.ms_trazabilidad.infrastructure.config.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class OrderTraceControllerTest {

    private TraceService traceService;
    private JwtUtil jwtUtil;
    private HttpServletRequest request;
    private OrderTraceController controller;

    @BeforeEach
    void setUp() {
        traceService = mock(TraceService.class);
        jwtUtil = mock(JwtUtil.class);
        request = mock(HttpServletRequest.class);
        controller = new OrderTraceController(traceService, jwtUtil);
    }

    @Test
    void shouldRegisterOrder() {
        OrderTraceabilityRequest dto = new OrderTraceabilityRequest(1L, 1L);
        controller.registerOrder(dto);
        verify(traceService).registerOrder(dto);
    }

    @Test
    void shouldAssignOrder() {
        AssignOrderRequest dto = new AssignOrderRequest(1L, 2L);
        controller.assignOrder(dto);
        verify(traceService).assignOrder(dto);
    }

    @Test
    void shouldMarkOrderAsReady() {
        ReadyOrderRequest dto = new ReadyOrderRequest(1L, "3001234567");
        controller.markOrderAsReady(dto);
        verify(traceService).markAsReady(dto);
    }

    @Test
    void shouldDeliverOrder() {
        OrderDeliveryRequest dto = new OrderDeliveryRequest(1L, "1234");
        controller.deliverOrder(dto);
        verify(traceService).deliverOrder(dto);
    }

    @Test
    void shouldCancelOrder() {
        CancelOrderRequest dto = new CancelOrderRequest(1L);
        controller.cancelOrder(dto);
        verify(traceService).cancelOrder(dto);
    }

    @Test
    void shouldGetOrderTrace() {
        Long orderId = 1L;
        String token = "Bearer mock.jwt.token";
        Long clientId = 10L;
        OrderTraceResponse response = new OrderTraceResponse(orderId, List.of(), 4000L);

        when(request.getHeader("Authorization")).thenReturn(token);
        when(jwtUtil.extractUserId("mock.jwt.token")).thenReturn(clientId);
        when(traceService.getOrderTrace(orderId, clientId)).thenReturn(response);

        var result = controller.getOrderTrace(orderId, request);

        assertThat(result.getBody()).isEqualTo(response);
        verify(jwtUtil).extractUserId("mock.jwt.token");
        verify(traceService).getOrderTrace(orderId, clientId);
    }

    @Test
    void shouldGetEmployeesEfficiency() {
        String token = "Bearer mock.jwt.token";
        Long ownerId = 99L;
        EmployeeIdsRequest dto = new EmployeeIdsRequest(List.of(10L, 20L));
        List<EmployeeEfficiencyResponse> list = List.of(
                new EmployeeEfficiencyResponse(10L, 3000L, List.of()),
                new EmployeeEfficiencyResponse(20L, 4000L, List.of())
        );

        when(request.getHeader("Authorization")).thenReturn(token);
        when(jwtUtil.extractUserId("mock.jwt.token")).thenReturn(ownerId);
        when(traceService.getEmployeesEfficiency(dto, ownerId)).thenReturn(list);

        var result = controller.getEfficiency(dto, request);

        assertThat(result.getBody()).hasSize(2);
        verify(traceService).getEmployeesEfficiency(dto, ownerId);
    }
}
