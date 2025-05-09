package com.example.plazoleta.ms_trazabilidad.application.services.impl;

import com.example.plazoleta.ms_trazabilidad.domain.model.OrderStates;
import com.example.plazoleta.ms_trazabilidad.domain.model.OrderTraceability;
import com.example.plazoleta.ms_trazabilidad.domain.model.OrderTrackingModel;
import com.example.plazoleta.ms_trazabilidad.infrastructure.adapters.persistence.OrderTraceabilityMongoAdapter;
import com.example.plazoleta.ms_trazabilidad.infrastructure.documents.OrderTraceabilityDocument;
import com.example.plazoleta.ms_trazabilidad.infrastructure.mappers.OrderTraceabilityEntityMapper;
import com.example.plazoleta.ms_trazabilidad.infrastructure.repositories.OrderTraceabilityRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class OrderTraceabilityMongoAdapterTest {

    private OrderTraceabilityRepository repository;
    private OrderTraceabilityEntityMapper mapper;
    private OrderTraceabilityMongoAdapter adapter;

    @BeforeEach
    void setUp() {
        repository = mock(OrderTraceabilityRepository.class);
        mapper = mock(OrderTraceabilityEntityMapper.class);
        adapter = new OrderTraceabilityMongoAdapter(repository, mapper);
    }

    @Test
    void should_return_only_delivered_orders_by_employee_id() {
        // Arrange
        String employeeId = "10";

        OrderTraceabilityDocument doc1 = mock(OrderTraceabilityDocument.class);
        OrderTraceabilityDocument doc2 = mock(OrderTraceabilityDocument.class);

        OrderTrackingModel tracking1 = new OrderTrackingModel(null, OrderStates.DELIVERED);
        OrderTrackingModel tracking2 = new OrderTrackingModel(null, OrderStates.READY);

        OrderTraceability trace1 = mock(OrderTraceability.class);
        OrderTraceability trace2 = mock(OrderTraceability.class);

        when(repository.findByAssignedEmployeeId(employeeId)).thenReturn(List.of(doc1, doc2));
        when(mapper.toModel(doc1)).thenReturn(trace1);
        when(mapper.toModel(doc2)).thenReturn(trace2);

        when(trace1.getOrderTrack()).thenReturn(List.of(tracking1)); // DELIVERED
        when(trace2.getOrderTrack()).thenReturn(List.of(tracking2)); // READY

        // Act
        List<OrderTraceability> result = adapter.findAllDeliveredByEmployeeId(10L);

        // Assert
        assertThat(result).containsExactly(trace1); // only delivered
        verify(repository).findByAssignedEmployeeId("10");
        verify(mapper).toModel(doc1);
        verify(mapper).toModel(doc2);
    }


}
