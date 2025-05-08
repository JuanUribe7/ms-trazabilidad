package com.example.plazoleta.ms_trazabilidad.infrastructure.endpoints.rest;


import com.example.plazoleta.ms_trazabilidad.application.dto.request.*;
import com.example.plazoleta.ms_trazabilidad.application.dto.response.EmployeeEfficiencyResponse;
import com.example.plazoleta.ms_trazabilidad.application.dto.response.OrderTraceResponse;
import com.example.plazoleta.ms_trazabilidad.application.services.TraceService;
import com.example.plazoleta.ms_trazabilidad.infrastructure.config.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/tracks")
public class OrderTraceController {

    private final TraceService traceService;
    private final JwtUtil jwtUtil;


    @PreAuthorize("hasRole('CLIENT')")
    @PostMapping
    public ResponseEntity<Void> registerOrder(@RequestBody @Valid OrderTraceabilityRequest request) {


        traceService.registerOrder(request);
        return ResponseEntity.ok().build();
    }


    @PreAuthorize("hasRole('EMPLOYEE')")
    @PutMapping("/assign")
    public ResponseEntity<Void> assignOrder(@RequestBody AssignOrderRequest request) {
        traceService.assignOrder(request);
        return ResponseEntity.ok().build();
    }


    @PreAuthorize("hasRole('EMPLOYEE')")
    @PutMapping("/ready")
    public ResponseEntity<Void> markOrderAsReady(@RequestBody ReadyOrderRequest request) {
        traceService.markAsReady(request);
        return ResponseEntity.ok().build();
    }


    @PreAuthorize("hasRole('EMPLOYEE')")
    @PutMapping("/deliver")
    public ResponseEntity<Void> deliverOrder(@RequestBody OrderDeliveryRequest request) {
        traceService.deliverOrder(request);
        return ResponseEntity.ok().build();
    }


    @PreAuthorize("hasRole('EMPLOYEE')")
    @PutMapping("/cancel")
    public ResponseEntity<Void> cancelOrder(@RequestBody CancelOrderRequest request) {
        traceService.cancelOrder(request);
        return ResponseEntity.ok().build();
    }

    @PreAuthorize("hasRole('CLIENT')")
    @GetMapping("/{orderId}")
    public ResponseEntity<OrderTraceResponse> getOrderTrace(
            @PathVariable Long orderId,
            HttpServletRequest request) {
        String token = request.getHeader("Authorization").substring(7);
        Long clientId = jwtUtil.extractUserId(token);

        return ResponseEntity.ok(traceService.getOrderTrace(orderId, clientId));
    }


    @PreAuthorize("hasRole('OWNER')")
    @PostMapping("/employees/efficiency")
    public ResponseEntity<List<EmployeeEfficiencyResponse>> getEfficiency(
            @RequestBody EmployeeIdsRequest employeeIds,
            HttpServletRequest request
            ) {
        String token = request.getHeader("Authorization").substring(7);
        Long ownerId = jwtUtil.extractUserId(token);
        return ResponseEntity.ok(traceService.getEmployeesEfficiency(employeeIds, ownerId));
    }












}
